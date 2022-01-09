package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PrimAlgorithmTest extends TestingClassTest{

    @Before
    public void setUp() {
        t = new PrimAlgorithm();
    }

    @Test
    public void shouldReturnTrue_ExampleShort(){
        String actual = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\short.txt");
        String expected = "A_1_B|A_2_C|A_7_D";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrue_ExampleLong(){
        String actual = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\long.txt");
        String expected = "A_1_B|B_1_G|B_2_C|A_6_Y|A_7_D|Y_10_R|G_12_E";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrue_LastData(){
        String actual = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\last_data.txt");
        String expected = "F_1_A|A_2_G|G_3_Z|Z_4_L|G_5_D|D_6_H|D_7_B";
        assertEquals(expected, actual);
    }

}
