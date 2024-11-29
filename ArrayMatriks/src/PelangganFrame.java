import java.sql.*;
import java.util.Scanner;

public class PelangganFrame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Menu Pelanggan ===");
            System.out.println("1. Tambah Data");
            System.out.println("2. Lihat Data");
            System.out.println("3. Update Data");
            System.out.println("4. Hapus Data");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tambahPelanggan();
                    break;
                case 2:
                    lihatPelanggan();
                    break;
                case 3:
                    updatePelanggan();
                    break;
                case 4:
                    hapusPelanggan();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void tambahPelanggan() {
        try (Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("NIK: ");
            String nik = scanner.nextLine();
            System.out.print("No Telp: ");
            String notelp = scanner.nextLine();
            System.out.print("Alamat: ");
            String alamat = scanner.nextLine();

            String sql = "INSERT INTO data_pelanggan (nama, nik, notelp, alamat) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nama);
            pstmt.setString(2, nik);
            pstmt.setString(3, notelp);
            pstmt.setString(4, alamat);
            pstmt.executeUpdate();
            System.out.println("Data pelanggan berhasil ditambahkan!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void lihatPelanggan() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM data_pelanggan";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n=== Data Pelanggan ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idpelanggan"));
                System.out.println("Nama: " + rs.getString("nama"));
                System.out.println("NIK: " + rs.getString("nik"));
                System.out.println("No Telp: " + rs.getString("notelp"));
                System.out.println("Alamat: " + rs.getString("alamat"));
                System.out.println("--------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updatePelanggan() {
        // Implementasi Update...
    }

    private static void hapusPelanggan() {
        // Implementasi Hapus...
    }
}
