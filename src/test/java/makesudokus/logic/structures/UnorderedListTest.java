package makesudokus.logic.structures;

import makesudokus.logic.algorithms.crook.Cell;
import makesudokus.structures.UnorderedList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UnorderedListTest {
    UnorderedList ul;

    @Before
    public void init() {
        this.ul = new UnorderedList();
    }

    @Test
    public void initializesSizeToZero() {
        assertEquals(0, ul.size());
    }

    @Test
    public void canAddIntegers() {
        ul.add(1);
        ul.add(2);
        ul.add(3);
        assertEquals(1, (int)ul.get(0));
        assertEquals(2, (int)ul.get(1));
        assertEquals(3, (int)ul.get(2));
    }

    @Test
    public void canAddCells() {
        ul.add(new Cell(1));
        ul.add(new Cell(2));
        assertEquals(1, ((Cell)ul.get(0)).getDigit());
        assertEquals(2, ((Cell)ul.get(1)).getDigit());
    }

    @Test
    public void canAddLoadsOfCells() {
        for (int i = 0; i < 60; i++) {
            ul.add(new Cell(i%9));
        }
        assertEquals(60, ul.size());
    }

    @Test
    public void canRemoveEntries() {
        ul.add(100);
        ul.add(200);
        ul.add(300);
        ul.add(400);
        ul.remove(2);
        assertEquals(3, ul.size());
        assertEquals(400, ul.get(2));
    }

    @Test
    public void cantRemoveOutOfBoundsIndex() {
        ul.add(100);
        ul.add(200);
        ul.add(300);
        ul.add(400);
        ul.remove(10);
        assertEquals(4, ul.size());
    }
}
