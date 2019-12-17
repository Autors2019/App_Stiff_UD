package com.civil.stiff.estructuras.cercha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.civil.stiff.R;
import com.civil.stiff.estructuras.cercha.algoritmocercha.matrix.RegidityMatrixCercha;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class CerchaNumberDegreeFreeActivity extends AppCompatActivity {
    // Objetos recibidos
    private int numElementos;
    private ArrayList<RegidityMatrixCercha> regidityMatrixCerchas;
    private ArrayList<Integer[]> ordenElementos;
    private SimpleMatrix matrizConsolidada, vectorFuerzasExt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cercha_number_degree_free);
        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            numElementos = bundle.getInt("numeroElementos");
            regidityMatrixCerchas = (ArrayList<RegidityMatrixCercha>) bundle.getSerializable("regidityMatrixCerchas");
            ordenElementos = (ArrayList<Integer[]>) bundle.getSerializable("ordenElementos");
            matrizConsolidada= (SimpleMatrix) bundle.getSerializable("matrizConsolidada");
            vectorFuerzasExt= (SimpleMatrix) bundle.getSerializable("vectorFuerzasExt");
            // Log matrices
            Log.i("matrizConsolidada", matrizConsolidada.toString());
            Log.i("vectorFuerzasExt", vectorFuerzasExt.toString());
        }

    }
}
