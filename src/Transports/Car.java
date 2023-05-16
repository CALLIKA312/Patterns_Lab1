package Transports;

import Exeptions.*;
import Interfaces.Transport;

import java.io.Serializable;
import java.util.Arrays;

public class Car implements Transport, Serializable, Cloneable {

    private class Model implements Serializable, Cloneable {
        public String modelName;
        public int modelPrice;

        public Model(String modelName, int modelPrice) {
            this.modelName = modelName;
            this.modelPrice = modelPrice;
        }

        public Object clone() {
            try {
                Model clone = (Model) super.clone();
                return clone;
            } catch (CloneNotSupportedException exception) {
                exception.printStackTrace();
                return null;
            }
        }
    }

    private String mark;
    private int id = 1;
    private Model[] modelsOfCar;
    private int modelsCount;

    public Car() {
        this.mark = "";
        this.modelsOfCar = new Model[1];
        this.modelsCount = 0;
    }

    public Car(String mark, int capacity) {
        this.mark = mark;
        this.modelsOfCar = new Model[capacity];
        for (int i = 0; i < capacity; i++) {
            modelsOfCar[i] = new Model("" + i, 1);
        }
        this.modelsCount = capacity;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(
                this.getClass().getName())
                .append("@").append(Integer.toHexString(hashCode()));
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {

        boolean ans = false;
        Transport transport = null;

        if (obj == null) {
            return ans;
        }
        if (obj == this) {
            return true;
        }

        if (obj instanceof Transport) {
            transport = (Transport) obj;
        } else return ans;

        if (!(transport.getMark()).equals(this.getMark())) {
            return ans;
        }

        if (!Arrays.equals((transport).getModelsOfVehicle(),
                this.getModelsOfVehicle())) {
            return ans;
        }

        if (!Arrays.equals((transport).getPricesOfVehicle(),
                this.getPricesOfVehicle())) {
            return ans;
        }

        return true;
    }

    public int hashCode() {
        return id * mark.hashCode() * getModelsOfVehicle().hashCode();
    }

    public Object clone() {
        Car result = null;
        try {
            result = (Car) super.clone();
            result.modelsOfCar = new Model[result.modelsCount];
            for (int i = 0; i < modelsCount; i++) {
                Model clone = (Model) this.modelsOfCar[i].clone();
                result.modelsOfCar[i] = clone;
            }
            return result;
        } catch (CloneNotSupportedException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void changeModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        int pos = findModelPos(oldName);
        if (oldName.equals(newName)) return;
        checkDuplicate(newName);
        modelsOfCar[pos].modelName = newName;
    }

    public String[] getModelsOfVehicle() {
        String[] modelsNames = new String[modelsOfCar.length];
        for (int i = 0; i < getModelsCount(); i++)
            modelsNames[i] = modelsOfCar[i].modelName;
        return modelsNames;
    }

    public int getModelPrice(String modelName) throws NoSuchModelNameException {
        int pos = findModelPos(modelName);
        return modelsOfCar[pos].modelPrice;
    }

    public void setModelPrice(String modelName, int newPrice) throws NoSuchModelNameException {
        checkPrice(newPrice);
        int pos = findModelPos(modelName);
        modelsOfCar[pos].modelPrice = newPrice;
    }

    public int[] getPricesOfVehicle() {
        int[] modelsPrices = new int[modelsOfCar.length];
        for (int i = 0; i < getModelsCount(); i++)
            modelsPrices[i] = modelsOfCar[i].modelPrice;
        return modelsPrices;
    }

    public void addModel(String modelName, int modelPrice) throws DuplicateModelNameException {
        checkPrice(modelPrice);
        checkDuplicate(modelName);
        modelsOfCar = Arrays.copyOf(modelsOfCar, getModelsCount() + 1);
        modelsOfCar[getModelsCount()] = new Model(modelName, modelPrice);
        modelsCount++;
    }

    public void removeModel(String modelName) throws NoSuchModelNameException {
        int pos = findModelPos(modelName);
        System.arraycopy(modelsOfCar, pos + 1, modelsOfCar, pos, getModelsCount() - pos - 1);
        modelsOfCar = Arrays.copyOf(modelsOfCar, getModelsCount() - 1);
        modelsCount--;
    }

    public int getModelsCount() {
        return modelsCount;
    }

    public int getId() {
        return id;
    }

    private int findModelPosOrNegative(String modelName) {
        if (modelsOfCar[0] == null) return -1;
        for (int i = 0; i < modelsOfCar.length; i++)
            if (modelsOfCar[i].modelName.equals(modelName)) return i;
        return -1;
    }

    private int findModelPos(String modelName) throws NoSuchModelNameException {
        int pos = findModelPosOrNegative(modelName);
        if (-1 == pos) {
            throw new NoSuchModelNameException("Модель не найдена: " + modelName);
        }
        return pos;
    }

    private void checkDuplicate(String modelName) throws DuplicateModelNameException {
        int pos = findModelPosOrNegative(modelName);
        if (0 <= pos)
            throw new DuplicateModelNameException(
                    String.format("Найдена модель с таким же именем \"%s\" в позиции %d", modelName, pos + 1));

    }

    private void checkPrice(int price) {
        if (price <= 0) throw new ModelPriceOutOfBoundsException("Ожидается положительная цена модель, а не: ", price);
    }


}
