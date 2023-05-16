package Patterns.Fabric;

import Exeptions.DuplicateModelNameException;
import Interfaces.TransportFactory;
import Interfaces.Transport;
import Transports.Motorcycle;

public class MotorcycleFabric implements TransportFactory {

    @Override
    public Transport createInstance(String mark, int capacity) throws DuplicateModelNameException {
        return new Motorcycle(mark, capacity);
    }
}