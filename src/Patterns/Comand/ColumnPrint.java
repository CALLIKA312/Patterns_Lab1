package Patterns.Comand;

import Transports.Car;

import java.io.PrintWriter;
import java.util.Arrays;

public class ColumnPrint implements iCommand{

    @Override
    public void execute(Car car, PrintWriter writer) {
        writer.println("Марка: " + car.getMark());
        writer.println("Количество моделей: " + car.getModelsCount());
        writer.println("Модели: " + Arrays.toString(car.getModelsNamesOfVehicle()));
    }
}
