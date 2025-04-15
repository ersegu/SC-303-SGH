package Controladores;

import Modelos.Conexion;
import Modelos.MedicoMOD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MedicoCTR {

    public MedicoCTR() {}

    public static boolean agregar(MedicoMOD medico) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.");
            return false;
        }

        String sql = "INSERT INTO medicos (nombre, apellido, fechaNacimiento, identificacion, salario, especialidad, disponibilidad) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);
            cs.setString(1, medico.getNombre());
            cs.setString(2, medico.getApellido());
            cs.setString(3, medico.getFechaNacimiento());
            cs.setString(4, medico.getIdentificacion());
            cs.setDouble(5, medico.getSalario());
            cs.setString(6, medico.getEspecialidad());
            cs.setString(7, medico.getDisponible());

            cs.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar médico: " + ex.getMessage());
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error general: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }

    public static boolean consultar(ArrayList<MedicoMOD> modelos) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            System.out.println("No se pudo conectar a la base de datos.");
            return false;
        }

        String sql = "SELECT * FROM medicos";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                MedicoMOD medico = new MedicoMOD(
                        rs.getInt("codigoMedico"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("fechaNacimiento"),
                        rs.getString("identificacion"),
                        rs.getDouble("salario"),
                        rs.getString("especialidad"),
                        rs.getString("disponibilidad")
                );
                modelos.add(medico);
            }
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERROR AL CONSULTAR MÉDICOS: " + ex.getMessage());
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR GENERAL: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }

    public static boolean eliminar(MedicoMOD medico) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.");
            return false;
        }

        String sql = "DELETE FROM medicos WHERE codigoMedico=?";

        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, medico.getCodigo());
            cs.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERROR AL ELIMINAR MÉDICO: " + ex.getMessage());
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR GENERAL AL ELIMINAR MÉDICO: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }

    public static boolean editar(MedicoMOD medico) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos");
            return false;
        }

        String sql = "UPDATE medicos SET nombre=?, fechaNacimiento=?, identificacion=?, salario=?, especialidad=?, disponibilidad=? WHERE codigoMedico=?";

        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, medico.getNombre());
            cs.setString(2, medico.getFechaNacimiento());
            cs.setString(3, medico.getIdentificacion());
            cs.setDouble(4, medico.getSalario());
            cs.setString(5, medico.getEspecialidad());
            cs.setString(6, medico.getDisponible());
            cs.setInt(7, medico.getCodigo());

            cs.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al editar médico: " + ex.getMessage());
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error general: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }
}
