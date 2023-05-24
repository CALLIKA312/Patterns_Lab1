package Patterns.СhainOfResponsibility;

import Transports.Transport;

public interface COFHandler {
    void setNextHandler(COFHandler handler);
    void handleRequest(Transport t);
}
