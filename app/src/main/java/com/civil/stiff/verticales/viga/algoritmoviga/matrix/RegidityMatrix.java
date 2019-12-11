package com.civil.stiff.verticales.viga.algoritmoviga.matrix;

import com.civil.stiff.verticales.trasversales.InterfaceMatrixElemento;

import org.ejml.simple.SimpleMatrix;

import java.io.Serializable;

public class RegidityMatrix implements InterfaceMatrixElemento {
    private double l;
    private double b;
    private double h;
    private double e;
    private double i;
    private SimpleMatrix m;
    public RegidityMatrix(double base, double altura, double longitud, double elasticidad){
        this.e=elasticidad;
        this.l=longitud;
        this.b=base;
        this.h= altura;
        this.i= ((b*(Math.pow(h,3)))/12);
    }
    @Override
    public SimpleMatrix calculate(){
         m= new SimpleMatrix(4,4);
         m.setRow(0,0, (e*i*(12.0/Math.pow(l,3))), (e*i*(6.0/Math.pow(l,2))), (-e*i*(12.0/Math.pow(l,3))), (e*i*(6.0/Math.pow(l,2))));
         m.setRow(1,0, (e*i*(6.0/Math.pow(l,2))), (e*i*(4.0/l)), (-e*i*(6.0/Math.pow(l,2))), (e*i*(2.0/l)));
         m.setRow(2,0, (-e*i*(12.0/Math.pow(l,3))), (-e*i*(6.0/Math.pow(l,2))), (e*i*(12.0/Math.pow(l,3))), (-e*i*(6.0/Math.pow(l,2))));
         m.setRow(3,0, (e*i*(6.0/Math.pow(l,2))), (e*i*(2.0/l)), (-e*i*(6.0/Math.pow(l,2))), (e*i*(4.0/l)));
         return  m;
    }
}
