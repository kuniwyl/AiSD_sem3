package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashListProductionTest {

    private HashTable<String> hashTable4096;
    private HashTable<String> hashTable8192;
    private HashTable<String> hashTable16384;
    private HashTable<String> hashTable32768;
    private HashTable<String> hashTable65536;
    private HashTable<String> hashTable131072;
    private HashTable<String> hashTable262144;
    private String[] strs = new String[100000];

    @BeforeClass
    public static void set() {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\resourse\\results.txt", false));
            writer.append("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {
        hashTable4096 = new HashListChaining<String>(4096);
        hashTable8192 = new HashListChaining<String>(8192);
        hashTable16384 = new HashListChaining<String>(16384);
        hashTable32768 = new HashListChaining<String>(32768);
        hashTable65536 = new HashListChaining<String>(65536);
        hashTable131072 = new HashListChaining<String>(131072);
        hashTable262144 = new HashListChaining<String>(262144);

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

    private void getToFile(long[] table, String title) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\resourse\\results.txt", true));
            writer.append(title + "\t");
            for (int i = 0; i < 30; i++) {
                writer.append(table[i] + "\t");
            }
            writer.append("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4096() {
        for (int i = 0; i < strs.length; i++) {
            hashTable4096.add(strs[i]);
        }
        long table[] = new long[30];
        for (int j = 0; j < 30; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < strs.length; i++) {
                hashTable4096.get(strs[i]);
            }
            long end = System.currentTimeMillis();
            table[j] = (end - start);
        }
        Arrays.sort(table);
        getToFile(table, "4096");
    }

    @Test
    public void test8192() {
        for (int i = 0; i < strs.length; i++) {
            hashTable8192.add(strs[i]);
        }
        long table[] = new long[30];
        for (int j = 0; j < 30; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < strs.length; i++) {
                hashTable8192.get(strs[i]);
            }
            long end = System.currentTimeMillis();
            table[j] = (end - start);
        }
        Arrays.sort(table);
        getToFile(table, "8192");
    }

    @Test
    public void test16384() {
        for (int i = 0; i < strs.length; i++) {
            hashTable16384.add(strs[i]);
        }
        long table[] = new long[30];
        for (int j = 0; j < 30; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < strs.length; i++) {
                hashTable16384.get(strs[i]);
            }
            long end = System.currentTimeMillis();
            table[j] = (end - start);
        }
        Arrays.sort(table);
        getToFile(table, "16384");
    }

    @Test
    public void test32768() {
        for (int i = 0; i < strs.length; i++) {
            hashTable32768.add(strs[i]);
        }
        long table[] = new long[30];
        for (int j = 0; j < 30; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < strs.length; i++) {
                hashTable32768.get(strs[i]);
            }
            long end = System.currentTimeMillis();
            table[j] = (end - start);
        }
        Arrays.sort(table);
        getToFile(table, "32768");
    }

    @Test
    public void test65536() {
        for (int i = 0; i < strs.length; i++) {
            hashTable65536.add(strs[i]);
        }
        long table[] = new long[30];
        for (int j = 0; j < 30; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < strs.length; i++) {
                hashTable65536.get(strs[i]);
            }
            long end = System.currentTimeMillis();
            table[j] = (end - start);
        }
        Arrays.sort(table);
        getToFile(table, "65536");
    }

    @Test
    public void test131072() {
        for (int i = 0; i < strs.length; i++) {
            hashTable131072.add(strs[i]);
        }
        long table[] = new long[30];
        for (int j = 0; j < 30; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < strs.length; i++) {
                hashTable131072.get(strs[i]);
            }
            long end = System.currentTimeMillis();
            table[j] = (end - start);
        }
        Arrays.sort(table);
        getToFile(table, "131072");
    }

    @Test
    public void test262144() {
        for (int i = 0; i < strs.length; i++) {
            hashTable262144.add(strs[i]);
        }
        long table[] = new long[30];
        for (int j = 0; j < 30; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < strs.length; i++) {
                hashTable262144.get(strs[i]);
            }
            long end = System.currentTimeMillis();
            table[j] = (end - start);
        }
        Arrays.sort(table);
        getToFile(table, "262144");
    }
}
