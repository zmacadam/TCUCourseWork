package problem8;

import org.junit.Test;
import problem8.factory.AlgorithmFactoryFactory;

import java.io.File;

public class Sorter {
    private static final long GB = 1000 * 1000 * 1000;
    private AlgorithmFactoryFactory algorithmFactoryFactory = new AlgorithmFactoryFactory();

    public void sortFile(String filePath) {
        File file = new File(filePath);
        long fileSize = file.length();
        int numGB = (int) Math.floor(fileSize / GB);
        algorithmFactoryFactory.getAlgorithmFactory(numGB).createAlgorithm().sort(filePath);
    }

    @Test
    public void test() {
        algorithmFactoryFactory.getAlgorithmFactory(4).createAlgorithm().sort("filePath");
        algorithmFactoryFactory.getAlgorithmFactory(8).createAlgorithm().sort("filePath");
        algorithmFactoryFactory.getAlgorithmFactory(50).createAlgorithm().sort("filePath");
        algorithmFactoryFactory.getAlgorithmFactory(120).createAlgorithm().sort("filePath");
    }
}
