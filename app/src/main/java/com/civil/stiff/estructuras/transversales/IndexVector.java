package com.civil.stiff.estructuras.transversales;

import org.ejml.simple.SimpleMatrix;

import java.io.Serializable;

public class IndexVector implements Serializable {
    private IndexVector(){}
    private static SimpleMatrix v;
    public static SimpleMatrix calculate ( SimpleMatrix  r, Integer [] dgreeFree, int lengthVector){
        v= new SimpleMatrix(lengthVector,1);
        for(int i=0; i< dgreeFree.length; i++){
                 v.set(dgreeFree[i],0,r.get(i,0));
        }
        return v;
    }
}
