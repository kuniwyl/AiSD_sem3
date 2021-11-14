package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    private double a;
    private double b;

    HashQuadraticProbing() {
        super();
        this.a = 1.1;
        this.b = 2.2;
    }

    HashQuadraticProbing(int size, double a, double b) {
        super(size);
        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (int) ((baseFunction_f_k(key) + this.a * i + this.b * i * i) % m);

        hash = hash < 0 ? -hash : hash;

        return hash;
    }
}
