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

    @Before
    public void setUp() throws Exception {
        dao = Mockito.mock(FakeOracleAlumnoDAO.class);
        alumnos = new HashMap<String, Alumno>();
        alumno1 = new Alumno("Nombre", "001", 20, "micorreo@hola.com");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addAlumnoTest() {
        int cuantosAntes = alumnos.size();
        System.out.println("Tamaño antes = " + cuantosAntes);

        when(dao.addAlumno(any(Alumno.class))).thenAnswer(
        		new Answer<Boolean>() {
        			public Boolean answer(InvocationOnMock invocation) throws Throwable {
        				Alumno arg = (Alumno) invocation.getArguments()[0];
        				alumnos.put("001", arg);
        				System.out.println("Tamaño después = " + alumnos.size());

        				return true;
        			}
        });
        dao.addAlumno(alumno1);
        int cuantosDesp = alumnos.size();
        assertThat(cuantosAntes + 1, is(cuantosDesp));
    }
    
    /*
    doAnswer(new Answer() {
        public Object answer(InvocationOnMock invocation) {
            Alumno arg = (Alumno) invocation.getArguments()[0];
            alumnos.put("001", arg);
            System.out.println("Tamaño después = " + alumnos.size());
            return null;
        }
    }).when(dao).addAlumno(any(Alumno.class));
    */
    
    @Test
    public void readAlumnoTest() {
        alumnos.put("001", alumno1);
        int cuantosAntes = alumnos.size();
        System.out.println("Tamaño antes = " + cuantosAntes);

        when(dao.consultarAlumno(any(String.class))).thenAnswer(new Answer<Alumno>(){
                public Alumno answer(InvocationOnMock invocation) throws Throwable {
                    String arg = (String) invocation.getArguments()[0];
                    System.out.println("Leer alumno = " + alumnos.get(arg).getNombre());
                    return alumnos.get(arg);
                }
        });

        dao.consultarAlumno("001");
        int cuantosDespues = alumnos.size();
        System.out.println("Tamaño después = " + cuantosDespues);

        assertThat(cuantosAntes, is(cuantosDespues));
    }
    
    @Test
    public void deleteALumnoTest() {
        alumnos.put("001", alumno1);
        int cuantosAntes = alumnos.size();
        System.out.println("Tamaño antes = " + cuantosAntes);

        when(dao.deleteAlumno(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
                    public Boolean answer(InvocationOnMock invocation) throws Throwable {
                        Alumno arg = (Alumno) invocation.getArguments()[0];
                        alumnos.remove(arg.getId(), arg);

                        return true;
                    }
                }
        );
        
        dao.deleteAlumno(alumno1);
        System.out.println("Tamaño después = " + alumnos.size());
        int cuantosDespues = alumnos.size();
        assertThat(cuantosAntes - 1, is(cuantosDespues));
    }

    @Test
    public void updateEmailTest() {
        alumnos.put(alumno1.getId(), alumno1);
        String correoAntes = alumno1.getEmail();
        System.out.println("Correo antes = " + correoAntes);
        alumno1 = new Alumno("Nombre", "001", 25, "micorreo2@hola.com");

        doAnswer(new Answer(){
            public Object answer(InvocationOnMock invocation){
                Alumno arg = (Alumno) invocation.getArguments()[0];
                alumnos.replace(arg.getId(), arg);
                return null;
            }}).when(dao).updateEmail(any(Alumno.class));
        	dao.updateEmail(alumno1);
        	String correoDesp = alumnos.get(alumno1.getId()).getEmail();
        	System.out.println("Correo después = " + correoDesp);
        	assertThat(correoAntes, is(not(correoDesp)));
    }
}