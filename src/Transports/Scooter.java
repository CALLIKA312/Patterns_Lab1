package Transports;

import Exeptions.*;
import Interfaces.Transport;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Scooter implements Transport, Serializable, Cloneable {

    private String mark;
    private HashMap<String, Integer> modelsOfScooter;
    private int id = 3;
    private int pseudoModelsCount;

    public Scooter() {
        this.mark = "";
        this.modelsOfScooter = new HashMap<>();
    }

    public Scooter(String mark, int capacity) {
        this.mark = mark;
        this.modelsOfScooter = new HashMap<>();
        for (int i = 0; i < capacity; i++) {
            modelsOfScooter.put("" + i, 1);
        }
        pseudoModelsCount = capacity;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Object clone() {
        Scooter result = null;
        try {
            result = (Scooter) super.clone();
            HashMap<String, Integer> newMap = new HashMap<String, Integer>();
            for (Map.Entry<String, Integer> entry : modelsOfScooter.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                newMap.put(key, value);
            }
            result.modelsOfScooter = newMap;
            return result;

        } catch (CloneNotSupportedException exception) {
            exception.printStackTrace();
            return null;
        }
    }


    public void changeModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        findKeyOrNot(oldName);
        if (oldName.equals(newName)) return;
        checkDuplicate(newName);
        modelsOfScooter.put(newName, modelsOfScooter.get(oldName));
        modelsOfScooter.remove(oldName);
    }


    public String[] getModelsOfVehicle() {
        String[] modelsNames = new String[getModelsCount()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : modelsOfScooter.entrySet()) {
            modelsNames[i] = entry.getKey();
            i++;
        }
        return modelsNames;
    }


    public int getModelPrice(String modelName) throws NoSuchModelNameException {
        findKeyOrNot(modelName);
        return modelsOfScooter.get(modelName);
    }


    public void setModelPrice(String modelName, int newModelPrice) throws NoSuchModelNameException {
        findKeyOrNot(modelName);
        checkPrice(newModelPrice);
        modelsOfScooter.remove(modelName);
        modelsOfScooter.put(modelName, newModelPrice);
    }


    public int[] getPricesOfVehicle() {
        int[] modelsPrices = new int[getModelsCount()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : modelsOfScooter.entrySet()) {
            modelsPrices[i] = entry.getValue();
            i++;
        }
        return modelsPrices;
    }


    public void addModel(String modelName, int modelPrice) throws DuplicateModelNameException {
        if (pseudoModelsCount != 0) {
            modelsOfScooter.clear();
            pseudoModelsCount = 0;
        }
        checkDuplicate(modelName);
        modelsOfScooter.put(modelName, modelPrice);
    }


    public void removeModel(String modelName) throws NoSuchModelNameException {
        findKeyOrNot(modelName);
        modelsOfScooter.remove(modelName);
    }


    public int getModelsCount() {
        return modelsOfScooter.size();
    }


    public int getId() {
        return id;
    }


    private void findKeyOrNot(String modelName) throws NoSuchModelNameException {
        if (!modelsOfScooter.containsKey(modelName))
            throw new NoSuchModelNameException("Модель не найдена: " + modelName);
    }

    private void checkDuplicate(String modelName) throws DuplicateModelNameException {
        if (modelsOfScooter.containsKey(modelName)) throw new DuplicateModelNameException(
                String.format("Найдена модель с таким же именем \"%s\"", modelName));

    }

    private void checkPrice(int price) {
        if (price <= 0) throw new ModelPriceOutOfBoundsException("Ожидается положительная цена модель, а не: ", price);
    }
}
