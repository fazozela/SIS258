import java.rmi.Naming;
import java.util.Scanner;

public class Cliente {

    public static void main(String args[]){

        ServidorReservaInterface hotel;

        String cadena;

        Scanner entrada = new Scanner(System.in);

        try {
            hotel=(ServidorReservaInterface) Naming.lookup("rmi://localhost/Hotel");
            System.out.println("Opciones: ");
            System.out.println( "     1) Cotizar ");
            System.out.println( "     2) Reservar");
            System.out.print("Opcion -> ");


            int op = entrada.nextInt();
            entrada.nextLine();
            System.out.print("Fecha de Inicio (dd-mm-aa):");
            String fInicio = entrada.nextLine();

            System.out.print("Fecha Fin (dd-mm-aa):");
            String fFin = entrada.nextLine();
            switch (op){
                case 1:
                    System.out.print("Fecha Cotizacion (dd-mm-aa):");
                    String fCotizacion = entrada.nextLine();

                    System.out.println("Costo total: " + hotel.cotizar(fInicio,fFin,fCotizacion) +"Bs");

                    break;
                case 2:
                    System.out.print("Id Cliente:");
                    String idCliente = entrada.nextLine();
                    System.out.print("Fecha Compra (dd-mm-aa):");
                    String fCompra = entrada.nextLine();

                    //hotel.reservar(fInicio,fFin,idCliente,fCompra);
                    System.out.println( hotel.reservar(fInicio,fFin,idCliente,fCompra) );
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
