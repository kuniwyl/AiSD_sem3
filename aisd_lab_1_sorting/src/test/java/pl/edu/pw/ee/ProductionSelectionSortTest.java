package pl.edu.pw.ee;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class ProductionSelectionSortTest {

    private Sorting sorting;

    @Before
    public void setUp() {
        sorting = new SelectionSort();
        System.out.println("SelectionSort");
    }

    private int[] tests = { 100, 250, 500, 750, 1000, 2000, 3000, 4000, 5000, 7000, 9000, 12000, 15000, 17000, 20000,
            30000, 40000, 50000, 75000, 100000, 120000, 140000, 160000, 180000, 200000, 300000 };

    private double[] getArray_from_0_to_index(int index) {
        double[] res = new double[index];
        for (int i = 0; i < index; i++) {
            res[i] = (double) i;
        }
        return res;
    }

    private double[] getArray_from_index_to_0(int index) {
        double[] res = new double[index];
        for (int i = 0; i < index; i++) {
            res[index - i - 1] = (double) i;
        }
        return res;
    }

    private double[] getArray_random(int index) {
        double[] res = new double[index];
        Random rand = new Random(311609);
        for (int i = 0; i < index; i++) {
            res[i] = rand.nextDouble() * 6000000;
        }
        return res;
    }

    @Test
    public void getPesymistycData() {
        System.out.println("pesymistyczne");
        for (int test : tests) {
            double[] actuals = getArray_from_index_to_0(test);
            long start = System.currentTimeMillis();
            sorting.sort(actuals);
            long end = System.currentTimeMillis();
            System.out.println(test + " " + (end - start));
        }
        System.out.println();
    }

    @Test
    public void getOptymisticData() {
        System.out.println("optymistyczne");
        for (int test : tests) {
            double[] actuals = getArray_from_0_to_index(test);
            long start = System.currentTimeMillis();
            sorting.sort(actuals);
            long end = System.currentTimeMillis();
            System.out.println(test + " " + (end - start));
        }
        System.out.println();
    }

    @Test
    public void getRandomData() {
        System.out.println("random");
        for (int test : tests) {
            double[] actuals = getArray_random(test);
            long start = System.currentTimeMillis();
            sorting.sort(actuals);
            long end = System.currentTimeMillis();
            System.out.println(test + " " + (end - start));
        }
        System.out.println();
    }

}
