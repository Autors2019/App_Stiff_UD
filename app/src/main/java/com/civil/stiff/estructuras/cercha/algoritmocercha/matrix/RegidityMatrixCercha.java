package com.civil.stiff.estructuras.cercha.algoritmocercha.matrix;

import com.civil.stiff.estructuras.transversales.InterfaceMatrixElemento;

import org.ejml.simple.SimpleMatrix;

public class RegidityMatrixCercha implements InterfaceMatrixElemento {
    private double l;
    private double e;
    private double a;
    private SimpleMatrix m;
    private double angulo;
    public RegidityMatrixCercha (double area, double longitud, double elasticidad, double angulo){
        this.e=elasticidad;
        this.l=longitud;
        this.a= area;
        this.angulo= angulo;
    }
    @Override
    public SimpleMatrix calculate(){
        m= new SimpleMatrix(4,4);
        m.setRow(0,0, ((e*a)*(1/l)),(0),((-e*a)*(1/l)),(0));
        m.setRow(1,0, (0),(0),(0),(0));
        m.setRow(2,0, ((-e*a)*(1/l)),(0),((e*a)*(1/l)),(0));
        m.setRow(3,0, (0),(0),(0),(0));
        return m;
    }

    public double getAngulo() {
        return angulo;
    }
}
