/**
 * Core engine untuk loyalty reward program dengan sistem tier dan voucher redemption.
 * 
 * <p>Class ini bertanggung jawab untuk:</p>
 * <ul>
 *   <li>🎯 Kalkulasi poin reward berdasarkan total belanja</li>
 *   <li>🏆 Manajemen sistem tier (Bronze, Silver, Gold, Platinum)</li>
 *   <li>💳 Processing penukaran poin dengan voucher</li>
 *   <li>📊 Bonus poin berdasarkan tier membership</li>
 * </ul>
 * 
 * <p><strong>Tier System:</strong></p>
 * <table border="1">
 *   <tr><th>Tier</th><th>Min Points</th><th>Bonus Rate</th></tr>
 *   <tr><td>🥉 Bronze</td><td>0</td><td>0%</td></tr>
 *   <tr><td>🥈 Silver</td><td>500</td><td>+2%</td></tr>
 *   <tr><td>🥇 Gold</td><td>2000</td><td>+5%</td></tr>
 *   <tr><td>💎 Platinum</td><td>5000</td><td>+10%</td></tr>
 * </table>
 * Core engine untuk loyalty reward program dengan sistem tier dan voucher redemption.
 * 
 * <p>Class ini bertanggung jawab untuk:</p>
 * <ul>
 *   <li>🎯 Kalkulasi poin reward berdasarkan total belanja</li>
 *   <li>🏆 Manajemen sistem tier (Bronze, Silver, Gold, Platinum)</li>
 *   <li>💳 Processing penukaran poin dengan voucher</li>
 *   <li>📊 Bonus poin berdasarkan tier membership</li>
 * </ul>
 * 
 * <p><strong>Tier System:</strong></p>
 * <table border="1">
 *   <caption>Daftar Tier dan Benefit</caption>
 *   <thead>
 *     <tr><th>Tier</th><th>Min Points</th><th>Bonus Rate</th></tr>
 *   </thead>
 *   <tbody>
 *     <tr><td>🥉 Bronze</td><td>0</td><td>0%</td></tr>
 *     <tr><td>🥈 Silver</td><td>500</td><td>+2%</td></tr>
 *     <tr><td>🥇 Gold</td><td>2000</td><td>+5%</td></tr>
 *     <tr><td>💎 Platinum</td><td>5000</td><td>+10%</td></tr>
 *   </tbody>
 * </table>
 * 
 * <p><strong>Point Calculation Formula:</strong></p>
 * <pre>
 * basePoints = totalSpent × POINT_RATE (5%)
 * tierBonus = basePoints × bonusRate
 * totalPoints = basePoints + tierBonus
 * </pre>
 * 
 * <p><strong>Example usage:</strong></p>
 * <pre>{@code
 * RewardSystem system = new RewardSystem();
 * Customer customer = new Customer("Alice", 500000);
 * 
 * // Calculate and assign points
 * system.checkReward(customer);
 * 
 * // Check tier
 * String tier = system.getTier(customer); // "🥇 Gold"
 * 
 * // Redeem voucher
 * system.redeemVoucher(customer, 1); // Redeem Rp 50k voucher
 * }</pre>
 * 
 * @author Sistem Reward Team
 * @version 2.0
 * @since 1.0
 * 
 * @see Customer
 */
public class RewardSystem {

    /**
     * Rasio konversi dari total belanja ke poin reward (5%).
     * 
     * <p>Setiap Rp 1 belanja = 0.05 poin</p>
     * <p>Contoh: Belanja Rp 100,000 = 5,000 poin (sebelum tier bonus)</p>
     * 
     * @since 1.0
     */
    private static final double POINT_RATE = 0.05;
    
    /**
     * Threshold minimum poin untuk mencapai tier Silver (500 poin).
     * 
     * @since 2.0
     */
    private static final int SILVER_THRESHOLD = 500;
    
    /**
     * Threshold minimum poin untuk mencapai tier Gold (2000 poin).
     * 
     * @since 2.0
     */
    private static final int GOLD_THRESHOLD = 2000;
    
    /**
     * Threshold minimum poin untuk mencapai tier Platinum (5000 poin).
     * 
     * @since 2.0
     */
    private static final int PLATINUM_THRESHOLD = 5000;
    
    /**
     * Bonus rate tambahan untuk member Silver (+2%).
     * 
     * @since 2.0
     */
    private static final double SILVER_BONUS = 0.02;
    
    /**
     * Bonus rate tambahan untuk member Gold (+5%).
     * 
     * @since 2.0
     */
    private static final double GOLD_BONUS = 0.05;
    
    /**
     * Bonus rate tambahan untuk member Platinum (+10%).
     * 
     * @since 2.0
     */
    private static final double PLATINUM_BONUS = 0.10;

    /**
     * Constructor default untuk RewardSystem.
     * 
     * <p>Membuat instance baru dari RewardSystem dengan semua konstanta
     * sudah terinisialisasi.</p>
     * 
     * @since 1.0
     */
    public RewardSystem() {
        // Default constructor
    }

    /**
     * Menghitung total poin reward dengan bonus tier untuk pelanggan.
     * 
     * <p>Kalkulasi meliputi:</p>
     * <ol>
     *   <li>Base points: totalSpent × POINT_RATE (5%)</li>
     *   <li>Tier bonus: basePoints × bonusRate (tergantung tier)</li>
     *   <li>Total: basePoints × (1 + bonusRate)</li>
     * </ol>
     * 
     * <p><strong>Example:</strong></p>
     * <pre>
     * // Customer dengan totalSpent Rp 1,000,000 dan tier Gold (+5% bonus)
     * basePoints = 1,000,000 × 0.05 = 50,000
     * totalPoints = 50,000 × 1.05 = 52,500 poin
     * </pre>
     * 
     * @param customer objek Customer yang akan dihitung poinnya
     * @return total poin yang dihitung (sudah termasuk tier bonus), cast ke int
     * 
     * @see #getBonusRate(Customer)
     * @see Customer#getTotalSpent()
     * 
     * @since 1.0
     */
    public int calculateRewardPoints(Customer customer) {
        double basePoints = customer.getTotalSpent() * POINT_RATE;
        double bonusRate = getBonusRate(customer);
        double totalPoints = basePoints * (1 + bonusRate);
        
        return (int) totalPoints;
    }
    
    /**
     * Mendapatkan bonus rate berdasarkan tier membership pelanggan.
     * 
     * <p>Method helper private untuk menentukan persentase bonus
     * yang didapat customer berdasarkan tier-nya saat ini.</p>
     * 
     * <p><strong>Bonus mapping:</strong></p>
     * <ul>
     *   <li>🥉 Bronze → 0% (no bonus)</li>
     *   <li>🥈 Silver → +2% bonus</li>
     *   <li>🥇 Gold → +5% bonus</li>
     *   <li>💎 Platinum → +10% bonus</li>
     * </ul>
     * 
     * @param customer objek Customer untuk dicek tier-nya
     * @return bonus rate sebagai decimal (0.0, 0.02, 0.05, atau 0.10)
     * 
     * @see #getTier(Customer)
     * 
     * @since 2.0
     */
    private double getBonusRate(Customer customer) {
        String tier = getTier(customer);
        
        switch (tier) {
            case "🥈 Silver":
                return SILVER_BONUS;
            case "🥇 Gold":
                return GOLD_BONUS;
            case "💎 Platinum":
                return PLATINUM_BONUS;
            default:
                return 0.0;
        }
    }
    
    /**
     * Menentukan tier/level membership pelanggan berdasarkan total poin.
     * 
     * <p>Tier ditentukan secara otomatis berdasarkan akumulasi poin:</p>
     * <ul>
     *   <li>0-499 poin → 🥉 Bronze</li>
     *   <li>500-1999 poin → 🥈 Silver</li>
     *   <li>2000-4999 poin → 🥇 Gold</li>
     *   <li>5000+ poin → 💎 Platinum</li>
     * </ul>
     * 
     * <p>Tier lebih tinggi = bonus poin lebih besar untuk transaksi selanjutnya.</p>
     * 
     * @param customer objek Customer yang akan dicek tier-nya
     * @return String emoji tier (contoh: "🥇 Gold")
     * 
     * @see Customer#getPoints()
     * 
     * @since 2.0
     */
    public String getTier(Customer customer) {
        int points = customer.getPoints();
        
        if (points >= PLATINUM_THRESHOLD) {
            return "💎 Platinum";
        } else if (points >= GOLD_THRESHOLD) {
            return "🥇 Gold";
        } else if (points >= SILVER_THRESHOLD) {
            return "🥈 Silver";
        } else {
            return "🥉 Bronze";
        }
    }

    /**
     * Memproses kalkulasi reward dan update poin pelanggan.
     * 
     * <p>Method utama untuk processing reward setelah transaksi.
     * Melakukan kalkulasi poin baru, update ke customer, dan menampilkan
     * informasi reward lengkap ke console.</p>
     * 
     * <p><strong>Process flow:</strong></p>
     * <ol>
     *   <li>Ambil poin lama customer</li>
     *   <li>Hitung total poin baru (dengan tier bonus)</li>
     *   <li>Hitung selisih poin yang didapat</li>
     *   <li>Update poin ke customer object</li>
     *   <li>Display reward info dan tier message</li>
     * </ol>
     * 
     * <p><strong>⚠️ Important:</strong> Harus dipanggil setiap kali ada transaksi baru
     * untuk mengupdate poin customer.</p>
     * 
     * @param customer objek Customer yang akan diproses reward-nya
     * 
     * @see #calculateRewardPoints(Customer)
     * @see #displayTierMessage(Customer)
     * @see Customer#setPoints(int)
     * 
     * @since 1.0
     */
    public void checkReward(Customer customer) {
        int oldPoints = customer.getPoints();
        int newTotalPoints = calculateRewardPoints(customer);
        int earnedPoints = newTotalPoints - oldPoints;
        
        customer.setPoints(newTotalPoints);
        
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      REWARD BERHASIL!             ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("✨ Pelanggan " + customer.getName() + " mendapat " +
                earnedPoints + " poin baru!");
        System.out.println("📊 Total poin sekarang: " + customer.getPoints());
        System.out.println("🏆 Tier: " + getTier(customer));
        
        // Bonus message berdasarkan tier
        displayTierMessage(customer);
    }
    
    /**
     * Menampilkan pesan motivasi dan progress berdasarkan tier pelanggan.
     * 
     * <p>Method helper private untuk memberikan feedback ke user tentang:</p>
     * <ul>
     *   <li>Benefit tier saat ini</li>
     *   <li>Progress menuju tier berikutnya</li>
     *   <li>Motivasi untuk terus bertransaksi</li>
     * </ul>
     * 
     * <p><strong>Message examples:</strong></p>
     * <ul>
     *   <li>Bronze: "Kumpulkan X poin lagi untuk naik ke Silver!"</li>
     *   <li>Silver: "Selamat! Bonus +2%. X poin lagi ke Gold!"</li>
     *   <li>Gold: "Luar biasa! Bonus +5%. X poin lagi ke Platinum!"</li>
     *   <li>Platinum: "Fantastis! Anda di tier tertinggi!"</li>
     * </ul>
     * 
     * @param customer objek Customer untuk ditampilkan tier message-nya
     * 
     * @see #getTier(Customer)
     * 
     * @since 2.0
     */
    private void displayTierMessage(Customer customer) {
        String tier = getTier(customer);
        int points = customer.getPoints();
        
        System.out.println();
        
        switch (tier) {
            case "🥉 Bronze":
                int pointsToSilver = SILVER_THRESHOLD - points;
                System.out.println("💡 Kumpulkan " + pointsToSilver + " poin lagi untuk naik ke Silver!");
                break;
            case "🥈 Silver":
                int pointsToGold = GOLD_THRESHOLD - points;
                System.out.println("🎉 Selamat! Anda mendapat bonus +2% poin!");
                System.out.println("💡 " + pointsToGold + " poin lagi untuk naik ke Gold!");
                break;
            case "🥇 Gold":
                int pointsToPlatinum = PLATINUM_THRESHOLD - points;
                System.out.println("🎉 Luar biasa! Anda mendapat bonus +5% poin!");
                System.out.println("💡 " + pointsToPlatinum + " poin lagi untuk naik ke Platinum!");
                break;
            case "💎 Platinum":
                System.out.println("👑 Fantastis! Anda di tier tertinggi dengan bonus +10% poin!");
                break;
        }
    }
    
    /**
     * Menampilkan informasi lengkap tentang sistem tier reward.
     * 
     * <p>Method ini menampilkan dashboard tier yang berisi:</p>
     * <ul>
     *   <li>📊 Tier current user</li>
     *   <li>🎯 Total poin user saat ini</li>
     *   <li>📋 Daftar semua tier dengan requirement dan benefit</li>
     * </ul>
     * 
     * <p>Berguna untuk edukasi user tentang sistem tier dan
     * memotivasi mereka untuk naik level.</p>
     * 
     * @param customer objek Customer yang informasi tier-nya akan ditampilkan
     * 
     * @see #getTier(Customer)
     * 
     * @since 2.0
     */
    public void displayTierInfo(Customer customer) {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      SISTEM TIER REWARD           ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("\n📊 Tier Anda Saat Ini: " + getTier(customer));
        System.out.println("🎯 Poin Anda: " + customer.getPoints());
        System.out.println("\n📋 Daftar Tier:");
        System.out.println("🥉 Bronze   : 0 - 499 poin (Bonus: 0%)");
        System.out.println("🥈 Silver   : 500 - 1999 poin (Bonus: +2%)");
        System.out.println("🥇 Gold     : 2000 - 4999 poin (Bonus: +5%)");
        System.out.println("💎 Platinum : 5000+ poin (Bonus: +10%)");
    }
    
    /**
     * Memproses penukaran poin dengan voucher belanja.
     * 
     * <p>Method ini handle complete flow voucher redemption:</p>
     * <ol>
     *   <li>Validasi pilihan voucher (1-5)</li>
     *   <li>Map pilihan ke poin required dan value voucher</li>
     *   <li>Cek apakah poin customer cukup</li>
     *   <li>Kurangi poin jika cukup, atau tampilkan error</li>
     *   <li>Display success/failure message</li>
     * </ol>
     * 
     * <p><strong>Voucher catalog:</strong></p>
     * <table border="1">
     *   <caption>Daftar Voucher yang Tersedia</caption>
     *   <thead>
     *     <tr><th>Choice</th><th>Value</th><th>Points Required</th></tr>
     *   </thead>
     *   <tbody>
     *     <tr><td>1</td><td>Rp 50.000</td><td>100</td></tr>
     *     <tr><td>2</td><td>Rp 100.000</td><td>200</td></tr>
     *     <tr><td>3</td><td>Rp 250.000</td><td>500</td></tr>
     *     <tr><td>4</td><td>Rp 500.000</td><td>1000</td></tr>
     *     <tr><td>5</td><td>Kembali</td><td>-</td></tr>
     *   </tbody>
     * </table>
     * 
     * <p><strong>Error handling:</strong> Jika poin tidak cukup, customer
     * akan mendapat notifikasi berapa poin yang dibutuhkan dan poin saat ini.</p>
     * 
     * @param customer objek Customer yang akan redeem voucher
     * @param voucherChoice pilihan voucher (1-5) dari user input
     * 
     * @see Customer#redeemPoints(int)
     * @see Customer#getPoints()
     * 
     * @since 2.0
     */
    public void redeemVoucher(Customer customer, int voucherChoice) {
        int pointsNeeded = 0;
        String voucherValue = "";
        
        switch (voucherChoice) {
            case 1:
                pointsNeeded = 100;
                voucherValue = "Rp 50.000";
                break;
            case 2:
                pointsNeeded = 200;
                voucherValue = "Rp 100.000";
                break;
            case 3:
                pointsNeeded = 500;
                voucherValue = "Rp 250.000";
                break;
            case 4:
                pointsNeeded = 1000;
                voucherValue = "Rp 500.000";
                break;
            case 5:
                return;
            default:
                System.out.println("❌ Pilihan tidak valid!");
                return;
        }
        
        if (customer.redeemPoints(pointsNeeded)) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║   PENUKARAN BERHASIL! 🎉          ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("✅ Voucher " + voucherValue + " telah ditukarkan!");
            System.out.println("💳 Poin terpakai: " + pointsNeeded);
            System.out.println("📊 Sisa poin: " + customer.getPoints());
        } else {
            System.out.println("\n❌ Poin Anda tidak cukup!");
            System.out.println("💡 Anda memerlukan " + pointsNeeded + " poin");
            System.out.println("📊 Poin Anda saat ini: " + customer.getPoints());
        }
    }
}