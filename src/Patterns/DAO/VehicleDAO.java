package Patterns.DAO;

import java.io.IOException;
import java.util.List;

public interface VehicleDAO<Transport> {
    Transport get(String name) throws IOException;
    void save(Transport t);
    void update(Transport t) throws IOException;
    void delete(String s) throws IOException;
    List<Transport> getAll() throws IOException;
}