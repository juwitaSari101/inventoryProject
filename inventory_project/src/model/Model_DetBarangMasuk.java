/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author juwita
 */
public class Model_DetBarangMasuk {
    private Integer no_masuk;
    private Model_Barang kodeBarang;
    private Integer jml_masuk;
    private Long subtotal_masuk;

    public Integer getNo_masuk() {
        return no_masuk;
    }

    public void setNo_masuk(Integer no_masuk) {
        this.no_masuk = no_masuk;
    }

    public Model_Barang getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(Model_Barang kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public Integer getJml_masuk() {
        return jml_masuk;
    }

    public void setJml_masuk(Integer jml_masuk) {
        this.jml_masuk = jml_masuk;
    }

    public Long getSubtotal_masuk() {
        return subtotal_masuk;
    }

    public void setSubtotal_masuk(Long subtotal_masuk) {
        this.subtotal_masuk = subtotal_masuk;
    }
}
