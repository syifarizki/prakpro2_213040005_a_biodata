/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionlistener;

import java.awt.event.*;
import java.util.UUID;

import biodata.Biodata;
import biodata.BiodataFrame;
import dao.BiodataDao;

/**
 *
 * @author asus
 */

public class SimpanActionListener implements ActionListener {
    private final BiodataFrame biodataFrame; 
    private final BiodataDao biodataDao;

    public SimpanActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    public void actionPerformed(ActionEvent e) {
        String jenisKelamin = "";

        if (biodataFrame.getJenisLaki().isSelected()) {
            jenisKelamin = biodataFrame.getJenisLaki().getText();
            biodataFrame.getJenisLaki().setSelected(false);
        }
        if (biodataFrame.getJenisPerempuan().isSelected()) {
            jenisKelamin = biodataFrame.getJenisPerempuan().getText();
            biodataFrame.getJenisPerempuan().setSelected(false);
        }

        String nama = biodataFrame.getNama();
        String telepon = biodataFrame.getNoTelepon();
        String alamat = biodataFrame.getAlamat();

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

        int confirmation = this.biodataFrame.showConfirmation("tambah");

        if (confirmation == 0) {
            Biodata biodata = new Biodata();

            biodata.setId(UUID.randomUUID().toString());
            biodata.setNama(nama);
            biodata.setNoTelepon(telepon);
            biodata.setJenisKelamin(jenisKelamin);
            biodata.setAlamat(alamat);

            this.biodataFrame.addBiodata(biodata);
            this.biodataDao.insert(biodata);

            this.biodataFrame.showAlertSuccess("ditambahkan");
        }
        else {
            this.biodataFrame.showAlertFailed("ditambahkan");
        }
        this.biodataFrame.getNamaTextField().setText("");
        this.biodataFrame.getNoTeleponTextField().setText("");
        this.biodataFrame.getAlamatTextField().setText("");
    }
}