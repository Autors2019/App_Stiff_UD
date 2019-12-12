package com.civil.stiff.verticales.trasversales;

import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public interface InterfaceMatrixOrden {
    default ArrayList<String> numeroGradosLibertad( ArrayList<Integer[]> arrayOrdenElementos) {
        ArrayList<String> numeroElmentosSpinner= new ArrayList<>();
        switch (arrayOrdenElementos.size()) {
            case 2:
                numeroElmentosSpinner.add(0, "V1");
                numeroElmentosSpinner.add(1, "θ1");
                numeroElmentosSpinner.add(2, "V2");
                numeroElmentosSpinner.add(3, "θ2");
                numeroElmentosSpinner.add(4, "V3");
                numeroElmentosSpinner.add(5, "θ3");
                break;
            case 3:
                numeroElmentosSpinner.add(0, "V1");
                numeroElmentosSpinner.add(1, "θ1");
                numeroElmentosSpinner.add(2, "V2");
                numeroElmentosSpinner.add(3, "θ2");
                numeroElmentosSpinner.add(4, "V3");
                numeroElmentosSpinner.add(5, "θ3");
                numeroElmentosSpinner.add(6, "V4");
                numeroElmentosSpinner.add(7, "θ4");
                break;
            case 4:
                numeroElmentosSpinner.add(0, "V1");
                numeroElmentosSpinner.add(1, "θ1");
                numeroElmentosSpinner.add(2, "V2");
                numeroElmentosSpinner.add(3, "θ2");
                numeroElmentosSpinner.add(4, "V3");
                numeroElmentosSpinner.add(5, "θ3");
                numeroElmentosSpinner.add(6, "V4");
                numeroElmentosSpinner.add(7, "θ4");
                numeroElmentosSpinner.add(8, "V5");
                numeroElmentosSpinner.add(9, "θ5");
                break;
        }
        return numeroElmentosSpinner;
    }
    default ArrayList<String> numeroGradosLibertad(int numeroElementos) {
        ArrayList<String> numeroElmentosSpinner= new ArrayList<>();
        switch (numeroElementos) {
            case 2:
                numeroElmentosSpinner.add(0, "V1");
                numeroElmentosSpinner.add(1, "θ1");
                numeroElmentosSpinner.add(2, "V2");
                numeroElmentosSpinner.add(3, "θ2");
                numeroElmentosSpinner.add(4, "V3");
                numeroElmentosSpinner.add(5, "θ3");
                break;
            case 3:
                numeroElmentosSpinner.add(0, "V1");
                numeroElmentosSpinner.add(1, "θ1");
                numeroElmentosSpinner.add(2, "V2");
                numeroElmentosSpinner.add(3, "θ2");
                numeroElmentosSpinner.add(4, "V3");
                numeroElmentosSpinner.add(5, "θ3");
                numeroElmentosSpinner.add(6, "V4");
                numeroElmentosSpinner.add(7, "θ4");
                break;
            case 4:
                numeroElmentosSpinner.add(0, "V1");
                numeroElmentosSpinner.add(1, "θ1");
                numeroElmentosSpinner.add(2, "V2");
                numeroElmentosSpinner.add(3, "θ2");
                numeroElmentosSpinner.add(4, "V3");
                numeroElmentosSpinner.add(5, "θ3");
                numeroElmentosSpinner.add(6, "V4");
                numeroElmentosSpinner.add(7, "θ4");
                numeroElmentosSpinner.add(8, "V5");
                numeroElmentosSpinner.add(9, "θ5");
                break;
        }
        return numeroElmentosSpinner;
    }
    // Validador spinner
    default boolean validadorSpinners(ArrayList<Spinner> spinners){
        boolean status= false;
        Set<Integer> listaNoDuplicados= new TreeSet<>();
        for(int i=0; i< spinners.size(); i++){
            listaNoDuplicados.add(spinners.get(i).getSelectedItemPosition());
        }
        if( listaNoDuplicados.size() == spinners.size()) status= true;
        else status= false;
        return status;
    }
}