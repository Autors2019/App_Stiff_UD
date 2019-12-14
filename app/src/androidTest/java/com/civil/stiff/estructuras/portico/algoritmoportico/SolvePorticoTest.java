package com.civil.stiff.estructuras.portico.algoritmoportico;
import android.util.Log;

import com.civil.stiff.estructuras.portico.algoritmoportico.matrix.ConsolidatedMatrixPortico;
import com.civil.stiff.estructuras.portico.algoritmoportico.matrix.RegidityMatrixPortico;
import com.civil.stiff.estructuras.portico.algoritmoportico.vector.ConsolidatedVectorPortico;

import org.ejml.simple.SimpleMatrix;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SolvePorticoTest {
    int numeroElementos;
    ArrayList<Integer[]> ordenElementos;
    ArrayList<RegidityMatrixPortico> regidityMatrixPorticos;
    ArrayList<SimpleMatrix> vectoresFuerzasInt;
    SimpleMatrix vectorFuerzaInt1;
    SimpleMatrix vectorFuerzaInt2;
    SimpleMatrix vectorFuerzaInt3;
    SimpleMatrix vectorConsolidado;
    SimpleMatrix vectorFuerzasExt;
    SimpleMatrix matrixConsolidada;
    SolvePortico solvePortico;
    ArrayList<Integer> a,b;
    @Before
    public void inicialaizacion(){
        numeroElementos=3;

        ordenElementos= new ArrayList<>();
        regidityMatrixPorticos= new ArrayList<>();
        ordenElementos.add(0, new Integer[]{0,1,2,3,4,5});
        ordenElementos.add(1, new Integer[]{3,4,5,6,7,8});
        ordenElementos.add(2, new Integer[]{9,10,11,6,7,8});
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
        a= new ArrayList<>();
        b= new ArrayList<>();
        a.add(0, 0);
        a.add(1, 1);
        a.add(2, 2);
        a.add(3, 9);
        a.add(4, 10);
        a.add(5, 11);
        b.add(0, 3);
        b.add(1, 4);
        b.add(2, 5);
        b.add(3, 6);
        b.add(4, 7);
        b.add(5, 8);
        vectorFuerzasExt= new SimpleMatrix(12,1);
        vectorFuerzasExt.setColumn(0,0, 0,0,0,0,-20,30,0,0,0,0,0,0);
        // calcular vector consolidado
        try {
            matrixConsolidada = ConsolidatedMatrixPortico.calculate(numeroElementos, ordenElementos, regidityMatrixPorticos);
        }
        catch (Exception e){
            System.out.println(e);
        }

        try {
            vectorConsolidado = ConsolidatedVectorPortico.calculate(numeroElementos,ordenElementos,vectoresFuerzasInt,regidityMatrixPorticos);
        }
        catch (Exception e){
            System.out.println(e);
        }

        solvePortico= new SolvePortico(a,b,ordenElementos,vectoresFuerzasInt,vectorConsolidado,vectorFuerzasExt,regidityMatrixPorticos, matrixConsolidada);
    }


    @Test
    public void getF_a() {
        SimpleMatrix P_a= solvePortico.getP_a();
        Log.i("F_a",P_a.toString());

    }
    @Test
    public void getD_b() {
        SimpleMatrix D_b= solvePortico.getD_b();
        Log.i("D_b",D_b.toString());
    }

    @Test
    public void getD() {
        SimpleMatrix D= solvePortico.getD();
        Log.i("D",D.toString());
    }

    @Test
    public void getReacciones() {

        ArrayList<SimpleMatrix> reacciones = solvePortico.getReacciones();
        for(SimpleMatrix m: reacciones){
            Log.i("reaccion", m.toString());
        }
    }

}