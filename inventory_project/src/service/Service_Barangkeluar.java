/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Model_Barangkeluar;

/**
 *
 * @author juwita
 */
public interface Service_Barangkeluar {

    void tambahData(Model_Barangkeluar mod_keluar);

    void perbaruiData(Model_Barangkeluar mod_keluar);

    void hapusData(Model_Barangkeluar mod_keluar);

    Model_Barangkeluar getByid(String id);

    List<Model_Barangkeluar> getData();

    List<Model_Barangkeluar> pencarian(String id);

    String nomor();

}
