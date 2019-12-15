package com.civil.stiff.estructuras.transversales;

import org.ejml.simple.SimpleMatrix;

import java.io.Serializable;

public interface InterfaceMatrixElemento extends Serializable {

    SimpleMatrix calculate();
}
