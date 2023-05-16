package Patterns.Fabric;

import Interfaces.TransportFactory;
import Interfaces.Transport;
import Transports.Car;

public class AutoFactory implements TransportFactory {

    @Override
    public Transport createInstance(String mark, int capacity) {
        return new Car(mark, capacity);
    }
}
