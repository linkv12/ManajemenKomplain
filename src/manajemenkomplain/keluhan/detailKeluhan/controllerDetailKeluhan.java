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

/**
 *
 * @author link_v12
 */
public class controllerDetailKeluhan implements ActionListener{

    private Keluhan kel;
    private viewDetailKeluhan view;
    private JFrame viewStr;
    
    public controllerDetailKeluhan (Keluhan k,JFrame viewStr) {
        kel = k;
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
            this.viewStr.setVisible(true);
            this.view.setVisible(false);
            this.view.dispose();
        }
        
    }
    
}
