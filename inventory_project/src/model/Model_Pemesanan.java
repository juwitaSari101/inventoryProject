package model;

/**
 * Model_Pemesanan merepresentasikan data pemesanan dalam sistem.
 * 
 * Atribut mencakup informasi terkait nomor pemesanan, tanggal pemesanan, 
 * total pemesanan, distributor terkait, dan pengguna yang melakukan pemesanan.
 * 
 * @author Juwita
 */
public class Model_Pemesanan {

    // Atribut
    private String no_pesan; // Nomor pemesanan
    private String tgl_pesan; // Tanggal pemesanan dalam format YYYY-MM-DD
    private Integer total_pesan; // Total nilai pemesanan
    private Model_Distributor id_distributor; // Distributor terkait pemesanan
    private Model_Pengguna id_pengguna; // Pengguna yang melakukan pemesanan

    
    
    public String getNo_pesan() {
        return no_pesan;
    }

    
    public void setNo_pesan(String no_pesan) {
        this.no_pesan = no_pesan;
    }

   
    public String getTgl_pesan() {
        return tgl_pesan;
    }

    
    public void setTgl_pesan(String tgl_pesan) {
        this.tgl_pesan = tgl_pesan;
    }

    
    public Integer getTotal_pesan() {
        return total_pesan;
    }

    
    public void setTotal_pesan(Integer total_pesan) {
        this.total_pesan = total_pesan;
    }

   
    public Model_Distributor getId_distributor() {
        return id_distributor;
    }

    
    public void setId_distributor(Model_Distributor id_distributor) {
        this.id_distributor = id_distributor;
    }

   
    public Model_Pengguna getId_pengguna() {
        return id_pengguna;
    }

   
    public void setId_pengguna(Model_Pengguna id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    // Metode tambahan (jika diperlukan) bisa ditambahkan di bawah ini.
}
