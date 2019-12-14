package com.civil.stiff.estructuras.portico.algoritmoportico.matrix;



import com.civil.stiff.estructuras.trasversales.IndexMatrix;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class ConsolidatedMatrixPortico {

    public static SimpleMatrix calculate(int numeroElementos, ArrayList<Integer[]> ordenElementos, ArrayList<RegidityMatrixPortico> regidityMatrixPorticos ) throws Exception{
        // SimpleMatrix
        SimpleMatrix k= new SimpleMatrix(LongitudMatrizPortico.calculate(numeroElementos), LongitudMatrizPortico.calculate(numeroElementos));
        //Calcular matrix consolidada
        for(int i=0; i<regidityMatrixPorticos.size(); i++){
            SimpleMatrix K_n=MatrixTransformation.calculate(regidityMatrixPorticos.get(i).getAngulo()).transpose().mult(regidityMatrixPorticos.get(i).calculate()).mult(MatrixTransformation.calculate(regidityMatrixPorticos.get(i).getAngulo()));
            SimpleMatrix Delta_Kn= IndexMatrix.calculate(K_n, ordenElementos.get(i),LongitudMatrizPortico.calculate(numeroElementos));
            k= k.plus(Delta_Kn);
        }
        return k;
    }
}
