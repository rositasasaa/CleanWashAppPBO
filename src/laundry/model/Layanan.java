package laundry.model;

// [MEMENUHI SYARAT: Minimal 1 abstract class]
// Kelas ini menjadi "cetak biru" (blueprint). Kita tidak akan membuat objek langsung dari kelas ini,
// melainkan dari kelas turunannya nanti (LayananKiloan, LayananSatuan) seperti contoh di pedoman tugas.
public abstract class Layanan {
    
    // [MEMENUHI SYARAT: Enkapsulasi - Atribut private/protected]
    // Kita gunakan protected agar variabel ini bisa diakses langsung oleh kelas anak (subclass),
    // tapi tetap aman dan tersembunyi dari kelas lain di luar hierarki ini.
    protected String namaPelanggan;
    protected double hargaDasar;
    
    // Konstruktor kelas Layanan
    public Layanan(String namaPelanggan, double hargaDasar) {
        this.namaPelanggan = namaPelanggan;
        this.hargaDasar = hargaDasar;
    }
    
    // [MEMENUHI SYARAT: Minimal 1 method abstrak]
    // Method ini tidak punya "isi" di sini. Kelas turunan wajib mengisi rumus hitungannya
    // (polimorfisme) karena cara hitung laundry kiloan dan satuan pasti berbeda.
    public abstract double hitungBiaya(); 
    
    // [MEMENUHI SYARAT: Akses lewat getter/setter yang memuat validasi]
    public void setHargaDasar(double hargaDasar) throws CucianInvalidException {
        // Validasi: Harga tidak boleh negatif (sesuai contoh di ketentuan tugas)
        if (hargaDasar < 0) {
            // Jika kasir input minus, program akan melempar exception buatan kita tadi
            throw new CucianInvalidException("Validasi Gagal: Harga dasar tidak boleh negatif!");
        }
        this.hargaDasar = hargaDasar;
    }
    
    // Getter standar untuk mengambil nilai data
    public double getHargaDasar() {
        return hargaDasar;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

	public String getEstimasiSelesai() {
		// TODO Auto-generated method stub
		return null;
	}
}