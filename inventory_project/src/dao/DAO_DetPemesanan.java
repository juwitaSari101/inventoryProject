package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Model_DetPemesanan;
import config.koneksi;
import model.Model_Pemesanan;
import model.Model_Barang;
import service.Service_DetPemesanan;

public class DAO_DetPemesanan implements Service_DetPemesanan  {

    private Connection conn;

    // Mengatur koneksi database
    public DAO_DetPemesanan() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_DetPemesanan mod_detpesan) {
        String sql = "INSERT INTO det_pemesanan (no_pesan, kode_barang, jml_pesan, subtotal_pesan, status) SELECT '"+mod_detpesan.getMod_pesan().getNo_pesan()+"', kode_barang, jml_pesan, subtotal_pesan, status FROM sem_pemesanan";
       
    }
    
    @Override
    public void sumTotal(Model_DetPemesanan mod_detpesan) {
        PreparedStatement st = null;
    ResultSet rs = null;
    String sql = "SELECT SUM(subtotal_pesan) FROM sem_pemesanan";
    try {
        st = conn.prepareStatement(sql);
        rs = st.executeQuery();
        if (rs.next()) {
            mod_detpesan.setSubtotal_pesan(rs.getLong(1));
        }
    } catch (SQLException ex) {
        java.util.logging.Logger.getLogger(DAO_DetPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } finally {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO_DetPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }
    }

    @Override
    public void hapusSementara(Model_DetPemesanan mod_detpesan) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Model_DetPemesanan getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
@Override
public List<Model_DetPemesanan> getData(String id) {
    PreparedStatement st = null;
    List<Model_DetPemesanan> list = new ArrayList<>();
    ResultSet rs = null;
    String sql = "SELECT det_psn.no_pesan, det_psn.kode_barang, brg.nama_barang, "
            + "brg.harga,brg.satuan, det_psn.jml_pesan, det_psn.subtotal_pesan, det_psn.status "
            + "FROM detail_pemesanan det_psn "
            + "INNER JOIN pemesanan psn ON psn.no_pesan = det_psn.no_pesan "
            + "INNER JOIN barang brg ON brg.kode_barang = det_psn.kode_barang "
            + "WHERE det_psn.no_pesan = ? ORDER BY det_psn.no_pesan ASC";
    try {
        st = conn.prepareStatement(sql);
        st.setString(1, id);
        rs = st.executeQuery();

        while (rs.next()) {
            // Buat instance untuk setiap tabel yang terlibat
            Model_Pemesanan psn = new Model_Pemesanan();
            Model_DetPemesanan det_psn = new Model_DetPemesanan();
            Model_Barang kodeBarang = new Model_Barang();

            // Mengisi data Model_Pemesanan
            psn.setNo_pesan(rs.getString("no_pesan"));       
            det_psn.setMod_pesan(psn);
            
            // Mengisi data Model_Barang
            kodeBarang.setKode_barang(rs.getString("kode_barang"));
            kodeBarang.setNama_barang(rs.getString("nama_barang"));
            kodeBarang.setSatuan(rs.getString("satuan"));
            kodeBarang.setHarga(rs.getLong("harga"));
            det_psn.setMod_barang(kodeBarang);

            // Mengisi data Model_DetPemesanan
            det_psn.setJml_pesan(rs.getLong("jml_pesan"));
            det_psn.setSubtotal_pesan(rs.getLong("subtotal_pesan"));
            det_psn.setStatus(rs.getString("status"));

            
            // Tambahkan objek ke daftar
            list.add(det_psn);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        throw new RuntimeException("Gagal mengambil data detail pemesanan", ex);
    } finally {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return list;
}


    @Override
    public List<Model_DetPemesanan> pencarian(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
