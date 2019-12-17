package com.civil.stiff.estructuras.cercha.algoritmocercha.matrix;

import org.ejml.simple.SimpleMatrix;

public class MatrixTransformationCercha {
    private static SimpleMatrix matrixTransformation;
    private static double eta;
    private static double mu;
    private MatrixTransformationCercha(){}
    public static SimpleMatrix calculate(double beta){
        matrixTransformation= new SimpleMatrix(4,4);
        eta= Math.cos(Math.toRadians(beta));
        mu= Math.sin(Math.toRadians(beta));
        // calculate Matrix
        matrixTransformation.setRow(0,0,eta,mu);
        matrixTransformation.setRow(1,0,(-1)*mu,eta);
        matrixTransformation.setRow(2,2,eta,mu);
        matrixTransformation.setRow(3,2,(-1)*mu,eta);

        return  matrixTransformation;
    }
}
