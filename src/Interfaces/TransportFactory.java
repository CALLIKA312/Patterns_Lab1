package Interfaces;

import Exeptions.DuplicateModelNameException;

import java.io.Serializable;

public interface TransportFactory extends Serializable {

    Transport createInstance(String mark, int capacity) throws DuplicateModelNameException;
}
