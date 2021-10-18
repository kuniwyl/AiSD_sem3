package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HeapInterface;
import pl.edu.pw.ee.services.Sorting;

public class HeapSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        heapSort(nums);
    }

    private void heapSort(double[] nums) {
        HeapInterface<Double> heap = new Heap();

        for (int i = 0; i < nums.length; i++) {
            heap.put(nums[i]);
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            nums[i] = heap.pop();
        }
    }
}
