package makesudokus.logic.algorithms.crook;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CellTest {
    Cell knownCell;
    Cell unknownCell;

    @Before
    public void init() {
        knownCell = new Cell(5);
        unknownCell = new Cell (0);
    }

    @Test
    public void constructorSetsKnownStatusCorrectly() {
        assertTrue(knownCell.isKnown());
        assertFalse(unknownCell.isKnown());
    }

    @Test
    public void canSetDigit() {
        unknownCell.setDigit(5);
        assertEquals(5, unknownCell.getDigit());
        knownCell.setDigit(9);
        assertEquals(9, knownCell.getDigit());
    }

    @Test
    public void settingDigitMakesCellKnown() {
        unknownCell.setDigit(3);
        assertTrue(unknownCell.isKnown());
    }

    @Test
    public void countsMarkupsCorrect() {
        assertEquals(9, unknownCell.countMarkups());
        assertEquals(0, knownCell.countMarkups());
    }

    @Test
    public void countsMarkupsCorrectAfterChanges() {
        unknownCell.crossout(3);
        unknownCell.crossout(6);
        assertEquals(7, unknownCell.countMarkups());
    }

    @Test
    public void canCrossoutDigits() {
        int[] markups = unknownCell.getMarkupDigits();
        assertTrue(Arrays.stream(markups).anyMatch(x -> x == 5));
        assertTrue(Arrays.stream(markups).anyMatch(x -> x == 1));
        unknownCell.crossout(5);
        unknownCell.crossout(1);
        markups = unknownCell.getMarkupDigits();
        assertFalse(Arrays.stream(markups).anyMatch(x -> x == 5));
        assertFalse(Arrays.stream(markups).anyMatch(x -> x == 1));
    }

    @Test
    public void returnsCorrectInitialMarkups() {
        int[] markups = {1,2,3,4,5,6,7,8,9};
        assertArrayEquals(new int[0], knownCell.getMarkupDigits());
        assertArrayEquals(markups, unknownCell.getMarkupDigits());
    }

    @Test
    public void returnsCorrectMarkupsAfterChange() {
        unknownCell.crossout(3);
        unknownCell.crossout(4);
        unknownCell.crossout(7);
        int[] markups = {1,2,5,6,8,9};
        assertArrayEquals(markups, unknownCell.getMarkupDigits());

    }
}
