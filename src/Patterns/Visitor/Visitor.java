package Patterns.Visitor;

import Exeptions.NoSuchModelNameException;
import Transports.Car;
import Transports.Motorcycle;

public interface Visitor {
    void visit(Car car);
    void visit(Motorcycle motorcycle);
}