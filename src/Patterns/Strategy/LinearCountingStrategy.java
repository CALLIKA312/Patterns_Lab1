package Patterns.Strategy;

import java.util.HashMap;
import java.util.Map;

public class LinearCountingStrategy implements CountingStrategy {
    @Override
    public Map<Integer, Integer> countOccurrences(int[] array) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int element : array) occurrences.put(element, occurrences.getOrDefault(element, 0) + 1);
        return occurrences;
    }
}