package makesudokus.logic;

import makesudokus.logic.algorithms.Backtracker;
import makesudokus.logic.algorithms.SudokuExaminer;
import makesudokus.logic.algorithms.crook.CrookSolver;

import java.util.Arrays;

/**
 * This class acts as the main controller for the application logic. It contains the sudoku, passes data
 * to the GUI and invokes the solving algorithms.
 */
public class SudokuController {
    private Sudoku sudoku;
    private boolean solved;
    private Backtracker backtracker;
    private CrookSolver crookSolver;
    private int[][] initialSudoku;
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
    private final int[][] EXAMPLE_SUDOKU_EXTREME_1 = {
            {0,9,2,3,0,0,4,0,0},
            {0,0,0,0,0,0,0,5,0},
            {6,0,7,2,0,0,0,0,0},
            {0,0,1,0,3,0,0,0,0},
            {0,7,0,0,0,9,5,0,2},
            {0,0,6,0,0,0,3,1,0},
            {0,1,0,0,0,7,0,0,6},
            {0,0,0,0,4,2,0,0,0},
            {0,0,0,0,0,0,0,8,0}
    };
    private final int[][] EXAMPLE_SUDOKU_EXTREME_2 = {
            {0,0,0,7,0,0,3,5,0},
            {0,0,0,8,0,0,6,0,0},
            {0,3,0,0,0,2,0,0,9},
            {9,4,0,0,0,0,0,2,6},
            {0,7,0,0,0,6,0,0,0},
            {1,0,0,9,0,8,4,0,0},
            {0,0,6,0,0,3,0,4,0},
            {0,0,0,4,9,0,0,6,0},
            {5,0,0,0,2,0,0,9,3}
    };

    /**
     * Constructor, initializes a test sudoku and solver class.
     */
    public SudokuController() {
        this.sudoku = new Sudoku();
        //We initialize the sudoku here for the time being.
        //Comment the following line out if you want to initialize and empty board.
        this.sudoku.setContent(EXAMPLE_SUDOKU_EXTREME_1);
        this.initializeBackup();
        this.backtracker = new Backtracker(this.sudoku);
        this.crookSolver = new CrookSolver(this.sudoku);
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
            this.solved = SudokuExaminer.checkForWinner(this.sudoku.getContent());
            if (this.solved) {
                System.out.println(sudoku);
                System.out.println("Congrats, you're a winner!");
            }
            return true;
        }
        return false;
    }


    /**
     * Fetches the number of a single cell in the Sudoku.
     * @param x x axis coordinate of the sudoku.
     * @param y y axis coordinate of the sudoku.
     * @return Returns the value in the given coordinates.
     */
    public int getNumber(int x, int y) {
        return this.sudoku.getNumber(x,y);
    }

    /**
     * @return Returns the solved state of the sudoku.
     */
    public boolean isSolved() {
        return this.solved;
    }


    /**
     * Calls the backtracker solving algorithm.
     * @return Returns true if the sudoku is solved.
     */
    public boolean solveWithBacktracker() {
        this.backtracker.solve();
        if (SudokuExaminer.checkForWinner(this.sudoku.getContent())) {
            this.solved = true;
            return true;
        }
        return false;
    }

    /**
     * Calls the solving algorithm based on crook's algorithm
     * @return Currently returns true if the sudoku is solved
     */
    public boolean solveWithCrook() {
        this.crookSolver.solve();
        if (SudokuExaminer.checkForWinner(this.sudoku.getContent())) {
            this.solved = true;
            return true;
        }
        return false;
    }

    /**
     * Resets the sudoku to what you started with.
     */
    public void resetSudoku() {
        this.sudoku.setContent(initialSudoku);
        if (SudokuExaminer.checkForWinner(this.sudoku.getContent())) {
            this.solved = true;
        } else {
            this.solved = false;
        }
    }

    /**
     * Takes a backup of the initial layout of the sudoku board.
     * Used as a restore point if player wants to start again.
     */
    private void initializeBackup() {
        initialSudoku = new int[9][9];
        for (int y = 0; y < 9; y++) {
            initialSudoku[y] = Arrays.copyOf(this.sudoku.getContent()[y], 9);
        }
    }
}
