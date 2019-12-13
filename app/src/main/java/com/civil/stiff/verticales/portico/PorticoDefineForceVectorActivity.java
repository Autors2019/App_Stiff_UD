package com.civil.stiff.verticales.portico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.civil.stiff.R;
import com.civil.stiff.verticales.portico.algoritmoportico.matrix.ConsolidatedMatrixPortico;
import com.civil.stiff.verticales.portico.algoritmoportico.matrix.RegidityMatrixPortico;
import com.civil.stiff.verticales.trasversales.InterfaceValidadores;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class PorticoDefineForceVectorActivity extends AppCompatActivity implements InterfaceValidadores {
    //Objetos recibidos
    private int numElementos;
    private ArrayList<RegidityMatrixPortico> regidityMatrixPorticos;
    private ArrayList<Integer[]> ordenElementos;
    // Rererencias de vistas;
    private EditText etVectorFuerza1;
    private EditText etVectorFuerza2;
    private EditText etVectorFuerza3;
    private EditText etVectorFuerza4;
    private EditText etVectorFuerza5;
    private EditText etVectorFuerza6;
    private TextView tvNumeroElemento;
    private Button  bGuardar;
    private Button  bSiguiente;
    // Variables atributo
    private ArrayList<SimpleMatrix> vectoresFuerzasInt;
    private SimpleMatrix vectorFuerza;
    private int indexElemento=1;
    private SimpleMatrix matrizConsolidada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portico_define_force_vector);
        // Instancia objetos
        etVectorFuerza1= findViewById(R.id.entradaVector1);
        etVectorFuerza2= findViewById(R.id.entradaVector2);
        etVectorFuerza3= findViewById(R.id.entradaVector3);
        etVectorFuerza4= findViewById(R.id.entradaVector4);
        etVectorFuerza5= findViewById(R.id.entradaVector5);
        etVectorFuerza6= findViewById(R.id.entradaVector6);
        tvNumeroElemento=findViewById(R.id.numeroElemento);
        tvNumeroElemento.setText("Elemento " + indexElemento);
        bGuardar= findViewById(R.id.bGuardar);
        bSiguiente= findViewById(R.id.bSiguiente);
        bSiguiente.setEnabled(false);
        vectoresFuerzasInt = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            numElementos = bundle.getInt("numeroElementos");
            regidityMatrixPorticos = (ArrayList<RegidityMatrixPortico>) bundle.getSerializable("regidityMatrixPorticos");
            ordenElementos = (ArrayList<Integer[]>) bundle.getSerializable("ordenElementos");
            // Log orden de  elementos
            for (Integer[] vector : ordenElementos) {
                for (int i : vector) {
                    Log.i("ordenElementos= ", Integer.toString(i));
                }
            }
            // Calcular matriz consolidada
            try {
                matrizConsolidada= ConsolidatedMatrixPortico.calculate(numElementos,ordenElementos,regidityMatrixPorticos);
            }
            catch (Exception e){
                Log.e( "matrizConsolidada: ", e.toString());
            }

        }
    }

    private void guardar( ) {

        if (validarCampoCC(etVectorFuerza1) && validarCampoCC(etVectorFuerza2) &&
                validarCampoCC(etVectorFuerza3) && validarCampoCC(etVectorFuerza4) &&
                validarCampoCC(etVectorFuerza5) && validarCampoCC(etVectorFuerza6)) {
            vectorFuerza = new SimpleMatrix(6, 1);
            vectorFuerza.set(0, 0, Double.parseDouble(etVectorFuerza1.getText().toString()));
            vectorFuerza.set(1, 0, Double.parseDouble(etVectorFuerza2.getText().toString()));
            vectorFuerza.set(2, 0, Double.parseDouble(etVectorFuerza3.getText().toString()));
            vectorFuerza.set(3, 0, Double.parseDouble(etVectorFuerza4.getText().toString()));
            vectorFuerza.set(4, 0, Double.parseDouble(etVectorFuerza5.getText().toString()));
            vectorFuerza.set(5, 0, Double.parseDouble(etVectorFuerza6.getText().toString()));
            vectoresFuerzasInt.add(indexElemento - 1, vectorFuerza);

            if (indexElemento < numElementos) {
                indexElemento++;
                tvNumeroElemento.setText("Elemento " + indexElemento);
                etVectorFuerza1.getText().clear();
                etVectorFuerza2.getText().clear();
                etVectorFuerza3.getText().clear();
                etVectorFuerza4.getText().clear();
                etVectorFuerza5.getText().clear();
                etVectorFuerza6.getText().clear();
            } else {

                etVectorFuerza1.setEnabled(false);
                etVectorFuerza2.setEnabled(false);
                etVectorFuerza3.setEnabled(false);
                etVectorFuerza4.setEnabled(false);
                etVectorFuerza5.setEnabled(false);
                etVectorFuerza6.setEnabled(false);
                bGuardar.setEnabled(false);
                bSiguiente.setEnabled(true);

            }

        } else {

            Toast.makeText(this, "Uno o más campos están vacios ", Toast.LENGTH_SHORT).show();
        }
    }

        public void onClick(View view){
        Intent intent= null;
        switch (view.getId()){
            case R.id.bAtras: finish();
                break;
            case R.id.bGuardar: guardar();
                break;
            case R.id.bSiguiente: intent= new Intent(PorticoDefineForceVectorActivity.this, PorticoDefineForceVectorExtActivity.class);
                Bundle bundle= new Bundle();
                bundle.putInt("numeroElementos", numElementos);
                bundle.putSerializable("regidityMatrixPorticos",regidityMatrixPorticos);
                bundle.putSerializable("ordenElementos", ordenElementos);
                bundle.putSerializable("vectoresFuerzasInt", vectoresFuerzasInt);
                bundle.putSerializable("matrizConsolidada",matrizConsolidada);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

        }
    }
}
