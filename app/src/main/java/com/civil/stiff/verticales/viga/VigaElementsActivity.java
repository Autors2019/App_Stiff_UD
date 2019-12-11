package com.civil.stiff.verticales.viga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.civil.stiff.R;
import com.civil.stiff.SelectorActivity;

public class VigaElementsActivity extends AppCompatActivity {

    RadioButton r1,r2,r3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements);
        r1=findViewById(R.id.dosElementos);
        r2=findViewById(R.id.tresElementos);
        r3=findViewById(R.id.cuatroElementos);

    }
    public void onClick(View view){
        Intent intent=null;
        Bundle bundle=null;
        switch (view.getId()){
            case R.id.atras: intent= new Intent(VigaElementsActivity.this, SelectorActivity.class);
                                    break;
            case R.id.siguiente: intent= new Intent(VigaElementsActivity.this, VigaDefineElementActivity.class);
                                    bundle= new Bundle();
                                    bundle.putInt("elementos", numeroElementos());
                                    intent.putExtras(bundle);
                                    break;
        }
        startActivity(intent);
    }

    public int numeroElementos(){
        int elementos=0;
        if(r1.isChecked()) elementos=2;
        if(r2.isChecked()) elementos=3;
        if(r3.isChecked()) elementos=4;
        return  elementos;
    }


}
