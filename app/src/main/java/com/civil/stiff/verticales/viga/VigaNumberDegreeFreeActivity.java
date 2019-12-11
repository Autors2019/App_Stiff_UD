package com.civil.stiff.verticales.viga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.civil.stiff.R;
import com.civil.stiff.TemplatePDF;
import com.civil.stiff.verticales.viga.algoritmoviga.SolveViga;
import com.civil.stiff.verticales.viga.algoritmoviga.matrix.RegidityMatrix;

import org.ejml.simple.SimpleMatrix;


import java.text.DateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;


public class VigaNumberDegreeFreeActivity extends AppCompatActivity {

    //
    private ArrayList<SimpleMatrix> vectoresFuerzasInternas= null;
    private ArrayList<RegidityMatrix> matricesRigidez= null;
    private ArrayList<Integer[]> arrayOrdenElementos=null;
    private SimpleMatrix vectorFuerzaInt=null;
    private SimpleMatrix vectorFuerzaExt=null;
    private SimpleMatrix matrizConsolidacion= null;

    private int gradosLibertad;
    private TextView tVGradosLibertad;
    private LinearLayout layoutSpinnerGradosLibertad;
    private ArrayList<Spinner> spinners;
    private ArrayList<String> numeroElmentosSpinner;
    private ArrayAdapter<CharSequence> adaptadorSpinner;
    private LinearLayout.LayoutParams    layoutParametrosSpinners;
    private ArrayList<Integer> a=null;
    private ArrayList<Integer> b=null;
    private SolveViga solveViga;

    private TemplatePDF templatePDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viga_number_degree_free);
        // Acceso app PDF
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        templatePDF= new TemplatePDF(getApplicationContext(), "viga");
        numeroElmentosSpinner= new ArrayList<>();
        tVGradosLibertad= findViewById(R.id.textViewNumeroGrados);
        tVGradosLibertad.setText("n="+gradosLibertad);
        layoutSpinnerGradosLibertad= findViewById(R.id.layoutSpinnerNumeroGrados);
        spinners= new ArrayList<>();

        Bundle objetoEnviado= getIntent().getExtras();
        if(objetoEnviado!=null){
            arrayOrdenElementos= (ArrayList<Integer[]>) objetoEnviado.getSerializable("arrayOrdenElementos");
            vectorFuerzaInt= (SimpleMatrix) objetoEnviado.getSerializable("vectorFuerzaIntConsolidado");
            vectoresFuerzasInternas= (ArrayList<SimpleMatrix>) objetoEnviado.getSerializable("vectoresFuerzasInternas");
            vectorFuerzaExt= (SimpleMatrix) objetoEnviado.getSerializable("vectorFuerzaExt");
            matricesRigidez= (ArrayList<RegidityMatrix>) objetoEnviado.getSerializable("matricesRigidez");
            matrizConsolidacion=(SimpleMatrix) objetoEnviado.getSerializable("matrizConsolidacion");
            Log.i("arrayOrdenElementos: ", arrayOrdenElementos.toString());
            Log.i("vectorFuerzaInt: ", vectorFuerzaInt.toString());
            Log.i("vectorFuerzaExt: ", vectorFuerzaExt.toString());
            Log.i("matrizConsolidacion: ", matrizConsolidacion.toString());

        }
        adaptadorSpinner= new ArrayAdapter(this, android.R.layout.simple_spinner_item, numeroGradosLibertad(arrayOrdenElementos));
        layoutParametrosSpinners = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParametrosSpinners.topMargin=45;
    }

    private ArrayList<String> numeroGradosLibertad(ArrayList<Integer[]> arrayOrdenElementos){

        switch (arrayOrdenElementos.size()){
            case 2:  numeroElmentosSpinner.add(0, "V1");
                numeroElmentosSpinner.add(1, "θ1");
                numeroElmentosSpinner.add(2, "V2");
                numeroElmentosSpinner.add(3, "θ2");
                numeroElmentosSpinner.add(4, "V3");
                numeroElmentosSpinner.add(5, "θ3");
                break;
            case 3:  numeroElmentosSpinner.add(0, "V1");
                numeroElmentosSpinner.add(1, "θ1");
                numeroElmentosSpinner.add(2, "V2");
                numeroElmentosSpinner.add(3, "θ");
                numeroElmentosSpinner.add(4, "V3");
                numeroElmentosSpinner.add(5, "θ3");
                numeroElmentosSpinner.add(6, "V4");
                numeroElmentosSpinner.add(7, "θ4");
                break;
            case 4:  numeroElmentosSpinner.add(0, "V1");
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
    private void agregarGradosLibertad(ArrayList<Integer[]> arrayOrdenElementos){
        int numeroGL=0;

        switch (arrayOrdenElementos.size()){
            case 2: numeroGL=3;
                break;
            case 3: numeroGL=4;
                break;
            case 4: numeroGL=5;
                break;
            default:
                numeroGL=0;
        }
        if(gradosLibertad<numeroGL){

            spinners.add(gradosLibertad, new Spinner(this));
            spinners.get(gradosLibertad).setAdapter(adaptadorSpinner);
            layoutSpinnerGradosLibertad.addView(spinners.get(gradosLibertad), gradosLibertad, layoutParametrosSpinners );
            gradosLibertad++;
            tVGradosLibertad.setText("n="+gradosLibertad);


        }
        else {
            gradosLibertad=gradosLibertad;
            Toast.makeText(this,"No se permiten mas grados de libertad", Toast.LENGTH_LONG).show();
        }
    }
    private void quitarGradosLibertad(){
        if(gradosLibertad>1){
            gradosLibertad--;
            layoutSpinnerGradosLibertad.removeViewAt(gradosLibertad);
            spinners.remove(gradosLibertad);
            tVGradosLibertad.setText("n="+gradosLibertad);
        }
        else gradosLibertad=gradosLibertad;
    }
    private boolean validador(){
        boolean status= false;
        Set<Integer> listaNoDuplicados= new TreeSet<>();
        for(int i=0; i< spinners.size(); i++){
            listaNoDuplicados.add(spinners.get(i).getSelectedItemPosition());
        }
        if( listaNoDuplicados.size() == spinners.size()) status= true;
        else status= false;
        return status;
    }
    private void calcularVectoresGradosLibertad() {
        a= new ArrayList<>();
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
        for(int i=0; i< longitud; i++){
            a.add(i,i);
        }

        for(int i=0; i<b.size(); i++){
            a.remove(b.get(i));
        }
    }
    // Cast Orden Elemetos PDF
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

    private void platillaPDFViga(SolveViga solveViga){
        Date currentTime = Calendar.getInstance().getTime();
        String dtf= DateFormat.getDateInstance(DateFormat.SHORT).format(currentTime);
        templatePDF.openDocument();
        templatePDF.addMetaData("Stiff", "Caliculo Viga", "Stiff");
        templatePDF.addTitles("Stiff", "Calculo Viga", dtf);
        templatePDF.addParagraph("Matrices de rigidez: ");
        // Imprimir matrices de regidez
        for(int i=0; i< matricesRigidez.size(); i++){
            templatePDF.createMatrix(castOrdenElementos(arrayOrdenElementos.get(i)),matricesRigidez.get(i).calculate(), 50);
        }
        templatePDF.addParagraph("Matriz de consolidación: ");
        // Imprimir matrices de consolidacion
        Integer[] elementosHeader= new Integer[matrizConsolidacion.numCols()];
        for(int i=0; i< matrizConsolidacion.numCols(); i++){
            elementosHeader[i]=i;
        }
        templatePDF.createMatrix(castOrdenElementos(elementosHeader),matrizConsolidacion, 100);
        // Imprimir grados libertad no restringidos
        templatePDF.addParagraph("Grados de libertad no restringidos: ");
        templatePDF.createMatrix(castOrdenElementos(b.toArray(new Integer[b.size()])),solveViga.getD_b(), 100);
        // Imprimir Reacciones
        templatePDF.addParagraph("Reacciones: ");
        templatePDF.createMatrix(castReaccionesElementos(a.toArray(new Integer[a.size()])),solveViga.getP_a(), 100);
        // Fuezas internas
        templatePDF.addParagraph("Fuerzas Internas: ");
        for(int i=0; i< solveViga.getReacciIntP().size(); i++){
            templatePDF.createMatrix(castReaccionesElementos(arrayOrdenElementos.get(i)),solveViga.getReacciIntP().get(i), 50);
        }

        templatePDF.closeDocument();
        templatePDF.viewPDF();
    }

    private void calcularViga(){
        b = new ArrayList<>();
        if(validador()) {
            for (int i = 0; i < spinners.size(); i++) {
                b.add(i, spinners.get(i).getSelectedItemPosition());
            }
            calcularVectoresGradosLibertad();
            //Log vectores a y b
            a.forEach(s->Log.i("calcularViga: Vector a= ", s.toString()));
            b.forEach(s->Log.i("calcularViga: Vector b= ", s.toString()));

            // Solucionar Viga
            try {
                solveViga= new SolveViga(a, b, arrayOrdenElementos, vectorFuerzaInt, vectoresFuerzasInternas, vectorFuerzaExt, matricesRigidez,matrizConsolidacion);
                platillaPDFViga(solveViga);
            }
            catch (RuntimeException e){
                Log.e("calcularViga", e.toString());
                Toast.makeText(this, "La operación a realizar contiene multiples soluciones, revise los datos ingresados" , Toast.LENGTH_LONG).show();
            }

        }
        else{
            Toast.makeText(this, "Uno o mas grados estan varias veces asignados", Toast.LENGTH_LONG).show();
        }
    }



    public void onClick(View view){

        switch (view.getId()){

            case R.id.atras: finish();
                break;

            case R.id.botonRestar: quitarGradosLibertad();
                break;
            case R.id.botonSumar:agregarGradosLibertad(arrayOrdenElementos);
                break;

            case R.id.siguiente: calcularViga();
                break;
        }
    }
}


