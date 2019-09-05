package com.jx.sockettcp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class client {
  public static void main(String[] args){
    int port = 5001; // puerto de comunicacion
    try{
      Scanner sc=new Scanner(System.in);
      String cadena="";
      int num;

      for(int i=1;i<=11;i++){
        System.out.print("Introduzca el " + i + " numero: ");
        num=sc.nextInt();
        cadena+=Integer.toString(num) +',';
      }
      //System.out.println(cadena);


      Socket client = new Socket("localhost", port); //conectarse al socket

      PrintStream toServer = new PrintStream(client.getOutputStream()); //mandar
      BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream())); //recibir

      toServer.println(cadena);  //mandar alservidor
      String result = fromServer.readLine();  // devolver del servidor

      System.out.println("cadena devuelta es: "+result);



    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }
}