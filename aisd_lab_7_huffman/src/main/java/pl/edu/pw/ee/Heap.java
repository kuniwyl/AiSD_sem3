package pl.edu.pw.ee;

public class Heap {

    private Element[] tree = new Element[1000];
    private int n = 0;

    public int getN() {
        return n;
    }

    public void put(Element e) {
        if (n == tree.length) {
            Element[] newTree = new Element[n * 2];
            System.arraycopy(tree, 0, newTree, 0, n);
            tree = newTree;
        }
        tree[n++] = e;
        heapUp();
    }

    public Element pop() {
        if (n == 0) {
            return null;
        }
        Element tmp = tree[0];
        tree[0] = tree[--n];
        heapDown();
        return tmp;
    }

    private void heapUp() {
        int i = n - 1;
        while (i > 0) {
            int r = (i - 1) / 2;
            if (tree[r].compareTo(tree[i]) <= 0) {
                return;
            }
            Element tmp = tree[r];
            tree[r] = tree[i];
            tree[i] = tmp;
            i = r;
        }
    }

    private void heapDown() {
        int i = 0;
        int d = 2 * i + 1;
        while (d < n) {
            if (d + 1 < n && tree[d + 1].compareTo(tree[d]) < 0) {
                d++;
            }
            if (tree[i].compareTo(tree[d]) <= 0) {
                return;
            }
            Element tmp = tree[i];
            tree[i] = tree[d];
            tree[d] = tmp;
            i = d;
            d = 2 * i + 1;
        }
    }

}
