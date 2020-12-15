package problem8.factory;

import problem8.Algorithm;
import problem8.ExternalSort;

public class ExternalSortFactory implements AlgorithmFactory {
    @Override
    public Algorithm createAlgorithm() {
        return new ExternalSort();
    }
}
