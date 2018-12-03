package com.ghidiu.gameoflife;

import java.util.Scanner;

/**
 * A runner for the "game of life".
 *
 * @author jghidiu
 */
public class GameRunner {

    // The demo mode flag; useful for testing
    private final static boolean DEMO_MODE = true;


    /**
     * Runs the program. If a command line argument of "demo" is provided, then the application will run in a
     * non-interactive manner with a simple example.
     *
     * @param args command line arguments
     */
    public final static void main(String[] args) {
        // The day number
        int day = 0;

        // If demo mode is provided, run non-interactive
        if (DEMO_MODE || (1 == args.length && "demo".equals(args[0].toLowerCase()))) {
            // Setup
            final GameOfLife gol = new GameOfLife(5, 3);
            //
            gol.addLife(0, 1);
            gol.addLife(1, 0);
            gol.addLife(1, 1);
            gol.addLife(1, 2);


            // Run the simulation
            while (gol.hasLife()) {
                System.out.println("Day " + (day++));
                System.out.println(gol);
                gol.runDay();
            }


            // Final output
            System.out.println("Day " + (day++));
            System.out.println(gol);
            System.out.println("Sorry - everyone died!");
        } else {
            // Run interactive mode

            // Setup
            //
            // Get the dimensions
            final int rows = readInt("Enter the number of rows: ");
            final int columns = readInt("Enter the number of columns: ");
            //
            final GameOfLife gol = new GameOfLife(rows, columns);
            //
            do {
                // Get the coordinates from the user
                final int rowCoordiante = readInt("Enter row of live cell: ");
                final int columnCoordinate = readInt("Enter column of live cell: ");

                // Add the coordinate to the game
                gol.addLife(rowCoordiante, columnCoordinate);
            } while (readBoolean("More live cells to enter?(y/n): "));


            // Run the simulation
            do {
                System.out.println("Day " + (day++));
                System.out.println(gol);
                gol.runDay();

            } while (readBoolean("Do you want to see the next day?(y/n): ") && gol.hasLife());


            // Final output
            if (!gol.hasLife()) {
                System.out.println("Day " + (day++));
                System.out.println(gol);
                System.out.println("Sorry - everyone died!");
            }
        }

    }

    /**
     * Read a boolean input from the command line. A true value is "yes", "y" or "true", while a false value is "no",
     * "n" or "false". Prompting will continue until the user enters a valid value.
     *
     * @param prompt the prompt to display
     * @return true if the user entered a valid true value or false if the user entered a valid false value
     */
    private final static boolean readBoolean(final String prompt) {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            final String response = scanner.nextLine().toLowerCase();
            if (response.equals("yes") || response.equals("y") || response.equals("true")) {
                return true;
            }
            if (response.equals("no") || response.equals("n") || response.equals("false")) {
                return false;
            }
        }
    }

    /**
     * Reads an integer input from the command line. Prompting will continue until the user enters a valid value.
     *
     * @param prompt the prompt to display
     * @return the user's integer input
     */
    private final static int readInt(final String prompt) {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (final Exception e) {
                // Could not read int; try again
            }
        }
    }

}

