package pl.edu.pw.ee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithmTest {
    
    // private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    // private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    // private final PrintStream originalOut = System.out;
    // private final PrintStream originalErr = System.err;

    // @Before
    // public void setUpStreams() {
    //     System.setOut(new PrintStream(outContent));
    //     System.setErr(new PrintStream(errContent));
    // }

    // @After
    // public void restoreStreams() {
    //     System.setOut(originalOut);
    //     System.setErr(originalErr);
    // }

    @Test
    public void test(){
        MinSpanningTree t = new PrimAlgorithm();
        t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\small_data.txt");
    }   
}
