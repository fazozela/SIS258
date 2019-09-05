package com.jx.sockettcp;
import java.io.*;
import java.net.*;

class server {

  public static void main(String[] args) throws InterruptedException{
    int port =5001; // puerto en el que escuchara el socket

    try {
      ServerSocket server = new ServerSocket(port); //instanciamos un servidor socket
      Socket client;
      BufferedReader fromClient;  // buffer de lectura
      PrintStream toClient;       // stream para escritura
      while(true){   // ciclo al infinito para elfuncionamiento del server
        client = server.accept(); // el servidorse queda esperando establecer conexion
        fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
        //String cadena;

        String[] cadena =  fromClient.readLine().split(",") ; //cadena obtenida desde el lector

        toClient = new PrintStream(client.getOutputStream()); //prepara el objetopara devolver
        //System.out.println(cadena+client.getInetAddress());


        //imprime cadena recibida desde el cliente
        //Thread.sleep(3000);
        String cad="";
        for (int i = 0;i<10;i++) {
          cad+=Integer.toString( Integer.parseInt(cadena[i])+5)+' ';
        }

        toClient.flush(); //
        toClient.println(cad);

      }
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }
}