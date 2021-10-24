package pl.edu.pw.ee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class ProductionHeapSortTest {

    private Sorting sorting;

    @BeforeClass
    public static void set() {
        addFileTitle("HeapSort", 1);
    }

    @Before
    public void setUp() {
        sorting = new HeapSort();
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
        for (int i = 0; i < index / 2; i++) {
            res[index - i - 1] = (double) i;
        }
        for (int i = index / 2; i < index; i++) {
            res[index - i - 1] = (double) i;
        }
        return res;
    }

    private double[] getArray_random(int index) {
        double[] res = new double[index];
        Random rand = new Random(311609);
        for (int i = 0; i < index; i++) {
            res[i] = rand.nextDouble() * index;
        }
        return res;
    }

    private static void addFileTitle(String test, int mode) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\proHeap.txt", mode == 1 ? false : true));
            writer.append(test + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addResultToFile(int test, long end, long start) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\proHeap.txt", true));
            writer.append(test + " \t" + (end - start) + "\n");
            // writer.append((end - start) + "\t");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Stworzenie ciągu który jest pesymistyczne teoretyczne jest możliwe jednak
     * stworzenie algorytmu który takowy ciąg by stworzył jest dość trudne przy
     * wejściu 1, 2, 3, 4, 5, ..., n liczba wykonanych operacji wydaje się
     * największa ponieważ wprowadzenie tych danych wymaga n operacji przesuwania
     * elementu z góry na dół
     */
    @Test
    public void getPesymistycData() {
        addFileTitle("pesymistyczne", 0);
        // for (int j = 0; j < 10; j++) {
        for (int i = 0; i < tests.length; i++) {
            double[] actuals = getArray_from_0_to_index(tests[i]);
            long start = System.currentTimeMillis();
            sorting.sort(actuals);
            long end = System.currentTimeMillis();
            addResultToFile(tests[i], end, start);
            // }
            // addFileTitle("", 0);
        }
    }

    @Test
    public void getOptymisticData() {
        addFileTitle("optymistyczne", 0);
        // for (int j = 0; j < 10; j++) {
        for (int i = 0; i < tests.length; i++) {
            double[] actuals = getArray_from_index_to_0(tests[i]);
            long start = System.currentTimeMillis();
            sorting.sort(actuals);
            long end = System.currentTimeMillis();
            addResultToFile(tests[i], end, start);
            // }
            // addFileTitle("", 0);
        }
    }

    @Test
    public void getRandomData() {
        addFileTitle("Random", 0);
        // for (int j = 0; j < 10; j++) {
        for (int i = 0; i < tests.length; i++) {
            double[] actuals = getArray_random(tests[i]);
            long start = System.currentTimeMillis();
            sorting.sort(actuals);
            long end = System.currentTimeMillis();
            addResultToFile(tests[i], end, start);
            // }
            // addFileTitle("", 0);
        }
    }
}
