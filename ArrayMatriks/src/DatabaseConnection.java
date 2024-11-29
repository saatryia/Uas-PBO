import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        // URL koneksi database
        String url = "jdbc:mysql://localhost:3306/dealermobil"; // Ubah 'contohdb' sesuai nama database Anda
        String username = "root"; // Ubah sesuai username MySQL Anda
        String password = ""; // Ubah sesuai password MySQL Anda

        Connection connection = null;

        try {
            // Membuat koneksi
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Koneksi berhasil!");

        } catch (SQLException e) {
            System.out.println("Koneksi gagal!");
            e.printStackTrace();

        } finally {
            // Menutup koneksi
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Koneksi ditutup.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}