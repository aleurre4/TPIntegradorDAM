package com.jaime_urresti.tpintegrador.bdd;

import com.jaime_urresti.tpintegrador.modelo.Idioma;

public class IdiomaDataSource implements DataSource<Idioma> {

    IdiomaDAO dao;

    public IdiomaDataSource(IdiomaDAO dao){
        this.dao = dao;
    }

    @Override
    public void guardarXYZ(Idioma entidad, GuardarCallback callback) {
        dao.insert(entidad);
    }

    @Override
    public void recuperarXYZ(RecuperarCallback callback) {
        dao.loadAll();
    }

}
