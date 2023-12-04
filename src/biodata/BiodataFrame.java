/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biodata;

import javax.swing.*;
import java.util.*;
import dao.BiodataDao;
import actionlistener.HapusActionListener;
import actionlistener.SimpanActionListener;
import actionlistener.UbahActionListener;
import actionlistener.CloseWindowActionListener;
import actionlistener.SaveToFileActionListener;

/**
 *
 * @author asus
 */
public class BiodataFrame extends JFrame {
    private List<Biodata> biodataList; 
    private final JTextField textFieldNama; 
    private final JTextField textFieldHP; 
    private final JRadioButton jenisLaki; 
    private final JRadioButton jenisPerempuan; 
    private final JTextArea txtOutput; 
    private final ModelTable tableModel; 
    private final JTable table; 
    private final JButton buttonSimpanUbah; 
    private final BiodataDao biodataDao; 

    public BiodataFrame(BiodataDao biodataDao) {
        
        this.biodataDao = biodataDao;
        this.biodataList = this.biodataDao.findAll();
        
        JLabel labelHeader = new JLabel("Form Biodata", JLabel.CENTER);
        labelHeader.setBounds(0, 20, 350, 10);

        JLabel labelNama = new JLabel("Nama: ");
        labelNama.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelHP = new JLabel("Nomor HP: ");
        labelHP.setBounds(15, 100, 350, 10);

        textFieldHP = new JTextField();
        textFieldHP.setBounds(15, 120, 350, 30);

        JLabel labelRadio = new JLabel("Jenis Kelamin:");
        labelRadio.setBounds(15, 160, 350, 10);

        jenisLaki = new JRadioButton("Laki-Laki", true);
        jenisLaki.setBounds(15, 180, 350, 30);

        jenisPerempuan = new JRadioButton("Perempuan");
        jenisPerempuan.setBounds(15, 210, 350, 30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(jenisLaki);
        bg.add(jenisPerempuan);

        JLabel labelAlamat = new JLabel("Alamat: ");
        labelAlamat.setBounds(15, 240, 350, 30);

        txtOutput = new JTextArea("");
        txtOutput.setBounds(15, 270, 350, 100);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 380, 100, 40);

        JButton buttonUbah = new JButton("Ubah");
        buttonUbah.setBounds(125, 380, 100, 40);

        JButton buttonHapus = new JButton("Hapus");
        buttonHapus.setBounds(235, 380, 100, 40);

        buttonSimpanUbah = new JButton("Simpan Ubah");
        buttonSimpanUbah.setBounds(345, 380, 150, 40);

        JButton refresh = new JButton("Refresh");
        refresh.setBounds(15, 650, 100, 40);

        refresh.addActionListener(e -> {
            refreshBiodataTable();
        });

        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15,
                440,
                500,
                200);

        tableModel = new ModelTable(biodataList);
        table.setModel(tableModel);

        JButton buttonFile = new JButton("Simpan ke File");
        buttonFile.setBounds(345, 650, 150, 40);

        SimpanActionListener simpanListener = new SimpanActionListener(this,
        biodataDao);
        button.addActionListener(simpanListener);

        UbahActionListener ubahListener = new UbahActionListener(this,
                biodataDao);
        buttonUbah.addActionListener(ubahListener);

        HapusActionListener hapusListener = new HapusActionListener(this,
                biodataDao);
        buttonHapus.addActionListener(hapusListener);

        SaveToFileActionListener saveToFileListener = new SaveToFileActionListener(this, biodataList);
        buttonFile.addActionListener(saveToFileListener);

        CloseWindowActionListener closeWindowListener = new CloseWindowActionListener(this);
        this.addWindowListener(closeWindowListener);

        this.add(labelHeader);
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelHP);
        this.add(textFieldHP);
        this.add(labelRadio);
        this.add(jenisLaki);
        this.add(jenisPerempuan);
        this.add(labelAlamat);
        this.add(txtOutput);
        this.add(button);
        this.add(buttonUbah);
        this.add(buttonHapus);
        this.add(buttonFile);
        this.add(scrollableTable);
        this.add(refresh);
        this.add(buttonSimpanUbah);

        this.setSize(550, 1000);
        this.setLayout(null);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public JTextField getNamaTextField() {
        return textFieldNama;
    }

    public String getNoTelepon() {
        return textFieldHP.getText();
    }

    public JTextField getNoTeleponTextField() {
        return textFieldHP;
    }

    public JRadioButton getJenisLaki() {
        return jenisLaki;
    }

    public JRadioButton getJenisPerempuan() {
        return jenisPerempuan;
    }

    public String getAlamat() {
        return txtOutput.getText();
    }

    public JTextArea getAlamatTextField() {
        return txtOutput;
    }

    public ModelTable getTableModel() {
        return this.tableModel;
    }

    public JTable getTable() {
        return this.table;
    }

    public JButton getButtonSimpanUbah() {
        return this.buttonSimpanUbah;
    }

    public void addBiodata(Biodata biodata) {
        tableModel.add(biodata);
        textFieldNama.setText("");
        textFieldHP.setText("");
        txtOutput.setText("");
    }

    public void updateBiodata(Biodata biodata) {
        tableModel.update(biodata);
        textFieldNama.setText("");
        textFieldHP.setText("");
        txtOutput.setText("");
    }

    public void deleteBiodata(Biodata biodata) {
        tableModel.delete(biodata);
    }

    public void refreshBiodataTable() {
        this.biodataList = this.biodataDao.findAll();
        this.getTable().setModel(new ModelTable(this.biodataList));

        System.out.println("Table refreshed: ");
        if (biodataList.isEmpty()) {
            System.out.println("Table is empty");
        } else {
            for (Biodata biodata : biodataList) {
                System.out.println(biodata.getNama() + " " + biodata.getNoTelepon() + " " + biodata.getJenisKelamin() + " " + biodata.getAlamat());
            }
        }
        System.out.println();
    }

    public void showAlertAllEmpty() {
        JOptionPane.showMessageDialog(BiodataFrame.this, "Nama, telepon dan alamat belum terisi", "Perhatian",
                JOptionPane.WARNING_MESSAGE);
    }

    public void showAlertNameEmpty() {
        JOptionPane.showMessageDialog(BiodataFrame.this, "Nama belum terisi", "Perhatian",
                JOptionPane.WARNING_MESSAGE);
    }

    public void showAlertTelephoneEmpty() {
        JOptionPane.showMessageDialog(BiodataFrame.this, "Telepon belum terisi", "Perhatian",
                JOptionPane.WARNING_MESSAGE);
    }

    public void showAlertAddressEmpty() {
        JOptionPane.showMessageDialog(BiodataFrame.this, "Alamat belum terisi", "Perhatian",
                JOptionPane.WARNING_MESSAGE);
    }

    public void showAlertSuccess(String message) {
        JOptionPane.showMessageDialog(BiodataFrame.this, "Data " + message, "Perhatian",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void showAlertFailed(String message) {
        JOptionPane.showMessageDialog(BiodataFrame.this, "Data " + message, "Perhatian",
                JOptionPane.ERROR_MESSAGE);
    }

    public int showConfirmation(String message) {
        return JOptionPane.showConfirmDialog(BiodataFrame.this,
                "Apakah anda yakin ingin " + message + " data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
    }

}
