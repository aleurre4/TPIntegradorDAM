package com.jaime_urresti.tpintegrador.bdd;

import java.util.List;

public interface DataSource<T> {

    interface GuardarCallback {
        void resultado(final boolean exito);
    }
    interface RecuperarCallback<T> {
        void resultado(final boolean exito, final List<T> resultados);
    }
    void guardarXYZ(final T entidad, final GuardarCallback callback);
    void recuperarXYZ(final RecuperarCallback callback);




}