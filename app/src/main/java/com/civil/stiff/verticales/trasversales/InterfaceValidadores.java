package com.civil.stiff.verticales.trasversales;

import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public interface InterfaceValidadores {
    // Metodo para validar campo
    default boolean validarCampo(EditText editText){
        String text= editText.getText().toString();
        boolean estadoCampo=false;
        double numero=0.0;
        // Estado del campo
        if(text.matches("")){
            estadoCampo=false;
        }
        else{
            numero= Double.parseDouble(text);
            //Campo diferente a cero
            if(numero!=0.0){
                estadoCampo=true;
            }
            else{
                estadoCampo=false;
            }
        }
        return  estadoCampo;
    }
    // Metodo para validar angulo
    default boolean validarCampoCC(EditText editText){
        String text= editText.getText().toString();
        boolean estadoCampo=false;
        // Estado del campo
        if(text.matches("")||text.equals(".")){
            estadoCampo=false;
        }
        else{
            estadoCampo=true;
        }
        return  estadoCampo;
    }
    // Validador spinner
    default boolean validadorSpinners(ArrayList<Spinner> spinners){
        boolean status= false;
        Set<Integer> listaNoDuplicados= new TreeSet<>();
        for(int i=0; i< spinners.size(); i++){
            listaNoDuplicados.add(spinners.get(i).getSelectedItemPosition());
        }
        if( listaNoDuplicados.size() == spinners.size()) status= true;
        else status= false;
        return status;
    }
}
