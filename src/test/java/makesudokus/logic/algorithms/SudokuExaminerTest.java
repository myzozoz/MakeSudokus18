package makesudokus.logic.algorithms;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuExaminerTest {
    SudokuExaminer se;
    int[][] EXAMPLE_SUDOKU_UNSOLVED = {
            {0, 0, 0, 8, 9, 0, 0, 0, 4},
            {9, 8, 5, 1, 3, 4, 6, 2, 0},
            {0, 7, 4, 0, 0, 0, 0, 1, 9},
            {0, 0, 0, 4, 1, 9, 0, 5, 2},
            {0, 0, 9, 7, 6, 5, 1, 0, 0},
            {7, 5, 1, 0, 8, 3, 0, 0, 6},
            {0, 1, 7, 0, 0, 0, 3, 6, 0},
            {6, 9, 0, 5, 0, 1, 2, 4, 0},
            {5, 4, 0, 3, 0, 0, 0, 0, 0}
    };
    int[][] EXAMPLE_SUDOKU_SOLVED = {
            {6, 3, 9, 5, 1, 4, 7, 8, 2},
            {8, 5, 7, 3, 2, 9, 6, 1, 4},
            {2, 1, 4, 7, 8, 6, 3, 5, 9},
            {1, 8, 2, 6, 4, 3, 9, 7, 5},
            {3, 7, 5, 1, 9, 2, 4, 6, 8},
            {9, 4, 6, 8, 5, 7, 1, 2, 3},
            {7, 6, 8, 9, 3, 5, 2, 4, 1},
            {5, 2, 3, 4, 7, 1, 8, 9, 6},
            {4, 9, 1, 2, 6, 8, 5, 3, 7}
    };
    private final int[][] EXAMPLE_SUDOKU_ALMOST_SOLVED = {
            {0,3,9,5,1,4,7,8,2},
            {8,5,7,3,2,9,6,1,4},
            {2,1,4,7,8,6,3,5,9},
            {1,8,2,6,4,3,9,7,5},
            {3,7,5,1,9,2,4,6,8},
            {9,4,6,8,5,7,1,2,3},
            {7,6,8,9,3,5,2,4,1},
            {5,2,3,4,7,1,8,9,6},
            {4,9,1,2,6,8,5,3,7}
    };
    private final int[][] EXAMPLE_SUDOKU_ALMOST_SOLVED_ILLEGAL = {
            {1,3,9,5,1,4,7,8,2},
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
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9}
    };
    int[][] EXAMPLE_SUDOKU_ONLY_COLUMNS_CORRECT = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 2, 2, 2, 2, 2, 2, 2, 2},
            {3, 3, 3, 3, 3, 3, 3, 3, 3},
            {4, 4, 4, 4, 4, 4, 4, 4, 4},
            {5, 5, 5, 5, 5, 5, 5, 5, 5},
            {6, 6, 6, 6, 6, 6, 6, 6, 6},
            {7, 7, 7, 7, 7, 7, 7, 7, 7},
            {8, 8, 8, 8, 8, 8, 8, 8, 8},
            {9, 9, 9, 9, 9, 9, 9, 9, 9}
    };
    int[][] EXAMPLE_SUDOKU_ONLY_CELLS_INCORRECT = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {2, 3, 4, 5, 6, 7, 8, 9, 1},
            {3, 4, 5, 6, 7, 8, 9, 1, 2},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {5, 6, 7, 8, 9, 1, 2, 3, 4},
            {6, 7, 8, 9, 1, 2, 3, 4, 5},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {8, 9, 1, 2, 3, 4, 5, 6, 7},
            {9, 1, 2, 3, 4, 5, 6, 7, 8}
    };
    int[][] EXAMPLE_SUDOKU_ONLY_CELLS_CORRECT = {
            {1, 2, 3, 1, 2, 3, 1, 2, 3},
            {4, 5, 6, 4, 5, 6, 4, 5, 6},
            {7, 8, 9, 7, 8, 9, 7, 8, 9},
            {1, 2, 3, 1, 2, 3, 1, 2, 3},
            {4, 5, 6, 4, 5, 6, 4, 5, 6},
            {7, 8, 9, 7, 8, 9, 7, 8, 9},
            {1, 2, 3, 1, 2, 3, 1, 2, 3},
            {4, 5, 6, 4, 5, 6, 4, 5, 6},
            {7, 8, 9, 7, 8, 9, 7, 8, 9},
    };

    @Before
    public void init() {
        se = new SudokuExaminer();
    }

    @Test
    public void numberSetCheckerReturnsTrueOnCorrectSet() {
        int[] numSet1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] numSet2 = {9, 2, 5, 1, 3, 8, 7, 4, 6};
        int[] numSet3 = {4, 5, 1, 2, 6, 7, 9, 3, 8};
        assertTrue(se.checkNumberSetForCompletion(numSet1));
        assertTrue(se.checkNumberSetForCompletion(numSet2));
        assertTrue(se.checkNumberSetForCompletion(numSet3));
    }

    @Test
    public void numberSetCheckerReturnsFalseWhenIncludesIllegalNumber() {
        int[] numSet1 = {1, 2, 3, 4, 0, 6, 7, 8, 9};
        int[] numSet2 = {9, 2, 5, 1, 3, 8, 7, 4, 100};
        assertFalse(se.checkNumberSetForCompletion(numSet1));
        assertFalse(se.checkNumberSetForCompletion(numSet2));
    }

    @Test
    public void numberSetCheckerReturnsFalseWhenIncludesLegalNumberTooOften() {
        int[] numSet1 = {1, 2, 3, 4, 4, 6, 7, 8, 9};
        int[] numSet2 = {3, 3, 3, 3, 3, 3, 3, 3, 3};
        assertFalse(se.checkNumberSetForCompletion(numSet1));
        assertFalse(se.checkNumberSetForCompletion(numSet2));
    }

    @Test
    public void duplicateCheckerReturnsFalseWhenThereAreDuplicates() {
        int[] numSet1 = {1, 2, 3, 4, 4, 6, 7, 8, 9};
        int[] numSet2 = {3, 3, 3, 3, 3, 3, 3, 3, 3};
        assertFalse(se.noDuplicatesCheck(numSet1));
        assertFalse(se.noDuplicatesCheck(numSet2));
    }

    @Test
    public void duplicateCheckerReturnsTrueWhenNoDuplicates() {
        int[] numSet1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] numSet2 = {9, 2, 5, 1, 3, 8, 7, 4, 6};
        assertTrue(se.noDuplicatesCheck(numSet1));
        assertTrue(se.noDuplicatesCheck(numSet2));
    }

    @Test
    public void duplicateCheckerReturnsTrueWhenIllegalNonUniqueNumbers() {
        int[] numSet1 = {1, 2, 0, 0, 0, 6, 7, 8, 9};
        int[] numSet2 = {9, 2, 5, 1, 100, 8, 0, 4, 100};
        assertTrue(se.noDuplicatesCheck(numSet1));
        assertTrue(se.noDuplicatesCheck(numSet2));
    }

    @Test
    public void numberSetCheckerReturnsFalseWithIllegalArraySize() {
        int[] numSet1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] numSet2 = {};
        int[] numSet3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9};
        assertFalse(se.checkNumberSetForCompletion(numSet1));
        assertFalse(se.checkNumberSetForCompletion(numSet2));
        assertFalse(se.checkNumberSetForCompletion(numSet3));
    }

    @Test
    public void rowCheckerReturnsTrueWhenRowsAreCorrect() {
        assertTrue(se.checkRowsForCompletion(EXAMPLE_SUDOKU_SOLVED));
        assertTrue(se.checkRowsForCompletion(EXAMPLE_SUDOKU_ONLY_ROWS_CORRECT));
    }

    @Test
    public void rowCheckerReturnsFalseWhenRowsIncorrect() {
        assertFalse(se.checkRowsForCompletion(EXAMPLE_SUDOKU_ONLY_COLUMNS_CORRECT));
        assertFalse(se.checkRowsForCompletion(EXAMPLE_SUDOKU_UNSOLVED));
    }

    @Test
    public void rowLegalityCheckReturnsTrueWhenRowsHaveNoIllegals() {
        assertTrue(se.legalRows(EXAMPLE_SUDOKU_SOLVED));
        assertTrue(se.legalRows(EXAMPLE_SUDOKU_UNSOLVED));
        assertTrue(se.legalRows(EXAMPLE_SUDOKU_ONLY_ROWS_CORRECT));
    }

    @Test
    public void rowLegalityCheckReturnsFalseWhenRowsHaveDuplicates() {
        assertFalse(se.legalRows(EXAMPLE_SUDOKU_ONLY_CELLS_CORRECT));
        assertFalse(se.legalRows(EXAMPLE_SUDOKU_ONLY_COLUMNS_CORRECT));
    }

    @Test
    public void columnCheckerReturnsTrueWhenColumnsAreCorrect() {
        assertTrue(se.checkColumnsForCompletion(EXAMPLE_SUDOKU_SOLVED));
        assertTrue(se.checkColumnsForCompletion(EXAMPLE_SUDOKU_ONLY_COLUMNS_CORRECT));
    }

    @Test
    public void columnCheckerReturnsFalseWhenColumnsIncorrect() {
        assertFalse(se.checkColumnsForCompletion(EXAMPLE_SUDOKU_UNSOLVED));
        assertFalse(se.checkColumnsForCompletion(EXAMPLE_SUDOKU_ONLY_ROWS_CORRECT));
    }

    @Test
    public void columnLegalityCheckReturnsTrueWhenNoIllegals() {
        assertTrue(se.legalColumns(EXAMPLE_SUDOKU_SOLVED));
        assertTrue(se.legalColumns(EXAMPLE_SUDOKU_UNSOLVED));
        assertTrue(se.legalColumns(EXAMPLE_SUDOKU_ONLY_COLUMNS_CORRECT));
    }

    @Test
    public void columnLegalityCheckReturnsFalseWhenColumnsHaveDuplicates() {
        assertFalse(se.legalColumns(EXAMPLE_SUDOKU_ONLY_CELLS_CORRECT));
        assertFalse(se.legalColumns(EXAMPLE_SUDOKU_ONLY_ROWS_CORRECT));
    }

    @Test
    public void cellCheckerReturnsTrueCorrectCells() {
        assertTrue(se.checkCellsForCompletion(EXAMPLE_SUDOKU_SOLVED));
        assertTrue(se.checkCellsForCompletion(EXAMPLE_SUDOKU_ONLY_CELLS_CORRECT));
    }

    @Test
    public void cellCheckerReturnsFalseIncorrectCells() {
        assertFalse(se.checkCellsForCompletion(EXAMPLE_SUDOKU_UNSOLVED));
        assertFalse(se.checkCellsForCompletion(EXAMPLE_SUDOKU_ONLY_COLUMNS_CORRECT));
        assertFalse(se.checkCellsForCompletion(EXAMPLE_SUDOKU_ONLY_ROWS_CORRECT));
    }

    @Test
    public void cellLegalityCheckReturnsTrueWhenNoIllegals() {
        assertTrue(se.legalCells(EXAMPLE_SUDOKU_SOLVED));
        assertTrue(se.legalCells(EXAMPLE_SUDOKU_UNSOLVED));
        assertTrue(se.legalCells(EXAMPLE_SUDOKU_ONLY_CELLS_CORRECT));
    }

    @Test
    public void cellLegalityCheckReturnsFalseWhenCellsHaveDuplicates() {
        assertFalse(se.legalCells(EXAMPLE_SUDOKU_ONLY_COLUMNS_CORRECT));
        assertFalse(se.legalCells(EXAMPLE_SUDOKU_ONLY_ROWS_CORRECT));
        assertFalse(se.legalCells(EXAMPLE_SUDOKU_ONLY_CELLS_INCORRECT));
    }

    @Test
    public void checkForWinnerFindsCorrectSudoku() {
        assertTrue(se.checkForWinner(EXAMPLE_SUDOKU_SOLVED));
    }

    @Test
    public void checkForWinnerFindsIncorrectSudoku() {
        assertFalse(se.checkForWinner(EXAMPLE_SUDOKU_UNSOLVED));
        assertFalse(se.checkForWinner(EXAMPLE_SUDOKU_ONLY_ROWS_CORRECT));
        assertFalse(se.checkForWinner(EXAMPLE_SUDOKU_ONLY_COLUMNS_CORRECT));
        assertFalse(se.checkForWinner(EXAMPLE_SUDOKU_ONLY_CELLS_CORRECT));
        assertFalse(se.checkForWinner(EXAMPLE_SUDOKU_ONLY_CELLS_INCORRECT));
    }

    @Test
    public void legalityTestReturnsTrueOnLegalSudokus() {
        assertTrue(se.legal(EXAMPLE_SUDOKU_SOLVED));
        assertTrue(se.legal(EXAMPLE_SUDOKU_UNSOLVED));
        assertTrue(se.legal(EXAMPLE_SUDOKU_ALMOST_SOLVED));
    }

    @Test
    public void legalityTestReturnsFalseOnIllegalSudokus() {
        assertFalse(se.legal(EXAMPLE_SUDOKU_ONLY_ROWS_CORRECT));
        assertFalse(se.legal(EXAMPLE_SUDOKU_ONLY_COLUMNS_CORRECT));
        assertFalse(se.legal(EXAMPLE_SUDOKU_ONLY_CELLS_CORRECT));
        assertFalse(se.legal(EXAMPLE_SUDOKU_ONLY_CELLS_INCORRECT));
        assertFalse(se.legal(EXAMPLE_SUDOKU_ALMOST_SOLVED_ILLEGAL));
    }

    @Test
    public void findsFirstUnknown() {
        assertArrayEquals(new int[]{0,0},se.findNextUnknown(EXAMPLE_SUDOKU_UNSOLVED, -1, -1));
        assertArrayEquals(new int[]{0,0},se.findNextUnknown(EXAMPLE_SUDOKU_UNSOLVED, 100, -1));
    }

    @Test
    public void findsNextUnknown() {
        assertArrayEquals(new int[]{2, 0},se.findNextUnknown(EXAMPLE_SUDOKU_UNSOLVED, 1, 0));
        assertArrayEquals(new int[]{5,0},se.findNextUnknown(EXAMPLE_SUDOKU_UNSOLVED, 2, 0));
        assertArrayEquals(new int[]{8,1},se.findNextUnknown(EXAMPLE_SUDOKU_UNSOLVED, 7, 0));
    }

    @Test
    public void nextUnknownIsNullWhenNoMoreUnknown() {
        assertNull(se.findNextUnknown(EXAMPLE_SUDOKU_SOLVED, 0 ,0));
        assertNull(se.findNextUnknown(EXAMPLE_SUDOKU_UNSOLVED, 8 ,8));
    }
}
