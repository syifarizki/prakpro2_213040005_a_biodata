/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biodata;

import javax.swing.table.*;
import java.util.List;

/**
 *
 * @author asus
 */

public class ModelTable extends AbstractTableModel {
    private String[] columnNames = { "Nama", "Nomor HP", "Jenis Kelamin", "Alamat" };
    private List<Biodata> data;

    public ModelTable(List<Biodata> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Biodata rowItem = data.get(row);
        String value = "";

        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;
            case 1:
                value = rowItem.getNoTelepon();
                break;
            case 2:
                value = rowItem.getJenisKelamin();
                break;
            case 3:
                value = rowItem.getAlamat();
                break;
        }

        return value;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Biodata value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void update(Biodata value) {
        int i = 0;

        for (Biodata b : data) {
            if (b.getId().equals(value.getId())) {
                b = value;
                data.set(i, value);
                fireTableCellUpdated(data.size() - 1,
                        data.size() - 1);
            }
            i++;
        }
    }

    public void delete(Biodata value) {
        int i = 0;

        for (Biodata b : data) {
            if (b.getId().equals(value.getId())) {
                data.remove(i);
                break;
            }
            i++;
        }
        
        fireTableRowsDeleted(data.size() - 1,
                data.size() - 1);
    }
}