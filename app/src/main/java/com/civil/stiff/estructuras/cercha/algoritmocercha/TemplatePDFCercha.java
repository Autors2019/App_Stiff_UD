package com.civil.stiff.estructuras.cercha.algoritmocercha;

import android.content.Context;

import com.civil.stiff.TemplatePDF;
import com.civil.stiff.estructuras.cercha.algoritmocercha.matrix.RegidityMatrixCercha;
import com.civil.stiff.estructuras.portico.algoritmoportico.SolvePortico;

import org.ejml.simple.SimpleMatrix;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TemplatePDFCercha extends TemplatePDF {
    private ArrayList<RegidityMatrixCercha> regidityMatrixCerchas;
    private ArrayList<Integer[]> ordenElementos;
    private SimpleMatrix matrizConsolidada;
    private ArrayList<Integer> a,b;
    public TemplatePDFCercha(Context context, String nombre){
        super(context,nombre);
    }
    public void platillaPDFViga(ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer[]> ordenElementos , ArrayList<RegidityMatrixCercha> regidityMatrixCerchas, SimpleMatrix matrizConsolidada, SolveCercha solveCercha){
        this.a=a;
        this.b=b;
        this.ordenElementos=ordenElementos;
        this.regidityMatrixCerchas = regidityMatrixCerchas;
        this.matrizConsolidada=matrizConsolidada;
        Date currentTime = Calendar.getInstance().getTime();
        String dtf= DateFormat.getDateInstance(DateFormat.SHORT).format(currentTime);
        openDocument();
        addMetaData("Stiff", "Calculo Cercha", "Stiff");
        addTitles("Stiff", "Calculo Cercha", dtf);
        addParagraph("Matrices de rigidez: ");
        // Imprimir matrices de regidez
        for(int i = 0; i< this.regidityMatrixCerchas.size(); i++){
            createMatrix(castOrdenElementos(ordenElementos.get(i)), this.regidityMatrixCerchas.get(i).calculate(), 80);
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
        createMatrix(castOrdenElementos(b.toArray(new Integer[b.size()])),solveCercha.getD_b(), 100);
        // Imprimir Reacciones
        addParagraph("Reacciones: ");
        createMatrix(castReaccionesElementos(a.toArray(new Integer[a.size()])),solveCercha.getF_a(), 100);
        // Fuerzas internas
        addParagraph("Fuerzas Internas: ");
        for(int i=0; i< solveCercha.getReacciones().size(); i++){
            createMatrix(castReaccionesElementos(ordenElementos.get(i)),solveCercha.getReacciones().get(i), 80);
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
                case 2: cast[i]="U2";
                    break;
                case 3: cast[i]="V2";
                    break;
                case 4: cast[i]="U3";
                    break;
                case 5: cast[i]="V3";
                    break;
                case 6: cast[i]="U4";
                    break;
                case 7: cast[i]="V4";
                    break;
                case 8: cast[i]="U5";
                    break;
                case 9: cast[i]="V5";
                    break;
                case 10: cast[i]="U6";
                    break;
                case 11: cast[i]="V6";
                    break;
                case 12: cast[i]="U7";
                    break;
                case 13: cast[i]="V7";
                    break;
                case 14: cast[i]="U8";
                    break;
                case 15: cast[i]="V8";
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
                case 2: cast[i]="X2";
                    break;
                case 3: cast[i]="Y2";
                    break;
                case 4: cast[i]="X3";
                    break;
                case 5: cast[i]="Y3";
                    break;
                case 6: cast[i]="X4";
                    break;
                case 7: cast[i]="Y4";
                    break;
                case 8: cast[i]="X5";
                    break;
                case 9: cast[i]="Y5";
                    break;
                case 10: cast[i]="X6";
                    break;
                case 11: cast[i]="Y6";
                    break;
                case 12: cast[i]="X7";
                    break;
                case 13: cast[i]="Y7";
                    break;
                case 14: cast[i]="X8";
                    break;
                case 15: cast[i]="Y8";
                    break;

                default:
                    cast[i]="";
            }
        }
        return cast;
    }

}
