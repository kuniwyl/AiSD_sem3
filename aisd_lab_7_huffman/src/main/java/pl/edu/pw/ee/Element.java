package pl.edu.pw.ee;

public class Element implements Comparable<Element> {

    private int key;
    private int value;
    private Element left;
    private Element right;

    Element(int value) {
        this.value = value;
    }

    Element(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public void setLeft(Element left) {
        this.left = left;
    }

    public void setRight(Element right) {
        this.right = right;
    }

    public Element getLeft() {
        return left;
    }

    public boolean hasLeft() {
        if (getLeft() != null) {
            return true;
        } else {
            return false;
        }
    }

    public Element getRight() {
        return right;
    }

    public boolean hasRight() {
        if (getRight() != null) {
            return true;
        } else {
            return false;
        }
    }

    public int getKey() {
        return key;
    }

    public boolean hasKey() {
        if (key > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getKey() + ":" + getValue();
    }

    @Override
    public int compareTo(Element o) {
        return Integer.compare(getValue(), o.getValue());
    }
}
