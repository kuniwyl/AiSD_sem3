package pl.edu.pw.ee;

import static pl.edu.pw.ee.Color.BLACK;
import static pl.edu.pw.ee.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {

    private Node<K, V> root;

    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    private String pre_order_rek(Node<K, V> node) {
        String ans = "";
        if (node == null) {
            return "";
        }
        ans = ans + node.getKey() + ":" + node.getValue() + " ";
        ans = ans + pre_order_rek(node.getLeft());
        ans = ans + pre_order_rek(node.getRight());
        return ans;
    }

    public String pre_order() {
        String ans = pre_order_rek(root);
        if (ans.length() == 0) {
            return ans;
        } else {
            return ans.substring(0, ans.length() - 1);
        }
    }

    private String in_order_rek(Node<K, V> node) {
        String ans = "";
        if (node == null) {
            return "";
        }
        ans = ans + in_order_rek(node.getLeft());
        ans = ans + node.getKey() + ":" + node.getValue() + " ";
        ans = ans + in_order_rek(node.getRight());
        return ans;
    }

    public String in_order() {
        String ans = in_order_rek(root);
        if (ans.length() == 0) {
            return ans;
        } else {
            return ans.substring(0, ans.length() - 1);
        }
    }

    private String post_order_rek(Node<K, V> node) {
        String ans = "";
        if (node == null) {
            return "";
        }
        ans = ans + post_order_rek(node.getLeft());
        ans = ans + post_order_rek(node.getRight());
        ans = ans + node.getKey() + ":" + node.getValue() + " ";
        return ans;
    }

    public String post_order() {
        String ans = post_order_rek(root);
        if (ans.length() == 0) {
            return ans;
        } else {
            return ans.substring(0, ans.length() - 1);
        }
    }

    public V get(K key) {
        validateKey(key);
        Node<K, V> node = root;

        V result = null;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                result = node.getValue();
                break;
            }
        }
        return result;
    }

    public void deleteMax() {
        validateRoot();

        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
            return;
        } else if (root.getRight() == null) {
            root = root.getLeft();
            return;
        } else if (root.getLeft().getColor().equals(RED)) {
            rotateRight(root);
        }

        root.setRight(deleteMaxRek(root.getRight()));

    }

    private Node<K, V> deleteMaxRek(Node<K, V> node) {
        if (node.getRight() == null) {
            return null;
        } else if (node.getRight() != null && node.getRight().getLeft() != null && !node.getRight().equals(RED)
                && !node.getRight().getLeft().equals(RED)) {
            changeColorsReverse(node);
            if (node.getLeft() != null && node.getLeft().getLeft() != null && node.getLeft().getLeft().equals(RED)
                    && node.getLeft().equals(RED)) {
                rotateRight(node);
            }
        }
        node.setRight(deleteMaxRek(node.getRight()));
        if (node.getRight() != null && node.getRight().getColor().equals(RED)) {
            rotateLeft(node);
        } else if (node.getLeft() != null && node.getLeft().getLeft() != null
                && node.getLeft().getLeft().getColor().equals(RED) && node.getLeft().getColor().equals(RED)) {
            rotateRight(node);
        } else if (node.getLeft() != null && node.getRight() != null && node.getLeft().getColor().equals(RED)
                && node.getRight().getColor().equals(RED)) {
            changeColors(node);
        }
        return node;
    }

    private void validateRoot() {
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }
    }

    public void put(K key, V value) {
        validateParams(key, value);
        root = put(root, key, value);
        root.setColor(BLACK);
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private boolean shouldCheckOnTheLeft(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateParams(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        counter++;

        if (node == null) {
            return new Node(key, value);
        }

        if (isKeyBiggerThanNode(key, node)) {
            putOnTheRight(node, key, value);

        } else if (isKeySmallerThanNode(key, node)) {
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        node = reorganizeTree(node);

        return node;
    }

    private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(Node<K, V> node, K key, V value) {
        Node<K, V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(Node<K, V> node, K key, V value) {
        Node<K, V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private Node<K, V> reorganizeTree(Node<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private void changeColorsIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Node<K, V> node) {
        node.setColor(RED);
        node.getLeft().setColor(BLACK);
        node.getRight().setColor(BLACK);
    }

    private void changeColorsReverse(Node<K, V> node) {
        node.setColor(node.getColor().equals(BLACK) ? RED : BLACK);
        node.getLeft().setColor(node.getLeft().getColor().equals(BLACK) ? RED : BLACK);
        node.getRight().setColor(node.getLeft().getColor().equals(BLACK) ? RED : BLACK);
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<K, V> node) {
        return node == null
                ? false
                : node.isRed();
    }
}
