package main;
import java.awt.Color;
import javax.swing.JScrollBar;
import Desain.ScrollBarCustom;
import com.sun.glass.events.KeyEvent;
import java.sql.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class perengkingan extends javax.swing.JPanel {
        private Connection conn = new koneksi().connect();
    PreparedStatement ps;
    Statement stat;
    DefaultTableModel tabelNormalisasi;
    DefaultTableModel tabelPeringkat;
    String sql, sqli, mysql;
    DefaultTableModel tabmode;
    public String lap;
      JasperReport JasRep;
      JasperPrint JasPrin;
      Map param = new HashMap();
      JasperDesign JasDes;
    public perengkingan() {
        initComponents();
        normalisasi();
        costumtabel();
    }
        private void costumtabel(){
        tblnormalisasi.fixTable(jScrollPane2);
        tblperingkat.fixTable(jScrollPane3);
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane3.setVerticalScrollBar(new ScrollBarCustom());
        ScrollBarCustom nr = new ScrollBarCustom();
        ScrollBarCustom pr = new ScrollBarCustom();
        nr.setOrientation(JScrollBar.HORIZONTAL);
        pr.setOrientation(JScrollBar.HORIZONTAL);
        jScrollPane2.setHorizontalScrollBar(nr);
        jScrollPane3.setHorizontalScrollBar(pr);
        
    }
    
     public void tabelmodelPeringkat(){
        Object[] Baris = {"NIK","Nama Karyawan","Nilai"};
        tabelPeringkat = new DefaultTableModel(null, Baris);
        tblperingkat.setModel(tabelPeringkat);
       try{
            String mysql ="SELECT hasil.nilai, karyawan.nik, karyawan.nama from hasil INNER JOIN karyawan ON hasil.nik=karyawan.nik ORDER BY nilai desc";
            java.sql.Statement stat = conn.createStatement();
            ResultSet peringkat = stat.executeQuery(mysql);
            while (peringkat.next()){
                String a = peringkat.getString("nik");
                String b = peringkat.getString("nama");
                String c = peringkat.getString("nilai"); 
                String[] data = {a,b,c};
               tabelPeringkat.addRow(data); 
            }
        }catch (Exception e){
        }    
    }
    
    public void cariperingkat(){
    LinkedList mm = new LinkedList();
        try{
             String sqli ="SELECT * from bobot";
            java.sql.Statement stat = conn.createStatement();
            ResultSet cariperingkat = stat.executeQuery(sqli);
            tabelmodelPeringkat();
            while (cariperingkat.next()){
                 mm.add(cariperingkat.getString("jam_kerja"));
                 mm.add(cariperingkat.getString("absensi"));
                 mm.add(cariperingkat.getString("kerapihan"));
                 mm.add(cariperingkat.getString("keterlambatan"));
            }
            for (int t = 0; t < tblnormalisasi.getRowCount(); t++) {
                String mysql = "Delete FROM hasil WHERE nik  ";
                PreparedStatement state = conn.prepareStatement(mysql);
                state.executeUpdate();
            }
            for (int x = 0; x < tblnormalisasi.getRowCount(); x++){
                double r1; 
                double r2;
                double r3;
                double r4;
                double w;
                r1 = (Float.valueOf(tblnormalisasi.getValueAt(x, 2).toString())*Float.valueOf(mm.get(0).toString()));
                r2=(Float.valueOf(tblnormalisasi.getValueAt(x, 3).toString())*Float.valueOf(mm.get(1).toString()));
                r3=(Float.valueOf(tblnormalisasi.getValueAt(x, 4).toString())*Float.valueOf(mm.get(2).toString()));
                r4=(Float.valueOf(tblnormalisasi.getValueAt(x, 5).toString())*Float.valueOf(mm.get(3).toString()));
                w=r1+r2+r3+r4; 
                String sql = "insert into hasil (nik, nilai) values"+"('"+tblnormalisasi.getValueAt(x, 0).toString()+"' , "+" '"+w+"') ";
                PreparedStatement state = conn.prepareStatement(sql);
                state.executeUpdate();
            }
            tabelModelNormalisasi();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex);
        }
    }    
    
    //objeck tabel normalisasi form rangking
    public void tabelModelNormalisasi(){
       tabelNormalisasi = new DefaultTableModel();
       tabelNormalisasi.addColumn("NIK");
       tabelNormalisasi.addColumn("Nama");
       tabelNormalisasi.addColumn("C1");
       tabelNormalisasi.addColumn("C2");
       tabelNormalisasi.addColumn("C3");
       tabelNormalisasi.addColumn("C4");
       tblnormalisasi.setModel(tabelNormalisasi);
    }
    // tabel normalisasi form rangking
    public void normalisasi(){
        LinkedList max = new LinkedList();
        LinkedList mn = new LinkedList();            
        try{
            String sql = "select max(nilai_jam_kerja), max(nilai_absensi), max(nilai_kerapihan), min(nilai_keterlambatan) from rating_kecocokan ";
            java.sql.Statement stat = conn.createStatement();
            ResultSet normalisasi = stat.executeQuery(sql);
            while (normalisasi.next()){
                max.add(normalisasi.getString(1));
                max.add(normalisasi.getString(2));
                max.add(normalisasi.getString(3));
                max.add(normalisasi.getString(4));
            }
            String sqli = "select rating_kecocokan.nilai_jam_kerja, rating_kecocokan.nilai_absensi, rating_kecocokan.nilai_kerapihan, rating_kecocokan.nilai_keterlambatan, "
                    + "karyawan.nik, karyawan.nama "
                    + "from rating_kecocokan INNER JOIN karyawan ON rating_kecocokan.nik=karyawan.nik";
            ResultSet res2 = stat.executeQuery(sqli);
            tabelModelNormalisasi();
            while (res2.next()){
                    tabelNormalisasi.addRow(new Object[]{
                    res2.getString("nik"),
                    res2.getString("nama"),
                    (res2.getFloat("nilai_jam_kerja")/Float.valueOf(max.get(0).toString())),
                    (res2.getFloat("nilai_absensi")/Float.valueOf(max.get(1).toString())),
                    (res2.getFloat("nilai_kerapihan")/Float.valueOf(max.get(2).toString())),
                    (Float.valueOf(max.get(3).toString())/res2.getFloat("nilai_keterlambatan"))});
                    //(res2.getFloat("keterlambatan")/Float.valueOf(max.get(3).toString()))});
                            //(Float.valueOf(max.get(3).toString())/res2.getFloat("keterlamban"))});
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex);
        }
    }
    
    //tabel peringkat form rangking
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        rpnormalisasi = new Desain.RoundPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblnormalisasi = new table.Table();
        btnnormalisasi = new Desain.Button();
        rpperingkat = new Desain.RoundPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblperingkat = new table.Table();
        btncetak = new Desain.Button();
        btnperingkat = new Desain.Button();

        setPreferredSize(new java.awt.Dimension(1215, 726));

        jPanel1.setBackground(new java.awt.Color(248, 248, 255));

        rpnormalisasi.setBackground(new java.awt.Color(255, 255, 255));
        rpnormalisasi.setRound(20);

        tblnormalisasi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblnormalisasi);

        btnnormalisasi.setBackground(new java.awt.Color(29, 162, 253));
        btnnormalisasi.setForeground(new java.awt.Color(255, 255, 255));
        btnnormalisasi.setText("Cek Normalisasi");
        btnnormalisasi.setPreferredSize(new java.awt.Dimension(59, 45));
        btnnormalisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnormalisasiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rpnormalisasiLayout = new javax.swing.GroupLayout(rpnormalisasi);
        rpnormalisasi.setLayout(rpnormalisasiLayout);
        rpnormalisasiLayout.setHorizontalGroup(
            rpnormalisasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpnormalisasiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rpnormalisasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(btnnormalisasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        rpnormalisasiLayout.setVerticalGroup(
            rpnormalisasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpnormalisasiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnnormalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        rpperingkat.setBackground(new java.awt.Color(255, 255, 255));
        rpperingkat.setRound(20);

        tblperingkat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "NIK", "Nama Karyawan", "Nilai"
            }
        ));
        jScrollPane3.setViewportView(tblperingkat);

        btncetak.setBackground(new java.awt.Color(29, 162, 253));
        btncetak.setForeground(new java.awt.Color(255, 255, 255));
        btncetak.setText("Cetak");
        btncetak.setPreferredSize(new java.awt.Dimension(59, 45));
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });

        btnperingkat.setBackground(new java.awt.Color(29, 162, 253));
        btnperingkat.setForeground(new java.awt.Color(255, 255, 255));
        btnperingkat.setText("Cek Peringkat");
        btnperingkat.setPreferredSize(new java.awt.Dimension(59, 45));
        btnperingkat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnperingkatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rpperingkatLayout = new javax.swing.GroupLayout(rpperingkat);
        rpperingkat.setLayout(rpperingkatLayout);
        rpperingkatLayout.setHorizontalGroup(
            rpperingkatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpperingkatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rpperingkatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(rpperingkatLayout.createSequentialGroup()
                        .addComponent(btnperingkat, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btncetak, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)))
                .addContainerGap())
        );
        rpperingkatLayout.setVerticalGroup(
            rpperingkatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpperingkatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(rpperingkatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnperingkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rpnormalisasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rpperingkat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(rpnormalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(rpperingkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
        try {
            File file = new File("src/laporan/Laporan_Peringkat.jrxml");
            JasDes = JRXmlLoader.load(file);
            param.clear();
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPrin = JasperFillManager.fillReport(JasRep, param, new koneksi().connect());
            JasperViewer.viewReport(JasPrin, false);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }//GEN-LAST:event_btncetakActionPerformed

    private void btnperingkatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnperingkatActionPerformed
        cariperingkat();
    }//GEN-LAST:event_btnperingkatActionPerformed

    private void btnnormalisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnormalisasiActionPerformed
        normalisasi();
    }//GEN-LAST:event_btnnormalisasiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Desain.Button btncetak;
    private Desain.Button btnnormalisasi;
    private Desain.Button btnperingkat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private Desain.RoundPanel rpnormalisasi;
    private Desain.RoundPanel rpperingkat;
    public static volatile table.Table tblnormalisasi;
    public static volatile table.Table tblperingkat;
    // End of variables declaration//GEN-END:variables
}
