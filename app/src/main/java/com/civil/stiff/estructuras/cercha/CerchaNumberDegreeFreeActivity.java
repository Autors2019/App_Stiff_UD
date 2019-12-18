package com.civil.stiff.estructuras.cercha;

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
import com.civil.stiff.estructuras.cercha.algoritmocercha.SolveCercha;
import com.civil.stiff.estructuras.cercha.algoritmocercha.TemplatePDFCercha;
import com.civil.stiff.estructuras.cercha.algoritmocercha.matrix.RegidityMatrixCercha;
import com.civil.stiff.estructuras.cercha.algoritmocercha.matrix.SizeMatrixCercha;
import com.civil.stiff.estructuras.portico.InterfaceNumGradLibPortico;
import com.civil.stiff.estructuras.transversales.CalVectA;
import com.civil.stiff.estructuras.transversales.InterfaceValidadores;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class CerchaNumberDegreeFreeActivity extends AppCompatActivity implements InterfaceNumGradLibCercha, InterfaceValidadores {
    // Objetos recibidos
    private int numElementos;
    private ArrayList<RegidityMatrixCercha> regidityMatrixCerchas;
    private ArrayList<Integer[]> ordenElementos;
    private SimpleMatrix matrizConsolidada, vectorFuerzasExt;
    // Referecias vistas
    private TextView tVGradosLibertad;
    private LinearLayout layoutSpinnerGradosLibertad;
    private ArrayList<Spinner> spinners;
    private ArrayAdapter<CharSequence> adaptadorSpinner;
    private LinearLayout.LayoutParams    layoutParametrosSpinners;
    // Variables atributo
    private int gradosLibertad;
    private ArrayList<String> numeroElmentosSpinner;
    private ArrayList<Integer> a;
    private ArrayList<Integer> b= new ArrayList<>();
    private SolveCercha solveCercha;
    private TemplatePDFCercha templatePDFCercha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cercha_number_degree_free);
        // Instancias de vistas
        tVGradosLibertad= findViewById(R.id.textViewNumeroGrados);
        tVGradosLibertad.setText("n="+gradosLibertad);
        layoutSpinnerGradosLibertad= findViewById(R.id.layoutSpinnerNumeroGrados);
        // Intancias variables atributo
        numeroElmentosSpinner= new ArrayList<>();
        spinners= new ArrayList<>();
        // Permisos pdf
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        templatePDFCercha= new TemplatePDFCercha(this,"cercha");

        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            numElementos = bundle.getInt("numeroElementos");
            regidityMatrixCerchas = (ArrayList<RegidityMatrixCercha>) bundle.getSerializable("regidityMatrixCerchas");
            ordenElementos = (ArrayList<Integer[]>) bundle.getSerializable("ordenElementos");
            matrizConsolidada= (SimpleMatrix) bundle.getSerializable("matrizConsolidada");
            vectorFuerzasExt= (SimpleMatrix) bundle.getSerializable("vectorFuerzasExt");
            // Log matrices
            Log.i("matrizConsolidada", matrizConsolidada.toString());
            Log.i("vectorFuerzasExt", vectorFuerzasExt.toString());
            //Log matrix
            regidityMatrixCerchas.forEach(m->Log.i("regidityMatrixCerchas", m.calculate().toString()));
        }
        // Spinner´s
        adaptadorSpinner= new ArrayAdapter(this, android.R.layout.simple_spinner_item, numeroGradosLibertad(numElementos));
        layoutParametrosSpinners = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParametrosSpinners.topMargin=45;
    }
    // agregar grados de libertad
    private void agregarGradosLibertad(){
        int numeroGL=0;

        switch (numElementos){
            case 2: numeroGL=4;
                break;
            case 3: numeroGL=6;
                break;
            case 4: numeroGL=8;
                break;
            case 5: numeroGL=10;
                break;
            case 6: numeroGL=12;
                break;
            case 7: numeroGL=14;
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
    // quitar grados de libertad
    private void quitarGradosLibertad(){
        if(gradosLibertad>1){
            gradosLibertad--;
            layoutSpinnerGradosLibertad.removeViewAt(gradosLibertad);
            spinners.remove(gradosLibertad);
            tVGradosLibertad.setText("n="+gradosLibertad);
        }
        else gradosLibertad=gradosLibertad;
    }

    private void calcularCercha(){
        if(validadorSpinners(spinners)) {
            // Caclcular  vector b
            b.clear();
            for (int i = 0; i < spinners.size(); i++) {
                b.add(i, spinners.get(i).getSelectedItemPosition());
            }
            // Calcular vector a
            a= CalVectA.calculate(new SizeMatrixCercha(ordenElementos).calculate(),b);
            //Log vectores a y b
            a.forEach(s->Log.i("calculaPortico: Vector a= ", s.toString()));
            b.forEach(s->Log.i("calcularPortico: Vector b= ", s.toString()));

            // Solucionar Cercha
            try {
                solveCercha= new SolveCercha(a,b,ordenElementos,vectorFuerzasExt, regidityMatrixCerchas,matrizConsolidada);
                templatePDFCercha.platillaPDFViga(a,b,ordenElementos,regidityMatrixCerchas,matrizConsolidada,solveCercha);
            }
            catch (RuntimeException e){
                Log.e("calcularCercha", e.toString());
                Toast.makeText(this, "La operación a realizar contiene multiples soluciones o revise los datos ingresados" , Toast.LENGTH_LONG).show();
            }

        }
        else{
            Toast.makeText(this, "Uno o mas grados estan varias veces asignados", Toast.LENGTH_LONG).show();
        }
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bAtras: finish();
                break;
            case R.id.botonRestar: quitarGradosLibertad();
                break;
            case R.id.botonSumar: agregarGradosLibertad();
                break;
            case R.id.bCalcular:  calcularCercha();
                break;
        }
    }
}
