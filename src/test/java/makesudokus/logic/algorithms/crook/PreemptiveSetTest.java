package makesudokus.logic.algorithms.crook;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PreemptiveSetTest {
    PreemptiveSet ps;

    @Before
    public void init() {
        ps = new PreemptiveSet();
    }

    @Test
    public void initializesCorrectly() {
        assertEquals(0, ps.getSize());
        assertEquals(0, ps.getSpan());
    }
}
