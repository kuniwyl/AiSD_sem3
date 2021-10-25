package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining implements HashTable {

    private final Elem nil = null;
    private Elem[] hashElems;
    private int nElem;

    private class Elem {
        private Object value;
        private Elem next;

        Elem(Object value, Elem nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        hashElems = new Elem[size];
        initializeHash();
    }

    @Override
    public void add(Object value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem oldElem = hashElems[hashId];
        while (oldElem != nil && !oldElem.value.equals(value)) {
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems[hashId] = new Elem(value, hashElems[hashId]);
            nElem++;
        }
    }

    @Override
    public Object get(Object value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems[hashId];
        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : nil;
    }

    @Override
    public String[][] get_table(){
        String[][] tab = new String[2][10];
        int n;
        for(int i = 0; i < hashElems.length; i++){
            Elem elem = hashElems[i];
            n = 0;
            while (elem != nil) {
                tab[i][n++] = (String) elem.value;
                elem = elem.next;
            }
        }
        return tab;
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

}