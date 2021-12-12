package pl.edu.pw.ee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Huffman {

    public int[] charCounter = new int[256];
    private Element head;

    public Element getHead() {
        return head;
    }

    public ArrayList<Dictonary> getDict() {
        return dict;
    }

    private String decompression;
    private String toHeap;
    private ArrayList<Dictonary> dict = new ArrayList<Dictonary>();
    private ArrayList<Integer> before = new ArrayList<Integer>();

    public int huffman(String pathToRootDir, boolean compress) {

        File folder = new File(pathToRootDir);
        if (compress) {
            readFile(pathToRootDir + "\\inputCompression.txt");
            createHuffmanTree();
            createDictonary();
            int unUsedBits = createCompresed(pathToRootDir);
            createDictFile(pathToRootDir, unUsedBits);
            File file = new File(pathToRootDir + "\\" + "output.txt");
            return (int) file.length();

        } else {
            readFileDecomression(pathToRootDir + "\\inputDecompression.txt");
            readFileToHeap(pathToRootDir + "\\inputDict.txt");
            createHuffmanTreeDecompress();
        }
        return -1;
    }

    private void readFile(String filename) {
        try {
            FileInputStream input = new FileInputStream(filename);
            int c = input.read();
            while (c != -1) {
                charCounter[c]++;
                before.add(c);
                c = input.read();
            }
            input.close();
        } catch (IOException e) {
        }
    }

    private void readFileDecomression(String path){
        FileInputStream input;
        int c;
        byte one;
        String intToString;
        int length;
        try {
            input = new FileInputStream(path);
            c = input.read();
            long sizeOfFile = Files.size(Paths.get(path));
            while(c != -1){
                one = (byte) c;
                intToString = String.format("%8s", Integer.toBinaryString(one & 0xFF).replace(' ', '0'));
                //length = intToString.length() - (8 - unUsedBits);
                //if( j == )
                c = input.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFileToHeap(String path) {

    }

    private void createHuffmanTree() {
        Heap heap = new Heap();
        for (int i = 0; i < charCounter.length; i++) {
            if (charCounter[i] > 0) {
                heap.put(new Element(i, charCounter[i]));
            }
        }

        while (heap.getN() > 1) {
            Element first = heap.pop();
            Element second = heap.pop();
            int sum = first.getValue() + second.getValue();

            Element parent = new Element(sum);
            parent.setLeft(first);
            parent.setRight(second);
            heap.put(parent);
        }

        head = heap.pop();
    }

    private void createDictonary() {
        preOrder(head, "");
    }

    private void preOrder(Element head, String before) {
        String ans = before;
        if (head.hasKey()) {
            dict.add(new Dictonary(ans, head.getKey()));
        }
        if (head.hasLeft()) {
            preOrder(head.getLeft(), ans + 0);
        }
        if (head.hasRight()) {
            preOrder(head.getRight(), ans + 1);
        }
    }

    private int createCompresed(String pathToDir) {
        String end = "";
        for (int i = 0; i < before.size(); i++) {
            for (int j = 0; j < dict.size(); j++) {
                if (dict.get(j).getZnak() == before.get(i)) {
                    end += (String.format(dict.get(j).getCode()));

                }
            }
        }
        byte result;
        String t;
        int y = 0;
        try {
            FileOutputStream writer = new FileOutputStream(pathToDir + "\\" + "output.txt");
            for (int j = 0; j < end.length(); j += 8) {
                if (j + 8 > end.length()) {
                    y = j + 8 - end.length();
                    t = end.substring(j, end.length());
                    for (int i = 0; i < y; i++) {
                        t += "0";
                    }
                } else {
                    t = end.substring(j, j + 8);
                }
                result = (byte) Integer.parseInt(t, 2);
                writer.write(result);
            }
            writer.close();
        } catch (IOException e) {
        }
        return y;
    }

    private void createDictFile(String pathToDir, int unUsedBits) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathToDir + "\\" + "outputDict.txt"));
            writer.write(unUsedBits);
            // String a = preOrderCreatorOutPut(head);
            // for(int i = 0; i < a.length(); i++){
            //     writer.write(a.charAt(i));
            // }
            writer.close();
        } catch (IOException e) {
        }
    }

    private String preOrderCreatorOutPut(Element head) {
        String ans = "";
        if (head.hasKey()) {
            ans += String.valueOf(head.getValue()) + (char) head.getKey();
        } else {
            ans += 0;
        }
        if (head.hasLeft()) {
            ans += preOrderCreatorOutPut(head.getLeft());
        }
        if (head.hasRight()) {
            ans += preOrderCreatorOutPut(head.getRight());
        }
        return ans;
    }

    private void createHuffmanTreeDecompress() {
        Heap heap = new Heap();
        if (toHeap.charAt(0) == '0') {
            heap.put(new Element(0));
            preOrderDecompressoin(head, toHeap, 1);
        } else {
            heap.put(new Element(toHeap.charAt(1), toHeap.charAt(0)));
        }
    }

    private void preOrderDecompressoin(Element head, String input, int i) {
        if (input.charAt(i) == '0') {
            if (!head.hasLeft()) {
                head.setLeft(new Element(0));
                preOrderDecompressoin(head.getLeft(), input, i + 1);
            } else if (!head.hasRight()) {
                head.setRight(new Element(0));
                preOrderDecompressoin(head.getRight(), input, i + 1);
            }
        } else {
            if (!head.hasLeft()) {
                head.setLeft(new Element(input.charAt(i + 1), input.charAt(i)));
                preOrderDecompressoin(head.getLeft(), input, i + 2);
            } else if (!head.hasRight()) {
                head.setRight(new Element(input.charAt(i + 1), input.charAt(i)));
                preOrderDecompressoin(head.getRight(), input, i + 2);
            }
        }
    }

}
