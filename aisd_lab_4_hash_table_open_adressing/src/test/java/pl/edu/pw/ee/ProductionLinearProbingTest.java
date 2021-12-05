package pl.edu.pw.ee;

public class ProductionLinearProbingTest extends ProductionTesting {

    @Override
    void createHash(int size) {
        hashTable = new HashLinearProbing<String>(size);
    }

    @Override
    String getFile() {
        return "src\\test\\java\\pl\\edu\\pw\\ee\\resourse\\resultsLinear.txt";
    }
}
