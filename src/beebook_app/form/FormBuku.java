/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebook_app.form;

import beebook_app.Beebook_App;
import beebook_app.controller.BukuController;
import beebook_app.data.Buku;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Annisa Juraini
 */
public class FormBuku extends javax.swing.JInternalFrame implements NavigatorFormInt{

    Buku buku=new Buku();
    BukuController cont=new BukuController(Beebook_App.emf);
    DefaultTableModel model;
    /**
     * Creates new form FormBuku
     */
    public FormBuku() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model=new DefaultTableModel();
        model.addColumn("ID Buku");
        model.addColumn("Judul");
        model.addColumn("Pengarang");
        model.addColumn("Penerbit");
        model.addColumn("Tahun Terbit");
        model.addColumn("Kategori");
        model.addColumn("Harga");
        tableBuku.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        bersih();
        tableBuku.getColumnModel().getColumn(1).setCellRenderer(new TableWrap());
        panjangKarakterField();
    }
    
    private void panjangKarakterField(){
        txtJudul.setDocument(new JTextFieldLimit(50));
        txtPengarang.setDocument(new JTextFieldLimit(30));
        txtPenerbit.setDocument(new JTextFieldLimit(30));
        txtTahun.setDocument(new JTextFieldLimit(4));
        txtKategori.setDocument(new JTextFieldLimit(30));
        txtHarga.setDocument(new JTextFieldLimit(7));
    }
    
    private void showTable(){
        tableBuku.setModel(cont.showTable(model));
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=cont.cariData(model, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            tableBuku.setModel(cont.cariData(model, cari));
        }
    }
    
    private void seleksiBaris(){
        int baris=tableBuku.getSelectedRow();
        if(baris != -1){
            txtId.setText(tableBuku.getValueAt(baris, 0).toString());
            txtJudul.setText(tableBuku.getValueAt(baris, 1).toString());
            txtPengarang.setText(tableBuku.getValueAt(baris, 2).toString());
            txtPenerbit.setText(tableBuku.getValueAt(baris, 3).toString());
            txtTahun.setText(tableBuku.getValueAt(baris, 4).toString());
            txtKategori.setText(tableBuku.getValueAt(baris, 5).toString());
            txtHarga.setText(tableBuku.getValueAt(baris, 6).toString());
        }
    }

    private void validasiTombol(){
        int baris=tableBuku.getSelectedRow();
        if(baris==-1){
            btnEdit.setEnabled(false);
            btnHapus.setEnabled(false);
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
        panelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBuku = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnCari = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        panelTambah = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPengarang = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        txtJudul = new javax.swing.JTextField();
        txtPenerbit = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTahun = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtKategori = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(970, 580));
        getContentPane().setLayout(new java.awt.CardLayout());

        panelUtama.setBackground(new java.awt.Color(255, 255, 255));

        panelNama.setBackground(new java.awt.Color(255, 97, 150));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Buku");

        javax.swing.GroupLayout panelNamaLayout = new javax.swing.GroupLayout(panelNama);
        panelNama.setLayout(panelNamaLayout);
        panelNamaLayout.setHorizontalGroup(
            panelNamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNamaLayout.createSequentialGroup()
                .addGap(442, 442, 442)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(435, 435, 435))
        );
        panelNamaLayout.setVerticalGroup(
            panelNamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNamaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        panelInfo.setLayout(new java.awt.CardLayout());

        panelTable.setBackground(new java.awt.Color(255, 255, 255));
        panelTable.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        tableBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBuku);

        panelTable.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 40, 920, 450));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        btnCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Search_25px.png"))); // NOI18N
        btnCari.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        jPanel2.add(btnCari);

        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCari.setText("Cari");
        jPanel2.add(txtCari);

        panelTable.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 270, -1));

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        btnTambah.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Plus Math_25px.png"))); // NOI18N
        btnTambah.setText("Tambah Data");
        btnTambah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        jPanel3.add(btnTambah);

        btnEdit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit File_25px.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel3.add(btnEdit);

        btnHapus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Trash Can_25px.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel3.add(btnHapus);

        panelTable.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 450, -1));

        panelInfo.add(panelTable, "card2");

        panelTambah.setBackground(new java.awt.Color(255, 255, 255));
        panelTambah.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Pengarang");
        panelTambah.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("ID Buku");
        panelTambah.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        txtId.setEditable(false);
        txtId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelTambah.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 410, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Judul");
        panelTambah.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        txtPengarang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelTambah.add(txtPengarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 410, -1));

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        btnSimpan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Check File_25px.png"))); // NOI18N
        btnSimpan.setText("Simpan Data");
        btnSimpan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel4.add(btnSimpan);

        btnBatal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Return_25px.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        jPanel4.add(btnBatal);

        panelTambah.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 410, -1));

        txtJudul.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelTambah.add(txtJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 410, -1));

        txtPenerbit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelTambah.add(txtPenerbit, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 410, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Penerbit");
        panelTambah.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        txtTahun.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelTambah.add(txtTahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 410, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Tahun Terbit");
        panelTambah.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Kategori");
        panelTambah.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, -1, -1));

        txtKategori.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelTambah.add(txtKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 410, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Harga");
        panelTambah.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, -1, -1));

        txtHarga.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHargaKeyTyped(evt);
            }
        });
        panelTambah.add(txtHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 410, -1));

        panelInfo.add(panelTambah, "card3");

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

    private void tableBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBukuMouseClicked
        btnEdit.setEnabled(true);
        btnHapus.setEnabled(true);
    }//GEN-LAST:event_tableBukuMouseClicked

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        cariTable(txtCari.getText());
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        panelTambah.setVisible(true);
        panelTable.setVisible(false);
        txtId.setText(cont.nomorOtomatis());
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        panelTambah.setVisible(true);
        seleksiBaris();
        panelTable.setVisible(false);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        seleksiBaris();
        if(JOptionPane.showConfirmDialog(null, "Hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            hapus();
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        simpan();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        bersih();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void txtHargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtHargaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelNama;
    private javax.swing.JPanel panelTable;
    private javax.swing.JPanel panelTambah;
    private javax.swing.JPanel panelUtama;
    private javax.swing.JTable tableBuku;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtKategori;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPengarang;
    private javax.swing.JTextField txtTahun;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        bersih();
    }

    @Override
    public void bersih() {
        txtCari.setText("");
        txtJudul.setText("");
        txtPengarang.setText("");
        txtPenerbit.setText("");
        txtTahun.setText("");
        txtKategori.setText("");
        txtHarga.setText("");
        panelTable.setVisible(true);
        panelTambah.setVisible(false);
        txtId.setText(cont.nomorOtomatis());
        showTable();
        validasiTombol();
    }

    @Override
    public void simpan() {
        if("".equals(txtJudul.getText())||"".equals(txtPengarang.getText())||
                "".equals(txtPenerbit.getText())||"".equals(txtTahun.getText())||
                "".equals(txtKategori.getText())||"".equals(txtHarga.getText())){
            JOptionPane.showMessageDialog(null, "Data belum lengkap!");
        }else{
            buku=cont.findBuku(txtId.getText());
            Buku b=new Buku();
            if(buku==null){
                b.setIdBuku(txtId.getText());
                b.setJudul(txtJudul.getText());
                b.setPengarang(txtPengarang.getText());
                b.setPenerbit(txtPenerbit.getText());
                b.setTahunTerbit(txtTahun.getText());
                b.setKategori(txtKategori.getText());
                b.setHarga(Double.parseDouble(txtHarga.getText()));
                try{
                    cont.save(b);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            }else{
                b.setIdBuku(txtId.getText());
                b.setJudul(txtJudul.getText());
                b.setPengarang(txtPengarang.getText());
                b.setPenerbit(txtPenerbit.getText());
                b.setTahunTerbit(txtTahun.getText());
                b.setKategori(txtKategori.getText());
                b.setHarga(Double.parseDouble(txtHarga.getText()));
                try{
                    cont.update(b);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
            }
            bersih();
        }
    }

    @Override
    public void hapus() {
        try{
            cont.delete(txtId.getText());
            JOptionPane.showMessageDialog(null, "Data telah dihapus!");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        bersih();
    }

    @Override
    public void cari() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tampil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
