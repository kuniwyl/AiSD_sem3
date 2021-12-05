package pl.edu.pw.ee;

public class ProductionDoubleHashingTest extends ProductionTesting {

	@Override
	void createHash(int size) {
		hashTable = new HashDoubleHashing<String>(size);
	}

	@Override
	String getFile() {
		return "src\\test\\java\\pl\\edu\\pw\\ee\\resourse\\resultsDouble.txt";
	}
}
