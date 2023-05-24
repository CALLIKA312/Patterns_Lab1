package Patterns.Fabric;

import Exeptions.DuplicateModelNameException;
import Transports.Transport;
import Transports.Motorcycle;

public class MotorcycleFabric implements TransportFactory {

    @Override
    public Transport createInstance(String mark, int capacity) throws DuplicateModelNameException {
        return new Motorcycle(mark, capacity);
    }
}