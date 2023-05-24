package Patterns.Strategy;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCountingStrategy implements CountingStrategy {
    @Override
    public Map<Integer, Integer> countOccurrences(int[] array) {
        return Arrays.stream(array)
                .boxed()
                .collect(Collectors.toMap(
                        element -> element,
                        element -> 1,
                        Integer::sum
                ));
    }
}
