package com.ghidiu.gameoflife;

/**
 * Encapsulates the "game of life", providing an abstraction of the game board and methods allowed.
 *
 * @author jghidiu
 */
public final class GameOfLife {

    // The life map
    private boolean[][] lifeMap;


    /**
     * Creates an instance of the game.
     *
     * @param rows the number of rows to use
     * @param columns the number of columns to use
     */
    public GameOfLife(final int rows, final int columns) {
        this.lifeMap = GameOfLifeMapUtil.createMap(rows, columns, false);
    }

    /**
     * Adds life to the board in a specified location.
     *
     * @param row the row coordinate to add life to
     * @param column the column coordinate to add life to
     */
    public final void addLife(final int row, final int column) {
        GameOfLifeMapUtil.setLife(row, column, true, this.lifeMap);
    }

    /**
     * Determines if life exists in this game.
     *
     * @return true, if life exists; false otherwise
     */
    public final boolean hasLife() {
        return GameOfLifeMapUtil.hasLife(this.lifeMap);
    }

    /**
     * Runs a day simulation.
     */
    public final void runDay() {
        this.lifeMap = GameOfLifeMapUtil.runDay(this.lifeMap);
    }

    /**
     * Returns a formatted representation of the game.
     *
     * @return a formatted representation of the game.
     */
    public final String toString() {
        return GameOfLifeMapUtil.toString(this.lifeMap);
    }

}
