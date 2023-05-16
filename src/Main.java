import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import Patterns.DAO.MotorcycleDAO;
import Transports.Car;
import Transports.Motorcycle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            StaticTasks.printMarkInfo((Interfaces.Transport) obj);

            ((Interfaces.Transport) obj).changeModelName("Fazer", "tmp_Name");
            //((Interfaces.Vehicle) obj).removeModel("Niken");
            ((Interfaces.Transport) obj).removeModel("tmp_Name");
            StaticTasks.printMarkInfo((Interfaces.Transport) obj);
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
//        CarDAO carDAO = new CarDAO();

//        List<Car> cars = new ArrayList<>();
//        Car pezho = new Car("pezho", 5);
//        Car asd = new Car("asd", 5);
//        cars.add(pezho);
//        cars.add(asd);
//        carDAO.saveAll(cars);
//        carDAO.save(pezho);

//        Car newPezho = carDAO.get("asd");
//        StaticTasks.printMarkInfo(newPezho);

//        List<Car> cars = carDAO.getAll();
//        for (Car car : cars) StaticTasks.printMarkInfo(car);
//        carDAO.delete("asd");


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



    }

}

