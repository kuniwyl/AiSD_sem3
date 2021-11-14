package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class ProductionDoubleHashingTest {

    private HashTable<String> hashTable;
    private String[] strs = new String[100000];
    private int[] sizes = { 512, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144 };
    // 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536,

    @BeforeClass
    public static void set() {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\resourse\\resultsDouble.txt", false));
            writer.append("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("src\\test\\java\\pl\\edu\\pw\\ee\\resourse\\words.txt"));
            String a = reader.readLine();
            int n = 0;

            while (a != null) {
                strs[n++] = a;
                a = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    private void getToFile(long[] input, long[] get, int title) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\resourse\\resultsDouble.txt", true));
            writer.append(title + "\n");
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

    @Test
    public void test() {

        for (int x = 0; x < 9; x++) {
            long[] input = new long[30];
            long[] get = new long[30];
            for (int j = 0; j < 30; j++) {
                hashTable = new HashDoubleHashing<>(sizes[x]);
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
            getToFile(input, get, sizes[x]);
        }
    }
}
