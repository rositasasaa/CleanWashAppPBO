package laundry.model;

public class LayananKiloan extends Layanan implements EstimasiWaktu {
    
    private double berat;
    private boolean isExpress; // Menentukan paketan

    // Konstruktor sekarang meminta status express
    public LayananKiloan(String namaPelanggan, double hargaDasar, double berat, boolean isExpress) {
        super(namaPelanggan, hargaDasar);
        this.berat = berat;
        this.isExpress = isExpress;
    }

    // [MEMENUHI SYARAT: Polimorfisme - Overriding]
    @Override
    public double hitungBiaya() {
        double total = hargaDasar * berat;
        // Jika express, tambah biaya layanan kilat Rp 5.000
        return isExpress ? total + 5000 : total; 
    }

    // [MEMENUHI SYARAT: Polimorfisme - Overloading]
    public double hitungBiaya(double diskon) {
        return hitungBiaya() - diskon;
    }

    // [MEMENUHI SYARAT: Implementasi Interface]
    @Override
    public String getEstimasiSelesai() {
        // Teks berubah dinamis tergantung pilihan kasir
        return isExpress ? "1 Hari (Express)" : "3 Hari (Reguler)";
    }
    
    public double getBerat() { return berat; }
    public void setBerat(double berat) { this.berat = berat; }
}
