package com.civil.stiff.estructuras.trasversales;



import java.util.ArrayList;

public class CalVectA {
    // caclular vector A
    public static ArrayList<Integer> calculate(int longitudMatriz, ArrayList<Integer> b) {
        ArrayList<Integer> a= new ArrayList<>();
        for(int i=0; i< longitudMatriz; i++){
            a.add(i,i);
        }
        for(int i=0; i<b.size(); i++){
            a.remove(b.get(i));
        }
        return a;
}
}
