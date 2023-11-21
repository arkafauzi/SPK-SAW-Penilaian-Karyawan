package main;
import java.awt.Color;
import Desain.ScrollBarCustom;
import javax.swing.JScrollBar;
import static main.perengkingan.tblnormalisasi;
import static main.perengkingan.tblperingkat;
public class about extends javax.swing.JPanel {
    public about() {
        initComponents();
     costumtabel();
    }
      private void costumtabel(){
        jsp_about.setVerticalScrollBar(new ScrollBarCustom());
        ScrollBarCustom pr = new ScrollBarCustom();
        pr.setOrientation(JScrollBar.HORIZONTAL);
        jsp_about.setHorizontalScrollBar(pr);   
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Desain.RoundPanel();
        jsp_about = new javax.swing.JScrollPane();
        aditorpane_about = new javax.swing.JEditorPane();

        setBackground(new java.awt.Color(248, 248, 255));
        setPreferredSize(new java.awt.Dimension(1215, 726));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setPreferredSize(new java.awt.Dimension(965, 580));
        roundPanel1.setRound(20);

        jsp_about.setBorder(null);

        aditorpane_about.setText("Metode SAW (Simple Additive Weighting)\nMetode Simple Additive Weighting (SAW) dikenal dengan istilah metode penjumlahan terbobot. Konsep dasar pada metode SAW adalah mencari penjumlahan\nterbobot dari rating kinerja pada setiap alternatif di semua atribut. Metode SAW membutuhkan proses normalisasi matriks keputusan (X) \nke suatu skala yang dapat diperbandingkan dengan semua rating alternatif yang ada.\n\nLogin\n1) Jika lupa password atau ingin mengubah password, klik yang bertuliskan “Lupa Password ?”\n    maka akan menampilkan form ubah password. Jika tidak, maka akan tetap berada diform login.\n2) Masukan username dan password.\n3) Klik tombol login, jika username atau password salah maka akan tampil berupa pesan  “Username atau Password salah”.\n    Jika username dan password benar, maka akan maka akan tampil berupa pesan “Login Berhasil” dan klik ok maka akan menampilkan menu utama.\n\nUbah Password\n1) Jika ingin login klik yang bertuliskan “Lupa Password ?” maka akan menampilkan form login. Jika tidak, maka akan tetap berada diform ubah password.\n2) Masukan username dan password.\n3) Klik tombol ubah password, jika username salah maka akan tampil berupa pesan  “Username salah” atau “Username hampir benar”.\n    Jika username benar, maka akan maka akan tampil berupa pesan “Password berhasil diubah” \n\nDashbord\nSetelah login, akan menampilkan form menu utama dan pertama kali yang tampil adalah dashbord. Didalam dashboard terdapat total karyawan, total jabatan,\n total penilaian dan total kriteria.\n\nKaryawan\n1) Input data karyawan mulai dari nik karyawan, nama, alamat, jenis kelamin, jabatan.\n2) Jika data sudah diisi keseluruhan, selanjutnya klik simpan. Maka tampil notif yang berisikan “Data Berhasil Disimpan”. \n3) Data karyawan akan tampil didalam tabel jika data karyawan sudah diinput.\n4) Jika ingin mengubah data karyawan, klik salah satu data karyawan yang berada didalam tabel. Jika sudah diklik maka akan berpindah ke dalam input, jika data sudah\n    diubah lalu klik ubah. Button ubah akan menampilkan pesan yang berisi ingin mengubah data atau tidak. Jika klik yes maka data karyawan akan berubah, jika no\n    maka data karyawan tidak terubah.\n5) Jika ingin menghapus data karyawan, klik salah satu data karyawan yang berada didalam tabel. Jika sudah diklik maka akan berpindah ke dalam input lalu klik hapus. \n     Button hapus akan menampilkan pesan yang berisi ingin menghapus data atau tidak. Jika klik yes maka data karyawan berhasil dihapus, jika no maka data karyawan \n     tidak terhapus\n6. Jika ingin menghapus keseluruhan data yang berada didalam input, klik bersih.\n7. Apabila ingin mencari data karyawan. input data karyawan maka data akan tampil didalam tabel\n8. Jika ingin mencetak data karyawan, klik cetak\n\nPenilaian\n1) Klik button cari. Maka akan menampilkan data karyawan yang sudah diinput. Klik salah satu data karyawan yang didalam tabel.\n2) Input data penilaian mulai dari nik karyawan dan nama karyawan yang sudah dicari datanya, jam kerja, absensi, kerapihan, keterlambatan.\n3) Jika data sudah diisi keseluruhan, selanjutnya klik tombol simpan. Maka tampil pesan yang berisikan “Data Berhasil Disimpan”. \n4) Data penilaian akan tampil didalam tabel jika data penilaian sudah diinput.\n5) Jika ingin mengubah data penilaian, klik salah satu data penilaian yang berada didalam tabel. Jika sudah diklik maka akan berpindah ke dalam input, jika data sudah\n    diubah lalu klik tombol ubah. tombol ubah akan menampilkan pesan yang berisi ingin mengubah data atau tidak. Jika klik yes maka data penilaian akan berubah, jika \n    no maka data penilaian tidak terubah.\n6) Jika ingin menghapus data penilaian, klik salah satu data penilaian yang berada didalam tabel. Jika sudah diklik maka akan berpindah ke dalam input lalu klik tombol \n    hapus. Tombol hapus akan menampilkan pesan yang berisi ingin menghapus data atau tidak. Jika klik yes maka data penilaian berhasil dihapus, jika no maka data \n    penilaian tidak terhapus\n7. Jika ingin menghapus keseluruhan data yang berada didalam input, klik bersih.\n8. Apabila ingin mencari data penilaian. Input data penilaian maka data akan tampil didalam tabel\n9. Jika ingin mencetak data penilaian, klik tombol cetak penilaian. Apabila ingin mencetak data nilai, klik tombol cetak nilai. \n\nRangking\nDidalam from rangking terdapat dua tabel yaitu tabel normalisasi dan peringkat. Untuk mencari nilai peringkat karyawan, klik tombok cari peringkat maka nik, \nnama karyawan dan nilai akan tampil didalam tabel peringkat.Nilai yang tertinggi berada diurutan atas maka bisa menjadi salah satu acuan untuk menjadi \nkaryawan terbaik. Jika ingin mencetak peringkat klik tombol cetak.\n\nAbout\nPenjesalan tentang metode saw dan tata cara penggunaan program.\n\nLogout\nJika ingin logout dari program, klik panel yang bertuliskan “Logout” maka from menu utama tertutup dan akan menampilkan form login.");
        jsp_about.setViewportView(aditorpane_about);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsp_about, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsp_about, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 61, -1, 610));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane aditorpane_about;
    private javax.swing.JScrollPane jsp_about;
    private Desain.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
