package com.civil.stiff.estructuras.portico.algoritmoportico.matrix;

import com.civil.stiff.estructuras.transversales.InterfaceMatrixElemento;

import org.ejml.simple.SimpleMatrix;

public class RegidityMatrixPortico implements InterfaceMatrixElemento {
    private double l;
    private double b;
    private double h;
    private double e;
    private double i;
    private double a;
    private SimpleMatrix m;
    private double angulo;
    public RegidityMatrixPortico (double base, double altura, double longitud, double elasticidad, double angulo){
        this.e=elasticidad;
        this.l=longitud;
        this.b=base;
        this.h= altura;
        this.angulo= angulo;
        this.i= ((b*(Math.pow(h,3)))/12);
        this.a=(b*h);
    }
    @Override
    public SimpleMatrix calculate(){
        m= new SimpleMatrix(6,6);
        m.setRow(0,0,((e*a)*(1.0/l)),(0) ,(0),((-e*a)*(1.0/l)),(0),(0));
        m.setRow(1,0,(0), ((e*i)*(12.0/(Math.pow(l,3)))), ((e*i)*(6.0/(Math.pow(l,2)))),(0),((-e*i)*(12.0/(Math.pow(l,3)))),((e*i)*(6.0/(Math.pow(l,2)))));
        m.setRow(2,0,(0),((e*i)*(6.0/(Math.pow(l,2)))),((e*i)*(4/l)),(0),((-e*i)*(6.0/(Math.pow(l,2)))),((e*i)*(2/l)));
        m.setRow(3,0,((-e*a)*(1/l)),(0),(0),((e*a)*(1/l)),(0),(0));
        m.setRow(4,0, (0),((-e*i)*(12.0/(Math.pow(l,3)))), ((-e*i)*(6.0/(Math.pow(l,2)))),(0),((e*i)*(12.0/(Math.pow(l,3)))),((-e*i)*(6.0/(Math.pow(l,2)))));
        m.setRow(5,0, (0),((e*i)*(6.0/(Math.pow(l,2)))),((e*i)*(2/l)),(0),((-e*i)*(6.0/(Math.pow(l,2)))),((e*i)*(4/l)));
        return m;
    }

    public double getAngulo() {
        return angulo;
    }

}
