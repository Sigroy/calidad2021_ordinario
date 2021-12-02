package com.anahuac.calidad.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
// import com.anahuac.calidad.app.Calculadora;

public class CalculadoraTest {

    private Calculadora miCalculadora;

    @Before
    public void setUp() throws Exception {
        miCalculadora = new Calculadora();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void SumaTest() {
        double resultadoEsperado = 4; 
        double resultadoEjecucion = miCalculadora.suma(2.0f, 2.0f);

        assertEquals(resultadoEsperado, resultadoEjecucion, .01);
    }

    @Test
    public void RestaTest() {
        double resultadoEsperado = 5;

        double resultadoEjecucion = miCalculadora.resta(8.0f, 3.0f);

        assertEquals(resultadoEsperado, resultadoEjecucion, .01);
    }

    @Test(/* expected = ArithmeticException.class */)
    public void divisionEnteraTest() {
        int resultadoEsperado = 2;
        int resultadoEjecucion = miCalculadora.divisionEntera(10, 5);

        assertThat(resultadoEsperado, is(resultadoEjecucion));
    }

}