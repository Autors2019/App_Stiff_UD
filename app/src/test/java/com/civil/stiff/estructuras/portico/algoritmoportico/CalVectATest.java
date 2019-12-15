package com.civil.stiff.estructuras.portico.algoritmoportico;

import com.civil.stiff.estructuras.portico.algoritmoportico.matrix.LongitudMatrizPortico;
import com.civil.stiff.estructuras.transversales.CalVectA;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CalVectATest {
    ArrayList<Integer> b= new ArrayList<>();
    int numeroElementos=3;
    @Before
    public void inicializacion(){
        b.add(0,3);
        b.add(1,4);
        b.add(2,5);
        b.add(3,6);
        b.add(4,7);
        b.add(5,8);

    }
    @Test
    public void calcularVectoresGradosLibertad() {
         ArrayList<Integer> vectorA= CalVectA.calculate(LongitudMatrizPortico.calculate(numeroElementos), b);
         vectorA.forEach(i-> System.out.println(i));
         assertEquals(new Integer[]{0,1,2,9,10,11},vectorA.toArray(new Integer[vectorA.size()]));
    }
}