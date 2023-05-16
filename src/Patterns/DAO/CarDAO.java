package Patterns.DAO;

import Exeptions.DuplicateModelNameException;
import Interfaces.Transport;
import Transports.Car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO implements VehicleDAO<Car> {

    private static final String FILENAME = "car.txt";

    @Override
    // Метод для получения машины по названию из файла
    public Car get(String name) throws IOException {
        Car car = new Car();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] carData = line.split(",");
                if (carData[0].equals(name)) {
                    String mark = carData[0];
                    int modelsCount = Integer.parseInt(carData[1]);
                    car.setMark(mark);
                    for (int i = 0; i < modelsCount; i++) {
                        String[] modelData = reader.readLine().split(",");
                        //System.out.println("12333 " + modelData[0] + "   " + modelData[1]);
                        car.addModel(modelData[0], Integer.parseInt(modelData[1]));
                    }
                    reader.close();
                    return car;
                }
            }
            reader.close();
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    // Метод для получения всех машин из файла
    public List<Car> getAll() throws IOException {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] carData = line.split(",");
                String mark = carData[0];
                int modelsCount = Integer.parseInt(carData[1]);
                Car car = new Car();
                car.setMark(mark);
                for (int i = 0; i < modelsCount; i++) {
                    String[] modelData = reader.readLine().split(",");
                    car.addModel(modelData[0], Integer.parseInt(modelData[1]));
                }
                cars.add(car);
            }
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }
        return cars;
    }


    @Override
    public void save(Car car) {
        try (FileWriter writer = new FileWriter(FILENAME, false)) {
            writer.write(car.getMark() + "," +
                    car.getModelsCount() + "\n" +
                    printModelNPrice(car));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Car car) throws IOException {
        List<Car> cars = getAll();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getMark().equals(car.getMark())) {
                cars.set(i, car);
                saveAll(cars);
                return;
            }
        }
    }

    @Override
    public void delete(String mark) throws IOException {
        List<Car> cars = getAll();
        cars.removeIf(c -> c.getMark().equals(mark));
        saveAll(cars);
    }

    public void saveAll(List<Car> cars) {
        try (FileWriter writer = new FileWriter(FILENAME, false)) {
            for (Car car : cars) {
                writer.write(car.getMark() + "," +
                        car.getModelsCount() + "\n" +
                        printModelNPrice(car));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String printModelNPrice(Transport transport) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < transport.getModelsCount(); i++)
            s.append(transport.getModelsOfVehicle()[i]).append(",").append(transport.getPricesOfVehicle()[i]).append("\n");
        return s.toString();
    }
}