/*
 * 
 *  LICENSE
 * 
 */

package manajemenkomplain.pengguna.tambahUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import manajemenkomplain.pengguna.User;
import manajemenkomplain.pengguna.UserDatabase;

/**
 *
 * @author link_v12
 */
public class controllerTambahUser implements ActionListener,ItemListener{

    private viewTambahUser view;
    private User userData;
    private UserDatabase udb;
    private JFrame preview;
    private String emptyStr = "";
    private String idlvl;

    public controllerTambahUser(JFrame preview) {
        //this.userData = userData;
        this.preview = preview;
        this.preview.setVisible(false);
        view = new viewTambahUser();
        udb = new UserDatabase();
        view.addActionListener(this);
        view.addItemListener(this);
        view.setVisible(true);
    }
    

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(view.getBtnBack())) {
            btnBackActionPerformed();
        } else if (source.equals(view.getBtnCreate())) {
            btnCreateActionPerformed();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
        if (source.equals(view.getCbIdLevel())) {
            cbIdLevelItemSelected();
        }
    }

    private void btnBackActionPerformed() {
        this.preview.setVisible(true);
        this.view.dispose();
    }

    private void btnCreateActionPerformed() {
        if (!isAllLegal()) {
            view.showMessage("Something invalid", "Error", 0);
        } else {
            this.userData = new User();
            this.userData.setAlamat(view.getTaAlamat());
            this.userData.setIdUser(view.getTfIdUser());
            this.userData.setNamaUser(view.getTfNama());
            this.userData.setNoTelp(view.getTfNoTelp());
            this.userData.setPassword(view.getPfPassword());
            this.userData.setIdLevel(idlvl);
            udb.addUser(userData);
            view.showMessage("Data berhasil ditambah", "Success", 1);
            this.btnBackActionPerformed();
        }
    }
    
    //// Set empty all
    private void setAll () {
        view.setTaAlamat(emptyStr);
        view.setTfIdUser(emptyStr);
        view.setTfNama(emptyStr);
        view.setTfNoTelp(emptyStr);
        view.setPfPassword(emptyStr);
        view.setPfReEnterPassword(emptyStr);
    }
    
    
    /// Checking legality
    private boolean isAllLegal () {
        System.out.println("Password legal : "+isPasswordLegal());
        System.out.println("Alamat legal : "+isAlamatLegal());
        System.out.println("idUser Legal  : "+ isIdUserLegal());
        System.out.println("nama legal : "+isNamaLegal());
        System.out.println("notelp legal : "+isNoTelpLegal());
        return (isPasswordLegal() && this.isAlamatLegal()
                && this.isIdUserLegal() && this.isNamaLegal()
                && this.isNoTelpLegal());
    }
    
    
    private boolean isPasswordLegal () {
        if (view.getPfPassword().equals("") 
            || view.getPfReEnterPassword().equals("") ) {
            view.setPfPassword(emptyStr);
            view.setPfReEnterPassword(emptyStr);
            return false;
        } else if (view.getPfPassword().equals(view.getPfReEnterPassword())) {
            if (view.getPfPassword().contains("'") || view.getPfPassword().contains("`")
              || view.getPfReEnterPassword().contains("'") || view.getPfReEnterPassword().contains("`")) {
                view.setPfPassword(emptyStr);
                view.setPfReEnterPassword(emptyStr);
                return false;
            } else {
                if (view.getPfPassword().length() <= 12) {
                    return true;
                } else {
                    view.setPfPassword(emptyStr);
                    view.setPfReEnterPassword(emptyStr);
                    return false;
            }
            }
        } else {
            view.setPfPassword(emptyStr);
            view.setPfReEnterPassword(emptyStr);
            return false;
        }
    }
    
    private boolean isIdUserLegal() {
        if (view.getTfIdUser().isEmpty()) {
            view.setTfIdUser(emptyStr);
            return false;
        } else if (view.getTfIdUser().contains("'") || view.getTfIdUser().contains("`")){
            view.setTfIdUser(emptyStr);
            return false;
        } else {
            if (view.getTfIdUser().length() > 6) {
                view.setTfIdUser(emptyStr);
                return false; 
            } else {
                if (this.udb.cekDuplikatIdUser(view.getTfIdUser())) {
                    view.setTfIdUser(emptyStr);
                    return false;
                } else {
                    return true;
                }
            }
        }
    }
    
    private boolean isNamaLegal () {
        if(view.getTfNama().isEmpty()) {
            view.setTfNama(emptyStr);
            return false;
        } else {
            if (view.getTfNama().contains("'") || view.getTfNama().contains("`")) {
                view.setTfNama(emptyStr);
                return false;
            } else {
                if (view.getTfNama().length() <= 20) {
                    return true;
                } else {
                    view.setTfNama(emptyStr);
                    return false;
                }
            }
        }
    }
    
    private boolean isAlamatLegal () {
        if(view.getTaAlamat().isEmpty()) {
            view.setTaAlamat(emptyStr);
            return false;
        } else {
            if (view.getTaAlamat().contains("'") || view.getTaAlamat().contains("`")) {
                view.setTaAlamat(emptyStr);
                return false;
            } else {
                if (view.getTaAlamat().length() <= 256) {
                    return true;
                } else {
                    view.setTaAlamat(emptyStr);
                    return false;
                }
            }
        }
    }
    
    private boolean isNoTelpLegal () {
        if(view.getTfNoTelp().isEmpty()) {
            view.setTfNoTelp(emptyStr);
            return false;
        } else {
            if (view.getTfNoTelp().contains("'") || view.getTfNoTelp().contains("`")) {
                view.setTfNoTelp(emptyStr);
                return false;
            } else {
                if (view.getTfNoTelp().length() <= 12) {
                    return true;
                } else {
                    view.setTfNoTelp(emptyStr);
                    return false;
                }
            }
        }
    }

    private void cbIdLevelItemSelected() {
        idlvl = String.valueOf(view.getCbIdLevel().getSelectedItem());
        //Admin, User, Teknisi, Manager
        if (idlvl.equals("Admin")) {
            idlvl = "adm";
        } else if (idlvl.equals("User")) {
           idlvl = "usr";
        } else if (idlvl.equals("Teknisi")) {
            idlvl = "tkn";
        } else if (idlvl.equals("Manager")) {
            idlvl = "mgr";
        }
    }
    
    
    
    
}
