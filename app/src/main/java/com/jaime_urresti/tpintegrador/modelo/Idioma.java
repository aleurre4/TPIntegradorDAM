package com.jaime_urresti.tpintegrador.modelo;


import androidx.annotation.NonNull;
import androidx.room.*;


@Entity(tableName = "idiomas")
public class Idioma {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_idioma")
    private Integer idIdioma;

    @ColumnInfo(name = "nombre")
    private String nombreIdioma;


    @ColumnInfo(name = "fecha_creacion")
    private Integer fechaCreacion;

//    private List<Tarjeta> tarjetas;

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getNombreIdioma() {
        return nombreIdioma;
    }

    public void setNombreIdioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }

//    public List<Tarjeta> getTarjetas() {
//        return tarjetas;
//    }

//    public void setTarjetas(List<Tarjeta> tarjetas) {
//        this.tarjetas = tarjetas;
//    }

    public Integer getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Integer fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return nombreIdioma;
    }
}
