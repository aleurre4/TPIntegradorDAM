package com.jaime_urresti.tpintegrador.bdd;


import com.jaime_urresti.tpintegrador.modelo.Tarjeta;

public class TarjetaDataSource implements DataSource<Tarjeta> {

    TarjetaDAO dao;

    public TarjetaDataSource(TarjetaDAO dao){
        this.dao = dao;
    }

    @Override
    public void guardarXYZ(Tarjeta entidad, GuardarCallback callback) {
        dao.insert(entidad);
    }

    @Override
    public void recuperarXYZ(RecuperarCallback callback) {
        dao.loadAll();
    }

}
