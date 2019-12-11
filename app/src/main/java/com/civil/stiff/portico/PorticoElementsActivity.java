package com.civil.stiff.portico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.civil.stiff.R;

public class PorticoElementsActivity extends AppCompatActivity {
    private RadioButton r1,r2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portico_elements);
        // Instancia de vistas
        r1= findViewById(R.id.radioBDos);
        r2= findViewById(R.id.radioBTres);
    }

    public void onClick(View view){
        Intent intent=null;
        switch (view.getId()){
            case R.id.bAtras: finish();
                break;
            case R.id.bSiguiente: intent= new Intent(PorticoElementsActivity.this, PorticoDefineElementActivity.class);
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
        return numElementos;
    }
}
