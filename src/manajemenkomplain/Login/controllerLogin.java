/*
 * 
 *  LICENSE
 * 
 */

package manajemenkomplain.Login;

import manajemenkomplain.Login.modelLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import manajemenkomplain.Login.viewLogin;

/**
 *
 * @author link_v12
 */
public class controllerLogin implements ActionListener{

    private viewLogin view;
    private databaseLogin db;

    public controllerLogin() {
        view = new viewLogin();
        db = new databaseLogin();
        view.addActionListener(this);
        view.setVisible(true);
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(view.getBtnLogin())) {
            btnLoginActionPerformed();
        }
    }

    private void btnLoginActionPerformed() {
        modelLogin login = new modelLogin (view.getTfUsername(),view.getPfPassword());
        //System.out.println(view.getTfUsername()+"   - "+view.getPfPassword());
        login = db.checkLogin(login);
        if (login != null) {
            view.showMessage("Login Berhasil", "Login", 1);
            //System.out.println("WORK");
        } else {
            view.setTfUsername("");
            view.setPfPassword("");
            view.showMessage("Invalid username / password", "Login Gagal", 0);
        }
    }
}
