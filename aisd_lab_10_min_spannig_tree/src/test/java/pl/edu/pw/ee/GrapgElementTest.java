package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GrapgElementTest {
    
    private GraphElement graphElement;

    @Before
    public void setUp(){
        graphElement = new GraphElement("newGraph");
    }

    @Test
    public void shouldReturnNull_GetWhenEmpty(){
        Element temp = graphElement.getElements();
        assertEquals(null, temp);
    }

    @Test
    public void shouldReturnTrue_IsEmptyDoubleTest(){
        assertEquals(true, graphElement.isEmpty());
        graphElement.addElement("rar", 5);
        assertEquals(false, graphElement.isEmpty());
    }

    @Test
    public void shouldReturnTrueWhenGivenName(){
        assertEquals("newGraph", graphElement.getName());
    }

    @Test
    public void shouldReturnTrue_WhenGivenOneElementToList(){
        graphElement.addElement("absr", 3);
        Element temp = graphElement.getElements();
        assertEquals("newGraph", temp.getFirst());
        assertEquals("absr", temp.getSecond());
        assertEquals(3, temp.getValue());
    }
}
