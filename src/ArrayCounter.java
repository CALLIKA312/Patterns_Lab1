import Patterns.Strategy.CountingStrategy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class ArrayCounter {
    private CountingStrategy strategy;

    public ArrayCounter(CountingStrategy strategy) {
        this.strategy = strategy;
    }

    public Map<Integer, Integer> countOccurrences(String inputFile) {
        try (FileInputStream fileInputStream = new FileInputStream(inputFile);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            int[] array = (int[]) objectInputStream.readObject();
            return strategy.countOccurrences(array);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}