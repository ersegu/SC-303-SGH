package com.mycompany.clienteservidorg6sistemagestionhospitalaria;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

public class Usuario {

    private String idUsuario;
    private String contrasenha;
    private String nombre;
    private String rol;

    // Constructores 
    public Usuario() {
    }

    public Usuario(String idUsuario, String contrasenha, String nombre, String rol) {
        this.idUsuario = idUsuario;
        this.contrasenha = contrasenha;
        this.nombre = nombre;
        this.rol = rol;
    }

    // Registro de Usuarios
    public boolean registrarUsuario(Usuario user) {
        Conexion conexion = new Conexion();

        String sql = "INSERT INTO usuarios (idUsuario, contrasenha, nombre, rol) VALUES (?, ?, ?, ?)";

        String hashed = BCrypt.hashpw(user.getContrasenha(), BCrypt.gensalt());

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);
            cs.setString(1, this.idUsuario);
            cs.setString(2, hashed);
            cs.setString(3, this.nombre);
            cs.setString(4, this.rol);

            cs.execute();

            return true;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            return false;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    public static boolean autenticar(String idUsuario, String contrasenha) {
        Conexion conexion = new Conexion();

        String sql = "SELECT * FROM usuarios WHERE username = ?";

        try {

            CallableStatement cs = conexion.conectar().prepareCall(sql);
            
            ResultSet rs = cs.executeQuery(sql);

            if (rs.next()) {
                String hashed = rs.getString(3);

                if (BCrypt.checkpw(contrasenha, hashed)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la autenticacion");
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se puede establecer conexion con la base de datos");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.desconectar();
        }

        return false;
    }

    // Getters y Setters
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
