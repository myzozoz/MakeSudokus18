package makesudokus.logic;

import makesudokus.logic.algorithms.SudokuExaminer;

public class SudokuController {
    private Sudoku sudoku;
    private boolean solved;
    //The following two are temporary
    private final int[][] EXAMPLE_SUDOKU_EASY = {
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

    public SudokuController() {
        this.sudoku = new Sudoku();
        //We initialize the sudoku here for the time being
        this.sudoku.setContent(EXAMPLE_SUDOKU_EASY);
    }

    public int[][] getSudoku() {
        return this.sudoku.getContent();
    }

    public boolean updateNumber(int x, int y, int newNumber) {
        if(this.sudoku.setNumber(x,y,newNumber)) {
            this.solved = SudokuExaminer.checkSolution(this.sudoku.getContent());
            return true;
        }
        return false;
    }

    public boolean isSolved() {
        return this.solved;
    }


}
