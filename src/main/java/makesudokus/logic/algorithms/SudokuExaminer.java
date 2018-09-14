package makesudokus.logic.algorithms;


public class SudokuExaminer {
    public static boolean checkSolution(int[][] sudokuContents) {
        //A sudoku is solved, when the following conditions are met:
        //-Every row contains every number from 1 to 9 exactly once
        if (!checkRows(sudokuContents))
            return false;
        //-Every column contains every number from 1 to 9 exactly once
        if (!checkColumns(sudokuContents))
            return false;
        //-Evey 3x3 cell contains every number from 1 to 9 exactly once
        if (!checkCells(sudokuContents))
            return false;

        return true;
    }

    public static boolean checkRows(int[][] sudokuContents) {
        for (int y = 0; y < 9; y++) {
            if (!checkNumberSet(sudokuContents[y]))
                return false;
        }
        return true;
    }

    public static boolean checkColumns(int[][] sudokuContents) {
        for (int x = 0; x < 9; x++) {
            int[] numSet = new int[9];
            for (int y = 0; y < 9; y++) {
                numSet[y] = sudokuContents[y][x];
            }
            if (!checkNumberSet(numSet))
                return false;
        }
        return true;
    }

    public static boolean checkCells(int[][] sudokuContents) {
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

                if(!checkNumberSet(numSet))
                    return false;

            }
        }
        return true;
    }

    public static boolean checkNumberSet(int[] numberSet) {
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
