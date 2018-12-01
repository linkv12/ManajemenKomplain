/*
 * 
 *  LICENSE
 * 
 */

package manajemenkomplain.pengguna.manajer;

import manajemenkomplain.pengguna.admin.*;
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
import manajemenkomplain.keluhan.progress.Progres;
import manajemenkomplain.keluhan.progress.ProgresDatabase;
import manajemenkomplain.keluhan.suratTugas.SuratTugas;
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
public class controllerManajer extends MouseAdapter implements ActionListener,ItemListener{

    private viewManajer view;
    private User userData;
    private UserDatabase udb;
    private KeluhanDatabase kdb;
    private JFrame loginFrame;
    private String searchColumn = "";
    private ProgresDatabase pdb;
    private SuratTugas sdb;
    
    public controllerManajer(User userData,JFrame loginFr) {
        this.userData = userData;
        System.out.println(this.userData.getIdLevel());
        //System.out.println(userData.getIdUser());
        view = new viewManajer();
        this.loginFrame = loginFr;
        this.loginFrame.setVisible(false);
        //System.out.println("frame works well");
        udb = new UserDatabase();
        kdb = new KeluhanDatabase();
        pdb = new ProgresDatabase();
        
        view.addActionListener(this);
        view.addMouseAdapter(this);
        view.addItemListener(this);
        reset();
        
        view.setVisible(true);
        this.loadTable();
        //System.out.println("try to load table........");

        //System.out.println("done load table........");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
        if (source.equals(view.getCbCategory())) {
            cbCategoryItemSelected();
        }
    }

    private void cbCategoryItemSelected() {
        this.searchColumn = String.valueOf(view.getCbCategory().getSelectedItem());
        System.out.println(String.valueOf(view.getCbCategory().getSelectedItem()));
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
        } else if (source.equals(view.getBtnViewUser())) {
            btnViewUserActionPerformed();
        } else if(source.equals(view.getBtnTambahKeluhan())) {
            btnTambahKeluhanActionPerformed();
        } else if (source.equals(view.getBtnCari())) {
            btnCariActionPerformed();
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
                                "temaKeluhan", "deskripsi","keluhanMendesak","idSuratTugas","Status"}, 0);
        //view.getTblResult() = new JTable(); 
        //model.setRowCount(0);
        ArrayList<Keluhan> keluhan = kdb.getKeluhan();
        //ArrayList<Progres> progres = pdb.getProgres();
        for (Keluhan k : keluhan) {
            Progres p = pdb.getProgres(k.getIdKeluhan());
            if (p == null) {
                break;
            }
            model.addRow(new Object[]{k.getIdKeluhan(), k.getIdUser(),
                            k.getTemaKeluhan(), k.getDeskripsi(), k.isKeluhanMendesak(),p.getIdSuratTugas(),
                            p.getStatus()});
        }
        view.setTblResult(model);
    }
    
    public void loadTable(String term){
        //System.out.println("it runn");
        DefaultTableModel model = new DefaultTableModel(new String[]{"idKeluhan", "idUser",
                                "temaKeluhan", "deskripsi","keluhanMendesak"}, 0);
        //view.getTblResult() = new JTable(); 
        //model.setRowCount(0);
        System.out.println(term);
        ArrayList<Keluhan> keluhan = kdb.getKeluhan();
        for (Keluhan k : keluhan) {
            //idKeluhan, idUser, Mendesak
            System.out.println("k is mendesak "+k.isKeluhanMendesak());
            if (this.searchColumn.equals("idUser")){
                if (k.getIdUser().equals(term)) {
                    Progres p = pdb.getProgres(k.getIdKeluhan());
            if (    p == null) {
                break;
            }
            model.addRow(new Object[]{k.getIdKeluhan(), k.getIdUser(),
                            k.getTemaKeluhan(), k.getDeskripsi(), k.isKeluhanMendesak(),p.getIdSuratTugas(),
                            p.getStatus()});
                }
            } else if (this.searchColumn.equals("idKeluhan")) {
                if (k.getIdKeluhan().equals(term)) {
                    Progres p = pdb.getProgres(k.getIdKeluhan());
                    if (p == null) {
                        break;
                    }
                        model.addRow(new Object[]{k.getIdKeluhan(), k.getIdUser(),
                            k.getTemaKeluhan(), k.getDeskripsi(), k.isKeluhanMendesak(),p.getIdSuratTugas(),
                            p.getStatus()});
                }
                
            } else if (this.searchColumn.equals("Mendesak")) {
                if (k.isKeluhanMendesak()) {
                   Progres p = pdb.getProgres(k.getIdKeluhan());
            if (   p == null) {
                break;
            }
            model.addRow(new Object[]{k.getIdKeluhan(), k.getIdUser(),
                            k.getTemaKeluhan(), k.getDeskripsi(), k.isKeluhanMendesak(),p.getIdSuratTugas(),
                            p.getStatus()});
                }
                
            }
        }
        view.setTblResult(model);
    }   
    
    @Override
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
        this.view.setLblUserType("Manajer");
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

    private void btnCariActionPerformed() {
        if (this.searchColumn.equals("")) {
            view.showMessage("Invalid column", "error", 0);
        } else if (view.getTfSearchFilter().equals("")) {
            view.showMessage("invalid search term", "error", 0);
        } else {
            this.loadTable(view.getTfSearchFilter());
            //this.btnRefreshActionPerformed();
        }
    }
}
