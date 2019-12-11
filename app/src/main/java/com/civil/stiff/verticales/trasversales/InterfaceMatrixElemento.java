package com.civil.stiff.verticales.trasversales;

import org.ejml.simple.SimpleMatrix;

import java.io.Serializable;

public interface InterfaceMatrixElemento extends Serializable {

    SimpleMatrix calculate();
}
