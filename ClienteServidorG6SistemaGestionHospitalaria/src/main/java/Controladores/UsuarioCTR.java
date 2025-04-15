/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Modelos.Conexion;
import Modelos.UsuarioMOD;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author vchan
 */
public class UsuarioCTR {

    public static boolean registrarUsuario(UsuarioMOD user) throws SQLException {
        Conexion conexion = new Conexion();
        if (user.getIdUsuario() == null || user.getIdUsuario().isBlank()
                || user.getContrasenha() == null || user.getContrasenha().isBlank()
                || user.getNombre() == null || user.getNombre().isBlank()
                || user.getRol() == null || user.getRol().isBlank()) {

            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return false;
        }

        String revisarUsuarioSQL = "SELECT * FROM usuarios WHERE idUsuario = ?";
        String registrarSQL = "INSERT INTO usuarios (idUsuario, contrasenha, nombre, rol) VALUES (?, ?, ?, ?)";
        String hashed = BCrypt.hashpw(user.getContrasenha(), BCrypt.gensalt());

        try {
            CallableStatement checkCS = conexion.conectar().prepareCall(revisarUsuarioSQL);
            checkCS.setString(1, user.getIdUsuario());
            ResultSet rs = checkCS.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe.");
                return false;
            }

            CallableStatement cs = conexion.conectar().prepareCall(registrarSQL);
            cs.setString(1, user.getIdUsuario());
            cs.setString(2, hashed);
            cs.setString(3, user.getNombre());
            cs.setString(4, user.getRol());

            cs.execute();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + ex.getMessage());
            return false;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error general: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }

    public static boolean autenticar(String idUsuario, String contrasenha) throws SQLException {
        Conexion conexion = new Conexion();

        String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";

        try {

            CallableStatement cs = conexion.conectar().prepareCall(sql);

            cs.setString(1, idUsuario);

            ResultSet rs = cs.executeQuery();

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

    public static boolean consultar(ArrayList<UsuarioMOD> usuarios) throws SQLException {
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM usuarios";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                UsuarioMOD u = new UsuarioMOD(
                        rs.getInt("codigo"),
                        rs.getString("idUsuario"),
                        rs.getString("contrasenha"),
                        rs.getString("nombre"),
                        rs.getString("rol")
                );
                usuarios.add(u);
            }
            return true;

        } catch (Exception ex) {
            System.out.println("ERROR AL CONSULTAR USUARIOS: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }

    public static boolean editar(UsuarioMOD user) throws SQLException {
        Conexion conexion = new Conexion();

        String sql = "UPDATE usuarios SET nombre = ?, rol = ? WHERE idUsuario = ?";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);
            cs.setString(1, user.getNombre());
            cs.setString(2, user.getRol());
            cs.setString(3, user.getIdUsuario());

            int filas = cs.executeUpdate();
            return filas > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al editar usuario: " + ex.getMessage());
            return false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error general: " + ex.getMessage());
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    public static boolean eliminar(UsuarioMOD user) throws SQLException {
        Conexion conexion = new Conexion();

        String sql = "DELETE FROM usuarios WHERE idUsuario = ?";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);
            cs.setString(1, user.getIdUsuario());

            int filas = cs.executeUpdate();
            return filas > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + ex.getMessage());
            return false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error general: " + ex.getMessage());
            return false;
        } finally {
            conexion.desconectar();
        }
    }
}
