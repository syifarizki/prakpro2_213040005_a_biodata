/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionlistener;

import java.awt.event.*;

import biodata.Biodata;
import biodata.BiodataFrame;
import dao.BiodataDao;

/**
 *
 * @author asus
 */

public class SimpanUbahActionListener implements ActionListener {
    private final BiodataFrame biodataFrame;
    private final BiodataDao biodataDao; 
    private String id; 

    public SimpanUbahActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao, String id) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
        this.id = id;
    }

    public void actionPerformed(ActionEvent e) {
        String jenisKelamin = "";

        if (biodataFrame.getJenisLaki().isSelected()) {
            jenisKelamin = biodataFrame.getJenisLaki().getText();
            biodataFrame.getJenisLaki().setSelected(false);
        }
        else if (biodataFrame.getJenisPerempuan().isSelected()) {
            jenisKelamin = biodataFrame.getJenisPerempuan().getText();
            biodataFrame.getJenisPerempuan().setSelected(false);
        }

        String nama = this.biodataFrame.getNamaTextField().getText();
        String telepon = this.biodataFrame.getNoTeleponTextField().getText();
        String alamat = this.biodataFrame.getAlamatTextField().getText();

        if (nama.equalsIgnoreCase("") && telepon.equalsIgnoreCase("") && alamat.equalsIgnoreCase("")) {
            this.biodataFrame.showAlertAllEmpty();
            return;
        } else {
            if (nama.equalsIgnoreCase("")) {
                this.biodataFrame.showAlertNameEmpty();
                return;
            }
            if (telepon.equalsIgnoreCase("")) {
                this.biodataFrame.showAlertTelephoneEmpty();
                return;
            }
            if (alamat.equalsIgnoreCase("")) {
                this.biodataFrame.showAlertAddressEmpty();
                return;
            }
        }

        int confirmation = this.biodataFrame.showConfirmation("ubah");

        if (confirmation == 0) {
            Biodata biodata = new Biodata();

            biodata.setId(this.id);
            biodata.setNama(nama);
            biodata.setNoTelepon(telepon);
            biodata.setJenisKelamin(jenisKelamin);
            biodata.setAlamat(alamat);

            this.biodataFrame.updateBiodata(biodata);
            this.biodataDao.update(biodata);

            this.biodataFrame.showAlertSuccess("diubah");

            this.id = null;
        }
        else {
            this.biodataFrame.showAlertFailed("diubah");
        }

        this.biodataFrame.getNamaTextField().setText("");
        this.biodataFrame.getNoTeleponTextField().setText("");
        this.biodataFrame.getAlamatTextField().setText("");

        this.biodataFrame.getButtonSimpanUbah().removeActionListener(this);
    }
}