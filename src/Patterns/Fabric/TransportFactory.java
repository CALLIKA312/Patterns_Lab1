package Patterns.Fabric;

import Exeptions.DuplicateModelNameException;
import Transports.Transport;

import java.io.Serializable;

public interface TransportFactory extends Serializable {

    Transport createInstance(String mark, int capacity) throws DuplicateModelNameException;
}
