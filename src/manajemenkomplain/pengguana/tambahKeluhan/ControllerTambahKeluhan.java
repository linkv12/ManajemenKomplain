/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manajemenkomplain.pengguana.tambahKeluhan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import manajemenkomplain.keluhan.Keluhan;
import manajemenkomplain.keluhan.KeluhanDatabase;
import manajemenkomplain.keluhan.progress.Progres;
import manajemenkomplain.keluhan.progress.ProgresDatabase;
import manajemenkomplain.pengguna.User;
import manajemenkomplain.pengguna.UserDatabase;

/**
 *
 * @author Affan
 */
public class ControllerTambahKeluhan implements ActionListener,ItemListener{

    private ViewTambahKeluhan view;
    private User userData;
    private UserDatabase udb;
    private KeluhanDatabase kdb;
    private Keluhan keluhan;
    private JFrame preview;
    private boolean isMendesak;
    private String tempTheme = "";
    private ProgresDatabase pdb;
    private Progres progress;

    public ControllerTambahKeluhan(JFrame preview, User userData) {
        this.userData = userData;
        this.preview = preview;
        view = new ViewTambahKeluhan();
        udb = new UserDatabase();
        kdb = new KeluhanDatabase();
        this.preview.setVisible(false);
        view.addActionListener(this);
        view.addItemListener(this);
        view.setVisible(true);
        view.setCkMendesak(false);
        isMendesak = view.getCkMendesak().isSelected();
        //view.setjLabelIdKeluhan("99");
        //view.setjLabelIdUser(this.userData.getIdUser());
        init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(view.getBtnBack())) {
            btnBackActionPerformed();
        } else if (source.equals(view.getBtnTambah())) {
            btnTambahActionPerformed();
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
        if (source.equals(view.getCkMendesak())) {
            isMendesak = view.getCkMendesak().isSelected();
        } else if (source.equals(view.getCbTemaKeluhan())) {
            /*
             Kabel Putus
             Bandwidh Down
             Internet Mati
             Koneksi Tidak Stabil
             */
            tempTheme = String.valueOf(view.getCbTemaKeluhan().getSelectedItem());
            //view.showMessage(tempTheme, tempTheme, 0);
        }
    }
    private void btnBackActionPerformed() {
        this.preview.setVisible(true);
        this.view.dispose();
    }

    private void btnTambahActionPerformed() {
        if (this.tempTheme.isEmpty() || this.view.getTextDeskrips().isEmpty()) {
            view.showMessage("Invalid input", "Error", 0);
        } else {
            if (isDescriptionNOTValid(this.view.getTextDeskrips()) 
                    && this.view.getTextDeskrips().length() > 256  ) {
                view.setTextDeskrips("");
                view.showMessage("Invalid Description", "Error", 0);
            } else {
                keluhan = new Keluhan();
                this.keluhan.setIdKeluhan(view.getjLabelIdKeluhan());
                this.keluhan.setIdUser(view.getjLabelIdUser());
                this.keluhan.setKeluhanMendesak(isMendesak);
                this.keluhan.setDeskripsi(view.getTextDeskrips());
                this.keluhan.setTemaKeluhan(tempTheme);
                kdb.addKeluhan(keluhan);
                init();
                resetCk();
                view.setTextDeskrips("");
                view.showMessage("Data Berhasil di input", "Status", 1);
                this.btnBackActionPerformed();
            }
        }
    }
    
    private void init () {
        view.setjLabelIdKeluhan(kdb.getMaxIdKeluhan());
        view.setjLabelIdUser(this.userData.getIdUser());
    }
    
    private void resetCk() {
        view.setCkMendesak(false);
    }

    private boolean isDescriptionNOTValid(String t) {
        //System.out.println((t.contains("'") || t.contains("`")));
        return (t.contains("'") || t.contains("`"));
    }

}
