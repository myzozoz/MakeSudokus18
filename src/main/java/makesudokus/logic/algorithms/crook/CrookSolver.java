package makesudokus.logic.algorithms.crook;

import makesudokus.logic.Sudoku;
import makesudokus.logic.algorithms.Solver;


/**
 * Class for solving a sudoku J.F.Crook style. Implements the Solver interface.
 */
public class CrookSolver implements Solver {
    private Sudoku sudoku;
    private SudokuBoard board;

    /**
     * Constructor initializes contained sudoku.
     * @param sudoku The sudoku you want us to solve.
     */
    public CrookSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    /**
     * Hopefully solves the sudoku.
     * @return Returns true if sudoku was solved.
     */
    public boolean solve() {
        this.board = new SudokuBoard(this.sudoku.getContent());
        System.out.println(this.board.getAllMarkups());
        solveLoop();
        return false;
    }

    private void solveLoop() {
        //Check for singletons and turn them into known numbers
        board.checkSingletons();
        sudoku.setContent(board.getBoardAsArray());
    }

}
