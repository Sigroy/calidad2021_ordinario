package com.anahuac.calidad.doublesDAO;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doAnswer;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class FakeOracleAlumnosDAOTest {

    private FakeOracleAlumnoDAO dao;

    public HashMap<String, Alumno> alumnos;

    Alumno alumno1;
    String nuevoCorreo = "nuevocorreo@hola.com";

    @Before
    public void setUp() throws Exception {
        dao = Mockito.mock(FakeOracleAlumnoDAO.class);
        alumnos = new HashMap<String, Alumno>();
        alumno1 = new Alumno("001", "nombre", 23, "micorreo2@hola.com");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addAlumnoTest() {
        int cuantosAntes = alumnos.size();

        Alumno alumno1 = new Alumno("nombre", "001", 20, "micorreo1@hola.com");

        when(dao.addAlumno(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
            // Method within the class
            public Boolean answer(InvocationOnMock invocation) throws Throwable {

                Alumno arg = (Alumno) invocation.getArguments()[0];
                alumnos.put(anyString(), arg);
                System.out.println("Size despues=" + alumnos.size() + "\n");

                return true;
            }
        });
        dao.addAlumno(alumno1);
        int cuantosDesp = alumnos.size();
        assertThat(cuantosAntes + 1, is(cuantosDesp));
    }

    @Test
    public void deleteAlumnoTest() {
        int cuantosAntes = alumnos.size();
        System.out.println("Size antes = " + cuantosAntes);
        final Alumno alumno1 = new Alumno("nombre", "001", 20, "micorreo@hola.com");

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Alumno arg = (Alumno) invocation.getArguments()[0];
                alumnos.put(anyString(), arg);
                System.out.println("Size después = " + alumnos.size());
                return null;
            }
        }).when(dao).addAlumno(any(Alumno.class));
        dao.addAlumno(alumno1);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Alumno arg = (Alumno) invocation.getArguments()[0];
                alumnos.remove(alumno1);
                System.out.println("Size después = " + alumnos.size());
                return null;
            }
        }).when(dao).deleteAlumno(any(Alumno.class));
        dao.deleteAlumno(alumno1);

    }

    @Test
    public void updateEmailTest() {
        alumnos.put(alumno1.getId(), alumno1);
        String correoAntes = alumno1.getEmail();
        System.out.println("Correo anterior: " + correoAntes);
        alumno1 = new Alumno("Nombre", "001", 20, "micorreo2@hola.com");

        dao.updateEmail(alumno1);
        String correoDesp = alumnos.get(alumno1.getId()).getEmail();
        System.out.println("Correo nuevo: " + correoDesp);
        assertThat(correoAntes, is(not(correoDesp)));
    }
}