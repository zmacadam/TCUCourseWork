package problem8.factory;

import problem8.Algorithm;
import problem8.QuickSort;

public class QuickSortFactory implements AlgorithmFactory {
    @Override
    public Algorithm createAlgorithm() {
        return new QuickSort();
    }
}
