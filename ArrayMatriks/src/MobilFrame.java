import java.sql.*;
import java.util.Scanner;

public class MobilFrame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Menu Mobil ===");
            System.out.println("1. Tambah Data");
            System.out.println("2. Lihat Data");
            System.out.println("3. Update Data");
            System.out.println("4. Hapus Data");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tambahMobil();
                    break;
                case 2:
                    lihatMobil();
                    break;
                case 3:
                    updateMobil();
                    break;
                case 4:
                    hapusMobil();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void tambahMobil() {
        try (Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            System.out.print("Merk: ");
            String merk = scanner.nextLine();
            System.out.print("Tahun: ");
            int tahun = scanner.nextInt();
            System.out.print("Harga: ");
            double harga = scanner.nextDouble();

            String sql = "INSERT INTO data_mobil (merk, tahun, harga) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, merk);
            pstmt.setInt(2, tahun);
            pstmt.setDouble(3, harga);
            pstmt.executeUpdate();
            System.out.println("Data mobil berhasil ditambahkan!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void lihatMobil() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM data_mobil";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n=== Data Mobil ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idmobil"));
                System.out.println("Merk: " + rs.getString("merk"));
                System.out.println("Tahun: " + rs.getInt("tahun"));
                System.out.println("Harga: " + rs.getDouble("harga"));
                System.out.println("--------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateMobil() {
        try (Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            System.out.print("Masukkan ID Mobil yang ingin diupdate: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            System.out.print("Merk baru: ");
            String merk = scanner.nextLine();
            System.out.print("Tahun baru: ");
            int tahun = scanner.nextInt();
            System.out.print("Harga baru: ");
            double harga = scanner.nextDouble();

            String sql = "UPDATE data_mobil SET merk = ?, tahun = ?, harga = ? WHERE idmobil = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, merk);
            pstmt.setInt(2, tahun);
            pstmt.setDouble(3, harga);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            System.out.println("Data mobil berhasil diupdate!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void hapusMobil() {
        try (Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            System.out.print("Masukkan ID Mobil yang ingin dihapus: ");
            int id = scanner.nextInt();

            String sql = "DELETE FROM data_mobil WHERE idmobil = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Data mobil berhasil dihapus!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
