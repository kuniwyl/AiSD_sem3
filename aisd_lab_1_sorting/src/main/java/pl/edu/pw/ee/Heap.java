package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.ee.services.HeapInterface;

public class Heap implements HeapInterface {

    private List<Comparable> items = new ArrayList<Comparable>();
    private int itemsSize = 0;

    private void swap(int i, int j) {
        if (i == j)
            return;
        Comparable temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);
    }

    private boolean compareTwo(int first, int second) {
        return items.get(first).compareTo(items.get(second)) < 0;
        // jeśli pierwszy jest większy od drugiego: false
        // jeśli pierwszy jest mniejszy od drugiego: true
        // jeśli są równe: false
    }

    private int leftChild(int i) {
        return i * 2 + 1;
    }

    private int rightChild(int i) {
        return i * 2 + 2;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int maxValueIndexFunction(int left, int right) {
        if (compareTwo(left, right))
            return right;
        else
            return left;
    }

    @Override
    public void put(Comparable item) {
        items.add(item);
        itemsSize++;

        int curret = itemsSize - 1;
        int parent = parent(curret);

        while (curret >= 0 && compareTwo(parent, curret)) {
            swap(parent, curret);
            curret = parent;
            parent = parent(curret);
        }
    }

    @Override
    public Comparable pop() {
        int currentPosition = 0;
        int maxValueIndex = 0;

        swap(0, itemsSize - 1);
        itemsSize--;

        do {
            if (rightChild(currentPosition) < itemsSize) {
                maxValueIndex = maxValueIndexFunction(rightChild(currentPosition), leftChild(currentPosition));
            } else if (leftChild(currentPosition) < itemsSize) {
                maxValueIndex = leftChild(currentPosition);
            }

            if (compareTwo(currentPosition, maxValueIndex)) {
                swap(currentPosition, maxValueIndex);
                currentPosition = maxValueIndex;
            } else
                break;

        } while (rightChild(currentPosition) < itemsSize || leftChild(currentPosition) < itemsSize);

        return items.get(itemsSize);
    }
}
