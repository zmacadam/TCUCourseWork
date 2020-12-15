package problem8.factory;

import problem8.Algorithm;
import problem8.MapreduceSort;

public class MapreduceSortFactory implements AlgorithmFactory {
    @Override
    public Algorithm createAlgorithm() {
        return new MapreduceSort();
    }
}
