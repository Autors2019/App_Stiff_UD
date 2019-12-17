package com.civil.stiff.estructuras.portico.algoritmoportico.matrix;

import org.ejml.simple.SimpleMatrix;
import org.junit.Test;

public class MatrixTransformationTest {

    @Test
    public void calculate() {
        SimpleMatrix simpleMatrix= MatrixTransformationPortico.calculate(53.13);
        simpleMatrix.print();
    }
}