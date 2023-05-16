import Exeptions.DuplicateModelNameException;
import Interfaces.Transport;
import Interfaces.TransportFactory;
import Patterns.Decorator;
import Patterns.Fabric.AutoFactory;
import Transports.Car;
import Transports.Motorcycle;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StaticTasks {

    public static double getAveragePrice(Transport transport) {
        int[] prices = transport.getPricesOfVehicle();
        if (prices.length == 0) return 0;
        double averagePrice = 0;
        for (int price : prices) {
            averagePrice += price;
        }
        averagePrice /= prices.length;
        return averagePrice;
    }

    public static double getAveragePrice(Transport... transports) {
        double averagePrice = 0;
        for (Transport transport : transports) {
            int[] modelsPrice = transport.getPricesOfVehicle();
            double sum = 0;
            for (int price : modelsPrice) {
                sum += price;
            }
            averagePrice += sum / modelsPrice.length;
        }
        return averagePrice / transports.length;
    }

    private static void printModelNPrice(Transport transport) {
        for (int i = 0; i < transport.getModelsCount(); i++)
            System.out.printf("Модель %s стоит: %d%n",
                    transport.getModelsOfVehicle()[i],
                    transport.getPricesOfVehicle()[i]);
    }

    public static void printMarkInfo(Transport transport) {
        System.out.println(transport.getMark() + " " + transport.getModelsCount());
        printModelNPrice(transport);
    }

    public static void writeVehicleToByteFile(Transport transport, OutputStream out) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(out);

        dataOutputStream.write(transport.getMark().length());
        dataOutputStream.write(transport.getMark().getBytes());

        dataOutputStream.write(transport.getModelsCount());
        String[] vehicleModels = transport.getModelsOfVehicle();
        int[] vehicleModelsPrices = transport.getPricesOfVehicle();
        for (int i = 0; i < transport.getModelsCount(); i++) {
            dataOutputStream.write(vehicleModels[i].length());
            dataOutputStream.write(vehicleModels[i].getBytes());
            dataOutputStream.write(vehicleModelsPrices[i]);
        }
        System.out.println("Byte file has been written");
    }

    public static Transport readVehicleFromByteFile(InputStream in) throws IOException, DuplicateModelNameException {
        DataInputStream dataInputStream = new DataInputStream(in);

        String mark = "";
        int capacity = 0;
        int markNameLength = 0;
        Transport transport = null;
        while (dataInputStream.available() > 0) {
            markNameLength = in.read();
            byte[] markInByte = new byte[markNameLength];
            for (int i = 0; i < markNameLength; i++) {
                markInByte[i] = (byte) in.read();
            }
            mark = new String(markInByte);
            capacity = in.read();
            transport = new Car(mark, capacity);
            int modelNameLength = 0;
            int modelPrice = 0;
            for (int i = 0; i < capacity; i++) {
                modelNameLength = in.read();
                byte[] modelInByte = new byte[modelNameLength];
                for (int j = 0; j < modelNameLength; j++) {
                    modelInByte[j] = (byte) in.read();
                }
                modelPrice = in.read();
                transport.addModel(new String(modelInByte), modelPrice);
            }
        }
        System.out.println("myClass.Interfaces.Vehicle has been added from byte file");
        return transport;
    }

    public static void writeVehicleToSymbolFile(Transport transport, Writer out) throws IOException {
        PrintWriter printWriter = new PrintWriter(out);
        String writerString = "";

        writerString += transport.getId() + " ";
        //System.out.printf("%d ",vehicle.getId());
        writerString += transport.getMark() + " " + transport.getModelsCount() + " ";
        //System.out.printf("%s %d",vehicle.getMark(),vehicle.getModelsCount());


        String[] vehicleModels = transport.getModelsOfVehicle();
        int[] vehicleModelsPrices = transport.getPricesOfVehicle();

        for (int i = 0; i < transport.getModelsCount(); i++) {
            //System.out.printf(" %s %d",vehicleModels[i],vehicleModelsPrices[i]);
            writerString += vehicleModels[i] + " " + vehicleModelsPrices[i] + " ";
        }
        writerString = writerString.substring(0, writerString.length() - 1);

        printWriter.write(writerString);
        printWriter.flush();

        System.out.println("'\n'Symbol file has been written");
    }

    public static Transport readVehicleFromSymbolFile(Reader in) throws IOException, DuplicateModelNameException {
        BufferedReader bufferedReader = new BufferedReader(in);
        String lineFromSymbol = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(lineFromSymbol);


        int id = Integer.parseInt(stringTokenizer.nextToken());
        Transport transport = null;
        if (!stringTokenizer.hasMoreElements()) {
            return transport;
        }

        switch (id) {
            case 1 -> transport = new Car();
            case 2 -> transport = new Motorcycle();
        }
        String mark = stringTokenizer.nextToken();

        transport.setMark(mark);
        int capacity = Integer.parseInt(stringTokenizer.nextToken());

        String[] vehicleModels = new String[capacity];
        int[] vehicleModelsPrices = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            vehicleModels[i] = stringTokenizer.nextToken();
            vehicleModelsPrices[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        if (capacity == 0) return transport;
        for (int i = 0; i < capacity; i++) {
            transport.addModel(vehicleModels[i], vehicleModelsPrices[i]);
        }
        System.out.println("Interfaces.Vehicle has been added from symbol file");
        return transport;
    }

    public static void writeVehiclePrintf(Transport transport) {
        System.out.printf("%s ", transport.getId());
        int capacity = transport.getModelsCount();
        System.out.printf("%s %d ", transport.getMark(), transport.getModelsCount());
        for (int i = 0; i < capacity; i++) {
            System.out.printf("%s %d ", transport.getModelsOfVehicle()[i], transport.getPricesOfVehicle()[i]);
        }
        System.out.println("\nSymbol file has been written");
    }

    public static Transport readVehicleScanner() throws DuplicateModelNameException {
        Transport transport = null;
        Scanner in = new Scanner(System.in);

        System.out.println("Input id of your Interfaces.Vehicle: ");
        int id = Integer.parseInt(in.next());

        switch (id) {
            case 1 -> transport = new Car();
            case 2 -> transport = new Motorcycle();
        }
        System.out.println("Input mark of your Interfaces.Vehicle: ");
        transport.setMark(in.next());

        System.out.println("Input capacity of your Interfaces.Vehicle: ");
        int capacity = Integer.parseInt(in.next());

        String[] vehicleModels = new String[capacity];
        int[] vehicleModelsPrices = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            System.out.println("Input name of your model: ");
            vehicleModels[i] = in.next();
            System.out.println("Input capacity of your model: ");
            vehicleModelsPrices[i] = Integer.parseInt(in.next());
        }

        if (capacity == 0) return transport;
        for (int i = 0; i < capacity; i++) {
            transport.addModel(vehicleModels[i], vehicleModelsPrices[i]);
        }
        System.out.println("Interfaces.Vehicle has been added from symbol file");
        return transport;
    }

    public static Transport createVehicle(String mark, int capacity, Transport transport) {
        Class<?> aClass = transport.getClass();
        Constructor<?> constructor = null;
        Transport res = null;
        try {
            constructor = aClass.getConstructor(String.class, Integer.TYPE);
            res = (Transport) constructor.newInstance(mark, capacity);
        } catch (InstantiationException e) {
            e.printStackTrace();
            return res;
        } catch (IllegalAccessException e) {
            System.out.println("Метод недоступен");
            e.printStackTrace();
            return res;
        } catch (InvocationTargetException e) {
            System.out.println("При вызове возникло исключение");
            e.printStackTrace();
            return res;
        } catch (NoSuchMethodException e) {
            System.out.println("Метод не найден");
            e.printStackTrace();
            return res;
        }
        return res;

    }

    private static TransportFactory factory = new AutoFactory();

    public static void setTransportFactory(TransportFactory factory) {
        StaticTasks.factory = factory;
    }

    public static Transport createInstance(String mark, int size) throws DuplicateModelNameException {
        return factory.createInstance(mark, size);
    }

    public static Transport synchronizedTransport(Transport t) {
        Decorator td = new Decorator(t);
        return td.getTransport();
    }


}
