import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BancoCentralInterface extends Remote {
    double cotizacionDolar (String fecha) throws RemoteException;
}
