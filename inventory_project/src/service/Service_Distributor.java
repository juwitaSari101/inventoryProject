/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Model_Distributor;

/**
 *
 * @author juwita
 */
public interface Service_Distributor {
    void tambahData (Model_Distributor mod_distributor);    
    void perbaruiData (Model_Distributor mod_distributor);
    void hapusData (Model_Distributor mod_distributor);
    
    Model_Distributor getByid (String id);
    
    List<Model_Distributor>getData();
    List<Model_Distributor>pencarian(String id);
    
    String nomor();
}
