package com.civil.stiff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.civil.stiff.viga.VigaElementsActivity;

public class SelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        Intent intent = null;
        switch(view.getId()){
            case R.id.viga: intent= new Intent(SelectorActivity.this, VigaElementsActivity.class);
                            break;
        }
        startActivity(intent);
    }
}
