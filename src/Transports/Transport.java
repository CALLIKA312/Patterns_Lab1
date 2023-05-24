package Transports;

import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import Patterns.Visitor.Visitor;

import java.io.Serializable;

public interface Transport extends Serializable {

    String getMark();

    void setMark(String mark);

    String toString();

    Object clone();

    void changeModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;

    public String[] getModelsNamesOfVehicle();

    int getModelPrice(String modelName) throws NoSuchModelNameException;

    void setModelPrice(String modelName, int newModelPrice) throws NoSuchModelNameException;

    int[] getPricesOfVehicle();

    void addModel(String modelName, int modelPrice) throws DuplicateModelNameException;

    void removeModel(String modelName) throws NoSuchModelNameException;

    int getModelsCount();
    int getId();

    void accept(Visitor visitor);

}