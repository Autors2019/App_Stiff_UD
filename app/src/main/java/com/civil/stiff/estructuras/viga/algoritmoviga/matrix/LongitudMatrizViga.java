package com.civil.stiff.estructuras.viga.algoritmoviga.matrix;

import java.util.ArrayList;

public class LongitudMatrizViga {
    private LongitudMatrizViga(){}
    public static int calculate(ArrayList<Integer[]> arrayOrdenElementos){
        int longitud=0;
        switch (arrayOrdenElementos.size()){
            case 2: longitud=6;
                break;
            case 3: longitud=8;
                break;
            case 4: longitud=10;
                break;
            default:
                longitud=0;
        }
        return longitud;
    }
}
