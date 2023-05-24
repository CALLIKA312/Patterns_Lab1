package Patterns.Comand;

import Transports.Car;

import java.io.PrintWriter;
import java.io.Writer;

public interface iCommand {
    void execute(Car car, PrintWriter writer);
}
