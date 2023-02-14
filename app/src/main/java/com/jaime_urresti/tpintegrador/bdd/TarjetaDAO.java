package com.jaime_urresti.tpintegrador.bdd;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jaime_urresti.tpintegrador.modelo.Idioma;
import com.jaime_urresti.tpintegrador.modelo.Tarjeta;

import java.util.List;




@Dao
public interface TarjetaDAO {

    @Update
    public void update(Tarjeta t);
    @Delete
    public void delete(Tarjeta t);

    @Insert
    public void insert(Tarjeta t);

    @Query("SELECT * FROM tarjetas")
    public List<Tarjeta> loadAll();


    @Query("SELECT * FROM tarjetas WHERE id_idioma = :idioma_id")
    public List<Tarjeta> loadByIdioma(int idioma_id);

    @Query("SELECT * FROM tarjetas WHERE id_tarjeta = :tarjeta_id")
    public Tarjeta loadByIdTarjeta(int tarjeta_id);


    @Query("SELECT max(id_tarjeta) FROM tarjetas")
    public Integer getMaxID();


    @Query("SELECT count(id_tarjeta) FROM tarjetas")
    public Integer getCantTarjetas();




}


