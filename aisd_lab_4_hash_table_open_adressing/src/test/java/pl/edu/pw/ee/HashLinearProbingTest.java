package pl.edu.pw.ee;

import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashLinearProbingTest extends HashTesting {

    @Override
    HashTable<Double> createDouble() {
        return new HashLinearProbing<Double>();
    }

    @Override
    HashTable<String> createString() {
        return new HashLinearProbing<String>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        int initialSize = 0;
        HashTable<Double> hash = new HashLinearProbing<>(initialSize);
        assert false;
    }

}
