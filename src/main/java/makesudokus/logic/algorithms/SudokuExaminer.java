package makesudokus.logic.algorithms;

/**
 * A class dedicated for testing whether a sudoku is solved or not.
 */
public class SudokuExaminer {
    /**
     * <p>This is the master method, acting as a coordinator for the lower-level methods.</p>
     *
     * A sudoku is solved, when the following conditions are met:
     * <ul>
     * <li>Every row contains every number from 1 to 9 exactly once</li>
     * <li>Every column contains every number from 1 to 9 exactly once</li>
     * <li>Every 3x3 cell contains every number from 1 to 9 exactly once</li>
     * </ul>
     * @param sudokuContents The contents of a sudoku in a two-dimensional array.
     * @return Returns true if the sudoku is solved, othewise returns false.
     */
    public static boolean checkForWinner(int[][] sudokuContents) {
        //A sudoku is solved, when the following conditions are met:
        //-Every row contains every number from 1 to 9 exactly once
        if (!checkRowsForCompletion(sudokuContents))
            return false;
        //-Every column contains every number from 1 to 9 exactly once
        if (!checkColumnsForCompletion(sudokuContents))
            return false;
        //-Every 3x3 cell contains every number from 1 to 9 exactly once
        if (!checkCellsForCompletion(sudokuContents))
            return false;

        return true;
    }

    /**
     * Checks if there are any illegal entries on any row, column or 3x3 cell.
     * @param sudokuContents Contents of a sudoku puzzle
     * @return Returns true if there were no obvious mistakes in the puzzle.
     */
    public static boolean legal(int[][] sudokuContents) {
        if (!legalRows(sudokuContents))
            return false;
        if (!legalColumns(sudokuContents))
            return false;
        if (!legalCells(sudokuContents))
            return false;
        return true;
    }

    /**
     * <p>NOT IMPLEMENTED YET! Designed for slightly more efficient legality checks on the sudoku.</p>
     * The basic principle is: Assuming our sudoku had no illegal entries before the last entry
     * and assuming that only one number was changed, then we only have to check if the single row,
     * the single column and the single cell that number exists in is legal.
     * @param sudokuContents Contents of the sudoku to be checked.
     * @param x -axis coordinate of the last number to be changed.
     * @param y -axis coordinat of the last number to be changed.
     * @return Returns true, if the contents of the row, column and cell of given coordinate are legal.
     */
    public static boolean legal(int[][] sudokuContents, int x, int y) {

        return false;
    }

    /**
     * Checks that the rows don't contain duplicates.
     * @param sudokuContents Contents of a sudoku.
     * @return Returns true when there are no duplicates on any row.
     */
    public static boolean legalRows(int[][] sudokuContents) {
        for (int y = 0; y < 9; y++) {
            if (!noDuplicatesCheck(sudokuContents[y]))
                return false;
        }
        return true;
    }

    /**
     * Checks if the rows are correct in a sudoku.
     * @param sudokuContents The contents of a sudoku in a two-dimensional array.
     * @return Returns true if the rows are correct.
     */
    public static boolean checkRowsForCompletion(int[][] sudokuContents) {
        for (int y = 0; y < 9; y++) {
            if (!checkNumberSetForCompletion(sudokuContents[y]))
                return false;
        }
        return true;
    }

    /**
     * Checks that the columns don't contain duplicates.
     * @param sudokuContents Contents of a sudoku.
     * @return Returns true when there are no duplicates in any column.
     */
    public static boolean legalColumns(int[][] sudokuContents) {
        for (int x = 0; x < 9; x++) {
            int[] numSet = new int[9];
            for (int y = 0; y < 9; y++) {
                numSet[y] = sudokuContents[y][x];
            }
            if (!noDuplicatesCheck(numSet))
                return false;
        }
        return true;
    }

    /**
     * Checks if the columns are correct in a sudoku.
     * @param sudokuContents The contents of a sudoku in a two-dimensional array.
     * @return Returns true if the columns are correct.
     */
    public static boolean checkColumnsForCompletion(int[][] sudokuContents) {
        for (int x = 0; x < 9; x++) {
            int[] numSet = new int[9];
            for (int y = 0; y < 9; y++) {
                numSet[y] = sudokuContents[y][x];
            }
            if (!checkNumberSetForCompletion(numSet))
                return false;
        }
        return true;
    }

    /**
     * Checks that the cells don't contain duplicates.
     * @param sudokuContents Contents of a sudoku.
     * @return Returns true when there are no duplicates in any cell.
     */
    public static boolean legalCells(int[][] sudokuContents) {
        //First we create a nested loop that cycles through all the 3x3 cells
        for (int cellRow = 0; cellRow < 3; cellRow++) {
            for (int cellCol = 0; cellCol < 3; cellCol++){
                //We create a helper array to contain the numbers we send for the set checker
                int[] numSet = new int[9];
                //Then we create another nested loop that cycles through the numbers in the cells
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 3; x++) {
                        numSet[(y*3)+x] = sudokuContents[cellRow*3+y][cellCol*3+x];
                    }
                }

                if(!noDuplicatesCheck(numSet))
                    return false;

            }
        }
        return true;
    }

    /**
     * Checks if the cells are correct in a sudoku.
     * @param sudokuContents The contents of a sudoku in a two-dimensional array.
     * @return Returns true if the columns are correct.
     */
    public static boolean checkCellsForCompletion(int[][] sudokuContents) {
        //First we create a nested loop that cycles through all the 3x3 cells
        for (int cellRow = 0; cellRow < 3; cellRow++) {
            for (int cellCol = 0; cellCol < 3; cellCol++){
                //We create a helper array to contain the numbers we send for the set checker
                int[] numSet = new int[9];
                //Then we create another nested loop that cycles through the numbers in the cells
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 3; x++) {
                        numSet[(y*3)+x] = sudokuContents[cellRow*3+y][cellCol*3+x];
                    }
                }

                if(!checkNumberSetForCompletion(numSet))
                    return false;

            }
        }
        return true;
    }

    /**
     * Checks that there are no duplicates of the numbers 1-9 in the given set.
     * @param numberSet Set of numbers.
     * @return Returns true when there are no duplicates.
     */
    public static boolean noDuplicatesCheck(int[] numberSet) {
        //We assume we only get arrays of nine integers, but we don't really care.
        //Because the sets are so small, we don't care a lot about efficiency either.
        //With sets of 9 numbers the iterations are still 9x9=81 times, which is not a lot.
        for (int i = 0; i < numberSet.length; i++) {
            int numberCount = 0;

            if (numberSet[i] >= 1 && numberSet[i] <= 9) {
                for (int e = 0; e < numberSet.length; e++) {
                    if (numberSet[i] == numberSet[e]) {
                        numberCount++;
                    }
                }
            }

            if (numberCount > 1)
                return false;
        }
        return true;
    }

    /**
     * An assisting function, dedicated to check arrays of exactly 9 integers.
     * The method checks the contents for uniqueness and size.
     * @param numberSet A set of 9 integers.
     * @return Returns true if there are exactly 9 unique integers, the numbers from 1 to 9.
     */
    public static boolean checkNumberSetForCompletion(int[] numberSet) {
        //Check to see that the numberSet is legal
        if( numberSet.length != 9) {
            return false;
        }

        //We need to find each number
        for (int numberToFind = 1; numberToFind <= 9; numberToFind++) {
            boolean numberFound = false;
            for (int i = 0; i < 9; i++) {
                if (numberSet[i] == numberToFind)
                    numberFound = true;
            }
            if (!numberFound) {
                return false;
            }
        }

        return true;
    }
}
