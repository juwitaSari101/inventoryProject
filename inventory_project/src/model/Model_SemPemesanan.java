package model;

public class Model_SemPemesanan {

    private String no_pesan;
    private Long subtotal_pesan;
    private Model_Barang mod_barang;
    private Model_DetPemesanan mod_detpesan;

    public String getNo_pesan() {
        return no_pesan;
    }

    public Long getSubtotal_pesan() {
        return subtotal_pesan;
    }

    public void setSubtotal_pesan(Long subtotal_pesan) {
        this.subtotal_pesan = subtotal_pesan;
    }
    
    public Model_Barang getMod_barang() {
        return mod_barang;
    }

    public void setMod_barang(Model_Barang mod_barang) {
        this.mod_barang = mod_barang;
    }

    public Model_DetPemesanan getMod_detpesan() {
        return mod_detpesan;
    }

    public void setMod_detpesan(Model_DetPemesanan mod_detpesan) {
        this.mod_detpesan = mod_detpesan;
    }

}
