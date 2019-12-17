package com.civil.stiff.estructuras.cercha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.civil.stiff.R;
import com.civil.stiff.estructuras.cercha.algoritmocercha.matrix.ConsolidatedMatrixCercha;
import com.civil.stiff.estructuras.cercha.algoritmocercha.matrix.RegidityMatrixCercha;
import com.civil.stiff.estructuras.cercha.algoritmocercha.matrix.SizeMatrixCercha;
import com.civil.stiff.estructuras.transversales.InterfaceValidadores;


import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class CerchaDefineForceVectorExtActivity extends AppCompatActivity  implements InterfaceValidadores {
    //Objetos recibidos
    private int numElementos;
    private ArrayList<RegidityMatrixCercha> regidityMatrixCerchas;
    private ArrayList<Integer[]> ordenElementos;
    private SimpleMatrix matrizConsolidada;
    // Referencias vistas
    private RelativeLayout layoutContenedorVectores;
    private ArrayList<RelativeLayout> layoutsVectores;
    private ArrayList<EditText> eTVectores, eTVectoresS;

    // Variables atributo
    private SimpleMatrix vectorFuerzasExt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cercha_define_force_vector);
        //Instancias vistas
        layoutContenedorVectores= findViewById(R.id.containerLayout);
        eTVectores= new ArrayList<>();
        eTVectoresS = new ArrayList<>();
        eTVectores.add(0,findViewById(R.id.entradaVectorExt1));
        eTVectores.add(1,findViewById(R.id.entradaVectorExt2));
        eTVectores.add(2,findViewById(R.id.entradaVectorExt3));
        eTVectores.add(3,findViewById(R.id.entradaVectorExt4));
        eTVectores.add(4,findViewById(R.id.entradaVectorExt5));
        eTVectores.add(5,findViewById(R.id.entradaVectorExt6));
        eTVectores.add(6,findViewById(R.id.entradaVectorExt7));
        eTVectores.add(7,findViewById(R.id.entradaVectorExt8));
        eTVectores.add(8,findViewById(R.id.entradaVectorExt9));
        eTVectores.add(9,findViewById(R.id.entradaVectorExt10));
        eTVectores.add(10,findViewById(R.id.entradaVectorExt11));
        eTVectores.add(11,findViewById(R.id.entradaVectorExt12));
        eTVectores.add(12,findViewById(R.id.entradaVectorExt13));
        eTVectores.add(13,findViewById(R.id.entradaVectorExt14));
        eTVectores.add(14,findViewById(R.id.entradaVectorExt15));
        eTVectores.add(15,findViewById(R.id.entradaVectorExt16));

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
            // Calcular matriz consolidada
            try {
                matrizConsolidada= ConsolidatedMatrixCercha.calculate(ordenElementos,regidityMatrixCerchas);
            }
            catch (Exception e){
                Log.e( "matrizConsolidada: ", e.toString());
            }

        }
        int size= new SizeMatrixCercha(ordenElementos).calculate();
        numeroVectores(size);
        vectorFuerzasExt = new SimpleMatrix(size,1);
    }

    private void numeroVectores(int sizeMatrixCercha){
        ArrayList<Integer> a= new ArrayList<>();
        ArrayList<Integer> b= new ArrayList<>();
        // Llenar vector a
        for(int i=0; i< sizeMatrixCercha; i++){
            a.add(i);
        }
        // LLenar vector b
        for(int i=0; i< 16; i++){
            b.add(i);
        }
        // Remover elementos a en b
        for(Integer v: a){
            b.remove(v);
        }
        b.forEach(s-> Log.i("b:", s.toString()));
        // Remover vistas
        layoutContenedorVectores.removeViews(b.get(0),b.size());
        // Vectores seleccionados
        for(int i: a){
         eTVectoresS.add(eTVectores.get(i));
        }

        Log.i("eTVectoresS size :", Integer.toString(eTVectoresS.size()));
    }

    // toma de valores de la pantalla
    private boolean siguiente(){
        boolean siguienteActividad= false;
        boolean estadoValidacion= true;
        for(EditText et: eTVectoresS){
            estadoValidacion= validarCampoCC(et) && estadoValidacion;
        }

        if(estadoValidacion){
            siguienteActividad= true;
            for(int i = 0; i< eTVectoresS.size(); i++){
                vectorFuerzasExt.set(i,0, Double.parseDouble(eTVectoresS.get(i).getText().toString()) );
            }
        }
        else{
            siguienteActividad=false;
            Toast.makeText(this,"Uno o más campos están vacios ", Toast.LENGTH_SHORT).show();
        }

        return siguienteActividad;
    }
    public  void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.bAtras: finish();
                break;
            case R.id.bSiguiente:
                intent= new Intent(CerchaDefineForceVectorExtActivity.this,CerchaNumberDegreeFreeActivity.class);
                Bundle bundle= new Bundle();
                if(siguiente()) {
                    bundle.putInt("numeroElementos", numElementos);
                    bundle.putSerializable("regidityMatrixPorticos", regidityMatrixCerchas);
                    bundle.putSerializable("ordenElementos", ordenElementos);
                    bundle.putSerializable("matrizConsolidada",matrizConsolidada);
                    bundle.putSerializable("vectorFuerzasExt",vectorFuerzasExt);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;

        }

    }

}


