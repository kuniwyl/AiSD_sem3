package pl.edu.pw.ee;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    HashDoubleHashing() {
        super();
    }

    HashDoubleHashing(int size) {
        super(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (int) ((key % m + i * (1 + (key % (m - 3)))) % m);

        hash = hash < 0 ? -hash : hash;

        return hash;
    }

    private double f_k(double k) {
        return k % getSize();
    }

    private double g_k(double k) {
        return 1 + (k % (getSize() - 3));
    }

}
