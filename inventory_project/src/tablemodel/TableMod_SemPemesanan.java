package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Model_SemPemesanan;

/**
 * TableModel untuk tabel Pemesanan
 */
public class TableMod_SemPemesanan extends AbstractTableModel {

    // List untuk menyimpan data Model_SemPemesanan
    private List<Model_SemPemesanan> list = new ArrayList<>();

    // Menambah data pemesanan ke dalam list dan memberitahu tabel untuk memperbarui tampilan
    public void tambahData(Model_SemPemesanan semPemesanan) {
        list.add(semPemesanan);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }

    // Memperbarui data pemesanan pada baris tertentu dan memberitahu tabel untuk memperbarui tampilan
    public void perbaruiData(int row, Model_SemPemesanan semPemesanan) {
        list.set(row, semPemesanan);
        fireTableRowsUpdated(row, row);
    }

    // Menghapus data pemesanan pada baris tertentu dan memberitahu tabel untuk memperbarui tampilan
    public void hapusData(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    // Menghapus semua data pemesanan dan memberitahu tabel untuk memperbarui tampilan
    public void clear() {
        list.clear();
        fireTableDataChanged();
    }

    // Mengatur data list dan memberitahu tabel untuk memperbarui tampilan
    public void setData(List<Model_SemPemesanan> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }

    // Mendapatkan data Model_SemPemesanan pada index tertentu
    public Model_SemPemesanan getData(int index) {
        return list.get(index);
    }

    // Mengembalikan jumlah baris dalam tabel
    @Override
    public int getRowCount() {
        return list.size();
    }

    // Mengembalikan jumlah kolom dalam tabel
    @Override
    public int getColumnCount() {
        return 6; 
    }

    // Mengambil nilai pada cell tertentu berdasarkan baris dan kolom
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Model_SemPemesanan semPemesanan = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return semPemesanan.getMod_barang().getKode_barang(); 
            case 1:
                return semPemesanan.getMod_barang().getNama_barang();
            case 2:
                return semPemesanan.getMod_barang().getSatuan();
            case 3:
                return semPemesanan.getMod_barang().getHarga(); 
            case 4:
                return semPemesanan.getMod_detpesan().getJml_pesan();
            case 5:
                return semPemesanan.getSubtotal_pesan();
            default:
                return null;
        }
    }

    // Mengambil nama kolom berdasarkan indeks kolom
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Kode Barang";
            case 1:
                return "Nama Barang"; 
            case 2:
                return "Satuan"; 
            case 3:
                return "Harga";
            case 4:
                return "Jumlah Pesan";
            case 5:
                return "Subtotal Pesan";
            default:
                return null;
        }
    }
}
