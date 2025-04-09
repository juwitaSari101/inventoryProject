package dao;

import config.koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Model_Pemesanan;
import model.Model_Distributor;
import model.Model_Pengguna;
import service.Service_Pemesanan;

public class DAO_Pemesanan implements Service_Pemesanan {

    private final Connection conn;

    public DAO_Pemesanan() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Pemesanan mod_pesan) {
        String sql = "INSERT INTO pemesanan (no_pesan, tgl_pesan, total_pesan, id_distributor, id_pengguna) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mod_pesan.getNo_pesan());
            ps.setString(2, mod_pesan.getTgl_pesan());
            ps.setInt(3, mod_pesan.getTotal_pesan());
            ps.setString(4, mod_pesan.getId_distributor().getId_distributor());
            ps.setString(5, mod_pesan.getId_pengguna().getId_pengguna());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void perbaruiData(Model_Pemesanan mod_pesan) {
        String sql = "UPDATE pemesanan SET tgl_pesan = ?, total_pesan = ?, id_distributor = ?, id_pengguna = ? WHERE no_pesan = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mod_pesan.getTgl_pesan());
            ps.setInt(2, mod_pesan.getTotal_pesan());
            ps.setString(3, mod_pesan.getId_distributor().getId_distributor());
            ps.setString(4, mod_pesan.getId_pengguna().getId_pengguna());
            ps.setString(5, mod_pesan.getNo_pesan());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hapusData(Model_Pemesanan mod_pesana) {
        String sql = "DELETE FROM pemesanan WHERE no_pesan = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mod_pesana.getNo_pesan());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Model_Pemesanan getByid(String id) {
        String sql = "SELECT * FROM pemesanan WHERE no_pesan = ?";
        Model_Pemesanan mod_pesan = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mod_pesan = new Model_Pemesanan();
                mod_pesan.setNo_pesan(rs.getString("no_pesan"));
                mod_pesan.setTgl_pesan(rs.getString("tgl_pesan"));
                mod_pesan.setTotal_pesan(rs.getInt("total_pesan"));

                Model_Distributor distributor = new Model_Distributor();
                distributor.setId_distributor(rs.getString("id_distributor"));
                mod_pesan.setId_distributor(distributor);

                Model_Pengguna pengguna = new Model_Pengguna();
                pengguna.setId_pengguna(rs.getString("id_pengguna"));
                mod_pesan.setId_pengguna(pengguna);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mod_pesan;
    }

    @Override
    public List<Model_Pemesanan> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM pemesanan";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Model_Pemesanan mod_pesan = new Model_Pemesanan();
                mod_pesan.setNo_pesan(rs.getString("no_pesan"));
                mod_pesan.setTgl_pesan(rs.getString("tgl_pesan"));
                mod_pesan.setTotal_pesan(rs.getInt("total_pesan"));

                Model_Distributor distributor = new Model_Distributor();
                distributor.setId_distributor(rs.getString("id_distributor"));
                mod_pesan.setId_distributor(distributor);

                Model_Pengguna pengguna = new Model_Pengguna();
                pengguna.setId_pengguna(rs.getString("id_pengguna"));
                mod_pesan.setId_pengguna(pengguna);

                list.add(mod_pesan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Model_Pemesanan> pencarian(String id) {
        String sql = "SELECT * FROM pemesanan WHERE no_pesan LIKE ?";
        List<Model_Pemesanan> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + id + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model_Pemesanan mod_pesan = new Model_Pemesanan();
                mod_pesan.setNo_pesan(rs.getString("no_pesan"));
                mod_pesan.setTgl_pesan(rs.getString("tgl_pesan"));
                mod_pesan.setTotal_pesan(rs.getInt("total_pesan"));

                Model_Distributor distributor = new Model_Distributor();
                distributor.setId_distributor(rs.getString("id_distributor"));
                mod_pesan.setId_distributor(distributor);

                Model_Pengguna pengguna = new Model_Pengguna();
                pengguna.setId_pengguna(rs.getString("id_pengguna"));
                mod_pesan.setId_pengguna(pengguna);

                list.add(mod_pesan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String nomor() {
        String sql = "SELECT MAX(no_pesan) AS maxNo FROM pemesanan";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maxNo = rs.getString("maxNo"); // Ganti maxId dengan maxNo
                if (maxNo != null) {
                    int nextId = Integer.parseInt(maxNo.substring(2)) + 1; // substring(2) karena "PS" memiliki panjang 2 karakter
                    return "PS" + String.format("%04d", nextId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat menghasilkan nomor pemesanan: " + e.getMessage());
        }
        return "PS0001"; // Default jika belum ada data
    }

}
