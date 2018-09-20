package makesudokus.logic;

import makesudokus.logic.algorithms.Backtracker;
import makesudokus.logic.algorithms.SudokuExaminer;

/**
 * This class acts as the main controller for the application logic. It contains the sudoku, (later on) passes data
 * to the GUI and invokes the solving algorithms. For now it doesn't do anything.
 */
public class SudokuController {
    private Sudoku sudoku;
    private boolean solved;
    private Backtracker backtracker;
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
    private final int[][] EXAMPLE_SUDOKU_HARD = {
            {0,0,3,0,7,0,0,0,0},
            {0,0,0,0,0,0,0,5,2},
            {0,0,0,0,0,0,8,0,1},
            {0,0,0,4,0,0,2,0,7},
            {5,0,7,0,0,9,0,1,0},
            {0,4,0,1,0,0,0,0,0},
            {3,7,0,0,0,2,0,8,0},
            {8,0,9,0,0,0,0,0,5},
            {0,0,1,6,0,5,0,0,9}
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

    /**
     * Constructor, initializes a test sudoku and solver class.
     */
    public SudokuController() {
        this.sudoku = new Sudoku();
        //We initialize the sudoku here for the time being
        this.sudoku.setContent(EXAMPLE_SUDOKU_HARD);
        this.backtracker = new Backtracker(this.sudoku);
    }

    /**
     * An API method for the GUI.
     * @return Returns the sudoku's contents.
     */
    public int[][] getSudoku() {
        return this.sudoku.getContent();
    }

    /**
     * An API method for the GUI. Invokes the setNumber(int x, int y, int newNumber) of the Sudoku object.
     * @param x x axis coordinate.
     * @param y y axis coordinate.
     * @param newNumber New number to be placed in the slot.
     * @return Returns true for successful operation and false for unsuccessful.
     */
    public boolean updateNumber(int x, int y, int newNumber) {
        if(this.sudoku.setNumber(x,y,newNumber)) {
            if(!SudokuExaminer.legal(this.sudoku.getContent())) {
                System.out.println("Number not legal");
            }
            this.solved = SudokuExaminer.checkForWinner(this.sudoku.getContent());
            if (this.solved) {
                System.out.println(sudoku);
                System.out.println("Congrats, you're a winner!");
            }
            return true;
        }
        return false;
    }


    public int getNumber(int x, int y) {
        return this.sudoku.getNumber(x,y);
    }

    /**
     * @return Returns the solved state of the sudoku.
     */
    public boolean isSolved() {
        return this.solved;
    }


    public void solve() {
        backtracker.solve();
    }
}
