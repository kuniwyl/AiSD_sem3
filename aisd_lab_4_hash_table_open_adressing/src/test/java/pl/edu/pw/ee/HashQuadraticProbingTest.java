package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Test;

import pl.edu.pw.ee.exceptions.NotInListException;
import pl.edu.pw.ee.exceptions.NullValueException;
import pl.edu.pw.ee.services.HashTable;

public class HashQuadraticProbingTest {

    // Except tests
    @Test(expected = NullValueException.class)
    public void should_ThrowException_WhenAddNull() {
        HashTable<Double> hash = new HashQuadraticProbing<>();
        hash.put(null);
        assert false;
    }

    @Test(expected = NullValueException.class)
    public void should_ThrowException_WhenGetNull() {
        HashTable<Double> hash = new HashQuadraticProbing<>();
        hash.get(null);
        assert false;
    }

    @Test(expected = NullValueException.class)
    public void should_ThrowException_WhenDeleteNull() {
        HashTable<Double> hash = new HashQuadraticProbing<>();
        hash.delete(null);
        assert false;
    }

    @Test(expected = NotInListException.class)
    public void should_ThrowException_GetNotExisting() {
        HashTable<String> hash = new HashQuadraticProbing<>();
        hash.get("b");
        assert false;
    }

    @Test(expected = NotInListException.class)
    public void should_ThrowException_DeleteNotExisting() {
        HashTable<String> hash = new HashQuadraticProbing<>();
        hash.delete("b");
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        HashTable<Double> hash = new HashQuadraticProbing<>(initialSize, 2, 5);

        // then
        assert false;
    }

    // True test
    @Test
    public void should_ReturnTrue_AddElement_GetElement() {
        HashTable<Double> hash = new HashQuadraticProbing<>();
        Double elem = 3.4;
        hash.put(elem);
        assertEquals(elem, hash.get(elem));
    }

    @Test
    public void should_ReturnTrue_AddElement_DeleteElement() {
        HashTable<Double> hash = new HashQuadraticProbing<>();
        Double elem = 6.4;
        int sizeBeforePut = getNumOfElems(hash);
        hash.put(elem);
        int sizeAfterPut = getNumOfElems(hash);
        hash.delete(elem);
        int sizeAfterDelete = getNumOfElems(hash);

        assertEquals(0, sizeBeforePut);
        assertEquals(1, sizeAfterPut);
        assertEquals(0, sizeAfterDelete);
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashQuadraticProbing<>();
        String newEleme = "nothing special";

        // when
        int nOfElemsBeforePut = getNumOfElems(emptyHash);
        emptyHash.put(newEleme);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenFewElements() {
        // given
        HashTable<String> hash = new HashQuadraticProbing<>();
        String[] table = { "a1", "a2", "a3", "a4", "a5" };

        for (int i = 0; i < table.length; i++) {
            hash.put(table[i]);
        }

        int afterAdding = getNumOfElems(hash);
        assertEquals(table.length, afterAdding);
    }

    @Test
    public void should_CheckIfAfterDeletingPlaceIsPUSTE_Double() {
        // given
        HashTable<Double> hash = new HashQuadraticProbing<>(2, 1.4, 2.4);
        Double[] table = { 1., 2., 3., 4., 5., 6., 7., 8., 9., 10. };

        for (int i = 0; i < table.length; i++) {
            hash.put((Double) table[i]);
        }

        hash.delete(5.);
        hash.delete(7.);
        hash.delete(2.);
        hash.delete(9.);
        int suma = 0;

        Comparable[] list = hash.getArray();
        for (int i = 0; i < list.length; i++) {
            if (list[i] == "SWPzI7PjYli")
                suma++;
        }
        assertEquals(4, suma);
    }

    @Test
    public void should_CheckIfAfterDeletingPlaceIsPUSTE_String() {
        HashTable<String> hash = new HashQuadraticProbing<>(2, 6.7, 2.3);
        String[] table = { "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8" };
        for (int i = 0; i < table.length; i++) {
            hash.put(table[i]);
        }
        hash.delete("a1");
        Comparable[] list = hash.getArray();
        for (int i = 0; i < list.length; i++) {
            if (list[i] == "SWPzI7PjYli")
                assert true;
        }
    }

    private int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        try {
            System.out.println(hash.getClass().getSuperclass().getName());
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
