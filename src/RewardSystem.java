/**
 * Sistem reward sederhana yang menghitung dan memeriksa poin pelanggan berdasarkan
 * total belanja mereka.
 *
 * <p>Konversi total belanja ke poin menggunakan konstanta {@code POINT_RATE}.
 * Metode publik menyediakan perhitungan poin baru dan logika untuk mengecek
 * apakah pelanggan berhak mendapat hadiah.</p>
 */
public class RewardSystem {

    /**
     * Rasio konversi belanja ke poin. Misal 0.05 berarti 5% dari total belanja
     * dikonversi menjadi poin (dibulatkan ke bawah sebagai integer).
     */
    private static final double POINT_RATE = 0.05;

    /**
     * Menghitung berapa poin yang didapatkan pelanggan berdasarkan total belanja.
     *
     * @param customer objek Customer yang akan dihitung poinnya
     * @return jumlah poin baru (tipe int)
     */
    public int calculateRewardPoints(Customer customer) {
        return (int) (customer.getTotalSpent() * POINT_RATE);
    }

    /**
     * Menambahkan poin yang dihitung ke akun pelanggan, menampilkan jumlah poin
     * baru dan total poin sekarang, serta menampilkan pesan hadiah jika memenuhi
     * syarat (lebih dari 100 poin).
     *
     * @param customer objek Customer yang akan diperiksa dan diupdate poinnya
     */
    public void checkReward(Customer customer) {
        int newPoints = customer.getPoints() + calculateRewardPoints(customer);
        customer.setPoints(newPoints);

        System.out.println("Pelanggan " + customer.getName() + " mendapat " +
                calculateRewardPoints(customer) + " poin baru!");
        System.out.println("Total poin sekarang : " + customer.getPoints());

        if (customer.getPoints() > 100) {
            System.out.println("🎁 Selamat! Anda mendapatkan hadiah voucher belanja!");
        } else {
            System.out.println("Kumpulkan lebih banyak poin untuk hadiah menarik!");
        }
    }
}
