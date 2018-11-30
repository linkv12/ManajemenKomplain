/*
 * 
 *  LICENSE
 * 
 */

package manajemenkomplain.pengguna.Setting.changePassword;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import manajemenkomplain.pengguna.Setting.viewSettingAccount;
import manajemenkomplain.pengguna.User;
import manajemenkomplain.pengguna.UserDatabase;

/**
 *
 * @author link_v12
 */
public class controllerChangePassword implements ActionListener{

    private viewChangePassword view;
    private JFrame preview;
    private User user;
    private UserDatabase udb;

    public controllerChangePassword(JFrame preview, User user) {
        this.preview = preview;
        this.user = user;
        view = new viewChangePassword();
        this.preview.setVisible(false);
        udb = new UserDatabase();
        view.addActionListener(this);
        view.setVisible(true);
        reset();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(view.getBtnBack())) {
            btnBackActionPerformed();
        } else if (source.equals(view.getBtnChangePassword())) {
            btnChangePasswordActionPerfomed();
        }
    }
    
    public void btnBackActionPerformed() {
        this.preview.setVisible(true);
        this.view.dispose();
    }
    
    public void btnChangePasswordActionPerfomed() {
        //System.out.println(view.getPfOldPassword());
        //System.out.println(view.getPfNewPassword());
        //System.out.println(view.getPfReEnterPassword());
        if (view.getPfOldPassword().isEmpty()) {
            reset();
            view.showMessage("Invalid Password", "Error", 0);
        } else if (udb.isPasswordCorrect(this.user.getIdUser(), this.user.getPassword())) {
            if (!view.getPfNewPassword().isEmpty() && !view.getPfNewPassword().equals(view.getPfReEnterPassword())) {
                view.showMessage("new Password not match", "Error", 0);
            } else if (!view.getPfNewPassword().isEmpty() && view.getPfNewPassword().equals( view.getPfReEnterPassword())) {
                udb.updatePassword(user, view.getPfNewPassword());
                this.user = this.udb.getUser(udb.getUser(), this.user.getIdUser(), this.user.getIdLevel());
                view.showMessage("Data Berhasil Diubah", "Success", 1);
                //this.view.dispose();
            } else {
                view.showMessage("Unexpected error", "Error", 0);
            }
        }
    }
    
    public void reset() {
        this.view.setPfNewPassword("");
        this.view.setPfOldPassword("");
        this.view.setPfReEnterPassword("");
    }
    
}
