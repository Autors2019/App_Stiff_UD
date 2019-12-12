package com.civil.stiff.verticales.portico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.civil.stiff.R;
import com.civil.stiff.verticales.portico.algoritmoportico.matrix.RegidityMatrixPortico;

import java.util.ArrayList;

public class PorticoDefineForceVectorActivity extends AppCompatActivity {

    //Objetos recibidos
    private int numElementos;
    private ArrayList<RegidityMatrixPortico> regidityMatrixPorticos;
    private ArrayList<Integer[]> ordenElementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portico_define_force_vector);
        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            numElementos= bundle.getInt("numeroElementos");
            regidityMatrixPorticos=( ArrayList<RegidityMatrixPortico>) bundle.getSerializable("regidityMatrixPorticos");
            ordenElementos=(ArrayList<Integer[]>) bundle.getSerializable("ordenElementos");
            for(Integer[] vector: ordenElementos){
                for(int i: vector){
                    Log.i("ordenElementos= ", Integer.toString(i));
                }
            }
        }

    }
}
