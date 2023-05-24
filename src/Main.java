import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import Patterns.Comand.ColumnPrint;
import Patterns.Comand.RowPrint;
import Patterns.Strategy.CountingStrategy;
import Patterns.Strategy.LinearCountingStrategy;
import Patterns.Strategy.StreamCountingStrategy;
import Patterns.Visitor.PrintVisitor;
import Patterns.Visitor.Visitor;
import Patterns.СhainOfResponsibility.ColumnHandler;
import Patterns.СhainOfResponsibility.RowHandler;
import Transports.Motorcycle;
import Transports.Transport;
import Patterns.DAO.TransportTextDAO;
import Patterns.Fabric.MotorcycleFabric;
import Transports.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws NoSuchModelNameException, DuplicateModelNameException, IOException {
        /* Lab 1 */
        /*Singleton*/
        /*{
            Properties properties = Singleton.getInstance();
            System.out.println(properties.getProperty("testString"));
            properties = Singleton.getInstance();
        }*/


        /*FabricMethod*/
        /*{
            Transport testCar =  StaticTasks.createInstance("Volvo", 5);
            System.out.println(testCar.getClass());
            StaticTasks.printMarkInfo(testCar);
            testCar.setModelPrice("2", 6);
            System.out.println("Средняя цена " + StaticTasks.getAveragePrice(testCar));

            StaticTasks.setTransportFactory(new MotorcycleFabric());
            Transport testMoto = StaticTasks.createInstance("Moto", 3);
            System.out.println(testMoto.getClass());
            StaticTasks.printMarkInfo(testMoto);
            testMoto.setModelPrice("1",4);
            System.out.println("Средняя цена " + StaticTasks.getAveragePrice(testMoto));
        }*/


        /*Prototype*/
        /*{
            Transport yamaha = new Motorcycle("Ямаха", 2);

            yamaha.setMark(("Yamaha"));
            yamaha.addModel("Niken", 13);
            yamaha.addModel("Fazer", 2);
            yamaha.addModel("Z315", 20);

            Object obj;

            System.out.println(yamaha.toString());
            System.out.println(yamaha.hashCode());

            obj = yamaha.clone();
            StaticTasks.printMarkInfo((Transports.Transport) obj);

            ((Transports.Transport) obj).changeModelName("Fazer", "tmp_Name");
            //((Interfaces.Vehicle) obj).removeModel("Niken");
            ((Transports.Transport) obj).removeModel("tmp_Name");
            StaticTasks.printMarkInfo((Transports.Transport) obj);
            StaticTasks.printMarkInfo(yamaha);

            if (yamaha.equals(obj)) System.out.println("Their equals by equality");
            else System.out.println("Their not equals by equality");

            if (yamaha.hashCode() == obj.hashCode()) System.out.println("Their equals by hashCode");
            else System.out.println("Their not equals by hashCode");
        }*/


        /* Lab 2 */
        /*Adapter*/
        /*{
            String[] sa = new String[]
                    {
                            "first",
                            "second",
                            "third"
                    };
            Adapter adapter = new Adapter();
            System.out.println(adapter.convertStringToOS(sa));
        }*/


        /*Decorator*/
        /*{
            Transport uaz = new Car("uaz", 5);
            System.out.println("Класс-обертка уаза " + StaticTasks.synchronizedTransport(uaz));
        }*/


        /*Facade*/
        //FacadeModel.main(args);


        /*Proxy*/
        /*try {
            Client client = new Client();
            client.startConnection("localhost", 5000);
            String response = client.sendTwoDigitToMulti(-2.3, 3.0005);
            client.stopConnection();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*DAO*/
//        TransportTextDAO transportTextDAO = new TransportTextDAO();
//
//        List<Car> cars = new ArrayList<>();
//        Car pezho = new Car("pezho", 5);
//        cars.add(pezho);
//        //carDAO.saveAll(cars);
//        transportTextDAO.save(pezho);
//
//        Transport testCar = StaticTasks.createInstance("xar", 5);
//
//        testCar = transportTextDAO.get("pezho");
//        System.out.println(testCar.getClass());
//        StaticTasks.printMarkInfo(testCar);
//
//        StaticTasks.setTransportFactory(new MotorcycleFabric());

       // List<Car> cars = carDAO.getAll();
      //  for (Car car : cars) StaticTasks.printMarkInfo(car);
       // carDAO.delete("asd");


//        MotorcycleDAO motorcycleDAO = new MotorcycleDAO();

//        List<Motorcycle> motorcycles = new ArrayList<>();
//        Motorcycle hastle = new Motorcycle("hastle", 5);
//        Motorcycle qwe = new Motorcycle("qwe", 5);
//        motorcycles.add(hastle);
//        motorcycles.add(qwe);
//        motorcycleDAO.saveAll(motorcycles);
        //motorcycleDAO.save(hastle);

//        Motorcycle newHastle = motorcycleDAO.get("qwe");
//        StaticTasks.printMarkInfo(newHastle);

//        motorcycleDAO.delete("qwe");
//        List<Motorcycle> motorcycles = motorcycleDAO.getAll();
//        for (Motorcycle motorcycle : motorcycles) StaticTasks.printMarkInfo(motorcycle);


        /*Chain of Responsibility*/

//        RowHandler handler1 = new RowHandler();
//        ColumnHandler handler2 = new ColumnHandler();
//        handler1.setNextHandler(handler2);
//
//        Car pezho = new Car("pezho", 5);
//        StaticTasks.printMarkInfo(pezho);
//        handler1.handleRequest(pezho);

        /*Command*/
//        RowPrint rowPrint = new RowPrint();
//        ColumnPrint columnPrint = new ColumnPrint();
//        Car car = new Car("car", 5);
//        car.setPrintCommand(columnPrint);
//        try (PrintWriter writer = new PrintWriter(new FileWriter("carOutput.txt"))) {
//            car.print(writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        /*Iterator*/
//        Car car = new Car("car", 5);
//        Iterator<Car.Model> iterator = car.iterator();
//        while (iterator.hasNext()) System.out.println(iterator.next());

        /*Memento*/
//        Car car = new Car("car", 5);
//        Car.Memento memento = car.createMemento();
//        memento.setCar(car);
//        car.setMemento(memento);
//        car.removeModel("2");
//        car.removeModel("0");
//        System.out.println("Измененная модель");
//        StaticTasks.printMarkInfo(car);
//        car.setMemento(memento);
//        System.out.println("Изначальная модель");
//        StaticTasks.printMarkInfo(car);

        /*Strategy*/
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[0]));
//        int[] arr = new int[]{
//                1, 2, 6, 4, 1, 6,2,3,4,5,1
//        };
//        out.writeObject(arr);
//
//        String inputFile = args[0];
//        //CountingStrategy strategy = new LinearCountingStrategy();
//        CountingStrategy strategy = new StreamCountingStrategy();
//        ArrayCounter arrayCounter = new ArrayCounter(strategy);
//        Map<Integer, Integer> occurrences = arrayCounter.countOccurrences(inputFile);
//        if (occurrences != null) {
//            for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
//                System.out.println("Element: " + entry.getKey() + ", Count: " + entry.getValue());
//            }
//        }

        /*Template method*/
//        ShapeAnimationApp.main(args);

        /*Visitor*/
        Car car = new Car("car",5);
        Visitor visitor = new PrintVisitor();
        car.accept(visitor);

        Motorcycle motorcycle = new Motorcycle("moto", 3);
        motorcycle.accept(visitor);

    }

}

