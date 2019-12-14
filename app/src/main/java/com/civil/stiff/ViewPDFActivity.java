package com.civil.stiff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class ViewPDFActivity extends AppCompatActivity {
    private PDFView pdfView;
    private File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        pdfView=(PDFView) findViewById(R.id.pdfView);
        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            file= new File(bundle.getString("path",""));
        }
        pdfView.fromFile(file)
                .enableSwipe(true)
                //.swipeHorizontal(true)
                .spacing(0)
                .autoSpacing(false)
                .enableDoubletap(true)
                .enableAntialiasing(true)
                .load();
    }

    public void atras(View view){
        finish();
    }
    public  void abrirPdf(View view){
        /*File pdfFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/PDF"+"/viga.pdf");
        Log.i("abrirPdf", pdfFile.getAbsolutePath());*/

        if(file.exists()){
            Uri uri= Uri.fromFile(file);
            Intent intent= new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/pdf");
            try {
                startActivity(intent);
            }
            catch (ActivityNotFoundException e){
                startActivity( new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.adobe.reader")));
                Toast.makeText(getApplicationContext(), "No cuentas con una aplicacion para visualizar PDF", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "El archivo no se encontro", Toast.LENGTH_SHORT).show();
        }


    }

}
