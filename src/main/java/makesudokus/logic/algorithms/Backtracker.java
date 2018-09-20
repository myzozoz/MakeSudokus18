package makesudokus.logic.algorithms;

import makesudokus.logic.Sudoku;

/**
 * <p>The Backtracker class is what it sounds like. A class containing a DFS, or backtracking,
 *  algorithm that solves a sudoku.</p>
 */
public class Backtracker implements Solver {
    private Sudoku sudoku;
    private int[][] solvedContent;

    /**
     * The constructor is fed the sudoku to be solved.
     * @param sudoku The sudoku to be solved.
     */
    public Backtracker(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    /**
     * A master function for the Backtracer class. First copies a working content table from the
     * sudoku. Then solves the sudoku and times it.
     */
    public void solve() {
        this.solvedContent = sudoku.getContent().clone();
        long sysTimeStart = System.currentTimeMillis();
        int[] firstUnknown = SudokuExaminer.findNextUnknown(solvedContent, -1,-1);
        if (firstUnknown != null && solveNumber(firstUnknown[0], firstUnknown[1])) {
            System.out.println("Solved in " + (System.currentTimeMillis() - sysTimeStart) + "ms");
        }
    }

    /**
     * A recursive function that completes a depth first search for the correct answer.
     * @param x Solve the numbers from x onward.
     * @param y Solve the numbers from y onward.
     */
    private boolean solveNumber(int x, int y) {
        for (int i = 1; i <= 9; i++) {
            solvedContent[y][x] = i;
            if (SudokuExaminer.legal(solvedContent)) {
                int[] nextCoords = SudokuExaminer.findNextUnknown(solvedContent, x, y);
                if (nextCoords != null) {
                    if (solveNumber(nextCoords[0], nextCoords[1])) {
                        return true;
                    }
                } else {
                    System.out.println("Solved?");
                    sudoku.setContent(solvedContent);
                    System.out.println(sudoku);
                    return true;
                }
            }
        }
        solvedContent[y][x] = 0;
        return false;
    }
}
