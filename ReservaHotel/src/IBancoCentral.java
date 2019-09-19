import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBancoCentral extends Remote {
    double cotizacionDolar (String fecha) throws RemoteException;
}
