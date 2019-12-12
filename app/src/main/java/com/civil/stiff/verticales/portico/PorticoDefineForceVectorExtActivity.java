package com.civil.stiff.verticales.portico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.civil.stiff.R;
import com.civil.stiff.verticales.portico.algoritmoportico.matrix.RegidityMatrixPortico;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;


public class PorticoDefineForceVectorExtActivity extends AppCompatActivity {
    // Objetos recibidos
    private int numElementos;
    private ArrayList<RegidityMatrixPortico> regidityMatrixPorticos;
    private ArrayList<Integer[]> ordenElementos;
    private ArrayList<SimpleMatrix> vectoresFuerzasInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portico_define_force_vector_ext);
        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            numElementos= bundle.getInt("numeroElementos");
            regidityMatrixPorticos= ( ArrayList<RegidityMatrixPortico>) bundle.getSerializable("regidityMatrixPorticos");
            ordenElementos= (ArrayList<Integer[]>) bundle.getSerializable("ordenElementos");
            vectoresFuerzasInt= (ArrayList<SimpleMatrix>)bundle.getSerializable("vectoresFuerzasInt");
            // Log
            for(SimpleMatrix i: vectoresFuerzasInt){
                Log.i("PorticoDefineForceVectorExtActivity: vectoresFuerzasInt= ", i.toString());
            }
        }

    }
}
