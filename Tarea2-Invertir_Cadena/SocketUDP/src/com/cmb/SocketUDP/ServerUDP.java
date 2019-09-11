/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.cmb.socketudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Carlos
 */
public class ServerUDP {

    public static void main(String[] args) {

        try {
            DatagramSocket socketUDP = new DatagramSocket(6789);
            byte[] bufer = new byte[1000];

            while (true) {
                // Construimos el DatagramPacket para recibir peticiones
                DatagramPacket peticion
                        = new DatagramPacket(bufer, bufer.length);

                // Leemos una petición del DatagramSocket
                socketUDP.receive(peticion);

                System.out.println("Datagrama recibido del host: " + peticion.getAddress()
                        + " desde el puerto remoto: " + peticion.getPort());

                // Construimos el DatagramPacket para enviar la respuesta
                String dato = new String(peticion.getData());
                String[] nums = dato.split(","); //separamos números por comas
                for (int i = 0; i < nums.length; i++) {
                    int num = Integer.parseInt(nums[i].trim()) + 5; // +5 a cada número
                    nums[i] = String.valueOf(num);
                }
                dato = String.join(", ", nums); //juntamos los números con comas
                peticion.setData(dato.getBytes()); //reemplazamos el dato original

                DatagramPacket respuesta = new DatagramPacket(peticion.getData(), peticion.getLength(),
                        peticion.getAddress(), peticion.getPort());

                // Enviamos la respuesta, que es un eco
                socketUDP.send(respuesta);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

}
