/*
 * 
 *  LICENSE
 * 
 */

package manajemenkomplain.pengguna.Setting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import manajemenkomplain.pengguna.User;

/**
 *
 * @author link_v12
 */
public class controllerSettingAccount implements ActionListener{

    private viewSettingAccount view;
    private JFrame preview;
    private User userData;

    public controllerSettingAccount(JFrame preview, User userData) {
        this.preview = preview;
        this.preview.setVisible(false);
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
            back();
        } else if (source.equals(view.getBtnChangePassword())) {
            //changePassword(view,userData);
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
        this.view.setTfNoTelp(String.valueOf(userData.getNoTelp()));
    }
    
    public void back() {
        this.preview.setVisible(true);
        this.view.dispose();
    }
    
   
}
