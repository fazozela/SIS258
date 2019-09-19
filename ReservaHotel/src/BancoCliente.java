import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BancoCliente {
    public static void main(String[] args) throws InterruptedException{
        int port =5001; // puerto en el que escuchara el socket

        try {
            ServerSocket server = new ServerSocket(port); //instanciamos un servidor socket
            Socket client;
            BufferedReader fromClient;  // buffer de lectura
            PrintStream toClient;       // stream para escritura
            while(true){   // ciclo al infinito para elfuncionamiento del server
                client = server.accept(); // el servidorse queda esperando establecer conexion
                fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
                String cadena;
                cadena = fromClient.readLine(); //cadena obtenida desde el lector
                toClient = new PrintStream(client.getOutputStream()); //prepara el objetopara devolver
                System.out.println(cadena+client.getInetAddress());
                String[] cad = cadena.split(",");
                toClient.flush();

                switch (cad[0]){
                    case "1":
                        toClient.println( (Integer.valueOf(cad[1])>300)? "0" : "1");
                        break;
                    case "2":
                        toClient.println( (Integer.valueOf(cad[1])>400)? "0" : "1");
                        break;
                    case "3":
                        toClient.println( (Integer.valueOf(cad[1])>1000)? "0" : "1");
                        break;
                }
                //


            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
