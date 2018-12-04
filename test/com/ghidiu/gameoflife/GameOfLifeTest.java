package com.ghidiu.gameoflife;

/**
 * Manual tests for the GameOfLife class.
 *
 * @author jghidiu
 */
public class GameOfLifeTest {

    /**
     * Main point of entry.
     *
     * @param args command line arguments
     */
    public final static void main(String [] args) {

        testConstructor();
        testAddLife();

        // TODO: Add each test method as they are created
    }

    // public GameOfLife(final int rows, final int columns)
    private final static void testConstructor() {
        // Create an instance
        final GameOfLife gol = new GameOfLife(2, 2);

        // There should be no life here
        if (gol.hasLife()) {
            System.err.println("life found with new instance");
        }
    }

    // public final void addLife(final int row, final int column)
    private final static void testAddLife() {
        // Create an instance
        final GameOfLife gol = new GameOfLife(2, 2);

        gol.addLife(1, 1);
        if (!gol.hasLife()) {
            System.err.println("no life found, but life was expected");
        }
    }

    // public final boolean hasLife()

    // public final void runDay()

    // public final String toString()

}
