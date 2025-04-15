package Modelos;

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
        this.user = "root";
        this.password = "root";
        this.driver = "com.mysql.cj.jdbc.Driver";
    }

    public Connection conectar() throws SQLException {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url + db, user, password);
            return connection;
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Error al cargar el driver de la base de datos: " + ex.getMessage(), ex);
        } catch (SQLException ex) {
            throw new SQLException("Error al establecer la conexión a la base de datos: " + ex.getMessage(), ex);
        }
    }

    public void desconectar() throws SQLException {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexion finalizada.");
            } catch (SQLException ex) {
                throw new SQLException("Error al cerrar la conexión a la base de datos: " + ex.getMessage(), ex);
            }
        }
    }

}
