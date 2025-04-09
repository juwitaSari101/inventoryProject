/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author juwita
 */
public class Model_Barangmasuk {
    
    private String tgl_masuk;
    private String kode_barangmasuk;
    private int jumlah_masuk;
   
    private Model_Barang mod_bar;

    public String getTgl_masuk() {
        return tgl_masuk;
    }

    public void setTgl_masuk(String tgl_masuk) {
        this.tgl_masuk = tgl_masuk;
    }

    public String getKode_barangmasuk() {
        return kode_barangmasuk;
    }

    public void setKode_barangmasuk(String kode_barangmasuk) {
        this.kode_barangmasuk = kode_barangmasuk;
    }

    public int getJumlah_masuk() {
        return jumlah_masuk;
    }

    public void setJumlah_masuk(int jumlah_masuk) {
        this.jumlah_masuk = jumlah_masuk;
    }

    public Model_Barang getMod_bar() {
        return mod_bar;
    }

    public void setMod_bar(Model_Barang mod_bar) {
        this.mod_bar = mod_bar;
    }

   
}
