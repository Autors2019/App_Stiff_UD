package com.civil.stiff.estructuras.portico.algoritmoportico.matrix;

import org.ejml.simple.SimpleMatrix;

public class MatrixTransformationPortico {
    private static SimpleMatrix matrixTransformation;
    private static double eta;
    private static double mu;
    private MatrixTransformationPortico(){}
    public static SimpleMatrix calculate(double beta){
        matrixTransformation= new SimpleMatrix(6,6);
        eta= Math.cos(Math.toRadians(beta));
        mu= Math.sin(Math.toRadians(beta));
        // calculate Matrix
        matrixTransformation.setRow(0,0,eta,mu);
        matrixTransformation.setRow(1,0,(-1)*mu,eta);
        matrixTransformation.setRow(2,2,1);
        matrixTransformation.setRow(3,3,eta,mu);
        matrixTransformation.setRow(4,3,(-1)*mu,eta);
        matrixTransformation.setRow(5,5,1);
        return  matrixTransformation;
    }
}
