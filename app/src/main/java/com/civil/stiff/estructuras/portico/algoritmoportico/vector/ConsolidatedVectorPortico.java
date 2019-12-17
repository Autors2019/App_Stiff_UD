package com.civil.stiff.estructuras.portico.algoritmoportico.vector;

import com.civil.stiff.estructuras.portico.algoritmoportico.matrix.LongitudMatrizPortico;
import com.civil.stiff.estructuras.portico.algoritmoportico.matrix.MatrixTransformationPortico;
import com.civil.stiff.estructuras.portico.algoritmoportico.matrix.RegidityMatrixPortico;
import com.civil.stiff.estructuras.transversales.IndexVector;


import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class ConsolidatedVectorPortico {
    private ConsolidatedVectorPortico(){}
    public static SimpleMatrix calculate(int numeroElementos, ArrayList<Integer[]> ordenElementos, ArrayList<SimpleMatrix> vectoresFuerzasInt, ArrayList<RegidityMatrixPortico> regidityMatrixPorticos) throws Exception{
        // SimpleMatrix
        SimpleMatrix k= new SimpleMatrix(LongitudMatrizPortico.calculate(numeroElementos), 1);
        //Calcular vector consolidada
        for(int i=0; i<vectoresFuerzasInt.size(); i++){
            SimpleMatrix K_n= MatrixTransformationPortico.calculate(regidityMatrixPorticos.get(i).getAngulo()).transpose().mult(vectoresFuerzasInt.get(i));
            SimpleMatrix Delta_Kn= IndexVector.calculate(K_n, ordenElementos.get(i), LongitudMatrizPortico.calculate(numeroElementos));
            k= k.plus(Delta_Kn);
        }
        return k;

    }
}
