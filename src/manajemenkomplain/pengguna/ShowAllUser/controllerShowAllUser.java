/*
 * 
 *  LICENSE
 * 
 */

package manajemenkomplain.pengguna.ShowAllUser;

//import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import manajemenkomplain.pengguna.User;
import manajemenkomplain.pengguna.UserDatabase;
import manajemenkomplain.pengguna.detailUser.controllerDetailUser;

/**
 *
 * @author link_v12
 */
public class controllerShowAllUser extends MouseAdapter implements ActionListener{

    private viewShowAllUser view;
    private User userData;
    private UserDatabase udb;
    private JFrame prev;
    
    public controllerShowAllUser(User userData,JFrame prv) {
        this.userData = userData;
        this.prev = prv;
        udb = new UserDatabase ();
        view = new viewShowAllUser();
        this.prev.setVisible(false);
        view.setVisible(true);
        init();
        view.addActionListener(this);
        view.addMouseAdapter(this);
    }


    
    
    @Override
    public void actionPerformed(ActionEvent e) { 
        Object source = e.getSource();
        if (source.equals(view.getBtnBack())) {
            btnBackActionPerformed();
        }
    }

    
    
    public void loadTable(){
        //System.out.println("it runn");
        DefaultTableModel model = new DefaultTableModel(new String[]{"idUser", "idLevel",
                                "nama", "alamat","noTelp"}, 0);
        //view.getTblResult() = new JTable(); 
        //model.setRowCount(0);
        //System.out.println("Load all user in view showalls ddsuaeae");
        ArrayList<User> usr = udb.getUser();
        //for (User k : usr) {
                //k = udb.getUser(k.getIdUser());
        //            System.out.println(k.getIdUser()+"---+"+k.getIdLevel()+"---+"+
       //              k.getNamaUser()+"---+"+k.getAlamat()+"---+"+k.getNoTelp()+k.getPassword());
           
        //}
        //System.out.println("ID level  : " + usr.get(0).getIdLevel());
        //System.out.println("id user : " + usr.get(0).getIdUser());
        for (User k : usr) {
                //k = udb.getUser(k.getIdUser());
                //System.out.println("in sho usr " + k.getIdLevel());
                model.addRow(new Object[]{k.getIdUser(), k.getIdLevel(),
                     k.getNamaUser(), k.getAlamat(), k.getNoTelp()});    
            
           
        }
        view.setTblListUser(model);
    }
    
    
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        if (source.equals(view.getTblListUser())){
            int i = view.getSelectedUser();
            //view.showMessage("Somthing selected", "some shiew", 1);
            User usr = new User();
            
            //DefaultTableModel model = new DefaultTableModel(new String[]{"idUser", "idLevel",
            //                  "nama", "alamat","noTelp"}, 0);
            //
            //usr.set(view.getTblListUser().getModel().getValueAt(i, 0).toString());
            usr.setIdUser(view.getTblListUser().getModel().getValueAt(i, 0).toString());
            usr.setIdLevel(view.getTblListUser().getModel().getValueAt(i, 1).toString());
            usr.setNamaUser(view.getTblListUser().getModel().getValueAt(i, 2).toString());
            usr.setAlamat(view.getTblListUser().getModel().getValueAt(i, 3).toString());
            usr.setNoTelp(view.getTblListUser().getModel().getValueAt(i, 4).toString());
            //System.out.println("Hei it work");
            this.view.setVisible(false);
            new controllerDetailUser(usr,this.view);
        }
    }
        
    public void changeDetail () {
        this.view.setLblNama(this.userData.getNamaUser());
        if (userData.getIdLevel().equalsIgnoreCase("adm")) {
             this.view.setLblUserType("Admin");
        }
    }
    
    public void btnBackActionPerformed() {
        this.prev.setVisible(true);
        this.view.dispose();
    }

    private void init() {
        changeDetail();
        loadTable();
    }

}


    
