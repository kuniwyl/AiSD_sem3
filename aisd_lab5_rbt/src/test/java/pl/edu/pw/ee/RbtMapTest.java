package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pl.edu.pw.ee.services.MapInterface;

public class RbtMapTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnTrue_SetValueIsNull() {
        MapInterface<Integer, Integer> tree = new RbtMap<Integer, Integer>();
        tree.setValue(1, null);
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnTrue_SetKeyIsNull() {
        MapInterface<Integer, Integer> tree = new RbtMap<Integer, Integer>();
        tree.setValue(null, 1);
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnTrue_GetKeyIsNull() {
        MapInterface<Integer, Integer> tree = new RbtMap<Integer, Integer>();
        tree.getValue(null);
        assert true;
    }

    @Test
    public void shouldReturnNull_GetWithEmptyTree() {
        MapInterface<Integer, Integer> tree = new RbtMap<Integer, Integer>();
        Object actual = tree.getValue(1);
        Object expected = null;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrue_PutWithOneAndGet() {
        MapInterface<Double, Double> tree = new RbtMap<Double, Double>();
        tree.setValue(1.1, 1.2);
        Double actual = tree.getValue(1.1);
        Double expected = 1.2;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrue_PutManyAndGetManyDoubles() {
        MapInterface<Double, Double> tree = new RbtMap<Double, Double>();
        tree.setValue(1.2, 10.2);
        tree.setValue(1.3, 135.3);
        tree.setValue(1.4, 121235.4);
        tree.setValue(1.1, 0.1);
        Double actual = tree.getValue(1.1);
        Double actual1 = tree.getValue(1.2);
        Double actual2 = tree.getValue(1.3);
        Double actual3 = tree.getValue(1.4);
        assertEquals(0.1, actual, 0);
        assertEquals(10.2, actual1, 0);
        assertEquals(135.3, actual2, 0);
        assertEquals(121235.4, actual3, 0);
    }

    @Test
    public void shouldReturnTrue_PutManyAndGetManyStrings() {
        MapInterface<String, String> tree = new RbtMap<String, String>();
        tree.setValue("a", "ala");
        tree.setValue("m", "ma");
        tree.setValue("k", "kotela");
        assertEquals("ala", tree.getValue("a"));
        assertEquals("ma", tree.getValue("m"));
        assertEquals("kotela", tree.getValue("k"));
    }

    @Test
    public void shouldReturnTrue_PutManyAndGetManyIntegersAndStrgins() {
        MapInterface<Integer, String> tree = new RbtMap<Integer, String>();
        tree.setValue(1, "ala");
        tree.setValue(2, "ma");
        tree.setValue(3, "kotela");
        tree.setValue(4, "kuba");
        tree.setValue(5, "posiada");
        tree.setValue(6, "psa");
        assertEquals("ala", tree.getValue(1));
        assertEquals("ma", tree.getValue(2));
        assertEquals("kotela", tree.getValue(3));
        assertEquals("psa", tree.getValue(6));
        assertEquals("posiada", tree.getValue(5));
        assertEquals("kuba", tree.getValue(4));
    }

}
