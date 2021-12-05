package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ProductionTest {

    private boolean isOverWrited = false;
    private String fileOutputName = "answers\\answer.txt";
    private int[] answers = new int[1000000];
    private String[] strs = new String[1000000];
    private String[] cats = { "cats1", "cats2", "cats3", "cats4", "cats5", "cats6", "cats7", "cats8", "cats9",
            "cats10" };

    @Before
    public void setUp() {
        if (!isOverWrited) {
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(fileOutputName, false));
                writer.append("");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            isOverWrited = true;
        }

        BufferedReader reader;
        try {
            int n = 0;
            for (int i = 0; i < 10; i++) {
                reader = new BufferedReader(new FileReader("resource\\words.txt"));
                String a = reader.readLine();

                while (a != null) {
                    strs[n++] = a + cats[i];
                    a = reader.readLine();
                }
                reader.close();
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    private void getToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(fileOutputName, true));
            for (int i = 0; i < answers.length; i++) {
                if (i < 1000) {
                    if ((i + 1) % 5 == 0 || i < 100) {
                        writer.append((i + 1) + "\t" + answers[i] + "\n");
                    }
                } else {
                    if ((i + 1) % 200 == 0) {
                        writer.append((i + 1) + "\t" + answers[i] + "\n");
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Production() {
        RedBlackTree<String, String> tree = new RedBlackTree<String, String>();
        int before;
        int after;
        for (int i = 0; i < strs.length; i++) {
            before = tree.getCounter();
            tree.put(strs[i], strs[i]);
            after = tree.getCounter();
            answers[i] = after - before;
        }
        getToFile();
    }
}
