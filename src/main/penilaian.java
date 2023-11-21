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
import Cari_Data.cari_karyawan;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import static main.perengkingan.tblnormalisasi;
import static main.perengkingan.tblperingkat;
import static main.dahshbore.lbljumlah_penilaian;


public class penilaian extends javax.swing.JPanel {
    private Connection conn = new koneksi().connect(); 
    
    public String lap;
    JasperReport JasRep;
    JasperPrint JasPrin;
    Map param = new HashMap();
    JasperDesign JasDes;
    
    DefaultTableModel tabmode;
    private DefaultTableModel tabelNormalisasi;
    private DefaultTableModel tabelPeringkat;
    
    Statement stat;
    ResultSet rs;
    PreparedStatement ps;
    String  sql, sqli, JamKerja, Absensi, Kerapihan, Keterlambatan;
    Float NilaiJam_Kerja, NilaiAbsensi, NilaiKerapihan, NilaiKeterlambatan;
    
    public penilaian() {
        initComponents();
        datatabel();
        IDOtomatis();
        costumtabel();
        placeholder();
    }
    
    private void costumtabel(){
        tblpenilaian.fixTable(jScrollPane2);
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
        ScrollBarCustom sp = new ScrollBarCustom();
        sp.setOrientation(JScrollBar.HORIZONTAL);
        jScrollPane2.setHorizontalScrollBar(sp);
        tblpenilaian.fixTable(jScrollPane2);
    }
    
    protected void bersih(){
         IDOtomatis();
        txtnik.setText("Nik Karyawan");
             txtnik.setForeground(Color.GRAY);
        txtnama.setText("Nama Karyawan");
             txtnama.setForeground(Color.GRAY);
        cmbjam_kerja.setSelectedIndex(0);
            txtnilai_jam.setText("0");
                 txtnilai_jam.setForeground(Color.GRAY);
        cmbabsensi.setSelectedIndex(0);
            txtnilai_absensi.setText("0");
                 txtnilai_absensi.setForeground(Color.GRAY);
        cmbkerapihan.setSelectedIndex(0);
            txtnilai_kerapihan.setText("0");
                  txtnilai_kerapihan.setForeground(Color.GRAY);
        cmbketerlambatan.setSelectedIndex(0);
            txtnilai_keterlambatan.setText("0");
                   txtnilai_keterlambatan.setForeground(Color.GRAY);
    }
    
     public void placeholder(){
        addPlaceholderStyle(txtcari); 
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

    public String nik_karyawan, nama_karyawan;
    public String getnik_karyawan() {
	return nik_karyawan;
    }
    public String getnama_karyawan() {
	return nama_karyawan;
    }
    public void itemTerpilih(){
        cari_karyawan tbl_data_karyawan= new  cari_karyawan();
        tbl_data_karyawan.data_penilaian = this;
        txtnik.setText(nik_karyawan);
        txtnik.setForeground(Color.black);
        txtnama.setText(nama_karyawan);
        txtnama.setForeground(Color.black);
    }
    
    //membuat id otomatis
    private void IDOtomatis(){
        try{
            sql = "select * from penilaian_karyawan order by id_penilaian desc";
            stat =  conn.createStatement(); 
            rs = stat.executeQuery(sql);
            if(rs.next()){
                String id = rs.getString("id_penilaian").substring(2);
                String AN = "" + (Integer.parseInt(id) + 1);
                String Nol = "";
                
                if (AN.length()==1){
                    Nol = "000";
                }else if (AN.length()==2){
                    Nol = "00";
                }else if (AN.length()==3){
                    Nol = "0";
                }else if (AN.length()==4){
                    Nol = "";
                }
                txtid.setText("PN" + Nol + AN);
            }else{
                txtid.setText("PN0001");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    protected void datatabel(){              
       Object[] Baris = {"ID Penilaian","NIK","Nama","C1","C2","C3", "C4","C1", "C2", "C3", "C4"};
        tabmode = new DefaultTableModel(null, Baris);
        tblpenilaian.setModel(tabmode);
        try{
            String sql = "select penilaian_karyawan.id_penilaian, penilaian_karyawan.jam_kerja, penilaian_karyawan.absensi, penilaian_karyawan.kerapihan, "
                    + "penilaian_karyawan.keterlambatan, karyawan.nik, karyawan.nama, rating_kecocokan.nilai_jam_kerja, rating_kecocokan.nilai_absensi, "
                    + "rating_kecocokan.nilai_kerapihan, rating_kecocokan.nilai_keterlambatan from penilaian_karyawan "
                    + "INNER JOIN karyawan ON penilaian_karyawan.nik=karyawan.nik INNER JOIN rating_kecocokan ON rating_kecocokan.nik=penilaian_karyawan.nik";
                {
                  int row=tabmode.getRowCount();
                  for (int i=0;i<row;i++){tabmode.removeRow(0);}
                } 
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
                String h = hasil.getString("nilai_jam_kerja"); 
                String i = hasil.getString("nilai_absensi");
                String j = hasil.getString("nilai_kerapihan"); 
                String k = hasil.getString("nilai_keterlambatan");
                String[] data = {a,b,c,d,e,f,g,h,i,j,k};
                tabmode.addRow(data); 
            }
        }
            catch (Exception e){
        }
        int b = tabmode.getRowCount();
        lbljumlah_penilaian.setText(""+b);    //label total karyawan yg diform karyawan,total data didatbel dipanggil ke label total
        //lbltotal_karyawan.setText(""+b); //label total karyawan yg dimenu utama,total data didata tabel dipanggil ke label total
    }   
 
    protected void caridata(String key){              
       Object[] Baris = {"ID Penilaian","NIK","Nama","C1","C2","C3", "C4","C1","C2","C3", "C4"};
        tabmode = new DefaultTableModel(null, Baris);
        tblpenilaian.setModel(tabmode);
        try{
            String sql = "select penilaian_karyawan.id_penilaian, penilaian_karyawan.jam_kerja, penilaian_karyawan.absensi, penilaian_karyawan.kerapihan, "
                    + "penilaian_karyawan.keterlambatan, karyawan.nik, karyawan.nama, rating_kecocokan.nilai_jam_kerja, rating_kecocokan.nilai_absensi, "
                    + "rating_kecocokan.nilai_kerapihan, rating_kecocokan.nilai_keterlambatan from penilaian_karyawan "
                    + "INNER JOIN karyawan ON penilaian_karyawan.nik=karyawan.nik INNER JOIN rating_kecocokan ON rating_kecocokan.nik=penilaian_karyawan.nik where nama like '%"+txtcari.getText()+"%' "
                 + "or jam_kerja like '%"+txtcari.getText()+"%' or absensi like '%"+txtcari.getText()+"%' "
                 + "or kerapihan like '%"+txtcari.getText()+"%' or keterlambatan like '%"+txtcari.getText()+"%' ";
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
                String h = hasil.getString("nilai_jam_kerja"); 
                String i = hasil.getString("nilai_absensi");
                String j = hasil.getString("nilai_kerapihan"); 
                String k = hasil.getString("nilai_keterlambatan");
                String[] data = {a,b,c,d,e,f,g,h,i,j,k};
                tabmode.addRow(data); 
            }
        }
        catch (Exception e){
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
        rpsearch = new Desain.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();
        tptabel = new Desain.RoundPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblpenilaian = new table.Table();
        btnubah = new Desain.Button();
        btcetak_penilaian = new Desain.Button();
        bthapus = new Desain.Button();
        btncetak_nilai = new Desain.Button();
        rpinput = new Desain.RoundPanel();
        lbldata_penilaian = new javax.swing.JLabel();
        lblid = new javax.swing.JLabel();
        lblnik = new javax.swing.JLabel();
        txtid = new Desain.TextFieldSuggestion();
        txtnik = new Desain.TextFieldSuggestion();
        lblnik1 = new javax.swing.JLabel();
        txtnama = new Desain.TextFieldSuggestion();
        btncari = new Desain.Button();
        lblalamat = new javax.swing.JLabel();
        cmbjam_kerja = new Desain.ComboBoxSuggestion();
        txtnilai_jam = new Desain.TextFieldSuggestion();
        lblabsensi = new javax.swing.JLabel();
        cmbabsensi = new Desain.ComboBoxSuggestion();
        lbljabatan = new javax.swing.JLabel();
        cmbkerapihan = new Desain.ComboBoxSuggestion();
        txtnilai_absensi = new Desain.TextFieldSuggestion();
        txtnilai_kerapihan = new Desain.TextFieldSuggestion();
        lblketerlambatan = new javax.swing.JLabel();
        txtnilai_keterlambatan = new Desain.TextFieldSuggestion();
        cmbketerlambatan = new Desain.ComboBoxSuggestion();
        btnsimpan = new Desain.Button();
        btnbersih = new Desain.Button();

        setPreferredSize(new java.awt.Dimension(1215, 726));

        jPanel1.setBackground(new java.awt.Color(248, 248, 255));
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        rpsearch.setBackground(new java.awt.Color(255, 255, 255));
        rpsearch.setPreferredSize(new java.awt.Dimension(739, 50));
        rpsearch.setRound(50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_search_30px.png"))); // NOI18N

        txtcari.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtcari.setText("Cari Data Penilaian");
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

        javax.swing.GroupLayout rpsearchLayout = new javax.swing.GroupLayout(rpsearch);
        rpsearch.setLayout(rpsearchLayout);
        rpsearchLayout.setHorizontalGroup(
            rpsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpsearchLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        tptabel.setBackground(new java.awt.Color(255, 255, 255));
        tptabel.setRound(20);

        tblpenilaian.setModel(new javax.swing.table.DefaultTableModel(
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
        tblpenilaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpenilaianMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblpenilaian);

        btnubah.setBackground(new java.awt.Color(246, 159, 50));
        btnubah.setForeground(new java.awt.Color(255, 255, 255));
        btnubah.setText("Ubah");
        btnubah.setPreferredSize(new java.awt.Dimension(59, 45));
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });

        btcetak_penilaian.setBackground(new java.awt.Color(29, 162, 253));
        btcetak_penilaian.setForeground(new java.awt.Color(255, 255, 255));
        btcetak_penilaian.setText("Cetak Penilaian");
        btcetak_penilaian.setPreferredSize(new java.awt.Dimension(59, 45));
        btcetak_penilaian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcetak_penilaianActionPerformed(evt);
            }
        });

        bthapus.setBackground(new java.awt.Color(253, 83, 83));
        bthapus.setForeground(new java.awt.Color(255, 255, 255));
        bthapus.setText("Hapus");
        bthapus.setPreferredSize(new java.awt.Dimension(59, 45));
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });

        btncetak_nilai.setBackground(new java.awt.Color(29, 162, 253));
        btncetak_nilai.setForeground(new java.awt.Color(255, 255, 255));
        btncetak_nilai.setText("Cetak Nilai");
        btncetak_nilai.setPreferredSize(new java.awt.Dimension(59, 45));
        btncetak_nilai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetak_nilaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tptabelLayout = new javax.swing.GroupLayout(tptabel);
        tptabel.setLayout(tptabelLayout);
        tptabelLayout.setHorizontalGroup(
            tptabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tptabelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tptabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tptabelLayout.createSequentialGroup()
                        .addComponent(btnubah, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btcetak_penilaian, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btncetak_nilai, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
                .addContainerGap())
        );
        tptabelLayout.setVerticalGroup(
            tptabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tptabelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tptabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnubah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcetak_penilaian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncetak_nilai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rpinput.setBackground(new java.awt.Color(255, 255, 255));
        rpinput.setPreferredSize(new java.awt.Dimension(292, 619));
        rpinput.setRound(20);

        lbldata_penilaian.setBackground(new java.awt.Color(204, 204, 204));
        lbldata_penilaian.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbldata_penilaian.setText("Data Penilaian");

        lblid.setBackground(new java.awt.Color(204, 204, 204));
        lblid.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblid.setForeground(new java.awt.Color(102, 102, 102));
        lblid.setText("ID Penilaian");

        lblnik.setBackground(new java.awt.Color(204, 204, 204));
        lblnik.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblnik.setForeground(new java.awt.Color(102, 102, 102));
        lblnik.setText("NIK Karyawan");

        txtid.setEditable(false);
        txtid.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtid.setRound(5);

        txtnik.setEditable(false);
        txtnik.setForeground(new java.awt.Color(102, 102, 102));
        txtnik.setText("2121...");
        txtnik.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnik.setRound(5);

        lblnik1.setBackground(new java.awt.Color(204, 204, 204));
        lblnik1.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblnik1.setForeground(new java.awt.Color(102, 102, 102));
        lblnik1.setText("Nama Karyawan");

        txtnama.setEditable(false);
        txtnama.setForeground(new java.awt.Color(102, 102, 102));
        txtnama.setText("Nama Karyawan");
        txtnama.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnama.setRound(5);

        btncari.setBackground(new java.awt.Color(29, 162, 253));
        btncari.setForeground(new java.awt.Color(255, 255, 255));
        btncari.setText("Cari");
        btncari.setPreferredSize(new java.awt.Dimension(59, 45));
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        lblalamat.setBackground(new java.awt.Color(204, 204, 204));
        lblalamat.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblalamat.setForeground(new java.awt.Color(102, 102, 102));
        lblalamat.setText("Jam Kerja (C1)");

        cmbjam_kerja.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Jam Kerja--", "169 Jam<=", "170-399 Jam", "400-549 Jam", "550-699 Jam", ">=700 Jam" }));
        cmbjam_kerja.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbjam_kerja.setPreferredSize(new java.awt.Dimension(99, 20));
        cmbjam_kerja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbjam_kerjaActionPerformed(evt);
            }
        });

        txtnilai_jam.setForeground(new java.awt.Color(102, 102, 102));
        txtnilai_jam.setText("0");
        txtnilai_jam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnilai_jam.setRound(5);

        lblabsensi.setBackground(new java.awt.Color(204, 204, 204));
        lblabsensi.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblabsensi.setForeground(new java.awt.Color(102, 102, 102));
        lblabsensi.setText("Absensi (C2)");

        cmbabsensi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Absensi--", "70<= Hadir", "71-77 Hadir", "78-84 Hadir", "85-94 Hadir", ">=95 Hadir" }));
        cmbabsensi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbabsensi.setPreferredSize(new java.awt.Dimension(99, 20));
        cmbabsensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbabsensiActionPerformed(evt);
            }
        });

        lbljabatan.setBackground(new java.awt.Color(204, 204, 204));
        lbljabatan.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lbljabatan.setForeground(new java.awt.Color(102, 102, 102));
        lbljabatan.setText("Kerapihan (C3)");

        cmbkerapihan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Kerapihan--", "70<=", "71-77", "78-84", "85-94", ">=95" }));
        cmbkerapihan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbkerapihan.setPreferredSize(new java.awt.Dimension(99, 20));
        cmbkerapihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbkerapihanActionPerformed(evt);
            }
        });

        txtnilai_absensi.setEditable(false);
        txtnilai_absensi.setForeground(new java.awt.Color(102, 102, 102));
        txtnilai_absensi.setText("0");
        txtnilai_absensi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnilai_absensi.setRound(5);

        txtnilai_kerapihan.setEditable(false);
        txtnilai_kerapihan.setForeground(new java.awt.Color(102, 102, 102));
        txtnilai_kerapihan.setText("0");
        txtnilai_kerapihan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnilai_kerapihan.setRound(5);

        lblketerlambatan.setBackground(new java.awt.Color(204, 204, 204));
        lblketerlambatan.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblketerlambatan.setForeground(new java.awt.Color(102, 102, 102));
        lblketerlambatan.setText("Keterlambatan (C3)");

        txtnilai_keterlambatan.setEditable(false);
        txtnilai_keterlambatan.setForeground(new java.awt.Color(102, 102, 102));
        txtnilai_keterlambatan.setText("0");
        txtnilai_keterlambatan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnilai_keterlambatan.setRound(5);

        cmbketerlambatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Keterlambatan--", ">=14 Telat", "11-13 Telat", "8-10 Telat", "5-7 Telat", "3<= Telat" }));
        cmbketerlambatan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbketerlambatan.setPreferredSize(new java.awt.Dimension(99, 20));
        cmbketerlambatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbketerlambatanActionPerformed(evt);
            }
        });

        btnsimpan.setBackground(new java.awt.Color(29, 162, 253));
        btnsimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnsimpan.setText("Simpan");
        btnsimpan.setPreferredSize(new java.awt.Dimension(59, 45));
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnbersih.setBackground(new java.awt.Color(29, 162, 253));
        btnbersih.setForeground(new java.awt.Color(255, 255, 255));
        btnbersih.setText("Bersih");
        btnbersih.setPreferredSize(new java.awt.Dimension(59, 45));
        btnbersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbersihActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rpinputLayout = new javax.swing.GroupLayout(rpinput);
        rpinput.setLayout(rpinputLayout);
        rpinputLayout.setHorizontalGroup(
            rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpinputLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnbersih, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblketerlambatan)
                    .addComponent(lbljabatan)
                    .addComponent(lblabsensi)
                    .addComponent(lblalamat)
                    .addGroup(rpinputLayout.createSequentialGroup()
                        .addComponent(lblid)
                        .addGap(18, 18, 18)
                        .addComponent(lblnik))
                    .addComponent(lbldata_penilaian)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rpinputLayout.createSequentialGroup()
                        .addGroup(rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rpinputLayout.createSequentialGroup()
                                .addComponent(lblnik1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtnama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rpinputLayout.createSequentialGroup()
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnik, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rpinputLayout.createSequentialGroup()
                        .addGroup(rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbkerapihan, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(cmbabsensi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbjam_kerja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbketerlambatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnilai_keterlambatan, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnilai_kerapihan, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnilai_absensi, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnilai_jam, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        rpinputLayout.setVerticalGroup(
            rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpinputLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbldata_penilaian)
                .addGap(35, 35, 35)
                .addGroup(rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblid)
                    .addComponent(lblnik))
                .addGap(6, 6, 6)
                .addGroup(rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnik, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(lblnik1)
                .addGap(6, 6, 6)
                .addGroup(rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(lblalamat)
                .addGap(6, 6, 6)
                .addGroup(rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtnilai_jam, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(cmbjam_kerja, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addComponent(lblabsensi)
                .addGap(6, 6, 6)
                .addGroup(rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbabsensi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnilai_absensi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(lbljabatan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbkerapihan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnilai_kerapihan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(lblketerlambatan)
                .addGap(6, 6, 6)
                .addGroup(rpinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbketerlambatan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnilai_keterlambatan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnbersih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(rpinput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rpsearch, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                    .addComponent(tptabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rpinput, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rpsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(tptabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
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

    private void tblpenilaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpenilaianMouseClicked

        int bar = tblpenilaian.getSelectedRow();
        txtid.setText(tabmode.getValueAt(bar, 0).toString());
        txtnik.setText(tabmode.getValueAt(bar, 1).toString());
        txtnama.setText(tabmode.getValueAt(bar, 2).toString());
        cmbjam_kerja.setSelectedItem(tabmode.getValueAt(bar, 3).toString());
        cmbabsensi.setSelectedItem(tabmode.getValueAt(bar, 4).toString());
        cmbkerapihan.setSelectedItem(tabmode.getValueAt(bar, 5).toString());
        cmbketerlambatan.setSelectedItem(tabmode.getValueAt(bar, 6).toString());
        txtnilai_jam.setText(tabmode.getValueAt(bar, 7).toString());
        txtnilai_absensi.setText(tabmode.getValueAt(bar, 8).toString());
        txtnilai_kerapihan.setText(tabmode.getValueAt(bar, 9).toString());
        txtnilai_keterlambatan.setText(tabmode.getValueAt(bar, 10).toString());
        txtid.setForeground(Color.BLACK);
        txtnik.setForeground(Color.BLACK);
        txtnama.setForeground(Color.BLACK);
    }//GEN-LAST:event_tblpenilaianMouseClicked

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
    try{
            String sql = "update penilaian_karyawan set nik=?, jam_kerja=?, absensi=?, kerapihan=?, keterlambatan=? where id_penilaian=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtnik.getText());
            stat.setString(2, cmbjam_kerja.getSelectedItem().toString());
            stat.setString(3, cmbkerapihan.getSelectedItem().toString());
            stat.setString(4, cmbabsensi.getSelectedItem().toString());
            stat.setString(5, cmbketerlambatan.getSelectedItem().toString());
            stat.setString(6, txtid.getText());
            stat.executeUpdate();
            txtid.requestFocus();
            
            String sql2 = "update rating_kecocokan set nik=?, nilai_jam_kerja=?, nilai_absensi=?, nilai_kerapihan=?, nilai_keterlambatan=? where id_penilaian=?";
            PreparedStatement stat2 = conn.prepareStatement(sql2);
            stat2.setString(1, txtnik.getText());
            stat2.setString(2, txtnilai_jam.getText());
            stat2.setString(3, txtnilai_absensi.getText());
            stat2.setString(4, txtnilai_kerapihan.getText());
            stat2.setString(5, txtnilai_keterlambatan.getText());
            stat2.setString(6, txtid.getText());
            stat2.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah "+e);
        }
            bersih();
            IDOtomatis();
            datatabel();
            normalisasi();
    }//GEN-LAST:event_btnubahActionPerformed

    private void btcetak_penilaianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcetak_penilaianActionPerformed
         try {
            File file = new File("src/laporan/Laporan_Penilaian.jrxml");
            JasDes = JRXmlLoader.load(file);
            param.clear();
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPrin = JasperFillManager.fillReport(JasRep, param, new koneksi().connect());
            JasperViewer.viewReport(JasPrin, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btcetak_penilaianActionPerformed

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
int OK = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if(OK==0){
            String sql = "delete from penilaian_karyawan where nik = '"+txtnik.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                txtid.requestFocus();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e);
            }
        }
        bersih();
        IDOtomatis();
        datatabel();
        normalisasi();
        tabelmodelPeringkat();        
    }//GEN-LAST:event_bthapusActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        cari_karyawan crikaryawan = new cari_karyawan();
        crikaryawan.data_penilaian = this;
        crikaryawan.setVisible(true);
        crikaryawan.setResizable(false);
    }//GEN-LAST:event_btncariActionPerformed

    private void cmbjam_kerjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbjam_kerjaActionPerformed
    JamKerja = (String) cmbjam_kerja.getSelectedItem();
        if (JamKerja == "--Jam Kerja--") {
        NilaiJam_Kerja= 0f;
 } else if (JamKerja == "169 Jam<=") {
        NilaiJam_Kerja = 0.2f;
 }  else if (JamKerja == "170-399 Jam") {
        NilaiJam_Kerja = 0.4f;
 }  else if (JamKerja == "400-549 Jam") {
        NilaiJam_Kerja = 0.6f;
 }  else if (JamKerja == "550-699 Jam") {
        NilaiJam_Kerja = 0.8f;
 }  else if (JamKerja == ">=700 Jam") {
        NilaiJam_Kerja = 1f;
 }
txtnilai_jam.setText(String.valueOf(NilaiJam_Kerja));
txtnilai_jam.setForeground(Color.BLACK);

    }//GEN-LAST:event_cmbjam_kerjaActionPerformed

    private void cmbabsensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbabsensiActionPerformed
        Absensi = (String) cmbabsensi.getSelectedItem();
   if (Absensi== "--Absensi--") {
        NilaiAbsensi= 0f;
 } else if (Absensi == "70<= Hadir") {
        NilaiAbsensi = 0.2f;
 }  else if (Absensi == "71-77 Hadir") {
        NilaiAbsensi = 0.4f;
 }  else if (Absensi == "78-84 Hadir") {
        NilaiAbsensi = 0.6f;
 }  else if (Absensi == "85-94 Hadir") {
        NilaiAbsensi = 0.8f;
 }  else if (Absensi == ">=95 Hadir") {
       NilaiAbsensi = 1f;
 }
   txtnilai_absensi.setText(String.valueOf(NilaiAbsensi));
    txtnilai_absensi.setForeground(Color.BLACK);
    }//GEN-LAST:event_cmbabsensiActionPerformed

    private void cmbkerapihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbkerapihanActionPerformed
      Kerapihan = (String) cmbkerapihan.getSelectedItem();
   if (Kerapihan== "--Kerapihan--") {
        NilaiKerapihan = 0f;
 } else if (Kerapihan == "70<=") {
        NilaiKerapihan = 0.2f;
 }  else if (Kerapihan == "71-77") {
        NilaiKerapihan = 0.4f;
 }  else if (Kerapihan == "78-84") {
        NilaiKerapihan = 0.6f;
 }  else if (Kerapihan == "85-94") {
        NilaiKerapihan = 0.8f;
 }  else if (Kerapihan == ">=95") {
       NilaiKerapihan = 1f;
 }
   txtnilai_kerapihan.setText(String.valueOf(NilaiKerapihan));
    txtnilai_kerapihan.setForeground(Color.BLACK);
    }//GEN-LAST:event_cmbkerapihanActionPerformed

    private void cmbketerlambatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbketerlambatanActionPerformed
       Keterlambatan = (String) cmbketerlambatan.getSelectedItem();
   if (Keterlambatan== "--Keterlambatan--") {
        NilaiKeterlambatan = 0f;
 } else if (Keterlambatan == ">=14 Telat") {
        NilaiKeterlambatan = 0.2f;
 }  else if (Keterlambatan == "11-13 Telat") {
        NilaiKeterlambatan = 0.4f;
 }  else if (Keterlambatan == "8-10 Telat") {
        NilaiKeterlambatan = 0.6f;
 }  else if (Keterlambatan == "5-7 Telat") {
        NilaiKeterlambatan = 0.8f;
 }  else if (Keterlambatan == "3<= Telat") {
       NilaiKeterlambatan = 1f;
 }
   txtnilai_keterlambatan.setText(String.valueOf(NilaiKeterlambatan));
    txtnilai_keterlambatan.setForeground(Color.BLACK);
    }//GEN-LAST:event_cmbketerlambatanActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        try{
            String sql1 = "select * from penilaian_karyawan where nik= '"+txtnik.getText()+"'";
            Statement stat1 = conn.createStatement();
            ResultSet hasil = stat1.executeQuery(sql1);
            if (hasil.first()) {
                JOptionPane.showMessageDialog(null, "Data Karyawan Sudah Ada");
            } else {
            
            String sql = "insert into penilaian_karyawan values (?,?,?,?,?,?)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtid.getText());
            stat.setString(2, txtnik.getText());
            stat.setString(3, cmbjam_kerja.getSelectedItem().toString());
            stat.setString(4, cmbabsensi.getSelectedItem().toString());
            stat.setString(5, cmbkerapihan.getSelectedItem().toString());
            stat.setString(6, cmbketerlambatan.getSelectedItem().toString());
            stat.executeUpdate();
            txtid.requestFocus();
        
            String sql2 = "insert into rating_kecocokan values (?,?,?,?,?,?)";
            PreparedStatement stat3 = conn.prepareStatement(sql2);
            stat3.setString(1, txtid.getText());
            stat3.setString(2, txtnik.getText());
            stat3.setString(3, txtnilai_jam.getText());
            stat3.setString(4, txtnilai_absensi.getText());
            stat3.setString(5, txtnilai_kerapihan.getText());
            stat3.setString(6, txtnilai_keterlambatan.getText());
            stat3.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }  
        bersih();
        datatabel();
        IDOtomatis();
        normalisasi();;
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnbersihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbersihActionPerformed
        bersih();
    }//GEN-LAST:event_btnbersihActionPerformed

    private void txtcariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcariFocusGained
        if(txtcari.getText().equals("Cari Data Penilaian"))
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
            txtcari.setText("Cari Data Penilaian");
        }
    }//GEN-LAST:event_txtcariFocusLost

    private void btncetak_nilaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetak_nilaiActionPerformed
        try {
            File file = new File("src/laporan/Laporan_Nilai.jrxml");
            JasDes = JRXmlLoader.load(file);
            param.clear();
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPrin = JasperFillManager.fillReport(JasRep, param, new koneksi().connect());
            JasperViewer.viewReport(JasPrin, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btncetak_nilaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Desain.Button btcetak_penilaian;
    private Desain.Button bthapus;
    private Desain.Button btnbersih;
    private Desain.Button btncari;
    private Desain.Button btncetak_nilai;
    private Desain.Button btnsimpan;
    private Desain.Button btnubah;
    private Desain.ComboBoxSuggestion cmbabsensi;
    private Desain.ComboBoxSuggestion cmbjam_kerja;
    private Desain.ComboBoxSuggestion cmbkerapihan;
    private Desain.ComboBoxSuggestion cmbketerlambatan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblabsensi;
    private javax.swing.JLabel lblalamat;
    private javax.swing.JLabel lbldata_penilaian;
    private javax.swing.JLabel lblid;
    private javax.swing.JLabel lbljabatan;
    private javax.swing.JLabel lblketerlambatan;
    private javax.swing.JLabel lblnik;
    private javax.swing.JLabel lblnik1;
    private Desain.RoundPanel rpinput;
    private Desain.RoundPanel rpsearch;
    public static volatile table.Table tblpenilaian;
    private Desain.RoundPanel tptabel;
    private javax.swing.JTextField txtcari;
    private Desain.TextFieldSuggestion txtid;
    private Desain.TextFieldSuggestion txtnama;
    private Desain.TextFieldSuggestion txtnik;
    private Desain.TextFieldSuggestion txtnilai_absensi;
    private Desain.TextFieldSuggestion txtnilai_jam;
    private Desain.TextFieldSuggestion txtnilai_kerapihan;
    private Desain.TextFieldSuggestion txtnilai_keterlambatan;
    // End of variables declaration//GEN-END:variables
}
