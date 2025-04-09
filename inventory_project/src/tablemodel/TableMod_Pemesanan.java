package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Model_Pemesanan;

/**
 *
 * @author
 */
public class TableMod_Pemesanan extends AbstractTableModel {

    private List<Model_Pemesanan> list = new ArrayList<>();

    public void tambahData(Model_Pemesanan pemesanan) {
        list.add(pemesanan);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }

    public void perbaruiData(int row, Model_Pemesanan pemesanan) {
        list.set(row, pemesanan);
        fireTableRowsUpdated(row, row);
    }

    public void hapusData(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void clear() {
        list.clear();
        fireTableDataChanged();
    }

    public void setData(List<Model_Pemesanan> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }

    public Model_Pemesanan getData(int index) {
        return list.get(index);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5; // Ada 5 kolom dalam model ini
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Model_Pemesanan pemesanan = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pemesanan.getNo_pesan();
            case 1:
                return pemesanan.getTgl_pesan();
            case 2:
                return pemesanan.getTotal_pesan();
            case 3:
                return pemesanan.getId_distributor(); 
            case 4:
                return pemesanan.getId_pengguna(); // Nama pengguna
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
                return "Tanggal Pesan";
            case 2:
                return "Total Pesanan";
            case 3:
                return "ID Distributor";
            case 4:
                return "ID Pengguna";
            default:
                return null;
        }
    }
}
