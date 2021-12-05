package pl.edu.pw.ee;

import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashDoubleHashingTest extends HashTesting {

    @Override
    HashTable<Double> createDouble() {
        return new HashDoubleHashing<Double>();
    }

    @Override
    HashTable<String> createString() {
        return new HashDoubleHashing<String>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        int initialSize = 0;
        HashTable<Double> hash = new HashDoubleHashing<>(initialSize);
        assert false;
    }

}
