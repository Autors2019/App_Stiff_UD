package com.civil.stiff.estructuras.cercha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.civil.stiff.R;
import com.civil.stiff.estructuras.portico.PorticoDefineElementActivity;
import com.civil.stiff.estructuras.portico.PorticoElementsActivity;

public class CerchaElementsActivity extends AppCompatActivity {
    private RadioButton r1,r2,r3,r4,r5,r6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cercha_elements);
        // Instancia de vistas
        r1= findViewById(R.id.radioBDos);
        r2= findViewById(R.id.radioBTres);
        r3= findViewById(R.id.radioBCuatro);
        r4= findViewById(R.id.radioBCinco);
        r5= findViewById(R.id.radioBSeis);
        r6= findViewById(R.id.radioBSiete);


    }
    public void onClick(View view){
        Intent intent=null;
        switch (view.getId()){
            case R.id.bAtras: finish();
                break;
            case R.id.bSiguiente: intent= new Intent(CerchaElementsActivity.this, CerchaDefineElementActivity.class);
                Bundle bundle= new Bundle();
                bundle.putInt("numeroElementos", numeroElementos());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
    // Metodo para definir el numero de elementos
    private int numeroElementos(){
        int numElementos=0;
        if(r1.isChecked())numElementos=2;
        if(r2.isChecked())numElementos=3;
        if(r3.isChecked())numElementos=4;
        if(r4.isChecked())numElementos=5;
        if(r5.isChecked())numElementos=6;
        if(r6.isChecked())numElementos=7;
        return numElementos;
    }
}
