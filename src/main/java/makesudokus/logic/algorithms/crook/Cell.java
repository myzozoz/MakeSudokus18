package makesudokus.logic.algorithms.crook;

/** <p>A data container class for a single Sudoku Cell used by the Crook's algorithm solver.</p>
 *  Has private variables for coordinates and markups and
 *  is aware whether or not the number it contains is known or not
 */
public class Cell {
    private int x;
    private int y;
    private boolean known;
    private int digit;
    private boolean[] markups;

    /**
     * <p>Constructor for the data structure.</p>
     * The digit parameter dictates, whether or not the number in the cell is known when
     * the Cell object is created. Then sets the boolean "known" accordingly. 1-9 means
     * the number is known and 0 means unknown.
     * @param x x axis location
     * @param y y axis location
     * @param digit The known initial digit of the cell. 0 if unknown.
     */
    public Cell(int x, int y, int digit) {
        this.x = x;
        this.y = y;
        this.markups = new boolean[9];
        this.digit = digit;
        if (digit == 0) {
            this.known = false;
            this.generateMarkups();
        } else {
            this.known = true;
        }
    }

    /**
     * Every cell where the contained digit is not originally known is initialized
     * with markups 1-9. Called by the constructor.
     */
    private void generateMarkups() {
        for (int i = 0; i < 9; i++) {
            this.markups[i] = true;
        }
    }

    /**
     * Counts how many markups there are in the cell.
     * @return Returns the amount of markups in the cell.
     */
    public int countMarkups() {
        int counter = 0;
        for (int i = 0; i < 9; i++) {
            if (this.markups[i]) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Crosses out one slot in the markups.
     * In other words, if this method gets an integer n, it will cross out the boolean representing n from the markups.
     * @param digit The number to crossout
     */
    public void crossout(int digit) {
        markups[digit-1] = false;
    }

    /**
     * Getter for the markups.
     * @return Returns markups as list of booleans.
     */
    public boolean[] getMarkups() {
        return this.markups;
    }

    public int[] getMarkupDigits() {
        int[] markupDigits = new int[this.countMarkups()];
        int markupDigitsIterator = 0;
        for (int i = 0; i < 9; i++) {
            if (markups[i]) {
                markupDigits[markupDigitsIterator] = i + 1;
                markupDigitsIterator++;
            }
        }
        return markupDigits;
    }

    /**
     * Getter for the digit
     * @return Returns the contained digit. 0 if unknown.
     */
    public int getDigit() {
        return this.digit;
    }

    /**
     * Setter for the digit.
     * @param newDigit New digit to set.
     */
    public void setDigit(int newDigit) {
        this.digit = newDigit;
    }

    public boolean isKnown() {
        return this.known;
    }
}
