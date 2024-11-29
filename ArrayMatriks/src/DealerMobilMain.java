import java.util.Scanner;

public class DealerMobilMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Sistem Dealer Mobil ===");
            System.out.println("1. Kelola Data Pelanggan");
            System.out.println("2. Kelola Data Mobil");
            System.out.println("3. Kelola Data Penjualan");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    PelangganFrame.main(null);
                    break;
                case 2:
                    MobilFrame.main(null);
                    break;
                case 3:
                    PenjualanFrame.main(null);
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan sistem ini!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi!");
            }
        }
    }
}
