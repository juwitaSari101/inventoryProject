/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Model_Barangmasuk;

/**
 *
 * @author juwita
 */
public interface Service_Barangmasuk {
    void tambahData(Model_Barangmasuk mod_masuk);
    void perbaruiData (Model_Barangmasuk mod_masuk);
    void hapusData (Model_Barangmasuk mod_masuk);
    
    Model_Barangmasuk getByid (String id);
    List<Model_Barangmasuk> getData();
    List<Model_Barangmasuk> pencarian(String id);
    String nomor();
    
}
