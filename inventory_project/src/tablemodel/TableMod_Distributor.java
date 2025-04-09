package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Model_Distributor;

/**
 *
 * @author 
 */
public class TableMod_Distributor extends AbstractTableModel {

    private List<Model_Distributor> list = new ArrayList<>();

    public void tambahData(Model_Distributor distributor) {
        list.add(distributor);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data Distributor Berhasil Ditambahkan");
    }

    public void perbaruiData(int row, Model_Distributor distributor) {
        list.set(row, distributor);
        fireTableRowsUpdated(row, row);
        JOptionPane.showMessageDialog(null, "Data Distributor Berhasil Diperbarui");
    }

    public void hapusData(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
        JOptionPane.showMessageDialog(null, "Data Distributor Berhasil Dihapus");
    }

    public void clear() {
        list.clear();
        fireTableDataChanged();
    }

    public void setData(List<Model_Distributor> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }

    public void setData(int index, Model_Distributor distributor) {
        list.set(index, distributor);
        fireTableRowsUpdated(index, index);
    }

    public Model_Distributor getData(int index) {
        return list.get(index);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4; // Ada 4 kolom
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getId_distributor();
            case 1:
                return list.get(rowIndex).getNama_distributor();
            case 2:
                return list.get(rowIndex).getAlamat_distributor();
            case 3:
                return list.get(rowIndex).getTelp_distributor();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID Distributor";
            case 1:
                return "Nama Distributor";
            case 2:
                return "Alamat Distributor";
            case 3:
                return "Telepon Distributor";
            default:
                return null;
        }
    }
}
