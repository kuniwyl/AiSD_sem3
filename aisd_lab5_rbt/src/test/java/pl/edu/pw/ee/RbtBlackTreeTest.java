package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RbtBlackTreeTest {
    @Test
    public void shouldReturnCorrectPreOrder() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>();
        String expected = "4:4 2:2 1:1 3:3 8:8 6:6 5:5 7:7 9:9";
        tree.put(1, 1);
        tree.put(2, 2);
        tree.put(3, 3);
        tree.put(4, 4);
        tree.put(5, 5);
        tree.put(6, 6);
        tree.put(7, 7);
        tree.put(8, 8);
        tree.put(9, 9);
        String actual = tree.pre_order();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectInOrder() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>();
        String expected = "1:1 2:2 3:3 4:4 5:5 6:6 7:7 8:8 9:9";
        tree.put(1, 1);
        tree.put(2, 2);
        tree.put(3, 3);
        tree.put(4, 4);
        tree.put(5, 5);
        tree.put(6, 6);
        tree.put(7, 7);
        tree.put(8, 8);
        tree.put(9, 9);
        String actual = tree.in_order();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectPostOrder() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>();
        String expected = "1:1 3:3 2:2 5:5 7:7 6:6 9:9 8:8 4:4";
        tree.put(1, 1);
        tree.put(2, 2);
        tree.put(3, 3);
        tree.put(4, 4);
        tree.put(5, 5);
        tree.put(6, 6);
        tree.put(7, 7);
        tree.put(8, 8);
        tree.put(9, 9);
        String actual = tree.post_order();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectPerOrderAfterDeleteMax() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>();
        String expected = "4:4 2:2 1:1 3:3 8:8 6:6 5:5 7:7";
        tree.put(1, 1);
        tree.put(2, 2);
        tree.put(3, 3);
        tree.put(4, 4);
        tree.put(5, 5);
        tree.put(6, 6);
        tree.put(7, 7);
        tree.put(8, 8);
        tree.put(9, 9);
        tree.deleteMax();
        String actual = tree.pre_order();
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_WhenDeleteAndRootIsNull() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>();
        tree.deleteMax();
        assert false;
    }

    @Test
    public void shouldReturnOneElement_WhenDeleteWithTwoElements() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>();
        tree.put(1, 1);
        tree.put(2, 2);
        tree.deleteMax();
        assertEquals("1:1", tree.pre_order());
    }

    @Test
    public void shouldReturnTwoElements_WhenDeleteWithThreeElements() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>();
        tree.put(1, 1);
        tree.put(2, 2);
        tree.put(3, 3);
        tree.deleteMax();
        assertEquals("2:2 1:1", tree.pre_order());
    }

    @Test
    public void checkIfRootWillBeNullAfterDeleteWithOneElement() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>();
        tree.put(1, 1);
        tree.deleteMax();
        assertEquals(tree.pre_order(), "");
    }

}
