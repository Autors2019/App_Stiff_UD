package com.civil.stiff.verticales.viga.algoritmoviga.matrix;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class SubMatrix {

    private SubMatrix(){}
    private static SimpleMatrix m;
    public static SimpleMatrix calculate(ArrayList<Integer> a, ArrayList<Integer> b, SimpleMatrix k ){
        m= new SimpleMatrix(a.size(), b.size());
        for(int i=0; i< m.numRows(); i++){
            for(int t=0; t< m.numCols(); t++){
                m.set(i,t, k.get(a.get(i), b.get(t)));
            }
        }
        return m;
    }
}
