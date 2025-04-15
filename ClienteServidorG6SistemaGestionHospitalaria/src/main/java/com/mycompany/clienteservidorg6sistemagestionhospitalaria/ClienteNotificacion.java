/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clienteservidorg6sistemagestionhospitalaria;

import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author vchan
 */
public class ClienteNotificacion {

    public static void enviar(String nombrePaciente, String fecha, String hora) {
        try {
            Socket socket = new Socket("127.0.0.1", 6900);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(nombrePaciente);
            out.writeUTF(fecha);
            out.writeUTF(hora);
            
            JOptionPane.showMessageDialog(null,
                    "Notificación enviada a " + nombrePaciente
                    + " para la cita del día " + fecha + " a las " + hora,
                    "Notificación", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }

}
