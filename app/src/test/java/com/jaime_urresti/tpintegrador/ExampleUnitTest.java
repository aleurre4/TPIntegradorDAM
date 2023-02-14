package com.jaime_urresti.tpintegrador;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void nivelPrincipianteCorrecto() {

        Integer cantidadTarjetas = 0;
        Integer cantidadIdiomas = 0;

        Integer nivel = PrimerFragment.calcularNivel(cantidadIdiomas,cantidadTarjetas);

        Integer esperado = 0;

        assertEquals(esperado, nivel);
    }

    @Test
    public void nivelMessiCorrecto() {
        Integer cantidadTarjetas = 25;
        Integer cantidadIdiomas = 6;

        Integer nivel = PrimerFragment.calcularNivel(cantidadIdiomas,cantidadTarjetas);
        Integer esperado = 4;

        assertEquals(esperado, nivel);
    }






}