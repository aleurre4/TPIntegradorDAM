package com.jaime_urresti.tpintegrador.modelo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "tarjetas")
public class Tarjeta {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_tarjeta")
    private Integer idTarjeta;

    @ColumnInfo(name = "texto_original")
    private String textoOrignal;

    @ColumnInfo(name = "texto_traduccion")
    private String textoTraduccion;

    @ColumnInfo(name = "audio")
    private String audio;

    @ColumnInfo(name = "id_idioma")
    private Integer idIdioma;

    @ColumnInfo(name = "fecha_creacion")
    private Integer fechaCreacion;

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public Integer getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Integer fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(Integer idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getTextoOrignal() {
        return textoOrignal;
    }

    public void setTextoOrignal(String textoOrignal) {
        this.textoOrignal = textoOrignal;
    }

    public String getTextoTraduccion() {
        return textoTraduccion;
    }

    public void setTextoTraduccion(String textoTraduccion) {
        this.textoTraduccion = textoTraduccion;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
