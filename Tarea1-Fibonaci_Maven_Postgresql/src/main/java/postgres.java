import java.sql.*;
import java.util.Scanner;

public class postgres {
    public static void main(String[] args) {
        try (Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/fibo", "postgres", "root")) {

            System.out.print("Introduzca un número para obtener su fibonnaci: ");
            Scanner sc = new Scanner(System.in);
            int position = sc.nextInt();

            int number = Fibonacci.getFibo(position);
            System.out.print("El fibonacci es: " + number + '\n');

            PreparedStatement st = connection.prepareStatement("INSERT INTO numbers (position, number) VALUES (?, ?)");
            st.setInt(1, position);
            st.setInt(2, number);
            st.executeUpdate();
            st.close();

            Statement statement = connection.createStatement();
            System.out.println("Tabla postgres:");
            System.out.printf("%-30.30s  %-30.30s%n", "position", "number");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM numbers");
            while (resultSet.next()) {
                System.out.printf("%-30.30s  %-30.30s%n", resultSet.getString("position"), resultSet.getString("number"));
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}

