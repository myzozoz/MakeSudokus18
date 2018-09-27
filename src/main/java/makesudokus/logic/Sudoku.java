package makesudokus.logic;

import java.util.Arrays;

/**
 * A data container class with minor helper functions. Contains the sudoku puzzle.
 */
public class Sudoku {
    private int[][] content;

    /**
     * Constructor creates a new, empty, 9x9 grid. Empty slots are contained as the number 0 under the hood.
     */
    public Sudoku() {
        //Eventually might be replaced by code to fetch random content from a list,
        //generate one or start an empty one.
        content = new int[9][9];
    }

    /**
     * @return Contents of the sudoku.
     */
    public int[][] getContent() {
        return content;
    }

    /**
     * Allows for manual setting of the sudokus contents.
     * @param newContent New contents for the sudoku.
     */
    public void setContent(int[][] newContent) {
        content = new int[9][9];
        for (int y = 0; y < 9; y++) {
            content[y] = Arrays.copyOf(newContent[y], 9);
        }
    }

    /**
     * Sets the number in a single, specific location.
     * @param x x -axis coordinate.
     * @param y y -axis coordinate.
     * @param newNumber New number to be placed in the slot.
     * @return Returns true if the operation was a success. Failure can happen
     * if the newNumber is out of the 1-9 range and if the coordinates are outside of the 0-8 range.
     */
    public boolean setNumber(int x, int y, int newNumber) {
        if (newNumber >= 0 && newNumber <= 9 &&
        x >= 0 && x <= 8 &&
        y >= 0 && y <= 8) {
            content[y][x] = newNumber;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the content of a specific coordinate
     * @param x x -axis coordinate.
     * @param y y -axis coordinate.
     * @return Returns the content of a specific coordinate
     */
    public int getNumber(int x, int y) {
        return content[y][x];
    }

    /**
     * A helper method for 'stringifying' the number array.
     * @return Returns the contents of the sudoku as a single, formatted string.
     */
    @Override
    public String toString(){
        String s = "";
        for (int y = 0; y < this.content.length; y++) {
            for (int x = 0; x < this.content[y].length; x++) {
                s += Integer.toString(content[y][x]);
            }
            s+= "\n";
        }
        return s;
    }
}
