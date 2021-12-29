package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ChristmasBonusTest {

    ChristmasBonus ch;

    @Before
    public void setUp() {
        ch = new ChristmasBonus();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_whenScheduleIsNull() {
        int[] tab = null;
        int actual = ch.findMaxLength(tab, 4);
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_whenScheduleIsToBig() {
        int[] tab = new int[100000];
        int actual = ch.findMaxLength(tab, 2);
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_whenScheduleValueAreProhibitedLessThan1() {
        int[] tab = { 1, 2, 4, 7, 10, 123, 0, 123 };
        int actual = ch.findMaxLength(tab, 2);
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_whenScheduleValueAreProhibitedHigherThan99999() {
        int[] tab = { 1, 2, 4, 7, 10, 123, 100000, 123 };
        int actual = ch.findMaxLength(tab, 2);
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_whenChangesAreLessThen0() {
        int[] tab = new int[10];
        int actual = ch.findMaxLength(tab, -1);
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_whenChangesAreMoreThen99999() {
        int[] tab = new int[10];
        int actual = ch.findMaxLength(tab, 100000);
        assert false;
    }

    @Test
    public void shouldReturnTrue_Example1FromTast() {
        int[] tab = { 2, 2, 4, 5, 4, 4, 5 };
        int actual = ch.findMaxLength(tab, 2);
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrue_Example2FromTast() {
        int[] tab = { 5, 1, 1, 5, 3, 3, 5 };
        int actual = ch.findMaxLength(tab, 0);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrue_Example3FromTast() {
        int[] tab = { 3, 5, 5, 5, 2 };
        int actual = ch.findMaxLength(tab, 2);
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void sholudReturnTrue_AllNumbersAreDifferentChangesEqual0() {
        int tab[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int actual = ch.findMaxLength(tab, 0);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void sholudReturnTrue_AllNumbersAreDifferentChangesEqualNot0() {
        int tab[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int actual = ch.findMaxLength(tab, 5);
        int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrue_OneNumberInSchedule() {
        int tab[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        int actual = ch.findMaxLength(tab, 4);
        int expected = 9;
        assertEquals(expected, actual);
    }

}
