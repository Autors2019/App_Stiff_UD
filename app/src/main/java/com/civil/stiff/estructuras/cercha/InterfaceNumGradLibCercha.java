package com.civil.stiff.estructuras.cercha;

import java.util.ArrayList;

public interface InterfaceNumGradLibCercha {
    default ArrayList<String> numeroGradosLibertad(int numeroElementos) {
        ArrayList<String> numeroElmentosSpinner= new ArrayList<>();
        switch (numeroElementos) {
            case 2:
                numeroElmentosSpinner.add(0, "U1");
                numeroElmentosSpinner.add(1, "V1");
                numeroElmentosSpinner.add(2, "U2");
                numeroElmentosSpinner.add(3, "V2");
                numeroElmentosSpinner.add(4, "U3");
                numeroElmentosSpinner.add(5, "V3");
                break;
            case 3:
                numeroElmentosSpinner.add(0, "U1");
                numeroElmentosSpinner.add(1, "V1");
                numeroElmentosSpinner.add(2, "U2");
                numeroElmentosSpinner.add(3, "V2");
                numeroElmentosSpinner.add(4, "U3");
                numeroElmentosSpinner.add(5, "V3");
                numeroElmentosSpinner.add(6, "U4");
                numeroElmentosSpinner.add(7, "V4");
                break;
            case 4:
                numeroElmentosSpinner.add(0, "U1");
                numeroElmentosSpinner.add(1, "V1");
                numeroElmentosSpinner.add(2, "U2");
                numeroElmentosSpinner.add(3, "V2");
                numeroElmentosSpinner.add(4, "U3");
                numeroElmentosSpinner.add(5, "V3");
                numeroElmentosSpinner.add(6, "U4");
                numeroElmentosSpinner.add(7, "V4");
                numeroElmentosSpinner.add(8, "U5");
                numeroElmentosSpinner.add(9, "V5");
                break;
            case 5:
                numeroElmentosSpinner.add(0, "U1");
                numeroElmentosSpinner.add(1, "V1");
                numeroElmentosSpinner.add(2, "U2");
                numeroElmentosSpinner.add(3, "V2");
                numeroElmentosSpinner.add(4, "U3");
                numeroElmentosSpinner.add(5, "V3");
                numeroElmentosSpinner.add(6, "U4");
                numeroElmentosSpinner.add(7, "V4");
                numeroElmentosSpinner.add(8, "U5");
                numeroElmentosSpinner.add(9, "V5");
                numeroElmentosSpinner.add(10, "U6");
                numeroElmentosSpinner.add(11, "V6");
                break;
            case 6:
                numeroElmentosSpinner.add(0, "U1");
                numeroElmentosSpinner.add(1, "V1");
                numeroElmentosSpinner.add(2, "U2");
                numeroElmentosSpinner.add(3, "V2");
                numeroElmentosSpinner.add(4, "U3");
                numeroElmentosSpinner.add(5, "V3");
                numeroElmentosSpinner.add(6, "U4");
                numeroElmentosSpinner.add(7, "V4");
                numeroElmentosSpinner.add(8, "U5");
                numeroElmentosSpinner.add(9, "V5");
                numeroElmentosSpinner.add(10, "U6");
                numeroElmentosSpinner.add(11, "V6");
                numeroElmentosSpinner.add(12, "U7");
                numeroElmentosSpinner.add(13, "V7");
                break;
            case 7:
                numeroElmentosSpinner.add(0, "U1");
                numeroElmentosSpinner.add(1, "V1");
                numeroElmentosSpinner.add(2, "U2");
                numeroElmentosSpinner.add(3, "V2");
                numeroElmentosSpinner.add(4, "U3");
                numeroElmentosSpinner.add(5, "V3");
                numeroElmentosSpinner.add(6, "U4");
                numeroElmentosSpinner.add(7, "V4");
                numeroElmentosSpinner.add(8, "U5");
                numeroElmentosSpinner.add(9, "V5");
                numeroElmentosSpinner.add(10, "U6");
                numeroElmentosSpinner.add(11, "V6");
                numeroElmentosSpinner.add(12, "U7");
                numeroElmentosSpinner.add(13, "V7");
                numeroElmentosSpinner.add(14, "U8");
                numeroElmentosSpinner.add(15, "V8");
                break;
        }
        return numeroElmentosSpinner;
    }
}
