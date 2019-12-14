package com.civil.stiff.estructuras.portico.algoritmoportico.vector;

import com.civil.stiff.estructuras.portico.algoritmoportico.matrix.RegidityMatrixPortico;

import org.ejml.simple.SimpleMatrix;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ConsolidatedVectorPorticoTest {
    int numeroElementos;
    ArrayList<Integer[]> ordenElementos;
    ArrayList<RegidityMatrixPortico> regidityMatrixPorticos;
    ArrayList<SimpleMatrix> vectoresFuerzasInt;
    SimpleMatrix vectorFuerzaInt1;
    SimpleMatrix vectorFuerzaInt2;
    SimpleMatrix vectorFuerzaInt3;
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
        vectorFuerzaInt1= new SimpleMatrix(6,1);
        vectorFuerzaInt2= new SimpleMatrix(6,1);
        vectorFuerzaInt3= new SimpleMatrix(6,1);
        vectorFuerzaInt1.setColumn(0,0,0,20,25,0,20,-25);
        vectorFuerzaInt2.setColumn(0,0,0,4.69,5.63,0,25.3,-16.9);
        vectorFuerzaInt3.setColumn(0,0,0,-40,-26.7,0,-40,26.7);
        vectoresFuerzasInt= new ArrayList<>();
        vectoresFuerzasInt.add(0, vectorFuerzaInt1);
        vectoresFuerzasInt.add(1, vectorFuerzaInt2);
        vectoresFuerzasInt.add(2, vectorFuerzaInt3);

    }
    @Test
    public void calculate() {
        try {
            SimpleMatrix k=ConsolidatedVectorPortico.calculate(numeroElementos,ordenElementos,vectoresFuerzasInt,regidityMatrixPorticos);
            k.print();
        }
       catch (Exception e){
            System.out.println(e);
        }
    }
}