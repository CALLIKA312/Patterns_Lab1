package Patterns;

import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import Interfaces.Transport;

public class Decorator implements Transport {
    private final Transport transport;

    public Decorator(Transport transport) {
        this.transport = transport;
    }

    public synchronized Transport getTransport() {
        return transport;
    }


    @Override
    public synchronized String getMark() {
        return transport.getMark();
    }

    @Override
    public synchronized void setMark(String mark) {
        transport.setMark(mark);
    }

    @Override
    public synchronized Object clone() {
        return transport.clone();
    }

    @Override
    public synchronized void changeModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        transport.changeModelName(oldName, newName);
    }

    @Override
    public synchronized String[] getModelsOfVehicle() {
        return transport.getModelsOfVehicle();
    }

    @Override
    public synchronized int getModelPrice(String modelName) throws NoSuchModelNameException {
        return transport.getModelPrice(modelName);
    }

    @Override
    public synchronized void setModelPrice(String modelName, int newModelPrice) throws NoSuchModelNameException {
        transport.setModelPrice(modelName, newModelPrice);
    }

    @Override
    public synchronized int[] getPricesOfVehicle() {
        return transport.getPricesOfVehicle();
    }

    @Override
    public synchronized void addModel(String modelName, int modelPrice) throws DuplicateModelNameException {
        transport.addModel(modelName, modelPrice);
    }

    @Override
    public synchronized void removeModel(String modelName) throws NoSuchModelNameException {
        transport.removeModel(modelName);
    }

    @Override
    public synchronized int getModelsCount() {
        return transport.getModelsCount();
    }

    @Override
    public synchronized int getId() {
        return transport.getId();
    }

}
