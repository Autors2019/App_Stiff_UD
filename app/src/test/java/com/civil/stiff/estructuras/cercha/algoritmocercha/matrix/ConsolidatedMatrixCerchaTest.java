package com.civil.stiff.estructuras.cercha.algoritmocercha.matrix;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ConsolidatedMatrixCerchaTest {
    ArrayList<Integer[]> ordenElementos;
    ArrayList<RegidityMatrixCercha> regidityMatrixCerchas;
    @Before
    public void inicializar(){
        ordenElementos= new ArrayList<>();
        regidityMatrixCerchas= new ArrayList<>();
        ordenElementos.add(new Integer[]{0,1,2,3});
        ordenElementos.add(new Integer[]{0,1,4,5});
        ordenElementos.add(new Integer[]{4,5,6,7});
        ordenElementos.add(new Integer[]{2,3,6,7});
        ordenElementos.add(new Integer[]{4,5,2,3});
        regidityMatrixCerchas.add( new RegidityMatrixCercha(0.001,5,200000000,36.87));
        regidityMatrixCerchas.add( new RegidityMatrixCercha(0.001,4,200000000,0.0));
        regidityMatrixCerchas.add( new RegidityMatrixCercha(0.001,4,200000000,0.0));
        regidityMatrixCerchas.add( new RegidityMatrixCercha(0.001,5,200000000,-36.87));
        regidityMatrixCerchas.add( new RegidityMatrixCercha(0.001,3,200000000,90.0));
    }

    @Test
    public void calculate() {
        try {
            ConsolidatedMatrixCercha.calculate(ordenElementos,regidityMatrixCerchas).print();
        }
       catch (Exception e){
            System.out.println(e);
       }

    }
}