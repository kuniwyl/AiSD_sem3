package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSortTest {

    private Sorting sorting;

    @Before
    public void setUp() {
        sorting = new SelectionSort();
    }

    @Test
    public void should_ReturnFalseValue_ArrayIsNull() {
        boolean value = true;
        double[] expecteds = null;

        try {
            sorting.sort(expecteds);
        } catch (IllegalArgumentException e) {
            value = false;
        }

        assertFalse(value);
    }

    @Test
    public void should_ReturnTrueValue_ArrayHasOneELement() {
        double[] expecteds = { 1 };
        double[] actuals = { 1 };

        sorting.sort(actuals);

        assertArrayEquals(expecteds, actuals, 0);
    }

    @Test
    public void should_ReturnTrueValue_ArrayHasTwoElements_ElementsAreSetGrowlingly() {
        double[] expecteds = { 3, 9 };
        double[] actuals = { 3, 9 };

        sorting.sort(actuals);

        assertArrayEquals(expecteds, actuals, 0);
    }

    @Test
    public void should_ReturnTrueValue_ArrayHasTwoELements_ElementsAreSetDesending() {
        double[] expecteds = { 2, 11 };
        double[] actuals = { 11, 2 };

        sorting.sort(actuals);

        assertArrayEquals(expecteds, actuals, 0);
    }

    @Test
    public void should_ReturnTrueValue_ElementSetOptimistic_AlreadySorted() {
        double[] expecteds = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        double[] actuals = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        sorting.sort(actuals);

        assertArrayEquals(expecteds, actuals, 0);
    }

    @Test
    public void should_ReturnTrueValue_ElementSetOptimistic_TwoElementsNeedToBeSort() {
        double[] expecteds = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        double[] actuals = { 1, 2, 3, 5, 4, 6, 7, 8, 9 };

        sorting.sort(actuals);

        assertArrayEquals(expecteds, actuals, 0);
    }

    @Test
    public void should_ReturnTrueValue_ElementSetPessimistic() {
        double[] actuals = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        double[] expecteds = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        sorting.sort(actuals);

        assertArrayEquals(expecteds, actuals, 0);
    }

    @Test
    public void should_ReturnTrue_ElementsAreNot() {
        double[] actuals = { 5, 3, 4, 7, 8, 1, 3 };
        double[] expecteds = { 1, 3, 3, 4, 5, 7, 8 };

        sorting.sort(actuals);

        assertArrayEquals(expecteds, actuals, 0);
    }

    @Test
    public void should_ReturnTrueValue_ArrayHasManyElements_ElementsAreChooseRandomly() {
        int SIZE_OF_TABLE = 400000;

        Random rand = new Random(311609);
        double[] actuals = new double[SIZE_OF_TABLE];
        for (int i = 0; i < SIZE_OF_TABLE; i++) {
            actuals[i] = rand.nextDouble();
        }

        sorting.sort(actuals);

        for (int i = 1; i < SIZE_OF_TABLE; i++) {
            if (actuals[i - 1] > actuals[i]) {
                assert false;
            }
        }

    }
}
