package Patterns;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Singleton {
    private static Properties property;

    private Singleton() {
    }

    public synchronized static Properties getInstance() {
        if (property == null) {
            FileReader fileReader = null;
            try {
                fileReader = new FileReader("src/Resources/config.properties");
                property = new Properties();
                property.load(fileReader);
            } catch (IOException e) {
                System.out.println("Ошибка открытия файла");
                e.printStackTrace();
            }
        }else {
            System.out.println("Синглтон уже создан, больше нельзя");
        }
        return property;
    }

}
