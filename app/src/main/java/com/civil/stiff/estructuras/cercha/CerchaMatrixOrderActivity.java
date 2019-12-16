package com.civil.stiff.estructuras.cercha;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.civil.stiff.R;
import com.civil.stiff.estructuras.cercha.algoritmocercha.RegidityMatrixCercha;

import java.util.ArrayList;

public class CerchaMatrixOrderActivity extends AppCompatActivity {
    // Objetos recibidos
    private int numElementos;
    private ArrayList<RegidityMatrixCercha> regidityMatrixCerchas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cercha_matrix_order);
        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            numElementos= bundle.getInt("numeroElementos");
            regidityMatrixCerchas= ( ArrayList<RegidityMatrixCercha>)bundle.getSerializable("regidityMatrixPorticos");
            Log.i("PorticoMatrixOrderActivity: numeroElementos= ", Integer.toString(numElementos));
            for(RegidityMatrixCercha m: regidityMatrixCerchas){
                Log.i("PorticoMatrixOrderActivity: angulo=", Double.toString(m.getAngulo()));
                Log.i("PorticoMatrixOrderActivity: regidityMatrixPorticos=", m.calculate().toString());
            }
        }
    }

}
