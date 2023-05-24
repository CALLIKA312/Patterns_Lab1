package Patterns.Fabric;

import Transports.Transport;
import Transports.Car;

public class CarFactory implements TransportFactory {

    @Override
    public Transport createInstance(String mark, int capacity) {
        return new Car(mark, capacity);
    }
}
