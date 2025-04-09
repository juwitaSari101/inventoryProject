package dao;

import Main.Menu_Utama;
import config.koneksi;
import model.Model_Login;
import service.Service_Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.Form_Login;

public class DAO_Login implements Service_Login {

    private Connection conn;
    private JFrame loginFrame;

    // Constructor untuk mendapatkan koneksi database
    public DAO_Login() {
        conn = koneksi.getConnection();
    }

    @Override
    public void prosesLogin(Model_Login mod_login) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String Id = null;
        String Nama = null;
        String Level2 = null;

        String sql = "SELECT * FROM pengguna WHERE (id_pengguna=?) OR username=? AND password=?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, mod_login.getId_user());
            st.setString(2, mod_login.getUsername());
            st.setString(3, mod_login.getPass_user()); // Gunakan password tanpa hash

            rs = st.executeQuery();

            if (rs.next()) {
                Id = rs.getString("id_pengguna");
                Nama = rs.getString("nama_pengguna");
                Level2 = rs.getString("level");

                Menu_Utama menu = new Menu_Utama(Id, Nama, Level2);
                menu.setVisible(true);
                menu.revalidate();

                Form_Login lg = new Form_Login();
                JOptionPane.showMessageDialog(null, "Login Berhasil! Selamat datang, " + Nama);
                lg.tutup = true;
            } else {
                JOptionPane.showMessageDialog(null, "Username dan Password Salah", "Pesan", JOptionPane.INFORMATION_MESSAGE);

                Form_Login lg = new Form_Login();
                lg.tutup = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
