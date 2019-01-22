package com.ghidiu.gameoflife;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the GameOfLife class.
 *
 * @author jghidiu
 */
public class GameOfLifeTest {

    private GameOfLife gol;

    @Before
    public void beforeEach() {
        // Create an instance
        gol = new GameOfLife(2, 2);
    }

    @Test
    public void testConstructor() {
        // There should be no life here
        assertThat("Life found with new instance", gol.hasLife(), equalTo(false));
    }

    @Test
    public void testAddLife() {
        // Add life
        gol.addLife(1, 1);
        assertThat("No life found, but life was expected", gol.hasLife(), equalTo(true));
    }

    @Test
    public void testHasLife() {
        // There should be no life at the start
        assertThat("Should have life", gol.hasLife(), is(false));

        gol.addLife(1, 1);
        assertThat("Should have life", gol.hasLife(), is(true));
    }

    @Test
    public void testRunDay() {
        // Add life
        gol.addLife(1, 1);
        assertThat("Should have life", gol.hasLife(), is(true));

        gol.runDay();
        assertThat("Should have life", gol.hasLife(), is(false));
    }

    @Test
    public void testToString() {
        // Add life
        gol.addLife(1, 1);
        assertThat("toString() should be correct", gol.toString().trim(), is(("OO" + System.lineSeparator() + "OX").trim()));

        gol.runDay();
        assertThat("toString() should be correct", gol.toString().trim(), is(("OO" + System.lineSeparator() + "OO").trim()));
    }

}
