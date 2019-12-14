package com.civil.stiff.verticales.portico.algoritmoportico;

import com.civil.stiff.verticales.portico.algoritmoportico.matrix.LongitudMatrizPortico;

import java.util.ArrayList;

public class CalVectA {
    // caclular vector A
    public static ArrayList<Integer> calculate(int numElementos, ArrayList<Integer> b) {
        ArrayList<Integer> a= new ArrayList<>();
        for(int i=0; i< LongitudMatrizPortico.calculate(numElementos); i++){
            a.add(i,i);
        }
        for(int i=0; i<b.size(); i++){
            a.remove(b.get(i));
        }
        return a;
}
}
