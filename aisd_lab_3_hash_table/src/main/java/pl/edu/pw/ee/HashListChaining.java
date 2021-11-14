package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem<T> nil = null;
    private Elem<T>[] hashElems;
    private int nElem;

    private class Elem<K extends T> {
        private K value;
        private Elem<K> next;

        Elem(K value, Elem<K> nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        hashElems = new Elem[size];
        initializeHash();
    }

    public double countLoadFactor() {
        double size = hashElems.length;
        return nElem / size;
    }

    private void initializeHash() {
        int n = hashElems.length;

        for (int i = 0; i < n; i++) {
            hashElems[i] = nil;
        }
    }

    private int countHashId(int hashCode) {
        int n = hashElems.length;
        return Math.abs(hashCode) % n;
    }

    @Override
    public void add(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> oldElem = hashElems[hashId];
        while (oldElem != nil && oldElem.value.compareTo(value) != 0) {
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value.compareTo(value);
        } else {
            hashElems[hashId] = new Elem<T>(value, hashElems[hashId]);
            nElem++;
        }
    }

    @Override
    public T get(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);
        Elem<T> elem = hashElems[hashId];
        while (elem != nil && elem.value.compareTo(value) != 0) {
            elem = elem.next;
        }
        return elem != nil ? elem.value : null;
    }

    @Override
    public void delete(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);
        Elem<T> head = hashElems[hashId];

        if (head == nil) {
            return;
        }
        if (head.value.compareTo(value) == 0) {
            if (head.next != nil) {
                head.value = head.next.value;
                head.next = head.next.next;
            } else {
                hashElems[hashId] = nil;
            }
        } else {
            while (head.next != nil && head.next.value.compareTo(value) != 0) {
                head = head.next;
            }
            if (head.next.value.compareTo(value) == 0 && head.next.next == nil) {
                head.next = nil;
            } else if (head.next.value.compareTo(value) == 0) {
                head.next = head.next.next;
            }
        }
    }

    // Test only
    @Override
    public boolean isEmpty() {
        boolean s = true;
        for (int i = 0; i < hashElems.length; i++) {
            if (hashElems[i] != nil)
                s = false;
        }
        return s;
    }

    // Test only
    @Override
    public String[][] getList() {
        String[][] tab = new String[hashElems.length][100000];

        for (int i = 0; i < hashElems.length; i++) {
            Elem<T> elem = hashElems[i];
            int j = 0;
            while (elem != nil) {
                tab[i][j++] = (String) elem.value;
                elem = elem.next;
            }
        }

        return tab;
    }
}