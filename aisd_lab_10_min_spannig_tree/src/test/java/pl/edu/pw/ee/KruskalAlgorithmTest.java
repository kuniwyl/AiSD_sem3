package pl.edu.pw.ee;

import org.junit.Test;

import pl.edu.pw.ee.services.MinSpanningTree;

public class KruskalAlgorithmTest {
    
    @Test
    public void test(){
        MinSpanningTree t = new KruskalAlgorithm();
        System.out.println(t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\small_data.txt"));
    }

}
