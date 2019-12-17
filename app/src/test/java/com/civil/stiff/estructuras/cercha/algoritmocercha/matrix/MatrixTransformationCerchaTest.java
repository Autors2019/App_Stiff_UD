package com.civil.stiff.estructuras.cercha.algoritmocercha.matrix;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTransformationCerchaTest {
    double angulo= -36.87;
    @Test
    public void calculate() {
        MatrixTransformationCercha.calculate(angulo).print();
    }
}