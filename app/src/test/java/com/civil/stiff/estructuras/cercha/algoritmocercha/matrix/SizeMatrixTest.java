package com.civil.stiff.estructuras.cercha.algoritmocercha.matrix;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SizeMatrixTest {
    ArrayList<Integer[]> ordenElementos= new ArrayList<>();
    @Before
    public void inicializar(){
        ordenElementos.add(new Integer[]{0,1,2,3});
        ordenElementos.add(new Integer[]{2,3,4,5});
        ordenElementos.add(new Integer[]{4,5,6,7});
        ordenElementos.add(new Integer[]{6,7,8,9});
        ordenElementos.add(new Integer[]{8,9,0,1});
    }
    @Test
    public void calculate() {
        SizeMatrixCercha sizeMatrixCercha = new SizeMatrixCercha(ordenElementos);
        assertEquals(10, sizeMatrixCercha.calculate());
    }
}