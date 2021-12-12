package pl.edu.pw.ee;

import org.junit.Test;

public class HuffmanTest {

    @Test
    public void myTest() {
        Huffman huf = new Huffman();
        System.out.println(huf.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\resource\\myTest", true));
    }

    @Test
    public void test1() {
        Huffman huf = new Huffman();
        System.out.println(
                huf.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\resource\\test1", true));
    }
}
