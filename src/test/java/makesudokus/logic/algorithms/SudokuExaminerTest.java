package makesudokus.logic.algorithms;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SudokuExaminerTest {
    SudokuExaminer se;
    int[][] EXAMPLE_SUDOKU_UNSOLVED = {
            {0,0,0,8,9,0,0,0,4},
            {9,8,5,1,3,4,6,2,0},
            {0,7,4,0,0,0,0,1,9},
            {0,0,0,4,1,9,0,5,2},
            {0,0,9,7,6,5,1,0,0},
            {7,5,1,0,8,3,0,0,6},
            {0,1,7,0,0,0,3,6,0},
            {6,9,0,5,0,1,2,4,0},
            {5,4,0,3,0,0,0,0,0}
    };
    int[][] EXAMPLE_SUDOKU_SOLVED = {
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
    int[][] EXAMPLE_SUDOKU_ONLY_ROWS_CORRECT = {
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9}
    };
    int[][] EXAMPLE_SUDOKU_ONLY_COLUMNS_CORRECT = {
            {1,1,1,1,1,1,1,1,1},
            {2,2,2,2,2,2,2,2,2},
            {3,3,3,3,3,3,3,3,3},
            {4,4,4,4,4,4,4,4,4},
            {5,5,5,5,5,5,5,5,5},
            {6,6,6,6,6,6,6,6,6},
            {7,7,7,7,7,7,7,7,7},
            {8,8,8,8,8,8,8,8,8},
            {9,9,9,9,9,9,9,9,9}
    };
    int[][] EXAMPLE_SUDOKU_ONLY_CELLS_INCORRECT = {
            {1,2,3,4,5,6,7,8,9},
            {2,3,4,5,6,7,8,9,1},
            {3,4,5,6,7,8,9,1,2},
            {4,5,6,7,8,9,1,2,3},
            {5,6,7,8,9,1,2,3,4},
            {6,7,8,9,1,2,3,4,5},
            {7,8,9,1,2,3,4,5,6},
            {8,9,1,2,3,4,5,6,7},
            {9,1,2,3,4,5,6,7,8}
    };
    int[][] EXAMPLE_SUDOKU_ONLY_CELLS_CORRECT = {
            {1,2,3,1,2,3,1,2,3},
            {4,5,6,4,5,6,4,5,6},
            {7,8,9,7,8,9,7,8,9},
            {1,2,3,1,2,3,1,2,3},
            {4,5,6,4,5,6,4,5,6},
            {7,8,9,7,8,9,7,8,9},
            {1,2,3,1,2,3,1,2,3},
            {4,5,6,4,5,6,4,5,6},
            {7,8,9,7,8,9,7,8,9},
    };

    @Before
    public void init() {
        se = new SudokuExaminer();
    }

    @Test
    public void numberSetCheckerReturnsTrueOnCorrectSet() {
        int[] numSet1 = {1,2,3,4,5,6,7,8,9};
        int[] numSet2 = {9,2,5,1,3,8,7,4,6};
        int[] numSet3 = {4,5,1,2,6,7,9,3,8};
        assertTrue(se.checkNumberSet(numSet1));
        assertTrue(se.checkNumberSet(numSet2));
        assertTrue(se.checkNumberSet(numSet3));
    }

    @Test
    public void numberSetCheckerReturnsFalseWhenIncludesIllegalNumber() {
        int[] numSet1 = {1,2,3,4,0,6,7,8,9};
        int[] numSet2 = {9,2,5,1,3,8,7,4,100};
        assertFalse(se.checkNumberSet(numSet1));
        assertFalse(se.checkNumberSet(numSet2));
    }

    @Test
    public void NumberSetCheckerReturnsFalseWhenIncludesLegalNumberTooOften() {
        int[] numSet1 = {1,2,3,4,4,6,7,8,9};
        int[] numSet2 = {3,3,3,3,3,3,3,3,3};
        assertFalse(se.checkNumberSet(numSet1));
        assertFalse(se.checkNumberSet(numSet2));
    }

    @Test
    public void NumberSetCheckerReturnsFalseWithIllegalArraySize() {
        int[] numSet1 = {1,2,3,4,5,6,7,8};
        int[] numSet2 = {};
        int[] numSet3 = {1,2,3,4,5,6,7,8,9,9,9,9};
        assertFalse(se.checkNumberSet(numSet1));
        assertFalse(se.checkNumberSet(numSet2));
        assertFalse(se.checkNumberSet(numSet3));
    }

    @Test
    public void rowCheckerReturnsTrueWhenRowsAreCorrect() {
        assertTrue(se.checkRows(EXAMPLE_SUDOKU_SOLVED));
        assertTrue(se.checkRows(EXAMPLE_SUDOKU_ONLY_ROWS_CORRECT));
    }

    @Test
    public void rowCheckerReturnsFalseWhenRowsIncorrect() {
        assertFalse(se.checkRows(EXAMPLE_SUDOKU_ONLY_COLUMNS_CORRECT));
        assertFalse(se.checkRows(EXAMPLE_SUDOKU_UNSOLVED));
    }

    @Test
    public void columnCheckerReturnsTrueWhenColumnsAreCorrect() {
        assertTrue(se.checkColumns(EXAMPLE_SUDOKU_SOLVED));
        assertTrue(se.checkColumns(EXAMPLE_SUDOKU_ONLY_COLUMNS_CORRECT));
    }

    @Test
    public void columnCheckerReturnsFalseWhenColumnsIncorrect() {
        assertFalse(se.checkColumns(EXAMPLE_SUDOKU_UNSOLVED));
        assertFalse(se.checkColumns(EXAMPLE_SUDOKU_ONLY_ROWS_CORRECT));
    }

    @Test
    public void cellCheckerReturnsTrueCorrectCells() {
        assertTrue(se.checkCells(EXAMPLE_SUDOKU_SOLVED));
        assertTrue(se.checkCells(EXAMPLE_SUDOKU_ONLY_CELLS_CORRECT));
    }

    @Test
    public void cellCheckerReturnsFalseIncorrectCells() {
        assertFalse(se.checkCells(EXAMPLE_SUDOKU_UNSOLVED));
        assertFalse(se.checkCells(EXAMPLE_SUDOKU_ONLY_COLUMNS_CORRECT));
        assertFalse(se.checkCells(EXAMPLE_SUDOKU_ONLY_ROWS_CORRECT));
    }

    @Test
    public void checkSolutionFindsCorrectSudoku() {
        assertTrue(se.checkSolution(EXAMPLE_SUDOKU_SOLVED));
    }

    @Test
    public void checkSolutionFindsIncorrectSudoku() {
        assertFalse(se.checkSolution(EXAMPLE_SUDOKU_UNSOLVED));
        assertFalse(se.checkSolution(EXAMPLE_SUDOKU_ONLY_ROWS_CORRECT));
        assertFalse(se.checkSolution(EXAMPLE_SUDOKU_ONLY_COLUMNS_CORRECT));
        assertFalse(se.checkSolution(EXAMPLE_SUDOKU_ONLY_CELLS_CORRECT));
        assertFalse(se.checkSolution(EXAMPLE_SUDOKU_ONLY_CELLS_INCORRECT));
    }
}
