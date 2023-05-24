package Patterns.DAO;

import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import Patterns.Fabric.MotorcycleFabric;
import Transports.Transport;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, DuplicateModelNameException, NoSuchModelNameException {
        /*DAO*/
        TransportTextDAO transportTextDAO = new TransportTextDAO();

        Transport pezho = StaticTasks.createInstance("pezho", 5);
        transportTextDAO.save(pezho);

        Transport testCar = transportTextDAO.get("pezho");
        System.out.println(testCar.getClass());
        StaticTasks.printMarkInfo(testCar);

        StaticTasks.setTransportFactory(new MotorcycleFabric());

        // List<Car> cars = carDAO.getAll();
        //  for (Car car : cars) StaticTasks.printMarkInfo(car);
        // carDAO.delete("asd");

        TransportSerializeDAO motorcycleDAO = new TransportSerializeDAO();
        Transport hastle = StaticTasks.createInstance("qwe", 5);
        motorcycleDAO.save(hastle);
        Transport newHastle = motorcycleDAO.get("qwe");
        StaticTasks.printMarkInfo(newHastle);


//        MotorcycleDAO motorcycleDAO = new MotorcycleDAO();

//        List<Motorcycle> motorcycles = new ArrayList<>();
//        Motorcycle hastle = new Motorcycle("hastle", 5);
//        Motorcycle qwe = new Motorcycle("qwe", 5);
//        motorcycles.add(hastle);
//        motorcycles.add(qwe);
//        motorcycleDAO.saveAll(motorcycles);
        //motorcycleDAO.save(hastle);
    }
}
