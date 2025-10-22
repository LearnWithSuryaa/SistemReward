/**
 * Representasi pelanggan dalam sistem reward.
 *
 * <p>Setiap instance menyimpan nama pelanggan, total pembelanjaan, dan
 * jumlah poin yang dimiliki. Kelas ini memberikan aksesors dan mutators
 * sederhana untuk properti tersebut serta metode utilitas untuk menampilkan
 * informasi pelanggan ke output standar.</p>
 */
public class Customer {
    private String name;
    private double totalSpent;
    private int points;

    /**
     * Membuat objek Customer baru.
     *
     * @param name       Nama pelanggan
     * @param totalSpent Total belanja pelanggan (dalam satuan mata uang lokal)
     */
    public Customer(String name, double totalSpent) {
        this.name = name;
        this.totalSpent = totalSpent;
        this.points = 0;
    }

    /**
     * Mengambil nama pelanggan.
     *
     * @return nama pelanggan
     */
    public String getName() {
        return name;
    }

    /**
     * Mengambil total belanja pelanggan.
     *
     * @return total belanja pelanggan
     */
    public double getTotalSpent() {
        return totalSpent;
    }

    /**
     * Mengambil jumlah poin yang dimiliki pelanggan saat ini.
     *
     * @return poin pelanggan
     */
    public int getPoints() {
        return points;
    }

    /**
     * Mengatur jumlah poin pelanggan.
     *
     * @param points jumlah poin yang akan disimpan untuk pelanggan
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Menampilkan informasi pelanggan ke sistem output (console).
     *
     * <p>Metode ini mencetak nama, total belanja, dan poin saat ini ke
     * System.out.</p>
     */
    public void displayInfo() {
        System.out.println("Nama Pelanggan : " + name);
        System.out.println("Total Belanja  : " + totalSpent);
        System.out.println("Poin Saat Ini  : " + points);
    }
}
