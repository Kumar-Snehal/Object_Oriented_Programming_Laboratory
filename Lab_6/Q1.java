package Lab_6;

import org.junit.Test;
import static org.junit.Assert.*;

public class Q1 {

    @Test
    public void testInsertCoverage() {
        Set s = new Set();
        s.insert(5);
        s.insert(3);
        s.insert(5);
        s.insert(10);
    }

    @Test
    public void testMemberCoverage() {
        Set s = new Set();
        assertFalse(s.member(1));
        s.insert(5);
        assertFalse(s.member(3));
        assertTrue(s.member(5));
        assertFalse(s.member(7));
    }

    @Test
    public void testSectionCoverage() {
        Set s1 = new Set();
        Set s2 = new Set();
        s1.section(s2);

        s1 = new Set();
        s1.insert(5);

        s2 = new Set();
        s2.insert(5);
        s1.section(s2);

        s1 = new Set();
        s1.insert(3);

        s2 = new Set();
        s2.insert(5);
        s1.section(s2);

        s1 = new Set();
        s1.insert(5);

        s2 = new Set();
        s2.insert(3);
        s1.section(s2);
    }

    @Test
    public void testContainsArithTripleCoverage() {
        Set s = new Set();
        assertFalse(s.containsArithTriple());
        s.insert(5);
        assertTrue(s.containsArithTriple());
    }
}