package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class HeapTest {
    private Heap heap;

    @Before
    public void setUp() {
        heap = new Heap();
    }

    @Test(expected = Heap.ArrayIndexOutOfBoundsException.class)
    public void shouldReturnFalse_PopWhenIsEmpty() {
        heap.pop();
    }

    @Test
    public void shouldReturnTrue_HeapPutOneElementAndPopOneElement() {
        heap.put(5);
        Comparable expected = 5;
        Comparable actual = heap.pop();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrue_HeapPutFewElementsNotSortedAndPopBiggestValue() {
        heap.put(3);
        heap.put(11);
        heap.put(-6);
        heap.put(1220);
        heap.put(-123);
        Comparable expected = 1220;
        Comparable actual = heap.pop();

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnTrue_PutFewElementsAndPopAllOffThem() {
        heap.put(3);
        heap.put(12);
        heap.put(123);
        heap.put(293);
        boolean result = true;
        Comparable[] expected = { 293, 123, 12, 3 };

        for (int i = 0; i < expected.length; i++) {
            if (heap.pop().compareTo(expected[i]) != 0) {
                result = false;
            }
        }

        assertTrue(result);
    }
}
