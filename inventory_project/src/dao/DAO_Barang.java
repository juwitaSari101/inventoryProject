package dao;

import config.koneksi;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Model_Barang;
import service.Service_Barang;

public class DAO_Barang implements Service_Barang {

    private final Connection conn;

    public DAO_Barang() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Barang mod_barang) {
        String sql = "INSERT INTO barang (kode_barang, nama_barang, satuan, jenis_barang, harga, stok) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mod_barang.getKode_barang());
            ps.setString(2, mod_barang.getNama_barang());
            ps.setString(3, mod_barang.getSatuan());
            ps.setString(4, mod_barang.getJenis_barang());
            ps.setLong(5, mod_barang.getHarga());
            ps.setInt(6, mod_barang.getStok());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saat menambahkan data: " + e.getMessage());
        }
    }

    @Override
    public void perbaruiData(Model_Barang mod_barang) {
        String sql = "UPDATE barang SET nama_barang = ?, satuan = ?, jenis_barang = ?, harga = ?, stok = ? WHERE kode_barang = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Set parameter sesuai urutan di query
            ps.setString(1, mod_barang.getNama_barang());
            ps.setString(2, mod_barang.getSatuan());
            ps.setString(3, mod_barang.getJenis_barang());
            ps.setLong(4, mod_barang.getHarga());
            ps.setInt(5, mod_barang.getStok());
            ps.setString(6, mod_barang.getKode_barang()); 

            // Eksekusi query
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data berhasil diperbarui!");
            } else {
                System.out.println("Tidak ada data yang diperbarui. Periksa kode_barang.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Kesalahan saat memperbarui data: " + e.getMessage());
        }
    }

    @Override
    public void hapusData(Model_Barang mod_barang) {
        String sql = "DELETE FROM barang WHERE kode_barang = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mod_barang.getKode_barang());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Model_Barang getByid(String id) {
        String sql = "SELECT * FROM barang WHERE kode_barang = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Model_Barang barang = new Model_Barang();
                barang.setKode_barang(rs.getString("kode_barang"));
                barang.setNama_barang(rs.getString("nama_barang"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setHarga(rs.getLong("harga"));
                barang.setStok(rs.getInt("stok"));
                barang.setJenis_barang(rs.getString("jenis_barang"));
                return barang;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Model_Barang> getDataByID() {
        String sql = "SELECT * FROM barang ORDER BY kode_barang";
        List<Model_Barang> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model_Barang barang = new Model_Barang();
                barang.setKode_barang(rs.getString("kode_barang"));
                barang.setNama_barang(rs.getString("nama_barang"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setHarga(rs.getLong("harga"));
                barang.setStok(rs.getInt("stok"));
                barang.setJenis_barang(rs.getString("jenis_barang"));
                list.add(barang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Model_Barang> getData() {
        return getDataByID(); // Menggunakan logika yang sama
    }

    @Override
    public List<Model_Barang> pencarian(String id) {
    String sql = "SELECT * FROM barang WHERE kode_barang LIKE ? OR nama_barang LIKE ?";
    List<Model_Barang> list = new ArrayList<>();
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, "%" + id + "%");
        ps.setString(2, "%" + id + "%");  
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Model_Barang barang = new Model_Barang();
            barang.setKode_barang(rs.getString("kode_barang"));
            barang.setNama_barang(rs.getString("nama_barang"));
            barang.setSatuan(rs.getString("satuan"));
            barang.setHarga(rs.getLong("harga"));
            barang.setStok(rs.getInt("stok"));
            barang.setJenis_barang(rs.getString("jenis_barang"));
            list.add(barang);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


    @Override
    public List<Model_Barang> pencarian2(String id) {
        return pencarian(id); // Menggunakan logika yang sama
    }

    @Override
    public String nomor() {
        String nomorBarang = "";
        try {
            // Ambil tanggal saat ini
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
            String tanggalFormatted = today.format(formatter);

            // Query untuk mendapatkan nomor urut terakhir berdasarkan tanggal
            String query = "SELECT kode_barang FROM barang WHERE kode_barang LIKE ? ORDER BY kode_barang DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "b" + tanggalFormatted + "%");

            System.out.println("Pattern yang dicari: b" + tanggalFormatted + "%");  // Log query pattern

            ResultSet rs = ps.executeQuery();

            int nomorUrut = 1; // Default nomor urut jika belum ada data
            if (rs.next()) {
                String lastKode = rs.getString("kode_barang");
                System.out.println("Kode Barang Terakhir: " + lastKode);  // Log kode barang terakhir
                // Ambil nomor urut terakhir dari kode
                String lastUrut = lastKode.substring(lastKode.length() - 3); // Ambil 3 digit terakhir
                nomorUrut = Integer.parseInt(lastUrut) + 1;
            } else {
                System.out.println("Tidak ada data sebelumnya, mulai dari 001");
            }

            // Format kode barang
            nomorBarang = "b" + tanggalFormatted + String.format("%03d", nomorUrut);

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return nomorBarang;
    }

    @Override
    public String nomor2() {
        return generateNomor("C");
    }

    private String generateNomor(String prefix) {
        String sql = "SELECT MAX(kode_barang) AS maxKode FROM barang WHERE kode_barang LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, prefix + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maxKode = rs.getString("maxKode");
                if (maxKode != null) {
                    int nextNumber = Integer.parseInt(maxKode.substring(1)) + 1;
                    return prefix + String.format("%04d", nextNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prefix + "0001"; // Default jika belum ada data
    }
}
