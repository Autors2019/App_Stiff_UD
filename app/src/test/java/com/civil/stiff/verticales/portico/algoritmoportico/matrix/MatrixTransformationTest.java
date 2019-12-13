package com.civil.stiff.verticales.portico.algoritmoportico.matrix;

import org.ejml.simple.SimpleMatrix;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTransformationTest {

    @Test
    public void calculate() {
        SimpleMatrix simpleMatrix= MatrixTransformation.calculate(53.13);
        simpleMatrix.print();
    }
}