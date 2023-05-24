package Patterns.DAO;

import Exeptions.DuplicateModelNameException;
import Transports.Transport;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransportSerializeDAO implements TransportDAO<Transport> {

    private static final String FILENAME = "motorcycle.txt";

    @Override
    public Transport get(String name) throws DuplicateModelNameException {
        Transport transport = StaticTasks.createInstance("", 0);
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))) {
            transport = (Transport) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transport;
    }

//    @Override
//    public Transport get(String name) throws IOException {
//        List<Transport> transports = getAll();
//        for (Transport transport : transports) {
//            if (transport.getMark().equals(name)) {
//                return transport;
//            }
//        }
//        return null;
//    }

    @Override
    public List<Transport> getAll() throws IOException {
        List<Transport> transports = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))) {
            transports = (List<Transport>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transports;
    }

    @Override
    public void save(Transport transport) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            out.writeObject(transport);
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
    public void delete(String name) throws IOException {
        List<Transport> transports = getAll();
        for (int i = 0; i < transports.size(); i++) {
            if (transports.get(i).getMark().equals(name)) {
                transports.remove(i);
                saveAll(transports);
                return;
            }
        }
    }

    public void saveAll(List<Transport> transports) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            out.writeObject(transports);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
