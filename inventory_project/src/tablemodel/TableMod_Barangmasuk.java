/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Model_Barangmasuk;

/**
 *
 * @author juwita
 */
public class TableMod_Barangmasuk extends AbstractTableModel{

    private List<Model_Barangmasuk> list = new ArrayList<>();
    
    public void tambahData(Model_Barangmasuk mod_masuk){
        list.add(mod_masuk);
        fireTableRowsInserted(list.size() - 1, list.size() -1);
        JOptionPane.showMessageDialog(null,"Data Berhasil Di Tambahkan");
    }
    
    public void perbaruiData (int row, Model_Barangmasuk mod_masuk){
        list.add(row, mod_masuk);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null,"Data Berhasil Di Perbaharui");
    }
    
    public void hapusData (int index){
        list.remove(index);
        fireTableRowsDeleted(index, index);
        JOptionPane.showMessageDialog(null,"Data Berhasil Di Hapus");
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<Model_Barangmasuk> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
            
    public void setData(int index, Model_Barangmasuk mod_masuk){
        list.set(index, mod_masuk);
        fireTableRowsUpdated(index,index);
    }
    
    public Model_Barangmasuk getData(int index){
        return list.get(index);
    }
    
    @Override
    public int getRowCount() {
         return list.size();
    }

    @Override
    public int getColumnCount() {
         return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Model_Barangmasuk mod = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return mod.getTgl_masuk(); 
            case 2:
                return mod.getKode_barangmasuk();
            case 3:
                return mod.getMod_bar().getNama_barang();
            case 4:
                return mod.getMod_bar().getSatuan(); 
            case 5:
                return mod.getJumlah_masuk();
            case 6:
                return mod.getMod_bar().getJenis_barang();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0: return "No";
            case 1: return "Tanggal";
            case 2: return "Kode Barang Masuk";
            case 3: return "Nama Barang";
            case 4: return "Satuan";
            case 5: return "Jumlah";
            case 6: return "Jenis";

            
            default: return null;
        }
    }
    
}

