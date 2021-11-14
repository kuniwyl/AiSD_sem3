package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        selectionSort(nums);
    }

    private void selectionSort(double[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex])
                    minIndex = j;
            }
            swap(nums, minIndex, i);
        }
    }

    private void swap(double[] data, int firstId, int secondId) {
        if (firstId == secondId)
            return;
        double firstValue = data[firstId];
        data[firstId] = data[secondId];
        data[secondId] = firstValue;
    }
}