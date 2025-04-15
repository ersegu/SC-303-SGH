/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clienteservidorg6sistemagestionhospitalaria;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author vchan
 */
public class Cliente {

    public static String verificar(String fecha, String hora, int codigoMedico) {
        try {
            Socket socket = new Socket("127.0.0.1", 5600);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            out.writeUTF(fecha);
            out.writeUTF(hora);
            out.writeUTF(String.valueOf(codigoMedico));

            String respuesta = in.readUTF();
            return respuesta;

        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
            return "ERROR";
        }
    }

}
