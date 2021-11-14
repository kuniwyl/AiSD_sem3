package pl.edu.pw.ee.services;

public interface HeapInterface<T extends Comparable<T>> {
    public void put(T item);

    public T pop();
}