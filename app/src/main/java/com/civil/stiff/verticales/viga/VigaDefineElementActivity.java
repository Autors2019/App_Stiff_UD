package com.civil.stiff.verticales.viga;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.civil.stiff.verticales.trasversales.InterfaceElementos;
import com.civil.stiff.verticales.trasversales.InterfaceValidadores;
import com.civil.stiff.verticales.trasversales.UnidadesLongitud;
import com.civil.stiff.verticales.trasversales.UnidadesPresion;
import com.civil.stiff.verticales.viga.algoritmoviga.matrix.RegidityMatrix;
import com.civil.stiff.R;


import java.util.ArrayList;



public class VigaDefineElementActivity extends AppCompatActivity implements InterfaceElementos, InterfaceValidadores {

    private int numeroElementos;
    private EditText tBase=null;
    private EditText tAltura= null;
    private EditText tLongitud= null;
    private EditText tElasticidad =null;
    private Button   bGuargar = null;
    private Button   bSiguiente= null;
    private TextView tElementos= null;
    private Spinner  sUBase= null;
    private Spinner  sUAltura= null;
    private Spinner  sULongitud= null;
    private Spinner  sUElasticidad= null;

    private int indexMatrix=1;
    private ArrayList <RegidityMatrix> regidityMatrices;

    private UnidadesLongitud unidadBase;
    private UnidadesLongitud unidadAltura;
    private UnidadesLongitud unidadLongitud;
    private UnidadesPresion unidadElasticidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_elemnt);
        // Instanciación de objetos
        tBase =findViewById(R.id.entradaBase);
        tAltura =findViewById(R.id.entradaAltura);
        tLongitud = findViewById(R.id.entradaLongitud);
        tElasticidad= findViewById(R.id.entradaElasticidad);
        sUBase= findViewById(R.id.unidadesBase);
        sUAltura= findViewById(R.id.unidadesAltura);
        sULongitud= findViewById(R.id.unidadesLongitud);
        sUElasticidad= findViewById(R.id.unidadesElasticidad);
        bGuargar= findViewById(R.id.guardar);
        bSiguiente=findViewById(R.id.siguiente);
        bSiguiente.setEnabled(false);
        tElementos= findViewById(R.id.elementos);
        tElementos.setText("Elemento " + indexMatrix);
        regidityMatrices= new ArrayList<>();

        Bundle bundle= this.getIntent().getExtras();
        if(bundle!=null){
            numeroElementos= bundle.getInt("elementos");
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


    private void guardarMatrix( EditText eTBase, EditText eTAltura, EditText eTLongitud, EditText eTElasticidad, int elementos,ArrayList<RegidityMatrix> regidityMatrices){


        if((validarCampo(eTBase)&& validarCampo(eTAltura) && validarCampo(eTLongitud)&& validarCampo(eTElasticidad))){

            regidityMatrices.add((indexMatrix-1), new RegidityMatrix(normalizarUnidadLonitud(unidadBase,Double.parseDouble(eTBase.getText().toString())),
                    normalizarUnidadLonitud(unidadAltura,Double.parseDouble(eTAltura.getText().toString())),
                    normalizarUnidadLonitud(unidadLongitud,Double.parseDouble(eTLongitud.getText().toString())),
                    normalizarUnidadPresion(unidadElasticidad,Double.parseDouble(eTElasticidad.getText().toString()))));

            if(indexMatrix<elementos){
                indexMatrix++;
                tElementos.setText("Elemento " + indexMatrix);
                eTBase.getText().clear();
                eTAltura.getText().clear();
                eTLongitud.getText().clear();
                eTElasticidad.getText().clear();
            }
            else{
                bGuargar.setEnabled(false);
                eTBase.setEnabled(false);
                eTAltura.setEnabled(false);
                eTLongitud.setEnabled(false);
                eTElasticidad.setEnabled(false);
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
            case R.id.atras: finish();
                            break;
            case R.id.guardar: guardarMatrix(tBase, tAltura, tLongitud, tElasticidad, numeroElementos,regidityMatrices);
                            break;

            case R.id.siguiente: intent= new Intent(VigaDefineElementActivity.this, VigaMatrixOrderActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("elementos",numeroElementos);
                            bundle.putSerializable("regidityMatrices", regidityMatrices);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;
        }

    }

}
