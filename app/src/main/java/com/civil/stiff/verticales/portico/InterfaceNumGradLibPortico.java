package com.civil.stiff.verticales.portico;



import java.util.ArrayList;

public interface InterfaceNumGradLibPortico {
    default ArrayList<String> numeroGradosLibertad(int numeroElementos) {
        ArrayList<String> numeroElmentosSpinner= new ArrayList<>();
        switch (numeroElementos) {
            case 2:
                numeroElmentosSpinner.add(0, "U1");
                numeroElmentosSpinner.add(1, "V1");
                numeroElmentosSpinner.add(2, "θ1");
                numeroElmentosSpinner.add(3, "U2");
                numeroElmentosSpinner.add(4, "V2");
                numeroElmentosSpinner.add(5, "θ2");
                numeroElmentosSpinner.add(6, "U3");
                numeroElmentosSpinner.add(7, "V3");
                numeroElmentosSpinner.add(8, "θ3");
                break;
            case 3:
                numeroElmentosSpinner.add(0, "U1");
                numeroElmentosSpinner.add(1, "V1");
                numeroElmentosSpinner.add(2, "θ1");
                numeroElmentosSpinner.add(3, "U2");
                numeroElmentosSpinner.add(4, "V2");
                numeroElmentosSpinner.add(5, "θ2");
                numeroElmentosSpinner.add(6, "U3");
                numeroElmentosSpinner.add(7, "V3");
                numeroElmentosSpinner.add(8, "θ3");
                numeroElmentosSpinner.add(9, "U4");
                numeroElmentosSpinner.add(10, "V4");
                numeroElmentosSpinner.add(11, "θ4");
                break;
        }
        return numeroElmentosSpinner;
    }
}
