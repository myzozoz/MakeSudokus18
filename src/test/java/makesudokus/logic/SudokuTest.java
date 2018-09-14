package makesudokus.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SudokuTest {
    Sudoku s;

    @Before
    public void init() {
        s = new Sudoku();
    }

    @Test
    public void sudokuConstructorCreatesCorrectArray() {
        int[][] expected = new int[9][9];

        assertArrayEquals(expected,s.getContent());
    }

    @Test
    public void canSetSudoku() {
        int[][] newSudoku = new int[][]{
                {0,0,0,8,9,0,0,0,4},
                {9,8,5,1,3,4,6,2,0},
                {0,7,4,0,0,0,0,1,9},
                {0,0,0,4,1,9,0,5,2},
                {0,0,9,7,6,5,1,0,0},
                {7,5,1,0,8,3,0,0,6},
                {0,1,7,0,0,0,3,6,0},
                {6,9,0,5,0,1,2,4,0},
                {5,4,0,3,0,0,0,0,0},
        };
        s.setContent(newSudoku);
        assertArrayEquals(newSudoku, s.getContent());
    }

    @Test
    public void canSetNumber() {
        s.setNumber(0,0, 5);
        assertEquals(5, s.getNumber(0,0));
    }

    @Test
    public void settingLegalNumberReturnsTrue() {
        assertTrue(s.setNumber(0,0,5));
    }

    @Test
    public void settingIllegalNumberReturnsFalse() {
        assertFalse(s.setNumber(0,0,100));
    }

    @Test
    public void settingNumberInLegalCoordinatesReturnsTrue() {
        assertTrue(s.setNumber(0,0,5));
        assertTrue(s.setNumber(0,8,5));
        assertTrue(s.setNumber(8,0,5));
        assertTrue(s.setNumber(8,8,5));
    }

    @Test
    public void settingNumberInIllegalCoordinatesReturnsFalse() {
        assertFalse(s.setNumber(-1,0,5));
        assertFalse(s.setNumber(0,-1,5));
        assertFalse(s.setNumber(9,0,5));
        assertFalse(s.setNumber(0,9,5));
    }

    @Test
    public void toStringReturnsCorrectOnEmptyField() {
        String expectedString = "";
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                expectedString += "0";
            }
            expectedString += "\n";
        }

        assertEquals(expectedString, s.toString());
    }

    @Test
    public void toStringReturnsCorrectWithFilledField() {
        int[][] newSudoku = new int[][]{
                {0,0,0,8,9,0,0,0,4},
                {9,8,5,1,3,4,6,2,0},
                {0,7,4,0,0,0,0,1,9},
                {0,0,0,4,1,9,0,5,2},
                {0,0,9,7,6,5,1,0,0},
                {7,5,1,0,8,3,0,0,6},
                {0,1,7,0,0,0,3,6,0},
                {6,9,0,5,0,1,2,4,0},
                {5,4,0,3,0,0,0,0,0},
        };
        s.setContent(newSudoku);

        String expectedString = "";
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                expectedString += Integer.toString(s.getNumber(x,y));
            }
            expectedString += "\n";
        }

        assertEquals(expectedString, s.toString());
    }
}
