package com.civil.stiff.estructuras.portico.algoritmoportico.matrix;

import org.junit.Before;
import org.junit.Test;

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