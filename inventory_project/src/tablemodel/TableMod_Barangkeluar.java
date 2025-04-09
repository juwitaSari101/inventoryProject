/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Model_Barangkeluar;

/**
 *
 * @author juwita
 */
public class TableMod_Barangkeluar extends AbstractTableModel{

    private List<Model_Barangkeluar> list = new ArrayList<>();
    
    public void tambahData(Model_Barangkeluar mod_keluar){
        list.add(mod_keluar);
        fireTableRowsInserted(list.size() - 1, list.size() -1);
        JOptionPane.showMessageDialog(null,"Data Berhasil Di Tambahkan");
    }
    
    public void perbaruiData (int row, Model_Barangkeluar mod_keluar){
        list.add(row, mod_keluar);
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
    
    public void setData(List<Model_Barangkeluar> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
            
    public void setData(int index, Model_Barangkeluar mod_keluar){
        list.set(index, mod_keluar);
        fireTableRowsUpdated(index,index);
    }
    
    public Model_Barangkeluar getData(int index){
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
        Model_Barangkeluar mod = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return mod.getTgl_keluar(); 
            case 2:
                return mod.getKode_barangkeluar();
            case 3:
                return mod.getMod_bar().getNama_barang();
            case 4:
                return mod.getMod_bar().getSatuan(); 
            case 5:
                return mod.getJumlah_keluar();
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
            case 2: return "Kode Barang Keluar";
            case 3: return "Nama Barang";
            case 4: return "Satuan";
            case 5: return "Jumlah";
            case 6: return "Jenis";

            
            default: return null;
        }
    }
    
}
