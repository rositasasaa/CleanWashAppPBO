package laundry.model;

public class LayananSatuan extends Layanan implements EstimasiWaktu {
    
    private int jumlahBarang;
    private boolean isExpress;

    public LayananSatuan(String namaPelanggan, double hargaDasar, int jumlahBarang, boolean isExpress) {
        super(namaPelanggan, hargaDasar);
        this.jumlahBarang = jumlahBarang;
        this.isExpress = isExpress;
    }

    @Override
    public double hitungBiaya() {
        double total = hargaDasar * jumlahBarang;
        // Jika satuan express, harganya dikali 1.5 (lebih mahal)
        return isExpress ? total * 1.5 : total;
    }

    @Override
    public String getEstimasiSelesai() {
        return isExpress ? "1 Hari (Express)" : "3 Hari (Reguler)";
    }
    
    public int getJumlahBarang() { return jumlahBarang; }
    public void setJumlahBarang(int jumlahBarang) { this.jumlahBarang = jumlahBarang; }
}
