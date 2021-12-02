package com.anahuac.calidad.app;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrimeraPrueba {
    @Before
    public void setUp() throws Exception {
        System.out.println("Este es el before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Este es el after");
    }

    @Test
    public void Primertest() {
        System.out.println("Este es el primer test");
        fail("Not yet implemented");
    }

    @Test
    public void Segundotest() {
        System.out.println("Este es el segundo test");
        fail("Not yet implemented");
    }

    @Test
    public void Tercertest() {
        System.out.println("Este es el tercer Test");
        fail("Not yet implemented");
    }
}