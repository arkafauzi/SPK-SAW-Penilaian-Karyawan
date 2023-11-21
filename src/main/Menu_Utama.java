package main;
import java.sql.ResultSet;
import java.util.Locale;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JScrollBar;
import Desain.ModernScrollBarUI;
import Desain.ScrollBarCustom;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import koneksi.koneksi;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Menu_Utama extends javax.swing.JFrame {
   private Connection conn = new koneksi().connect();
 //fotm
 dahshbore de;
 karyawan kr;
 penilaian pn;
 perengkingan pr;
 about at;
 //
 
  public String lap;
    JasperReport JasRep;
    JasperPrint JasPrin;
    Map param = new HashMap();
    JasperDesign JasDes;
    DefaultTableModel tabmode;
    
    public Menu_Utama() {
        initComponents();
        icontrue_false();// icon bersifat true atau false saam membuka form menu
        costumscroll_tabel();//costum scroll tabel
        panel_form();// void
    }
   
    //jpanel form 
    public void panel_form(){
        //jpanel form namanya diubah menjadi variabel
        de = new dahshbore();
        kr = new karyawan();
        pn = new penilaian();
        pr = new perengkingan();
        at = new about();
        //memnanggil jpanel form ke panelyang sudah diubah menjadi variabel
        panelbody.add(de);
        panelbody.add(kr);
        panelbody.add(pn);
        panelbody.add(pr);
        panelbody.add(at);
        // variabel jpanel form awalan saat membuka form menu
        de.setVisible(true);
        kr.setVisible(false);
        pn.setVisible(false);
        pr.setVisible(false);
        at.setVisible(false);
    }
    //costum scroll tabel
    public void costumscroll_tabel(){
       jspbody.setVerticalScrollBar(new ScrollBarCustom());      
       ScrollBarCustom body = new ScrollBarCustom();      
       body.setOrientation(JScrollBar.HORIZONTAL);
       jspbody.setHorizontalScrollBar(body);
    }
    
    //icon true and false saat awal nampil
    public void icontrue_false (){
        lblicon_dashbore_abu.setVisible(false);
        lblicon_dashbore_biru.setVisible(true);
        lblicon_karyawan_abu.setVisible(true);
        lblicon_karyawan_biru.setVisible(false);
        lblicon_penilaian_abu.setVisible(true);
        lblicon_penilaian_biru.setVisible(false);
        lblicon_rangking_abu.setVisible(true);
        lblicon_rangking_biru.setVisible(false);
        lblicon_about_abu.setVisible(true);
        lblicon_about_biru.setVisible(false);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_menu = new javax.swing.JPanel();
        jspbody = new javax.swing.JScrollPane();
        panelbody = new javax.swing.JPanel();
        jspitem = new javax.swing.JScrollPane();
        panel_kiri = new javax.swing.JPanel();
        btndashbord = new javax.swing.JPanel();
        lbldashbord = new javax.swing.JLabel();
        lblicon_dashbore_abu = new javax.swing.JLabel();
        lblicon_dashbore_biru = new javax.swing.JLabel();
        pnldashbord = new javax.swing.JPanel();
        btnkaryawan = new javax.swing.JPanel();
        lblkaryawan = new javax.swing.JLabel();
        pnlkaryawan = new javax.swing.JPanel();
        lblicon_karyawan_abu = new javax.swing.JLabel();
        lblicon_karyawan_biru = new javax.swing.JLabel();
        btnrangking = new javax.swing.JPanel();
        pnlrangking = new javax.swing.JPanel();
        lblrangking = new javax.swing.JLabel();
        lblicon_rangking_abu = new javax.swing.JLabel();
        lblicon_rangking_biru = new javax.swing.JLabel();
        btnpenilaian = new javax.swing.JPanel();
        lblpenilaian = new javax.swing.JLabel();
        pnlpenilaian = new javax.swing.JPanel();
        lblicon_penilaian_abu = new javax.swing.JLabel();
        lblicon_penilaian_biru = new javax.swing.JLabel();
        lbl_xy1 = new javax.swing.JLabel();
        usernamee = new javax.swing.JLabel();
        btnabout = new javax.swing.JPanel();
        pnlabout = new javax.swing.JPanel();
        lblabout = new javax.swing.JLabel();
        lblicon_about_abu = new javax.swing.JLabel();
        lblicon_about_biru = new javax.swing.JLabel();
        btnlogout = new javax.swing.JPanel();
        pnllogout = new javax.swing.JPanel();
        lbllogout = new javax.swing.JLabel();
        lblicon_logout_abu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistem Penilaian Karyawan");
        setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1340, 740));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panel_menu.setBackground(new java.awt.Color(248, 248, 255));
        panel_menu.setMinimumSize(new java.awt.Dimension(1200, 650));
        panel_menu.setPreferredSize(new java.awt.Dimension(1900, 1200));
        panel_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jspbody.setBorder(null);
        jspbody.setMinimumSize(new java.awt.Dimension(1300, 700));

        panelbody.setBackground(new java.awt.Color(204, 0, 204));
        panelbody.setMinimumSize(new java.awt.Dimension(1300, 900));
        panelbody.setLayout(new javax.swing.BoxLayout(panelbody, javax.swing.BoxLayout.LINE_AXIS));
        jspbody.setViewportView(panelbody);

        panel_menu.add(jspbody, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 0, 1170, 1500));

        jspitem.setBorder(null);
        jspitem.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jspitem.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        panel_kiri.setBackground(new java.awt.Color(255, 255, 255));
        panel_kiri.setMinimumSize(new java.awt.Dimension(195, 410));
        panel_kiri.setName(""); // NOI18N
        panel_kiri.setPreferredSize(new java.awt.Dimension(300, 3000));

        btndashbord.setBackground(new java.awt.Color(255, 255, 255));
        btndashbord.setPreferredSize(new java.awt.Dimension(118, 40));
        btndashbord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndashbordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btndashbordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btndashbordMouseExited(evt);
            }
        });

        lbldashbord.setBackground(new java.awt.Color(0, 0, 0));
        lbldashbord.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbldashbord.setForeground(new java.awt.Color(0, 194, 255));
        lbldashbord.setText("Dashbord");

        lblicon_dashbore_abu.setBackground(new java.awt.Color(0, 0, 0));
        lblicon_dashbore_abu.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblicon_dashbore_abu.setForeground(new java.awt.Color(153, 153, 153));
        lblicon_dashbore_abu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Dashbore abu-abu.png"))); // NOI18N

        lblicon_dashbore_biru.setBackground(new java.awt.Color(0, 0, 0));
        lblicon_dashbore_biru.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblicon_dashbore_biru.setForeground(new java.awt.Color(153, 153, 153));
        lblicon_dashbore_biru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dashboard biru.png"))); // NOI18N

        pnldashbord.setBackground(new java.awt.Color(0, 194, 255));
        pnldashbord.setPreferredSize(new java.awt.Dimension(6, 30));

        javax.swing.GroupLayout pnldashbordLayout = new javax.swing.GroupLayout(pnldashbord);
        pnldashbord.setLayout(pnldashbordLayout);
        pnldashbordLayout.setHorizontalGroup(
            pnldashbordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnldashbordLayout.setVerticalGroup(
            pnldashbordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btndashbordLayout = new javax.swing.GroupLayout(btndashbord);
        btndashbord.setLayout(btndashbordLayout);
        btndashbordLayout.setHorizontalGroup(
            btndashbordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btndashbordLayout.createSequentialGroup()
                .addComponent(pnldashbord, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(btndashbordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblicon_dashbore_abu)
                    .addComponent(lblicon_dashbore_biru))
                .addGap(8, 8, 8)
                .addComponent(lbldashbord)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        btndashbordLayout.setVerticalGroup(
            btndashbordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btndashbordLayout.createSequentialGroup()
                .addGroup(btndashbordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnldashbord, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(btndashbordLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(btndashbordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblicon_dashbore_abu)
                            .addComponent(lblicon_dashbore_biru)
                            .addComponent(lbldashbord))))
                .addGap(51, 51, 51))
        );

        btnkaryawan.setBackground(new java.awt.Color(255, 255, 255));
        btnkaryawan.setPreferredSize(new java.awt.Dimension(118, 40));
        btnkaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnkaryawanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnkaryawanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnkaryawanMouseExited(evt);
            }
        });
        btnkaryawan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblkaryawan.setBackground(new java.awt.Color(0, 0, 0));
        lblkaryawan.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblkaryawan.setForeground(new java.awt.Color(153, 153, 153));
        lblkaryawan.setText("Karyawan");
        btnkaryawan.add(lblkaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 10, -1, -1));

        pnlkaryawan.setBackground(new java.awt.Color(0, 194, 255));
        pnlkaryawan.setOpaque(false);
        pnlkaryawan.setPreferredSize(new java.awt.Dimension(6, 30));

        javax.swing.GroupLayout pnlkaryawanLayout = new javax.swing.GroupLayout(pnlkaryawan);
        pnlkaryawan.setLayout(pnlkaryawanLayout);
        pnlkaryawanLayout.setHorizontalGroup(
            pnlkaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnlkaryawanLayout.setVerticalGroup(
            pnlkaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btnkaryawan.add(pnlkaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, 40));

        lblicon_karyawan_abu.setBackground(new java.awt.Color(0, 0, 0));
        lblicon_karyawan_abu.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblicon_karyawan_abu.setForeground(new java.awt.Color(153, 153, 153));
        lblicon_karyawan_abu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-employee-19.png"))); // NOI18N
        btnkaryawan.add(lblicon_karyawan_abu, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 10, -1, -1));

        lblicon_karyawan_biru.setBackground(new java.awt.Color(0, 0, 0));
        lblicon_karyawan_biru.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblicon_karyawan_biru.setForeground(new java.awt.Color(153, 153, 153));
        lblicon_karyawan_biru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-employee-19 buru.png"))); // NOI18N
        btnkaryawan.add(lblicon_karyawan_biru, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 10, -1, -1));

        btnrangking.setBackground(new java.awt.Color(255, 255, 255));
        btnrangking.setPreferredSize(new java.awt.Dimension(118, 40));
        btnrangking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnrangkingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnrangkingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnrangkingMouseExited(evt);
            }
        });
        btnrangking.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlrangking.setBackground(new java.awt.Color(0, 194, 255));
        pnlrangking.setOpaque(false);
        pnlrangking.setPreferredSize(new java.awt.Dimension(6, 30));

        javax.swing.GroupLayout pnlrangkingLayout = new javax.swing.GroupLayout(pnlrangking);
        pnlrangking.setLayout(pnlrangkingLayout);
        pnlrangkingLayout.setHorizontalGroup(
            pnlrangkingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnlrangkingLayout.setVerticalGroup(
            pnlrangkingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btnrangking.add(pnlrangking, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, 40));

        lblrangking.setBackground(new java.awt.Color(0, 0, 0));
        lblrangking.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblrangking.setForeground(new java.awt.Color(153, 153, 153));
        lblrangking.setText("Rangking");
        btnrangking.add(lblrangking, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 11, -1, -1));

        lblicon_rangking_abu.setBackground(new java.awt.Color(255, 0, 0));
        lblicon_rangking_abu.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblicon_rangking_abu.setForeground(new java.awt.Color(153, 153, 153));
        lblicon_rangking_abu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/win abu.png"))); // NOI18N
        btnrangking.add(lblicon_rangking_abu, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 11, -1, -1));

        lblicon_rangking_biru.setBackground(new java.awt.Color(255, 0, 0));
        lblicon_rangking_biru.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblicon_rangking_biru.setForeground(new java.awt.Color(153, 153, 153));
        lblicon_rangking_biru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-medal-first-place-18.png"))); // NOI18N
        btnrangking.add(lblicon_rangking_biru, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 11, -1, -1));

        btnpenilaian.setBackground(new java.awt.Color(255, 255, 255));
        btnpenilaian.setPreferredSize(new java.awt.Dimension(118, 40));
        btnpenilaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnpenilaianMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnpenilaianMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnpenilaianMouseExited(evt);
            }
        });

        lblpenilaian.setBackground(new java.awt.Color(0, 0, 0));
        lblpenilaian.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblpenilaian.setForeground(new java.awt.Color(153, 153, 153));
        lblpenilaian.setText("Penilaian");

        pnlpenilaian.setBackground(new java.awt.Color(0, 194, 255));
        pnlpenilaian.setOpaque(false);
        pnlpenilaian.setPreferredSize(new java.awt.Dimension(6, 30));

        javax.swing.GroupLayout pnlpenilaianLayout = new javax.swing.GroupLayout(pnlpenilaian);
        pnlpenilaian.setLayout(pnlpenilaianLayout);
        pnlpenilaianLayout.setHorizontalGroup(
            pnlpenilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnlpenilaianLayout.setVerticalGroup(
            pnlpenilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        lblicon_penilaian_abu.setBackground(new java.awt.Color(0, 0, 0));
        lblicon_penilaian_abu.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblicon_penilaian_abu.setForeground(new java.awt.Color(153, 153, 153));
        lblicon_penilaian_abu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/master abu-abu.png"))); // NOI18N

        lblicon_penilaian_biru.setBackground(new java.awt.Color(0, 0, 0));
        lblicon_penilaian_biru.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblicon_penilaian_biru.setForeground(new java.awt.Color(153, 153, 153));
        lblicon_penilaian_biru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/master biru.png"))); // NOI18N

        javax.swing.GroupLayout btnpenilaianLayout = new javax.swing.GroupLayout(btnpenilaian);
        btnpenilaian.setLayout(btnpenilaianLayout);
        btnpenilaianLayout.setHorizontalGroup(
            btnpenilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnpenilaianLayout.createSequentialGroup()
                .addComponent(pnlpenilaian, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(btnpenilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblicon_penilaian_biru)
                    .addComponent(lblicon_penilaian_abu))
                .addGap(6, 6, 6)
                .addComponent(lblpenilaian))
        );
        btnpenilaianLayout.setVerticalGroup(
            btnpenilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlpenilaian, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(btnpenilaianLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(btnpenilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblicon_penilaian_biru)
                    .addComponent(lblicon_penilaian_abu)
                    .addComponent(lblpenilaian)))
        );

        lbl_xy1.setBackground(new java.awt.Color(0, 0, 0));
        lbl_xy1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lbl_xy1.setForeground(new java.awt.Color(0, 194, 255));
        lbl_xy1.setText("SPK");

        usernamee.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        usernamee.setForeground(new java.awt.Color(102, 102, 102));
        usernamee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernamee.setText("USERNAME");

        btnabout.setBackground(new java.awt.Color(255, 255, 255));
        btnabout.setPreferredSize(new java.awt.Dimension(118, 40));
        btnabout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnaboutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnaboutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnaboutMouseExited(evt);
            }
        });

        pnlabout.setBackground(new java.awt.Color(0, 194, 255));
        pnlabout.setOpaque(false);
        pnlabout.setPreferredSize(new java.awt.Dimension(6, 30));

        javax.swing.GroupLayout pnlaboutLayout = new javax.swing.GroupLayout(pnlabout);
        pnlabout.setLayout(pnlaboutLayout);
        pnlaboutLayout.setHorizontalGroup(
            pnlaboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnlaboutLayout.setVerticalGroup(
            pnlaboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        lblabout.setBackground(new java.awt.Color(0, 0, 0));
        lblabout.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblabout.setForeground(new java.awt.Color(153, 153, 153));
        lblabout.setText("About");

        lblicon_about_abu.setBackground(new java.awt.Color(255, 0, 0));
        lblicon_about_abu.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblicon_about_abu.setForeground(new java.awt.Color(153, 153, 153));
        lblicon_about_abu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-about-19 abu.png"))); // NOI18N

        lblicon_about_biru.setBackground(new java.awt.Color(255, 0, 0));
        lblicon_about_biru.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblicon_about_biru.setForeground(new java.awt.Color(153, 153, 153));
        lblicon_about_biru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-about-19 biru.png"))); // NOI18N

        javax.swing.GroupLayout btnaboutLayout = new javax.swing.GroupLayout(btnabout);
        btnabout.setLayout(btnaboutLayout);
        btnaboutLayout.setHorizontalGroup(
            btnaboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnaboutLayout.createSequentialGroup()
                .addComponent(pnlabout, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(btnaboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblicon_about_abu)
                    .addComponent(lblicon_about_biru))
                .addGap(10, 10, 10)
                .addComponent(lblabout))
        );
        btnaboutLayout.setVerticalGroup(
            btnaboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlabout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(btnaboutLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(btnaboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblicon_about_abu)
                    .addComponent(lblicon_about_biru)
                    .addComponent(lblabout)))
        );

        btnlogout.setBackground(new java.awt.Color(255, 255, 255));
        btnlogout.setPreferredSize(new java.awt.Dimension(118, 40));
        btnlogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnlogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnlogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnlogoutMouseExited(evt);
            }
        });

        pnllogout.setBackground(new java.awt.Color(0, 194, 255));
        pnllogout.setOpaque(false);
        pnllogout.setPreferredSize(new java.awt.Dimension(6, 30));

        javax.swing.GroupLayout pnllogoutLayout = new javax.swing.GroupLayout(pnllogout);
        pnllogout.setLayout(pnllogoutLayout);
        pnllogoutLayout.setHorizontalGroup(
            pnllogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnllogoutLayout.setVerticalGroup(
            pnllogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        lbllogout.setBackground(new java.awt.Color(0, 0, 0));
        lbllogout.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbllogout.setForeground(new java.awt.Color(153, 153, 153));
        lbllogout.setText("Logout");

        lblicon_logout_abu.setBackground(new java.awt.Color(255, 0, 0));
        lblicon_logout_abu.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblicon_logout_abu.setForeground(new java.awt.Color(153, 153, 153));
        lblicon_logout_abu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-logout-23.png"))); // NOI18N

        javax.swing.GroupLayout btnlogoutLayout = new javax.swing.GroupLayout(btnlogout);
        btnlogout.setLayout(btnlogoutLayout);
        btnlogoutLayout.setHorizontalGroup(
            btnlogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnlogoutLayout.createSequentialGroup()
                .addComponent(pnllogout, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(lblicon_logout_abu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbllogout)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        btnlogoutLayout.setVerticalGroup(
            btnlogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnllogout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(btnlogoutLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(btnlogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblicon_logout_abu)
                    .addComponent(lbllogout)))
        );

        javax.swing.GroupLayout panel_kiriLayout = new javax.swing.GroupLayout(panel_kiri);
        panel_kiri.setLayout(panel_kiriLayout);
        panel_kiriLayout.setHorizontalGroup(
            panel_kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnpenilaian, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(btnabout, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(panel_kiriLayout.createSequentialGroup()
                .addGroup(panel_kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnrangking, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_kiriLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(lbl_xy1))
                        .addComponent(btnkaryawan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btndashbord, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addComponent(usernamee, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 105, Short.MAX_VALUE))
        );
        panel_kiriLayout.setVerticalGroup(
            panel_kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_kiriLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lbl_xy1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usernamee)
                .addGap(54, 54, 54)
                .addComponent(btndashbord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnpenilaian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnrangking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnabout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnlogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jspitem.setViewportView(panel_kiri);

        panel_menu.add(jspitem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 189, 770));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_menu, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_menu, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //saat dirun, tampilan form menu utama akan menampilkan ukuran maksimal layar 
        setExtendedState(Menu_Utama.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void btnrangkingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnrangkingMouseExited
        resetColorExited(btnrangking);
    }//GEN-LAST:event_btnrangkingMouseExited

    private void btnrangkingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnrangkingMouseEntered
        setColorEntered(btnrangking);
    }//GEN-LAST:event_btnrangkingMouseEntered

    private void btnrangkingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnrangkingMouseClicked
        //set atau reset warna label item
        resetlabelColor(lbldashbord);
        resetlabelColor(lblkaryawan);
        resetlabelColor(lblpenilaian);
        setlabelColor(lblrangking);
        resetlabelColor(lblabout);
        resetlabelColor(lblabout);
        //
        // set atau reswt warna panel kiri item
        resetpanelColor(pnldashbord);
        resetpanelColor(pnlkaryawan);
        resetpanelColor(pnlpenilaian);
        setpanelColor(pnlrangking);
        resetpanelColor(pnlabout);
        //
        //tampil awal sebelum dirun pada panel kiri item
        pnldashbord.setOpaque(false);
        pnlkaryawan.setOpaque(false);
        pnlpenilaian.setOpaque(false);
        pnlrangking.setOpaque(true);
        pnlabout.setOpaque(false);
        //
        //menampilkan atau tidak icon pada panel item
        lblicon_dashbore_abu.setVisible(true);
        lblicon_dashbore_biru.setVisible(false);
        lblicon_karyawan_abu.setVisible(true);
        lblicon_karyawan_biru.setVisible(false);
        lblicon_rangking_abu.setVisible(false);
        lblicon_rangking_biru.setVisible(true);
        lblicon_penilaian_abu.setVisible(true);
        lblicon_penilaian_biru.setVisible(false);
        lblicon_about_abu.setVisible(true);
        lblicon_about_biru.setVisible(false);
        //
        //memilih form sesuai yang dipilih
        de.setVisible(false);
        kr.setVisible(false);
        pn.setVisible(false);
        pr.setVisible(true);
        at.setVisible(false);
       
        //btn input
        btnkaryawan.setVisible(true);
        btnpenilaian.setVisible(true);
        btnabout.setVisible(true);
    }//GEN-LAST:event_btnrangkingMouseClicked

    private void btnkaryawanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkaryawanMouseExited
        resetColorExited(btnkaryawan);
    }//GEN-LAST:event_btnkaryawanMouseExited

    private void btnkaryawanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkaryawanMouseEntered
        setColorEntered(btnkaryawan);
    }//GEN-LAST:event_btnkaryawanMouseEntered

    private void btnkaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkaryawanMouseClicked
        //set atau reset warna label item
        resetlabelColor(lbldashbord);
        setlabelColor(lblkaryawan);
        resetlabelColor(lblpenilaian);
        resetlabelColor(lblrangking);
         resetlabelColor(lblabout);
        //
        // set atau reswt warna panel kiri item
        resetpanelColor(pnldashbord);
        setpanelColor(pnlkaryawan);
        resetpanelColor(pnlpenilaian);
        resetpanelColor(pnlrangking);
        resetpanelColor(pnlabout);
        //
        //tampil awal sebelum dirun pada panel kiri item
        pnldashbord.setOpaque(false);
        pnlkaryawan.setOpaque(true);
        pnlpenilaian.setOpaque(false);
        pnlrangking.setOpaque(false);
        pnlabout.setOpaque(false);
        //
        //menampilkan atau tidak icon pada panel item
        lblicon_dashbore_abu.setVisible(true);
        lblicon_dashbore_biru.setVisible(false);
        lblicon_karyawan_abu.setVisible(false);
        lblicon_karyawan_biru.setVisible(true);
        lblicon_rangking_abu.setVisible(true);
        lblicon_rangking_biru.setVisible(false);
        lblicon_penilaian_abu.setVisible(true);
        lblicon_penilaian_biru.setVisible(false);
        lblicon_about_abu.setVisible(true);
        lblicon_about_biru.setVisible(false);
        //
        //memilih form sesuai yang dipilih
        de.setVisible(false);
        kr.setVisible(true);
        pn.setVisible(false);
        pr.setVisible(false);
        at.setVisible(false);
        // btn input
        btnkaryawan.setVisible(true);
        btnpenilaian.setVisible(true);
        btnabout.setVisible(true);
    }//GEN-LAST:event_btnkaryawanMouseClicked

    private void btndashbordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndashbordMouseExited
        resetColorExited(btndashbord);
    }//GEN-LAST:event_btndashbordMouseExited

    private void btndashbordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndashbordMouseEntered
        setColorEntered(btndashbord);
    }//GEN-LAST:event_btndashbordMouseEntered

    private void btndashbordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndashbordMouseClicked
        //set atau reset warna label item
        setlabelColor(lbldashbord);
        resetlabelColor(lblkaryawan);
        resetlabelColor(lblpenilaian);
        resetlabelColor(lblrangking);
        resetlabelColor(lblabout);
        //
        // set atau reswt warna panel kiri item
        setpanelColor(pnldashbord);
        resetpanelColor(pnlkaryawan);
        resetpanelColor(pnlpenilaian);
        resetpanelColor(pnlrangking);
        resetpanelColor(pnlabout);
        //
        //tampil awal sebelum dirun pada panel kiri item
        pnldashbord.setOpaque(true);
        pnlkaryawan.setOpaque(false);
        pnlpenilaian.setOpaque(false);
        pnlrangking.setOpaque(false);
        pnlabout.setOpaque(false);
        //
        //menampilkan atau tidak icon pada panel item
        lblicon_dashbore_abu.setVisible(false);
        lblicon_dashbore_biru.setVisible(true);
        lblicon_karyawan_abu.setVisible(true);
        lblicon_karyawan_biru.setVisible(false);
        lblicon_rangking_abu.setVisible(true);
        lblicon_rangking_biru.setVisible(false);
        lblicon_penilaian_abu.setVisible(true);
        lblicon_penilaian_biru.setVisible(false);
        lblicon_about_abu.setVisible(true);
        lblicon_about_biru.setVisible(false);
        //
        //memilih form sesuai yang dipilih
        de.setVisible(true);
        kr.setVisible(false);
        pn.setVisible(false);
        pr.setVisible(false);
        at.setVisible(false);
        // btn input
    }//GEN-LAST:event_btndashbordMouseClicked

    private void btnpenilaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpenilaianMouseClicked
         //set atau reset warna label item
        resetlabelColor(lbldashbord);
        resetlabelColor(lblkaryawan);
        setlabelColor(lblpenilaian);
        resetlabelColor(lblrangking);
        resetlabelColor(lblabout);
        //
        // set atau reswt warna panel kiri item
        resetpanelColor(pnldashbord);
        resetpanelColor(pnlkaryawan);
        setpanelColor(pnlpenilaian);
        resetpanelColor(pnlrangking);
        resetpanelColor(pnlabout);
        //
        //tampil awal sebelum dirun pada panel kiri item
        pnldashbord.setOpaque(false);
        pnlkaryawan.setOpaque(false);
        pnlpenilaian.setOpaque(true);
        pnlrangking.setOpaque(false);
        pnlabout.setOpaque(false);
        //
        //menampilkan atau tidak icon pada panel item
        lblicon_dashbore_abu.setVisible(true);
        lblicon_dashbore_biru.setVisible(false);
        lblicon_karyawan_abu.setVisible(true);
        lblicon_karyawan_biru.setVisible(false);
        lblicon_rangking_abu.setVisible(true);
        lblicon_rangking_biru.setVisible(false);
        lblicon_penilaian_abu.setVisible(false);
        lblicon_penilaian_biru.setVisible(true);
        lblicon_about_abu.setVisible(true);
        lblicon_about_biru.setVisible(false);
        //
        //memilih form sesuai yang dipilih
        de.setVisible(false);
        kr.setVisible(false);
        pn.setVisible(true);
        pr.setVisible(false);
        at.setVisible(false);
        // btn input
        btnkaryawan.setVisible(true);
        btnpenilaian.setVisible(true);
        btnabout.setVisible(true);
    }//GEN-LAST:event_btnpenilaianMouseClicked

    private void btnpenilaianMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpenilaianMouseEntered
        setColorEntered(btnpenilaian);
    }//GEN-LAST:event_btnpenilaianMouseEntered

    private void btnpenilaianMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpenilaianMouseExited
        resetColorExited(btnpenilaian);
    }//GEN-LAST:event_btnpenilaianMouseExited

    private void btnaboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnaboutMouseClicked
         //set atau reset warna label item
        resetlabelColor(lbldashbord);
        resetlabelColor(lblkaryawan);
        resetlabelColor(lblpenilaian);
        resetlabelColor(lblrangking);
        setlabelColor(lblabout); 
        //
        // set atau reswt warna panel kiri item
        resetpanelColor(pnldashbord);
        resetpanelColor(pnlkaryawan);
        resetpanelColor(pnlpenilaian);
        resetpanelColor(pnlrangking);
        setpanelColor(pnlabout);
        //
        //tampil awal sebelum dirun pada panel kiri item
        pnldashbord.setOpaque(false);
        pnlkaryawan.setOpaque(false);
        pnlpenilaian.setOpaque(false);
        pnlrangking.setOpaque(false);
        pnlabout.setOpaque(true);
        //
        //menampilkan atau tidak icon pada panel item
        lblicon_dashbore_abu.setVisible(true);
        lblicon_dashbore_biru.setVisible(false);
        lblicon_karyawan_abu.setVisible(true);
        lblicon_karyawan_biru.setVisible(false);
        lblicon_rangking_abu.setVisible(true);
        lblicon_rangking_biru.setVisible(false);
        lblicon_penilaian_abu.setVisible(true);
        lblicon_penilaian_biru.setVisible(false);
        lblicon_about_abu.setVisible(false);
        lblicon_about_biru.setVisible(true);
        //
        //memilih form sesuai yang dipilih
        de.setVisible(false);
        kr.setVisible(false);
        pn.setVisible(false);
        pr.setVisible(false);
        at.setVisible(true);
        // btn input
        btnkaryawan.setVisible(true);
        btnpenilaian.setVisible(true);
        btnpenilaian.setVisible(true);
    }//GEN-LAST:event_btnaboutMouseClicked

    private void btnaboutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnaboutMouseEntered
        setColorEntered(btnabout);
    }//GEN-LAST:event_btnaboutMouseEntered

    private void btnaboutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnaboutMouseExited
        resetColorExited(btnabout);
    }//GEN-LAST:event_btnaboutMouseExited

    private void btnlogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlogoutMouseClicked
        this.setVisible(false);
        Login lg = new Login();
        lg.setVisible(true);     
    }//GEN-LAST:event_btnlogoutMouseClicked

    private void btnlogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlogoutMouseEntered
         setColorEntered(btnlogout);
    }//GEN-LAST:event_btnlogoutMouseEntered

    private void btnlogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlogoutMouseExited
        resetColorExited(btnlogout);
    }//GEN-LAST:event_btnlogoutMouseExited
    //warna button even entered dan exited
    void setColorEntered(JPanel panel){
        panel.setBackground(new Color(222,244,251));
    }
    void resetColorExited(JPanel panel){
        panel.setBackground(new Color(255,255,255));
    }
    //
    //warna label
    void setlabelColor(JLabel label){
        label.setForeground(new Color(0,194,255));
    }
    
    void resetlabelColor(JLabel label){
        label.setForeground(new Color(153,153,153));
    }
    //
    //warna panel
    void setpanelColor(JPanel panel){
        panel.setForeground(new Color(0,194,255));
    }
    void resetpanelColor(JPanel panel){
        panel.setForeground(new Color(249,250,252));
    }
    //
    //akses user
     void user() {
        btndashbord.setVisible(true);
        btnkaryawan.setVisible(true);
        btnrangking.setVisible(false);
    }
     //akses admin
    void admin() {
        btndashbord.setVisible(true);
        btnrangking.setVisible(true);
    }
    
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
            java.util.logging.Logger.getLogger(Menu_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Utama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnabout;
    private javax.swing.JPanel btndashbord;
    private javax.swing.JPanel btnkaryawan;
    private javax.swing.JPanel btnlogout;
    private javax.swing.JPanel btnpenilaian;
    private javax.swing.JPanel btnrangking;
    private javax.swing.JScrollPane jspbody;
    private javax.swing.JScrollPane jspitem;
    private javax.swing.JLabel lbl_xy1;
    private javax.swing.JLabel lblabout;
    private javax.swing.JLabel lbldashbord;
    private javax.swing.JLabel lblicon_about_abu;
    private javax.swing.JLabel lblicon_about_biru;
    private javax.swing.JLabel lblicon_dashbore_abu;
    private javax.swing.JLabel lblicon_dashbore_biru;
    private javax.swing.JLabel lblicon_karyawan_abu;
    private javax.swing.JLabel lblicon_karyawan_biru;
    private javax.swing.JLabel lblicon_logout_abu;
    private javax.swing.JLabel lblicon_penilaian_abu;
    private javax.swing.JLabel lblicon_penilaian_biru;
    private javax.swing.JLabel lblicon_rangking_abu;
    private javax.swing.JLabel lblicon_rangking_biru;
    private javax.swing.JLabel lblkaryawan;
    private javax.swing.JLabel lbllogout;
    private javax.swing.JLabel lblpenilaian;
    private javax.swing.JLabel lblrangking;
    private javax.swing.JPanel panel_kiri;
    private javax.swing.JPanel panel_menu;
    private javax.swing.JPanel panelbody;
    private javax.swing.JPanel pnlabout;
    private javax.swing.JPanel pnldashbord;
    private javax.swing.JPanel pnlkaryawan;
    private javax.swing.JPanel pnllogout;
    private javax.swing.JPanel pnlpenilaian;
    private javax.swing.JPanel pnlrangking;
    public static volatile javax.swing.JLabel usernamee;
    // End of variables declaration//GEN-END:variables

    private void normalisasi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
