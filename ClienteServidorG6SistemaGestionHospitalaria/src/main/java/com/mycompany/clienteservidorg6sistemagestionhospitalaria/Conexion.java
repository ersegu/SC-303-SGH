package com.mycompany.clienteservidorg6sistemagestionhospitalaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private String url;
    private String db;
    private String user;
    private String password;
    private String driver;
    private Connection connection;

    public Conexion() {
        this.url = "jdbc:mysql://localhost:3306/";
        this.db = "SGHDB";
        this.user = "admin";
        this.password = "fidelitas!";
        this.driver = "com.mysql.cj.jdbc.Driver";
    }

    public Connection conectar() throws Exception {

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url + db, user, password);
            return connection;
        } catch (ClassNotFoundException ex) {
            System.out.println("Exception Class: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Exception SQL: " + ex.getMessage());
        }
        throw new Exception("Conector Null");

    }

    public void desconectar() {

        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexion Finalizada.");
            } catch (SQLException ex) {
                System.out.println("Exception SQL: " + ex.getMessage());
            }
        }

    }

}
