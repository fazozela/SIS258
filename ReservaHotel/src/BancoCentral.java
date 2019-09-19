import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class BancoCentral extends UnicastRemoteObject implements IBancoCentral {

    BancoCentral() throws java.rmi.RemoteException{
        super();
    }

    @Override
    public double cotizacionDolar(String fecha) throws RemoteException {
        switch (fecha){
            case "26-09-19":
                return 6.90;
            case "27-09-19":
                return 6.91;
            case "28-09-19":
                return 6.93;
            case "29-09-19":
                return 6.92;
            case "30-09-19":
                return 6.96;

        }
        return 0;
    }

    public static void main(String args[]) {
        try {
            BancoCentral cotizar;
            LocateRegistry.createRegistry(1099);
            cotizar = new BancoCentral();
            Naming.bind("Cotizar", cotizar);
            System.out.println("El BancoCentral esta listo\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
