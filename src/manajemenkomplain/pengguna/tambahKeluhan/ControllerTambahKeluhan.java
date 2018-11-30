/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manajemenkomplain.pengguna.tambahKeluhan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import manajemenkomplain.keluhan.Keluhan;
import manajemenkomplain.pengguna.User;
import manajemenkomplain.pengguna.UserDatabase;

/**
 *
 * @author Affan
 */
public class ControllerTambahKeluhan implements ActionListener {

    private ViewTambahKeluhan view;
    private User userData;
    private UserDatabase udb;
    private Keluhan keluhan;
    private JFrame preview;

    public ControllerTambahKeluhan(JFrame preview, User userData) {
        this.userData = userData;
        this.preview = preview;
        view = new ViewTambahKeluhan();
        this.preview.setVisible(false);
        view.addActionListener(this);
        view.setVisible(true);
        view.setjLabelIdKeluhan("99");
        view.setjLabelIdUser(this.userData.getIdUser());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(view.getBtnBack())) {
            btnBackActionPerformed();
        } else if (source.equals(view.getBtnTambah())) {
            /* koding */
        }

        }
        
    private void btnBackActionPerformed() {
        this.preview.setVisible(true);
        this.view.dispose();
    }

}
