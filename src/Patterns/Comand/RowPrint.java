package Patterns.Comand;

import Transports.Car;

import java.io.PrintWriter;
import java.util.Arrays;

public class RowPrint implements iCommand {

    @Override
    public void execute(Car car, PrintWriter writer) {
        writer.print("Марка: " + car.getMark() + ", ");
        writer.print("Количество моделей: " + car.getModelsCount() + ", ");
        writer.print("Модели: " + Arrays.toString(car.getModelsNamesOfVehicle()));
    }
}
