package Patterns.Strategy;


import java.util.Map;

public interface CountingStrategy {
    Map<Integer, Integer> countOccurrences(int[] array);
}