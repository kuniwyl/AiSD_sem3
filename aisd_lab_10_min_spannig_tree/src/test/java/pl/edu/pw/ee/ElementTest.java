package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class ElementTest {

    @Test
    public void shouldReturnTrue_GiveOneGetOne(){
        Element element = new Element("p", null, 3);
        assertEquals(null, element.getSecond());
        assertEquals("p", element.getFirst());
        assertEquals(3, element.getValue());
    }
}
