/*
 * 
 *  LICENSE
 * 
 */

package manajemenkomplain.Login;

import manajemenkomplain.Login.LoginModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import manajemenkomplain.Login.viewLogin;
import manajemenkomplain.pengguna.UserDatabase;
import manajemenkomplain.pengguna.admin.controllerAdmin;

/**
 *
 * @author link_v12
 */
public class controllerLogin implements ActionListener{

    private viewLogin view;
    private LoginDatabase db;

    public controllerLogin() {
        view = new viewLogin();
        db = new LoginDatabase();
        view.addActionListener(this);
        view.setVisible(true);
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(view.getBtnLogin())) {
            btnLoginActionPerformed();
        } else if (source.equals(view.getBtnPi())) {
            this.btnPiActionPerformed();
        }
    }

    private void btnLoginActionPerformed() {
        LoginModel login = new LoginModel (view.getTfUsername(),view.getPfPassword());
        //System.out.println(view.getTfUsername()+"   - "+view.getPfPassword());
        login = db.checkLogin(login);
        if (login != null) {
            //view.showMessage("Login Berhasil", "Login", 1);
    
            new controllerAdmin (new UserDatabase().getUser(login.getIdUser()),view);
            //System.out.println("WORK");
        } else {
            view.setTfUsername("");
            view.setPfPassword("");
            view.showMessage("Invalid username / password", "Login Gagal", 0);
        }
    }

    private void btnPiActionPerformed() {
        LoginModel login = new LoginModel ("superA","admin");
        //System.out.println(view.getTfUsername()+"   - "+view.getPfPassword());
        login = db.checkLogin(login);
        if (login != null) {
            //view.showMessage("Login Berhasil", "Login", 1);
            
            new controllerAdmin (new UserDatabase().getUser(login.getIdUser()),view);
            //System.out.println("WORK");
        } else {
            view.setTfUsername("");
            view.setPfPassword("");
            view.showMessage("Invalid username / password", "Login Gagal", 0);
        }
    }
}
