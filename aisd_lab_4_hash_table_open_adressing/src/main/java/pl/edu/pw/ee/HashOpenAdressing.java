package pl.edu.pw.ee;

import java.util.UUID;

import pl.edu.pw.ee.exceptions.NotInListException;
import pl.edu.pw.ee.exceptions.NullValueException;
import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final String PUSTY = UUID.randomUUID().toString();
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

        while (hashElems[hashId] != nil && hashElems[hashId].compareTo(newElem) != 0
                && hashElems[hashId] != (T) PUSTY && i < size) {
            i = i + 1;
            hashId = hashFunc(key, i);
        }
        if(i == size){
            doubleResize();
            put(newElem);
            return;
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
            if (i > size) {
                break;
            }
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
            if (i >= size) {
                break;
            }
        }

        if (hashElems[hashId] == null || hashElems[hashId].compareTo(elem) != 0)
            throw new NotInListException("Element is not in the list");
        else if (hashElems[hashId].compareTo(elem) == 0) {
            hashElems[hashId] = (T) PUSTY;
            nElems--;
        } else
            return;

    }

    // Test
    @Override
    public T[] getArray() {
        return hashElems;
    }

    @Override
    public String getUidd() {
        return PUSTY;
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
        T[] oldHash = hashElems;
        size *= 2;
        nElems = 0;
        hashElems = (T[]) new Comparable[size];
        for (T i : oldHash) {
            if (i != nil && i != PUSTY) {
                put(i);
            }
        }
    }
}