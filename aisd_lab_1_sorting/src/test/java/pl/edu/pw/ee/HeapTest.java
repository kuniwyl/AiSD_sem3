package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HeapTest {
    private Heap heap;

    @Before
    public void setUp() {
        heap = new Heap();
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
}
