package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LongestCommonSubsequenceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnExceeptionWhenStringInNull(){
        LongestCommonSubsequence t = new LongestCommonSubsequence(null, "tar");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnExceeptionWhenStringInNullSecond(){
        LongestCommonSubsequence t = new LongestCommonSubsequence("tar", null);
    }

    @Test
    public void shouldReturnTrueToFindLongestLCS_NoLCS(){
        LongestCommonSubsequence t = new LongestCommonSubsequence("abcd", "");
        String expected = "";
        String actual = t.findLCS();
        assertEquals(expected, actual); 
    }

    @Test
    public void shouldReturnTrueToFindLongestLCS_StringAreEmpty(){
        LongestCommonSubsequence t = new LongestCommonSubsequence("", "");
        String expected = "";
        String actual = t.findLCS();
        assertEquals(expected, actual); 
    }

    @Test
    public void shouldReturnTrueToFindLongestLCS_LCSEqualOneOfString(){
        LongestCommonSubsequence t = new LongestCommonSubsequence("abcd", "abc");
        String expected = "abc";
        String actual = t.findLCS();
        assertEquals(expected, actual); 
    }

    @Test
    public void shouldReturnTrueToFindLongestLCS_FirstTest(){
        LongestCommonSubsequence t = new LongestCommonSubsequence("asftjsyjseadrhsthge", "abcde");
        String expected = "ade";
        String actual = t.findLCS();
        assertEquals(expected, actual); 
    }
    
    @Test
    public void shouldReturnTrueToFindLongestLCS_SecondTest(){
        LongestCommonSubsequence t = new LongestCommonSubsequence("piłem", "piłowałem");
        String expected = "piłem";
        String actual = t.findLCS();
        assertEquals(expected, actual); 
    }

    @Test
    public void shouldReturnTrueToFindLongestLCS_ThirdTest(){
        LongestCommonSubsequence t = new LongestCommonSubsequence("abraKadabra", "AlaMaKota");
        String expected = "aaKa";
        String actual = t.findLCS();
        assertEquals(expected, actual); 
    }

    @Test
    public void shouldReturnTrue_LettersWithSlash(){
        LongestCommonSubsequence t = new LongestCommonSubsequence("\t\b\n\b", "tbnb");
        String expected = "";
        String actual = t.findLCS();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrue_DisplayTestWithSlash(){
        LongestCommonSubsequence t = new LongestCommonSubsequence("\b\t\n\f", "\'\"\\\r");
        t.display();
        String expected =
        "+-------+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |\n" +
        "|       |     | \\b  | \\t  | \\n  | \\f  |\n" +
        "|       |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |\n" +
        "|       |  0  |  0  |  0  |  0  |  0  |\n" +
        "|       |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |\n" +
        "|  \\\'   |  0  |  0  |  0  |  0  |  0  |\n" +
        "|       |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |\n" +
        "|  \\\"   |  0  |  0  |  0  |  0  |  0  |\n" +
        "|       |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |\n" +
        "|  \\\\   |  0  |  0  |  0  |  0  |  0  |\n" +
        "|       |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |\n" +
        "|  \\r   |  0  |  0  |  0  |  0  |  0  |\n" +
        "|       |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+\n";
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnTrue_DisplayTestWithEmptyStrings(){
        LongestCommonSubsequence t = new LongestCommonSubsequence("", "");
        t.display();
        String expected =
        "+-------+-----+\n" +
        "|       |     |\n" +
        "|       |     |\n" + 
        "|       |     |\n" +
        "+-------+-----+\n" + 
        "|       |     |\n" +
        "|       |  0  |\n" +
        "|       |     |\n" +
        "+-------+-----+\n";
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnTrue_DisplayTestWithSimilarStrings(){
        LongestCommonSubsequence t = new LongestCommonSubsequence("absd", "abs");
        t.display();
        String expected =
        "+-------+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |\n" + 
        "|       |     |  a  |  b  |  s  |  d  |\n" +
        "|       |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |\n" +
        "|       |  0  |  0  |  0  |  0  |  0  |\n" +
        "|       |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+\n" +
        "|       |     |\\    |     |     |     |\n" +
        "|   a   |  0  |  1  |  1  |  1  |  1  |\n" +
        "|       |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |\\    |     |     |\n" +
        "|   b   |  0  |  1  |  2  |  2  |  2  |\n" +
        "|       |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |\\    |     |\n" +
        "|   s   |  0  |  1  |  2  |  3  |< 3  |\n" +
        "|       |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+\n";
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnTrue_DisplayTestWithDiferentStrings(){
        LongestCommonSubsequence t = new LongestCommonSubsequence("HarryPotter", "Okulary");
        t.display();
        String expected = 
        "+-------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "|       |     |  H  |  a  |  r  |  r  |  y  |  P  |  o  |  t  |  t  |  e  |  r  |\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "|       |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "|   O   |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "|   k   |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "|   u   |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "|   l   |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |\\    |     |     |     |     |     |     |     |     |     |\n" +
        "|   a   |  0  |  0  |  1  |< 1  |  1  |  1  |  1  |  1  |  1  |  1  |  1  |  1  |\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |\\    |     |     |     |     |     |     |     |\n" +
        "|   r   |  0  |  0  |  1  |  2  |  2  |  2  |  2  |  2  |  2  |  2  |  2  |  2  |\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+\n" +
        "|       |     |     |     |     |     |\\    |     |     |     |     |     |     |\n" +
        "|   y   |  0  |  0  |  1  |  2  |  2  |  3  |< 3  |< 3  |< 3  |< 3  |< 3  |< 3  |\n" +
        "|       |     |     |     |     |     |     |     |     |     |     |     |     |\n" +
        "+-------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+\n";
        assertEquals(expected, outContent.toString());
    }
}
