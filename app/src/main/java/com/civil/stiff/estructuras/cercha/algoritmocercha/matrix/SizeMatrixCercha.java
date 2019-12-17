package com.civil.stiff.estructuras.cercha.algoritmocercha.matrix;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class SizeMatrixCercha {
    private ArrayList<Integer[]> ordenElementos;
    private Set<Integer> norepetidos= new TreeSet<>();
    public SizeMatrixCercha(ArrayList<Integer[]> ordenElementos){
        this.ordenElementos=ordenElementos;
    }
    public int calculate(){
        // Calcular elementos que no se repiten
        for(Integer[] elementos: ordenElementos){
            for(Integer i: elementos){
                norepetidos.add(i);
            }
        }
        return norepetidos.size();
    }
}
