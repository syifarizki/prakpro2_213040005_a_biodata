/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionlistener;

import java.awt.event.*;

import biodata.BiodataFrame;
import dao.BiodataDao;

/**
 *
 * @author asus
 */

public class UbahActionListener implements ActionListener {
    private final BiodataFrame biodataFrame; 
    private final BiodataDao biodataDao;

    public UbahActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    public void actionPerformed(ActionEvent e) {
        int row = this.biodataFrame.getTable().getSelectedRow();

        int column = this.biodataFrame.getTable().getSelectedColumn();

        String biodataUbah = (String) this.biodataFrame.getTable().getModel().getValueAt(row, column);

        String id = "";

        String col = "";

        switch (column) {
            case 0:
                col = "nama";
                break;
            case 1:
                col = "no_telepon";
                break;
            case 2:
                col = "jenis_kelamin";
                break;
            case 3:
                col = "alamat";
                break;
            default:
                System.out.println("Kolom tidak ditemukan");
                break;
        }

        id = this.biodataDao.select(col, biodataUbah).getId();
        
        this.biodataFrame.getNamaTextField().setText(this.biodataDao.select(col, biodataUbah).getNama());
        this.biodataFrame.getNoTeleponTextField().setText(this.biodataDao.select(col, biodataUbah).getNoTelepon());

        if (this.biodataDao.select(col, biodataUbah).getJenisKelamin().equals("Laki-Laki")) {
            this.biodataFrame.getJenisLaki().setSelected(true);
        } else {
            this.biodataFrame.getJenisPerempuan().setSelected(true);
        }

        this.biodataFrame.getAlamatTextField().setText(this.biodataDao.select(col, biodataUbah).getAlamat());

        SimpanUbahActionListener simpanUbahListener = new SimpanUbahActionListener(
                this.biodataFrame,
                this.biodataDao,
                id);
        
        this.biodataFrame.getButtonSimpanUbah().addActionListener(simpanUbahListener);
    }
}