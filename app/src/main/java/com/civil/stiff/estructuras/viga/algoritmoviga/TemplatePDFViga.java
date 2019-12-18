package com.civil.stiff.estructuras.viga.algoritmoviga;

import android.content.Context;

import com.civil.stiff.TemplatePDF;
import com.civil.stiff.estructuras.viga.algoritmoviga.matrix.RegidityMatrix;

import org.ejml.simple.SimpleMatrix;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TemplatePDFViga extends TemplatePDF{
    private ArrayList<RegidityMatrix> matricesRigidez;
    private ArrayList<Integer[]> arrayOrdenElementos;
    private SimpleMatrix matrizConsolidacion;
    private ArrayList<Integer> a,b;
    public TemplatePDFViga(Context context, String nombre){
        super(context, nombre);

    }

    public void platillaPDFViga(ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer[]> arrayOrdenElementos ,ArrayList<RegidityMatrix> matricesRigidez, SimpleMatrix matrizConsolidacion,SolveViga solveViga){
        this.a=a;
        this.b=b;
        this.arrayOrdenElementos=arrayOrdenElementos;
        this.matricesRigidez=matricesRigidez;
        this.matrizConsolidacion=matrizConsolidacion;
        Date currentTime = Calendar.getInstance().getTime();
        String dtf= DateFormat.getDateInstance(DateFormat.SHORT).format(currentTime);
        openDocument();
        addMetaData("Stiff", "Calculo Viga", "Stiff");
        addTitles("Stiff", "Calculo Viga", dtf);
        addParagraph("Matrices de rigidez: ");
        // Imprimir matrices de regidez
        for(int i=0; i< matricesRigidez.size(); i++){
            createMatrix(castOrdenElementos(arrayOrdenElementos.get(i)),matricesRigidez.get(i).calculate(), 50);
        }
        addParagraph("Matriz de consolidación: ");
        // Imprimir matrices de consolidacion
        Integer[] elementosHeader= new Integer[matrizConsolidacion.numCols()];
        for(int i=0; i< matrizConsolidacion.numCols(); i++){
            elementosHeader[i]=i;
        }
        createMatrix(castOrdenElementos(elementosHeader),matrizConsolidacion, 100);
        // Imprimir grados libertad no restringidos
        addParagraph("Grados de libertad no restringidos: ");
        createMatrix(castOrdenElementos(b.toArray(new Integer[b.size()])),solveViga.getD_b(), 100);
        // Imprimir Reacciones
        addParagraph("Reacciones: ");
        createMatrix(castReaccionesElementos(a.toArray(new Integer[a.size()])),solveViga.getF_a(), 100);
        // Fuerzas internas
        addParagraph("Fuerzas Internas: ");
        for(int i=0; i< solveViga.getReacciIntP().size(); i++){
            createMatrix(castReaccionesElementos(arrayOrdenElementos.get(i)),solveViga.getReacciIntP().get(i), 50);
        }
        closeDocument();
        viewPDF();
    }

    // Cast Orden Elementos PDF
    private String[] castOrdenElementos(Integer[] ordenElementos) {
        String [] cast= new String[ordenElementos.length];
        for(int i=0; i< ordenElementos.length; i++){
            switch (ordenElementos[i]){
                case 0: cast[i]="V1";
                    break;
                case 1: cast[i]="θ1";
                    break;
                case 2: cast[i]="V2";
                    break;
                case 3: cast[i]="θ2";
                    break;
                case 4: cast[i]="V3";
                    break;
                case 5: cast[i]="θ3";
                    break;
                case 6: cast[i]="V4";
                    break;
                case 7: cast[i]="θ4";
                    break;
                case 8: cast[i]="V5";
                    break;
                case 9: cast[i]="θ5";
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
                case 0: cast[i]="Y1";
                    break;
                case 1: cast[i]="M1";
                    break;
                case 2: cast[i]="Y2";
                    break;
                case 3: cast[i]="M2";
                    break;
                case 4: cast[i]="Y3";
                    break;
                case 5: cast[i]="M3";
                    break;
                case 6: cast[i]="Y4";
                    break;
                case 7: cast[i]="M4";
                    break;
                case 8: cast[i]="Y5";
                    break;
                case 9: cast[i]="M5";
                    break;
                default:
                    cast[i]="";
            }
        }
        return cast;
    }
}
