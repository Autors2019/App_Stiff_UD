package com.civil.stiff.verticales.viga;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.civil.stiff.R;
import com.civil.stiff.verticales.viga.algoritmoviga.matrix.RegidityMatrix;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class VigaMatrixOrderActivity extends AppCompatActivity {
    private Spinner  sgradoLibertad1;
    private Spinner  sgradoLibertad2;
    private Spinner  sgradoLibertad3;
    private Spinner  sgradoLibertad4;
    private Button   bGuardar;
    private Button   bSiguiente;
    private TextView tElementos= null;
    private int indexElemento=1;
    private int varGradoLibretad1;
    private int varGradoLibretad2;
    private int varGradoLibretad3;
    private int varGradoLibretad4;
    private int numeroElementos;
    private ArrayList<RegidityMatrix> regidityMatrices = null;

    private ArrayList<Integer[]> arrayOrdenElementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_order);
        sgradoLibertad1= findViewById(R.id.sgradoLibertad1);
        sgradoLibertad2= findViewById(R.id.sgradoLibertad2);
        sgradoLibertad3= findViewById(R.id.sgradoLibertad3);
        sgradoLibertad4= findViewById(R.id.sgradoLibertad4);
        bGuardar= findViewById(R.id.guardarOrden);
        bSiguiente= findViewById(R.id.siguiente);
        arrayOrdenElementos= new ArrayList<>();
        bSiguiente.setEnabled(false);
        tElementos=findViewById(R.id.elementos);
        tElementos.setText("Elemento " + indexElemento);


        // Recive object
        Bundle objetoEnviado= getIntent().getExtras();
        if(objetoEnviado!=null){
            numeroElementos= objetoEnviado.getInt("elementos");
            regidityMatrices=(ArrayList<RegidityMatrix>) objetoEnviado.getSerializable("regidityMatrices");
        }

        // Adapter
        sgradoLibertad1.setAdapter(adapterGradosLibertad(numeroElementos));
        sgradoLibertad2.setAdapter(adapterGradosLibertad(numeroElementos));
        sgradoLibertad3.setAdapter(adapterGradosLibertad(numeroElementos));
        sgradoLibertad4.setAdapter(adapterGradosLibertad(numeroElementos));

        sgradoLibertad1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String grado= parent.getItemAtPosition(position).toString();
                    switch (grado){
                        case "V1": varGradoLibretad1= 0;
                            break;
                        case "θ1": varGradoLibretad1= 1;
                            break;
                        case "V2": varGradoLibretad1= 2;
                            break;
                        case "θ2": varGradoLibretad1= 3;
                            break;
                        case "V3": varGradoLibretad1= 4;
                            break;
                        case "θ3": varGradoLibretad1= 5;
                            break;
                        case "V4": varGradoLibretad1= 6;
                            break;
                        case "θ4": varGradoLibretad1= 7;
                            break;
                        case "V5": varGradoLibretad1= 8;
                            break;
                        case "θ5": varGradoLibretad1= 9;
                            break;
                    }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sgradoLibertad2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String grado= parent.getItemAtPosition(position).toString();
                switch (grado){
                    case "V1": varGradoLibretad2= 0;
                        break;
                    case "θ1": varGradoLibretad2= 1;
                        break;
                    case "V2": varGradoLibretad2= 2;
                        break;
                    case "θ2": varGradoLibretad2= 3;
                        break;
                    case "V3": varGradoLibretad2= 4;
                        break;
                    case "θ3": varGradoLibretad2= 5;
                        break;
                    case "V4": varGradoLibretad2= 6;
                        break;
                    case "θ4": varGradoLibretad2= 7;
                        break;
                    case "V5": varGradoLibretad2= 8;
                        break;
                    case "θ5": varGradoLibretad2= 9;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sgradoLibertad3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String grado= parent.getItemAtPosition(position).toString();
                switch (grado){
                    case "V1": varGradoLibretad3= 0;
                        break;
                    case "θ1": varGradoLibretad3= 1;
                        break;
                    case "V2": varGradoLibretad3= 2;
                        break;
                    case "θ2": varGradoLibretad3= 3;
                        break;
                    case "V3": varGradoLibretad3= 4;
                        break;
                    case "θ3": varGradoLibretad3= 5;
                        break;
                    case "V4": varGradoLibretad3= 6;
                        break;
                    case "θ4": varGradoLibretad3= 7;
                        break;
                    case "V5": varGradoLibretad3= 8;
                        break;
                    case "θ5": varGradoLibretad3= 9;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sgradoLibertad4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String grado= parent.getItemAtPosition(position).toString();
                switch (grado){
                    case "V1": varGradoLibretad4= 0;
                        break;
                    case "θ1": varGradoLibretad4= 1;
                        break;
                    case "V2": varGradoLibretad4= 2;
                        break;
                    case "θ2": varGradoLibretad4= 3;
                        break;
                    case "V3": varGradoLibretad4= 4;
                        break;
                    case "θ3": varGradoLibretad4= 5;
                        break;
                    case "V4": varGradoLibretad4= 6;
                        break;
                    case "θ4": varGradoLibretad4= 7;
                        break;
                    case "V5": varGradoLibretad4= 8;
                        break;
                    case "θ5": varGradoLibretad4= 9;
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public ArrayAdapter<CharSequence> adapterGradosLibertad(int numeroElementos){
        ArrayList<String> gradosLibertad= new ArrayList<>();
        ArrayAdapter<CharSequence> adapterGradosLibertad = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                gradosLibertad);
        switch (numeroElementos){
            case 2: adapterGradosLibertad.insert("V1", 0);
                    adapterGradosLibertad.insert("θ1", 1);
                    adapterGradosLibertad.insert("V2", 2);
                    adapterGradosLibertad.insert("θ2", 3);
                    adapterGradosLibertad.insert("V3", 4);
                    adapterGradosLibertad.insert("θ3", 5);
                    break;
            case 3: adapterGradosLibertad.insert("V1", 0);
                    adapterGradosLibertad.insert("θ1", 1);
                    adapterGradosLibertad.insert("V2", 2);
                    adapterGradosLibertad.insert("θ2", 3);
                    adapterGradosLibertad.insert("V3", 4);
                    adapterGradosLibertad.insert("θ3", 5);
                    adapterGradosLibertad.insert("V4", 6);
                    adapterGradosLibertad.insert("θ4", 7);
                    break;
            case 4: adapterGradosLibertad.insert("V1", 0);
                    adapterGradosLibertad.insert("θ1", 1);
                    adapterGradosLibertad.insert("V2", 2);
                    adapterGradosLibertad.insert("θ2", 3);
                    adapterGradosLibertad.insert("V3", 4);
                    adapterGradosLibertad.insert("θ3", 5);
                    adapterGradosLibertad.insert("V4", 6);
                    adapterGradosLibertad.insert("θ4", 7);
                    adapterGradosLibertad.insert("V5", 8);
                    adapterGradosLibertad.insert("θ5", 9);
                break;
            default:
                    adapterGradosLibertad.clear();
        }
        return  adapterGradosLibertad;
    }


    public void guardar(int numeroElementos, int varGradoLibretad1, int varGradoLibretad2, int varGradoLibretad3, int varGradoLibretad4){

        Set<Integer> varGradosLibertad= new TreeSet<>();
        varGradosLibertad.add(varGradoLibretad1);
        varGradosLibertad.add(varGradoLibretad2);
        varGradosLibertad.add(varGradoLibretad3);
        varGradosLibertad.add(varGradoLibretad4);
        if(varGradosLibertad.size()==4 ){

            if(indexElemento < numeroElementos){
                arrayOrdenElementos.add(indexElemento-1, new Integer[]{varGradoLibretad1,varGradoLibretad2,varGradoLibretad3,varGradoLibretad4});
                indexElemento++;
                tElementos.setText("Elemento " + indexElemento);
            }
            else {
                arrayOrdenElementos.add(indexElemento-1, new Integer[]{varGradoLibretad1,varGradoLibretad2,varGradoLibretad3,varGradoLibretad4});
                sgradoLibertad1.setEnabled(false);
                sgradoLibertad2.setEnabled(false);
                sgradoLibertad3.setEnabled(false);
                sgradoLibertad4.setEnabled(false);
                bGuardar.setEnabled(false);
                bSiguiente.setEnabled(true);
            }
        }
        else{

            Toast.makeText(this, "Uno o más grados están asignados más de una vez", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClick(View view){
        Intent intent= null;
        switch (view.getId()) {

            case R.id.atras: finish();
                             break;
            case R.id.guardarOrden: guardar(numeroElementos, varGradoLibretad1, varGradoLibretad2, varGradoLibretad3, varGradoLibretad4);
                            break;
            case R.id.siguiente:    intent= new Intent(VigaMatrixOrderActivity.this, VigaDefineForceVector.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("arrayOrdenElementos", arrayOrdenElementos);
                                    bundle.putSerializable("regidityMatrices", regidityMatrices);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                            break;
        }
    }
}
