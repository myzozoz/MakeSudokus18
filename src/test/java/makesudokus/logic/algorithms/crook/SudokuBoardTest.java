package makesudokus.logic.algorithms.crook;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SudokuBoardTest {
    SudokuBoard board;
    SudokuBoard board2;
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


    @Before
    public void init() {
        board = new SudokuBoard(EXAMPLE_SUDOKU_UNSOLVED);
        board2 = new SudokuBoard(EXAMPLE_SUDOKU_ALMOST_SOLVED);
    }

    @Test
    public void returnsGivenBoardAfterInitialization() {
        assertArrayEquals(EXAMPLE_SUDOKU_UNSOLVED, board.getBoardAsArray());
    }

    @Test
    public void getMarkupsReturnsCorrectString() {
        assertEquals("Markups in cell (0, 0)[6]\n", board2.getAllMarkups());
    }
}
