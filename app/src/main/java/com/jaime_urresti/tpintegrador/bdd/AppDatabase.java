package com.jaime_urresti.tpintegrador.bdd;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jaime_urresti.tpintegrador.modelo.Idioma;
import com.jaime_urresti.tpintegrador.modelo.Tarjeta;

@Database(entities = {
        Idioma.class, Tarjeta.class },version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase INSTANCE;

    public abstract TarjetaDAO tarjetaDAO();

    public abstract IdiomaDAO idiomaDAO();


    public synchronized static AppDatabase getInstance(Context context){
        if(INSTANCE == null){

            INSTANCE = buildDatebase(context);

        }

        return INSTANCE;


    }

    private static AppDatabase buildDatebase(final Context context){

        return Room.databaseBuilder(context,AppDatabase.class,"tpintegrador_dam.db").addCallback(
                new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }
                }
        ).build();

    };


}