package makesudokus.logic.algorithms.crook;

import makesudokus.logic.Sudoku;
import makesudokus.logic.algorithms.Solver;
import makesudokus.logic.algorithms.SudokuExaminer;


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
        solveLoop();
        return false;
    }

    /**
     * Contains a loop with a straight forward pattern:
     * 1. Check for singletons and turn those singletons into solved and known cells and do crossouts based on this information
     * 2. Create preemptive sets
     * 3. Do crossouts based on the preemptive sets.
     *
     * Additional functionalities are needed to solve situations where we have to guess the next number
     * (i.e. no more solved numbers via preemptive sets)
     */
    private void solveLoop() {
        long sysTimeStart = System.currentTimeMillis();
        int iterationCount = 0;
        //Check for singletons and turn them into known numbers
        while(!SudokuExaminer.checkForWinner(sudoku.getContent())){
            System.out.println(this.board.getAllMarkups());
            board.checkSingletons();
            board.preemptiveSets();
            sudoku.setContent(board.getBoardAsArray());
            iterationCount++;
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - sysTimeStart) + "ms");
        System.out.println("With " + iterationCount + " calls");
    }

}
