/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author juwita
 */
public class Model_DetBarangKeluar {
    private Integer no_keluar;
    private Model_Barang kodeBarang;
    private Integer jml_keluar;
    private Long subtotal_keluar;

    public Integer getNo_keluar() {
        return no_keluar;
    }

    public void setNo_keluar(Integer no_keluar) {
        this.no_keluar = no_keluar;
    }

    public Model_Barang getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(Model_Barang kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public Integer getJml_keluar() {
        return jml_keluar;
    }

    public void setJml_keluar(Integer jml_keluar) {
        this.jml_keluar = jml_keluar;
    }

    public Long getSubtotal_keluar() {
        return subtotal_keluar;
    }

    public void setSubtotal_keluar(Long subtotal_keluar) {
        this.subtotal_keluar = subtotal_keluar;
    }
}
