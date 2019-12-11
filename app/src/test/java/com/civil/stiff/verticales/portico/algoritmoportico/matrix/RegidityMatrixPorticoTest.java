package com.civil.stiff.verticales.portico.algoritmoportico.matrix;

import org.ejml.simple.SimpleMatrix;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegidityMatrixPorticoTest {
    RegidityMatrixPortico matriz=null;

    @Before
    public void initMatrix(){
        matriz = new RegidityMatrixPortico(0.3,0.4,5,20000000, 45);

    }
    @Test
    public void calculate() {
       matriz.calculate().print();
    }
}