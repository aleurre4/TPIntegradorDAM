package com.jaime_urresti.tpintegrador.bdd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jaime_urresti.tpintegrador.modelo.Idioma;

import java.util.List;




import java.util.List;

    @Dao
    public interface IdiomaDAO {

        @Update
        public void update(Idioma i);
        @Delete
        public void delete(Idioma i);

        @Insert
        public void insert(Idioma i);

        @Query("SELECT * FROM idiomas")
        public List<Idioma> loadAll();

        @Query("SELECT max(id_idioma) FROM idiomas")
        public Integer getMaxID();

        @Query("SELECT count(id_idioma) FROM idiomas")
        public Integer getCantIdiomas();

    }


