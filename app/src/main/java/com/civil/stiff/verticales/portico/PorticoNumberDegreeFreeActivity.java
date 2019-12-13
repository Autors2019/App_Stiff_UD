package com.civil.stiff.verticales.portico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.civil.stiff.R;
import com.civil.stiff.verticales.portico.algoritmoportico.matrix.RegidityMatrixPortico;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class PorticoNumberDegreeFreeActivity extends AppCompatActivity {
    // Objetos recibidos
    private int numElementos;
    private ArrayList<RegidityMatrixPortico> regidityMatrixPorticos;
    private ArrayList<Integer[]> ordenElementos;
    private ArrayList<SimpleMatrix> vectoresFuerzasInt;
    private SimpleMatrix matrizConsolidada;
    private SimpleMatrix vectorFuerzasExt;
    private SimpleMatrix vectorConsolidado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portico_number_degree_free);
        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            numElementos= bundle.getInt("numeroElementos");
            regidityMatrixPorticos= ( ArrayList<RegidityMatrixPortico>) bundle.getSerializable("regidityMatrixPorticos");
            ordenElementos= (ArrayList<Integer[]>) bundle.getSerializable("ordenElementos");
            vectoresFuerzasInt= (ArrayList<SimpleMatrix>)bundle.getSerializable("vectoresFuerzasInt");
            matrizConsolidada= (SimpleMatrix)bundle.getSerializable("matrizConsolidada");
            vectorFuerzasExt= (SimpleMatrix)bundle.getSerializable("vectorFuerzasExt");
            vectorConsolidado= (SimpleMatrix)bundle.getSerializable("vectorConsolidado");
            // Log vectorFuerzasExt
            Log.i("vectorFuerzasExt",vectorFuerzasExt.toString());
            // Log vectorFuerzasExt
            Log.i("vectorConsolidado",vectorConsolidado.toString());
        }
    }
}
