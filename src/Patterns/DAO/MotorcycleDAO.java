package Patterns.DAO;

import Transports.Motorcycle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MotorcycleDAO implements VehicleDAO<Motorcycle> {

    private static final String FILENAME = "motorcycle.txt";

    @Override
    public Motorcycle get(String name) throws IOException {
        List<Motorcycle> motorcycles = getAll();
        for (Motorcycle motorcycle : motorcycles) {
            if (motorcycle.getMark().equals(name)) {
                return motorcycle;
            }
        }
        return null;
    }

    @Override
    public List<Motorcycle> getAll() throws IOException {
        List<Motorcycle> motorcycles = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))) {
            motorcycles = (List<Motorcycle>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return motorcycles;
    }

    @Override
    public void save(Motorcycle motorcycle) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            out.writeObject(motorcycle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Motorcycle motorcycle) throws IOException {
        List<Motorcycle> motorcycles = getAll();
        for (int i = 0; i < motorcycles.size(); i++) {
            if (motorcycles.get(i).getMark().equals(motorcycle.getMark())) {
                motorcycles.set(i, motorcycle);
                saveAll(motorcycles);
                return;
            }
        }
    }

    @Override
    public void delete(String name) throws IOException {
        List<Motorcycle> motorcycles = getAll();
        for (int i = 0; i < motorcycles.size(); i++) {
            if (motorcycles.get(i).getMark().equals(name)) {
                motorcycles.remove(i);
                saveAll(motorcycles);
                return;
            }
        }
    }

    public void saveAll(List<Motorcycle> motorcycles) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            out.writeObject(motorcycles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
