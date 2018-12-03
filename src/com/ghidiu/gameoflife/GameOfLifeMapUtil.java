package com.ghidiu.gameoflife;

/**
 * Utility class for the "game of life"; functions do not mutate parameters.
 *
 * @author jghidiu
 */
public final class GameOfLifeMapUtil {

    /**
     * Creates a map with initial values.
     *
     * @param rows the number of rows for the map
     * @param columns the number of columns for the map
     * @param initialValue the initial value for each cell in the map
     * @return a map of the specified size and initial values
     */
    public final static boolean[][] createMap(final int rows, final int columns, final boolean initialValue) {
        final boolean[][] map = new boolean[rows][columns];

        // Initialize to false
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                setLife(row, column, initialValue, map);
            }
        }

        return map;
    }

    /**
     * Creates a map based on the dimensions of an existing map.
     *
     * @param map the map whose dimensions to use
     * @param initialValue the initial value for each cell in the map
     * @return a map of the specified size and initial values
     */
    public final static boolean[][] createMap(final boolean[][] map, final boolean initialValue) {
        // Edge case
        if (0 == map.length) {
            return new boolean[0][0];
        }

        // Return the result of createMap(rows, columns, initialValue)
        return createMap(map.length, map[0].length, initialValue);
    }

    /**
     * Clones a map.
     *
     * @param map the map to clone
     * @return a map which is identical to the passed in map
     */
    public final static boolean[][] cloneMap(final boolean[][] map) {
        final boolean[][] clonedMap = createMap(map, false);

        // Copy values
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                setLife(row, column, getLife(row, column, map), clonedMap);
            }
        }

        return clonedMap;
    }

    /**
     * Determines if a map has life (that is, is at least one cell alive)?
     *
     * @param map the map to interrogate
     * @return true if at least one cell is alive; false otherwise
     */
    public final static boolean hasLife(final boolean[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                if (getLife(row, column, map)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Runs a day simulation.
     *
     * <code>
     * if (the cell is alive on the previous day) {
     *   if (the number of neighbors was 2 or 3) {
     *     the cell remains alive
     *   } else {
     *     the cell dies
     *   }
     * } else if (the cell is not alive on the previous day) {
     *   if (the number of neighbors was exactly 3) {
     *     the cell becomes alive
     *   } else {
     *     the cell remains dead
     *   }
     * }
     * </code>
     *
     * @param map the map which represents the starting state of the simulation
     * @return a map which represents the end of the simulation
     */
    public final static boolean[][] runDay(final boolean[][] map) {
        // Get a copy of "yesterday"
        final boolean[][] yesterdayMap = cloneMap(map);
        final boolean[][] todayMap = cloneMap(yesterdayMap);

        for (int row = 0; row < yesterdayMap.length; row++) {
            for (int column = 0; column < yesterdayMap[row].length; column++) {
                if (getLife(row, column, yesterdayMap)) {
                    int neighborCount = getLivingNeighborCount(row, column, yesterdayMap);
                    if (2 != neighborCount && 3 != neighborCount) {
                        setLife(row, column, false, todayMap);
                    }
                } else {
                    if (3 == getLivingNeighborCount(row, column, yesterdayMap)) {
                        setLife(row, column, true, todayMap);
                    }
                }
            }
        }

        return todayMap;
    }

    /**
     * Gets the count of living neighbors. This counts all adjacent cells (vertical, horizontal and diagonal).
     *
     * @param row the row coordinate of the cell to interrogate
     * @param column the column coordinate of the cell to interrogate
     * @param map the map which the cell resides on
     * @return the number of direct neighbors
     */
    public final static int getLivingNeighborCount(final int row, final int column, boolean[][] map) {
        // The counter
        int neighborCount = 0;

        // Check the row above (if this row is not the first row)
        if (row > 0) {
            // XOO - left most cell (diagonally to the left)
            if (column > 0 && getLife(row - 1, column - 1, map)) {
                neighborCount++;
            }
            // OXO - center cell (directly above)
            if (getLife(row - 1, column, map)) {
                neighborCount++;
            }
            // OOX - right most cell (diagonally to the right)
            if (column < map[row].length - 1 && getLife(row - 1, column + 1, map)) {
                neighborCount++;
            }
        }


        // Check the current row
        // X_O - the cell to the left
        if (column > 0 && getLife(row, column - 1, map)) {
            neighborCount++;
        }
        // O_X - the cell to the right
        if (column < map[row].length - 1 && getLife(row, column + 1, map)) {
            neighborCount++;
        }


        // Check the row below (if this row is not the last row)
        if (row < map.length - 1) {
            // XOO - the cell to the lower left (diagonally)
            if (column > 0 && getLife(row + 1, column - 1, map)) {
                neighborCount++;
            }
            // OXO - the cell directly below the interrogation cell
            if (getLife(row + 1, column, map)) {
                neighborCount++;
            }
            // OOX - the cell to the lower right (diagonally)
            if (column < map[row].length - 1 && getLife(row + 1, column + 1, map)) {
                neighborCount++;
            }
        }

        // Return the count
        return neighborCount;
    }

    /**
     * Sets the aliveness of a cell.
     *
     * @param row the row coordinate of the cell to set life status
     * @param column the column coordinate of the cell to set life status
     * @param alive the aliveness of the cell
     * @param map the map on which the board resides
     */
    public final static void setLife(final int row, final int column, final boolean alive, boolean[][] map) {
        map[row][column] = alive;
    }

    /**
     * Determines if a cell is alive or dead.
     *
     * @param row the row coordinate of the cell to interrogate
     * @param column the column coordinate of the cell to interrogate
     * @param map the map in which the cell resides
     * @return true, if the specified cell is alive; false otherwise
     */
    public final static boolean getLife(final int row, final int column, boolean[][] map) {
        return map[row][column];
    }

    /**
     * Returns a pretty representation of the map.
     * @param map the map to format
     * @return a pretty representation of the map
     */
    public final static String toString(final boolean[][] map) {
        final StringBuffer stringBuffer = new StringBuffer();

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                stringBuffer.append(getLife(row, column, map) ? "X" : "O");
            }
            stringBuffer.append("\n");
        }

        return stringBuffer.toString();
    }

}
