package Cari_Data;
import java.awt.Color;
import javax.swing.JScrollBar;
import Desain.ScrollBarCustom;
import com.sun.glass.events.KeyEvent;
import java.sql.*;
import java.awt.*;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;
import koneksi.koneksi;
/*import static main.dahshbore.lbltotal_karyawan;
import static main.penilaian.tblpenilaian;
import static main.perengkingan.tbl_normalisasi;
import static main.perengkingan.tbl_peringkat;*/
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import main.penilaian;

public class cari_karyawan extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect(); 
    DefaultTableModel tabmode;
    public penilaian data_penilaian = null;
    
    public cari_karyawan() {
        initComponents();
        datatabel();
        costumtabel();
    }
    
    private void costumtabel(){
        tblkaryawan.fixTable(jScrollPane2);
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
        ScrollBarCustom sp = new ScrollBarCustom();
        sp.setOrientation(JScrollBar.HORIZONTAL);
        jScrollPane2.setHorizontalScrollBar(sp);
        tblkaryawan.fixTable(jScrollPane2);
    }

    protected void datatabel(){              
        Object[] Baris = {"NIK Karyawan","Nama Karyawan","Alamat","Jenis Kelamin","Jabatan"};
        tabmode = new DefaultTableModel(null, Baris);
        tblkaryawan.setModel(tabmode);
        String sql = "select  karyawan.nik, karyawan.nama, karyawan.alamat, karyawan.jenis_kelamin, "
                + "jabatan.jabatan from karyawan INNER JOIN jabatan ON karyawan.nik=jabatan.nik ";
           /* {
             int row=tabmode.getRowCount();
             for (int i=0;i<row;i++){tabmode.removeRow(0);}
            } */
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nik");
                String b = hasil.getString("nama");
                String c = hasil.getString("alamat");
                String d = hasil.getString("jenis_kelamin");
                String e = hasil.getString("jabatan");
                String[] data = {a,b,c,d,e};
                tabmode.addRow(data);
            }
        }catch (Exception e){
        }
        //int b = tabmode.getRowCount();
        //lbltotal.setText(""+b);    //label total karyawan yg diform karyawan,total data didatbel dipanggil ke label total
        //lbltotal_karyawan.setText(""+b); //label total karyawan yg dimenu utama,total data didata tabel dipanggil ke label total
    }   
    
    protected void caridata(String key){
        Object[] Baris = {"NIK Karyawan","Nama Karyawan","Alamat","Jenis Kelamin","Jabatan"};
        tabmode = new DefaultTableModel(null, Baris);
        tblkaryawan.setModel(tabmode);
        String sql = "select  karyawan.nik, karyawan.nama, karyawan.alamat, karyawan.jenis_kelamin, "
                + "jabatan.jabatan from karyawan INNER JOIN jabatan ON karyawan.nik=jabatan.nik where nama like '%"+txtcari.getText()+"%' "
                 + "or alamat like '%"+txtcari.getText()+"%' or jenis_kelamin like '%"+txtcari.getText()+"%' "
                 + "or jabatan like '%"+txtcari.getText()+"%'";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nik");
                String b = hasil.getString("nama");
                String c = hasil.getString("alamat");
                String d = hasil.getString("jenis_kelamin");
                String e = hasil.getString("jabatan");
                String[] data = {a,b,c,d,e};
                tabmode.addRow(data);
            }
        }catch (Exception e){
        }
     }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        roundPanel1 = new Desain.RoundPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblkaryawan = new table.Table();
        rpsearch = new Desain.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(804, 281));

        jPanel1.setBackground(new java.awt.Color(248, 248, 255));

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setRound(20);

        tblkaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblkaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkaryawanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblkaryawan);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        rpsearch.setBackground(new java.awt.Color(255, 255, 255));
        rpsearch.setPreferredSize(new java.awt.Dimension(739, 50));
        rpsearch.setRound(50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_search_30px.png"))); // NOI18N

        txtcari.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtcari.setForeground(new java.awt.Color(153, 153, 153));
        txtcari.setText("Cari Data Karyawan");
        txtcari.setBorder(null);
        txtcari.setCaretColor(new java.awt.Color(102, 102, 0));
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout rpsearchLayout = new javax.swing.GroupLayout(rpsearch);
        rpsearch.setLayout(rpsearchLayout);
        rpsearchLayout.setHorizontalGroup(
            rpsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpsearchLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        rpsearchLayout.setVerticalGroup(
            rpsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpsearchLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(rpsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(rpsearch, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(rpsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        String key=txtcari.getText();
        System.out.println(key);
        if(key!=""){
            caridata(key);
        }else{
            datatabel();
        }
    }//GEN-LAST:event_txtcariKeyReleased

    private void tblkaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkaryawanMouseClicked
       int bar = tblkaryawan.getSelectedRow();
        data_penilaian.nik_karyawan = tblkaryawan.getValueAt( bar, 0).toString();
        data_penilaian.nama_karyawan = tblkaryawan.getValueAt( bar, 1).toString();
          data_penilaian.itemTerpilih();
        this.dispose();
    }//GEN-LAST:event_tblkaryawanMouseClicked

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
            java.util.logging.Logger.getLogger(cari_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cari_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cari_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cari_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cari_karyawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private Desain.RoundPanel roundPanel1;
    private Desain.RoundPanel rpsearch;
    private table.Table tblkaryawan;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
