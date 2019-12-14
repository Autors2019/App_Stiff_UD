package com.civil.stiff.estructuras.viga;

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

import com.civil.stiff.estructuras.trasversales.CalVectA;
import com.civil.stiff.estructuras.trasversales.InterfaceValidadores;
import com.civil.stiff.estructuras.viga.algoritmoviga.SolveViga;
import com.civil.stiff.estructuras.viga.algoritmoviga.TemplatePDFViga;
import com.civil.stiff.estructuras.viga.algoritmoviga.matrix.LongitudMatrizViga;
import com.civil.stiff.estructuras.viga.algoritmoviga.matrix.RegidityMatrix;

import org.ejml.simple.SimpleMatrix;



import java.util.ArrayList;



public class VigaNumberDegreeFreeActivity extends AppCompatActivity implements InterfaceNumGradLibMatrix, InterfaceValidadores {

    //
    private ArrayList<SimpleMatrix> vectoresFuerzasInternas;
    private ArrayList<RegidityMatrix> matricesRigidez;
    private ArrayList<Integer[]> arrayOrdenElementos;
    private SimpleMatrix vectorFuerzaInt;
    private SimpleMatrix vectorFuerzaExt;
    private SimpleMatrix matrizConsolidacion;

    private int gradosLibertad;
    private TextView tVGradosLibertad;
    private LinearLayout layoutSpinnerGradosLibertad;
    private ArrayList<Spinner> spinners;
    private ArrayList<String> numeroElmentosSpinner;
    private ArrayAdapter<CharSequence> adaptadorSpinner;
    private LinearLayout.LayoutParams    layoutParametrosSpinners;
    private ArrayList<Integer> a;
    private ArrayList<Integer> b = new ArrayList<>();
    private SolveViga solveViga;

    private TemplatePDFViga templatePDFViga;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viga_number_degree_free);
        // Acceso app PDF
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        // Crear template pdf
        templatePDFViga= new TemplatePDFViga(getApplicationContext(), "viga");
        // Intancias vistas
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

    private void calcularViga(){
        b = new ArrayList<>();
        if(validadorSpinners(spinners)) {
            // Calcular b
            for (int i = 0; i < spinners.size(); i++) {
                b.add(i, spinners.get(i).getSelectedItemPosition());
            }
            // Calcular a
            a= CalVectA.calculate(LongitudMatrizViga.calculate(arrayOrdenElementos),b);
            //Log vectores a y b
            a.forEach(s->Log.i("calcularViga: Vector a= ", s.toString()));
            b.forEach(s->Log.i("calcularViga: Vector b= ", s.toString()));

            // Solucionar Viga
            try {
                solveViga= new SolveViga(a, b, arrayOrdenElementos, vectorFuerzaInt, vectoresFuerzasInternas, vectorFuerzaExt, matricesRigidez,matrizConsolidacion);
                templatePDFViga.platillaPDFViga(a,b,arrayOrdenElementos,matricesRigidez,matrizConsolidacion,solveViga);
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


