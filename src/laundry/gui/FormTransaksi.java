package laundry.gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat; // Class untuk memformat tampilan angka
import java.util.Locale;       // Class untuk mengatur standar regional (Indonesia)
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import laundry.model.*;

// Kelas GUI utama untuk halaman transaksi
public class FormTransaksi extends JFrame {
    // Deklarasi variabel komponen GUI
    private JTextField txtNama;
    private JTextField txtAngka;
    private JComboBox<String> cbTipe;
    private JComboBox<String> cbPaket;
    private JTable table;
    private DefaultTableModel model;
    
    // [MEMENUHI SYARAT: Penggunaan Collection (List)]
    // Array dinamis untuk menyimpan data objek Layanan di memori aplikasi
    private List<Layanan> daftarLayanan = new ArrayList<>();

    // Method utama (main) agar halaman ini bisa di-run langsung
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FormTransaksi frame = new FormTransaksi();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Konstruktor: Tempat mengatur tata letak dan bentuk awal komponen GUI
    public FormTransaksi() {
        setTitle("Kelola Transaksi Laundry");
        setBounds(100, 100, 600, 500); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Program berhenti saat tombol silang (X) diklik
        getContentPane().setLayout(null); // Menggunakan layout bebas (Absolute Layout)

        // --- KOMPONEN INPUT NAMA ---
        JLabel lblNama = new JLabel("Nama Pelanggan:");
        lblNama.setBounds(20, 20, 120, 20);
        getContentPane().add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(150, 20, 150, 20);
        getContentPane().add(txtNama);

        // --- KOMPONEN INPUT TIPE LAYANAN ---
        JLabel lblTipe = new JLabel("Tipe Layanan:");
        lblTipe.setBounds(20, 50, 120, 20);
        getContentPane().add(lblTipe);

        // [MEMENUHI SYARAT: Komponen input bervariasi] Menggunakan Dropdown (JComboBox)
        cbTipe = new JComboBox<>(new String[]{"Kiloan", "Satuan"});
        cbTipe.setBounds(150, 50, 150, 20);
        getContentPane().add(cbTipe);

        // --- KOMPONEN INPUT BERAT/JUMLAH ---
        JLabel lblAngka = new JLabel("Berat (kg) / Jumlah:");
        lblAngka.setBounds(20, 80, 120, 20);
        getContentPane().add(lblAngka);

        txtAngka = new JTextField();
        txtAngka.setBounds(150, 80, 150, 20);
        getContentPane().add(txtAngka);

        // --- KOMPONEN INPUT PAKET EXPRESS/REGULER ---
        JLabel lblPaket = new JLabel("Pilihan Paket:");
        lblPaket.setBounds(20, 110, 120, 20);
        getContentPane().add(lblPaket);

        cbPaket = new JComboBox<>(new String[]{"Reguler", "Express"});
        cbPaket.setBounds(150, 110, 150, 20);
        getContentPane().add(cbPaket);

        // --- KOMPONEN TOMBOL ---
        JButton btnTambah = new JButton("Tambah");
        btnTambah.setBounds(20, 150, 90, 25);
        getContentPane().add(btnTambah);

        JButton btnHapus = new JButton("Hapus");
        btnHapus.setBounds(120, 150, 90, 25);
        getContentPane().add(btnHapus);

        // --- KOMPONEN TABEL ---
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 190, 540, 240);
        getContentPane().add(scrollPane);

        // Menyiapkan judul kolom tabel. Perhatikan kolom ke-4 ditambahkan "(Rp)"
        String[] judulKolom = {"Nama", "Tipe", "Berat/Jml", "Total Biaya (Rp)", "Estimasi"};
        
        // [MENGUNCI TABEL] Menimpa (override) aturan tabel agar tidak bisa diketik/diedit manual oleh kasir
        model = new DefaultTableModel(judulKolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        table = new JTable(model);
        scrollPane.setViewportView(table);

        // --- EVENT HANDLING ---
        // Memberikan perintah ke tombol agar memanggil method tertentu saat diklik
        btnTambah.addActionListener(e -> tambahData());
        btnHapus.addActionListener(e -> hapusData());
    }

    // --- LOGIKA UNTUK MENAMBAH DATA (CREATE) ---
    private void tambahData() {
        // [MEMENUHI SYARAT: Exception Handling] Blok try-catch untuk menangkap error input
        try {
            // Mengambil teks yang diketik/dipilih oleh kasir dari GUI
            String nama = txtNama.getText();
            String tipe = cbTipe.getSelectedItem().toString();
            String paket = cbPaket.getSelectedItem().toString();
            // Mengubah teks angka menjadi tipe double. Jika diketik huruf, akan memicu NumberFormatException
            double angka = Double.parseDouble(txtAngka.getText()); 

            // Validasi jika kolom nama dibiarkan kosong
            if(nama.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return; // Menghentikan proses
            }
            
            // Logika konversi paket: Jika kasir milih "Express", maka isExpress bernilai true
            boolean isExpress = paket.equals("Express"); 

            // Menyiapkan variabel kosong untuk menampung objek (Polimorfisme)
            Layanan layananBaru;
            String estimasi = "";

            // Jika Kiloan, buat objek dari kelas LayananKiloan
            if (tipe.equals("Kiloan")) {
                LayananKiloan lk = new LayananKiloan(nama, 6000, angka, isExpress); 
                lk.setHargaDasar(6000); // Memicu pengecekan harga minus (CucianInvalidException)
                estimasi = lk.getEstimasiSelesai(); // Mengambil string estimasi dari Interface
                layananBaru = lk; // Memasukkan ke variabel induk
            } 
            // Jika Satuan, buat objek dari kelas LayananSatuan
            else {
                LayananSatuan ls = new LayananSatuan(nama, 15000, (int) angka, isExpress);
                ls.setHargaDasar(15000); 
                estimasi = ls.getEstimasiSelesai();
                layananBaru = ls;
            }

            // Menyimpan data pesanan utuh ke dalam Collection (backend)
            daftarLayanan.add(layananBaru);

            // --- FORMAT ANGKA ---
            // Menggunakan getNumberInstance agar keluarannya hanya angka dengan titik ribuan (Contoh: 15.000)
            NumberFormat formatAngka = NumberFormat.getNumberInstance(new Locale("id", "ID"));
            String biayaDiformat = formatAngka.format(layananBaru.hitungBiaya());

            // Memasukkan sebaris data baru ke dalam tabel GUI (frontend)
            model.addRow(new Object[]{
                layananBaru.getNamaPelanggan(),
                tipe,
                angka,
                biayaDiformat, // Hanya memasukkan angka format titik ke tabel
                estimasi
            });

            // Mengosongkan isian di form agar kasir bisa langsung input pelanggan berikutnya
            txtNama.setText("");
            txtAngka.setText("");
            cbPaket.setSelectedIndex(0);

        } catch (NumberFormatException ex) {
            // Menangkap error jika kasir mengetikkan huruf di kolom angka
            JOptionPane.showMessageDialog(this, "Berat atau Jumlah harus berupa angka!", "Error Input", JOptionPane.ERROR_MESSAGE);
        } catch (CucianInvalidException ex) {
            // [MEMENUHI SYARAT: Menangkap custom exception buatan sendiri]
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error Harga", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- LOGIKA UNTUK MENGHAPUS DATA (DELETE) ---
    private void hapusData() {
        // Mengecek baris ke berapa yang sedang diklik (disorot) oleh kasir di tabel
        int row = table.getSelectedRow();
        
        // Jika ada baris yang diklik (indeks 0 atau lebih besar)
        if (row >= 0) {
            // [MEMENUHI SYARAT: Dialog konfirmasi Yes/No]
            int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus pesanan ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            
            // Jika kasir menekan tombol "Yes"
            if (konfirmasi == JOptionPane.YES_OPTION) {
                daftarLayanan.remove(row); // Menghapus dari memori Collection
                model.removeRow(row);      // Menghapus dari tampilan tabel GUI
            }
        } else {
            // Jika kasir langsung klik tombol Hapus tanpa memilih baris di tabel
            JOptionPane.showMessageDialog(this, "Pilih baris di tabel terlebih dahulu untuk dihapus!");
        }
    }
}
