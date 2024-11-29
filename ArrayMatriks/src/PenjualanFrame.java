import java.sql.*;
import java.util.Scanner;

public class PenjualanFrame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Menu Penjualan ===");
            System.out.println("1. Tambah Data");
            System.out.println("2. Lihat Data");
            System.out.println("3. Update Data");
            System.out.println("4. Hapus Data");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tambahPenjualan();
                    break;
                case 2:
                    lihatPenjualan();
                    break;
                case 3:
                    updatePenjualan();
                    break;
                case 4:
                    hapusPenjualan();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void tambahPenjualan() {
        try (Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID Pelanggan: ");
            int idpelanggan = scanner.nextInt();
            System.out.print("ID Mobil: ");
            int idmobil = scanner.nextInt();
            System.out.print("Total Biaya: ");
            double totalbiaya = scanner.nextDouble();

            String sql = "INSERT INTO data_penjualan (idpelanggan, idmobil, totalbiaya) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idpelanggan);
            pstmt.setInt(2, idmobil);
            pstmt.setDouble(3, totalbiaya);
            pstmt.executeUpdate();
            System.out.println("Data penjualan berhasil ditambahkan!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void lihatPenjualan() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM data_penjualan";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n=== Data Penjualan ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idpenjualan"));
                System.out.println("ID Pelanggan: " + rs.getInt("idpelanggan"));
                System.out.println("ID Mobil: " + rs.getInt("idmobil"));
                System.out.println("Total Biaya: " + rs.getDouble("totalbiaya"));
                System.out.println("--------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updatePenjualan() {
        try (Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            System.out.print("Masukkan ID Penjualan yang ingin diupdate: ");
            int id = scanner.nextInt();
            System.out.print("ID Pelanggan baru: ");
            int idpelanggan = scanner.nextInt();
            System.out.print("ID Mobil baru: ");
            int idmobil = scanner.nextInt();
            System.out.print("Total Biaya baru: ");
            double totalbiaya = scanner.nextDouble();

            String sql = "UPDATE data_penjualan SET idpelanggan = ?, idmobil = ?, totalbiaya = ? WHERE idpenjualan = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idpelanggan);
            pstmt.setInt(2, idmobil);
            pstmt.setDouble(3, totalbiaya);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            System.out.println("Data penjualan berhasil diupdate!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void hapusPenjualan() {
        try (Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            System.out.print("Masukkan ID Penjualan yang ingin dihapus: ");
            int id = scanner.nextInt();

            String sql = "DELETE FROM data_penjualan WHERE idpenjualan = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Data penjualan berhasil dihapus!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
