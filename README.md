# Tugas 3 - Reward System (Java)

Proyek contoh sederhana yang menunjukkan sistem reward pelanggan.

Deskripsi singkat

- Terdapat tiga kelas utama:
  - `Customer` — merepresentasikan data pelanggan (nama, total belanja, poin).
  - `RewardSystem` — menghitung poin berdasarkan total belanja dan memeriksa
    apakah pelanggan layak mendapatkan hadiah.
  - `Main` — entrypoint aplikasi yang menjalankan demo sederhana.

Instruksi kompilasi dan jalankan

1. Buka terminal pada folder proyek (folder yang berisi `src/`).

2. Kompilasi semua file Java:

```bash
javac src/*.java -d out
```

3. Jalankan program:

```bash
java -cp out Main
```

Menghasilkan JavaDoc

Untuk membuat dokumentasi JavaDoc dari komentar yang telah ditambahkan:

```bash
javadoc -d docs src/*.java
```

- Output JavaDoc akan berada di folder `docs`.
- Buka `docs/index.html` di browser untuk melihat dokumentasi.

Catatan

- Kode ini menggunakan angka pembulatan sederhana (konversi ke `int`) untuk
  menghitung poin. Sesuaikan logika pembulatan bila diperlukan.
- Jika Anda menggunakan paket (package), sesuaikan struktur folder dan perintah
  kompilasi/jalankan.

Kontak

Jika ada pertanyaan, hubungi pengembang proyek.