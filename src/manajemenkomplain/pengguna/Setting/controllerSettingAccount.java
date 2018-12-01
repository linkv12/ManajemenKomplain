/*
 * 
 *  LICENSE
 * 
 */

package manajemenkomplain.pengguna.Setting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import manajemenkomplain.pengguna.Setting.changePassword.controllerChangePassword;
import manajemenkomplain.pengguna.User;
import manajemenkomplain.pengguna.UserDatabase;

/**
 *
 * @author link_v12
 */
public class controllerSettingAccount implements ActionListener{

    private viewSettingAccount view;
    private JFrame preview;
    private User userData;
    private UserDatabase udb;

    public controllerSettingAccount(JFrame preview, User userData) {
        this.preview = preview;
        this.preview.setVisible(false);
        udb = new UserDatabase();
        view = new viewSettingAccount();
        //System.out.println(userData.getIdUser());
        this.userData = userData;
        //System.out.println(this.userData.getIdUser() + "daskdasndj");
        setData(this.userData);
        //System.out.println("data init");
        this.view.addActionListener(this);
        this.view.setVisible(true);
        
    }

    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(view.getBtnBack())) {
            btnBackActionPerformed();
            //updateViewData();
        } else if (source.equals(view.getBtnChangePassword())) {
            btnChangePasswordActionPerformed(view,userData);
            //System.out.println("This should not executed");
            //updateViewData();
        }  else if (source.equals(view.getBtnChangeUserData())) {
            btnChangeUserData();
            //System.out.println("WORK HERE");
            //updateViewData();
        }
    }
    
    public void setData (User userData) {
        //System.out.println(userData.getIdLevel());
        //this.view.setLblType(userData.getIdLevel());
        //this.view.setLblIdUserContent(userData.getIdUser());
        //this.view.setTfAlamat(userData.getAlamat());
        //this.view.setTfNama(userData.getNamaUser());
        //this.view.setTfNoTelp(String.valueOf(userData.getNoTelp()));
        
        
        this.view.setLblType(userData.getIdLevel());
        this.view.setLblIdUserContent(userData.getIdUser());
        this.view.setTfAlamat(userData.getAlamat());
        this.view.setTfNama(userData.getNamaUser());
        this.view.setTfNoTelp(userData.getNoTelp());
    }
    
    public void btnBackActionPerformed() {
        this.preview.setVisible(true);
        this.view.dispose();
    }
    
    public void btnChangePasswordActionPerformed(JFrame view,User user) {
        this.view.setVisible(false);
        new controllerChangePassword(view,user);
        //System.out.println("it runnnnn");
    }

    private void btnChangeUserData() {
        if (!isTfLegal()) {
           view.showMessage("Invalid data", "Error", 0);
           this.setData(userData);
        } else if (isTfLegal() && isUserLegal(new User(this.userData.getIdUser()
            ,this.userData.getIdLevel()
            ,view.getTfNama(), this.userData.getPassword()
            ,view.getTfAlamat(),view.getTfNoTelp()))){
            //System.out.println(view.getTfNama());
            User newData = new User(this.userData.getIdUser(),this.userData.getIdLevel()
                        ,view.getTfNama(),this.userData.getPassword(),view.getTfAlamat()
                        ,view.getTfNoTelp());
            //public User(String idUser, String idLevel, String namaUser, String password, String alamat, String noTelp) 
            newData.setIdUser(this.userData.getIdUser());
            newData.setIdLevel(this.userData.getIdLevel());
            //System.out.println("newData idUser " + newData.getIdUser());
            //System.out.println("newData idLevel " + newData.getIdLevel());
            //System.out.println("newData name " + newData.getNamaUser());
            //System.out.println("newData pass " + newData.getPassword());
            //System.out.println("newData alamat " + newData.getAlamat());
            //System.out.println("newData noTelp " + newData.getNoTelp());
            udb.updateUser(newData);
            view.showMessage("Data changed succesfully", "Succes", 1);
        } else {
            view.showMessage("unexpected", "logic err", 0);
        }
    }
   
    public void updateViewData () {
        this.userData = this.udb.getUser(this.userData.getIdUser());
        //System.out.println(this.userData.getNamaUser());
        this.setData(userData);
    }
    
    public boolean isUserLegal (User u) {
        return (u.getIdUser().length() <=6 
                && u.getIdLevel().length() <= 6
                && u.getPassword().length() <= 12
                && u.getNamaUser().length() <= 6
                && u.getAlamat().length() <= 256
                && u.getNoTelp().length() <= 12);
    }
    
    public boolean isTfLegal () {
        return ((view.getTfNama().length() <= 20 && !view.getTfAlamat().equals(""))
              && (view.getTfNoTelp().length() <= 12 && !view.getTfNoTelp().equals(""))
              && (view.getTfAlamat().length() <= 256 && !view.getTfAlamat().equals("")));
    }
}