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
                cells[y][x] = new Cell(x, y, sudokuContent[y][x]);
            }
        }
    }

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

    public void checkSingletons() {
        checkSingletonsSingleMarkup();
    }

    private void checkSingletonsSingleMarkup() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                //If a cell is unkown but has only one possible digit left in its markups
                //then set that number as the cells primary digit and cross it out
                Cell c = cells[y][x];
                if (!c.isKnown() && c.countMarkups() == 1) {
                    c.setDigit(c.getMarkupDigits()[0]);
                }
            }
        }
    }
}
