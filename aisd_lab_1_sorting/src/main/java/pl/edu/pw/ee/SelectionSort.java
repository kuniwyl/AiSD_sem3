package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {    
        if( nums == null){
            throw new IllegalArgumentException("Nums array cannot be null");
        }

        selectionSort(nums);
    }

    private void selectionSort (double[] nums){
        for(int i = 0; i < nums.length - 2; i++){
            int minIndex = i + 1;
            for(int j = i + 1; j < nums.length - 1; j++){
                minIndex = j;
            }
            swap(nums, minIndex, i);
        }
    }

    private void swap(double[] data, int firstId, int secondId) {
        if (firstId != secondId) {
            double firstValue = data[firstId];
            data[firstId] = data[secondId];
            data[secondId] = firstValue;
        }
    }
}