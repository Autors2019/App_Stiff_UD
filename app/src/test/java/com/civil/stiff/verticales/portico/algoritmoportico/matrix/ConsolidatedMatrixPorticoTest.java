package com.civil.stiff.verticales.portico.algoritmoportico.matrix;

import org.ejml.simple.SimpleMatrix;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ConsolidatedMatrixPorticoTest {
    int numeroElementos;
    ArrayList<Integer[]> ordenElementos;
    ArrayList<RegidityMatrixPortico> regidityMatrixPorticos;
    @Before
    public void inicializacion(){
        numeroElementos=3;
        ordenElementos= new ArrayList<>();
        regidityMatrixPorticos= new ArrayList<>();
        ordenElementos.add(0, new Integer[]{6,7,8,0,1,2});
        ordenElementos.add(1, new Integer[]{0,1,2,3,4,5});
        ordenElementos.add(2, new Integer[]{9,10,11,3,4,5});
        regidityMatrixPorticos.add(0, new RegidityMatrixPortico(0.3,0.4,5,20000000,53.13));
        regidityMatrixPorticos.add(1, new RegidityMatrixPortico(0.3,0.35,4,20000000,0.0));
        regidityMatrixPorticos.add(2, new RegidityMatrixPortico(0.3,0.4,4,20000000,90.0));
    }

    @Test
    public void calculate() {
        regidityMatrixPorticos.forEach(s->s.calculate().print());
        try {
            SimpleMatrix k = ConsolidatedMatrixPortico.calculate(numeroElementos, ordenElementos, regidityMatrixPorticos);
            k.print();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}