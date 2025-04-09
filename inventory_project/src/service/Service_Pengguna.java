package service;

import java.util.List;
import model.Model_Pengguna;

public interface Service_Pengguna {
    void tambahData(Model_Pengguna mod_pengguna);
    void perbaruiData(Model_Pengguna mod_pengguna);
    void hapusData(Model_Pengguna mod_pengguna);
    Model_Pengguna getByid(String id);
    List<Model_Pengguna> getData();
    List<Model_Pengguna> pencarian(String id);
    String nomor();
}
