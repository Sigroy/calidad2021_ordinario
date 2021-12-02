package com.anahuac.calidad.dbunit;

import static org.junit.Assert.*;
// FIle Imports
import java.io.File;

// DB Unit test imports
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.FilteredTableMetaData;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.RowFilterTable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
// Test Imports
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.anahuac.calidad.doublesDAO.Alumno;
import com.anahuac.calidad.dbunit.AlumnoDAOMysql;

public class DAOALumnoTest extends DBTestCase {

    String nuevoCorreo = "hola003@gmail.com";
    // Declare DAO
    private AlumnoDAOMysql daoMySql;

    public DAOALumnoTest() {
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.cj.jdbc.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                "jdbc:mysql://localhost:33060/pruebas_db");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "secret");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {

        return new FlatXmlDataSetBuilder().build(new File("src/resources/iniDB.xml"));
    }

    @Before
    public void setUp() throws Exception {

        daoMySql = new AlumnoDAOMysql();
        IDatabaseConnection connection = getConnection();
        try {
            DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
        } catch (Exception e) {
            fail("Error in setup: " + e.getMessage());
        } finally {
            connection.close();
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddAlumno() {
        Alumno alumno = new Alumno("004", "alumno004", 18, "hola4@hola.com");

        daoMySql.addAlumno(alumno);

        try {
            // DB
            IDataSet databaseDataSet = getConnection().createDataSet();

            ITable actualTable = databaseDataSet.getTable("alumnos_tbl");

            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
            ITable expectedTable = expectedDataSet.getTable("alumnos_tbl");

            Assertion.assertEquals(expectedTable, actualTable);

        } catch (Exception e) {
            fail("Error in insert test: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteAlumno() {
        Alumno alumno = new Alumno("003", "alumno003", 17, "hola3@hola.com");

        daoMySql.deleteAlumno(alumno);

        try {
            // DB
            IDataSet databaseDataSet = getConnection().createDataSet();

            ITable actualTable = databaseDataSet.getTable("alumnos_tbl");

            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/delete_result.xml"));
            ITable expectedTable = expectedDataSet.getTable("alumnos_tbl");

            Assertion.assertEquals(expectedTable, actualTable);

        } catch (Exception e) {
            fail("Error in insert test: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateEmail() {
        Alumno alumno = new Alumno("003", "alumno003", 17, "hola3@hola.com");

        alumno.setEmail(nuevoCorreo);

        daoMySql.updateEmail(alumno);

        try {
            // DB
            IDataSet databaseDataSet = getConnection().createDataSet();

            ITable actualTable = databaseDataSet.getTable("alumnos_tbl");

            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/update_result.xml"));
            ITable expectedTable = expectedDataSet.getTable("alumnos_tbl");

            Assertion.assertEquals(expectedTable, actualTable);

        } catch (Exception e) {
            fail("Error in insert test: " + e.getMessage());
        }
    }

    @Test
    public void testSearchAlumno() {
        Alumno retrivied = daoMySql.consultarAlumno("003");
        String query = "SELECT * FROM alumnos_tbl WHERE id = \"003\"";

        try {
            // DB
            IDataSet databaseDataSet = getConnection().createDataSet();

            QueryDataSet actualTable = new QueryDataSet(getConnection());
            actualTable.addTable("alumnosTemp_tbl", query);

            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/select_result.xml"));
            ITable expectedTable = expectedDataSet.getTable("alumnos_tbl");

            Assertion.assertEquals(expectedTable, actualTable.getTable("alumnosTemp_tbl"));

        } catch (Exception e) {
            fail("Error in insert test: " + e.getMessage());
        }
    }
}