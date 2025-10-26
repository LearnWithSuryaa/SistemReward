import java.util.Scanner;

/**
 * Entrypoint aplikasi sistem reward dengan menu interaktif lengkap.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer customer = null;
        RewardSystem rewardSystem = new RewardSystem();
        
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║   SISTEM REWARD PELANGGAN v2.0    ║");
        System.out.println("╚════════════════════════════════════╝\n");
        
        // Registrasi pelanggan baru
        System.out.print("Masukkan nama pelanggan: ");
        String name = scanner.nextLine();
        
        System.out.print("Masukkan total belanja awal (Rp): ");
        double totalSpent = scanner.nextDouble();
        
        while (totalSpent < 0) {
            System.out.println("❌ Total belanja tidak boleh negatif!");
            System.out.print("Masukkan total belanja awal (Rp): ");
            totalSpent = scanner.nextDouble();
        }
        
        customer = new Customer(name, totalSpent);
        rewardSystem.checkReward(customer);
        
        // Menu utama
        boolean running = true;
        while (running) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║          MENU UTAMA               ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1. Lihat Informasi Pelanggan");
            System.out.println("2. Tambah Transaksi Baru");
            System.out.println("3. Tukar Poin dengan Voucher");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("5. Lihat Status Tier/Level");
            System.out.println("6. Keluar");
            System.out.print("\nPilih menu (1-6): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    displayCustomerInfo(customer, rewardSystem);
                    break;
                case 2:
                    addNewTransaction(scanner, customer, rewardSystem);
                    break;
                case 3:
                    redeemPoints(scanner, customer, rewardSystem);
                    break;
                case 4:
                    customer.displayTransactionHistory();
                    break;
                case 5:
                    rewardSystem.displayTierInfo(customer);
                    break;
                case 6:
                    System.out.println("\n✨ Terima kasih telah menggunakan sistem reward!");
                    System.out.println("Sampai jumpa lagi, " + customer.getName() + "! 👋");
                    running = false;
                    break;
                default:
                    System.out.println("❌ Pilihan tidak valid!");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Menampilkan informasi lengkap pelanggan ke console.
     * 
     * <p>Method ini akan menampilkan detail pelanggan termasuk nama, total belanja,
     * poin yang dimiliki, jumlah transaksi, dan tier saat ini dengan format yang rapi.</p>
     * 
     * @param customer objek Customer yang informasinya akan ditampilkan
     * @param rewardSystem objek RewardSystem untuk mendapatkan tier pelanggan
     * 
     * @see Customer#displayInfo()
     * @see RewardSystem#getTier(Customer)
     * 
     * @since 2.0
     */
    private static void displayCustomerInfo(Customer customer, RewardSystem rewardSystem) {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     INFORMASI PELANGGAN           ║");
        System.out.println("╚════════════════════════════════════╝");
        customer.displayInfo();
        System.out.println("Tier Saat Ini  : " + rewardSystem.getTier(customer));
    }
    
    /**
     * Memproses transaksi baru dari pelanggan.
     * 
     * <p>Method ini meminta input jumlah belanja dari user, memvalidasi input,
     * menambahkan transaksi ke history pelanggan, dan menghitung reward poin baru.</p>
     * 
     * <p><strong>Validasi:</strong></p>
     * <ul>
     *   <li>Jumlah belanja tidak boleh negatif</li>
     *   <li>Jika input invalid, transaksi dibatalkan</li>
     * </ul>
     * 
     * @param scanner objek Scanner untuk membaca input user
     * @param customer objek Customer yang melakukan transaksi
     * @param rewardSystem objek RewardSystem untuk menghitung reward
     * 
     * @throws java.util.InputMismatchException jika input bukan angka
     * 
     * @see Customer#addTransaction(double)
     * @see RewardSystem#checkReward(Customer)
     * 
     * @since 2.0
     */
    private static void addNewTransaction(Scanner scanner, Customer customer, RewardSystem rewardSystem) {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      TRANSAKSI BARU               ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.print("Masukkan jumlah belanja (Rp): ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        if (amount < 0) {
            System.out.println("❌ Jumlah belanja tidak boleh negatif!");
            return;
        }
        
        customer.addTransaction(amount);
        rewardSystem.checkReward(customer);
    }
    
    /**
     * Memproses penukaran poin pelanggan dengan voucher.
     * 
     * <p>Method ini menampilkan menu voucher yang tersedia, meminta pilihan user,
     * dan memproses penukaran poin. Sistem akan mengecek apakah poin cukup sebelum
     * melakukan penukaran.</p>
     * 
     * <p><strong>Voucher yang tersedia:</strong></p>
     * <ul>
     *   <li>Voucher Rp 50.000 → 100 poin</li>
     *   <li>Voucher Rp 100.000 → 200 poin</li>
     *   <li>Voucher Rp 250.000 → 500 poin</li>
     *   <li>Voucher Rp 500.000 → 1000 poin</li>
     * </ul>
     * 
     * @param scanner objek Scanner untuk membaca pilihan user
     * @param customer objek Customer yang akan menukar poin
     * @param rewardSystem objek RewardSystem untuk memproses penukaran
     * 
     * @see RewardSystem#redeemVoucher(Customer, int)
     * @see Customer#redeemPoints(int)
     * 
     * @since 2.0
     */
    private static void redeemPoints(Scanner scanner, Customer customer, RewardSystem rewardSystem) {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      TUKAR POIN                   ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("Poin Anda saat ini: " + customer.getPoints());
        System.out.println("\n📋 Daftar Voucher:");
        System.out.println("1. Voucher Rp 50.000  - 100 poin");
        System.out.println("2. Voucher Rp 100.000 - 200 poin");
        System.out.println("3. Voucher Rp 250.000 - 500 poin");
        System.out.println("4. Voucher Rp 500.000 - 1000 poin");
        System.out.println("5. Kembali");
        System.out.print("\nPilih voucher (1-5): ");
        
        int voucherChoice = scanner.nextInt();
        scanner.nextLine();
        
        rewardSystem.redeemVoucher(customer, voucherChoice);
    }
}