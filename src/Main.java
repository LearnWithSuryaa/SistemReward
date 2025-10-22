/**
 * Entrypoint aplikasi demo untuk sistem reward.
 *
 * <p>Membuat instance {@link Customer} dan {@link RewardSystem} lalu menjalankan
 * pemeriksaan reward untuk menampilkan hasil ke console.</p>
 */
public class Main {

    /**
     * Metode utama yang dijalankan saat program dieksekusi.
     *
     * @param args argumen baris perintah (tidak digunakan)
     */
    public static void main(String[] args) {
        Customer customer = new Customer("Andi", 250000);
        customer.displayInfo();

        RewardSystem rewardSystem = new RewardSystem();
        rewardSystem.checkReward(customer);
    }
}
