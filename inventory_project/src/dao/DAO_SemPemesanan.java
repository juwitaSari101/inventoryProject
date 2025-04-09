/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Model_Barang;
import model.Model_DetPemesanan;
import model.Model_Distributor;
import model.Model_Pemesanan;
import model.Model_Pengguna;
import model.Model_SemPemesanan;
import service.Service_SemPemesanan;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author juwita
 */
public class DAO_SemPemesanan implements Service_SemPemesanan {

    private final java.sql.Connection conn;

    public DAO_SemPemesanan() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_SemPemesanan mod_pesan) {
        PreparedStatement st = null;
        String sql = "INSERT INTO sem_pemesanan (kode_barang, nama_barang, satuan, harga, jml_pesan, subtotal_pesan) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            st = conn.prepareStatement(sql);

            st.setString(1, mod_pesan.getMod_barang().getKode_barang());
            st.setString(2, mod_pesan.getMod_barang().getNama_barang());
            st.setString(3, mod_pesan.getMod_barang().getSatuan());
            st.setLong(4, mod_pesan.getMod_barang().getHarga());
            st.setLong(5, mod_pesan.getMod_detpesan().getJml_pesan());
            st.setLong(6, mod_pesan.getMod_detpesan().getSubtotal_pesan());
            st.executeUpdate();

            Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.INFO, "Data telah ditambahkan ke database.");

        } catch (SQLException ex) {
            Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_SemPemesanan mod_pesan) {
        PreparedStatement st = null;
        String sql = "UPDATE sem_pemesanan SET nama_barang=?, harga=?, jml_pesan=?, subtotal_pesan=? WHERE kode_barang='" + mod_pesan.getMod_barang().getKode_barang() + "'";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, mod_pesan.getMod_barang().getNama_barang());
            st.setLong(2, mod_pesan.getMod_barang().getHarga());
            st.setLong(3, mod_pesan.getMod_detpesan().getJml_pesan());
            st.setLong(4, mod_pesan.getMod_detpesan().getSubtotal_pesan());

            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Pesan: Perbarui Data Gagal!");
            Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void hapusData(Model_SemPemesanan mod_pesan) {
        String sql = "DELETE FROM sem_pemesanan WHERE kode_barang = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            // Ambil kode_barang dari Model_Barang di dalam Model_SemPemesanan
            st.setString(1, mod_pesan.getMod_barang().getKode_barang());

            // Eksekusi penghapusan data
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.INFO, "Data berhasil dihapus dari database.");
            } else {
                Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.WARNING, "Tidak ada data yang dihapus. Periksa kode_barang.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, "Gagal menghapus data.", ex);
        }
    }

    @Override
    public Model_SemPemesanan getByid(String id) {
        String sql = "SELECT * FROM sem_pemesanan WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Model_SemPemesanan smt = new Model_SemPemesanan();
                Model_Barang brg = new Model_Barang();
                Model_DetPemesanan det_psn = new Model_DetPemesanan();

                brg.setKode_barang(rs.getString("kode_barang"));
                brg.setNama_barang(rs.getString("nama_barang"));
                brg.setSatuan(rs.getString("satuan"));
                brg.setHarga(rs.getLong("harga"));

                det_psn.setJml_pesan(rs.getLong("Jml_pesan"));
                det_psn.setSubtotal_pesan(rs.getLong("Subtotal_pesan"));
                det_psn.setStatus(rs.getString("status"));

                smt.setMod_barang(brg);
                smt.setMod_detpesan(det_psn);

                Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.INFO, "Data ditemukan.");

                return smt;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, "Gagal mengambil data berdasarkan ID.", ex);
        }
        return null;
    }

    @Override
    public List<Model_SemPemesanan> getData() {
        PreparedStatement st = null;
        List<Model_SemPemesanan> list = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM sem_pemesanan";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Model_SemPemesanan smt = new Model_SemPemesanan();
                Model_Barang brg = new Model_Barang();
                Model_DetPemesanan det_psn = new Model_DetPemesanan();

                brg.setKode_barang(rs.getString("kode_barang"));
                brg.setNama_barang(rs.getString("nama_barang"));
                brg.setSatuan(rs.getString("satuan"));

                brg.setHarga(rs.getLong("harga"));

                det_psn.setJml_pesan(rs.getLong("jml_pesan"));

                smt.setSubtotal_pesan(rs.getLong("subtotal_pesan"));
                

                smt.setMod_barang(brg); // Set Model_Barang
                smt.setMod_detpesan(det_psn); // Set Model_DetPemesanan

                list.add(smt); // Tambahkan data ke list
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

}
