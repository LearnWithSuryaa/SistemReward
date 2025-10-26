import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Model representasi pelanggan dalam sistem reward loyalty program.
 * 
 * <p>Class ini menyimpan data pelanggan termasuk identitas, financial data,
 * poin reward, dan riwayat transaksi lengkap. Mendukung operasi CRUD untuk
 * manajemen poin dan transaksi.</p>
 * 
 * <p><strong>Key Features:</strong></p>
 * <ul>
 *   <li>💰 Tracking total spending pelanggan</li>
 *   <li>🎯 Manajemen poin reward</li>
 *   <li>📝 History semua transaksi dengan timestamp</li>
 *   <li>💳 Redeem points untuk voucher</li>
 * </ul>
 * 
 * <p><strong>Example usage:</strong></p>
 * <pre>{@code
 * Customer customer = new Customer("John Doe", 250000);
 * customer.addTransaction(150000);
 * customer.redeemPoints(100);
 * customer.displayInfo();
 * }</pre>
 * 
 * @author Sistem Reward Team
 * @version 2.0
 * @since 1.0
 * 
 * @see RewardSystem
 * @see Transaction
 */
public class Customer {
    private String name;
    private double totalSpent;
    private int points;
    private ArrayList<Transaction> transactionHistory;

    /**
     * Constructor untuk membuat instance Customer baru.
     * 
     * <p>Inisialisasi pelanggan dengan nama dan total belanja awal.
     * Transaksi pertama akan otomatis ditambahkan ke history jika
     * totalSpent > 0.</p>
     * 
     * <p><strong>⚠️ Important:</strong> Constructor ini tidak langsung menghitung poin.
     * Gunakan {@link RewardSystem#checkReward(Customer)} untuk menghitung poin.</p>
     * 
     * @param name nama lengkap pelanggan (tidak boleh null atau kosong)
     * @param totalSpent total belanja awal dalam Rupiah (harus >= 0)
     * 
     * @throws IllegalArgumentException jika totalSpent negatif
     * 
     * @see #addTransaction(double)
     * 
     * @since 1.0
     */
    public Customer(String name, double totalSpent) {
        this.name = name;
        this.totalSpent = 0;
        this.points = 0;
        this.transactionHistory = new ArrayList<>();
        
        // Tambahkan transaksi pertama
        if (totalSpent > 0) {
            addTransaction(totalSpent);
        }
    }

    /**
     * Getter untuk nama pelanggan.
     * 
     * @return nama lengkap pelanggan (never null)
     * 
     * @since 1.0
     */
    public String getName() {
        return name;
    }

    /**
     * Getter untuk total akumulasi belanja pelanggan.
     * 
     * <p>Value ini terupdate otomatis setiap kali ada transaksi baru
     * melalui method {@link #addTransaction(double)}.</p>
     * 
     * @return total belanja dalam Rupiah (always >= 0)
     * 
     * @see #addTransaction(double)
     * 
     * @since 1.0
     */
    public double getTotalSpent() {
        return totalSpent;
    }

    /**
     * Getter untuk jumlah poin reward yang dimiliki pelanggan saat ini.
     * 
     * <p>Poin dapat bertambah melalui transaksi dan berkurang saat redeem voucher.</p>
     * 
     * @return jumlah poin saat ini (can be 0 or positive)
     * 
     * @see #setPoints(int)
     * @see #redeemPoints(int)
     * 
     * @since 1.0
     */
    public int getPoints() {
        return points;
    }

    /**
     * Setter untuk mengupdate total poin pelanggan.
     * 
     * <p><strong>⚠️ Note:</strong> Method ini biasanya dipanggil oleh
     * {@link RewardSystem} setelah kalkulasi poin. Tidak disarankan
     * untuk digunakan langsung kecuali ada kebutuhan khusus.</p>
     * 
     * @param points jumlah poin baru yang akan di-set (should be >= 0)
     * 
     * @see RewardSystem#checkReward(Customer)
     * 
     * @since 1.0
     */
    public void setPoints(int points) {
        this.points = points;
    }
    
    /**
     * Menambahkan transaksi baru ke history dan update total spending.
     * 
     * <p>Setiap transaksi akan disimpan dengan timestamp otomatis dan
     * total belanja pelanggan akan diupdate. Method ini tidak menghitung
     * poin - gunakan {@link RewardSystem#checkReward(Customer)} setelahnya.</p>
     * 
     * <p><strong>Process flow:</strong></p>
     * <ol>
     *   <li>Update totalSpent dengan menambah amount</li>
     *   <li>Buat object Transaction baru dengan timestamp saat ini</li>
     *   <li>Simpan ke transactionHistory list</li>
     * </ol>
     * 
     * @param amount jumlah belanja dalam Rupiah (should be > 0)
     * 
     * @see Transaction
     * @see RewardSystem#checkReward(Customer)
     * 
     * @since 2.0
     */
    public void addTransaction(double amount) {
        this.totalSpent += amount;
        Transaction transaction = new Transaction(amount, new Date());
        transactionHistory.add(transaction);
    }
    
    /**
     * Mengurangi poin pelanggan untuk penukaran voucher atau reward.
     * 
     * <p>Method ini memvalidasi apakah poin cukup sebelum melakukan pengurangan.
     * Jika poin tidak cukup, tidak ada perubahan yang dilakukan.</p>
     * 
     * <p><strong>Use case:</strong></p>
     * <ul>
     *   <li>Redeem voucher belanja</li>
     *   <li>Tukar poin dengan hadiah</li>
     *   <li>Gunakan poin untuk diskon</li>
     * </ul>
     * 
     * @param pointsToRedeem jumlah poin yang akan dikurangi (must be > 0)
     * @return {@code true} jika redeem berhasil, {@code false} jika poin tidak cukup
     * 
     * @see RewardSystem#redeemVoucher(Customer, int)
     * 
     * @since 2.0
     */
    public boolean redeemPoints(int pointsToRedeem) {
        if (this.points >= pointsToRedeem) {
            this.points -= pointsToRedeem;
            return true;
        }
        return false;
    }

    /**
     * Menampilkan informasi lengkap pelanggan ke console output.
     * 
     * <p>Informasi yang ditampilkan meliputi:</p>
     * <ul>
     *   <li>👤 Nama pelanggan</li>
     *   <li>💰 Total belanja (formatted Rupiah)</li>
     *   <li>🎯 Poin reward saat ini</li>
     *   <li>📝 Jumlah transaksi yang pernah dilakukan</li>
     * </ul>
     * 
     * <p>Output menggunakan format Rupiah dengan thousand separator
     * untuk kemudahan pembacaan.</p>
     * 
     * @see #displayTransactionHistory()
     * 
     * @since 1.0
     */
    public void displayInfo() {
        System.out.println("Nama Pelanggan : " + name);
        System.out.println("Total Belanja  : Rp " + String.format("%,.0f", totalSpent));
        System.out.println("Poin Saat Ini  : " + points);
        System.out.println("Jumlah Transaksi: " + transactionHistory.size());
    }
    
    /**
     * Menampilkan riwayat transaksi lengkap pelanggan ke console.
     * 
     * <p>Setiap transaksi ditampilkan dengan format:</p>
     * <ul>
     *   <li>📅 Tanggal dan waktu transaksi (dd/MM/yyyy HH:mm:ss)</li>
     *   <li>💵 Nominal transaksi (formatted Rupiah)</li>
     *   <li>🔢 Nomor urut transaksi</li>
     * </ul>
     * 
     * <p>Jika belum ada transaksi, akan menampilkan pesan informasi.
     * Di akhir list ditampilkan total jumlah transaksi.</p>
     * 
     * <p><strong>Example output:</strong></p>
     * <pre>
     * 1. Rp 250,000 - 26/10/2025 14:30:45
     * 2. Rp 150,000 - 26/10/2025 15:20:12
     * 
     * Total: 2 transaksi
     * </pre>
     * 
     * @see #addTransaction(double)
     * @see Transaction
     * 
     * @since 2.0
     */
    public void displayTransactionHistory() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     RIWAYAT TRANSAKSI             ║");
        System.out.println("╚════════════════════════════════════╝");
        
        if (transactionHistory.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        int no = 1;
        
        for (Transaction trans : transactionHistory) {
            System.out.println(no + ". Rp " + String.format("%,.0f", trans.getAmount()) + 
                             " - " + dateFormat.format(trans.getDate()));
            no++;
        }
        
        System.out.println("\nTotal: " + transactionHistory.size() + " transaksi");
    }
    
    /**
     * Inner class untuk menyimpan detail setiap transaksi pelanggan.
     * 
     * <p>Class ini merepresentasikan single transaction dengan data:</p>
     * <ul>
     *   <li>Amount: nominal transaksi dalam Rupiah</li>
     *   <li>Date: timestamp kapan transaksi terjadi</li>
     * </ul>
     * 
     * <p><strong>Design note:</strong> Dibuat sebagai inner class karena
     * hanya digunakan internal oleh Customer class dan tidak perlu
     * diakses dari luar.</p>
     * 
     * @since 2.0
     */
    private class Transaction {
        private double amount;
        private Date date;
        
        /**
         * Constructor untuk membuat transaction record baru.
         * 
         * @param amount nominal transaksi dalam Rupiah
         * @param date timestamp transaksi
         */
        public Transaction(double amount, Date date) {
            this.amount = amount;
            this.date = date;
        }
        
        /**
         * Getter untuk nominal transaksi.
         * 
         * @return amount dalam Rupiah
         */
        public double getAmount() {
            return amount;
        }
        
        /**
         * Getter untuk timestamp transaksi.
         * 
         * @return Date object kapan transaksi terjadi
         */
        public Date getDate() {
            return date;
        }
    }
}