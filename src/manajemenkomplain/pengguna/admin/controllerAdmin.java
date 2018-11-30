/*
 * 
 *  LICENSE
 * 
 */

package manajemenkomplain.pengguna.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import manajemenkomplain.keluhan.Keluhan;
//import manajemenkomplain.Login.viewLogin;
import manajemenkomplain.keluhan.KeluhanDatabase;
import manajemenkomplain.keluhan.detailKeluhan.controllerDetailKeluhan;
import manajemenkomplain.pengguna.Setting.controllerSettingAccount;
import manajemenkomplain.pengguna.User;
import manajemenkomplain.pengguna.UserDatabase;

/**
 *
 * @author link_v12
 */
public class controllerAdmin extends MouseAdapter implements ActionListener{

    private viewAdmin view;
    private User userData;
    private UserDatabase udb;
    private KeluhanDatabase kdb;
    private JFrame loginFrame;
    
    public controllerAdmin(User userData,JFrame loginFr) {
        this.userData = userData;
        //System.out.println(userData.getIdUser());
        view = new viewAdmin();
        this.loginFrame = loginFr;
        this.loginFrame.setVisible(false);
        //System.out.println("frame works well");
        udb = new UserDatabase();
        kdb = new KeluhanDatabase();
        view.addActionListener(this);
        view.addMouseAdapter(this);
        reset();
        view.setVisible(true);
        //System.out.println("try to load table........");
        loadTable();
        //System.out.println("done load table........");
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(view.getBtnLogOut())) {
            btnLogoutActionPerformed();
        } else if (source.equals(view.getBtnSetting())) {
            btnSettingActionPerformed(userData);
        }
    }

    public void loadTable(){
        DefaultTableModel model = new DefaultTableModel(new String[]{"idKeluhan", "idUser",
                                "temaKeluhan", "deskripsi","keluhanMendesak"}, 0);
        ArrayList<Keluhan> keluhan = kdb.getKeluhan();
        for (Keluhan k : keluhan) {
            model.addRow(new Object[]{k.getIdKeluhan(), k.getIdUser(),
                            k.getTemaKeluhan(), k.getDeskripsi(), k.isKeluhanMendesak()});
        }
        view.setTblResult(model);
    }
    
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        if (source.equals(view.getTblResult())){
            int i = view.getSelectedKeluhan();
            
            Keluhan keluhan = new Keluhan();
            keluhan.setIdKeluhan(view.getTblResult().getModel().getValueAt(i, 0).toString());
            keluhan.setIdUser(view.getTblResult().getModel().getValueAt(i, 1).toString());
            keluhan.setTemaKeluhan(view.getTblResult().getModel().getValueAt(i, 2).toString());
            keluhan.setDeskripsi(view.getTblResult().getModel().getValueAt(i, 3).toString());
            keluhan.setKeluhanMendesak(Boolean.valueOf(view.getTblResult().getModel().getValueAt(i, 4).toString()));
            //System.out.println("Hei it work");
            this.view.setVisible(false);
            new controllerDetailKeluhan(keluhan,this.view);
        }
    }
        
    public void changeDetail () {
        this.view.setLblNama(this.userData.getNamaUser());
        this.view.setLblUserType("Admin");
    }
    
    public void btnLogoutActionPerformed() {
        this.loginFrame.setVisible(true);
        this.view.dispose();
    }
    
    public void btnSettingActionPerformed (User ud) {
        //this.view.setVisible(false);
        new controllerSettingAccount(view,ud);
        this.userData = udb.getUser(this.userData.getIdUser());
        loadTable();
        changeDetail();
    }
    
    public void reset() {
        //loadTable();
        view.setLblNama(this.userData.getNamaUser());
        changeDetail();
    }
}
