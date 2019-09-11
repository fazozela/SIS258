
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;


public class Servidor
        extends UnicastRemoteObject
        implements InterfazOperaciones
{
    Servidor() throws java.rmi.RemoteException{
        super();
    }

    public String invertida(String cadena){
        String invertida="";

        for(int i = cadena.length() - 1; i >= 0; i--){
            invertida += cadena.charAt(i);
        }

        return invertida;
    }


    public static void main(String args[]) {
        try {
            Servidor invertir;
            LocateRegistry.createRegistry(1099);
            invertir = new Servidor();
            Naming.bind("Invertir", invertir);
            System.out.println("El servidor esta listo\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
