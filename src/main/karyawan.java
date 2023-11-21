package main;
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
//import static main.dahshbore.lbltotal_karyawan;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import static main.penilaian.tblpenilaian;
import static main.perengkingan.tblnormalisasi;
import static main.perengkingan.tblperingkat;
import static main.dahshbore.lbljumlah_karyawan;

public class karyawan extends javax.swing.JPanel {
    private Connection conn = new koneksi().connect(); 
    
    public String lap;
    JasperReport JasRep;
    JasperPrint JasPrin;
    Map param = new HashMap();
    JasperDesign JasDes;
    
    DefaultTableModel tabmode;
    private DefaultTableModel tabelNormalisasi;
    private DefaultTableModel tabelPeringkat;
    private DefaultTableModel tabelpenilaian;
    
    Statement stat;
    ResultSet rs;
    PreparedStatement ps;
    String sql;

    public karyawan() {
        initComponents();
         datatabel();
         costumtabel();
         placeholder();
        btnhapus.setEnabled(false);
        btnubah.setEnabled(false);
        btnubah.setEnabled(true);
    }
    
    private void costumtabel(){
        tblkaryawan.fixTable(jScrollPane2);
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
        ScrollBarCustom sp = new ScrollBarCustom();
        sp.setOrientation(JScrollBar.HORIZONTAL);
        jScrollPane2.setHorizontalScrollBar(sp);
        tblkaryawan.fixTable(jScrollPane2);
    }
    
    public void placeholder(){
        addPlaceholderStyle(txtnik);
        addPlaceholderStyle(txtnama);
        addPlaceholderStyle(txtalamat);   
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
        
    protected void datatabel(){              
        Object[] Baris = {"NIK Karyawan","Nama Karyawan","Alamat","Jenis Kelamin","Jabatan"};
        tabmode = new DefaultTableModel(null, Baris);
        tblkaryawan.setModel(tabmode);
        String sql = "select  karyawan.nik, karyawan.nama, karyawan.alamat, karyawan.jenis_kelamin, "
                + "jabatan.jabatan from karyawan INNER JOIN jabatan ON karyawan.nik=jabatan.nik ";
            {
             int row=tabmode.getRowCount();
             for (int i=0;i<row;i++){tabmode.removeRow(0);}
            } 
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
        int b = tabmode.getRowCount();
        lbljumlah_karyawan.setText(""+b);    //label total karyawan yg diform karyawan,total data didatbel dipanggil ke label total
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
    
    public void bersih(){
        txtnik.setText("2121...");
            txtnik.setForeground(Color.GRAY);
        txtnama.setText("Nama Karyawan");
            txtnama.setForeground(Color.GRAY);
        txtalamat.setText("Alamat Karyawan");
            txtalamat.setForeground(Color.GRAY);
        cmbjk.setSelectedIndex(0);
              cmbjk.setForeground(Color.GRAY);
        cmbjabatan.setSelectedIndex(0);
              cmbjabatan.setForeground(Color.GRAY);
        btnhapus.setEnabled(false);
        btnubah.setEnabled(false);
        btnsimpan.setEnabled(true);
    }
    
    // tabel form penilaian
    protected void datatabel_penilaian(){              
       Object[] Baris = {"ID Penilaian","NIK","Nama","Jam Kerja","Absensi","Kerapihan", "Keterlambatan"};
        tabelpenilaian = new DefaultTableModel(null, Baris);
        tblpenilaian.setModel(tabelpenilaian);
        try{
            String sql = "select penilaian_karyawan.id_penilaian, penilaian_karyawan.jam_kerja, penilaian_karyawan.absensi, penilaian_karyawan.kerapihan, "
                    + "penilaian_karyawan.keterlambatan, karyawan.nik, karyawan.nama from penilaian_karyawan "
                    + "INNER JOIN karyawan ON penilaian_karyawan.nik=karyawan.nik";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_penilaian");
                String b = hasil.getString("nik");
                String c = hasil.getString("nama"); 
                String d = hasil.getString("jam_kerja"); 
                String e = hasil.getString("absensi");
                String f = hasil.getString("kerapihan"); 
                String g = hasil.getString("keterlambatan");
                String[] data = {a,b,c,d,e,f,g};
                tabelpenilaian.addRow(data); 
            }
        }catch (Exception e){
        }
        //int b = tabmode.getRowCount();
        //lbltotal.setText(""+b);    //label total karyawan yg diform karyawan,total data didatbel dipanggil ke label total
        //lbltotal_karyawan.setText(""+b); //label total karyawan yg dimenu utama,total data didata tabel dipanggil ke label total
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
    //tabel normalisasi form rangking
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        rpinput = new Desain.RoundPanel();
        lbldata_karyawan = new javax.swing.JLabel();
        lblnik = new javax.swing.JLabel();
        txtnik = new Desain.TextFieldSuggestion();
        lblnama = new javax.swing.JLabel();
        txtnama = new Desain.TextFieldSuggestion();
        lblalamat = new javax.swing.JLabel();
        txtalamat = new Desain.TextFieldSuggestion();
        lbljk = new javax.swing.JLabel();
        cmbjk = new Desain.ComboBoxSuggestion();
        lbljabatan = new javax.swing.JLabel();
        cmbjabatan = new Desain.ComboBoxSuggestion();
        btnsimpan = new Desain.Button();
        Bersih = new Desain.Button();
        rpsearch = new Desain.RoundPanel();
        txtcari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        roundPanel1 = new Desain.RoundPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblkaryawan = new table.Table();
        btnubah = new Desain.Button();
        btcetak = new Desain.Button();
        btnhapus = new Desain.Button();

        jPanel1.setBackground(new java.awt.Color(248, 248, 255));

        rpinput.setBackground(new java.awt.Color(255, 255, 255));
        rpinput.setPreferredSize(new java.awt.Dimension(292, 100));
        rpinput.setRound(20);

        lbldata_karyawan.setBackground(new java.awt.Color(204, 204, 204));
        lbldata_karyawan.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbldata_karyawan.setText("Data Karyawan");

        lblnik.setBackground(new java.awt.Color(204, 204, 204));
        lblnik.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblnik.setForeground(new java.awt.Color(102, 102, 102));
        lblnik.setText("NIK Karyawan");

        txtnik.setText("2121...");
        txtnik.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnik.setRound(5);
        txtnik.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnikFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtnikFocusLost(evt);
            }
        });

        lblnama.setBackground(new java.awt.Color(204, 204, 204));
        lblnama.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblnama.setForeground(new java.awt.Color(102, 102, 102));
        lblnama.setText("Nama");

        txtnama.setText("Nama Karyawan");
        txtnama.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnama.setRound(5);
        txtnama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnamaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtnamaFocusLost(evt);
            }
        });

        lblalamat.setBackground(new java.awt.Color(204, 204, 204));
        lblalamat.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblalamat.setForeground(new java.awt.Color(102, 102, 102));
        lblalamat.setText("Alamat");

        txtalamat.setText("Alamat Karyawan");
        txtalamat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtalamat.setRound(5);
        txtalamat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtalamatFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtalamatFocusLost(evt);
            }
        });

        lbljk.setBackground(new java.awt.Color(204, 204, 204));
        lbljk.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lbljk.setForeground(new java.awt.Color(102, 102, 102));
        lbljk.setText("Jenis Kelamin");

        cmbjk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Pilih Jenis Kelamin--", "L", "P" }));
        cmbjk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbjk.setPreferredSize(new java.awt.Dimension(56, 20));

        lbljabatan.setBackground(new java.awt.Color(204, 204, 204));
        lbljabatan.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lbljabatan.setForeground(new java.awt.Color(102, 102, 102));
        lbljabatan.setText("Jabatan");

        cmbjabatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Pilih Jabatan--", "Barista", "Coffee store manager", "Production assistant" }));
        cmbjabatan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbjabatan.setPreferredSize(new java.awt.Dimension(56, 20));

        btnsimpan.setBackground(new java.awt.Color(29, 162, 253));
        btnsimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnsimpan.setText("Simpan");
        btnsimpan.setPreferredSize(new java.awt.Dimension(59, 45));
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        Bersih.setBackground(new java.awt.Color(29, 162, 253));
        Bersih.setForeground(new java.awt.Color(255, 255, 255));
        Bersih.setText("Bersih");
        Bersih.setPreferredSize(new java.awt.Dimension(59, 45));
        Bersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BersihActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rpinputLayout = new javax.swing.GroupLayout(rpinput);
        rpinput.setLayout(rpinputLayout);
        rpinputLayout.setHorizontalGroup(
            rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpinputLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbljabatan)
                    .addComponent(lbljk)
                    .addComponent(txtalamat, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addComponent(lblalamat)
                    .addComponent(txtnama, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addComponent(lblnama)
                    .addComponent(lblnik)
                    .addComponent(lbldata_karyawan)
                    .addComponent(txtnik, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbjk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbjabatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsimpan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bersih, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        rpinputLayout.setVerticalGroup(
            rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpinputLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbldata_karyawan)
                .addGap(35, 35, 35)
                .addComponent(lblnik)
                .addGap(6, 6, 6)
                .addComponent(txtnik, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lblnama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lblalamat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtalamat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lbljk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbjk, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lbljabatan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbjabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Bersih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        rpsearch.setBackground(new java.awt.Color(255, 255, 255));
        rpsearch.setPreferredSize(new java.awt.Dimension(739, 50));
        rpsearch.setRound(50);

        txtcari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtcari.setText("Cari Data Karyawan");
        txtcari.setBorder(null);
        txtcari.setCaretColor(new java.awt.Color(102, 102, 0));
        txtcari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcariFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcariFocusLost(evt);
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_search_30px.png"))); // NOI18N

        javax.swing.GroupLayout rpsearchLayout = new javax.swing.GroupLayout(rpsearch);
        rpsearch.setLayout(rpsearchLayout);
        rpsearchLayout.setHorizontalGroup(
            rpsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpsearchLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rpsearchLayout.setVerticalGroup(
            rpsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpsearchLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(rpsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

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

        btnubah.setBackground(new java.awt.Color(246, 159, 50));
        btnubah.setForeground(new java.awt.Color(255, 255, 255));
        btnubah.setText("Ubah");
        btnubah.setPreferredSize(new java.awt.Dimension(59, 45));
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });

        btcetak.setBackground(new java.awt.Color(29, 162, 253));
        btcetak.setForeground(new java.awt.Color(255, 255, 255));
        btcetak.setText("Cetak");
        btcetak.setPreferredSize(new java.awt.Dimension(59, 45));
        btcetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcetakActionPerformed(evt);
            }
        });

        btnhapus.setBackground(new java.awt.Color(253, 83, 83));
        btnhapus.setForeground(new java.awt.Color(255, 255, 255));
        btnhapus.setText("Hapus");
        btnhapus.setPreferredSize(new java.awt.Dimension(59, 45));
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(btnubah, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btcetak, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnubah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(rpinput, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rpsearch, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rpsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rpinput, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(253, 253, Short.MAX_VALUE))
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

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
            String key=txtcari.getText();
        System.out.println(key);  
        
        if(key!=""){
            caridata(key);
        }else{
            datatabel();
        }
    }//GEN-LAST:event_txtcariKeyReleased

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        try{
            String sql = "insert into karyawan values (?,?,?,?)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtnik .getText());
            stat.setString(2, txtnama.getText());
            stat.setString(3, txtalamat.getText());
            stat.setString(4, cmbjk.getSelectedItem().toString());
            stat.executeUpdate();
            txtnik.requestFocus();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }
        try{
            String sql = "insert into jabatan values (?,?)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtnik .getText());
            stat.setString(2, cmbjabatan.getSelectedItem().toString());
                stat.executeUpdate();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }
        bersih();
        datatabel();
        normalisasi();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void BersihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BersihActionPerformed
        bersih();
    }//GEN-LAST:event_BersihActionPerformed

    private void tblkaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkaryawanMouseClicked
               int bar = tblkaryawan.getSelectedRow();
        txtnik.setText(tabmode.getValueAt(bar, 0).toString());
        txtnama.setText(tabmode.getValueAt(bar, 1).toString());
        txtalamat.setText(tabmode.getValueAt(bar, 2).toString());
        cmbjk.setSelectedItem(tabmode.getValueAt(bar, 3).toString());
        cmbjabatan.setSelectedItem(tabmode.getValueAt(bar, 4).toString());
        
         txtnik.setForeground(Color.BLACK);
         txtnama.setForeground(Color.BLACK);
         txtalamat.setForeground(Color.BLACK);
         
         btnhapus.setEnabled(true);
         btnubah.setEnabled(true);
         btnsimpan.setEnabled(false);
    }//GEN-LAST:event_tblkaryawanMouseClicked

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
                try{
            String sql = "update karyawan set nama=?, alamat=?, jenis_kelamin=? where nik=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtnama.getText());
            stat.setString(2, txtalamat.getText());
            stat.setString(3, cmbjk.getSelectedItem().toString());
            stat.setString(4, txtnik.getText());
            stat.executeUpdate();
            txtnik.requestFocus();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah "+e);
        }
        try{
            String sql = "update jabatan set jabatan=? where nik=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, cmbjabatan.getSelectedItem().toString());
            stat.setString(2, txtnik .getText());
            stat.executeUpdate();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }
        bersih();
        datatabel();
        normalisasi();
        datatabel_penilaian();
    }//GEN-LAST:event_btnubahActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
          int OK = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if(OK==0){
            String sql = "delete from karyawan where nik = '"+txtnik.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                txtnik.requestFocus();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e);
            }
        }
        bersih();
        datatabel();
        normalisasi();
        datatabel_penilaian();
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btcetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcetakActionPerformed
        try {
            File file = new File("src/laporan/Laporan_Karyawan.jrxml");
            JasDes = JRXmlLoader.load(file);
            param.clear();
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPrin = JasperFillManager.fillReport(JasRep, param, new koneksi().connect());
            JasperViewer.viewReport(JasPrin, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btcetakActionPerformed

    private void txtnikFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnikFocusGained
        if(txtnik.getText().equals("2121..."))
        {
            txtnik.setText(null);
            txtnik.requestFocus();
            resetPlaceholderStyle(txtnik);
        }
    }//GEN-LAST:event_txtnikFocusGained

    private void txtnikFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnikFocusLost
        if(txtnik.getText().length()==0)
        {
            addPlaceholderStyle(txtnik);
            txtnik.setText("2121...");
        }
    }//GEN-LAST:event_txtnikFocusLost

    private void txtnamaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnamaFocusGained
        if(txtnama.getText().equals("Nama Karyawan"))
        {
            txtnama.setText(null);
            txtnama.requestFocus();
            resetPlaceholderStyle(txtnama);
        }
    }//GEN-LAST:event_txtnamaFocusGained

    private void txtnamaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnamaFocusLost
        if(txtnama.getText().length()==0)
        {
            addPlaceholderStyle(txtnama);
            txtnama.setText("Nama Karyawan");
        }
    }//GEN-LAST:event_txtnamaFocusLost

    private void txtalamatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtalamatFocusGained
        if(txtalamat.getText().equals("Alamat Karyawan"))
        {
            txtalamat.setText(null);
            txtalamat.requestFocus();
            resetPlaceholderStyle(txtalamat);
        }
    }//GEN-LAST:event_txtalamatFocusGained

    private void txtalamatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtalamatFocusLost
        if(txtalamat.getText().length()==0)
        {
            addPlaceholderStyle(txtalamat);
            txtalamat.setText("Alamat Karyawan");
        }
    }//GEN-LAST:event_txtalamatFocusLost

    private void txtcariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcariFocusGained
         if(txtcari.getText().equals("Cari Data Karyawan"))
        {
            txtcari.setText(null);
            txtcari.requestFocus();
            resetPlaceholderStyle(txtcari);
        }
    }//GEN-LAST:event_txtcariFocusGained

    private void txtcariFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcariFocusLost
       if(txtcari.getText().length()==0)
        {
            addPlaceholderStyle(txtcari);
            txtcari.setText("Cari Data Karyawan");
        }
    }//GEN-LAST:event_txtcariFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Desain.Button Bersih;
    private Desain.Button btcetak;
    private Desain.Button btnhapus;
    private Desain.Button btnsimpan;
    private Desain.Button btnubah;
    private Desain.ComboBoxSuggestion cmbjabatan;
    private Desain.ComboBoxSuggestion cmbjk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblalamat;
    private javax.swing.JLabel lbldata_karyawan;
    private javax.swing.JLabel lbljabatan;
    private javax.swing.JLabel lbljk;
    private javax.swing.JLabel lblnama;
    private javax.swing.JLabel lblnik;
    private Desain.RoundPanel roundPanel1;
    private Desain.RoundPanel rpinput;
    private Desain.RoundPanel rpsearch;
    private table.Table tblkaryawan;
    private Desain.TextFieldSuggestion txtalamat;
    private javax.swing.JTextField txtcari;
    private Desain.TextFieldSuggestion txtnama;
    private Desain.TextFieldSuggestion txtnik;
    // End of variables declaration//GEN-END:variables
}
