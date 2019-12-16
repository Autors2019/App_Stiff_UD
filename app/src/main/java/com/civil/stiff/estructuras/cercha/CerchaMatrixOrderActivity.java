package com.civil.stiff.estructuras.cercha;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.civil.stiff.R;
import com.civil.stiff.estructuras.cercha.algoritmocercha.matrix.RegidityMatrixCercha;
import com.civil.stiff.estructuras.transversales.InterfaceValidadores;

import java.util.ArrayList;

public class CerchaMatrixOrderActivity extends AppCompatActivity implements InterfaceNumGradLibCercha, InterfaceValidadores {
    // Objetos recibidos
    private int numElementos;
    private ArrayList<RegidityMatrixCercha> regidityMatrixCerchas;
    // Referencias de vistas
    private LinearLayout layoutSpinnerOrdenMatriz;
    private LinearLayout.LayoutParams    layoutParametrosSpinners;
    private ArrayAdapter<CharSequence> adaptadorSpinner;
    private ArrayList<Spinner> spinners;
    private Button bGuardar;
    private Button bSiguiente;
    private TextView tvNumeroElemento;
    // Variables atributo
    private final int SPINNERS=4;
    private ArrayList<Integer[]> ordenElementos;
    private int indexOrdenElementos=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cercha_matrix_order);
        Bundle bundle= getIntent().getExtras();
        //Instancias vistas
        bGuardar= findViewById(R.id.bGuardar);
        bSiguiente= findViewById(R.id.bSiguiente);
        bSiguiente.setEnabled(false);
        layoutSpinnerOrdenMatriz= findViewById(R.id.layoutSpinnerOrdenMatriz);
        spinners= new ArrayList<>();
        tvNumeroElemento= findViewById(R.id.numeroElemento);
        tvNumeroElemento.setText("Elemento " + indexOrdenElementos);
        // Instancias de variables atributo
        ordenElementos= new ArrayList<>();

        if(bundle!=null){
            numElementos= bundle.getInt("numeroElementos");
            regidityMatrixCerchas= ( ArrayList<RegidityMatrixCercha>)bundle.getSerializable("regidityMatrixPorticos");
            Log.i("PorticoMatrixOrderActivity: numeroElementos= ", Integer.toString(numElementos));
            for(RegidityMatrixCercha m: regidityMatrixCerchas){
                Log.i("PorticoMatrixOrderActivity: angulo=", Double.toString(m.getAngulo()));
                Log.i("PorticoMatrixOrderActivity: regidityMatrixPorticos=", m.calculate().toString());
            }
        }
        // Set adapatador
        adaptadorSpinner= new ArrayAdapter(this, android.R.layout.simple_spinner_item, numeroGradosLibertad(numElementos));
        layoutParametrosSpinners = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParametrosSpinners.topMargin=60;
        // Agregar sppiner
        for(int i=0; i<SPINNERS; i++){
            spinners.add(i, new Spinner(this));
            spinners.get(i).setAdapter(adaptadorSpinner);
            layoutSpinnerOrdenMatriz.addView(spinners.get(i), i, layoutParametrosSpinners );
        }
    }

    private void guardarOrndenElementos(){
        Integer[] elementoSppiner= new Integer[spinners.size()];

        if(validadorSpinners(spinners)){
            if(indexOrdenElementos < numElementos){
                for(int i=0; i < elementoSppiner.length; i++){
                    elementoSppiner[i]= spinners.get(i).getSelectedItemPosition();
                }
                ordenElementos.add(indexOrdenElementos-1,elementoSppiner);
                indexOrdenElementos++;
                tvNumeroElemento.setText("Elemento " + indexOrdenElementos);
            }
            else{
                for(int i=0; i < elementoSppiner.length; i++){
                    elementoSppiner[i]= spinners.get(i).getSelectedItemPosition();
                }
                ordenElementos.add(indexOrdenElementos-1,elementoSppiner);
                for(int i=0; i < spinners.size(); i++){
                    spinners.get(i).setEnabled(false);
                }
                bGuardar.setEnabled(false);
                bSiguiente.setEnabled(true);
            }
        }
        else{
            Toast.makeText(this, "Uno o mas grados estan varias veces asignados", Toast.LENGTH_LONG).show();
        }

    }
    public void onClick(View view){
        Intent intent= null;
        switch(view.getId()){
            case R.id.bAtras: finish();
                break;
            case R.id.bGuardar: guardarOrndenElementos();
                break;
            case R.id.bSiguiente: intent= new Intent(CerchaMatrixOrderActivity.this, CerchaDefineForceVectorActivity.class);
                Bundle bundle= new Bundle();
                bundle.putInt("numeroElementos", numElementos);
                bundle.putSerializable("regidityMatrixCerchas",regidityMatrixCerchas);
                bundle.putSerializable("ordenElementos", ordenElementos);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

}
