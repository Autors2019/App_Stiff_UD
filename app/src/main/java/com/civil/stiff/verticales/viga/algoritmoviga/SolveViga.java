package com.civil.stiff.verticales.viga.algoritmoviga;





import android.util.Log;

import com.civil.stiff.verticales.viga.algoritmoviga.matrix.RegidityMatrix;
import com.civil.stiff.verticales.viga.algoritmoviga.matrix.SubMatrix;
import com.civil.stiff.verticales.viga.algoritmoviga.vector.IndexVector;
import com.civil.stiff.verticales.viga.algoritmoviga.vector.SubVector;

import org.ejml.data.SingularMatrixException;
import org.ejml.simple.SimpleMatrix;


import java.util.ArrayList;

public final class SolveViga  {

    private ArrayList<Integer> a=null;
    private ArrayList<Integer> b=null;
    private ArrayList<Integer[]> arrayOrdenElementos=null;
    private SimpleMatrix vectorConsolidadoFuerzasInt= null, vectorFuerzasExt= null, matrizConsolidada= null,
            m_aa, m_ab,m_ba,m_bb, vInt_a, vInt_b,vExt_a,vExt_b,D_b, P_a, D;
    private ArrayList<SimpleMatrix> vectoresFuerzasInternas, reacciIntP;
    private ArrayList<RegidityMatrix> matricesRegidez ;


    public SolveViga(ArrayList<Integer> a, ArrayList<Integer> b,
                     ArrayList<Integer[]> arrayOrdenElementos,
                     SimpleMatrix vectorConsolidadoFuerzasInt,
                     ArrayList<SimpleMatrix> vectoresFuerzasInternas,
                     SimpleMatrix vectorFuerzasExt,ArrayList<RegidityMatrix> matricesRegidez,
                     SimpleMatrix matrizConsolidada) throws SingularMatrixException{
        this.a=a;
        this.b=b;
        this.arrayOrdenElementos= arrayOrdenElementos;
        this.vectorConsolidadoFuerzasInt= vectorConsolidadoFuerzasInt;
        this.vectoresFuerzasInternas= vectoresFuerzasInternas;
        this.vectorFuerzasExt= vectorFuerzasExt;
        this.matricesRegidez= matricesRegidez;
        this.matrizConsolidada= matrizConsolidada;
        calcularSubMatrices();
        calcularSubVectores();
        calculoIncognitas();
    }

    private void calcularSubMatrices()
    {
        // Calcular subMatrices
        m_aa= SubMatrix.calculate(a,a,matrizConsolidada);
        m_ab= SubMatrix.calculate(a,b,matrizConsolidada);
        m_ba= SubMatrix.calculate(b,a,matrizConsolidada);
        m_bb= SubMatrix.calculate(b,b,matrizConsolidada);

    }
    private void calcularSubVectores(){
        // Calcular SubVectores
        vInt_a= SubVector.calculate(a,vectorConsolidadoFuerzasInt);
        vInt_b= SubVector.calculate(b,vectorConsolidadoFuerzasInt);
        vExt_a= SubVector.calculate(a,vectorFuerzasExt);
        vExt_b= SubVector.calculate(b,vectorFuerzasExt);
    }
    private int calculoLongitud(){
        // Calculo de longitud matrices
        int longitud=0;
        switch (arrayOrdenElementos.size()){

            case 2: longitud=6;
                break;
            case 3: longitud=8;
                break;
            case 4: longitud=10;
                break;
            default:
                longitud=0;
        }
        return longitud;
    }
    private void calculoIncognitas() {
        reacciIntP= new ArrayList<>();
        ArrayList<Integer> orderElementos;
        // Calculo grados libertad D_b
        D_b = m_bb.invert().mult(vExt_b.minus(vInt_b));
        Log.i("Viga: calculoIncognitas: D_b=", D_b.toString());
        // Calculo grados libertad D
        D= IndexVector.calculate(D_b, b.toArray(new Integer[b.size()]), calculoLongitud());
        Log.i("Viga: calculoIncognitas: D=", D.toString());
        // Calculo grados libertad P_a
        P_a = m_ab.mult(D_b).plus(vInt_a);
        Log.i("calculoIncognitas: P_a=", P_a.toString());

        for(int i=0; i< arrayOrdenElementos.size(); i++){
            orderElementos= new ArrayList<>();
            for(int t=0; t< arrayOrdenElementos.get(i).length; t++){
                    orderElementos.add(t, arrayOrdenElementos.get(i)[t]);
                }
                // Calculo grados libertad reacciIntP
                reacciIntP.add(i, matricesRegidez.get(i).calculate().mult(SubVector.calculate(orderElementos,D)).plus(vectoresFuerzasInternas.get(i)));
            }
        Log.i("Viga: calculoIncognitas: reacciIntP=", reacciIntP.toString());
    }
    // Getter´s
    public SimpleMatrix getD_b() {
        return D_b;
    }

    public SimpleMatrix getP_a() {
        return P_a;
    }

    public SimpleMatrix getD() {
        return D;
    }

    public ArrayList<SimpleMatrix> getReacciIntP() {
        return reacciIntP;
    }
}
