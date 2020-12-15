package problem8.factory;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

public class AlgorithmFactoryFactory {
    private static final RangeMap<Integer, AlgorithmFactory> cachedFactories = TreeRangeMap.create();

    static {
        cachedFactories.put(Range.closed(0,5), new QuickSortFactory());
        cachedFactories.put(Range.closed(6, 9), new ExternalSortFactory());
        cachedFactories.put(Range.closed(10, 99), new ConcurrentExternalSortFactory());
        cachedFactories.put(Range.closed(100, Integer.MAX_VALUE), new MapreduceSortFactory());
    }

    public static AlgorithmFactory getAlgorithmFactory(int size) {
        if (size < 0) {
            return null;
        }
        AlgorithmFactory algorithmFactory = cachedFactories.get(size);
        return algorithmFactory;
    }
}
