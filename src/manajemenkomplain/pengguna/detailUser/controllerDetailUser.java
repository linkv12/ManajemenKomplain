/*
 * 
 *  LICENSE
 * 
 */

package manajemenkomplain.pengguna.detailUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import manajemenkomplain.pengguna.User;

/**
 *
 * @author link_v12
 */
public class controllerDetailUser implements ActionListener{

    private User userData;
    private viewDetailUser view;
    private JFrame viewStr;
    
    public controllerDetailUser (User k,JFrame viewStr) {
        this.userData = k;
        view = new viewDetailUser();
        init();
        view.setVisible(true);
        view.addActionListener(this);
        this.viewStr = viewStr;
        this.viewStr.setVisible(false);
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
    public void init() {
        view.setLblAlamat(userData.getAlamat());
        view.setLblIdLevel(userData.getIdLevel());
        view.setLblIdUser(userData.getIdUser());
        view.setLblNama(userData.getNamaUser());
        view.setLblNoTelp(userData.getNoTelp());
    }
    
}
