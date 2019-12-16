package com.civil.stiff.estructuras.cercha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.civil.stiff.R;
import com.civil.stiff.estructuras.cercha.algoritmocercha.RegidityMatrixCercha;
import com.civil.stiff.estructuras.portico.algoritmoportico.matrix.RegidityMatrixPortico;
import com.civil.stiff.estructuras.transversales.InterfaceElementos;
import com.civil.stiff.estructuras.transversales.InterfaceValidadores;
import com.civil.stiff.estructuras.transversales.UnidadesArea;
import com.civil.stiff.estructuras.transversales.UnidadesLongitud;
import com.civil.stiff.estructuras.transversales.UnidadesPresion;

import java.util.ArrayList;

public class CerchaDefineElementActivity extends AppCompatActivity  implements InterfaceElementos, InterfaceValidadores {
    // rerencias vistas
    private TextView tvNumElementos;
    private EditText etArea;
    private EditText etLongitud;
    private EditText etElasticidad;
    private EditText etAngulo;
    private Spinner  sUArea;
    private Spinner  sULongitud;
    private Spinner  sUElasticidad;
    private Button   bGuardar;
    private Button   bSiguiente;
    // Variables de atributo
    private int numElementos;
    private UnidadesArea unidadArea;
    private UnidadesLongitud unidadLongitud;
    private UnidadesPresion unidadElasticidad;
    private int indexMatrix=1;
    private ArrayList<RegidityMatrixCercha> regidityMatrixCercha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cercha_define_element);
        // Instanciar vistas
        tvNumElementos= findViewById(R.id.numeroElemento);
        tvNumElementos.setText("Elemento " + indexMatrix);
        etArea =findViewById(R.id.entradaArea);
        etLongitud =findViewById(R.id.entradaLongitud);
        etElasticidad=findViewById(R.id.entradaElasticidad);
        etAngulo= findViewById(R.id.entradaAngulo);
        sUArea=findViewById(R.id.unidadesArea);
        sULongitud= findViewById(R.id.unidadesLongitud);
        sUElasticidad=findViewById(R.id.unidadesElasticidad);
        bGuardar= findViewById(R.id.bGuardar);
        bSiguiente= findViewById(R.id.bSiguiente);
        bSiguiente.setEnabled(false);
        regidityMatrixCercha= new ArrayList<>();
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
        ArrayAdapter<CharSequence> adapterArea= ArrayAdapter.createFromResource(this,
                R.array.area,android.R.layout.simple_spinner_item);
        sUArea.setAdapter(adapterArea);
        sULongitud.setAdapter(adapterLongitud);
        sUElasticidad.setAdapter(adapterElasticidad);
        sUArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String uLongitud= parent.getItemAtPosition(position).toString();
                switch (uLongitud){
                    case "m2": unidadArea = UnidadesArea.m2;
                        break;
                    case "cm2": unidadArea= UnidadesArea.cm2;
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

    private void guardarMatrix( ){


        if(validarCampo(etArea) && validarCampo(etLongitud)&& validarCampo(etElasticidad) && validarCampoCC(this.etAngulo)){
            regidityMatrixCercha.add((indexMatrix-1), new RegidityMatrixCercha(normalizarUnidadArea(unidadArea,Double.parseDouble(etArea.getText().toString())),
                    normalizarUnidadLonitud(unidadLongitud,Double.parseDouble(etLongitud.getText().toString())),
                    normalizarUnidadPresion(unidadElasticidad,Double.parseDouble(etElasticidad.getText().toString())), Double.parseDouble(this.etAngulo.getText().toString())));

            if(indexMatrix< numElementos){
                indexMatrix++;
                tvNumElementos.setText("Elemento " + indexMatrix);
                etArea.getText().clear();
                etLongitud.getText().clear();
                etElasticidad.getText().clear();
                etAngulo.getText().clear();
            }
            else{
                bGuardar.setEnabled(false);
                etArea.setEnabled(false);
                etLongitud.setEnabled(false);
                etElasticidad.setEnabled(false);
                this.etAngulo.setEnabled(false);
                sUArea.setEnabled(false);
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
            case R.id.bGuardar: guardarMatrix();
                break;
            case R.id.bSiguiente:intent= new Intent(CerchaDefineElementActivity.this, CerchaMatrixOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("numeroElementos",numElementos);
                bundle.putSerializable("regidityMatrixPorticos", regidityMatrixCercha);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
