package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Model_DetPemesanan;

/**
 *
 * @author 
 */
public class TableMod_DetPemesanan extends AbstractTableModel {

    private List<Model_DetPemesanan> list = new ArrayList<>();

    public void tambahData(Model_DetPemesanan mod_pesan) {
        list.add(mod_pesan);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }

    public void perbaruiData(int row, Model_DetPemesanan mod_pesan) {
        list.set(row, mod_pesan);
        fireTableRowsUpdated(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }

    public void hapusData(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }

    public void clear() {
        list.clear();
        fireTableDataChanged();
    }

    public void setData(List<Model_DetPemesanan> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }

    public void setData(int index, Model_DetPemesanan mod_pesan) {
        list.set(index, mod_pesan);
        fireTableRowsUpdated(index, index);
    }

    public Model_DetPemesanan getData(int index) {
        return list.get(index);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    
    @Override
public int getColumnCount() {
    return 8; // Ada 7 kolom sesuai permintaan
}

@Override
public Object getValueAt(int rowIndex, int columnIndex) {
    Model_DetPemesanan detPemesanan = list.get(rowIndex); // Ambil objek Model_DetPemesanan

    switch (columnIndex) {
        case 0:
            return detPemesanan.getMod_pesan().getNo_pesan(); // No Pemesanan
        case 1:
            return detPemesanan.getMod_barang().getKode_barang(); // Kode Barang dari Model_Barang
        case 2:
            return detPemesanan.getMod_barang().getNama_barang();
        case 3:
            return detPemesanan.getMod_barang().getSatuan();
        case 4:
            return detPemesanan.getMod_barang().getHarga(); // Harga Barang dari Model_Barang
        case 5:
            return detPemesanan.getJml_pesan(); // Jumlah Pesan
        case 6:
            return detPemesanan.getSubtotal_pesan(); // Subtotal Pemesanan
        case 7:
            return detPemesanan.getStatus(); // Keterangan (Status Pemesanan)
        default:
            return null;
    }
}

@Override
public String getColumnName(int column) {
    switch (column) {
        case 0:
            return "No Pesan";
        case 1:
            return "Kode Barang";
        case 2:
            return "Nama Barang";
        case 3:
            return "Satuan";
        case 4:
            return "Harga";
        case 5:
            return "Jumlah Pesan";
        case 6:
            return "Subtotal";
        case 7:
            return "Keterangan";
        default:
            return null;
    }
}

}
