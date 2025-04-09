package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Model_Pengguna;

public class TableMod_Pengguna extends AbstractTableModel {

    private List<Model_Pengguna> list = new ArrayList<>();

    // Menambahkan data ke tabel
    public void tambahData(Model_Pengguna pengguna) {
        list.add(pengguna);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data Pengguna Berhasil Ditambahkan");
    }

    public void perbaruiData(int row, Model_Pengguna pengguna) {
        list.set(row, pengguna);
        fireTableRowsUpdated(row, row);
        JOptionPane.showMessageDialog(null, "Data Pengguna Berhasil Diperbarui");
    }

    public void hapusData(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
        JOptionPane.showMessageDialog(null, "Data Pengguna Berhasil Dihapus");
    }

    // Menghapus semua data dalam tabel
    public void clear() {
        list.clear();
        fireTableDataChanged();
    }

    // Mengatur data tabel dengan list pengguna baru
    public void setData(List<Model_Pengguna> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }

    // Mengambil data pengguna pada indeks tertentu
    public Model_Pengguna getData(int index) {
        return list.get(index);
    }

    @Override
    public int getRowCount() {
        return list.size(); // Menghitung jumlah baris dalam tabel
    }

    @Override
    public int getColumnCount() {
        return 7; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Model_Pengguna pengguna = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pengguna.getId_pengguna();
            case 1:
                return pengguna.getNama_pengguna();
            case 2:
                return pengguna.getUsername();
            case 3:
                return pengguna.getPassword();
            case 4:
                return pengguna.getTelp_pengguna();
            case 5:
                return pengguna.getAlamat_pengguna();
            case 6:
                return pengguna.getLevel();
            
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID Pengguna";
            case 1:
                return "Nama Pengguna";
            case 2:
                return "Username";  
            case 3:
                return "Password";
            case 4:
                return "Telepon";
            case 5:
                return "Alamat";
            case 6:
                return "Level";
            
            default:
                return null;
        }
    }
}
