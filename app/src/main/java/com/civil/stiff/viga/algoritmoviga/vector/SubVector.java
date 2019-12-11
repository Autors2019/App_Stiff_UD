package com.civil.stiff.viga.algoritmoviga.vector;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class SubVector {
    private SubVector(){}
    private static SimpleMatrix m;
    public static SimpleMatrix calculate(ArrayList<Integer> a, SimpleMatrix k ){
        m= new SimpleMatrix(a.size(), 1);
        for(int i=0; i< m.numRows(); i++){
              m.set(i,0, k.get(a.get(i),0));
        }
        return m;
    }
}
