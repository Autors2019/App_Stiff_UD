package com.civil.stiff.estructuras.portico.algoritmoportico;

import android.content.Context;

import com.civil.stiff.TemplatePDF;
import com.civil.stiff.estructuras.portico.algoritmoportico.matrix.RegidityMatrixPortico;


import org.ejml.simple.SimpleMatrix;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TemplatePDFPortico extends TemplatePDF {
    private ArrayList<RegidityMatrixPortico> regidityMatrixPorticos;
    private ArrayList<Integer[]> ordenElementos;
    private SimpleMatrix matrizConsolidada;
    private ArrayList<Integer> a,b;
    public TemplatePDFPortico(Context context, String nombre){
        super(context,nombre);
    }
    public void platillaPDFViga(ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer[]> ordenElementos ,  ArrayList<RegidityMatrixPortico> regidityMatrixPorticos, SimpleMatrix matrizConsolidada, SolvePortico solvePortico){
        this.a=a;
        this.b=b;
        this.ordenElementos=ordenElementos;
        this.regidityMatrixPorticos=regidityMatrixPorticos;
        this.matrizConsolidada=matrizConsolidada;
        Date currentTime = Calendar.getInstance().getTime();
        String dtf= DateFormat.getDateInstance(DateFormat.SHORT).format(currentTime);
        openDocument();
        addMetaData("Stiff", "Caliculo Portico", "Stiff");
        addTitles("Stiff", "Calculo portico", dtf);
        addParagraph("Matrices de rigidez: ");
        // Imprimir matrices de regidez
        for(int i=0; i< regidityMatrixPorticos.size(); i++){
            createMatrix(castOrdenElementos(ordenElementos.get(i)),regidityMatrixPorticos.get(i).calculate(), 80);
        }
        addParagraph("Matriz de consolidación: ");
        // Imprimir matrices de consolidacion
        Integer[] elementosHeader= new Integer[matrizConsolidada.numCols()];
        for(int i=0; i< matrizConsolidada.numCols(); i++){
            elementosHeader[i]=i;
        }
        createMatrix(castOrdenElementos(elementosHeader),matrizConsolidada, 100);
        // Imprimir grados libertad no restringidos
        addParagraph("Grados de libertad no restringidos: ");
        createMatrix(castOrdenElementos(b.toArray(new Integer[b.size()])),solvePortico.getD_b(), 100);
        // Imprimir Reacciones
        addParagraph("Reacciones: ");
        createMatrix(castReaccionesElementos(a.toArray(new Integer[a.size()])),solvePortico.getP_a(), 100);
        // Fuezas internas
        addParagraph("Fuerzas Internas: ");
        for(int i=0; i< solvePortico.getReacciones().size(); i++){
            createMatrix(castReaccionesElementos(ordenElementos.get(i)),solvePortico.getReacciones().get(i), 80);
        }
        closeDocument();
        viewPDF();
    }

    // Cast Orden Elemetos PDF
    private String[] castOrdenElementos(Integer[] ordenElementos) {
        String [] cast= new String[ordenElementos.length];
        for(int i=0; i< ordenElementos.length; i++){
            switch (ordenElementos[i]){
                case 0: cast[i]="U1";
                    break;
                case 1: cast[i]="V1";
                    break;
                case 2: cast[i]="θ1";
                    break;
                case 3: cast[i]="U2";
                    break;
                case 4: cast[i]="V2";
                    break;
                case 5: cast[i]="θ2";
                    break;
                case 6: cast[i]="U3";
                    break;
                case 7: cast[i]="V3";
                    break;
                case 8: cast[i]="θ3";
                     break;
                case 9: cast[i]="U4";
                    break;
                case 10: cast[i]="V4";
                    break;
                case 11: cast[i]="θ4";
                    break;

                default:
                    cast[i]="";
            }
        }
        return cast;
    }
    private String[] castReaccionesElementos(Integer[] ordenElementos) {
        String [] cast= new String[ordenElementos.length];
        for(int i=0; i< ordenElementos.length; i++){
            switch (ordenElementos[i]){
                case 0: cast[i]="X1";
                    break;
                case 1: cast[i]="Y1";
                    break;
                case 2: cast[i]="M1";
                    break;
                case 3: cast[i]="X2";
                    break;
                case 4: cast[i]="Y2";
                    break;
                case 5: cast[i]="M2";
                    break;
                case 6: cast[i]="X3";
                    break;
                case 7: cast[i]="Y3";
                    break;
                case 8: cast[i]="M3";
                    break;
                case 9: cast[i]="X4";
                    break;
                case 10: cast[i]="Y4";
                    break;
                case 11: cast[i]="M4";
                    break;
                default:
                    cast[i]="";
            }
        }
        return cast;
    }

}
