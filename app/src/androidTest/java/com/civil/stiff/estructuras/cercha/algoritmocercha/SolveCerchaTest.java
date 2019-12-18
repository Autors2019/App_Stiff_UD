package com.civil.stiff.estructuras.cercha.algoritmocercha;

import com.civil.stiff.estructuras.cercha.algoritmocercha.matrix.ConsolidatedMatrixCercha;
import com.civil.stiff.estructuras.cercha.algoritmocercha.matrix.RegidityMatrixCercha;

import org.ejml.simple.SimpleMatrix;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class SolveCerchaTest {
    ArrayList<Integer[]> ordenElementos;
    ArrayList<RegidityMatrixCercha> regidityMatrixCerchas;
    SimpleMatrix matrixConsolidada, vectorFuerzasExt;
    SolveCercha solveCercha;
    ArrayList<Integer> a,b;
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

        try {
            matrixConsolidada= ConsolidatedMatrixCercha.calculate(ordenElementos,regidityMatrixCerchas);
        }
        catch (Exception e){
            System.out.println(e);
        }
        a= new ArrayList<>();
        b= new ArrayList<>();
        a.add(0);
        a.add(1);
        a.add(7);

        b.add(6);
        b.add(2);
        b.add(3);
        b.add(4);
        b.add(5);
        vectorFuerzasExt= new SimpleMatrix(8,1);
        vectorFuerzasExt.setColumn(0,0, 0,0,20,30,0,-50,0,0);
        solveCercha= new SolveCercha(a,b,ordenElementos, vectorFuerzasExt, regidityMatrixCerchas, matrixConsolidada);

    }

    @Test
    public void getP_a() {
        solveCercha.getF_a().print();
    }

    @Test
    public void getD_b() {
        solveCercha.getD_b().print();
    }

    @Test
    public void getD() {
        solveCercha.getD().print();
    }

    @Test
    public void getReacciones() {
        solveCercha.getReacciones().forEach(s -> s.print());
    }

}