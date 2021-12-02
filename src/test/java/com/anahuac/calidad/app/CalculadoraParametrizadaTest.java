package com.anahuac.calidad.app;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
// import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

// import org.hamcrest.Matchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalculadoraParametrizadaTest {
    private double arg1; 
    private double arg2; 
    private double arg3; 
    private Calculadora miCalculadora; 

    // set arguments to test
    public CalculadoraParametrizadaTest(double arg1, double arg2, double arg3) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
    }

    // set parameters to test
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { 4, 2, 2 }, // Integers
                { 6, -3, -2 }, // Negative Integers
                { 5, 5, 1 }, // Same number
                { 5, 2, 2.5 }, // decimal
                { 5, -2, -2.5 }, // negative decimal
                { 10, 0, Double.POSITIVE_INFINITY }, // INFINITY
                { 0, 0, Double.NaN } // not a number
        });
    }

    @Before
    public void setUp() throws Exception {
        
        miCalculadora = new Calculadora();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        double resultadoEjecucion = miCalculadora.division(this.arg1, this.arg2);
        assertThat(this.arg3, is(resultadoEjecucion));
    }
}