package dao;

import config.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Model_Distributor;
import service.Service_Distributor;

public class DAO_Distributor implements Service_Distributor {

    private final Connection conn;

    public DAO_Distributor() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Distributor mod_distributor) {
        String sql = "INSERT INTO distributor (id_distributor, nama_distributor, alamat_distributor, telp_distributor) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mod_distributor.getId_distributor());
            ps.setString(2, mod_distributor.getNama_distributor());
            ps.setString(3, mod_distributor.getAlamat_distributor());
            ps.setString(4, mod_distributor.getTelp_distributor());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saat menambahkan data distributor: " + e.getMessage());
        }
    }

    @Override
    public void perbaruiData(Model_Distributor mod_distributor) {
        String sql = "UPDATE distributor SET nama_distributor = ?, alamat_distributor = ?, telp_distributor = ? WHERE id_distributor = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mod_distributor.getNama_distributor());
            ps.setString(2, mod_distributor.getAlamat_distributor());
            ps.setString(3, mod_distributor.getTelp_distributor());
            ps.setString(4, mod_distributor.getId_distributor());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saat memperbarui data distributor: " + e.getMessage());
        }
    }

    @Override
    public void hapusData(Model_Distributor mod_distributor) {
        String sql = "DELETE FROM distributor WHERE id_distributor = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mod_distributor.getId_distributor());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saat menghapus data distributor: " + e.getMessage());
        }
    }

    @Override
    public Model_Distributor getByid(String id) {
        String sql = "SELECT * FROM distributor WHERE id_distributor = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Model_Distributor distributor = new Model_Distributor();
                distributor.setId_distributor(rs.getString("id_distributor"));
                distributor.setNama_distributor(rs.getString("nama_distributor"));
                distributor.setAlamat_distributor(rs.getString("alamat_distributor"));
                distributor.setTelp_distributor(rs.getString("telp_distributor"));
                return distributor;
            }
        } catch (SQLException e) {
            System.err.println("Error saat mendapatkan data distributor: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Model_Distributor> getData() {
        String sql = "SELECT * FROM distributor";
        List<Model_Distributor> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model_Distributor distributor = new Model_Distributor();
                distributor.setId_distributor(rs.getString("id_distributor"));
                distributor.setNama_distributor(rs.getString("nama_distributor"));
                distributor.setAlamat_distributor(rs.getString("alamat_distributor"));
                distributor.setTelp_distributor(rs.getString("telp_distributor"));
                list.add(distributor);
            }
        } catch (SQLException e) {
            System.err.println("Error saat mendapatkan data distributor: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Model_Distributor> pencarian(String keyword) {
        String sql = "SELECT * FROM distributor WHERE nama_distributor LIKE ? OR id_distributor LIKE ?";
        List<Model_Distributor> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model_Distributor distributor = new Model_Distributor();
                distributor.setId_distributor(rs.getString("id_distributor"));
                distributor.setNama_distributor(rs.getString("nama_distributor"));
                distributor.setAlamat_distributor(rs.getString("alamat_distributor"));
                distributor.setTelp_distributor(rs.getString("telp_distributor"));
                list.add(distributor);
            }
        } catch (SQLException e) {
            System.err.println("Error saat mencari data distributor: " + e.getMessage());
        }
        return list;
    }

    @Override
    public String nomor() {
        String sql = "SELECT MAX(id_distributor) AS maxId FROM distributor";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maxId = rs.getString("maxId");
                if (maxId != null) {
                    int nextId = Integer.parseInt(maxId.substring(1)) + 1;
                    return "D" + String.format("%04d", nextId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat menghasilkan nomor distributor: " + e.getMessage());
        }
        return "D0001"; // Default jika belum ada data
    }
}
