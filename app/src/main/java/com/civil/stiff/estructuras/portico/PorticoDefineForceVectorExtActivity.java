package com.civil.stiff.estructuras.portico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.civil.stiff.R;
import com.civil.stiff.estructuras.portico.algoritmoportico.matrix.RegidityMatrixPortico;
import com.civil.stiff.estructuras.portico.algoritmoportico.vector.ConsolidatedVectorPortico;
import com.civil.stiff.estructuras.trasversales.InterfaceValidadores;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.List;


public class PorticoDefineForceVectorExtActivity extends AppCompatActivity implements InterfaceValidadores {
    // Objetos recibidos
    private int numElementos;
    private ArrayList<RegidityMatrixPortico> regidityMatrixPorticos;
    private ArrayList<Integer[]> ordenElementos;
    private ArrayList<SimpleMatrix> vectoresFuerzasInt;
    private SimpleMatrix matrizConsolidada;
    // Referencias vistas
    private RelativeLayout layoutContenedorVectores;
    private RelativeLayout layoutContenedorVector1;
    private RelativeLayout layoutContenedorVector2;
    private RelativeLayout layoutContenedorVector3;
    private RelativeLayout layoutContenedorVector4;
    private RelativeLayout layoutContenedorVector5;
    private RelativeLayout layoutContenedorVector6;
    private RelativeLayout layoutContenedorVector7;
    private RelativeLayout layoutContenedorVector8;
    private RelativeLayout layoutContenedorVector9;
    private RelativeLayout layoutContenedorVector10;
    private RelativeLayout layoutContenedorVector11;
    private RelativeLayout layoutContenedorVector12;
    private List<EditText> eTVectores;
    // Variables atributo
    private SimpleMatrix vectorFuerzasExt;
    private SimpleMatrix vectorConsolidado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portico_define_force_vector_ext);
        //Instancias vistas
        layoutContenedorVectores= findViewById(R.id.containerLayout);
        layoutContenedorVector1= findViewById(R.id.layoutVectorExt1);
        layoutContenedorVector2= findViewById(R.id.layoutVectorExt2);
        layoutContenedorVector3= findViewById(R.id.layoutVectorExt3);
        layoutContenedorVector4= findViewById(R.id.layoutVectorExt4);
        layoutContenedorVector5= findViewById(R.id.layoutVectorExt5);
        layoutContenedorVector6= findViewById(R.id.layoutVectorExt6);
        layoutContenedorVector7= findViewById(R.id.layoutVectorExt7);
        layoutContenedorVector8= findViewById(R.id.layoutVectorExt8);
        layoutContenedorVector9= findViewById(R.id.layoutVectorExt9);
        layoutContenedorVector10= findViewById(R.id.layoutVectorExt10);
        layoutContenedorVector11= findViewById(R.id.layoutVectorExt11);
        layoutContenedorVector12= findViewById(R.id.layoutVectorExt12);
        eTVectores= new ArrayList<>();

        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            numElementos= bundle.getInt("numeroElementos");
            regidityMatrixPorticos= ( ArrayList<RegidityMatrixPortico>) bundle.getSerializable("regidityMatrixPorticos");
            ordenElementos= (ArrayList<Integer[]>) bundle.getSerializable("ordenElementos");
            vectoresFuerzasInt= (ArrayList<SimpleMatrix>)bundle.getSerializable("vectoresFuerzasInt");
            matrizConsolidada= (SimpleMatrix)bundle.getSerializable("matrizConsolidada");
            // Log vectoresFuerzasInt
            for(SimpleMatrix i: vectoresFuerzasInt){
                Log.i("PorticoDefineForceVectorExtActivity: 1vectoresFuerzasInt= ", i.toString());
            }
            // Log matrizConsolidada
            Log.i("matrizConsolidada= ", matrizConsolidada.toString());

            try{
                vectorConsolidado= ConsolidatedVectorPortico.calculate(numElementos,ordenElementos,vectoresFuerzasInt,regidityMatrixPorticos);
            }
            catch (Exception e){
                Log.e("vectorConsolidado", vectorConsolidado.toString());
            }
        }
        //numero de vectores
        numeroVectores(numElementos);
        vectorFuerzasExt = new SimpleMatrix(eTVectores.size(),1);
    }
    // numero de vistas
    private void numeroVectores(int numeroElementos){
        switch (numeroElementos){
            case 2: this.layoutContenedorVectores.removeView(this.layoutContenedorVector10);
                this.layoutContenedorVectores.removeView(this.layoutContenedorVector11);
                this.layoutContenedorVectores.removeView(this.layoutContenedorVector12);
                eTVectores.add(0,findViewById(R.id.entradaVectorExt1));
                eTVectores.add(1,findViewById(R.id.entradaVectorExt2));
                eTVectores.add(2,findViewById(R.id.entradaVectorExt3));
                eTVectores.add(3,findViewById(R.id.entradaVectorExt4));
                eTVectores.add(4,findViewById(R.id.entradaVectorExt5));
                eTVectores.add(5,findViewById(R.id.entradaVectorExt6));
                eTVectores.add(6,findViewById(R.id.entradaVectorExt7));
                eTVectores.add(7,findViewById(R.id.entradaVectorExt8));
                eTVectores.add(8,findViewById(R.id.entradaVectorExt9));
                break;

            default:
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

        }
    }
    // toma de valores de la pantalla
    private boolean siguiente(){
        boolean siguienteActividad= false;
        boolean estadoValidacion= true;
        for(EditText et: eTVectores){
            estadoValidacion= validarCampoCC(et) && estadoValidacion;
        }

        if(estadoValidacion){
            siguienteActividad= true;
            for(int i=0; i<eTVectores.size(); i++){
                vectorFuerzasExt.set(i,0, Double.parseDouble(eTVectores.get(i).getText().toString()) );
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
            case R.id.bSiguiente: intent= new Intent(PorticoDefineForceVectorExtActivity.this,PorticoNumberDegreeFreeActivity.class);
                Bundle bundle= new Bundle();
                if(siguiente()) {
                    bundle.putInt("numeroElementos", numElementos);
                    bundle.putSerializable("regidityMatrixPorticos", regidityMatrixPorticos);
                    bundle.putSerializable("ordenElementos", ordenElementos);
                    bundle.putSerializable("vectoresFuerzasInt", vectoresFuerzasInt);
                    bundle.putSerializable("matrizConsolidada",matrizConsolidada);
                    bundle.putSerializable("vectorFuerzasExt",vectorFuerzasExt);
                    bundle.putSerializable("vectorConsolidado",vectorConsolidado);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
        }

    }
}
