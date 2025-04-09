/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Model_Pemesanan;

/**
 *
 * @author juwita
 */
public interface Service_Pemesanan {

    void tambahData(Model_Pemesanan mod_pesan);
    void perbaruiData(Model_Pemesanan mod_pesan);
    void hapusData(Model_Pemesanan mod_pesana);

    Model_Pemesanan getByid(String id);

    List<Model_Pemesanan> getData();

    List<Model_Pemesanan> pencarian(String id);

    String nomor();
}
