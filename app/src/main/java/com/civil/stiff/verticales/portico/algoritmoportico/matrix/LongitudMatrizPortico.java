package com.civil.stiff.verticales.portico.algoritmoportico.matrix;

public class LongitudMatrizPortico {
    private  LongitudMatrizPortico(){}
    // Calcular longitud de matriz portico
    public static int calculate(int numeroElementos) {
        int longitud = 0;
        switch (numeroElementos) {
            case 2:
                longitud = 9;
                break;
            case 3:
                longitud = 12;
                break;
            default:
                longitud = 0;
        }
        return longitud;
    }
}
