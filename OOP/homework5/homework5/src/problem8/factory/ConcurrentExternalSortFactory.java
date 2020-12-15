package problem8.factory;

import problem8.Algorithm;
import problem8.ConcurrentExternalSort;

public class ConcurrentExternalSortFactory implements AlgorithmFactory {
    @Override
    public Algorithm createAlgorithm() {
        return new ConcurrentExternalSort();
    }
}
