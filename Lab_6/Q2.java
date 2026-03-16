package Lab_6;

import org.junit.Test;
import static org.junit.Assert.*;

public class Q2 {
    @Test
    public void testPushCoverage() {
        Stack<Integer> s = new Stack<>();
        s.push(10);
        assertFalse(s.isEmpty());
        assertEquals(Integer.valueOf(10), s.peek());
        s.push(20);
        assertEquals(Integer.valueOf(20), s.peek());
    }

    @Test
    public void testPopCoverage() {
        Stack<String> s = new Stack<>();
        assertNull("Popping an empty stack should return null", s.pop());
        s.push("Hello");
        s.push("World");
        assertEquals("World", s.pop());
        assertEquals("Hello", s.pop());
    }

    @Test
    public void testPeekCoverage() {
        Stack<Double> s = new Stack<>();
        s.push(3.14);
        assertEquals(Double.valueOf(3.14), s.peek());
    }

    @Test
    public void testIsEmptyCoverage() {
        Stack<Character> s = new Stack<>();
        assertTrue(s.isEmpty());
        s.push('A');
        assertFalse(s.isEmpty());
    }
}