package Transports;

import Exeptions.DuplicateModelNameException;
import Exeptions.ModelPriceOutOfBoundsException;
import Exeptions.NoSuchModelNameException;
import Patterns.Comand.iCommand;
import Patterns.Visitor.Visitor;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class Car implements Transport, Serializable, Cloneable {

    public static class Model implements Serializable, Cloneable {
        public String name;
        public int price;

        public Model(String name, int price) {
            this.name = name;
            this.price = price;
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

        @Override
        public String toString() {
            return "Название: " + name + ", Цена: " + price;
        }
    }

    private String mark;
    private final int id = 1;
    private Model[] models;
    private int modelsCount;

    public Car() {
        this.mark = "";
        this.models = new Model[1];
        this.modelsCount = 0;
    }

    public Car(String mark, int capacity) {
        this.mark = mark;
        this.models = new Model[capacity];
        for (int i = 0; i < capacity; i++) {
            models[i] = new Model("" + i, 1);
        }
        this.modelsCount = capacity;
    }

    public String toString() {
        return this.getClass().getName() +
                "@" + Integer.toHexString(hashCode());
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

        if (!Arrays.equals((transport).getModelsNamesOfVehicle(),
                this.getModelsNamesOfVehicle())) {
            return ans;
        }

        if (!Arrays.equals((transport).getPricesOfVehicle(),
                this.getPricesOfVehicle())) {
            return ans;
        }

        return true;
    }

    public int hashCode() {
        return id * mark.hashCode() * getModelsNamesOfVehicle().hashCode();
    }

    public Object clone() {
        Car result = null;
        try {
            result = (Car) super.clone();
            result.models = new Model[result.modelsCount];
            for (int i = 0; i < modelsCount; i++) {
                Model clone = (Model) this.models[i].clone();
                result.models[i] = clone;
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
        models[pos].name = newName;
    }

    public Model[] getModels() {
        return models;
    }

    public void setModels(Model[] models) {
        this.models = models;
    }

    public String[] getModelsNamesOfVehicle() {
        String[] modelsNames = new String[models.length];
        for (int i = 0; i < getModelsCount(); i++)
            modelsNames[i] = models[i].name;
        return modelsNames;
    }

    public int getModelPrice(String modelName) throws NoSuchModelNameException {
        int pos = findModelPos(modelName);
        return models[pos].price;
    }

    public void setModelPrice(String modelName, int newPrice) throws NoSuchModelNameException {
        checkPrice(newPrice);
        int pos = findModelPos(modelName);
        models[pos].price = newPrice;
    }

    public int[] getPricesOfVehicle() {
        int[] modelsPrices = new int[models.length];
        for (int i = 0; i < getModelsCount(); i++)
            modelsPrices[i] = models[i].price;
        return modelsPrices;
    }

    public void addModel(String modelName, int modelPrice) throws DuplicateModelNameException {
        checkPrice(modelPrice);
        checkDuplicate(modelName);
        models = Arrays.copyOf(models, getModelsCount() + 1);
        models[getModelsCount()] = new Model(modelName, modelPrice);
        modelsCount++;
    }

    public void removeModel(String modelName) throws NoSuchModelNameException {
        int pos = findModelPos(modelName);
        System.arraycopy(models, pos + 1, models, pos, getModelsCount() - pos - 1);
        models = Arrays.copyOf(models, getModelsCount() - 1);
        modelsCount--;
    }

    public int getModelsCount() {
        return modelsCount;
    }

    public int getId() {
        return id;
    }

    private int findModelPosOrNegative(String modelName) {
        if (models.length > 0 && models[0] == null) return -1;
        for (int i = 0; i < models.length; i++)
            if (models[i].name.equals(modelName)) return i;
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

    private iCommand command;

    public void print(PrintWriter writer) {
        if (command != null) command.execute(this, writer);
    }

    public void setPrintCommand(iCommand command) {
        this.command = command;
    }

    public Iterator<Model> iterator() {
        return new CarIterator();
    }

    private class CarIterator implements Iterator<Model> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < models.length;
        }

        @Override
        public Model next() {
            return models[currentIndex++];
        }
    }


    public static class Memento {
        private byte[] serializedState;

        public Memento(Car car) {
            setCar(car);
        }

        public void setCar(Car car) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(car);
                oos.close();
                serializedState = baos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Car getCar() {
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(serializedState);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Car car = (Car) ois.readObject();
                ois.close();
                return car;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


    public Memento createMemento() {
        return new Memento(this);
    }

    public void setMemento(Memento memento) {
        this.mark = memento.getCar().getMark();
        this.modelsCount = memento.getCar().getModelsCount();
        this.models = memento.getCar().getModels();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
