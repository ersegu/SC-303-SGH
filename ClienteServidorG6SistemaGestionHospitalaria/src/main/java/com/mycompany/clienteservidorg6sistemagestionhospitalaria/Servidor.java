package com.mycompany.clienteservidorg6sistemagestionhospitalaria;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Servidor de disponibilidad iniciado...");
            ServerSocket servidor = new ServerSocket(5600);
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado.");

                DataInputStream in = new DataInputStream(cliente.getInputStream());
                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                String fecha = in.readUTF();
                String hora = in.readUTF();
                int codigoMedico = Integer.parseInt(in.readUTF());

                boolean disponible = Controladores.CitaCTR.fechaDisponible(fecha, hora, codigoMedico);

                if (disponible) {
                    out.writeUTF("DISPONIBLE");
                } else {
                    out.writeUTF("NO DISPONIBLE");
                }

                cliente.close();
                System.out.println("Cliente desconectado.");
            }

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
