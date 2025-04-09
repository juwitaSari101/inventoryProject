/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author juwita
 */
public class Model_Barangkeluar {
    private String tgl_keluar;
    private String kode_barangkeluar;
    private int jumlah_keluar;
   
    private Model_Barang mod_bar; 

    public String getTgl_keluar() {
        return tgl_keluar;
    }

    public void setTgl_keluar(String tgl_keluar) {
        this.tgl_keluar = tgl_keluar;
    }

    public String getKode_barangkeluar() {
        return kode_barangkeluar;
    }

    public void setKode_barangkeluar(String kode_barangkeluar) {
        this.kode_barangkeluar = kode_barangkeluar;
    }

    public int getJumlah_keluar() {
        return jumlah_keluar;
    }

    public void setJumlah_keluar(int jumlah_keluar) {
        this.jumlah_keluar = jumlah_keluar;
    }

    public Model_Barang getMod_bar() {
        return mod_bar;
    }

    public void setMod_bar(Model_Barang mod_bar) {
        this.mod_bar = mod_bar;
    }

   
}
