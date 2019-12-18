package com.civil.stiff.estructuras.cercha.algoritmocercha;

import android.util.Log;

import com.civil.stiff.estructuras.cercha.algoritmocercha.matrix.MatrixTransformationCercha;
import com.civil.stiff.estructuras.cercha.algoritmocercha.matrix.RegidityMatrixCercha;
import com.civil.stiff.estructuras.cercha.algoritmocercha.matrix.SizeMatrixCercha;
import com.civil.stiff.estructuras.transversales.IndexVector;
import com.civil.stiff.estructuras.transversales.SubMatrix;
import com.civil.stiff.estructuras.transversales.SubVector;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class SolveCercha {
    private ArrayList<Integer> a,b, orderElementosU;
    private ArrayList<Integer[]> ordenElementos;
    private SimpleMatrix vectorFuerzasExt,matrizConsolidada, K_aa, K_ab, K_ba, K_bb,P_a, P_b, D_b, D;
    private ArrayList<RegidityMatrixCercha> regidityMatrixCerchas;
    private ArrayList<SimpleMatrix> reacciones= new ArrayList<>();
    public SolveCercha(ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer[]> ordenElementos , SimpleMatrix vectorFuerzasExt, ArrayList<RegidityMatrixCercha> regidityMatrixCerchas, SimpleMatrix matrizConsolidada){
        this.a=a;
        this.b=b;
        this.ordenElementos=ordenElementos;
        this.vectorFuerzasExt= vectorFuerzasExt;
        this.regidityMatrixCerchas= regidityMatrixCerchas;
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
        P_a=SubVector.calculate(a, vectorFuerzasExt);
        P_b=SubVector.calculate(b, vectorFuerzasExt);
        //Log calculo de submatrices
        Log.i("P_a",P_a.toString());
        Log.i("P_b",P_b.toString());
    }
    private void calculoIncognitas(){
        // Calculo grados libertad D_b
        D_b= K_bb.invert().mult(P_b);
        Log.i("Cercha: calculoIncognitas: D_b=", D_b.toString());
        // Calculo grados libertad P_a
        P_a= K_ab.mult(D_b);
        Log.i(" Cercha: calculoIncognitas: P_a=", P_a.toString());
        // Calculo grados libertad D
        D= IndexVector.calculate(D_b,  b.toArray(new Integer[b.size()]), new SizeMatrixCercha(ordenElementos).calculate());
        Log.i("Cercha: calculoIncognitas: D=", D.toString());
        for(int i=0; i< ordenElementos.size();i++){
            orderElementosU= new ArrayList<>();
            for(int t=0; t< ordenElementos.get(i).length; t++){
                orderElementosU.add(t, ordenElementos.get(i)[t]);
            }
            //Calculo reacciones
            reacciones.add(regidityMatrixCerchas.get(i).calculate().mult(MatrixTransformationCercha.calculate(regidityMatrixCerchas.get(i).getAngulo())).mult(SubVector.calculate(orderElementosU,D)));
        }
       Log.i("Cercha: calculoIncognitas: reacciIntP=", reacciones.toString());

    }

    //Getter's
    public SimpleMatrix getF_a() {
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
