# 🎁 Sistem Reward Pelanggan v2.0

> Aplikasi loyalty program berbasis Java dengan sistem tier membership dan voucher redemption

[![Java Version](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Active-success.svg)]()

---

## 📋 Daftar Isi

- [Tentang Aplikasi](#-tentang-aplikasi)
- [Fitur Utama](#-fitur-utama)
- [Sistem Tier](#-sistem-tier)
- [Teknologi](#-teknologi)
- [Instalasi](#-instalasi)
- [Cara Menggunakan](#-cara-menggunakan)
- [Struktur Project](#-struktur-project)
- [Dokumentasi JavaDoc](#-dokumentasi-javadoc)
- [Screenshot](#-screenshot)
- [Kontributor](#-kontributor)
- [Lisensi](#-lisensi)

---

## 🎯 Tentang Aplikasi

**Sistem Reward Pelanggan** adalah aplikasi console-based yang dirancang untuk mengelola program loyalty pelanggan dengan fitur perhitungan poin otomatis, sistem tier membership, dan penukaran voucher.

### Keunggulan:
- ✅ **User-friendly Interface** - Menu interaktif yang mudah digunakan
- ✅ **Automatic Point Calculation** - Poin dihitung otomatis berdasarkan total belanja
- ✅ **Tier-based Bonus** - Bonus poin tambahan sesuai level membership
- ✅ **Transaction History** - Riwayat lengkap semua transaksi
- ✅ **Voucher Redemption** - Tukar poin dengan berbagai voucher belanja

---

## 🌟 Fitur Utama

### 1. **Registrasi Pelanggan**
- Input nama pelanggan
- Input total belanja awal
- Validasi input (tidak boleh negatif)
- Kalkulasi poin otomatis

### 2. **Sistem Poin Reward**
- **Base Rate**: 5% dari total belanja
- **Tier Bonus**: 0% - 10% tergantung tier
- Poin terakumulasi otomatis setiap transaksi

### 3. **Menu Interaktif**
```
1. Lihat Informasi Pelanggan
2. Tambah Transaksi Baru
3. Tukar Poin dengan Voucher
4. Lihat Riwayat Transaksi
5. Lihat Status Tier/Level
6. Keluar
```

### 4. **Penukaran Voucher**
| Voucher | Poin Dibutuhkan |
|---------|-----------------|
| Rp 50.000 | 100 poin |
| Rp 100.000 | 200 poin |
| Rp 250.000 | 500 poin |
| Rp 500.000 | 1000 poin |

### 5. **Riwayat Transaksi**
- Semua transaksi tersimpan dengan timestamp
- Format: `Nominal - Tanggal/Waktu`
- Akses kapan saja melalui menu

---

## 🏆 Sistem Tier

### Tier Membership

| Tier | Range Poin | Bonus Poin | Icon |
|------|-----------|------------|------|
| **Bronze** | 0 - 499 | 0% | 🥉 |
| **Silver** | 500 - 1,999 | +2% | 🥈 |
| **Gold** | 2,000 - 4,999 | +5% | 🥇 |
| **Platinum** | 5,000+ | +10% | 💎 |

### Contoh Perhitungan:

**Pelanggan Bronze (0% bonus):**
```
Belanja: Rp 1,000,000
Base Poin: 1,000,000 × 0.05 = 50,000 poin
Bonus: 0%
Total: 50,000 poin
```

**Pelanggan Gold (+5% bonus):**
```
Belanja: Rp 1,000,000
Base Poin: 1,000,000 × 0.05 = 50,000 poin
Bonus: 50,000 × 0.05 = 2,500 poin
Total: 52,500 poin
```

---

## 💻 Teknologi

- **Bahasa**: Java 21
- **Build Tool**: Javac (Command Line)
- **IDE**: IntelliJ IDEA / Eclipse / VS Code
- **Documentation**: JavaDoc
- **Version Control**: Git

### Requirements:
- Java Development Kit (JDK) 21 atau lebih tinggi
- Terminal/Command Prompt

---

## 📥 Instalasi

### 1. Clone Repository
```bash
git clone https://github.com/username/sistem-reward.git
cd sistem-reward
```

### 2. Compile Program
```bash
# Compile semua file Java
javac src/*.java -d bin

# Atau compile satu per satu
javac src/Customer.java -d bin
javac src/RewardSystem.java -d bin
javac src/Main.java -d bin
```

### 3. Jalankan Aplikasi
```bash
# Dari root directory
java -cp bin Main

# Atau langsung dari folder src (jika belum compile)
cd src
javac *.java
java Main
```

---

## 🚀 Cara Menggunakan

### **Step 1: Registrasi Pelanggan**
```
Masukkan nama pelanggan: Andi
Masukkan total belanja awal (Rp): 250000
```

### **Step 2: Pilih Menu**
Aplikasi akan menampilkan menu dengan 6 pilihan. Masukkan angka 1-6.

### **Step 3: Transaksi Baru**
```
Pilih menu: 2
Masukkan jumlah belanja (Rp): 150000

✨ Pelanggan Andi mendapat 7500 poin baru!
```

### **Step 4: Tukar Poin**
```
Pilih menu: 3
Poin Anda: 12500

1. Voucher Rp 50.000  - 100 poin
2. Voucher Rp 100.000 - 200 poin
3. Voucher Rp 250.000 - 500 poin
4. Voucher Rp 500.000 - 1000 poin

Pilih voucher: 1
✅ Voucher Rp 50.000 telah ditukarkan!
```

### **Step 5: Lihat Riwayat**
```
Pilih menu: 4

RIWAYAT TRANSAKSI
1. Rp 250,000 - 27/10/2025 14:30:45
2. Rp 150,000 - 27/10/2025 15:20:12

Total: 2 transaksi
```

---

## 📁 Struktur Project

```
SistemReward/
├── src/
│   ├── Main.java              # Entry point & menu controller
│   ├── Customer.java          # Model pelanggan & transaksi
│   └── RewardSystem.java      # Logic reward & tier system
├── bin/                       # Compiled .class files
├── docs/                      # JavaDoc HTML documentation
│   ├── index.html
│   ├── Customer.html
│   ├── Main.html
│   └── RewardSystem.html
├── README.md                  # File ini
└── LICENSE                    # MIT License
```

### **Deskripsi File:**

#### `Main.java`
- Entry point aplikasi
- Menghandle user input & menu navigation
- Orchestrator antara Customer dan RewardSystem

#### `Customer.java`
- Model data pelanggan
- Menyimpan nama, total belanja, poin
- Mengelola riwayat transaksi
- Method untuk redeem points

#### `RewardSystem.java`
- Core business logic
- Kalkulasi poin dengan tier bonus
- Manajemen tier system
- Processing voucher redemption

---

## 📚 Dokumentasi JavaDoc

### Generate JavaDoc:
```bash
javadoc -d docs \
  -encoding UTF-8 \
  -charset UTF-8 \
  -windowtitle "Sistem Reward v2.0" \
  -doctitle "<h1>Sistem Reward Pelanggan</h1>" \
  -author \
  -version \
  src/*.java
```

### Akses Dokumentasi:
Buka file `docs/index.html` di browser untuk melihat dokumentasi lengkap API.

**Dokumentasi mencakup:**
- ✅ Class overview & purpose
- ✅ Constructor documentation
- ✅ Method signatures & parameters
- ✅ Return values & exceptions
- ✅ Code examples
- ✅ Cross-references

---

## 📸 Screenshot

### Menu Utama
```
╔════════════════════════════════════╗
║   SISTEM REWARD PELANGGAN v2.0    ║
╚════════════════════════════════════╝

Masukkan nama pelanggan: Budi
Masukkan total belanja awal (Rp): 500000
```

### Informasi Pelanggan
```
╔════════════════════════════════════╗
║     INFORMASI PELANGGAN           ║
╚════════════════════════════════════╝
Nama Pelanggan : Budi
Total Belanja  : Rp 500,000
Poin Saat Ini  : 25,000
Jumlah Transaksi: 1
Tier Saat Ini  : 💎 Platinum
```

### Reward Berhasil
```
╔════════════════════════════════════╗
║      REWARD BERHASIL!             ║
╚════════════════════════════════════╝
✨ Pelanggan Budi mendapat 25000 poin baru!
📊 Total poin sekarang: 25000
🏆 Tier: 💎 Platinum

👑 Fantastis! Anda di tier tertinggi dengan bonus +10% poin!
```
---
<div align="center">

**⭐ Jika project ini membantu, berikan star! ⭐**

Made with ❤️ by LearnWithSuryaa

[⬆ Back to Top](#-sistem-reward-pelanggan-v20)

</div>