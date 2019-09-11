
import java.rmi.*;

public interface InterfazOperaciones extends Remote {
    String invertida(String cadena) throws RemoteException;
    //long Factorial(long arg) throws RemoteException;
}
