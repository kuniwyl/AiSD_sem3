package pl.edu.pw.ee;

import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashQuadraticProbingTest extends HashTesting {

    @Override
    HashTable<Double> createDouble() {
        return new HashQuadraticProbing<Double>();
    }

    @Override
    HashTable<String> createString() {
        return new HashQuadraticProbing<String>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        int initialSize = 0;
        HashTable<Double> hash = new HashQuadraticProbing<>(initialSize, 1, 2);
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenAIs0() {
        int initialSize = 12;
        HashTable<Double> hash = new HashQuadraticProbing<>(initialSize, 0, 1);
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenBIs0() {
        int initialSize = 12;
        HashTable<Double> hash = new HashQuadraticProbing<>(initialSize, 2, 0);
        assert false;
    }

}
