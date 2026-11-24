package laundry.gui;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

// Kelas GUI untuk Menu Utama setelah kasir berhasil login
public class MenuUtama extends JFrame {

    // Method main agar bisa dites terpisah (opsional)
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MenuUtama frame = new MenuUtama();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Konstruktor Menu Utama
    public MenuUtama() {
        setTitle("Menu Utama - CleanWashApp");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // --- LABEL JUDUL ---
        JLabel lblJudul = new JLabel("Menu Utama Kasir Laundry");
        lblJudul.setHorizontalAlignment(SwingConstants.CENTER); // Teks rata tengah
        lblJudul.setFont(new Font("Tahoma", Font.BOLD, 16)); // Memperbesar font
        lblJudul.setBounds(10, 30, 364, 30);
        getContentPane().add(lblJudul);

        // --- TOMBOL TRANSAKSI ---
        JButton btnTransaksi = new JButton("Kelola Transaksi");
        btnTransaksi.setBounds(110, 90, 160, 40); // Ukuran tombol sedikit dibesarkan
        getContentPane().add(btnTransaksi);

        // --- TOMBOL LOGOUT ---
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(110, 150, 160, 40);
        getContentPane().add(btnLogout);

        // --- EVENT HANDLING (NAVIGASI HALAMAN) ---
        
        // 1. Logika saat tombol 'Kelola Transaksi' diklik
        btnTransaksi.addActionListener(e -> {
            // Membuat objek dari FormTransaksi dan menampilkannya
            FormTransaksi form = new FormTransaksi();
            form.setVisible(true);
            
            // Menutup jendela MenuUtama ini agar layarnya tidak menumpuk
            dispose(); 
        });

        // 2. Logika saat tombol 'Logout' diklik
        btnLogout.addActionListener(e -> {
            // Memunculkan pop-up konfirmasi
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin keluar (logout)?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            
            if(confirm == JOptionPane.YES_OPTION) {
                // Membuka kembali halaman Login
                HalamanLogin login = new HalamanLogin();
                login.setVisible(true);
                
                // Menutup MenuUtama
                dispose();
            }
        });
    }
}