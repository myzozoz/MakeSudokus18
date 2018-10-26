package makesudokus.logic;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SudokuControllerTest {
    SudokuController sc;
    int[][] startingSudoku;
    private final int[][] EXAMPLE_SUDOKU_SOLVED = {
            {6,3,9,5,1,4,7,8,2},
            {8,5,7,3,2,9,6,1,4},
            {2,1,4,7,8,6,3,5,9},
            {1,8,2,6,4,3,9,7,5},
            {3,7,5,1,9,2,4,6,8},
            {9,4,6,8,5,7,1,2,3},
            {7,6,8,9,3,5,2,4,1},
            {5,2,3,4,7,1,8,9,6},
            {4,9,1,2,6,8,5,3,7}
    };

    @Before
    public void init() {
        sc = new SudokuController();
        startingSudoku = new int[9][9];
        for (int y = 0; y < 9; y++) {
            startingSudoku[y]= Arrays.copyOf(sc.getSudoku()[y], 9);
        }
    }

    @Test
    public void canResetSudoku() {
        sc.updateNumber(1,1,8);
        sc.updateNumber(1,2,8);
        sc.updateNumber(1,3,8);
        sc.resetSudoku();
        assertArrayEquals(startingSudoku, sc.getSudoku());
    }

    @Test
    public void canResetSudokuMultipleTimes() {
        sc.updateNumber(3,1,8);
        sc.updateNumber(3,2,8);
        sc.updateNumber(3,3,8);
        sc.resetSudoku();
        assertArrayEquals(startingSudoku, sc.getSudoku());

        sc.updateNumber(8,1,8);
        sc.updateNumber(8,2,8);
        sc.updateNumber(1,3,8);
        sc.resetSudoku();
        assertArrayEquals(startingSudoku, sc.getSudoku());

        sc.updateNumber(1,1,8);
        sc.updateNumber(4,2,8);
        sc.updateNumber(3,3,8);
        sc.resetSudoku();
        assertArrayEquals(startingSudoku, sc.getSudoku());
    }

    @Test
    public void solveSudokuWithCrook() {
        assertFalse(sc.isSolved());
        assertTrue(sc.solveWithCrook());
        assertTrue(sc.isSolved());
    }

    @Test
    public void solveSudokuWithBacktracking() {
        assertFalse(sc.isSolved());
        assertTrue(sc.solveWithBacktracker());
        assertTrue(sc.isSolved());
    }

    @Test
    public void canGetSingleNumber() {
        sc.updateNumber(5,5,5);
        assertEquals(5, sc.getNumber(5,5));
    }

}
