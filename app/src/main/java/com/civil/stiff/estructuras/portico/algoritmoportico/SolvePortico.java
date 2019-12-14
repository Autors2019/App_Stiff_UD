package com.civil.stiff.estructuras.portico.algoritmoportico;

import android.util.Log;

import com.civil.stiff.estructuras.portico.algoritmoportico.matrix.LongitudMatrizPortico;
import com.civil.stiff.estructuras.portico.algoritmoportico.matrix.MatrixTransformation;
import com.civil.stiff.estructuras.portico.algoritmoportico.matrix.RegidityMatrixPortico;
import com.civil.stiff.estructuras.trasversales.IndexVector;
import com.civil.stiff.estructuras.trasversales.SubMatrix;
import com.civil.stiff.estructuras.trasversales.SubVector;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class SolvePortico {
    private ArrayList<Integer> a,b, orderElementosU;
    private ArrayList<Integer[]> ordenElementos;
    private ArrayList<SimpleMatrix> vectoresFuerzasInt;
    private SimpleMatrix vectorConsolidado, vectorFuerzasExt,matrizConsolidada, K_aa, K_ab, K_ba, K_bb, R_a, R_b, P_a, P_b, D_b, D;
    private ArrayList<RegidityMatrixPortico> regidityMatrixPorticos;
    private ArrayList<SimpleMatrix> reacciones= new ArrayList<>();
    public SolvePortico(ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer[]> ordenElementos , ArrayList<SimpleMatrix> vectoresFuerzasInt, SimpleMatrix vectorConsolidado, SimpleMatrix vectorFuerzasExt, ArrayList<RegidityMatrixPortico> regidityMatrixPorticos, SimpleMatrix matrizConsolidada){
        this.a=a;
        this.b=b;
        this.ordenElementos=ordenElementos;
        this.vectoresFuerzasInt=vectoresFuerzasInt;
        this.vectorConsolidado= vectorConsolidado;
        this.vectorFuerzasExt= vectorFuerzasExt;
        this.regidityMatrixPorticos= regidityMatrixPorticos;
        this.matrizConsolidada=matrizConsolidada;
        // Calcular sub matrices
        calculateSubMatrix();
        // Calcular sub vectores;
        calculateSubVector();
        calculoIncognitas();
    }
    private void calculateSubMatrix(){
        K_aa= SubMatrix.calculate(a,a,matrizConsolidada);
        K_ab= SubMatrix.calculate(a,b,matrizConsolidada);
        K_ba= SubMatrix.calculate(b,a,matrizConsolidada);
        K_bb= SubMatrix.calculate(b,b,matrizConsolidada);
        //Log calculo de submatrices
        Log.i("K_aa",K_aa.toString());
        Log.i("K_ab",K_ab.toString());
        Log.i("K_ba",K_ba.toString());
        Log.i("K_bb",K_bb.toString());
    }
    private void calculateSubVector(){
        R_a=SubVector.calculate(a,vectorConsolidado);
        R_b=SubVector.calculate(b,vectorConsolidado);
        P_a=SubVector.calculate(a, vectorFuerzasExt);
        P_b=SubVector.calculate(b, vectorFuerzasExt);
        //Log calculo de submatrices
        Log.i("R_a",R_a.toString());
        Log.i("R_b",R_b.toString());
        Log.i("P_a",P_a.toString());
        Log.i("P_b",P_b.toString());
    }
    private void calculoIncognitas(){
        // Calculo grados libertad D_b
        D_b= K_bb.invert().mult(P_b.minus(R_b));
        Log.i("Portico: calculoIncognitas: D_b=", D_b.toString());
        // Calculo grados libertad P_a
        P_a= K_ab.mult(D_b).plus(R_a);
        Log.i(" Portico: calculoIncognitas: P_a=", P_a.toString());
        // Calculo grados libertad D
        D= IndexVector.calculate(D_b,  b.toArray(new Integer[b.size()]), LongitudMatrizPortico.calculate(regidityMatrixPorticos.size()));
        Log.i("Portico: calculoIncognitas: D=", D.toString());
        for(int i=0; i< ordenElementos.size();i++){
            orderElementosU= new ArrayList<>();
            for(int t=0; t< ordenElementos.get(i).length; t++){
                orderElementosU.add(t, ordenElementos.get(i)[t]);
            }
            //Calculo reaaciones
            reacciones.add(regidityMatrixPorticos.get(i).calculate().mult(MatrixTransformation.calculate(regidityMatrixPorticos.get(i).getAngulo())).mult(SubVector.calculate(orderElementosU, D)).plus(vectoresFuerzasInt.get(i)));
        }
        Log.i("Portico: calculoIncognitas: reacciIntP=", reacciones.toString());

    }

    //Getter's
    public SimpleMatrix getP_a() {
        return P_a;
    }

    public SimpleMatrix getD_b() {
        return D_b;
    }

    public SimpleMatrix getD() {
        return D;
    }

    public ArrayList<SimpleMatrix> getReacciones() {
        return reacciones;
    }
}
