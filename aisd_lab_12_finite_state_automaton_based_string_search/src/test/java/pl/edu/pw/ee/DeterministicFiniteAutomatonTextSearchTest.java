package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import pl.edu.pw.ee.services.PatternSearch;

public class DeterministicFiniteAutomatonTextSearchTest {

    private PatternSearch search;

    public void setUp(String pattern) {
        search = new DeterministicFiniteAutomatonTextSearch(pattern);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_patterIsNull() {
        setUp(null);
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_patterIsEmpty() {
        setUp("");
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_TextIsNull_findFirst() {
        setUp("AVC");
        search.findFirst(null);
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_TextIsNull_findAll() {
        setUp("AVC");
        search.findAll(null);
        assert true;
    }

    @Test
    public void shouldReturnTrue_NoPatternInText() {
        setUp("OOO");
        String text = "ABCDEGGSTYAOOTJN";
        int expected = -1;
        int[] expected_list = new int[0];
        int actual = search.findFirst(text);
        int[] actual_list = search.findAll(text);
        isCorrect(expected, actual, expected_list, actual_list);
    }

    @Test
    public void shouldReturnTrue_PatternOnceInText() {
        setUp("MA");
        String text = "ALAMAKOTA";
        int expected = 3;
        int[] expected_list = { 3 };
        int actual = search.findFirst(text);
        int[] actual_list = search.findAll(text);
        isCorrect(expected, actual, expected_list, actual_list);
    }

    @Test
    public void shouldReturnTrue_TwoTimePatterInText_NotInvade() {
        setUp("BCDC");
        String text = "EEEABCDCBHBCDC";
        int actual = search.findFirst(text);
        int[] actual_list = search.findAll(text);
        int expected = 4;
        int[] expected_list = { 4, 10 };
        isCorrect(expected, actual, expected_list, actual_list);
    }

    @Test
    public void shouldReturnTrue_TwoTimePatterInText_Invade() {
        setUp("BACABA");
        String text = "ARBACABACABART";
        int actual = search.findFirst(text);
        int[] actual_list = search.findAll(text);
        int expected = 2;
        int[] expected_list = { 2, 6 };
        isCorrect(expected, actual, expected_list, actual_list);
    }

    @Test
    public void shouldReturnException_patterInTextManyTimes_NotInvade() {
        setUp("AAA");
        String text = "AAACAAADAAARAAA";
        int expected = 0;
        int[] expected_list = { 0, 4, 8, 12 };
        int actual = search.findFirst(text);
        int[] actual_list = search.findAll(text);
        isCorrect(expected, actual, expected_list, actual_list);
    }

    @Test
    public void shouldReturnException_patterInTextManyTimes_Invade() {
        setUp("AAA");
        String text = "AAAAAAA";
        int expected = 0;
        int[] expected_list = { 0, 1, 2, 3, 4 };
        int actual = search.findFirst(text);
        int[] actual_list = search.findAll(text);
        isCorrect(expected, actual, expected_list, actual_list);
    }

    @Test
    public void shouldReturnException_patterLongerThanText() {
        setUp("ABCDEFG");
        String text = "ABCDEF";
        int expected = -1;
        int[] expected_list = {};
        int actual = search.findFirst(text);
        int[] actual_list = search.findAll(text);
        isCorrect(expected, actual, expected_list, actual_list);
    }

    @Test
    public void shouldReturnException_textIsEmpty() {
        setUp("ABCDEFG");
        String text = "";
        int expected = -1;
        int[] expected_list = {};
        int actual = search.findFirst(text);
        int[] actual_list = search.findAll(text);
        isCorrect(expected, actual, expected_list, actual_list);
    }

    private void isCorrect(int expected, int actual, int[] expected_list, int[] actual_list) {
        assertEquals(expected, actual);
        assertEquals(expected_list.length, actual_list.length);
        for (int i = 0; i < actual_list.length; i++) {
            assertEquals(expected_list[i], actual_list[i]);
        }
    }
}