package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashListChainingTest {

    private HashTable<String> hashTable;

    @Before
    public void setUp() {
        hashTable = new HashListChaining<String>(10);

    }

    // Simple add + get
    @Test
    public void addOneElementAndChcekIfInTheList() {
        String expected = "garg";
        hashTable.add(expected);
        String actual = hashTable.get(expected);
        assertEquals(expected, actual);
    }

    // Simple get if null
    @Test
    public void checkIfInEmptyList() {
        String expected = null;
        String actual = hashTable.get("awd");
        assertEquals(expected, actual);
    }

    // Simple add + delete
    @Test
    public void addOneAndDelete_CheckIfEmpty() {
        String t = "garg";
        hashTable.add(t);
        hashTable.delete(t);
        boolean a = hashTable.isEmpty();
        assertTrue(a);
    }

    // Simple delete element not in list
    @Test
    public void tryToDeleteIfElementNotInList() {
        String[] table = { "a", "b", "c", "d", "e" };
        for (int i = 0; i < table.length; i++) {
            hashTable.add(table[i]);
        }
        hashTable.delete("awd");
        assertTrue(true);
    }

    // Many elements in list chcek if in, add
    @Test
    public void addFewElementsAndGetThemToChcekIfThereAreInList() {
        String[] expecteds = { "a", "b", "c", "d", "e", "f", "g" };
        String[] actuals = new String[7];
        int n = 0;
        for (int i = 0; i < expecteds.length; i++) {
            hashTable.add(expecteds[i]);
        }
        String[][] tab = hashTable.getList();

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] != null)
                    actuals[n++] = tab[i][j];
            }
        }
        Arrays.sort(actuals);

        assertArrayEquals(expecteds, actuals);
    }

    // Many elements in list delete them and chcek if list is empty, add + delete
    @Test
    public void addFewElementsAndDeleteAndChcekIfListIsEmpty() {
        boolean expected = true;
        String[] table = { "assf", "bawd", "rgdthc", "dhfth", "ewadaw", "gsarhdtyj", "ktyhd" };
        for (int i = 0; i < table.length; i++) {
            hashTable.add(table[i]);
        }

        for (int i = 0; i < table.length; i++) {
            hashTable.delete(table[i]);
        }

        boolean actual = hashTable.isEmpty();

        assertEquals(expected, actual);
    }
}
