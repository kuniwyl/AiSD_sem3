package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HeapTest {
    
    private Heap heap;

    @Before
    public void setUp(){
        heap = new Heap();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnTrueWhenNullIsGiven(){
        heap.put(null);
        assert false;
    }

    @Test
    public void shouldReturnTrue_GivenElementAndGetElement(){
        heap.put(new Element("p", "d", 4));
        Element expected = heap.pop();
        assertEquals(expected.getFirst(), "p");
        assertEquals(expected.getSecond(), "d");
        assertEquals(expected.getValue(), 4);
    }
    
    @Test
    public void shouldReturnTrue_GivenFewElementAndGetElementLowestByValue(){
        heap.put(new Element("a", "A", 3));
        heap.put(new Element("b", "B", 7));
        heap.put(new Element("c", "C", 2));
        heap.put(new Element("d", "D", 10));
        Element expected = heap.pop();
        assertEquals(expected.getFirst(), "c");
        assertEquals(expected.getSecond(), "C");
        assertEquals(expected.getValue(), 2);
    }

}
