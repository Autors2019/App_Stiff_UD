package com.civil.stiff.verticales.viga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.civil.stiff.R;
import com.civil.stiff.verticales.trasversales.InterfaceElementos;
import com.civil.stiff.verticales.trasversales.InterfaceValidadores;
import com.civil.stiff.verticales.viga.algoritmoviga.matrix.IndexMatrix;
import com.civil.stiff.verticales.viga.algoritmoviga.matrix.RegidityMatrix;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class VigaDefineForceVector extends AppCompatActivity implements InterfaceElementos, InterfaceValidadores {
    private ArrayList<Integer[]> arrayOrdenElementos=null;
    private ArrayList<RegidityMatrix> regidityMatrices = null;
    private EditText eVectorFuerza1;
    private EditText eVectorFuerza2;
    private EditText eVectorFuerza3;
    private EditText eVectorFuerza4;
    private Button   bGuardar;
    private Button   bSiguiente;
    private TextView tElementos;
    private int indexElemento=1;
    private ArrayList<SimpleMatrix> vectoresFuerzas= null;
    private SimpleMatrix vectorFuerza= null;
    private SimpleMatrix k=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viga_define_force_vector);
        eVectorFuerza1= findViewById(R.id.entradaVector1);
        eVectorFuerza2= findViewById(R.id.entradaVector2);
        eVectorFuerza3= findViewById(R.id.entradaVector3);
        eVectorFuerza4= findViewById(R.id.entradaVector4);
        bGuardar= findViewById(R.id.guardar);
        bSiguiente= findViewById(R.id.siguiente);
        bSiguiente.setEnabled(false);
        tElementos= findViewById(R.id.elementos);
        tElementos.setText("Elemento " + indexElemento);

        vectoresFuerzas= new ArrayList<>();
        Bundle objetoEnviado= getIntent().getExtras();
        if(objetoEnviado!=null){
            arrayOrdenElementos= (ArrayList<Integer[]>) objetoEnviado.getSerializable("arrayOrdenElementos");
            regidityMatrices=(ArrayList<RegidityMatrix>) objetoEnviado.getSerializable("regidityMatrices");
        }

        k= consolidarMatriz(arrayOrdenElementos, regidityMatrices);

    }

    private SimpleMatrix consolidarMatriz(ArrayList<Integer[]> arrayOrdenElementos, ArrayList<RegidityMatrix> regidityMatrices){
        SimpleMatrix k= null;
        int elementos= arrayOrdenElementos.size();
        int longitud=0;
        switch(elementos){
            case 2: longitud=6;
                break;
            case 3: longitud=8;
                break;
            case 4: longitud=10;
                break;
            default: longitud=0;
        }
        if(regidityMatrices!=null){
                switch(longitud){
                    case 6: k= IndexMatrix.calculate(regidityMatrices.get(0),arrayOrdenElementos.get(0),longitud);
                            k= k.plus(IndexMatrix.calculate(regidityMatrices.get(1),arrayOrdenElementos.get(1),longitud));
                            break;
                    case 8: k= IndexMatrix.calculate(regidityMatrices.get(0),arrayOrdenElementos.get(0),longitud);
                            k= k.plus(IndexMatrix.calculate(regidityMatrices.get(1),arrayOrdenElementos.get(1),longitud));
                            k= k.plus(IndexMatrix.calculate(regidityMatrices.get(2),arrayOrdenElementos.get(2),longitud));
                            break;
                    case 10: k= IndexMatrix.calculate(regidityMatrices.get(0),arrayOrdenElementos.get(0),longitud);
                            k= k.plus(IndexMatrix.calculate(regidityMatrices.get(1),arrayOrdenElementos.get(1),longitud));
                            k= k.plus(IndexMatrix.calculate(regidityMatrices.get(2),arrayOrdenElementos.get(2),longitud));
                            k= k.plus(IndexMatrix.calculate(regidityMatrices.get(3),arrayOrdenElementos.get(3),longitud));
                        break;
                    default:
                            k=null;
                }

        }
        return k;
    }


    public void guardar(int numeroElementos, EditText eVectorFuerza1, EditText eVectorFuerza2, EditText eVectorFuerza3, EditText eVectorFuerza4, ArrayList<SimpleMatrix> vectoresFuerzas ){

        if( validarCampoCC(eVectorFuerza1) && validarCampoCC(eVectorFuerza2) && validarCampoCC(eVectorFuerza3) && validarCampoCC(eVectorFuerza4)){
            vectorFuerza= new SimpleMatrix(4,1);
            vectorFuerza.set(0,0,Double.parseDouble(eVectorFuerza1.getText().toString()));
            vectorFuerza.set(1,0,Double.parseDouble(eVectorFuerza2.getText().toString()));
            vectorFuerza.set(2,0,Double.parseDouble(eVectorFuerza3.getText().toString()));
            vectorFuerza.set(3,0,Double.parseDouble(eVectorFuerza4.getText().toString()));
            vectoresFuerzas.add(indexElemento-1, vectorFuerza);

            if(indexElemento<numeroElementos) {
                indexElemento++;
                tElementos.setText("Elemento " + indexElemento);
                eVectorFuerza1.getText().clear();
                eVectorFuerza2.getText().clear();
                eVectorFuerza3.getText().clear();
                eVectorFuerza4.getText().clear();
            }
            else{

                eVectorFuerza1.setEnabled(false);
                eVectorFuerza2.setEnabled(false);
                eVectorFuerza3.setEnabled(false);
                eVectorFuerza4.setEnabled(false);
                bGuardar.setEnabled(false);
                bSiguiente.setEnabled(true);

            }

        }

        else{

            Toast.makeText(this,"Uno o más campos están vacios ", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClick(View view){

        Intent intent;

        switch(view.getId()){
            case R.id.atras: finish();
                break;
            case R.id.guardar: guardar(arrayOrdenElementos.size(), eVectorFuerza1, eVectorFuerza2, eVectorFuerza3, eVectorFuerza4, vectoresFuerzas);
                break;
            case R.id.siguiente: intent= new Intent(VigaDefineForceVector.this, VigaDefineForceVectorExt.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("arrayOrdenElementos", arrayOrdenElementos);
                bundle.putSerializable("vectoresFuerzas", vectoresFuerzas);
                bundle.putSerializable("matrizConsolidacion",k);
                bundle.putSerializable("matricesRigidez",regidityMatrices);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
