package pl.edu.pw.ee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class ProductionQuickSortTest {

    private Sorting sorting;

    @BeforeClass
    public static void set() {
        addFileTitle("QuickSort", 1);
    }

    @Before
    public void setUp() {
        sorting = new QuickSort();
    }

    private int[] tests = { 100, 250, 500, 750, 1000, 2000, 3000, 4000, 5000, 7000, 9000, 12000, 15000, 17000, 20000,
            30000, 40000, 50000, 75000, 100000, 120000, 140000, 160000, 180000, 200000, 300000 };

    /*
     * Quick sort działa najwolniej gdy liczby są poukładane od 1 do n lub w
     * odwrotnej kolejności
     */
    private double[] getArray_from_index_to_0(int index) {
        double[] res = new double[index];
        for (int i = 0; i < index; i++) {
            res[index - i - 1] = i;
        }
        return res;
    }

    private double[] getArray_random(int index) {
        double[] res = new double[index];
        Random rand = new Random(311609);
        for (int i = 0; i < index; i++) {
            res[i] = rand.nextDouble() * 300000;
        }
        return res;
    }

    /*
     * Quick sort działa najszybcjie w przypadku gdy pivot będzie elementem
     * środkowym poniższy algorytm tworzy tablie w sposób rekurencyjny z elementami
     * poukładanymi w ten właśnie sposób
     */
    private double[] getOptymisticData(int index) {
        double[] res = new double[index];
        for (int i = 0; i < index; i++) {
            res[i] = i;
        }
        split(res, 0, index - 1);
        return res;
    }

    private void split(double[] res, int p, int k) {
        int m = p + (k - p) / 2; // ustala środek
        swap(res, m, p); // zamienia środek z początkiem
        if (p == m || k == m) { // kończy gdy środek jest równy początkowi lub końcowi
            return;
        }
        split(res, p + 1, m); // rozdziela na lewą
        split(res, m + 1, k); // rozdziela na prawą
        return; // kończy działanie
    }

    private void swap(double[] res, int i, int j) { // zwykły swap
        double temp = res[i];
        res[i] = res[j];
        res[j] = temp;
    }

    private static void addFileTitle(String test, int mode) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\proQuick.txt", mode == 1 ? false : true));
            writer.append(test + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addResultToFile(int test, long end, long start) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\proQuick.txt", true));
            writer.append(test + "\t" + (end - start) + "\n");
            // writer.append((end - start) + "\t");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPesymistycData() {
        addFileTitle("pesymistyczne", 0);
        for (int i = 0; i < tests.length; i++) {
            double[] actuals = getArray_from_index_to_0(tests[i]);
            long start = System.currentTimeMillis();
            sorting.sort(actuals);
            long end = System.currentTimeMillis();
            addResultToFile(tests[i], end, start);
        }
    }

    @Test
    public void getOptymisticData() {
        addFileTitle("optymistyczne", 0);
        // for (int j = 0; j < 30; j++) {
        for (int i = 0; i < tests.length; i++) {
            double[] actuals = getOptymisticData(tests[i]);
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
        // for (int j = 0; j < 20; j++) {
        for (int i = 0; i < tests.length; i++) {
            double[] actuals = getArray_random(tests[i]);
            long start = System.currentTimeMillis();
            sorting.sort(actuals);
            long end = System.currentTimeMillis();
            addResultToFile(tests[i], end, start);
        }
        // addFileTitle("", 0);
        // }
    }

}
