package com.jaime_urresti.tpintegrador.util;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class ClaseUtil {


    public static void alerta(String cadena, String titulo, Activity activity) {

        try{
            AlertDialog.Builder builder= new AlertDialog.Builder(activity);
            builder.setTitle(titulo);
            builder.setMessage(cadena);
            builder.setPositiveButton("Joya maestro",null);
            builder.create();
            builder.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
