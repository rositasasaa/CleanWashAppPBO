package laundry.model;

// [MEMENUHI SYARAT: Minimal 1 custom exception]
// Kelas ini kita buat sendiri untuk menangani error khusus aplikasi Laundry, 
// misalnya jika kasir tidak sengaja menginputkan harga atau berat cucian yang minus (negatif).
public class CucianInvalidException extends Exception {
    
    // Konstruktor yang menerima pesan error
    public CucianInvalidException(String pesan) {
        super(pesan); // Mengirim pesan error ke kelas induk (Exception bawaan Java)
    }
}