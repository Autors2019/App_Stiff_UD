package com.civil.stiff.estructuras.trasversales;

import com.civil.stiff.estructuras.viga.algoritmoviga.matrix.RegidityMatrix;

import org.ejml.simple.SimpleMatrix;

import java.io.Serializable;

public final class IndexMatrix implements Serializable {

    private static SimpleMatrix m;
    private static SimpleMatrix m1;
    private IndexMatrix(){}
    public static SimpleMatrix calculate(RegidityMatrix k, Integer [] dgreeFree, int lengthMatrix ){
        m= new SimpleMatrix(lengthMatrix, lengthMatrix);
        for(int i=0; i< k.calculate().numRows(); i++){
            for(int t=0; t< k.calculate().numCols(); t++){
                m.set(dgreeFree[i],dgreeFree[t], k.calculate().get(i,t));
            }
        }
        return  m;
    }
    public static SimpleMatrix calculate(SimpleMatrix k, Integer [] dgreeFree, int lengthMatrix ){
        m1= new SimpleMatrix(lengthMatrix, lengthMatrix);
        for(int i=0; i< k.numRows(); i++){
            for(int t=0; t< k.numCols(); t++){
                m1.set(dgreeFree[i],dgreeFree[t], k.get(i,t));
            }
        }
        return  m1;
    }
}
