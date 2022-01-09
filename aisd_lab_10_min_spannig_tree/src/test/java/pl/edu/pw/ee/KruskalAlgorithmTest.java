package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class KruskalAlgorithmTest extends TestingClassTest {
    
    @Before
    public void setUp(){
        t = new KruskalAlgorithm();
    }

    @Test
    public void shouldReturnTrue_ExampleShort(){
        String actual = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\short.txt");
        String expected = "A_1_B|B_2_C|D_7_A";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrue_ExampleLong(){
        String actual = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\long.txt");
        String expected = "A_1_B|B_1_G|B_2_C|A_6_Y|D_7_A|R_10_Y|E_12_G";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrue_LastData(){
        String actual = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\last_data.txt");
        String expected = "F_1_A|A_2_G|Z_3_G|L_4_Z|D_5_G|H_6_D|D_7_B";
        assertEquals(expected, actual);
    }

}
