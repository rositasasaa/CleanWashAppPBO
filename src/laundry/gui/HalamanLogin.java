package laundry.gui;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

// Kelas GUI untuk Halaman Login
public class HalamanLogin extends JFrame {

    // Deklarasi variabel untuk kolom input
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    // Method main agar halaman login bisa dijalankan pertama kali saat program di-Run
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HalamanLogin frame = new HalamanLogin();
                frame.setVisible(true); // Menampilkan jendela login ke layar
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Konstruktor: Tempat mengatur bentuk jendela dan tata letak komponen di dalamnya
    public HalamanLogin() {
        // --- PENGATURAN JENDELA UTAMA (FRAME) ---
        setTitle("Login Kasir - CleanWashApp"); // Judul di pojok kiri atas aplikasi
        setBounds(100, 100, 400, 300); // Ukuran jendela (x, y, lebar, tinggi)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Program benar-benar berhenti jika disilang (X)
        getContentPane().setLayout(null); // Memakai layout bebas (Absolute Layout) agar gampang diatur posisinya

        // --- LABEL JUDUL HALAMAN ---
        JLabel lblJudul = new JLabel("Login CleanWashApp");
        lblJudul.setFont(new Font("Tahoma", Font.BOLD, 18)); // Mengubah jenis dan ukuran huruf
        lblJudul.setHorizontalAlignment(SwingConstants.CENTER); // Mengetengahkan teks judul
        lblJudul.setBounds(10, 30, 364, 30);
        getContentPane().add(lblJudul);

        // --- KOMPONEN INPUT USERNAME ---
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 90, 80, 20);
        getContentPane().add(lblUsername);

        txtUsername = new JTextField(); // Kotak teks biasa untuk nama pengguna
        txtUsername.setBounds(140, 90, 180, 20);
        getContentPane().add(txtUsername);

        // --- KOMPONEN INPUT PASSWORD ---
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 130, 80, 20);
        getContentPane().add(lblPassword);

        // Menggunakan JPasswordField agar ketikan berubah menjadi titik-titik rahasia (enkripsi visual)
        txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 130, 180, 20);
        getContentPane().add(txtPassword);

        // --- KOMPONEN TOMBOL LOGIN ---
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(140, 180, 90, 30);
        getContentPane().add(btnLogin);

        // --- LOGIKA EVENT HANDLING (SAAT TOMBOL DIKLIK) ---
        btnLogin.addActionListener(e -> {
            // Mengambil teks dari kolom username
            String username = txtUsername.getText(); 
            // Mengambil teks rahasia dari kolom password (harus dikonversi ke String)
            String password = new String(txtPassword.getPassword()); 

            // Mengecek apakah kasir memasukkan data yang benar
            // Di sini kita pakai "admin" dan "12345" sebagai syarat masuk
            if (username.equals("admin") && password.equals("12345")) {
                
                // Jika benar, munculkan pop-up pemberitahuan sukses
                JOptionPane.showMessageDialog(this, "Login Berhasil!");
                
                // Memanggil dan membuka halaman Menu Utama
                MenuUtama menu = new MenuUtama();
                menu.setVisible(true);
                
                // Menutup (menghancurkan) jendela login ini agar tidak menumpuk di belakang Menu Utama
                dispose(); 
                
            } else {
                // Jika salah, munculkan pop-up error peringatan
                JOptionPane.showMessageDialog(this, "Username atau Password salah!", "Error Login", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}