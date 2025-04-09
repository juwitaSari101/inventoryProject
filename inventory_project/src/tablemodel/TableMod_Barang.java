package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Model_Barang;

/**
 *
 * @author juwita
 */
public class TableMod_Barang extends AbstractTableModel {

    private List<Model_Barang> list = new ArrayList<>();

    // Menambahkan data baru ke tabel
    public void tambahData(Model_Barang mod_barang) {
        list.add(mod_barang);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }

    // Memperbarui data pada baris tertentu
    public void perbaruiData(int row, Model_Barang mod_barang) {
        list.set(row, mod_barang);
        fireTableRowsUpdated(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }

    // Menghapus data pada indeks tertentu
    public void hapusData(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }

    // Menghapus semua data dalam tabel
    public void clear() {
        list.clear();
        fireTableDataChanged();
    }

    // Menyetel data tabel secara keseluruhan
    public void setData(List<Model_Barang> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }

    // Menyetel data pada indeks tertentu
    public void setData(int index, Model_Barang mod_barang) {
        list.set(index, mod_barang);
        fireTableRowsUpdated(index, index);
    }

    // Mengambil data dari baris tertentu
    public Model_Barang getData(int index) {
        return list.get(index);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 7; // Total kolom (termasuk kolom jenis barang)
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex + 1; // Kolom nomor
            case 1:
                return list.get(rowIndex).getKode_barang();
            case 2:
                return list.get(rowIndex).getNama_barang();
            case 3:
                return list.get(rowIndex).getSatuan();
            case 4:
                return list.get(rowIndex).getHarga();
            case 5:
                return list.get(rowIndex).getStok();
            case 6:
                return list.get(rowIndex).getJenis_barang(); // Kolom jenis barang
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "No."; // Kolom nomor
            case 1:
                return "Kode Barang";
            case 2:
                return "Nama Barang";
            case 3:
                return "Satuan";
            case 4:
                return "Harga";
            case 5:
                return "Stok";
            case 6:
                return "Jenis Barang"; // Nama kolom jenis barang
            default:
                return null;
        }
    }
}
