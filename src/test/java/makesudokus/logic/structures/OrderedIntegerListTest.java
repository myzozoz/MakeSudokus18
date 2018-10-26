package makesudokus.logic.structures;

import makesudokus.structures.OrderedIntegerList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderedIntegerListTest {
    OrderedIntegerList oil;

    @Before
    public void init() {
        oil = new OrderedIntegerList();
    }

    @Test
    public void findsContainedNumber() {
        oil.add(10);
        assertTrue(oil.contains(10));
    }


    @Test
    public void canAddNumbers() {
        oil.add(10);
        oil.add(20);
        oil.add(15);
        oil.add(12);
        assertTrue(oil.contains(20));
        assertTrue(oil.contains(15));
        assertTrue(oil.contains(12));
    }

    @Test
    public void addedNumbersInCorrectOrder() {
        oil.add(10);
        oil.add(20);
        oil.add(15);
        oil.add(12);
        assertEquals(10, oil.get(0));
        assertEquals(12, oil.get(1));
        assertEquals(15, oil.get(2));
        assertEquals(20, oil.get(3));
    }

    @Test
    public void canAddLoadsOfIntegers() {
        for(int i = 0; i < 1000000; i++) {
            oil.add(i);
        }
        assertEquals(1000000, oil.size());
    }

    @Test
    public void illegalIndexReturnsNegativeDevil() {
        oil.add(100);
        assertEquals(-666, oil.get(-1));
        assertEquals(-666, oil.get(1));
        assertEquals(-666, oil.get(10000));
    }
}
