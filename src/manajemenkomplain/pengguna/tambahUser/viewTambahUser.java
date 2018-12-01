/*
 * 
 *  LICENSE
 * 
 */
package manajemenkomplain.pengguna.tambahUser;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author link
 */
public class viewTambahUser extends javax.swing.JFrame {

    /**
     * Creates new form viewTambahUser
     */
    public viewTambahUser() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTambahUser = new javax.swing.JLabel();
        lblIdUser = new javax.swing.JLabel();
        lblIdLevel = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblReEnterPassword = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        lblNoTelp = new javax.swing.JLabel();
        tfIdUser = new javax.swing.JTextField();
        cbIdLevel = new javax.swing.JComboBox<>();
        tfNama = new javax.swing.JTextField();
        pfPassword = new javax.swing.JPasswordField();
        pfReEnterPassword = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taAlamat = new javax.swing.JTextArea();
        tfNoTelp = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTambahUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTambahUser.setText("Tambah User");

        lblIdUser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblIdUser.setLabelFor(tfIdUser);
        lblIdUser.setText("idUser");

        lblIdLevel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblIdLevel.setLabelFor(cbIdLevel);
        lblIdLevel.setText("idLevel");

        lblNama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNama.setText("Nama");

        lblPassword.setText("Password");

        lblReEnterPassword.setText("Re-Password");

        lblAlamat.setText("Alamat");

        lblNoTelp.setText("No Telp");

        cbIdLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User", "Manager" }));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        taAlamat.setColumns(20);
        taAlamat.setRows(5);
        jScrollPane1.setViewportView(taAlamat);

        btnCreate.setText("Create");

        btnBack.setText("Back");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTambahUser)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblReEnterPassword)
                                .addComponent(lblNama, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblNoTelp, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblAlamat, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(lblIdUser)
                            .addComponent(lblIdLevel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbIdLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(pfReEnterPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(pfPassword, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnCreate)
                                        .addGap(80, 80, 80)
                                        .addComponent(btnBack))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tfIdUser, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                                        .addComponent(tfNama))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTambahUser)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbIdLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdLevel))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNama)
                    .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReEnterPassword)
                    .addComponent(pfReEnterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoTelp)
                    .addComponent(tfNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlamat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnBack))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(viewTambahUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewTambahUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewTambahUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewTambahUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewTambahUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreate;
    private javax.swing.JComboBox<String> cbIdLevel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblIdLevel;
    private javax.swing.JLabel lblIdUser;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNoTelp;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblReEnterPassword;
    private javax.swing.JLabel lblTambahUser;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JPasswordField pfReEnterPassword;
    private javax.swing.JTextArea taAlamat;
    private javax.swing.JTextField tfIdUser;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfNoTelp;
    // End of variables declaration//GEN-END:variables

    
    /// Setter
    public void setCbIdLevel(JComboBox<String> cbIdLevel) {
        this.cbIdLevel = cbIdLevel;
    }

    public void setPfPassword(String t) {
        this.pfPassword.setText(t);
    }

    public void setPfReEnterPassword(String t) {
        this.pfReEnterPassword.setText(t);
    }

    public void setTaAlamat(String t) {
        this.taAlamat.setText(t);
    }

    public void setTfIdUser(String t) {
        this.tfIdUser.setText(t);
    }
    
    public void setTfNama(String t) {
        this.tfNama.setText(t);
    }

    public void setTfNoTelp(String t) {
        this.tfNoTelp.setText(t);
    }
    //// Getter

    public JButton getBtnBack() {
        return btnBack;
    }

    public JButton getBtnCreate() {
        return btnCreate;
    }

    public JComboBox<String> getCbIdLevel() {
        return cbIdLevel;
    }

    public String getPfPassword() {
        return String.valueOf(pfPassword.getPassword());
    }

    public String getPfReEnterPassword() {
        return String.valueOf(pfReEnterPassword.getPassword());
    }

    public String getTaAlamat() {
        return taAlamat.getText();
    }

    public String getTfIdUser() {
        return tfIdUser.getText();
    }

    public String getTfNama() {
        return tfNama.getText();
    }

    public String getTfNoTelp() {
        return tfNoTelp.getText();
    }
    

    ////
    public void addActionListener (ActionListener x) {
        this.btnBack.addActionListener(x);
        this.btnCreate.addActionListener(x);
    }
    
    public void addItemListener (ItemListener x) {
        this.cbIdLevel.addItemListener(x);
    }

    public void showMessage(String message, String title, int type){
        JOptionPane.showMessageDialog(null, message, title, type);
    }
}
