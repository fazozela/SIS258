import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ServidorReserva  extends UnicastRemoteObject implements ServidorReservaInterface {
    protected ServidorReserva() throws RemoteException {
        super();
    }
    int[] precios = {30,25,25,35,40};

    @Override
    public double cotizar(String inicio, String fin, String fechaCotizacion) throws RemoteException {
        int s=0;
        int ini = Integer.valueOf(inicio.substring(0,2));
        int fi = Integer.valueOf(fin.substring(0,2));
        System.out.println("fIni="+ini+"  fFin="+fi);
        for(int i=ini-26;i<=fi-26;i++){
            s+=precios[i];
        }
        System.out.println("total"+s);
        BancoCentralInterface cotizar;

        String cadena;

        //Scanner entrada = new Scanner(System.in);
        double dolar;
        try {
            cotizar=(BancoCentralInterface) Naming.lookup("rmi://localhost/Cotizar");
            dolar = cotizar.cotizacionDolar(fechaCotizacion);
            return  dolar*s;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public String reservar(String inicio, String fin, String idCliente, String fechaCompra) throws RemoteException {
        double total = cotizar(inicio,fin,fechaCompra);
        int port = 5001; // puerto de comunicacion
        try{

            String cadena = idCliente + "," + String.valueOf(total);

            Socket client = new Socket("localhost", port); //conectarse al socket

            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

            toServer.println(cadena);  //mandar alservidor
            String result = fromServer.readLine();  // devolver del servidor
            if(result.equals("1"))return "Compra exitosa";
            else return "Compra fallida";



        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        return ":(";
    }


    public static void main(String args[]) {
        try {
            ServidorReserva hotel;
            //LocateRegistry.createRegistry(1099);
            hotel = new ServidorReserva();
            Naming.bind("Hotel", hotel);
            System.out.println("El ServidorReserva esta listo\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
