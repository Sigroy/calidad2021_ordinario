package com.anahuac.calidad.doublesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DriverApp {

	public static void main(String[] args) {
		// Conexión a la base de datos de MySql
        Alumno a = new Alumno("001", "alumno1", 15, "hola@hola.com");
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost33060/pruebas_db", "root", "secret");

            PreparedStatement preparedStatement;
            preparedStatement = con
                    .prepareStatement("insert INTO alumnos_tbl(id, nombre, email, edad) values(?,?,?,?)");
            preparedStatement.setString(1, a.getId());
            preparedStatement.setString(2, a.getNombre());
            preparedStatement.setString(3, a.getEmail());
            preparedStatement.setInt(4, a.getEdad());
            preparedStatement.executeUpdate();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}