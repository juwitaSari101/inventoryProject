/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.koneksi;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Model_Barang;
import model.Model_Barangmasuk;
import service.Service_Barangmasuk;

/**
 *
 * @author juwita
 */
public class DAO_BarangMasuk implements Service_Barangmasuk {

    private final Connection conn;

    public DAO_BarangMasuk() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Barangmasuk mod_masuk) {
        String sqlInsert = "INSERT INTO barang_masuk (tanggal_masuk, kode_barangmasuk, nama_barang, satuan, jumlah_barangmasuk, jenis_barang) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlUpdateStok = "UPDATE barang SET stok = stok + ? WHERE nama_barang = ?";

        try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert); PreparedStatement psUpdateStok = conn.prepareStatement(sqlUpdateStok)) {

            // Insert data ke tabel barang_masuk
            psInsert.setString(1, mod_masuk.getTgl_masuk());
            psInsert.setString(2, mod_masuk.getKode_barangmasuk());
            psInsert.setString(3, mod_masuk.getMod_bar().getNama_barang());
            psInsert.setString(4, mod_masuk.getMod_bar().getSatuan());
            psInsert.setInt(5, mod_masuk.getJumlah_masuk());
            psInsert.setString(6, mod_masuk.getMod_bar().getJenis_barang());
            psInsert.executeUpdate();

            // Update stok di tabel barang
            psUpdateStok.setInt(1, mod_masuk.getJumlah_masuk());
            psUpdateStok.setString(2, mod_masuk.getMod_bar().getNama_barang());
            int rowsUpdated = psUpdateStok.executeUpdate();

            if (rowsUpdated > 0) {
                Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.INFO, "Stok barang berhasil diperbarui.");
            } else {
                Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.WARNING, "Barang tidak ditemukan di tabel barang untuk memperbarui stok.");
            }

        } catch (SQLException e) {
            Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, "Gagal menambahkan data barang masuk: " + e.getMessage(), e);
        }
    }

    @Override
public void hapusData(Model_Barangmasuk mod_masuk) {
    String sqlSelect = "SELECT jumlah_barangmasuk, nama_barang FROM barang_masuk WHERE kode_barangmasuk = ?";
    String sqlUpdateStok = "UPDATE barang SET stok = stok - ? WHERE nama_barang = ?";
    String sqlDelete = "DELETE FROM barang_masuk WHERE kode_barangmasuk = ?";

    try (PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
         PreparedStatement psUpdateStok = conn.prepareStatement(sqlUpdateStok);
         PreparedStatement psDelete = conn.prepareStatement(sqlDelete)) {

        // Ambil jumlah barang masuk dan nama barang sebelum data dihapus
        psSelect.setString(1, mod_masuk.getKode_barangmasuk());
        ResultSet rs = psSelect.executeQuery();

        if (rs.next()) {
            int jumlahBarangMasuk = rs.getInt("jumlah_barangmasuk");
            String namaBarang = rs.getString("nama_barang");

            System.out.println("Jumlah barang masuk: " + jumlahBarangMasuk);
            System.out.println("Nama barang: " + namaBarang);

            // Kurangi stok di tabel barang
            psUpdateStok.setInt(1, jumlahBarangMasuk);
            psUpdateStok.setString(2, namaBarang);

            int rowsUpdated = psUpdateStok.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Stok barang berhasil diperbarui.");
            } else {
                System.err.println("Gagal memperbarui stok. Barang mungkin tidak ditemukan.");
            }
        } else {
            System.err.println("Data barang masuk tidak ditemukan.");
        }

        // Hapus data dari tabel barang_masuk
        psDelete.setString(1, mod_masuk.getKode_barangmasuk());
        int rowsDeleted = psDelete.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Data barang masuk berhasil dihapus.");
        } else {
            System.out.println("Data barang masuk tidak ditemukan untuk dihapus.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Gagal menghapus data barang masuk: " + e.getMessage());
    }
}

    @Override
    public Model_Barangmasuk getByid(String id) {
        String sql = "SELECT * FROM barang_masuk WHERE kode_barangmasuk = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Model_Barangmasuk smt = new Model_Barangmasuk();
                Model_Barang brg = new Model_Barang();

                smt.setTgl_masuk(rs.getString("tanggal_masuk"));
                smt.setKode_barangmasuk(rs.getString("kode_barangmasuk"));
                brg.setNama_barang(rs.getString("nama_barang"));
                brg.setSatuan(rs.getString("satuan"));
                smt.setJumlah_masuk(rs.getInt("jumlah_barangmasuk"));
                brg.setJenis_barang(rs.getString("jenis_barang"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Model_Barangmasuk> getData() {
        PreparedStatement st = null;
        List<Model_Barangmasuk> list = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM barang_masuk";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Model_Barangmasuk smt = new Model_Barangmasuk();
                Model_Barang brg = new Model_Barang();

                smt.setTgl_masuk(rs.getString("tanggal_masuk"));
                smt.setKode_barangmasuk(rs.getString("kode_barangmasuk"));
                brg.setNama_barang(rs.getString("nama_barang"));
                brg.setSatuan(rs.getString("satuan"));
                smt.setJumlah_masuk(rs.getInt("jumlah_barangmasuk"));
                brg.setJenis_barang(rs.getString("jenis_barang"));

                smt.setMod_bar(brg);

                list.add(smt);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<Model_Barangmasuk> pencarian(String id) {
        String sql = "SELECT * FROM barang_masuk WHERE kode_barangmasuk LIKE ? OR nama_barang LIKE ?";
        List<Model_Barangmasuk> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + id + "%");
            ps.setString(2, "%" + id + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model_Barangmasuk smt = new Model_Barangmasuk();
                Model_Barang brg = new Model_Barang();

                smt.setTgl_masuk(rs.getString("tanggal_masuk"));
                smt.setKode_barangmasuk(rs.getString("kode_barangmasuk"));
                brg.setNama_barang(rs.getString("nama_barang"));
                brg.setSatuan(rs.getString("satuan"));
                smt.setJumlah_masuk(rs.getInt("jumlah_barangmasuk"));
                brg.setJenis_barang(rs.getString("jenis_barang"));

                smt.setMod_bar(brg);

                list.add(smt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String nomor() {
        String sql = "SELECT MAX(kode_barangmasuk) AS maxNo FROM barang_masuk";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maxNo = rs.getString("maxNo");
                if (maxNo != null) {
                    int nextId = Integer.parseInt(maxNo.substring(2)) + 1;
                    return "BM" + String.format("%04d", nextId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat menghasilkan nomor pemesanan: " + e.getMessage());
        }
        return "BM0001"; // Default jika belum ada data
    }

    @Override
    public void perbaruiData(Model_Barangmasuk mod_masuk) {
        String sqlSelect = "SELECT jumlah_barangmasuk FROM barang_masuk WHERE kode_barangmasuk = ?";
        String sqlUpdateMasuk = "UPDATE barang_masuk SET tanggal_masuk = ?, nama_barang = ?, satuan = ?, jumlah_barangmasuk = ?, jenis_barang = ? WHERE kode_barangmasuk = ?";
        String sqlUpdateStok = "UPDATE barang SET stok = stok + ? WHERE nama_barang = ?";

        try (PreparedStatement psSelect = conn.prepareStatement(sqlSelect); PreparedStatement psUpdateMasuk = conn.prepareStatement(sqlUpdateMasuk); PreparedStatement psUpdateStok = conn.prepareStatement(sqlUpdateStok)) {

            // Ambil data lama dari tabel barang_masuk
            psSelect.setString(1, mod_masuk.getKode_barangmasuk());
            ResultSet rs = psSelect.executeQuery();

            int jumlahLama = 0;
            if (rs.next()) {
                jumlahLama = rs.getInt("jumlah_barangmasuk");
            }

            // Hitung jumlah yang akan ditambahkan ke stok
            int jumlahBaru = mod_masuk.getJumlah_masuk();
            int selisihJumlah = jumlahBaru - jumlahLama;

            // Tambahkan selisih jumlah ke stok barang
            if (selisihJumlah > 0) {
                psUpdateStok.setInt(1, selisihJumlah);
                psUpdateStok.setString(2, mod_masuk.getMod_bar().getNama_barang());
                psUpdateStok.executeUpdate();
            }

            // Update data di tabel barang_masuk
            psUpdateMasuk.setString(1, mod_masuk.getTgl_masuk());
            psUpdateMasuk.setString(2, mod_masuk.getMod_bar().getNama_barang());
            psUpdateMasuk.setString(3, mod_masuk.getMod_bar().getSatuan());
            psUpdateMasuk.setInt(4, mod_masuk.getJumlah_masuk());
            psUpdateMasuk.setString(5, mod_masuk.getMod_bar().getJenis_barang());
            psUpdateMasuk.setString(6, mod_masuk.getKode_barangmasuk());
            int rowsUpdated = psUpdateMasuk.executeUpdate();

            if (rowsUpdated > 0) {
                Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.INFO, "Data barang masuk berhasil diperbarui.");
            } else {
                Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.WARNING, "Data barang masuk tidak ditemukan.");
            }

        } catch (SQLException e) {
            Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, "Gagal memperbarui data barang masuk: " + e.getMessage(), e);
        }
    }

}
