package com.civil.stiff.estructuras.cercha.algoritmocercha.matrix;



import com.civil.stiff.estructuras.transversales.IndexMatrix;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class ConsolidatedMatrixCercha {
    private ConsolidatedMatrixCercha(){}
    public static SimpleMatrix calculate( ArrayList<Integer[]> ordenElementos, ArrayList<RegidityMatrixCercha> regidityMatrixCerchas ) throws Exception{
        // SimpleMatrix
        int size= new SizeMatrixCercha(ordenElementos).calculate();
        SimpleMatrix k= new SimpleMatrix(size, size);
        //Calcular matrix consolidada
        for(int i=0; i<regidityMatrixCerchas.size(); i++){
            SimpleMatrix K_n=MatrixTransformationCercha.calculate(regidityMatrixCerchas.get(i).getAngulo()).transpose().mult(regidityMatrixCerchas.get(i).calculate()).mult(MatrixTransformationCercha.calculate(regidityMatrixCerchas.get(i).getAngulo()));
            SimpleMatrix Delta_Kn= IndexMatrix.calculate(K_n, ordenElementos.get(i),size);
            k= k.plus(Delta_Kn);
        }
        return k;
    }
}
