/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebook_app.form;

import beebook_app.Beebook_App;
import beebook_app.controller.AnggotaController;
import beebook_app.data.Anggota;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Annisa Juraini
 */
public class FormAnggota extends javax.swing.JInternalFrame implements NavigatorFormInt{
    Anggota anggota=new Anggota();
    AnggotaController aCont=new AnggotaController(Beebook_App.emf);
    DefaultTableModel model;
    String status;
    /**
     * Creates new form FormAnggota
     */
    public FormAnggota() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model=new DefaultTableModel();
        model.addColumn("ID Anggota");
        model.addColumn("Nama");
        model.addColumn("Kelamin");
        model.addColumn("No Telp");
        model.addColumn("Alamat");
        model.addColumn("Email");
        model.addColumn("Status");
        tableAnggota.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        bersih();
        tableAnggota.getColumnModel().getColumn(4).setCellRenderer(new TableWrap());
        panjangKarakterField();
    }
    
    private void panjangKarakterField(){
        txtNama.setDocument(new JTextFieldLimit(30));
        txtTelp.setDocument(new JTextFieldLimit(15));
        txtAlamat.setDocument(new JTextFieldLimit(100));
        txtEmail.setDocument(new JTextFieldLimit(30));
    }
    
    private void showTable(){
        tableAnggota.setModel(aCont.showTable(model));
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=aCont.cariData(model, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            tableAnggota.setModel(aCont.cariData(model, cari));
        }
    }
    
    private void seleksiBaris(){
        int baris=tableAnggota.getSelectedRow();
        if(baris != -1){
            txtId.setText(tableAnggota.getValueAt(baris, 0).toString());
            txtNama.setText(tableAnggota.getValueAt(baris, 1).toString());
            txtTelp.setText(tableAnggota.getValueAt(baris, 2).toString());
            cbKelamin.setSelectedItem(tableAnggota.getValueAt(baris, 3).toString());
            txtAlamat.setText(tableAnggota.getValueAt(baris, 4).toString());
            txtEmail.setText(tableAnggota.getValueAt(baris, 5).toString());
            status=tableAnggota.getValueAt(baris, 5).toString();
        }
    }

    private void validasiTombol(){
        int baris=tableAnggota.getSelectedRow();
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
        tableAnggota = new javax.swing.JTable();
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
        txtTelp = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        txtNama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbKelamin = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(970, 580));
        getContentPane().setLayout(new java.awt.CardLayout());

        panelUtama.setBackground(new java.awt.Color(255, 255, 255));

        panelNama.setBackground(new java.awt.Color(255, 97, 150));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Data Anggota");

        javax.swing.GroupLayout panelNamaLayout = new javax.swing.GroupLayout(panelNama);
        panelNama.setLayout(panelNamaLayout);
        panelNamaLayout.setHorizontalGroup(
            panelNamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNamaLayout.createSequentialGroup()
                .addGap(439, 439, 439)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(438, 438, 438))
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
        tableAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAnggotaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableAnggota);

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
        btnTambah.setText("Tambah Anggota");
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

        panelTable.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 540, -1));

        panelInfo.add(panelTable, "card2");

        panelTambah.setBackground(new java.awt.Color(255, 255, 255));
        panelTambah.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("No Telp");
        panelTambah.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("ID Anggota");
        panelTambah.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        txtId.setEditable(false);
        txtId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelTambah.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 410, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Nama Anggota");
        panelTambah.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        txtTelp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelpKeyTyped(evt);
            }
        });
        panelTambah.add(txtTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 410, -1));

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

        panelTambah.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 410, -1));

        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelTambah.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 410, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Alamat");
        panelTambah.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, -1, -1));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelTambah.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 410, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Email");
        panelTambah.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Jenis Kelamin");
        panelTambah.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        cbKelamin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pria", "Wanita" }));
        panelTambah.add(cbKelamin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 410, -1));

        txtAlamat.setColumns(20);
        txtAlamat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAlamat.setLineWrap(true);
        txtAlamat.setRows(5);
        txtAlamat.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtAlamat);

        panelTambah.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 410, 130));

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

    private void tableAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAnggotaMouseClicked
        btnEdit.setEnabled(true);
        btnHapus.setEnabled(true);
    }//GEN-LAST:event_tableAnggotaMouseClicked

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        cariTable(txtCari.getText());
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        panelTambah.setVisible(true);
        panelTable.setVisible(false);
        txtId.setText(aCont.nomorOtomatis());
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        panelTambah.setVisible(true);
        seleksiBaris();
        panelTable.setVisible(false);

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int baris=tableAnggota.getSelectedRow();
        String a=tableAnggota.getValueAt(baris, 6).toString();
        if(a.equals("Meminjam")){
            JOptionPane.showMessageDialog(null, "Anggota sedang meminjam!");
        }else{
            seleksiBaris();
            if(JOptionPane.showConfirmDialog(null, "Hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                hapus();
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        simpan();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        bersih();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void txtTelpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelpKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTelpKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cbKelamin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelNama;
    private javax.swing.JPanel panelTable;
    private javax.swing.JPanel panelTambah;
    private javax.swing.JPanel panelUtama;
    private javax.swing.JTable tableAnggota;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTelp;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        bersih();
    }

    @Override
    public void bersih() {
        txtCari.setText("");
        txtNama.setText("");
        cbKelamin.setSelectedIndex(0);
        txtTelp.setText("");
        txtEmail.setText("");
        txtAlamat.setText("");
        panelTable.setVisible(true);
        panelTambah.setVisible(false);
        showTable();
        validasiTombol();
    }

    @Override
    public void simpan() {
        if("".equals(txtNama.getText())||"".equals(txtTelp.getText())||
                "".equals(txtAlamat)||"".equals(txtEmail.getText())){
            JOptionPane.showMessageDialog(null, "Data belum lengkap!");
        }else{
            anggota=aCont.findAnggota(txtId.getText());
            Anggota ag=new Anggota();
            if(anggota==null){
                ag.setIdAnggota(txtId.getText());
                ag.setNama(txtNama.getText());
                ag.setJenisKelamin(cbKelamin.getSelectedItem().toString());
                ag.setNoTelp(txtTelp.getText());
                ag.setAlamat(txtAlamat.getText());
                ag.setEmail(txtEmail.getText());
                ag.setStatus("-");
                try{
                    aCont.save(ag);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            }else{
                ag.setIdAnggota(txtId.getText());
                ag.setNama(txtNama.getText());
                ag.setJenisKelamin(cbKelamin.getSelectedItem().toString());
                ag.setNoTelp(txtTelp.getText());
                ag.setAlamat(txtAlamat.getText());
                ag.setEmail(txtEmail.getText());
                ag.setStatus(status);
                try{
                    aCont.update(ag);
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
            aCont.delete(txtId.getText());
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
