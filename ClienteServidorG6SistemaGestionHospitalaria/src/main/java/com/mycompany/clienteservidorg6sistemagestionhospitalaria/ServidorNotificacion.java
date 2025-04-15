package com.mycompany.clienteservidorg6sistemagestionhospitalaria;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorNotificacion implements Runnable {

    @Override
    public void run() {
        ServerSocket servidor;
        Socket cliente;
        DataOutputStream out;

        try {
            System.out.println("Servidor iniciado ...");
            servidor = new ServerSocket(6900);
            while (true) {
                cliente = servidor.accept();
                System.out.println("Cliente conectado.");

                DataInputStream in = new DataInputStream(cliente.getInputStream());

                String nombre = in.readUTF();
                String fecha = in.readUTF();
                String hora = in.readUTF();

                System.out.println("📩 Notificación enviada a " + nombre
                        + " para el día " + fecha + " a las " + hora);
                cliente.close();

                System.out.println("Cliente desconectado.");
            }
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }
}
