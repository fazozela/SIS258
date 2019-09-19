import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServidorReservaInterface extends Remote {
    double cotizar(String inicio,String fin,String fechaCotizacion)throws RemoteException;
    String reservar(String inicio, String fin, String idCliente, String fechaCompra) throws RemoteException;

}
