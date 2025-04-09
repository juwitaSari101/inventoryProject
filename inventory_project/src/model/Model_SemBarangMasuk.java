/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author juwita
 */
public class Model_SemBarangMasuk {
    private Integer No_Masuk;
    private Model_Barang kodeBarang;
    private Integer Jmlh_Masuk;
    private Long Subtotal_Masuk;

    public Integer getNo_Masuk() {
        return No_Masuk;
    }

    public void setNo_Masuk(Integer No_Masuk) {
        this.No_Masuk = No_Masuk;
    }

    public Model_Barang getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(Model_Barang kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public Integer getJmlh_Masuk() {
        return Jmlh_Masuk;
    }

    public void setJmlh_Masuk(Integer Jmlh_Masuk) {
        this.Jmlh_Masuk = Jmlh_Masuk;
    }

    public Long getSubtotal_Masuk() {
        return Subtotal_Masuk;
    }

    public void setSubtotal_Masuk(Long Subtotal_Masuk) {
        this.Subtotal_Masuk = Subtotal_Masuk;
    }

    
}
