package com.ghidiu.gameoflife;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * JUnit test cases for the GameOfLifeMapUtil class.
 *
 * @author jghidiu
 */
public class GameOfLifeMapUtilTest {

    // Randomizer
    private final Random random = new Random();

    // Row counts
    private final int rowCount = 10;
    private final int columnCount = 10;

    // The map for each test
    private boolean[][] actualMap;


    /**
     * This method runs before *each* test.
     */
    @Before
    public void beforeEach() {
        // Generate the map
        actualMap = GameOfLifeMapUtil.createMap(rowCount, columnCount, true);
        // Randomize the map
        randomizeMap(actualMap);
    }

    @After
    public void afterEach() {
        // Any code to run after each test
    }


    ////////////////////////////////////////////////////////////////////////////
    // createMap(row, column, initialValue)
    ////////////////////////////////////////////////////////////////////////////
    @Test
    public void testCreateMapRowsAndColumnsInitialValueTrue() {
        // Recreate the map with ALL true values
        actualMap = GameOfLifeMapUtil.createMap(rowCount, columnCount, true);

        // Assert that all values are true
        assertMapEquality(actualMap, rowCount, columnCount, true);
    }

    @Test
    public void testCreateMapRowsAndColumnsInitialValueFalse() {
        // Recreate the map with all false values
        actualMap = GameOfLifeMapUtil.createMap(rowCount, columnCount, false);

        // Assert that all values are false
        assertMapEquality(actualMap, rowCount, columnCount, false);
    }

    @Test
    public void testCreateMapNoDimensions() {
        // Create a map with no dimensions based on a template map of no dimensions
        final boolean[][] map = GameOfLifeMapUtil.createMap(new boolean[0][0], false);

        // Assertions
        assertThat(map.length, is(0));
    }


    ////////////////////////////////////////////////////////////////////////////
    // createMap(row, column, initialValue)
    ////////////////////////////////////////////////////////////////////////////
    @Test
    public void testCreateMapMapInitialValueTrue() {
        // Create a map with all true values, based on the size of the actualMap
        boolean[][] createdMap = GameOfLifeMapUtil.createMap(actualMap, true);

        // Assert that all values are true and the size is correct
        assertMapEquality(createdMap, rowCount, columnCount, true);
    }

    @Test
    public void testCreateMapMapInitialValueFalse() {
        // Create a map with all false values, based on the size of the actualMap
        boolean[][] createdMap = GameOfLifeMapUtil.createMap(actualMap, false);

        // Assert that all values are false and the size is correct
        assertMapEquality(createdMap, rowCount, columnCount, false);
    }


    ////////////////////////////////////////////////////////////////////////////
    // cloneMap(map)
    ////////////////////////////////////////////////////////////////////////////
    @Test
    public void testCloneMap() {
        // Test the maps to see if the clone is equal to the map
        assertMapEquality(actualMap, GameOfLifeMapUtil.cloneMap(actualMap));
    }


    ////////////////////////////////////////////////////////////////////////////
    // hasLife(map)
    ////////////////////////////////////////////////////////////////////////////
    @Test
    public void testHasLifeWhenNoLifeExists() {
        // Create an all-dead map
        actualMap = GameOfLifeMapUtil.createMap(rowCount, columnCount, false);

        // Assert that no life exists
        assertThat(GameOfLifeMapUtil.hasLife(actualMap), is(false));
    }

    @Test
    public void testHasLifeWhenSomeLifeExists() {
        // Create an all-dead map
        actualMap = GameOfLifeMapUtil.createMap(rowCount, columnCount, false);

        // Randomly assign some life
        actualMap[random.nextInt(rowCount)][random.nextInt(columnCount)] = true;


        // Assert that some life exists
        assertThat(GameOfLifeMapUtil.hasLife(actualMap), is(true));
    }


    ////////////////////////////////////////////////////////////////////////////
    // runDay(map)
    ////////////////////////////////////////////////////////////////////////////
    @Test
    public void testRunDay() {
        // Create a new map
        actualMap = GameOfLifeMapUtil.createMap(5, 3, false);
        //
        // Set the initial state
        GameOfLifeMapUtil.setLife(0, 1, true, actualMap);
        GameOfLifeMapUtil.setLife(1, 0, true, actualMap);
        GameOfLifeMapUtil.setLife(1, 1, true, actualMap);
        GameOfLifeMapUtil.setLife(1, 2, true, actualMap);

        // Day 1
        actualMap = GameOfLifeMapUtil.runDay(actualMap);
        // XXX
        // XXX
        // OXO
        // OOO
        // OOO
        //
        // Row 1
        assertThat("day 1, 0:0", actualMap[0][0], is(true));
        assertThat("day 1, 0:1", actualMap[0][1], is(true));
        assertThat("day 1, 0:2", actualMap[0][2], is(true));
        // Row 2
        assertThat("day 1, 1:0", actualMap[1][0], is(true));
        assertThat("day 1, 1:1", actualMap[1][1], is(true));
        assertThat("day 1, 1:2", actualMap[1][2], is(true));
        // Row 3
        assertThat("day 1, 2:0", actualMap[2][0], is(false));
        assertThat("day 1, 2:1", actualMap[2][1], is(true));
        assertThat("day 1, 2:2", actualMap[2][2], is(false));
        // Row 4
        assertThat("day 1, 3:0", actualMap[3][0], is(false));
        assertThat("day 1, 3:1", actualMap[3][1], is(false));
        assertThat("day 1, 3:2", actualMap[3][2], is(false));
        // Row 5
        assertThat("day 1, 4:0", actualMap[4][0], is(false));
        assertThat("day 1, 4:1", actualMap[4][1], is(false));
        assertThat("day 1, 4:2", actualMap[4][2], is(false));

        // Day 2
        actualMap = GameOfLifeMapUtil.runDay(actualMap);
        // XOX
        // OOO
        // XXX
        // OOO
        // OOO
        //
        // Row 1
        assertThat("day 2, 0:0", actualMap[0][0], is(true));
        assertThat("day 2, 0:1", actualMap[0][1], is(false));
        assertThat("day 2, 0:2", actualMap[0][2], is(true));
        // Row 2
        assertThat("day 2, 1:0", actualMap[1][0], is(false));
        assertThat("day 2, 1:1", actualMap[1][1], is(false));
        assertThat("day 2, 1:2", actualMap[1][2], is(false));
        // Row 3
        assertThat("day 2, 2:0", actualMap[2][0], is(true));
        assertThat("day 2, 2:1", actualMap[2][1], is(true));
        assertThat("day 2, 2:2", actualMap[2][2], is(true));
        // Row 4
        assertThat("day 2, 3:0", actualMap[3][0], is(false));
        assertThat("day 2, 3:1", actualMap[3][1], is(false));
        assertThat("day 2, 3:2", actualMap[3][2], is(false));
        // Row 5
        assertThat("day 2, 4:0", actualMap[4][0], is(false));
        assertThat("day 2, 4:1", actualMap[4][1], is(false));
        assertThat("day 2, 4:2", actualMap[4][2], is(false));
    }


    ////////////////////////////////////////////////////////////////////////////
    // getLivingNeighborCount(row, column, map)
    ////////////////////////////////////////////////////////////////////////////
    @Test
    public void testGetLivingNeighborCount() {
        // Create an all live map
        actualMap = GameOfLifeMapUtil.createMap(rowCount, columnCount, true);

        // Assert the values are correct for ALL cells
        //
        // Top row
        // Check top left (should have three: to the right, below and below/right)
        assertThat(GameOfLifeMapUtil.getLivingNeighborCount(0, 0, actualMap), is(3));
        // Check top right (should have three: to the left, below and below/left)
        assertThat(GameOfLifeMapUtil.getLivingNeighborCount(0, columnCount - 1, actualMap), is(3));
        // The rest in the first row (should have 5: to the left, to the right, below, below/right and below/left)
        for (int column = 1; column < columnCount - 2; column++) {
            assertThat(GameOfLifeMapUtil.getLivingNeighborCount(0, column, actualMap), is(5));
        }
        //
        // Middle rows
        for (int row = 1; row < rowCount - 2; row++) {
            // Left-most (should have 5: above, below, right, above/right and below/right)
            assertThat(GameOfLifeMapUtil.getLivingNeighborCount(row, 0, actualMap), is(5));
            //
            // Right-most (should have 5: above, below, left, above/left and below/left
            assertThat(GameOfLifeMapUtil.getLivingNeighborCount(row, columnCount - 1, actualMap), is(5));
            //
            // The rest in the row (should have eight: above, below, left, right, above/left, above/right, below/left, below/right)
            for (int column = 1; column < columnCount - 2; column++) {
                assertThat(GameOfLifeMapUtil.getLivingNeighborCount(row, column, actualMap), is(8));
            }
        }
        //
        // Bottom row
        // Check bottom left (should have three: right, above and above/right)
        assertThat(GameOfLifeMapUtil.getLivingNeighborCount(rowCount - 1, 0, actualMap), is(3));
        // Check bottom right (should have three: left, above and above/left)
        assertThat(GameOfLifeMapUtil.getLivingNeighborCount(rowCount - 1, columnCount - 1, actualMap), is(3));
        // The rest in the last row (should have five: left, right, above, above/left, above/right)
        for (int column = 1; column < columnCount - 2; column++) {
            assertThat(GameOfLifeMapUtil.getLivingNeighborCount(rowCount - 1, column, actualMap), is(5));
        }
    }


    ////////////////////////////////////////////////////////////////////////////
    // setLife(row, column, map)
    ////////////////////////////////////////////////////////////////////////////
    @Test
    public void testSetLifeNoLife() {
        // Repeat n times (where n is the number of cells)
        for (int i = 0; i < (rowCount * columnCount); i++) {
            // Choose a cell at random
            final int row = random.nextInt(rowCount);
            final int column = random.nextInt(columnCount);

            // Manually set the value to the OPPOSITE of what is expected
            actualMap[row][column] = true;

            // Set the value
            GameOfLifeMapUtil.setLife(row, column, false, actualMap);

            // Assertion
            assertThat(GameOfLifeMapUtil.getLife(row, column, actualMap), is(false));
        }
    }

    @Test
    public void testSetLifeWithLife() {
        // Repeat n times (where n is the number of cells)
        for (int i = 0; i < (rowCount * columnCount); i++) {
            // Choose a cell at random
            final int row = random.nextInt(rowCount);
            final int column = random.nextInt(columnCount);

            // Manually set the value to the OPPOSITE of what is expected
            actualMap[row][column] = false;

            // Set the value
            GameOfLifeMapUtil.setLife(row, column, true, actualMap);

            // Assertion
            assertThat(GameOfLifeMapUtil.getLife(row, column, actualMap), is(true));
        }
    }


    ////////////////////////////////////////////////////////////////////////////
    // getLife(row, column, map)
    ////////////////////////////////////////////////////////////////////////////
    @Test
    public void testGetLifeNoLife() {
        // Repeat n times (where n is the number of cells)
        for (int i = 0; i < (rowCount * columnCount); i++) {
            // Choose a cell at random
            final int row = random.nextInt(rowCount);
            final int column = random.nextInt(columnCount);
            actualMap[row][column] = false;

            assertThat(GameOfLifeMapUtil.getLife(row, column, actualMap), is(false));
        }
    }

    @Test
    public void testGetLifeWithLife() {
        // Repeat n times (where n is the number of cells)
        for (int i = 0; i < (rowCount * columnCount); i++) {
            // Choose a cell at random
            final int row = random.nextInt(rowCount);
            final int column = random.nextInt(columnCount);
            actualMap[row][column] = true;

            assertThat(GameOfLifeMapUtil.getLife(row, column, actualMap), is(true));
        }
    }


    ////////////////////////////////////////////////////////////////////////////
    // toString(map)
    ////////////////////////////////////////////////////////////////////////////
    @Test
    public void testToString() {
        // Build a string which represents the state of the map
        final StringBuffer stringBuffer = new StringBuffer();

        for (int row = 0; row < actualMap.length; row++) {
            for (int column = 0; column < actualMap[0].length; column++) {
                stringBuffer.append(actualMap[row][column] ? "X" : "O");
            }
            stringBuffer.append(System.lineSeparator());
        }

        // Assert that the built string is the same as the string from GameOfLifeMapUtil.toString
        assertThat(GameOfLifeMapUtil.toString(actualMap), is(stringBuffer.toString()));
    }



    ////////////////////////////////////////////////////////////////////////////
    // HELPER METHODS
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Asserts that two maps are the same by value (not the same object).
     *
     * @param actualMap the actual map to test
     * @param expectedMap the expected values
     */
    private void assertMapEquality(boolean[][] actualMap, boolean[][] expectedMap) {
        assertThat("maps are the same object", actualMap.equals(expectedMap), is(false));
        assertThat("row count is different", actualMap.length, is(expectedMap.length));
        assertThat("column count is different", actualMap[0].length, is(expectedMap[0].length));

        for (int row = 0; row < actualMap.length; row++) {
            for (int column = 0; column < actualMap[0].length; column++) {
                assertThat(String.format("row '%d', column '%d' should be '%b' but was '%b'", row, column, expectedMap[row][column], actualMap[row][column]), actualMap[row][column], is(expectedMap[row][column]));
            }
        }
    }

    /**
     * Asserts that a map conforms to the specified parameters (size and values).
     *
     * @param map the map to test
     * @param expectedRowCount the row count
     * @param expectedColumnCount the column count
     * @param expectedValue the value expected in each cell
     */
    private void assertMapEquality(boolean[][] map, int expectedRowCount, int expectedColumnCount, boolean expectedValue) {
        assertThat(map.length, is(expectedRowCount));
        assertThat(map[0].length, is(expectedColumnCount));

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                assertThat(map[row][column], is(expectedValue));
            }
        }
    }

    /**
     * Randomizes a map. This modifies the passed in map!
     *
     * @param map the map to randomize
     */
    private void randomizeMap(boolean[][] map) {
        // Randomly set some values (randomly flip n values, where n is the total number of values
        for (int i = 0; i < (rowCount * columnCount); i++) {
            int rowToFlip = random.nextInt(rowCount);
            int columnToFlip = random.nextInt(columnCount);

            map[rowToFlip][columnToFlip] = !map[rowToFlip][columnToFlip];
        }
    }

}
