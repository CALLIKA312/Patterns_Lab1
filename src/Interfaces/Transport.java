package Interfaces;

import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;

import java.io.Serializable;

public interface Transport extends Serializable {

    String getMark();

    void setMark(String mark);

    String toString();

    Object clone();

    void changeModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;

    public String[] getModelsOfVehicle();

    int getModelPrice(String modelName) throws NoSuchModelNameException;

    void setModelPrice(String modelName, int newModelPrice) throws NoSuchModelNameException;

    int[] getPricesOfVehicle();

    void addModel(String modelName, int modelPrice) throws DuplicateModelNameException;

    void removeModel(String modelName) throws NoSuchModelNameException;

    int getModelsCount();
    int getId();

}