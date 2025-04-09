package dao;

import config.koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Model_Pengguna;
import service.Service_Pengguna;

public class DAO_Pengguna implements Service_Pengguna {

    private final Connection conn;

    public DAO_Pengguna() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Pengguna mod_pengguna) {
        String sql = "INSERT INTO pengguna (id_pengguna, nama_pengguna, username, password, telp_pengguna, alamat_pengguna, level) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mod_pengguna.getId_pengguna());
            ps.setString(2, mod_pengguna.getNama_pengguna());
            ps.setString(3, mod_pengguna.getUsername());
            ps.setString(4, mod_pengguna.getPassword()); // Tidak menggunakan hashing
            ps.setString(5, mod_pengguna.getTelp_pengguna());
            ps.setString(6, mod_pengguna.getAlamat_pengguna());
            ps.setString(7, mod_pengguna.getLevel());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void perbaruiData(Model_Pengguna mod_pengguna) {
        String sql = "UPDATE pengguna SET nama_pengguna = ?, username = ?, password = ?, telp_pengguna = ?, alamat_pengguna = ?, level = ? WHERE id_pengguna = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mod_pengguna.getNama_pengguna());
            ps.setString(2, mod_pengguna.getUsername());
            ps.setString(3, mod_pengguna.getPassword());
            ps.setString(4, mod_pengguna.getTelp_pengguna());
            ps.setString(5, mod_pengguna.getAlamat_pengguna());
            ps.setString(6, mod_pengguna.getLevel());
            ps.setString(7, mod_pengguna.getId_pengguna());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hapusData(Model_Pengguna mod_pengguna) {
        String sql = "DELETE FROM pengguna WHERE id_pengguna = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mod_pengguna.getId_pengguna());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Model_Pengguna getByid(String id) {
        Model_Pengguna mod_pengguna = null;
        String sql = "SELECT * FROM pengguna WHERE id_pengguna = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mod_pengguna = new Model_Pengguna();
                mod_pengguna.setId_pengguna(rs.getString("id_pengguna"));
                mod_pengguna.setNama_pengguna(rs.getString("nama_pengguna"));
                mod_pengguna.setPassword(rs.getString("password"));
                mod_pengguna.setTelp_pengguna(rs.getString("telp_pengguna"));
                mod_pengguna.setAlamat_pengguna(rs.getString("alamat_pengguna"));
                mod_pengguna.setLevel(rs.getString("level"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mod_pengguna;
    }

    @Override
    public List<Model_Pengguna> getData() {
        List<Model_Pengguna> listPengguna = new ArrayList<>();
        String sql = "SELECT * FROM pengguna";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Model_Pengguna mod_pengguna = new Model_Pengguna();
                mod_pengguna.setId_pengguna(rs.getString("id_pengguna"));
                mod_pengguna.setNama_pengguna(rs.getString("nama_pengguna"));
                mod_pengguna.setUsername(rs.getString("username"));
                mod_pengguna.setPassword(rs.getString("password"));
                mod_pengguna.setTelp_pengguna(rs.getString("telp_pengguna"));
                mod_pengguna.setAlamat_pengguna(rs.getString("alamat_pengguna"));
                mod_pengguna.setLevel(rs.getString("level"));
                listPengguna.add(mod_pengguna);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPengguna;
    }

    @Override
    public List<Model_Pengguna> pencarian(String id) {
        List<Model_Pengguna> listPengguna = new ArrayList<>();
        String sql = "SELECT * FROM pengguna WHERE id_pengguna LIKE? OR nama_pengguna LIKE ? OR username LIKE ? OR tel_pengguna LIKE ? OR alamat_pengguna LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + id + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model_Pengguna mod_pengguna = new Model_Pengguna();
                mod_pengguna.setId_pengguna(rs.getString("id_pengguna"));
                mod_pengguna.setNama_pengguna(rs.getString("nama_pengguna"));
                mod_pengguna.setUsername(rs.getString("username"));
                mod_pengguna.setPassword(rs.getString("password"));
                mod_pengguna.setTelp_pengguna(rs.getString("telp_pengguna"));
                mod_pengguna.setAlamat_pengguna(rs.getString("alamat_pengguna"));
                mod_pengguna.setLevel(rs.getString("level"));
                listPengguna.add(mod_pengguna);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPengguna;
    }

    @Override
    public String nomor() {
        String sql = "SELECT MAX(id_pengguna) FROM pengguna";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String lastId = rs.getString(1);
                if (lastId != null) {
                    // Mengambil angka terakhir dan menambahkannya
                    int lastNumber = Integer.parseInt(lastId.substring(1)); // Mengambil angka setelah 'p'
                    lastNumber++; // Increment angka
                    return String.format("p%04d", lastNumber); // Format menjadi pXXXX
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Jika tidak ada data, mulai dari p0001
        return "p0001";
    }

}
