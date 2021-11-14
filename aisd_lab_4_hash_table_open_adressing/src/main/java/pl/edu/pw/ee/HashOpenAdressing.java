package pl.edu.pw.ee;

import pl.edu.pw.ee.exceptions.NotInListException;
import pl.edu.pw.ee.exceptions.NullValueException;
import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final T PUSTY = (T) "SWPzI7PjYli";
    private final double correctLoadFactor;

    HashOpenAdressing() {
        this(2039);
    }

    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.correctLoadFactor = 0.75;
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId].compareTo(newElem) != 0 && hashElems[hashId] != PUSTY) {
            i = i + 1;
            hashId = hashFunc(key, i);
        }

        hashElems[hashId] = newElem;
        nElems++;
    }

    @Override
    public T get(T elem) {
        validateGetElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId].compareTo(elem) != 0) {
            i = i + 1;
            hashId = hashFunc(key, i);
        }

        if (hashElems[hashId] == null || hashElems[hashId].compareTo(elem) != 0)
            throw new NotInListException("Element is not in the list");
        else
            return hashElems[hashId];

    }

    @Override
    public void delete(T elem) {
        validateDeleteElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId].compareTo(elem) != 0) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

        if (hashElems[hashId] == null || hashElems[hashId].compareTo(elem) != 0)
            throw new NotInListException("Element is not in the list");
        else if (hashElems[hashId].compareTo(elem) == 0) {
            hashElems[hashId] = PUSTY;
            nElems--;
        } else
            return;

    }

    // Test
    @Override
    public T[] getArray() {
        return hashElems;
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new NullValueException("Input elem cannot be null!");
        }
    }

    private void validateGetElem(T newElem) {
        if (newElem == null) {
            throw new NullValueException("Get elem cannot be null!");
        }
    }

    private void validateDeleteElem(T newElem) {
        if (newElem == null) {
            throw new NullValueException("Delete elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i);

    int getSize() {
        return size;
    }

    int baseFunction_f_k(int key) {
        double A = Math.sqrt(5) - 1;
        return (int) (size * (key * A % 1));
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        this.size *= 2;
        if (size < 0) {
            throw new ArrayIndexOutOfBoundsException("List size is bigger than max value of int");
        } else {
            T[] newHash = (T[]) new Comparable[this.size];
            int key;
            int j;
            int hashId;
            for (int i = 0; i < hashElems.length; i++) {
                if (hashElems[i] != nil) {
                    T elem = hashElems[i];

                    key = elem.hashCode();
                    j = 0;
                    hashId = hashFunc(key, j);
                    while (newHash[hashId] != nil && newHash[hashId].compareTo(elem) != 0) {
                        j = j + 1;
                        hashId = hashFunc(key, j);
                    }

                    newHash[hashId] = elem;
                }
            }
            hashElems = newHash;
        }
    }
}