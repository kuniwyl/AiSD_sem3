package pl.edu.pw.ee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class ProductionQuadraticTest extends ProductionTesting {

    @Override
    void createHash(int size) {
        return;
    }

    @Override
    String getFile() {
        return "src\\test\\java\\pl\\edu\\pw\\ee\\resourse\\resultsQua.txt";
    };

    private double[] a = { 1.0, 1.3, 1.6, 1.9, 2.2, 2.5, 2.8, 3.1, 3.4, 3.7 };
    private double[] b = { 2.0, 2.4, 2.8, 3.2, 3.6, 4.0, 4.4, 4.8, 5.2, 5.6 };

    private void getToFile(long[] input, long[] get, int title, double a, double b) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(fileOutputName, true));
            writer.append(title + " " + a + " " + b + "\n");
            for (int i = 0; i < 30; i++) {
                writer.append(input[i] + "\t");
            }
            writer.append("\n");
            for (int i = 0; i < 30; i++) {
                writer.append(get[i] + "\t");
            }
            writer.append("\n");
            writer.append("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Production() {

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {

                long[] input = new long[30];
                long[] get = new long[30];

                for (int j = 0; j < 30; j++) {

                    hashTable = new HashQuadraticProbing<>(sizes[x], a[y], b[y]);

                    long inputStart = System.currentTimeMillis();
                    for (int i = 0; i < 100000; i++) {
                        hashTable.put(strs[i]);
                    }
                    long inputEnd = System.currentTimeMillis();

                    long getStart = System.currentTimeMillis();
                    for (int i = 0; i < 100000; i++) {
                        hashTable.get(strs[i]);
                    }
                    long getEnd = System.currentTimeMillis();

                    input[j] = inputEnd - inputStart;
                    get[j] = getEnd - getStart;
                }
                Arrays.sort(input);
                Arrays.sort(get);
                getToFile(input, get, sizes[x], a[y], b[y]);
            }
        }
    }
}
