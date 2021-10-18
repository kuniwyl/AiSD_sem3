package pl.edu.pw.ee;

import java.util.ArrayList;

import pl.edu.pw.ee.services.HeadInterface;

public class Heap implements HeadInterface{

    private double[] items = new double[16];
    private int itemsSize = 0;


    private void doubleSize(){
        double[] tepmoraryTable = new double[items.length * 2];
        System.arraycopy(items, 0, tepmoraryTable, 0, items.length);
        items = tepmoraryTable;
    }

    private void swap(int i, int j){
        double temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    private int leftChild(int i){
        return i * 2 + 1;
    }

    private int rightChild(int i){
        return i * 2 + 2;
    }

    private int parent(int i){
        return (i - 1) / 2;
    }

    @Override
    public void put(Comparable item) {
        // TODO Auto-generated method stub
    }

    @Override
    public Comparable pop() {
        swap(0, itemsSize - 1);
        
        int currentPosition = 0;
        while(leftChild(currentPosition) < itemsSize || rightChild(currentPosition) < itemsSize{

        }

        return null;
    }
}
