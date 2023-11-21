package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ubah_password extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    Statement stat;
    ResultSet rs;
    PreparedStatement ps;
    String sql,sqli;
    public ubah_password() {
        initComponents();
        placeholder();
        setLocationRelativeTo(getRootPane());
        Locale lokasi = new Locale("id","ID");
        Locale.setDefault(lokasi);
        icon_eyes.setVisible(true);
        icon_hideeyes.setVisible(false);
    }
    
    public void placeholder(){
        addPlaceholderStyle(txtusername);
        addPlaceholderStyle(password_field);
    }
    
    public void addPlaceholderStyle(JTextField textField){
    Font font = textField.getFont();
    textField.setFont(font);
    textField.setForeground(Color.gray);
    }
    
    public void resetPlaceholderStyle(JTextField textField){
    Font font = textField.getFont();
    textField.setFont(font);
    textField.setForeground(Color.black);
    }
    
    //coding update
    private void update(){
        int OK = JOptionPane.showConfirmDialog(null, "Ubah Password ?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        String user, pass;
            user =  txtusername.getText();
            pass = password_field.getText();
            String sql = "SELECT * FROM login WHERE username = '" + txtusername.getText() + "'"; 
        if(OK==0){
            String sqli = "update login set password='"+pass+"' where username='"+user+"'";
         try {
            PreparedStatement lanjut = conn.prepareStatement(sqli);
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
                if(hasil.next()){
                    if(hasil.getString("username").equals(user)){
                        txtusername.requestFocus();
                        lanjut.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Password Berhasil Diubah");
                    }
                    else{
                        JOptionPane.showMessageDialog(this,"Username Hampir Benar");
                    }
                }
                else{
                    
                     JOptionPane.showMessageDialog(this,"Username Salah");
                     password_field.setText("");
                }
            }catch (Exception e){
            }
        }   
        }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlgambar = new javax.swing.JPanel();
        lblsistem_keputusan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnllogin = new javax.swing.JPanel();
        lbllogin = new javax.swing.JLabel();
        lblusername = new javax.swing.JLabel();
        lblpassword = new javax.swing.JLabel();
        lblingin_login = new javax.swing.JLabel();
        btnubah = new Desain.Button();
        txtusername = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        password_field = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        icon_eyes = new javax.swing.JLabel();
        icon_hideeyes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ubah Password");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        pnlgambar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblsistem_keputusan.setForeground(new java.awt.Color(255, 255, 255));
        lblsistem_keputusan.setText("Sistem Keputusan Karyawan Terbaik");
        pnlgambar.add(lblsistem_keputusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/professional-executive-manager-briefing-her-colleg-Y79YMQG.png"))); // NOI18N
        pnlgambar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 346, -1));

        pnllogin.setBackground(new java.awt.Color(255, 255, 255));
        pnllogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbllogin.setFont(new java.awt.Font("Century Gothic", 1, 34)); // NOI18N
        lbllogin.setForeground(new java.awt.Color(102, 102, 102));
        lbllogin.setText("Ubah Password");
        pnllogin.add(lbllogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        lblusername.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblusername.setForeground(new java.awt.Color(102, 102, 102));
        lblusername.setText("Username");
        pnllogin.add(lblusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        lblpassword.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblpassword.setForeground(new java.awt.Color(102, 102, 102));
        lblpassword.setText("Password");
        pnllogin.add(lblpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        lblingin_login.setBackground(new java.awt.Color(29, 162, 253));
        lblingin_login.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblingin_login.setForeground(new java.awt.Color(29, 162, 253));
        lblingin_login.setText("Ingin Login ?");
        lblingin_login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblingin_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblingin_loginMouseClicked(evt);
            }
        });
        pnllogin.add(lblingin_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 320, -1, -1));

        btnubah.setBackground(new java.awt.Color(103, 103, 103));
        btnubah.setForeground(new java.awt.Color(255, 255, 255));
        btnubah.setText("Ubah Password");
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });
        pnllogin.add(btnubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 356, 223, 52));

        txtusername.setText("Username");
        txtusername.setBorder(null);
        txtusername.setPreferredSize(new java.awt.Dimension(147, 37));
        txtusername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtusernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtusernameFocusLost(evt);
            }
        });
        pnllogin.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 214, -1));
        pnllogin.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 220, 10));

        password_field.setText("Password");
        password_field.setBorder(null);
        password_field.setPreferredSize(new java.awt.Dimension(147, 37));
        password_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                password_fieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                password_fieldFocusLost(evt);
            }
        });
        pnllogin.add(password_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 160, -1));
        pnllogin.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 214, 10));

        icon_eyes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eyes.png"))); // NOI18N
        icon_eyes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_eyesMouseClicked(evt);
            }
        });
        pnllogin.add(icon_eyes, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 270, -1, -1));

        icon_hideeyes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/hide eyes.png"))); // NOI18N
        icon_hideeyes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_hideeyesMouseClicked(evt);
            }
        });
        pnllogin.add(icon_hideeyes, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 270, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlgambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnllogin, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlgambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnllogin, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblingin_loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblingin_loginMouseClicked
        this.setVisible(false);
        Login rp = new Login();
        rp.setVisible(true);
    }//GEN-LAST:event_lblingin_loginMouseClicked

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
        update();
        password_field.setText("");
  
    }//GEN-LAST:event_btnubahActionPerformed

    private void icon_eyesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_eyesMouseClicked
        icon_hideeyes.setVisible(true);
        icon_eyes.setVisible(false);
        password_field.setEchoChar((char)0);
    }//GEN-LAST:event_icon_eyesMouseClicked

    private void icon_hideeyesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_hideeyesMouseClicked
        icon_eyes.setVisible(true);
        icon_hideeyes.setVisible(false);
        password_field.setEchoChar('*');
    }//GEN-LAST:event_icon_hideeyesMouseClicked

    private void txtusernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtusernameFocusGained
         if(txtusername.getText().equals("Username"))
        {
            txtusername.setText(null);
            txtusername.requestFocus();
            resetPlaceholderStyle(txtusername);
        }
    }//GEN-LAST:event_txtusernameFocusGained

    private void txtusernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtusernameFocusLost
        if(txtusername.getText().length()==0)
        {
            addPlaceholderStyle(txtusername);
            txtusername.setText("Username");
        }
    }//GEN-LAST:event_txtusernameFocusLost

    private void password_fieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password_fieldFocusGained
          if(password_field.getText().equals("Password"))
        {
            password_field.setText(null);
            password_field.requestFocus();
            resetPlaceholderStyle(password_field);
        }
    }//GEN-LAST:event_password_fieldFocusGained

    private void password_fieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password_fieldFocusLost
        if(password_field.getText().length()==0)
        {
            addPlaceholderStyle(password_field);
            password_field.setText("Password");
        }
    }//GEN-LAST:event_password_fieldFocusLost

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
            java.util.logging.Logger.getLogger(ubah_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ubah_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ubah_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ubah_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ubah_password().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Desain.Button btnubah;
    private javax.swing.JLabel icon_eyes;
    private javax.swing.JLabel icon_hideeyes;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblingin_login;
    private javax.swing.JLabel lbllogin;
    private javax.swing.JLabel lblpassword;
    private javax.swing.JLabel lblsistem_keputusan;
    private javax.swing.JLabel lblusername;
    private javax.swing.JPasswordField password_field;
    private javax.swing.JPanel pnlgambar;
    private javax.swing.JPanel pnllogin;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
