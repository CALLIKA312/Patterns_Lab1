package Transports;

import Exeptions.DuplicateModelNameException;
import Exeptions.ModelPriceOutOfBoundsException;
import Exeptions.NoSuchModelNameException;
import Patterns.Visitor.Visitor;

import java.io.Serializable;
import java.util.Arrays;

public class Motorcycle implements Transport, Serializable, Cloneable {


    public class Model implements Serializable, Cloneable {
        public String modelName;
        public int modelPrice;
        public Model prev;
        public Model next;

        public Model() {
            this.prev = null;
            this.next = null;
        }

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

    private Model head = new Model();
    {
        head.prev = head;
        head.next = head;
    }


    private final int id = 2;
    private String mark;
    private int modelsCount;
    private int pseudoModelsCount;

    public Motorcycle() {
        this.mark = "";
        this.modelsCount = 0;
        this.pseudoModelsCount = 0;
    }

    public Motorcycle(String mark, int capacity) throws DuplicateModelNameException { //capacity
        this.mark = mark;
        this.modelsCount = 0;
        for (int i = 0; i < capacity; i++) {
            addModel("" + i, 1);
        }
        this.pseudoModelsCount = capacity;

    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(
                this.getClass().getName()).
                append("@").append(Integer.toHexString(hashCode()));
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
        return id * getModelsNamesOfVehicle().hashCode() * getPricesOfVehicle().hashCode();
    }

    public Object clone() {
        Motorcycle result = null;
        try {
            result = (Motorcycle) super.clone();
            result.head = new Model();
            result.head.next = result.head;
            result.head.prev = result.head;
            Model curMod = head.next;
            while (curMod != head) {
                Model clone = (Model) curMod.clone();
                clone.next = result.head;
                clone.prev = result.head.prev;
                result.head.prev.next = clone;
                result.head.prev = clone;
                curMod = curMod.next;
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
        Model model = findModel(oldName);
        if (oldName.equals(newName)) return;
        checkDuplicate(newName);
        model.modelName = newName;
    }


    public String[] getModelsNamesOfVehicle() {
        String[] modelsNames = new String[getModelsCount()];
        Model curModel = head.next;
        for (int i = 0; i < getModelsCount(); i++) {
            modelsNames[i] = curModel.modelName;
            curModel = curModel.next;
        }
        return modelsNames;
    }

    public int getModelPrice(String modelName) throws NoSuchModelNameException {
        Model model = findModel(modelName);
        return model.modelPrice;
    }

    public void setModelPrice(String modelName, int newPrice) throws NoSuchModelNameException {
        checkPrice(newPrice);
        Model model = findModel(modelName);
        model.modelPrice = newPrice;
    }


    public int[] getPricesOfVehicle() {
        int[] modelsPrices = new int[getModelsCount()];
        Model curModel = head.next;
        for (int i = 0; i < getModelsCount(); i++) {
            modelsPrices[i] = curModel.modelPrice;
            curModel = curModel.next;
        }
        return modelsPrices;
    }

    public void addModel(String modelName, int modelPrice) throws DuplicateModelNameException {
        Model newModel = new Model(modelName, modelPrice);
        if (pseudoModelsCount != 0) {
            int pseudoCounter = pseudoModelsCount;
            Model curModel = head;
            while (pseudoCounter > 0) {
                curModel = curModel.prev;
                pseudoCounter--;
            }
            newModel.next = curModel.next;
            newModel.prev = curModel.prev;
            newModel.next.prev = newModel;
            newModel.prev.next = newModel;
            pseudoModelsCount--;
        } else {
            checkPrice(modelPrice);
            checkDuplicate(modelName);
            head.prev.next = newModel;
            newModel.prev = head.prev;
            newModel.next = head;
            head.prev = newModel;
            modelsCount++;
        }

    }

    public void removeModel(String modelName) throws NoSuchModelNameException {
        Model model = findModel(modelName);
        model.prev.next = model.next;
        model.next.prev = model.prev;
        modelsCount--;
    }

    public int getModelsCount() {
        return modelsCount;
    }

    public int getId() {
        return id;
    }

    private Model findModelOrNot(String modelName) {
        Model curModel = head.next;
        for (int i = 0; i < getModelsCount(); i++) {
            if (curModel.modelName.equals(modelName)) return curModel;
            curModel = curModel.next;
        }
        return new Model("#error", -1);
    }

    private Model findModel(String modelName) throws NoSuchModelNameException {
        Model model = findModelOrNot(modelName);
        if (model.modelName.equals("#error")) throw new NoSuchModelNameException("Модель не найдена: " + modelName);
        return model;
    }

    private void checkDuplicate(String modelName) throws DuplicateModelNameException {
        Model model = findModelOrNot(modelName);
        if (!model.modelName.equals("#error"))
            throw new DuplicateModelNameException(
                    String.format("Найдена модель с таким же именем \"%s\"", modelName));
    }

    private void checkPrice(int price) {
        if (price <= 0)
            throw new ModelPriceOutOfBoundsException("Ожидается положительная цена модель, а не: ", price);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
