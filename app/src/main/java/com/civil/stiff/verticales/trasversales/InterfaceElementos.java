package com.civil.stiff.verticales.trasversales;

import android.widget.EditText;

public interface InterfaceElementos {

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
    default boolean validarCampoAngulo(EditText editText){
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
            estadoCampo=true;
        }
        return  estadoCampo;
    }
    // Metodo para  normalizar unidades
    default double  normalizarUnidadLonitud(UnidadesLongitud unidadesLongitud, double valor){

        switch(unidadesLongitud){
            case m:  valor= valor;
                break;
            case cm: valor= valor/100;
                break;

        }
        return valor;
    }
    default double  normalizarUnidadPresion(UnidadesPresion unidadesPresion, double valor){

        switch(unidadesPresion){
            case Pa:  valor= valor;
                break;
            case kPa: valor= valor*1000;
                break;
            case MPa: valor= valor*1000000;
                break;
            case GPa: valor= valor*1000000000;
                break;
        }
        return valor;
    }

}
