package com.civil.stiff.verticales.viga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.civil.stiff.R;
import com.civil.stiff.verticales.trasversales.InterfaceValidadores;
import com.civil.stiff.verticales.viga.algoritmoviga.matrix.RegidityMatrix;
import com.civil.stiff.verticales.trasversales.IndexVector;


import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.List;


public class VigaDefineForceVectorExt extends AppCompatActivity implements InterfaceValidadores {

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
    private List<EditText> eTVectores;

    // Objetos proxima actividad

    private ArrayList<SimpleMatrix> vectoresFuerzas= null;
    private ArrayList<RegidityMatrix> matricesRegidez= null;
    private ArrayList<Integer[]> arrayOrdenElementos=null;
    private SimpleMatrix vectorFuerzaInt=null;
    private SimpleMatrix vectorFuerzaExt=null;
    private SimpleMatrix matrizConsolidacion= null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viga_define_force_vector_ext);
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
        eTVectores= new ArrayList<>();


        Bundle objetoEnviado= getIntent().getExtras();
        if(objetoEnviado!=null){
            arrayOrdenElementos= (ArrayList<Integer[]>) objetoEnviado.getSerializable("arrayOrdenElementos");
            vectoresFuerzas= (ArrayList<SimpleMatrix>) objetoEnviado.getSerializable("vectoresFuerzas");
            matricesRegidez= (ArrayList<RegidityMatrix>) objetoEnviado.getSerializable("matricesRigidez");
            matrizConsolidacion=(SimpleMatrix) objetoEnviado.getSerializable("matrizConsolidacion");
        }

        // Numero de vectores
        numeroVectores(arrayOrdenElementos.size());
        vectorFuerzaExt= new SimpleMatrix(eTVectores.size(),1);
        vectorFuerzaInt = consolidarVector(arrayOrdenElementos,vectoresFuerzas);


    }

    private SimpleMatrix consolidarVector(ArrayList<Integer[]> arrayOrdenElementos,  ArrayList<SimpleMatrix> vectoresFuerzas){
        SimpleMatrix k= null;
        int elementos= arrayOrdenElementos.size();
        int longitud=0;
        switch(elementos){
            case 2: longitud=6;
                break;
            case 3: longitud=8;
                break;
            case 4: longitud=10;
                break;
            default: longitud=0;
        }
        if(vectoresFuerzas!=null){
            switch(longitud){
                case 6: k= IndexVector.calculate(vectoresFuerzas.get(0), arrayOrdenElementos.get(0), longitud);
                        k=k.plus(IndexVector.calculate(vectoresFuerzas.get(1), arrayOrdenElementos.get(1), longitud));
                    break;
                case 8: k= IndexVector.calculate(vectoresFuerzas.get(0), arrayOrdenElementos.get(0), longitud);
                        k=k.plus(IndexVector.calculate(vectoresFuerzas.get(1), arrayOrdenElementos.get(1), longitud));
                        k=k.plus(IndexVector.calculate(vectoresFuerzas.get(2), arrayOrdenElementos.get(2), longitud));
                    break;
                case 10: k= IndexVector.calculate(vectoresFuerzas.get(0), arrayOrdenElementos.get(0), longitud);
                         k=k.plus(IndexVector.calculate(vectoresFuerzas.get(1), arrayOrdenElementos.get(1), longitud));
                         k=k.plus(IndexVector.calculate(vectoresFuerzas.get(2), arrayOrdenElementos.get(2), longitud));
                         k=k.plus(IndexVector.calculate(vectoresFuerzas.get(3), arrayOrdenElementos.get(3), longitud));
                    break;
                default:
                    k=null;
            }

        }
        return k;

    }

    private void numeroVectores(int numeroElementos){
        switch (numeroElementos){
            case 2: this.layoutContenedorVectores.removeView(this.layoutContenedorVector7);
                    this.layoutContenedorVectores.removeView(this.layoutContenedorVector8);
                    this.layoutContenedorVectores.removeView(this.layoutContenedorVector9);
                    this.layoutContenedorVectores.removeView(this.layoutContenedorVector10);
                    eTVectores.add(0,findViewById(R.id.entradaVectorExt1));
                    eTVectores.add(1,findViewById(R.id.entradaVectorExt2));
                    eTVectores.add(2,findViewById(R.id.entradaVectorExt3));
                    eTVectores.add(3,findViewById(R.id.entradaVectorExt4));
                    eTVectores.add(4,findViewById(R.id.entradaVectorExt5));
                    eTVectores.add(5,findViewById(R.id.entradaVectorExt6));
                    break;
            case 3: this.layoutContenedorVectores.removeView(this.layoutContenedorVector9);
                    this.layoutContenedorVectores.removeView(this.layoutContenedorVector10);
                    eTVectores.add(0,findViewById(R.id.entradaVectorExt1));
                    eTVectores.add(1,findViewById(R.id.entradaVectorExt2));
                    eTVectores.add(2,findViewById(R.id.entradaVectorExt3));
                    eTVectores.add(3,findViewById(R.id.entradaVectorExt4));
                    eTVectores.add(4,findViewById(R.id.entradaVectorExt5));
                    eTVectores.add(5,findViewById(R.id.entradaVectorExt6));
                    eTVectores.add(6,findViewById(R.id.entradaVectorExt7));
                    eTVectores.add(7,findViewById(R.id.entradaVectorExt8));
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

        }
    }



    private boolean siguiente(){
        boolean siguienteActividad= false;
        boolean estadoValidacion= true;
        for(EditText et: eTVectores){
            estadoValidacion= validarCampoCC(et) && estadoValidacion;
        }

        if(estadoValidacion){
                  siguienteActividad= true;
                  for(int i=0; i<eTVectores.size(); i++){
                         vectorFuerzaExt.set(i,0, Double.parseDouble(eTVectores.get(i).getText().toString()) );
                  }
        }
        else{
            siguienteActividad=false;
            Toast.makeText(this,"Uno o más campos están vacios ", Toast.LENGTH_SHORT).show();
        }

        return siguienteActividad;
    }

    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.atras: finish();
                break;
            case R.id.siguiente: intent= new Intent(VigaDefineForceVectorExt.this, VigaNumberDegreeFreeActivity.class);
                Bundle bundle= new Bundle();
                if(siguiente()) {
                    bundle.putSerializable("arrayOrdenElementos", arrayOrdenElementos);
                    bundle.putSerializable("vectorFuerzaIntConsolidado",  vectorFuerzaInt);
                    bundle.putSerializable("vectoresFuerzasInternas",  vectoresFuerzas);
                    bundle.putSerializable("vectorFuerzaExt",  vectorFuerzaExt);
                    bundle.putSerializable("matricesRigidez", matricesRegidez);
                    bundle.putSerializable("matrizConsolidacion",matrizConsolidacion);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
        }

    }
}
