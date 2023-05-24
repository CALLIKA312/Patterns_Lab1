package Patterns.СhainOfResponsibility;

import Transports.Transport;

public class RowHandler implements COFHandler {

    private COFHandler nextHandler;

    @Override
    public void setNextHandler(COFHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(Transport t) {
        if (t.getModelsCount() <= 3) for (String s : t.getModelsNamesOfVehicle()) System.out.print(s + ' ');
        else if (nextHandler != null) nextHandler.handleRequest(t);
    }
}
