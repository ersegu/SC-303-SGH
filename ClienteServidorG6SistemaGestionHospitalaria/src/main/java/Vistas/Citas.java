/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import Controladores.CitaCTR;
import Controladores.MedicoCTR;
import Controladores.PacienteCTR;
import Modelos.CitaMOD;
import Modelos.MedicoMOD;
import Modelos.PacienteMOD;
import com.mycompany.clienteservidorg6sistemagestionhospitalaria.Cliente;
import com.mycompany.clienteservidorg6sistemagestionhospitalaria.ClienteNotificacion;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ashji
 */
public class Citas extends javax.swing.JFrame {

    /**
     * Creates new form Citas
     */
    public Citas(Principal principal) throws SQLException {
        initComponents();
        btnValidarFecha.setVisible(false);
        boxPacientes.setModel(new javax.swing.DefaultComboBoxModel<PacienteMOD>());
        boxMedicos.setModel(new javax.swing.DefaultComboBoxModel<MedicoMOD>());
        actualizarTabla();
        cargarCombos();
        setLocationRelativeTo(null);
        this.principal = principal;
    }

    private void limpiar() {
        if (boxPacientes.getItemCount() > 0) {
            boxPacientes.setSelectedIndex(0);
        }
        if (boxMedicos.getItemCount() > 0) {
            boxMedicos.setSelectedIndex(0);
        }
        txtFecha.setText("");
        txtHora.setText("");

        btnAgregar.setEnabled(true);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);

        citaSeleccionada = null;

        btnValidarFecha.setVisible(false);
    }

    private void cargarCombos() throws SQLException {
        boxMedicos.removeAllItems();
        boxPacientes.removeAllItems();
        mapaMedicos.clear();
        mapaPacientes.clear();

        listaMedicos.clear();
        if (MedicoCTR.consultar(listaMedicos)) {
            for (MedicoMOD m : listaMedicos) {
                String etiqueta = m.getCodigo() + " - " + m.nombre_completo();
                boxMedicos.addItem(etiqueta);
                mapaMedicos.put(etiqueta, m);
            }
        }

        listaPacientes.clear();
        if (PacienteCTR.consultar(listaPacientes)) {
            for (PacienteMOD p : listaPacientes) {
                String etiqueta = p.getCodigo() + " - " + p.nombre_completo();
                boxPacientes.addItem(etiqueta);
                mapaPacientes.put(etiqueta, p);
            }
        }
    }

    private boolean validarFecha(String fecha) {
        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendValue(ChronoField.DAY_OF_MONTH)
                    .appendLiteral('/')
                    .appendValue(ChronoField.MONTH_OF_YEAR)
                    .appendLiteral('/')
                    .appendValue(ChronoField.YEAR)
                    .toFormatter();

            LocalDate fechaIngresada = LocalDate.parse(fecha, formatter);
            LocalDate hoy = LocalDate.now();

            if (!fechaIngresada.isAfter(hoy)) {
                JOptionPane.showMessageDialog(this, "La fecha debe ser futura.");
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "El formato de la fecha es inválido. Usa dd/MM/yyyy, por ejemplo 08/04/2025.");
            return false;
        }
    }

    private void actualizarTabla() throws SQLException {
        listaCitas.clear();

        DefaultTableModel modelTable = new DefaultTableModel();

        modelTable.addColumn("Código");
        modelTable.addColumn("Médico");
        modelTable.addColumn("Paciente");
        modelTable.addColumn("Fecha");
        modelTable.addColumn("Hora");

        ArrayList<CitaMOD> citasTemp = new ArrayList<>();

        if (CitaCTR.consultar(citasTemp)) {
            for (CitaMOD c : citasTemp) {
                listaCitas.add(c);

                Object[] fila = new Object[]{
                    c.getCodigoCita(),
                    c.getMedico().nombre_completo(),
                    c.getPaciente().nombre_completo(),
                    c.getFecha(),
                    c.getHora()
                };
                modelTable.addRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al cargar las citas", "Error", JOptionPane.ERROR_MESSAGE);
        }

        tablaCitas.setModel(modelTable);
    }

    public void volverPrincipal() {
        this.principal.setVisible(true);
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelCodigoPaciente = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        labelHora = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        labelCodigoMedico = new javax.swing.JLabel();
        boxPacientes = new javax.swing.JComboBox();
        boxMedicos = new javax.swing.JComboBox();
        btnReporte = new javax.swing.JButton();
        btnValidarFecha = new javax.swing.JButton();
        btnHistorialCitas = new javax.swing.JButton();
        btnRecordatorio = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCitas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 3, true), "Gestion de Citas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(0, 51, 153))); // NOI18N
        jPanel1.setToolTipText("");

        labelCodigoPaciente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelCodigoPaciente.setText("Codigo del Paciente:");

        labelFecha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelFecha.setText("Fecha:");

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(0, 51, 153));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(0, 51, 153));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 2, true));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 51, 153));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 2, true));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(0, 51, 153));
        btnEditar.setText("Editar");
        btnEditar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 2, true));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(0, 51, 153));
        btnAgregar.setText("Agregar");
        btnAgregar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 2, true));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        txtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaKeyReleased(evt);
            }
        });

        labelHora.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelHora.setText("Hora:");

        txtHora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHoraKeyReleased(evt);
            }
        });

        labelCodigoMedico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelCodigoMedico.setText("Codigo del Medico:");

        boxPacientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        boxMedicos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnReporte.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReporte.setForeground(new java.awt.Color(0, 51, 153));
        btnReporte.setText("Generar Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        btnValidarFecha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnValidarFecha.setForeground(new java.awt.Color(0, 51, 153));
        btnValidarFecha.setText("Validar Disponibilidad");
        btnValidarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarFechaActionPerformed(evt);
            }
        });

        btnHistorialCitas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHistorialCitas.setForeground(new java.awt.Color(0, 51, 153));
        btnHistorialCitas.setText("Historial de Citas");
        btnHistorialCitas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 2, true));
        btnHistorialCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialCitasActionPerformed(evt);
            }
        });

        btnRecordatorio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRecordatorio.setForeground(new java.awt.Color(0, 51, 153));
        btnRecordatorio.setText("Enviar Recordatorio");
        btnRecordatorio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 2, true));
        btnRecordatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFecha)
                            .addComponent(labelCodigoPaciente)
                            .addComponent(labelHora)
                            .addComponent(labelCodigoMedico))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnReporte)
                        .addGap(152, 152, 152)
                        .addComponent(btnValidarFecha)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btnSalir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRecordatorio, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(btnHistorialCitas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRecordatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelCodigoPaciente)
                            .addComponent(boxPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(boxMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(labelCodigoMedico)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelFecha)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelHora)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnReporte)
                            .addComponent(btnValidarFecha))
                        .addGap(15, 15, 15))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnSalir)
                .addGap(26, 26, 26)
                .addComponent(btnHistorialCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tablaCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tablaCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCitasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCitas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        volverPrincipal();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        try {
            String fecha = txtFecha.getText().trim();
            String hora = txtHora.getText().trim();

            String keyMedico = (String) boxMedicos.getSelectedItem();
            String keyPaciente = (String) boxPacientes.getSelectedItem();

            // Validación
            boolean fechaValida = validarFecha(fecha);
            if (!fechaValida) {
                return;
            }

            MedicoMOD medico = mapaMedicos.get(keyMedico);
            PacienteMOD paciente = mapaPacientes.get(keyPaciente);

            if (medico == null || paciente == null) {
                JOptionPane.showMessageDialog(this, "Médico o paciente inválido.");
                return;
            }

            CitaMOD cita = new CitaMOD(0, medico, paciente, fecha, hora);

            cita.verificarCampos();

            if (CitaCTR.agregar(cita)) {
                JOptionPane.showMessageDialog(this, "Cita agregada correctamente.");
                limpiar();
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agregar la cita.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar cita: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tablaCitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCitasMouseClicked
        // TODO add your handling code here:
        int fila = tablaCitas.getSelectedRow();
        btnValidarFecha.setVisible(true);

        if (fila >= 0) {
            citaSeleccionada = listaCitas.get(fila);

            txtFecha.setText(citaSeleccionada.getFecha());
            txtHora.setText(citaSeleccionada.getHora());

            String medicoNombre = citaSeleccionada.getMedico().getCodigo() + " - " + citaSeleccionada.getMedico().getNombre();
            for (int i = 0; i < boxMedicos.getItemCount(); i++) {
                if (((String) boxMedicos.getItemAt(i)).startsWith(medicoNombre)) {
                    boxMedicos.setSelectedIndex(i);
                    break;
                }
            }

            String pacienteNombre = citaSeleccionada.getPaciente().getCodigo() + " - " + citaSeleccionada.getPaciente().getNombre();
            for (int i = 0; i < boxPacientes.getItemCount(); i++) {
                if (((String) boxPacientes.getItemAt(i)).startsWith(pacienteNombre)) {
                    boxPacientes.setSelectedIndex(i);
                    break;
                }
            }

            tablaCitas.setDefaultEditor(Object.class, null);

            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnAgregar.setEnabled(false);
        }
    }//GEN-LAST:event_tablaCitasMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        try {
            if (citaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una cita para editar.");
                return;
            }

            String nuevaFecha = txtFecha.getText().trim();
            String nuevaHora = txtHora.getText().trim();
            String keyMedico = (String) boxMedicos.getSelectedItem();
            String keyPaciente = (String) boxPacientes.getSelectedItem();

            // Validación
            boolean fechaValida = validarFecha(nuevaFecha);
            if (!fechaValida) {
                return;
            }


            MedicoMOD nuevoMedico = mapaMedicos.get(keyMedico);
            PacienteMOD nuevoPaciente = mapaPacientes.get(keyPaciente);

            if (nuevoMedico == null || nuevoPaciente == null) {
                JOptionPane.showMessageDialog(this, "Médico o paciente inválido.");
                return;
            }

            citaSeleccionada.setMedico(nuevoMedico);
            citaSeleccionada.setPaciente(nuevoPaciente);
            citaSeleccionada.setFecha(nuevaFecha);
            citaSeleccionada.setHora(nuevaHora);

            citaSeleccionada.verificarCampos();

            if (CitaCTR.editar(citaSeleccionada)) {
                JOptionPane.showMessageDialog(this, "Cita actualizada correctamente.");
                limpiar();
                actualizarTabla();
                citaSeleccionada = null;
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar la cita.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al editar cita: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Desea eliminar la cita del paciente: " + citaSeleccionada.getPaciente().getNombre() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (CitaCTR.eliminar(citaSeleccionada)) {
                    JOptionPane.showMessageDialog(this, "Cita eliminada correctamente.");
                    limpiar();
                    actualizarTabla();
                    citaSeleccionada = null;
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar la cita.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Citas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        Reportes reportesFrame = new Reportes(this, listaCitas);
        reportesFrame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnReporteActionPerformed

    private void btnValidarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarFechaActionPerformed
        // TODO add your handling code here:
        String keyMedico = (String) boxMedicos.getSelectedItem();
        String fecha = txtFecha.getText();
        String hora = txtHora.getText();
        MedicoMOD medico = mapaMedicos.get(keyMedico);
        int codigoMedico = medico.getCodigo();

        boolean fechaValida = validarFecha(fecha);
        if (!fechaValida) {
            return;
        }
        try {
            DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime.parse(hora, horaFormatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "El formato de la hora es inválido. Usa HH:mm en formato 24 horas, por ejemplo 14:30.");
            return;
        }

        String resultado = Cliente.verificar(fecha, hora, codigoMedico);
        JOptionPane.showMessageDialog(this, "Resultado: " + resultado);
    }//GEN-LAST:event_btnValidarFechaActionPerformed

    private void txtFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaKeyReleased
        // TODO add your handling code here:
        if (!txtFecha.getText().trim().isEmpty() && !txtHora.getText().trim().isEmpty()) {
            btnValidarFecha.setVisible(true);
        } else {
            btnValidarFecha.setVisible(false);
        }
    }//GEN-LAST:event_txtFechaKeyReleased

    private void txtHoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraKeyReleased
        // TODO add your handling code here:
        if (!txtFecha.getText().trim().isEmpty() && !txtHora.getText().trim().isEmpty()) {
            btnValidarFecha.setVisible(true);
        } else {
            btnValidarFecha.setVisible(false);
        }
    }//GEN-LAST:event_txtHoraKeyReleased

    private void btnHistorialCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialCitasActionPerformed
        try {
            // TODO add your handling code here:
            HistorialCitas historial;
            historial = new HistorialCitas(this);
            historial.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        actualizarTabla();
                    } catch (SQLException ex) {
                        System.out.println("ERROR AL ACTUALIZAR TABLA: " + ex.getMessage());
                    }
                }
            });
            historial.setVisible(true);
            setVisible(false);
        } catch (SQLException ex) {
            System.out.println("ERROR AL BUSCAR HISTORIAL CITAS: " + ex.getMessage());
        }

    }//GEN-LAST:event_btnHistorialCitasActionPerformed

    private void btnRecordatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecordatorioActionPerformed
        // TODO add your handling code here:
        if (citaSeleccionada == null) {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar una cita",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ClienteNotificacion.enviar(citaSeleccionada.getPaciente().getNombre(), citaSeleccionada.getFecha(), citaSeleccionada.getHora());
    }//GEN-LAST:event_btnRecordatorioActionPerformed

    private Principal principal;
    private CitaMOD citaSeleccionada;
    private ArrayList<CitaMOD> listaCitas = new ArrayList<>();
    private ArrayList<MedicoMOD> listaMedicos = new ArrayList<>();
    private ArrayList<PacienteMOD> listaPacientes = new ArrayList<>();
    private Map<String, MedicoMOD> mapaMedicos = new HashMap<>();
    private Map<String, PacienteMOD> mapaPacientes = new HashMap<>();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox boxMedicos;
    private javax.swing.JComboBox boxPacientes;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHistorialCitas;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRecordatorio;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnValidarFecha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCodigoMedico;
    private javax.swing.JLabel labelCodigoPaciente;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelHora;
    private javax.swing.JTable tablaCitas;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    // End of variables declaration//GEN-END:variables
}
