/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebook_app.form;

import beebook_app.Beebook_App;
import beebook_app.controller.AnggotaController;
import beebook_app.controller.BukuController;
import beebook_app.controller.DetailController;
import beebook_app.controller.PeminjamanController;
import beebook_app.data.DetailTransaksi;
import beebook_app.data.Peminjaman;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale; 
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Annisa Juraini
 */
public class FormTransaksi extends javax.swing.JInternalFrame implements NavigatorFormInt{
    AnggotaController aCont=new AnggotaController(Beebook_App.emf);
    BukuController bCont=new BukuController(Beebook_App.emf);
    PeminjamanController pCont=new PeminjamanController(Beebook_App.emf);
    DetailController dCont=new DetailController(Beebook_App.emf);
    Peminjaman pj=new Peminjaman();
    DetailTransaksi dt=new DetailTransaksi();
    DefaultTableModel aModel;
    DefaultTableModel bModel;
    DefaultTableModel list;
    DefaultTableModel pModel;
    double harga, totalHarga, ubay, ukem;
    /**
     * Creates new form FormTransaksi
     */
    public FormTransaksi() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        dialogAnggota.setLocationRelativeTo(null);
        dialogBuku.setLocationRelativeTo(null);
        tableDialogAnggota();
        tableDialogBuku();
        tableListBuku();
        tableTransaksi();
        bersih();
        tombolToolTip();
        
    }
    
    private void tombolToolTip(){
        btnCariAnggota.setToolTipText("Cari anggota");
        btnCariBuku.setToolTipText("Cari buku");
        btnHapus.setToolTipText("Hapus buku yang diseleksi");
        btnClear.setToolTipText("Hapus semua daftar buku");
        txtBuku.setToolTipText("Masukan ID buku atau judul buku, lalu tekan enter");
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=pCont.cariTransaksi(pModel, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            tableTransaksi.setModel(pCont.showTableTransaksi(pModel, cari));
        }
    }
    
    private void tableTransaksi(){
        pModel=new DefaultTableModel();
        pModel.addColumn("No. Pinjam");
        pModel.addColumn("ID Anggota");
        pModel.addColumn("Nama");
        pModel.addColumn("Status");
        tableTransaksi.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
    }
    
    private void dataTransaksi(){
        String x="";
        tableTransaksi.setModel(pCont.showTableTransaksi(pModel, x));
    }
    
    private void setTglPinjam(){
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
        Calendar cal=Calendar.getInstance();
        txtTglPinjam.setText(sdf.format(cal.getTime()));
    }
    
    private void setTglKembali(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
        txtTglKembali.setText(sdf.format(cal.getTime()));
    }
    
    //Dialog Anggota
    private void tableDialogAnggota(){
        aModel=new DefaultTableModel();
        aModel.addColumn("ID Anggota");
        aModel.addColumn("Nama ");
        aModel.addColumn("Kelamin");
        aModel.addColumn("No Telp");
        aModel.addColumn("Alamat");
        aModel.addColumn("Email");
        aModel.addColumn("Status");
        tableDialogAnggota.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
    }
    //Dialog Anggota
    private void dataDialogAnggota(){
        String a=cbStatus.getSelectedItem().toString();
        if(a.equals("Semua")){
            tableDialogAnggota.setModel(aCont.showTable(aModel));
        }else{
            tableDialogAnggota.setModel(aCont.showTableDialog(aModel, a));
        }
    }
    //Dialog Anggota
    private void cariDialogAnggota(String cari){
        DefaultTableModel x=aCont.cariData(aModel, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            tableDialogAnggota.setModel(aCont.cariData(aModel, cari));
        }
    }
    //Dialog Anggota
    private void pilihDialogAnggota(){
        int baris=tableDialogAnggota.getSelectedRow();
        String a=tableDialogAnggota.getValueAt(baris, 6).toString();
        if(a.equals("Meminjam")){
            JOptionPane.showMessageDialog(null, "Anggota ini sedang meminjam!");
         }else{
            txtIdAnggota.setText(tableDialogAnggota.getValueAt(baris, 0).toString());
            txtNama.setText(tableDialogAnggota.getValueAt(baris, 1).toString());
            
        }
    }
    
     //Dialog Buku
    private void tableDialogBuku(){
        bModel=new DefaultTableModel();
        bModel.addColumn("ID Buku");
        bModel.addColumn("Judul ");
        bModel.addColumn("Pengarang");
        bModel.addColumn("Penerbit");
        bModel.addColumn("tahun");
        bModel.addColumn("Kategori");
        bModel.addColumn("Harga");
        tableDialogBuku.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        seleksiDialogBuku();
    }
    //Dialog Buku
    private void dataDialogBuku(){
        tableDialogBuku.setModel(bCont.showTable(bModel));
    }
    //Dialog Buku
    private void cariDialogBuku(String cari){
        DefaultTableModel x=bCont.cariData(bModel, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            tableDialogBuku.setModel(bCont.cariData(bModel, cari));
        }
    }
    //Dialog Buku
    private void seleksiDialogBuku(){
        tableDialogBuku.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableDialogBuku.getSelectedRow();       
                if(baris != -1){                        
                    txtIdBuku.setText(tableDialogBuku.getValueAt(baris, 0).toString());
                    txtJudul.setText(tableDialogBuku.getValueAt(baris, 1).toString());
                    txtPengarang.setText(tableDialogBuku.getValueAt(baris, 2).toString());
                    txtPenerbit.setText(tableDialogBuku.getValueAt(baris, 3).toString());
                    txtTahun.setText(tableDialogBuku.getValueAt(baris, 4).toString());
                    txtKategori.setText(tableDialogBuku.getValueAt(baris, 5).toString());
                    txtHarga.setText(tableDialogBuku.getValueAt(baris, 6).toString());
                }
            }
        });
    }
    
    private void bukuEnter(){
        Iterator itr=bCont.enterButton(txtBuku.getText());
        Object[] buku;
        if(itr.hasNext()){
            buku = (Object[]) itr.next();
            Object[] obj=new Object[5];  
            obj[1]=String.valueOf(buku[0]); 
            obj[2]=String.valueOf(buku[1]); 
            obj[3]=String.valueOf(buku[2]); 
            obj[4]=Double.parseDouble(String.valueOf(buku[3])); 
            list.addRow(obj); 
            noTable();
        }else{
            JOptionPane.showMessageDialog(null, "Buku tidak ditemukan!");
        }
    }
    
    private void tableListBuku(){
        list=(DefaultTableModel) tableListBuku.getModel();
    }
    private void tampilBuku(){
        Object[] obj=new Object[5];  
        obj[1]=txtIdBuku.getText(); 
        obj[2]=txtJudul.getText(); 
        obj[3]=txtKategori.getText(); 
        obj[4]=txtHarga.getText(); 
        list.addRow(obj); 
        noTable();
    }
    private void noTable(){
        int row=list.getRowCount(); 
        for(int a=0; a<row ;a++){
            String no=String.valueOf(a+1);
            tableListBuku.setValueAt(no, a, 0); 
        }
    }
    private void hapusList(){
        int pilih=tableListBuku.getSelectedRow();
        if(pilih==-1){
            JOptionPane.showMessageDialog(null, "Pilih buku yang mau dihapus!");
        }else{
            list.removeRow(pilih);
        }
        noTable(); 
        totalHarga();
    }
    private void clearList(){
        int baris=tableListBuku.getRowCount();
        if(baris==-1){
            JOptionPane.showMessageDialog(null, "List buku masih kosong!");
        }else{
            int row=list.getRowCount();
            while(row>0){
                row--;
                list.removeRow(row);
            }
            totalHarga();
            txtUbay.setText(String.valueOf(ubay=0));
            txtUkem.setText(String.valueOf(ukem=0));
        }
    }
    private void totalHarga(){
        totalHarga=0;
        int row=tableListBuku.getRowCount();
        if(row==-1){
            txtTotalHarga.setText(String.valueOf(txtTotalHarga));
        }else{
            for(int i=0;i<row;i++){
                harga=Double.parseDouble(tableListBuku.getValueAt(i, 4).toString());
                totalHarga+=harga;
            }
            txtTotalHarga.setText(String.valueOf(totalHarga));
        }
    }
    
    private void ukem(){
        ubay=Double.parseDouble(txtUbay.getText());
        if(ubay<totalHarga){
            JOptionPane.showMessageDialog(null, "Uang bayar kurang!");
            txtUbay.setText("");
            txtUbay.requestFocus();
        }else{
            ukem=ubay-totalHarga;
            txtUkem.setText(String.valueOf(ukem));
            int j=JOptionPane.showConfirmDialog(null, "Simpan transaksi?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION);
                if(j==JOptionPane.OK_OPTION){
                    btnSimpanActionPerformed(null);
            }
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

        dialogAnggota = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDialogAnggota = new javax.swing.JTable();
        btnPilihAnggota = new javax.swing.JButton();
        txtCariDialogAnggota = new javax.swing.JTextField();
        btnCari1 = new javax.swing.JButton();
        cbStatus = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        dialogBuku = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableDialogBuku = new javax.swing.JTable();
        btnPilihBuku = new javax.swing.JButton();
        txtCariDialogBuku = new javax.swing.JTextField();
        btnCariDialogBuku = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTahun = new javax.swing.JTextField();
        txtPenerbit = new javax.swing.JTextField();
        txtPengarang = new javax.swing.JTextField();
        txtJudul = new javax.swing.JTextField();
        txtIdBuku = new javax.swing.JTextField();
        txtKategori = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        panelUtama = new javax.swing.JPanel();
        panelNama = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelInfo = new javax.swing.JPanel();
        panelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTransaksi = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnDetail = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnPinjam = new javax.swing.JButton();
        btnBukaKembali = new javax.swing.JButton();
        panelPinjam = new javax.swing.JPanel();
        txtTotalHarga = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNoPinjam = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIdAnggota = new javax.swing.JTextField();
        txtTglPinjam = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtBuku = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableListBuku = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        btnCariAnggota = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnCariBuku = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        btnKembalii = new javax.swing.JButton();
        txtTglKembali = new javax.swing.JTextField();
        btnHapus = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        txtUbay = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtUkem = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();

        dialogAnggota.setTitle("Cari Anggota");
        dialogAnggota.setBackground(new java.awt.Color(255, 255, 255));
        dialogAnggota.setMinimumSize(new java.awt.Dimension(500, 500));
        dialogAnggota.setSize(new java.awt.Dimension(750, 500));

        tableDialogAnggota.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tableDialogAnggota.setForeground(new java.awt.Color(51, 51, 51));
        tableDialogAnggota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Anggota", "Nama", "Kelamin", "No Telp", "Alamat", "Email", "Status"
            }
        ));
        tableDialogAnggota.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tableDialogAnggota.setRowHeight(20);
        tableDialogAnggota.setRowMargin(2);
        tableDialogAnggota.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tableDialogAnggota);

        btnPilihAnggota.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPilihAnggota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Check File_25px.png"))); // NOI18N
        btnPilihAnggota.setText("Pilih Anggota");
        btnPilihAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihAnggotaActionPerformed(evt);
            }
        });

        txtCariDialogAnggota.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCariDialogAnggota.setText("Cari");

        btnCari1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Search_25px.png"))); // NOI18N
        btnCari1.setText("Cari");
        btnCari1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCari1ActionPerformed(evt);
            }
        });

        cbStatus.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Meminjam", "Semua" }));
        cbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatusActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setText("Tampilkan Status :");

        javax.swing.GroupLayout dialogAnggotaLayout = new javax.swing.GroupLayout(dialogAnggota.getContentPane());
        dialogAnggota.getContentPane().setLayout(dialogAnggotaLayout);
        dialogAnggotaLayout.setHorizontalGroup(
            dialogAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogAnggotaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
                    .addComponent(btnPilihAnggota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogAnggotaLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCari1)
                        .addGap(0, 0, 0)
                        .addComponent(txtCariDialogAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        dialogAnggotaLayout.setVerticalGroup(
            dialogAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogAnggotaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCariDialogAnggota)
                    .addGroup(dialogAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPilihAnggota)
                .addContainerGap())
        );

        dialogBuku.setTitle("Dialog Buku");
        dialogBuku.setSize(new java.awt.Dimension(800, 600));

        tableDialogBuku.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tableDialogBuku.setForeground(new java.awt.Color(51, 51, 51));
        tableDialogBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableDialogBuku.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tableDialogBuku.setRowHeight(20);
        tableDialogBuku.setRowMargin(2);
        tableDialogBuku.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(tableDialogBuku);

        btnPilihBuku.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPilihBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Check File_25px.png"))); // NOI18N
        btnPilihBuku.setText("Pilih Buku");
        btnPilihBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihBukuActionPerformed(evt);
            }
        });

        txtCariDialogBuku.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCariDialogBuku.setText("Cari");

        btnCariDialogBuku.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnCariDialogBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Search_25px.png"))); // NOI18N
        btnCariDialogBuku.setText("Cari");
        btnCariDialogBuku.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCariDialogBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariDialogBukuActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel9.setText("ID Buku");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setText("Judul Buku");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel11.setText("Pengarang");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel12.setText("Penerbit");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel13.setText("Tahun Terbit");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel14.setText("Kategori");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel15.setText("Harga");

        txtTahun.setEditable(false);
        txtTahun.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txtPenerbit.setEditable(false);
        txtPenerbit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txtPengarang.setEditable(false);
        txtPengarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txtJudul.setEditable(false);
        txtJudul.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txtIdBuku.setEditable(false);
        txtIdBuku.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txtKategori.setEditable(false);
        txtKategori.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txtHarga.setEditable(false);
        txtHarga.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        javax.swing.GroupLayout dialogBukuLayout = new javax.swing.GroupLayout(dialogBuku.getContentPane());
        dialogBuku.getContentPane().setLayout(dialogBukuLayout);
        dialogBukuLayout.setHorizontalGroup(
            dialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogBukuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPilihBuku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addGroup(dialogBukuLayout.createSequentialGroup()
                        .addGroup(dialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dialogBukuLayout.createSequentialGroup()
                                .addGroup(dialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(26, 26, 26)
                                .addGroup(dialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPengarang, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                    .addComponent(txtJudul)
                                    .addComponent(txtPenerbit))
                                .addGap(34, 34, 34)
                                .addGroup(dialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHarga, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(dialogBukuLayout.createSequentialGroup()
                                .addComponent(btnCariDialogBuku)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCariDialogBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dialogBukuLayout.setVerticalGroup(
            dialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogBukuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariDialogBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariDialogBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPilihBuku)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        setPreferredSize(new java.awt.Dimension(970, 580));
        getContentPane().setLayout(new java.awt.CardLayout());

        panelUtama.setBackground(new java.awt.Color(255, 255, 255));

        panelNama.setBackground(new java.awt.Color(255, 97, 150));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Peminjaman");

        javax.swing.GroupLayout panelNamaLayout = new javax.swing.GroupLayout(panelNama);
        panelNama.setLayout(panelNamaLayout);
        panelNamaLayout.setHorizontalGroup(
            panelNamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNamaLayout.createSequentialGroup()
                .addGap(452, 452, 452)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(425, 425, 425))
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

        tableTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableTransaksi.setForeground(new java.awt.Color(51, 51, 51));
        tableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Pinjam", "ID Anggota", "Nama", "Status"
            }
        ));
        tableTransaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tableTransaksi.setRowHeight(20);
        tableTransaksi.setRowMargin(2);
        tableTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableTransaksi);

        panelTable.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 40, 920, 450));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        btnDetail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Books_25px_1.png"))); // NOI18N
        btnDetail.setText("Lihat Detail");
        btnDetail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });
        jPanel2.add(btnDetail);

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

        panelTable.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 340, -1));

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        btnPinjam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPinjam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Borrow Book_25px_1.png"))); // NOI18N
        btnPinjam.setText("Buka Peminjaman");
        btnPinjam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPinjamActionPerformed(evt);
            }
        });
        jPanel3.add(btnPinjam);

        btnBukaKembali.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBukaKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Return Book_25px.png"))); // NOI18N
        btnBukaKembali.setText("Buka Pengembalian");
        btnBukaKembali.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBukaKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBukaKembaliActionPerformed(evt);
            }
        });
        jPanel3.add(btnBukaKembali);

        panelTable.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 370, -1));

        panelInfo.add(panelTable, "card2");

        panelPinjam.setBackground(new java.awt.Color(255, 255, 255));
        panelPinjam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTotalHarga.setBackground(new java.awt.Color(247, 225, 188));
        txtTotalHarga.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTotalHarga.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotalHarga.setText("0");
        txtTotalHarga.setOpaque(true);
        panelPinjam.add(txtTotalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 210, 130, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("No Pinjaman");
        panelPinjam.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 30));

        txtNoPinjam.setEditable(false);
        txtNoPinjam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelPinjam.add(txtNoPinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 200, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("ID Angota");
        panelPinjam.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, -1));

        txtIdAnggota.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelPinjam.add(txtIdAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 140, 30));

        txtTglPinjam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelPinjam.add(txtTglPinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 200, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tanggal Pinjam");
        panelPinjam.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        txtBuku.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBukuKeyPressed(evt);
            }
        });
        panelPinjam.add(txtBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 200, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Tanggal Kembali");
        panelPinjam.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelPinjam.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 290, -1));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        tableListBuku.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableListBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "ID Buku", "Judul", "Kategori", "Harga"
            }
        ));
        tableListBuku.setRowHeight(25);
        tableListBuku.setRowMargin(5);
        jScrollPane3.setViewportView(tableListBuku);
        if (tableListBuku.getColumnModel().getColumnCount() > 0) {
            tableListBuku.getColumnModel().getColumn(0).setMinWidth(50);
            tableListBuku.getColumnModel().getColumn(0).setMaxWidth(50);
            tableListBuku.getColumnModel().getColumn(1).setMinWidth(100);
            tableListBuku.getColumnModel().getColumn(1).setMaxWidth(100);
            tableListBuku.getColumnModel().getColumn(2).setMinWidth(350);
            tableListBuku.getColumnModel().getColumn(2).setMaxWidth(350);
        }

        panelPinjam.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 790, 130));
        panelPinjam.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 940, 10));

        btnCariAnggota.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCariAnggota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Search Property_20px.png"))); // NOI18N
        btnCariAnggota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCariAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariAnggotaActionPerformed(evt);
            }
        });
        panelPinjam.add(btnCariAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, -1, 30));

        btnSimpan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Check File_25px.png"))); // NOI18N
        btnSimpan.setText("Simpan Data");
        btnSimpan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        panelPinjam.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 190, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Tambah Buku");
        panelPinjam.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 90, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Uang Bayar :");
        panelPinjam.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, -1, 30));

        btnCariBuku.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCariBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Search Property_20px.png"))); // NOI18N
        btnCariBuku.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCariBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariBukuActionPerformed(evt);
            }
        });
        panelPinjam.add(btnCariBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Nama Anggota");
        panelPinjam.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, -1, -1));

        btnKembalii.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnKembalii.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Return_25px.png"))); // NOI18N
        btnKembalii.setText("Batal");
        btnKembalii.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKembalii.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliiActionPerformed(evt);
            }
        });
        panelPinjam.add(btnKembalii, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 190, -1));

        txtTglKembali.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelPinjam.add(txtTglKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 200, -1));

        btnHapus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete_20px.png"))); // NOI18N
        btnHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        panelPinjam.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 50, -1));

        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Empty Trash_20px.png"))); // NOI18N
        btnClear.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        panelPinjam.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 50, -1));
        panelPinjam.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 420, 130, 10));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("Total Harga :");
        panelPinjam.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, -1, 30));

        txtUbay.setBackground(new java.awt.Color(247, 225, 188));
        txtUbay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtUbay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtUbay.setText("0");
        txtUbay.setBorder(null);
        txtUbay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUbayKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUbayKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUbayKeyTyped(evt);
            }
        });
        panelPinjam.add(txtUbay, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 390, 130, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setText("Uang Kembali :");
        panelPinjam.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 420, -1, 30));

        txtUkem.setBackground(new java.awt.Color(247, 225, 188));
        txtUkem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtUkem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtUkem.setText("0");
        txtUkem.setOpaque(true);
        panelPinjam.add(txtUkem, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 420, 130, 30));
        panelPinjam.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 820, 10));

        panelInfo.add(panelPinjam, "card3");

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

    private void tableTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTransaksiMouseClicked
        int row=tableTransaksi.getSelectedRow();
        String a=tableTransaksi.getValueAt(row, 3).toString();
        if(a.equals("Selesai")){
            btnBukaKembali.setEnabled(false);
            btnDetail.setEnabled(true);
        }else{
            btnBukaKembali.setEnabled(true);
            btnDetail.setEnabled(true);
        }
    }//GEN-LAST:event_tableTransaksiMouseClicked

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        cariTable(txtCari.getText());
        btnBukaKembali.setEnabled(false);
        btnDetail.setEnabled(false);
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPinjamActionPerformed
        panelPinjam.setVisible(true);
        panelTable.setVisible(false);
        txtNoPinjam.setText(pCont.nomorTrans());
        setTglPinjam();
        setTglKembali();
    }//GEN-LAST:event_btnPinjamActionPerformed

    private void btnBukaKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBukaKembaliActionPerformed
        int baris=tableTransaksi.getSelectedRow();
        String a=tableTransaksi.getValueAt(baris, 0).toString();
        String b=tableTransaksi.getValueAt(baris, 1).toString();
        FormPengembalian FA=new FormPengembalian(a, b);
        JDesktopPane pane=getDesktopPane();
        pane.add(FA);
        FA.setVisible(true);
    }//GEN-LAST:event_btnBukaKembaliActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        hapusList();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        int baris=tableTransaksi.getSelectedRow();
        String a=tableTransaksi.getValueAt(baris, 0).toString();
        String b=tableTransaksi.getValueAt(baris, 1).toString();
        
        FormDetail FA=new FormDetail(a, b);
        JDesktopPane pane=getDesktopPane();
        pane.add(FA);
        FA.setVisible(true);
    }//GEN-LAST:event_btnDetailActionPerformed

    private void btnKembaliiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliiActionPerformed
        bersih();
    }//GEN-LAST:event_btnKembaliiActionPerformed

    private void btnCariBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariBukuActionPerformed
        dataDialogBuku();
        tableDialogAnggota.getColumnModel().getColumn(1).setCellRenderer(new TableWrapDialog());
        dialogBuku.setVisible(true);
    }//GEN-LAST:event_btnCariBukuActionPerformed

    private void btnCariAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariAnggotaActionPerformed
        dataDialogAnggota();
        tableDialogAnggota.getColumnModel().getColumn(4).setCellRenderer(new TableWrapDialog());
        dialogAnggota.setVisible(true);
    }//GEN-LAST:event_btnCariAnggotaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearList();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        simpan();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCari1ActionPerformed
        cariDialogAnggota(txtCariDialogAnggota.getText());
    }//GEN-LAST:event_btnCari1ActionPerformed

    private void btnPilihAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihAnggotaActionPerformed
        int baris=tableDialogAnggota.getSelectedRow();
        if(baris!=-1){
            pilihDialogAnggota();
        }  
        dialogAnggota.dispose();
    }//GEN-LAST:event_btnPilihAnggotaActionPerformed

    private void btnCariDialogBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariDialogBukuActionPerformed
        cariDialogBuku(txtCariDialogBuku.getText());
    }//GEN-LAST:event_btnCariDialogBukuActionPerformed

    private void btnPilihBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihBukuActionPerformed
        int row=tableDialogBuku.getSelectedRow();
        if(row==-1){
            dialogBuku.dispose();
        }else{
            tampilBuku();
            totalHarga();
            dialogBuku.dispose();
            txtUbay.requestFocus();
            txtUbay.setText("");
        }
    }//GEN-LAST:event_btnPilihBukuActionPerformed

    private void cbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusActionPerformed
        dataDialogAnggota();
    }//GEN-LAST:event_cbStatusActionPerformed

    private void txtBukuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBukuKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            bukuEnter();
            totalHarga();
            txtUbay.requestFocus();
            txtUbay.setText("");
        }
    }//GEN-LAST:event_txtBukuKeyPressed

    private void txtUbayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbayKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(txtUbay.getText().equals("")||txtUbay.getText().equals("0.0")){
                getToolkit().beep();
                evt.consume();
            }else{
                ukem();
            }
        }
    }//GEN-LAST:event_txtUbayKeyPressed

    private void txtUbayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbayKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtUbayKeyTyped

    private void txtUbayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbayKeyReleased
        if(txtUbay.getText().equals("")){
            getToolkit().beep();
            evt.consume();
            txtUkem.setText(String.valueOf(ukem=0));
        }else{
            ubay=Double.parseDouble(txtUbay.getText());
            ukem=ubay-totalHarga;
            txtUkem.setText(String.valueOf(ukem));
        }
        
    }//GEN-LAST:event_txtUbayKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBukaKembali;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCari1;
    private javax.swing.JButton btnCariAnggota;
    private javax.swing.JButton btnCariBuku;
    private javax.swing.JButton btnCariDialogBuku;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembalii;
    private javax.swing.JButton btnPilihAnggota;
    private javax.swing.JButton btnPilihBuku;
    private javax.swing.JButton btnPinjam;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JDialog dialogAnggota;
    private javax.swing.JDialog dialogBuku;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelNama;
    private javax.swing.JPanel panelPinjam;
    private javax.swing.JPanel panelTable;
    private javax.swing.JPanel panelUtama;
    private javax.swing.JTable tableDialogAnggota;
    private javax.swing.JTable tableDialogBuku;
    private javax.swing.JTable tableListBuku;
    private javax.swing.JTable tableTransaksi;
    private javax.swing.JTextField txtBuku;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtCariDialogAnggota;
    private javax.swing.JTextField txtCariDialogBuku;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtIdAnggota;
    private javax.swing.JTextField txtIdBuku;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtKategori;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoPinjam;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPengarang;
    private javax.swing.JTextField txtTahun;
    private javax.swing.JTextField txtTglKembali;
    private javax.swing.JTextField txtTglPinjam;
    private javax.swing.JLabel txtTotalHarga;
    private javax.swing.JTextField txtUbay;
    private javax.swing.JLabel txtUkem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        bersih();
    }

    @Override
    public void bersih() {
        dataTransaksi();
        txtCari.setText("");
        txtIdAnggota.setText("");
        txtNama.setText("");
        txtIdBuku.setText("");
        txtJudul.setText("");
        txtCari.setText("");
        txtCari.setText("");
        panelTable.setVisible(true);
        panelPinjam.setVisible(false);
        btnBukaKembali.setEnabled(false);
        btnDetail.setEnabled(false);
        clearList(); 
    }

    @Override
    public void simpan() {
        int baris=tableListBuku.getRowCount();
        if("".equals(txtIdAnggota.getText())||baris==0){
            JOptionPane.showMessageDialog(null, "Data belum lengkap!");
        }else if(txtUbay.getText().equals("")){
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "Masukan uang bayar!");
            txtUbay.setText(String.valueOf(ubay=0));
            txtUbay.setText("");
            txtUkem.setText(String.valueOf(ukem=0));
            txtUbay.requestFocus();
        }else if(ukem<0||ubay<totalHarga){
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "Uang bayar kurang!");
            txtUbay.setText(String.valueOf(ubay=0));
            txtUbay.setText("");
            txtUkem.setText(String.valueOf(ukem=0));
            txtUbay.requestFocus();
        }else{
            try{
                pj.setNoPinjam(txtNoPinjam.getText());
                pj.setTglPinjam(new Date());
                SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
                Date tgl=(Date) sdf.parse(txtTglKembali.getText());
                pj.setTglPengembalian(tgl);
                pj.setBiayaSewa(totalHarga);
                pj.setUangBayar(ubay);
                pj.setUangKembali(ukem);
                pj.setIdAnggota(txtIdAnggota.getText());
                pj.setStatus("Meminjam");
                pCont.save(pj);
                aCont.Status(txtIdAnggota.getText(), "Meminjam");
                int row=tableListBuku.getRowCount();
                for(int i=0;i<row;i++){
                    dt.setNoPinjam(txtNoPinjam.getText());
                    dt.setIdBuku(tableListBuku.getValueAt(i, 1).toString());
                    dCont.saveDetail(dt);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            bersih();
        }
        
        
    }

    @Override
    public void hapus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
