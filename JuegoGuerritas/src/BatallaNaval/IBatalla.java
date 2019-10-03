package BatallaNaval;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBatalla extends Remote {
    public void iniciarpartida() throws RemoteException;
    public boolean ponerfichas(int jugador,int [][] posiciones) throws RemoteException;
    public boolean disparar (int jugador,int i,int j) throws RemoteException;
    public int turno()throws RemoteException;
}
