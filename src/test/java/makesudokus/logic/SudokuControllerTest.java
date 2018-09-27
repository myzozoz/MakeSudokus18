package makesudokus.logic;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class SudokuControllerTest {
    SudokuController sc;
    int[][] startingSudoku;

    @Before
    public void init() {
        sc = new SudokuController();
        startingSudoku = new int[9][9];
        for (int y = 0; y < 9; y++) {
            startingSudoku[y]= Arrays.copyOf(sc.getSudoku()[y], 9);
        }
    }

    @Test
    public void canResetSudoku() {
        sc.updateNumber(1,1,8);
        sc.updateNumber(1,2,8);
        sc.updateNumber(1,3,8);
        sc.resetSudoku();
        assertArrayEquals(startingSudoku, sc.getSudoku());
    }

    @Test
    public void canResetSudokuMultipleTimes() {
        sc.updateNumber(3,1,8);
        sc.updateNumber(3,2,8);
        sc.updateNumber(3,3,8);
        sc.resetSudoku();
        assertArrayEquals(startingSudoku, sc.getSudoku());

        sc.updateNumber(8,1,8);
        sc.updateNumber(8,2,8);
        sc.updateNumber(1,3,8);
        sc.resetSudoku();
        assertArrayEquals(startingSudoku, sc.getSudoku());

        sc.updateNumber(1,1,8);
        sc.updateNumber(4,2,8);
        sc.updateNumber(3,3,8);
        sc.resetSudoku();
        assertArrayEquals(startingSudoku, sc.getSudoku());
    }
}
