package com.civil.stiff.verticales.portico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.civil.stiff.R;
import com.civil.stiff.verticales.portico.algoritmoportico.matrix.RegidityMatrixPortico;

import java.util.ArrayList;

public class PorticoMatrixOrderActivity extends AppCompatActivity {
    private int numElementos;
    private ArrayList<RegidityMatrixPortico> regidityMatrixPorticos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portico_matrix_order);
        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            numElementos= bundle.getInt("numeroElementos");
            regidityMatrixPorticos= ( ArrayList<RegidityMatrixPortico>)bundle.getSerializable("regidityMatrixPorticos");
            Log.i("PorticoMatrixOrderActivity: numeroElementos= ", Integer.toString(numElementos));
            for(RegidityMatrixPortico m: regidityMatrixPorticos){
                Log.i("PorticoMatrixOrderActivity: regidityMatrixPorticos=", m.calculate().toString());
            }
        }
    }
}
