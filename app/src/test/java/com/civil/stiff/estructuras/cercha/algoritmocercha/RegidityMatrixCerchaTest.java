package com.civil.stiff.estructuras.cercha.algoritmocercha;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegidityMatrixCerchaTest {
    RegidityMatrixCercha regidityMatrixCercha;
    double e=200000000, a=0.001, l=5, angulo=53.13;

    @Test
    public void calculate() {
        regidityMatrixCercha= new RegidityMatrixCercha(a,l,e,angulo);
        regidityMatrixCercha.calculate().print();
    }

    @Test
    public void getAngulo() {
        regidityMatrixCercha= new RegidityMatrixCercha(a,l,e,angulo);
        assertEquals(53.13, regidityMatrixCercha.getAngulo(),0.1);
    }
}