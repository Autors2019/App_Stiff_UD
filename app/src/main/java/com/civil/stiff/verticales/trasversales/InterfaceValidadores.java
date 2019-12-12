package com.civil.stiff.verticales.trasversales;

import android.widget.EditText;

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
}
