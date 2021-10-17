package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        insertionSort(nums);
    }

    public void insertionSort(double[] nums) {

        for (int i = 1; i < nums.length; i++) {
            double tym = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > tym)
                nums[j + 1] = nums[j--];
            nums[j + 1] = tym;
        }
    }
}
