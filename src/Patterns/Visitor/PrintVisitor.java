package Patterns.Visitor;

import Exeptions.NoSuchModelNameException;
import Transports.Car;
import Transports.Motorcycle;

import java.util.Arrays;

public class PrintVisitor implements Visitor {
    @Override
    public void visit(Car car) {
        System.out.println(car.getMark() + " - " + Arrays.toString(car.getModels()));
    }

    @Override
    public void visit(Motorcycle motorcycle) {
        System.out.println(motorcycle.getMark());
        for (int i = 0; i < motorcycle.getModelsCount(); i++) {
            String name = motorcycle.getModelsNamesOfVehicle()[i];
            int price = 0;
            try {
                price = motorcycle.getModelPrice(name);
            } catch (NoSuchModelNameException e) {
                e.printStackTrace();
            }
            System.out.println(name + " - " + price);
        }
    }
}
