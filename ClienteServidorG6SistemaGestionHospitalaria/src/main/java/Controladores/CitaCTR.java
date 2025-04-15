package Controladores;

import Modelos.CitaMOD;
import Modelos.Conexion;
import Modelos.MedicoMOD;
import Modelos.PacienteMOD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CitaCTR {

    public static boolean agregar(CitaMOD cita) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            JOptionPane.showMessageDialog(null, "No se pudo establecer conexion con la base de datos");
            return false;
        }

        String sql = "INSERT INTO citas (codigoMedico, codigoPaciente, fechaCita, horaCita) VALUES (?, ?, ?, ?)";

        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, cita.getMedico().getCodigo());
            cs.setInt(2, cita.getPaciente().getCodigo());
            cs.setString(3, cita.getFecha());
            cs.setString(4, cita.getHora());

            cs.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar la cita: " + ex.getMessage());
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error general al agregar cita: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }

    public static boolean editar(CitaMOD cita) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            JOptionPane.showMessageDialog(null, "No se pudo establecer conexion con la base de datos");
            return false;
        }

        String sql = "UPDATE citas SET codigoMedico=?, codigoPaciente=?, fechaCita=?, horaCita=? WHERE codigoCita=?";

        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, cita.getMedico().getCodigo());
            cs.setInt(2, cita.getPaciente().getCodigo());
            cs.setString(3, cita.getFecha());
            cs.setString(4, cita.getHora());
            cs.setInt(5, cita.getCodigoCita());

            cs.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al editar cita: " + ex.getMessage());
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error general al editar cita: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }

    public static boolean consultar(ArrayList<CitaMOD> modelos) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            System.out.println("No se pudo establecer conexion con la base de datos");
            return false;
        }

        String sql = """
        SELECT c.codigoCita, c.fechaCita, c.horaCita,
               m.codigoMedico, m.nombre AS nombreMedico, m.apellido AS apellidoMedico, m.especialidad,
               p.codigo AS codigoPaciente, p.nombre AS nombrePaciente, p.apellido AS apellidoPaciente
        FROM citas c
        JOIN medicos m ON c.codigoMedico = m.codigoMedico
        JOIN pacientes p ON c.codigoPaciente = p.codigo
        """;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                MedicoMOD medico = new MedicoMOD();
                medico.setCodigo(rs.getInt("codigoMedico"));
                medico.setNombre(rs.getString("nombreMedico"));
                medico.setApellido(rs.getString("apellidoMedico"));
                medico.setEspecialidad(rs.getString("especialidad"));

                PacienteMOD paciente = new PacienteMOD();
                paciente.setCodigo(rs.getInt("codigoPaciente"));
                paciente.setNombre(rs.getString("nombrePaciente"));
                paciente.setApellido(rs.getString("apellidoPaciente"));

                CitaMOD cita = new CitaMOD(
                        rs.getInt("codigoCita"),
                        medico,
                        paciente,
                        rs.getString("fechaCita"),
                        rs.getString("horaCita")
                );

                modelos.add(cita);
            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERROR AL CONSULTAR CITAS: " + ex.getMessage());
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR GENERAL AL CONSULTAR CITAS: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }

    public static boolean eliminar(CitaMOD cita) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            JOptionPane.showMessageDialog(null, "No se pudo establecer conexion con la base de datos");
            return false;
        }

        String sql = "DELETE FROM citas WHERE codigoCita=?";

        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, cita.getCodigoCita());
            cs.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar la cita: " + ex.getMessage());
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error general al eliminar cita: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }

    public static boolean fechaDisponible(String fecha, String hora, int codigoMedico) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        String sql = "SELECT * FROM citas WHERE fechaCita = ? AND horaCita = ? AND codigoMedico = ?";

        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, fecha);
            cs.setString(2, hora);
            cs.setInt(3, codigoMedico);

            ResultSet rs = cs.executeQuery();

            boolean disponible = !rs.next();
            return disponible;

        } catch (SQLException ex) {
            System.out.println("ERROR AL CONSULTAR DISPONIBILIDAD: " + ex.getMessage());
            return false;
        } catch (Exception ex) {
            System.out.println("ERROR GENERAL: " + ex.getMessage());
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    public static boolean consultarHistorial(ArrayList<CitaMOD> modelos, String idPaciente) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            System.out.println("No se pudo establecer conexion con la base de datos");
            return false;
        }

        String sql = """
        SELECT c.codigoCita, c.fechaCita, c.horaCita,
        	   m.codigoMedico, m.nombre AS nombreMedico, m.apellido AS apellidoMedico, m.especialidad,
        	   p.codigo AS codigoPaciente, p.nombre AS nombrePaciente, p.apellido AS apellidoPaciente
        FROM citas c
        JOIN medicos m ON c.codigoMedico = m.codigoMedico
        JOIN pacientes p ON c.codigoPaciente = p.codigo
        WHERE p.codigo = ?;
        """;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idPaciente);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MedicoMOD medico = new MedicoMOD();
                medico.setCodigo(rs.getInt("codigoMedico"));
                medico.setNombre(rs.getString("nombreMedico"));
                medico.setApellido(rs.getString("apellidoMedico"));
                medico.setEspecialidad(rs.getString("especialidad"));

                PacienteMOD paciente = new PacienteMOD();
                paciente.setCodigo(rs.getInt("codigoPaciente"));
                paciente.setNombre(rs.getString("nombrePaciente"));
                paciente.setApellido(rs.getString("apellidoPaciente"));

                CitaMOD cita = new CitaMOD(
                        rs.getInt("codigoCita"),
                        medico,
                        paciente,
                        rs.getString("fechaCita"),
                        rs.getString("horaCita")
                );

                modelos.add(cita);
            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERROR AL CONSULTAR CITAS: " + ex.getMessage());
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR GENERAL AL CONSULTAR CITAS: " + ex.getMessage());
            return false;

        } finally {
            conexion.desconectar();
        }
    }

}
