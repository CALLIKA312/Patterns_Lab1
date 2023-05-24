package Patterns.DAO;

import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import Transports.Transport;
import Transports.Car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransportTextDAO implements TransportDAO<Transport> {

    private static final String FILENAME = "car.txt";

    @Override
    // Метод для получения машины по названию из файла
    public Transport get(String name) throws IOException, DuplicateModelNameException, NoSuchModelNameException {
        Transport transport = StaticTasks.createInstance("",0);
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] transportData = line.split(",");
                if (transportData[0].equals(name)) {
                    String mark = transportData[0];
                    int modelsCount = Integer.parseInt(transportData[1]);
                    transport.setMark(mark);
                    for (int i = 0; i < modelsCount; i++) {
                        String[] modelData = reader.readLine().split(",");
                        //System.out.println("12333 " + modelData[0] + "   " + modelData[1]);
                        transport.addModel(modelData[0], Integer.parseInt(modelData[1]));
                    }
                    reader.close();
                    return transport;
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
    public List<Transport> getAll() throws IOException {
        List<Transport> transports = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] transportData = line.split(",");
                String mark = transportData[0];
                int modelsCount = Integer.parseInt(transportData[1]);
                Car car = new Car();
                car.setMark(mark);
                for (int i = 0; i < modelsCount; i++) {
                    String[] modelData = reader.readLine().split(",");
                    car.addModel(modelData[0], Integer.parseInt(modelData[1]));
                }
                transports.add(car);
            }
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }
        return transports;
    }


    @Override
    public void save(Transport transport) {
        try (FileWriter writer = new FileWriter(FILENAME, false)) {
            writer.write(transport.getMark() + "," +
                    transport.getModelsCount() + "\n" +
                    printModelNPrice(transport));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Transport transport) throws IOException {
        List<Transport> transports = getAll();
        for (int i = 0; i < transports.size(); i++) {
            if (transports.get(i).getMark().equals(transport.getMark())) {
                transports.set(i, transport);
                saveAll(transports);
                return;
            }
        }
    }

    @Override
    public void delete(String mark) throws IOException {
        List<Transport> transports = getAll();
        transports.removeIf(c -> c.getMark().equals(mark));
        saveAll(transports);
    }

    public void saveAll(List<Transport> transports) {
        try (FileWriter writer = new FileWriter(FILENAME, false)) {
            for (Transport transport : transports) {
                writer.write(transport.getMark() + "," +
                        transport.getModelsCount() + "\n" +
                        printModelNPrice(transport));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String printModelNPrice(Transport transport) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < transport.getModelsCount(); i++)
            s.append(transport.getModelsNamesOfVehicle()[i]).append(",").append(transport.getPricesOfVehicle()[i]).append("\n");
        return s.toString();
    }
}