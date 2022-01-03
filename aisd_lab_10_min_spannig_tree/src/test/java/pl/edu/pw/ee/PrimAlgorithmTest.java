package pl.edu.pw.ee;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;

import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithmTest {

    private MinSpanningTree t;

    @Before
    public void setUp() {
        t = new PrimAlgorithm();
    }

    @Test(expected = RuntimeException.class)
    public void shouldReturnException_RuntimeException_FileNameIsNull() {
        String a = t.findMST(null);
        assert false;
    }

    // do poprawy
    @Test(expected = RuntimeException.class)
    public void shouldReturnException_FileNotFoundException_FileNameNotExist() {
        String a = t.findMST("temporary.txt");
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_IllegalArgumentException_FileContainBannedValued() {
        String a = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\bad_data.txt");
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_IllegalArgumentException_FileContainNotConnectedGraph() {
        String a = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\not_connected.txt");
        assert false;
    }

    // @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_IllegalArgumentException_FileIsEmpty() {
        System.out.println("empty");
        String a = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\empty.txt");
        assert false;
    }

    @Test
    public void shouldReturnTrue_ExampleFromISOD() {
        String actual = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\small_data.txt");
        String expected = "A_3_B|B_1_C|C_1_D|D_7_E";
        assertEquals(expected, actual);
    }

}
