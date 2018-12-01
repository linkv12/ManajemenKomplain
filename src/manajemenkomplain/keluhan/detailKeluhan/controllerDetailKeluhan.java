/*
 * 
 *  LICENSE
 * 
 */

package manajemenkomplain.keluhan.detailKeluhan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import manajemenkomplain.keluhan.Keluhan;
import manajemenkomplain.keluhan.KeluhanDatabase;

/**
 *
 * @author link_v12
 */
public class controllerDetailKeluhan implements ActionListener{

    private Keluhan kel;
    private viewDetailKeluhan view;
    private KeluhanDatabase kdb;
    private JFrame viewStr;
    
    public controllerDetailKeluhan (Keluhan kel,JFrame viewStr) {
        this.kel = kel;
        kdb = new KeluhanDatabase();
        view = new viewDetailKeluhan();
        view.setLblIdKeluhan(kel.getIdKeluhan());
        view.setLblIdUser(kel.getIdUser());
        view.setLblTemaKeluhan(kel.getTemaKeluhan());
        view.setLblMendesak(String.valueOf(kel.isKeluhanMendesak()));
        view.setTaDeskripsi(kel.getDeskripsi());
       
        view.setVisible(true);
        view.addActionListener(this);
        this.viewStr = viewStr;
        //this.viewStr.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(this.view.getBtnBack())) {
            //view.showMessage("Data Berhasil Diubah", "Success", 1);
            btnBackActionPerformed();
        } else if (source.equals(this.view.getBtnDelete())) {
            btnDeleteActioPerformed();
            
        }
    }

    private void btnBackActionPerformed() {
        this.viewStr.setVisible(true);
        this.view.setVisible(false);
        this.view.dispose();
    }

    private void btnDeleteActioPerformed() {
        this.kdb.delKeluhan(this.kel.getIdKeluhan());
        view.showMessage("Berhasil dihapus", "Status", 1);
        btnBackActionPerformed();
    }
    
}
