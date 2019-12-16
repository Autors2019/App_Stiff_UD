package com.civil.stiff.estructuras.cercha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.civil.stiff.R;
import com.civil.stiff.estructuras.cercha.algoritmocercha.matrix.RegidityMatrixCercha;


import java.util.ArrayList;

public class CerchaDefineForceVectorActivity extends AppCompatActivity {
    //Objetos recibidos
    private int numElementos;
    private ArrayList<RegidityMatrixCercha> regidityMatrixCerchas;
    private ArrayList<Integer[]> ordenElementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cercha_define_force_vector);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            numElementos = bundle.getInt("numeroElementos");
            regidityMatrixCerchas = (ArrayList<RegidityMatrixCercha>) bundle.getSerializable("regidityMatrixCerchas");
            ordenElementos = (ArrayList<Integer[]>) bundle.getSerializable("ordenElementos");
            // Log orden de  elementos
            for (Integer[] vector : ordenElementos) {
                for (int i : vector) {
                    Log.i("ordenElementos= ", Integer.toString(i));
                }
            }
            /*
            // Calcular matriz consolidada
            try {

            }
            catch (Exception e){
                Log.e( "matrizConsolidada: ", e.toString());
            }*/

        }
    }


}


