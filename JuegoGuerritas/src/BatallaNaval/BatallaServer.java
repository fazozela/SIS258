/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatallaNaval;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Carlos
 */
public class BatallaServer extends UnicastRemoteObject implements IBatalla {
    private int [][] tablero1=new int[10][10];
    private int [][] tablero2=new int[10][10];
    public static int turno=0;

    int estado=0;

    public BatallaServer() throws RemoteException {
        super();
    }


    public static void main(String args[]) {

        try {
            BatallaServer batallaServer;
            LocateRegistry.createRegistry(1099);
            batallaServer = new BatallaServer();
            batallaServer.iniciarpartida();
            Naming.bind("Guerrita", batallaServer);
            System.out.println("juego esta listo\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


 public void iniciarpartida() throws RemoteException
 {
     for (int i=0;i<10;i++)
     {
         for (int j=0;j<10;j++)
         {
             tablero1[i][j]=0;
             tablero2[i][j]=0;
         }
     }

 }
 public boolean ponerfichas(int jugador,int [][] posiciones) throws RemoteException
 {
   int filas=posiciones.length;
   int columnas=posiciones[0].length;
   estado++;
   if(estado>1)turno=1;
     System.out.println(turno);
   boolean correcto=true;
           
     for (int i=0;i<1;i++)
     {

         if (jugador==1)
             if (tablero1[posiciones[i][0]][posiciones[i][1]]==0)
                tablero1[posiciones[i][0]][posiciones[i][1]]=1;
             else
                 correcto=false;
         else
             if (tablero2[posiciones[i][0]][posiciones[i][1]]==0)
             tablero2[posiciones[i][0]][posiciones[i][1]]=1;
            else
                  correcto=false;
     }
     for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 10; j++) {
             System.out.print(tablero1[i][j]+ " ");
         }
         System.out.println();
     }
   
 return true;
 }

 public boolean disparar (int jugador,int i,int j) throws RemoteException {
     if (turno==jugador && estado>1 )
      if (jugador==1) {
          if (tablero2[i][j] == 1) {
              tablero2[i][j] = 2;
              for (int a = 0; a < 10; a++) {
                  for (int b = 0; b < 10; b++) {
                      System.out.print(tablero1[a][b]+ " ");
                  }
                  System.out.println();
              }
              return true;
          }
          else {
              turno=2;
              return false;
          }
      }
      else{
          if (tablero1[i][j] == 1) {
              tablero1[i][j] = 2;
              for (int a = 0; a < 10; a++) {
                  for (int b = 0; b < 10; b++) {
                      System.out.print(tablero1[a][b]+ " ");
                  }
                  System.out.println();
              }
              return true;
          }
          else {
              turno=1;
              return false;
          }
      }

  return true;
 }

    @Override
    public int turno() throws RemoteException {
        return turno;
    }


}
