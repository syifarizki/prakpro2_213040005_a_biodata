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


public class HapusActionListener implements ActionListener {
  private final BiodataFrame biodataFrame; 
  private final BiodataDao biodataDao; 

  public HapusActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
    this.biodataFrame = biodataFrame;
    this.biodataDao = biodataDao;
  }

  public void actionPerformed(ActionEvent e) {
    int row = this.biodataFrame.getTable().getSelectedRow();

    int column = this.biodataFrame.getTable().getSelectedColumn();

    if (row == -1 || column == -1) {
      this.biodataFrame.showAlertFailed("dihapus");
      return;
    } else {
      String newValue = (String) this.biodataFrame.getTable().getModel().getValueAt(row, column);
      Biodata id = new Biodata();

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

	  id = this.biodataDao.select(col, newValue);

      int confirmation = this.biodataFrame.showConfirmation("hapus");

      if (confirmation == 1) {
        this.biodataFrame.showAlertFailed("tidak dihapus");
        return;
      } 
      else {
        this.biodataFrame.deleteBiodata(id);
        this.biodataDao.delete(id);
        this.biodataFrame.showAlertSuccess("dihapus");
      }
    }
  }
}