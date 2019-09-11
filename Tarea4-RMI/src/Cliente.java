
import java.rmi.*;
import java.util.Scanner;

public class Cliente{
    public static void main(String args[]){

        InterfazOperaciones invertir;

        String cadena;

        Scanner entrada = new Scanner(System.in);

        try {
            invertir=(InterfazOperaciones)Naming.lookup("rmi://192.168.43.239/Invertir");
            System.out.print("Introduzca la cadena: ");
            cadena = entrada.next();
            System.out.println();
            System.out.println("La cadena invertida es: " + invertir.invertida(cadena));
            System.out.println();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
