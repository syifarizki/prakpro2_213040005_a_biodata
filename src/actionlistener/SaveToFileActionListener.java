/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionlistener;

import java.util.List;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import biodata.BiodataFrame;
import biodata.Biodata;

/**
 *
 * @author asus
 */


public class SaveToFileActionListener implements ActionListener {
  private final BiodataFrame biodataFrame;
  private final List<Biodata> biodataList; 

  public SaveToFileActionListener(BiodataFrame biodataFrame, List<Biodata> biodataList) {
    this.biodataFrame = biodataFrame;
    this.biodataList = biodataList;
  }

  public void actionPerformed(ActionEvent e) {
    int confirmation = JOptionPane.showConfirmDialog(this.biodataFrame,
            "Apakah anda yakin ingin menyimpan data ke file?",
            "Form Biodata",
            JOptionPane.YES_NO_OPTION);

    if (confirmation == JOptionPane.YES_OPTION) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan Data ke File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("File Teks", "txt"));
        int userSelection = fileChooser.showSaveDialog(this.biodataFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter writer = new FileWriter("data.txt");

                for (int i = 0; i < biodataList.size(); i++) {
                    if (i == biodataList.size() - 1) {
                        System.out.print(
                                biodataList.get(i).getNama() + "," + biodataList.get(i).getNoTelepon() + " "
                                        + biodataList.get(i).getJenisKelamin() + ","
                                        + biodataList.get(i).getAlamat());
                        writer.write(biodataList.get(i).getNama() + ",");
                        writer.write(biodataList.get(i).getNoTelepon() + ",");
                        writer.write(biodataList.get(i).getJenisKelamin() + ",");
                        writer.write(biodataList.get(i).getAlamat());
                    } else {
                        System.out.print(
                                biodataList.get(i).getNama() + "," + biodataList.get(i).getNoTelepon() + ","
                                        + biodataList.get(i).getJenisKelamin() + ","
                                        + biodataList.get(i).getAlamat() + "\n");
                        writer.write(biodataList.get(i).getNama() + ",");
                        writer.write(biodataList.get(i).getNoTelepon() + ",");
                        writer.write(biodataList.get(i).getJenisKelamin() + ",");
                        writer.write(biodataList.get(i).getAlamat() + "\n");
                    }
                }

                writer.close();

                JOptionPane.showMessageDialog(this.biodataFrame, "Data berhasil disimpan ke file",
                        "Perhatian",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
  }
}