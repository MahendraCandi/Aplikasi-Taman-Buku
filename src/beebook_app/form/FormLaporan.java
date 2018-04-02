/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebook_app.form;

import beebook_app.Beebook_App;
import beebook_app.controller.AnggotaController;
import beebook_app.controller.BukuController;
import beebook_app.controller.LaporanController;
import beebook_app.controller.PeminjamanController;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Annisa Juraini
 */
public class FormLaporan extends javax.swing.JInternalFrame {
    BukuController bCont=new BukuController(Beebook_App.emf);
    AnggotaController aCont=new AnggotaController(Beebook_App.emf);
    PeminjamanController pCont=new PeminjamanController(Beebook_App.emf);
    LaporanController lapCont=new LaporanController(Beebook_App.emf);
    DefaultTableModel bModel, aModel, tModel;
    double totalHarga, harga;
    /**
     * Creates new form FormLaporan
     */
    public FormLaporan() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        tableBuku();
        tableAnggota();
        tableTransaksi();
        formatTgl();
    }
    
    private void tableBuku(){
        bModel=new DefaultTableModel();
        bModel.addColumn("ID Buku");
        bModel.addColumn("Judul");
        bModel.addColumn("Pengarang");
        bModel.addColumn("Penerbit");
        bModel.addColumn("Tahun Terbit");
        bModel.addColumn("Kategori");
        bModel.addColumn("Harga");
        tableBuku.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
    }
    
    private void dataBuku(){
        tableBuku.setModel(bCont.showTable(bModel));
        tableBuku.getColumnModel().getColumn(1).setCellRenderer(new TableWrap());
    }
    
    private void tableAnggota(){
        aModel=new DefaultTableModel();
        aModel.addColumn("ID Anggota");
        aModel.addColumn("Nama ");
        aModel.addColumn("Kelamin");
        aModel.addColumn("No Telp");
        aModel.addColumn("Alamat");
        aModel.addColumn("Email");
        aModel.addColumn("Status");
        tableAnggota.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    private void dataAnggota(){
        tableAnggota.setModel(aCont.showTable(aModel));
        tableAnggota.getColumnModel().getColumn(4).setCellRenderer(new TableWrap());
    }
    
    private void formatTgl(){
        jdcTgl1.setMaxSelectableDate(new Date());
        jdcTgl2.setMaxSelectableDate(new Date());
        jdcTgl1.setLocale(Locale.forLanguageTag("id-ID"));
        jdcTgl2.setLocale(Locale.forLanguageTag("id-ID"));
        firstDateLastDate();
        btnTglActionPerformed(null);
        jdcTgl1.setDateFormatString("dd MMMM yyyy");
        jdcTgl2.setDateFormatString("dd MMMM yyyy");
    }
    
    private void tableTransaksi(){
        tModel=new DefaultTableModel();
        tModel.addColumn("ID Pinjam");
        tModel.addColumn("Tgl. Pinjam");
        tModel.addColumn("Tgl. Kembali");
        tModel.addColumn("ID Anggota");
        tModel.addColumn("Nama");
        tModel.addColumn("Biaya");
        tableTransaksi.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
    }
    
    private void firstDateLastDate(){
        Object[] obj=pCont.firstDateLastDate();
        if(obj[0]==null && obj[1]==null){
            jdcTgl1.setDate(new Date());
            jdcTgl2.setDate(new Date());
        }else{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd", Locale.forLanguageTag("id-ID"));
            try {
                Date tgl1=sdf.parse(obj[0].toString());
                Date tgl2=sdf.parse(obj[1].toString());
                jdcTgl1.setDate(tgl1);
                jdcTgl2.setDate(tgl2);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void dataTransaksi(){
        if(jdcTgl2.getCalendar().before(jdcTgl1.getCalendar())){
            JOptionPane.showMessageDialog(null, "Tanggal Tidak Valid!");
        }else{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd", Locale.forLanguageTag("id-ID"));
            String tgl1=sdf.format(jdcTgl1.getDate());
            String tgl2=sdf.format(jdcTgl2.getDate());
            tableTransaksi.setModel(pCont.dataTransaksi(tModel, tgl1, tgl2));

        }
    }
    
    private void totalHarga(){
        totalHarga=0;
        int row=tableTransaksi.getRowCount();
        if(row==-1){
            labelTotal.setText(String.valueOf(totalHarga));
        }else{
            for(int i=0;i<row;i++){
                harga=Double.parseDouble(tableTransaksi.getValueAt(i, 5).toString());
                totalHarga+=harga;
            }
            labelTotal.setText(String.valueOf(totalHarga));
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelUtama = new javax.swing.JPanel();
        panelNama = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelInfo = new javax.swing.JPanel();
        panelLaporan = new javax.swing.JPanel();
        btnAnggota = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnBuku = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnTransaksi = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        panelBuku = new javax.swing.JPanel();
        btnCetakBuku = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBuku = new javax.swing.JTable();
        btnKembalii1 = new javax.swing.JButton();
        panelAnggota = new javax.swing.JPanel();
        btnCetakAnggota = new javax.swing.JButton();
        btnKembalii2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableAnggota = new javax.swing.JTable();
        panelTransaksi = new javax.swing.JPanel();
        btnKembalii3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableTransaksi = new javax.swing.JTable();
        labelTotal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jdcTgl1 = new com.toedter.calendar.JDateChooser();
        btnCetakTransaksi = new javax.swing.JButton();
        jdcTgl2 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnTgl = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1014, 584));
        getContentPane().setLayout(new java.awt.CardLayout());

        panelUtama.setBackground(new java.awt.Color(255, 255, 255));

        panelNama.setBackground(new java.awt.Color(255, 97, 150));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cetak Laporan");

        javax.swing.GroupLayout panelNamaLayout = new javax.swing.GroupLayout(panelNama);
        panelNama.setLayout(panelNamaLayout);
        panelNamaLayout.setHorizontalGroup(
            panelNamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNamaLayout.createSequentialGroup()
                .addGap(443, 443, 443)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(434, 434, 434))
        );
        panelNamaLayout.setVerticalGroup(
            panelNamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNamaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        panelInfo.setLayout(new java.awt.CardLayout());

        panelLaporan.setBackground(new java.awt.Color(255, 255, 255));
        panelLaporan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAnggota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/People_99px.png"))); // NOI18N
        btnAnggota.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAnggotaMousePressed(evt);
            }
        });
        panelLaporan.add(btnAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Laporan Buku");
        panelLaporan.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, -1, -1));

        btnBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Books_99px.png"))); // NOI18N
        btnBuku.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBukuMousePressed(evt);
            }
        });
        panelLaporan.add(btnBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, -1, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Laporan Anggota");
        panelLaporan.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, -1, -1));

        btnTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Receipt_99px_2.png"))); // NOI18N
        btnTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTransaksiMousePressed(evt);
            }
        });
        panelLaporan.add(btnTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, -1, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText(" Laporan Transaksi");
        panelLaporan.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, -1, -1));

        panelInfo.add(panelLaporan, "card2");

        panelBuku.setBackground(new java.awt.Color(255, 255, 255));
        panelBuku.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCetakBuku.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCetakBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Return_25px.png"))); // NOI18N
        btnCetakBuku.setText("Cetak Laporan");
        btnCetakBuku.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCetakBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakBukuActionPerformed(evt);
            }
        });
        panelBuku.add(btnCetakBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 190, -1));

        tableBuku.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableBuku.setForeground(new java.awt.Color(51, 51, 51));
        tableBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Buku", "Judul", "Pengarang", "Penerbit", "Tahun", "Kategori", "Harga"
            }
        ));
        tableBuku.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tableBuku.setRowHeight(20);
        tableBuku.setRowMargin(2);
        tableBuku.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableBuku);

        panelBuku.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 40, 920, 450));

        btnKembalii1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnKembalii1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Return_25px.png"))); // NOI18N
        btnKembalii1.setText("Kembali");
        btnKembalii1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKembalii1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembalii1ActionPerformed(evt);
            }
        });
        panelBuku.add(btnKembalii1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 190, -1));

        panelInfo.add(panelBuku, "card3");

        panelAnggota.setBackground(new java.awt.Color(255, 255, 255));
        panelAnggota.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCetakAnggota.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCetakAnggota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Return_25px.png"))); // NOI18N
        btnCetakAnggota.setText("Cetak Laporan");
        btnCetakAnggota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCetakAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakAnggotaActionPerformed(evt);
            }
        });
        panelAnggota.add(btnCetakAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 190, -1));

        btnKembalii2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnKembalii2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Return_25px.png"))); // NOI18N
        btnKembalii2.setText("Kembali");
        btnKembalii2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKembalii2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembalii2ActionPerformed(evt);
            }
        });
        panelAnggota.add(btnKembalii2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 190, -1));

        tableAnggota.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableAnggota.setForeground(new java.awt.Color(51, 51, 51));
        tableAnggota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Anggota", "Nama", "No Telp", "Alamat", "Email", "Status"
            }
        ));
        tableAnggota.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tableAnggota.setRowHeight(20);
        tableAnggota.setRowMargin(2);
        tableAnggota.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tableAnggota);

        panelAnggota.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 40, 920, 450));

        panelInfo.add(panelAnggota, "card4");

        panelTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        panelTransaksi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnKembalii3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnKembalii3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Return_25px.png"))); // NOI18N
        btnKembalii3.setText("Kembali");
        btnKembalii3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKembalii3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembalii3ActionPerformed(evt);
            }
        });
        panelTransaksi.add(btnKembalii3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 190, -1));

        tableTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableTransaksi.setForeground(new java.awt.Color(51, 51, 51));
        tableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Pinjam", "Tgl. Pinjam", "Tgl. Kembali", "ID Anggota", "Nama", "Biaya"
            }
        ));
        tableTransaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tableTransaksi.setRowHeight(20);
        tableTransaksi.setRowMargin(2);
        jScrollPane3.setViewportView(tableTransaksi);

        panelTransaksi.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 40, 920, 400));

        labelTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        panelTransaksi.add(labelTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 450, 150, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Hingga");
        panelTransaksi.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, -1, 30));

        jdcTgl1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelTransaksi.add(jdcTgl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 160, 30));

        btnCetakTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCetakTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Return_25px.png"))); // NOI18N
        btnCetakTransaksi.setText("Cetak Laporan");
        btnCetakTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCetakTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakTransaksiActionPerformed(evt);
            }
        });
        panelTransaksi.add(btnCetakTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 190, -1));

        jdcTgl2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelTransaksi.add(jdcTgl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 160, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Grand Total :");
        panelTransaksi.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 450, -1, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Pilih tanggal");
        panelTransaksi.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, -1, 30));

        btnTgl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTgl.setText("OK");
        btnTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTglActionPerformed(evt);
            }
        });
        panelTransaksi.add(btnTgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 60, -1));

        panelInfo.add(panelTransaksi, "card5");

        javax.swing.GroupLayout panelUtamaLayout = new javax.swing.GroupLayout(panelUtama);
        panelUtama.setLayout(panelUtamaLayout);
        panelUtamaLayout.setHorizontalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelUtamaLayout.setVerticalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addComponent(panelNama, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panelUtama, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCetakBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakBukuActionPerformed
        int row=tModel.getRowCount();
        if(row==0){
            JOptionPane.showMessageDialog(null, "Data masih kosong!");
        }else{
            lapCont.cetakBuku();
        }
    }//GEN-LAST:event_btnCetakBukuActionPerformed

    private void btnAnggotaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAnggotaMousePressed
        panelBuku.setVisible(false);
        panelAnggota.setVisible(true);
        panelTransaksi.setVisible(false);
        panelLaporan.setVisible(false);
        dataAnggota();
    }//GEN-LAST:event_btnAnggotaMousePressed

    private void btnBukuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBukuMousePressed
        panelBuku.setVisible(true);
        panelAnggota.setVisible(false);
        panelTransaksi.setVisible(false);
        panelLaporan.setVisible(false);
        dataBuku();
    }//GEN-LAST:event_btnBukuMousePressed

    private void btnTransaksiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransaksiMousePressed
        panelBuku.setVisible(false);
        panelAnggota.setVisible(false);
        panelTransaksi.setVisible(true);
        panelLaporan.setVisible(false);
    }//GEN-LAST:event_btnTransaksiMousePressed

    private void btnKembalii1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembalii1ActionPerformed
        panelLaporan.setVisible(true);
        panelBuku.setVisible(false);
        panelAnggota.setVisible(false);
        panelTransaksi.setVisible(false);
    }//GEN-LAST:event_btnKembalii1ActionPerformed

    private void btnCetakAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakAnggotaActionPerformed
        int row=tModel.getRowCount();
        if(row==0){
            JOptionPane.showMessageDialog(null, "Data masih kosong!");
        }else{
            lapCont.cetakAnggota();
        }
        
    }//GEN-LAST:event_btnCetakAnggotaActionPerformed

    private void btnKembalii2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembalii2ActionPerformed
        panelLaporan.setVisible(true);
        panelBuku.setVisible(false);
        panelAnggota.setVisible(false);
        panelTransaksi.setVisible(false);
    }//GEN-LAST:event_btnKembalii2ActionPerformed

    private void btnKembalii3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembalii3ActionPerformed
        panelLaporan.setVisible(true);
        panelBuku.setVisible(false);
        panelAnggota.setVisible(false);
        panelTransaksi.setVisible(false);
    }//GEN-LAST:event_btnKembalii3ActionPerformed

    private void btnCetakTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakTransaksiActionPerformed
        int row=tModel.getRowCount();
        if(row==0){
            JOptionPane.showMessageDialog(null, "Data masih kosong!");
        }else{
            Date tgl1=jdcTgl1.getDate();
            Date tgl2=jdcTgl2.getDate();
            lapCont.cetakTransaksi(tgl1, tgl2);
        }
    }//GEN-LAST:event_btnCetakTransaksiActionPerformed

    private void btnTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTglActionPerformed
        dataTransaksi();
        totalHarga();
    }//GEN-LAST:event_btnTglActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAnggota;
    private javax.swing.JLabel btnBuku;
    private javax.swing.JButton btnCetakAnggota;
    private javax.swing.JButton btnCetakBuku;
    private javax.swing.JButton btnCetakTransaksi;
    private javax.swing.JButton btnKembalii1;
    private javax.swing.JButton btnKembalii2;
    private javax.swing.JButton btnKembalii3;
    private javax.swing.JButton btnTgl;
    private javax.swing.JLabel btnTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jdcTgl1;
    private com.toedter.calendar.JDateChooser jdcTgl2;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JPanel panelAnggota;
    private javax.swing.JPanel panelBuku;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelLaporan;
    private javax.swing.JPanel panelNama;
    private javax.swing.JPanel panelTransaksi;
    private javax.swing.JPanel panelUtama;
    private javax.swing.JTable tableAnggota;
    private javax.swing.JTable tableBuku;
    private javax.swing.JTable tableTransaksi;
    // End of variables declaration//GEN-END:variables
}
