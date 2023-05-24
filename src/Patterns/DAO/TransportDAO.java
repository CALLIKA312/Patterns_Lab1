package Patterns.DAO;

import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;

import java.io.IOException;
import java.util.List;

public interface TransportDAO<Transport> {
    Transport get(String name) throws IOException, DuplicateModelNameException, NoSuchModelNameException;
    void save(Transport t);
    void update(Transport t) throws IOException;
    void delete(String s) throws IOException;
    List<Transport> getAll() throws IOException;
}