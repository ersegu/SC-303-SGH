package Controladores;

import Modelos.Conexion;
import Modelos.PacienteMOD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PacienteCTR {

    public PacienteCTR() {
    }

    public static boolean consultar(ArrayList<PacienteMOD> modelos) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            System.out.println("No se pudo conectar a la base de datos.");
            return false;
        }

        String sql = "SELECT * FROM pacientes";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                PacienteMOD paciente = new PacienteMOD(
                        rs.getInt("codigo"),
                        rs.getString("apellido"),
                        rs.getString("fechaNacimiento"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("historialMedico"),
                        rs.getString("nombre"),
                        rs.getString("identificacion")
                );
                modelos.add(paciente);
            }
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERROR AL CONSULTAR PACIENTES: " + ex.getMessage());
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR GENERAL AL CONSULTAR PACIENTES: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }

    public static boolean agregar(PacienteMOD paciente) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            System.out.println("No se pudo conectar a la base de datos.");
            return false;
        }

        String sql = "INSERT INTO pacientes (nombre, apellido, fechaNacimiento, direccion, telefono, correo, identificacion, historialMedico) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, paciente.getNombre());
            cs.setString(2, paciente.getApellido());
            cs.setString(3, paciente.getFechaNacimiento());
            cs.setString(4, paciente.getDireccion());
            cs.setString(5, paciente.getTelefono());
            cs.setString(6, paciente.getCorreo());
            cs.setString(7, paciente.getIdentificacion());
            cs.setString(8, paciente.getHistorialMedico());

            cs.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERROR AL AGREGAR PACIENTE: " + ex.getMessage());
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR GENERAL AL AGREGAR PACIENTE: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }

    public static boolean editar(PacienteMOD paciente) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            System.out.println("No se pudo conectar a la base de datos.");
            return false;
        }

        String sql = "UPDATE pacientes SET nombre=?, apellido=?, fechaNacimiento=?, direccion=?, telefono=?, correo=?, identificacion=?, historialMedico=? WHERE codigo=?";

        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, paciente.getNombre());
            cs.setString(2, paciente.getApellido());
            cs.setString(3, paciente.getFechaNacimiento());
            cs.setString(4, paciente.getDireccion());
            cs.setString(5, paciente.getTelefono());
            cs.setString(6, paciente.getCorreo());
            cs.setString(7, paciente.getIdentificacion());
            cs.setString(8, paciente.getHistorialMedico());
            cs.setInt(9, paciente.getCodigo());

            cs.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERROR AL EDITAR PACIENTE: " + ex.getMessage());
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR GENERAL AL EDITAR PACIENTE: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }

    public static boolean eliminar(PacienteMOD paciente) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            System.out.println("No se pudo conectar a la base de datos.");
            return false;
        }

        String sql = "DELETE FROM pacientes WHERE codigo=?";

        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, paciente.getCodigo());
            cs.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERROR AL ELIMINAR PACIENTE: " + ex.getMessage());
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR GENERAL AL ELIMINAR PACIENTE: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }

}
