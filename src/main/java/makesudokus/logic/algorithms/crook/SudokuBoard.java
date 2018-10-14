package makesudokus.logic.algorithms.crook;

import java.util.Arrays;

/**
 * <p>A container class for all of the cells.</p>
 * Under the hood this class rocks a two dimensional array of cells.
 * It also has helper functions for handling the cells.
 */
public class SudokuBoard {
    private Cell[][] cells;

    /**
     * The constructor initializes the two dimensional array of cells by calling
     * the helper function initializeCells.
     * @param sudokuContents Initial contents of a sudoku game in int[][] form
     */
    public SudokuBoard(int[][] sudokuContents) {
        this.cells = new Cell[9][9];
        this.initializeCells(sudokuContents);
        this.initializeMarkups();
    }

    /**
     * Helper function for the constructor. Initializes cells by calling their constructor.
     * @param sudokuContent Contents of a sudoku in int[][] form.
     */
    private void initializeCells(int[][] sudokuContent) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                cells[y][x] = new Cell(sudokuContent[y][x]);
            }
        }
    }

    /**
     * Does the first pass on the markups. Markups are initialized with all true for unknown cells
     * and all false for known cells.
     */
    private void initializeMarkups() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (cells[y][x].isKnown()) {
                    //crossout all instances of digit in the box, row and column
                    crossoutKnownCell(x, y);
                }
            }
        }
    }

    /**
     * Crossout method designed for a situation where we learn a new number
     * @param x x axis coordinate of known cell
     * @param y y axis coordinate of known cell
     */
    private void crossoutKnownCell(int x, int y) {
        crossoutBox(x, y, cells[y][x].getDigit());
        crossoutRow(y, cells[y][x].getDigit());
        crossoutColumn(x, cells[y][x].getDigit());
    }

    /**
     * Crosses out all markups of digit in column x.
     * @param x Column to crossout digit on.
     * @param digit Digit to crossout.
     */
    private void crossoutColumn(int x, int digit) {
        for (int y = 0; y < 9; y++) {
            cells[y][x].crossout(digit);
        }
    }

    /**
     * Crosses out all markups of digit on row y.
     * @param y Row to crossout digit on.
     * @param digit Digit to crossout.
     */
    private void crossoutRow(int y, int digit) {
        for (int x = 0; x< 9; x++) {
            cells[y][x].crossout(digit);
        }
    }

    /**
     * Crosses out all markups of digit in cell (x, y) from the 3x3 box the cell exists in.
     * @param x x axis coordinate of the cell
     * @param y y axis coordinate of the cell
     */
    private void crossoutBox(int x, int y, int digit) {
        //First we need to find out which box we're in
        int xBox = x/3;
        int yBox = y/3;
        //Then do the crossouts
        for (int _y = 0; _y < 3; _y++) {
            for (int _x = 0; _x < 3; _x++) {
                cells[yBox*3+_y][xBox*3+_x].crossout(digit);
            }
        }
    }

    /**
     * Returns the board in a form that other parts of the program can handle.
     * @return Returns the digits from the cells on the board in a two dimensional array of integers.
     */
    public int[][] getBoardAsArray() {
        int[][] boardArray = new int[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                boardArray[y][x] = cells[y][x].getDigit();
            }
        }
        return boardArray;
    }

    /**
     * This is a helper method for testing the markup logic.
     * @return Returns all markups as a single string.
     */
    public String getAllMarkups() {
        String s = "";
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (!cells[y][x].isKnown()) {
                    s +="Markups in cell (" + x + ", " + y + ")" + Arrays.toString(cells[y][x].getMarkupDigits()) + "\n";
                }
            }
        }
        return s;
    }

    /**
     * <p>Finds singletons. Turns them into known cells.</p>
     *
     * There are two types of singletons. The checkSingletons() method is responsible for calling
     * the different types of checks.
     */
    public void checkSingletons() {
        checkSingletonsSingleMarkup();
    }

    /**
     * This method finds cases, where all other digits have been excluded from a cell, then
     * places the last available digit as the primary digit for the cell, changing its
     * status to "known".
     */
    private void checkSingletonsSingleMarkup() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                //If a cell is unkown but has only one possible digit left in its markups
                //then set that number as the cell's primary digit and cross it out
                Cell c = cells[y][x];
                if (!c.isKnown() && c.countMarkups() == 1) {
                    c.setDigit(c.getMarkupDigits()[0]);
                    crossoutKnownCell(x, y);
                }
            }
        }
    }

    /**
     * Calls the sub methods for rows, columns and boxes
     */
    public void preemptiveSets() {
        preemptiveSetsBoxes();
        preemptiveSetsRowsAndColumns();
    }

    /**
     * Iterates through every box and does cross outs based on the findings.
     */
    private void preemptiveSetsBoxes() {
        //for every box
        for(int yBox = 0; yBox < 3; yBox++) {
            for (int xBox = 0; xBox < 3; xBox++) {

                Cell[] set = new Cell[9];
                for(int y = 0; y < 3; y++) {
                    for(int x = 0; x < 3; x++) {
                        set[3*y+x] = this.cells[yBox*3+y][xBox*3+x];
                    }
                }
                //check the box for preemptive sets and do crossouts in the box
                PreemptiveSetExaminer.examine(set);
            }
        }
    }

    /**
     * Iterates through every row and column and does cross outs based on the findings.
     */
    private void preemptiveSetsRowsAndColumns() {
        //for every row
        for(int y = 0; y < 9; y++) {
            Cell[] setRow = new Cell[9];
            Cell[] setColumn = new Cell[9];
            for(int x = 0; x < 9; x++) {
                setRow[x] = this.cells[y][x];
                setColumn[x] = this.cells[x][y];
            }
            //check the rows and columns for preemptive sets
            //crossouts in the rows and columns
            PreemptiveSetExaminer.examine(setColumn);
            PreemptiveSetExaminer.examine(setRow);
        }
    }

}
