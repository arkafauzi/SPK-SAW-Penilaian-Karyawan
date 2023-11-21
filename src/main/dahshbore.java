package main;
import java.awt.Color;
import javax.swing.JScrollBar;
import Desain.ScrollBarCustom;
import com.sun.glass.events.KeyEvent;
import java.sql.*;
import java.awt.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import koneksi.koneksi;

/*import static main.penilaian.tblpenilaian;
import static main.perengkingan.tbl_normalisasi;
import static main.perengkingan.tbl_peringkat;*/

public class dahshbore extends javax.swing.JPanel {
    private Connection conn = new koneksi().connect();
     DefaultTableModel tabmode;
     
    public dahshbore() {
        initComponents();
        datatabel();
    }

 protected void datatabel(){              
        Object[] Baris = {" Karyawan","Jenis Kelamin","Jabatan","Nilai","Rangking"};
        tabmode = new DefaultTableModel(null, Baris);
//        tbl_dashbore.setModel(tabmode);
        String sql = "select * from karyawan, jabatan where karyawan.id_karyawan = jabatan.id_karyawan ";
 
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nama");
                String b = hasil.getString("jenis_kelamin");
                String c = hasil.getString("jabatan");
             //   String d = hasil.getString("nilai");
                String[] data = {a,b,c};
                tabmode.addRow(data);
            }
        }catch (Exception e){
        }
        int b = tabmode.getRowCount();
        //lbltotal.setText(""+b);    //label total karyawan yg diform karyawan,total data didatbel dipanggil ke label total
     //   lbltotal_karyawan.setText(""+b); //label total karyawan yg dimenu utama,total data didata tabel dipanggil ke label total
    }   
      //
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new table.Table();
        rpkaryawan = new Desain.RoundPanel();
        lbldata_karyawan = new javax.swing.JLabel();
        lbljumlah_karyawan = new javax.swing.JLabel();
        rpjabatan = new Desain.RoundPanel();
        lbldata_karyawan4 = new javax.swing.JLabel();
        lbldata_karyawan5 = new javax.swing.JLabel();
        rppenilaian = new Desain.RoundPanel();
        lbldata_karyawan6 = new javax.swing.JLabel();
        lbljumlah_penilaian = new javax.swing.JLabel();
        rpkriteria = new Desain.RoundPanel();
        lbldata_karyawan8 = new javax.swing.JLabel();
        lbldata_karyawan9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(248, 248, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1215, 726));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Jam Kerja", "Nilai ", "", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table1);

        rpkaryawan.setBackground(new java.awt.Color(255, 255, 255));
        rpkaryawan.setRound(20);

        lbldata_karyawan.setBackground(new java.awt.Color(204, 204, 204));
        lbldata_karyawan.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbldata_karyawan.setText("Total Karyawan");

        lbljumlah_karyawan.setBackground(new java.awt.Color(204, 204, 204));
        lbljumlah_karyawan.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbljumlah_karyawan.setForeground(new java.awt.Color(102, 102, 102));
        lbljumlah_karyawan.setText("0");

        javax.swing.GroupLayout rpkaryawanLayout = new javax.swing.GroupLayout(rpkaryawan);
        rpkaryawan.setLayout(rpkaryawanLayout);
        rpkaryawanLayout.setHorizontalGroup(
            rpkaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpkaryawanLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(lbldata_karyawan)
                .addGap(30, 30, 30))
            .addGroup(rpkaryawanLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(lbljumlah_karyawan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rpkaryawanLayout.setVerticalGroup(
            rpkaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpkaryawanLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbldata_karyawan)
                .addGap(18, 18, 18)
                .addComponent(lbljumlah_karyawan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rpjabatan.setBackground(new java.awt.Color(255, 255, 255));
        rpjabatan.setRound(20);

        lbldata_karyawan4.setBackground(new java.awt.Color(204, 204, 204));
        lbldata_karyawan4.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbldata_karyawan4.setText("Total Jabatan");

        lbldata_karyawan5.setBackground(new java.awt.Color(204, 204, 204));
        lbldata_karyawan5.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbldata_karyawan5.setForeground(new java.awt.Color(102, 102, 102));
        lbldata_karyawan5.setText("3");

        javax.swing.GroupLayout rpjabatanLayout = new javax.swing.GroupLayout(rpjabatan);
        rpjabatan.setLayout(rpjabatanLayout);
        rpjabatanLayout.setHorizontalGroup(
            rpjabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpjabatanLayout.createSequentialGroup()
                .addGroup(rpjabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rpjabatanLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(lbldata_karyawan5))
                    .addGroup(rpjabatanLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lbldata_karyawan4)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        rpjabatanLayout.setVerticalGroup(
            rpjabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpjabatanLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbldata_karyawan4)
                .addGap(18, 18, 18)
                .addComponent(lbldata_karyawan5)
                .addGap(15, 15, 15))
        );

        rppenilaian.setBackground(new java.awt.Color(255, 255, 255));
        rppenilaian.setRound(20);

        lbldata_karyawan6.setBackground(new java.awt.Color(204, 204, 204));
        lbldata_karyawan6.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbldata_karyawan6.setText("Total Penilaian");

        lbljumlah_penilaian.setBackground(new java.awt.Color(204, 204, 204));
        lbljumlah_penilaian.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbljumlah_penilaian.setForeground(new java.awt.Color(102, 102, 102));
        lbljumlah_penilaian.setText("0");

        javax.swing.GroupLayout rppenilaianLayout = new javax.swing.GroupLayout(rppenilaian);
        rppenilaian.setLayout(rppenilaianLayout);
        rppenilaianLayout.setHorizontalGroup(
            rppenilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rppenilaianLayout.createSequentialGroup()
                .addGroup(rppenilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rppenilaianLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lbldata_karyawan6))
                    .addGroup(rppenilaianLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(lbljumlah_penilaian)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        rppenilaianLayout.setVerticalGroup(
            rppenilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rppenilaianLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbldata_karyawan6)
                .addGap(18, 18, 18)
                .addComponent(lbljumlah_penilaian)
                .addGap(15, 15, 15))
        );

        rpkriteria.setBackground(new java.awt.Color(255, 255, 255));
        rpkriteria.setRound(20);

        lbldata_karyawan8.setBackground(new java.awt.Color(204, 204, 204));
        lbldata_karyawan8.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbldata_karyawan8.setText("Total Kriteria");

        lbldata_karyawan9.setBackground(new java.awt.Color(204, 204, 204));
        lbldata_karyawan9.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbldata_karyawan9.setForeground(new java.awt.Color(102, 102, 102));
        lbldata_karyawan9.setText("4");

        javax.swing.GroupLayout rpkriteriaLayout = new javax.swing.GroupLayout(rpkriteria);
        rpkriteria.setLayout(rpkriteriaLayout);
        rpkriteriaLayout.setHorizontalGroup(
            rpkriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpkriteriaLayout.createSequentialGroup()
                .addGroup(rpkriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rpkriteriaLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lbldata_karyawan8))
                    .addGroup(rpkriteriaLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(lbldata_karyawan9)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        rpkriteriaLayout.setVerticalGroup(
            rpkriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rpkriteriaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbldata_karyawan8)
                .addGap(18, 18, 18)
                .addComponent(lbldata_karyawan9)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rpkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(rpjabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80)
                .addComponent(rppenilaian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(rpkriteria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rpkriteria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rppenilaian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rpjabatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rpkaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbldata_karyawan;
    private javax.swing.JLabel lbldata_karyawan4;
    private javax.swing.JLabel lbldata_karyawan5;
    private javax.swing.JLabel lbldata_karyawan6;
    private javax.swing.JLabel lbldata_karyawan8;
    private javax.swing.JLabel lbldata_karyawan9;
    public static volatile javax.swing.JLabel lbljumlah_karyawan;
    public static volatile javax.swing.JLabel lbljumlah_penilaian;
    private Desain.RoundPanel rpjabatan;
    private Desain.RoundPanel rpkaryawan;
    private Desain.RoundPanel rpkriteria;
    private Desain.RoundPanel rppenilaian;
    private table.Table table1;
    // End of variables declaration//GEN-END:variables
}
