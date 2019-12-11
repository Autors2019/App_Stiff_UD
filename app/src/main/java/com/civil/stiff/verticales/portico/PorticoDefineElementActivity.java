package com.civil.stiff.verticales.portico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.civil.stiff.verticales.portico.algoritmoportico.matrix.RegidityMatrixPortico;
import com.civil.stiff.verticales.trasversales.UnidadesLongitud;
import com.civil.stiff.verticales.trasversales.UnidadesPresion;
import com.civil.stiff.R;
import com.civil.stiff.verticales.trasversales.InterfaceElementos;

import java.util.ArrayList;

public class PorticoDefineElementActivity extends AppCompatActivity  implements InterfaceElementos {
    private int numElementos;
    // rerencias vistas
    private TextView tvNumElementos;
    private EditText etBase;
    private EditText etAltura;
    private EditText etLongitud;
    private EditText etElasticidad;
    private EditText etAngulo;
    private Spinner  sUBase;
    private Spinner  sUAltura;
    private Spinner  sULongitud;
    private Spinner  sUElasticidad;
    private Button   bGuardar;
    private Button   bSiguiente;

    // Variables de atributo
    private UnidadesLongitud unidadBase;
    private UnidadesLongitud unidadAltura;
    private UnidadesLongitud unidadLongitud;
    private UnidadesPresion unidadElasticidad;
    private int indexMatrix=1;
    private ArrayList <RegidityMatrixPortico> regidityMatrixPorticos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portico_define_element);
        // Instanciar vistas
        tvNumElementos= findViewById(R.id.numeroElemento);
        tvNumElementos.setText("Elemento " + indexMatrix);
        etBase= findViewById(R.id.entradaBase);
        etAltura =findViewById(R.id.entradaAltura);
        etLongitud =findViewById(R.id.entradaLongitud);
        etElasticidad=findViewById(R.id.entradaElasticidad);
        etAngulo= findViewById(R.id.entradaAngulo);
        sUBase=findViewById(R.id.unidadesBase);
        sUAltura=findViewById(R.id.unidadesAltura);
        sULongitud= findViewById(R.id.unidadesLongitud);
        sUElasticidad=findViewById(R.id.unidadesElasticidad);
        bGuardar= findViewById(R.id.bGuardar);
        bSiguiente= findViewById(R.id.bSiguiente);
        bSiguiente.setEnabled(false);
        regidityMatrixPorticos= new ArrayList<>();

        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            numElementos = bundle.getInt("numeroElementos");
            Log.i("PorticoDefineElementActivity: numeroElementos= ", Integer.toString(numElementos));
        }
        // Adapter´s Sppiners
        ArrayAdapter<CharSequence> adapterLongitud= ArrayAdapter.createFromResource(this,
                R.array.longitug,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterElasticidad= ArrayAdapter.createFromResource(this,
                R.array.elasticidad,android.R.layout.simple_spinner_item);

        sUBase.setAdapter(adapterLongitud);
        sUAltura.setAdapter(adapterLongitud);
        sULongitud.setAdapter(adapterLongitud);
        sUElasticidad.setAdapter(adapterElasticidad);

        sUBase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String uBase= parent.getItemAtPosition(position).toString();
                switch (uBase){
                    case "m": unidadBase = UnidadesLongitud.m;
                        break;
                    case "cm": unidadBase= UnidadesLongitud.cm;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sUAltura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String uAltura= parent.getItemAtPosition(position).toString();
                switch (uAltura){
                    case "m": unidadAltura = UnidadesLongitud.m;
                        break;
                    case "cm": unidadAltura= UnidadesLongitud.cm;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sULongitud.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String uLongitud= parent.getItemAtPosition(position).toString();
                switch (uLongitud){
                    case "m": unidadLongitud = UnidadesLongitud.m;
                        break;
                    case "cm": unidadLongitud= UnidadesLongitud.cm;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sUElasticidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String uElasticidad= parent.getItemAtPosition(position).toString();
                switch(uElasticidad){
                    case "Pa": unidadElasticidad= UnidadesPresion.Pa;
                        break;
                    case "kPa": unidadElasticidad= UnidadesPresion.kPa;
                        break;
                    case "MPa": unidadElasticidad= UnidadesPresion.MPa;
                        break;
                    case "GPa": unidadElasticidad= UnidadesPresion.GPa;
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void guardarMatrix( EditText eTBase, EditText eTAltura, EditText eTLongitud, EditText eTElasticidad,  EditText eTAngulo, int numeroElementos,ArrayList<RegidityMatrixPortico> regidityMatrixPorticos){


        if((validarCampo(eTBase)&& validarCampo(eTAltura) && validarCampo(eTLongitud)&& validarCampo(eTElasticidad) && validarCampoAngulo(etAngulo))){

            regidityMatrixPorticos.add((indexMatrix-1), new RegidityMatrixPortico(normalizarUnidadLonitud(unidadBase,Double.parseDouble(eTBase.getText().toString())),
                    normalizarUnidadLonitud(unidadAltura,Double.parseDouble(eTAltura.getText().toString())),
                    normalizarUnidadLonitud(unidadLongitud,Double.parseDouble(eTLongitud.getText().toString())),
                    normalizarUnidadPresion(unidadElasticidad,Double.parseDouble(eTElasticidad.getText().toString())), Double.parseDouble(etAngulo.getText().toString())));

            if(indexMatrix< numElementos){
                indexMatrix++;
                tvNumElementos.setText("Elemento " + indexMatrix);
                eTBase.getText().clear();
                eTAltura.getText().clear();
                eTLongitud.getText().clear();
                eTElasticidad.getText().clear();
                eTAngulo.getText().clear();
            }
            else{
                bGuardar.setEnabled(false);
                eTBase.setEnabled(false);
                eTAltura.setEnabled(false);
                eTLongitud.setEnabled(false);
                eTElasticidad.setEnabled(false);
                etAngulo.setEnabled(false);
                sUBase.setEnabled(false);
                sUAltura.setEnabled(false);
                sULongitud.setEnabled(false);
                sUElasticidad.setEnabled(false);
                bSiguiente.setEnabled(true);

            }

        }
        else{
            indexMatrix= indexMatrix;
            Toast.makeText(this, "Uno o más campos vacios o igual a cero", Toast.LENGTH_SHORT).show();
        }


    }
    public void onClick(View view){
        Intent intent=null;
        switch(view.getId()){
            case R.id.bAtras: finish();
                break;
            case R.id.bGuardar: guardarMatrix(etBase, etAltura, etLongitud, etElasticidad, etAngulo, numElementos, regidityMatrixPorticos);
                break;
            case R.id.bSiguiente:intent= new Intent(PorticoDefineElementActivity.this, PorticoMatrixOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("numeroElementos",numElementos);
                bundle.putSerializable("regidityMatrixPorticos", regidityMatrixPorticos);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
