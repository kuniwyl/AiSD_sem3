package pl.edu.pw.ee.services;

public interface HashTable<T extends Comparable<T>> {

    void add(T value);

    T get(T value);

    void delete(T value);

    // Test only
    boolean isEmpty();

    // Test only
    String[][] getList();
}