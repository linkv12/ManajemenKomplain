/*
 * 
 *  LICENSE
 * 
 */

package manajemenkomplain.pengguna.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import manajemenkomplain.keluhan.Keluhan;
//import manajemenkomplain.Login.viewLogin;
import manajemenkomplain.keluhan.KeluhanDatabase;
import manajemenkomplain.keluhan.detailKeluhan.controllerDetailKeluhan;
import manajemenkomplain.pengguana.tambahKeluhan.ControllerTambahKeluhan;
import manajemenkomplain.pengguna.Setting.controllerSettingAccount;
import manajemenkomplain.pengguna.ShowAllUser.controllerShowAllUser;
import manajemenkomplain.pengguna.User;
import manajemenkomplain.pengguna.UserDatabase;
import manajemenkomplain.pengguna.tambahUser.controllerTambahUser;

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
        System.out.println(this.userData.getIdLevel());
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
        this.loadTable();
        view.setVisible(true);
        //System.out.println("try to load table........");

        //System.out.println("done load table........");
    }


    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(view.getBtnLogOut())) {
            btnLogoutActionPerformed();
        } else if (source.equals(view.getBtnSetting())) {
            view.setVisible(true);
            //view.stop();
            btnSettingActionPerformed(userData);
            //reset();
        } else if (source.equals(view.getBtnRefresh())) {
            btnRefreshActionPerformed();
            //reset();
        } else if (source.equals(view.getBtnTambahKeluhan())) {
            btnTambahKeluhanActionPerformed();
        } else if (source.equals(view.getBtnViewUser())) {
            btnViewUserActionPerformed();
        } else if (source.equals(view.getBtnTambahUser())) {
            btnTambahUserActionPerformed();
        }
        //System.out.println("IT run after win closed");
        //reset();
    }

    public void emptyTable (JTable t) {
        while (t.getModel().getRowCount()>0)
          {
             //t.getModel().e
          }
    }
    
    
    public void loadTable(){
        //System.out.println("it runn");
        DefaultTableModel model = new DefaultTableModel(new String[]{"idKeluhan", "idUser",
                                "temaKeluhan", "deskripsi","keluhanMendesak"}, 0);
        //view.getTblResult() = new JTable(); 
        //model.setRowCount(0);
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
        //this.userData = udb.getUser(this.userData.getIdUser());
        //System.out.println("Setting clossed");
    }
    
    public void updateUserData() {
        this.userData = this.udb.getUser(this.userData.getIdUser());
        //System.out.println("get name : "+ this.userData.getNamaUser());
    }
    
    public void reset() {
        updateUserData();
        changeDetail();
    }

    private void btnTambahKeluhanActionPerformed() {
        new ControllerTambahKeluhan(this.view,this.userData);
    }

    
    private void btnRefreshActionPerformed() {
        reset();
        kdb.loadKeluhan();
        loadTable();
    }

    private void btnViewUserActionPerformed() {
        new controllerShowAllUser(this.userData, view);
    }

    private void btnTambahUserActionPerformed() {
        new controllerTambahUser(view);
    }
}
