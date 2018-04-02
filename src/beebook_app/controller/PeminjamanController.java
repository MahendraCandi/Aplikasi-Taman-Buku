/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebook_app.controller;

import beebook_app.data.Peminjaman;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Annisa Juraini
 */
public class PeminjamanController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public PeminjamanController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Peminjaman pinjam) throws Exception {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(pinjam);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Peminjaman findPinjam(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(Peminjaman.class, nama);
        }finally{}
    }
    
    public String nomorTrans(){
        String kode="P001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select count (p.noPinjam) from Peminjaman p");
            q.setMaxResults(1);
            Long hasil=(Long) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor=new DecimalFormat("P000");
                kode=formatnomor.format(hasil+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
    
    public DefaultTableModel showTableTransaksi(DefaultTableModel model, String cari){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Connection connect=em.unwrap(Connection.class);
            Statement st=(Statement) connect.createStatement();
            ResultSet rs=st.executeQuery("SELECT no_pinjam, id_anggota, anggota.nama, peminjaman.status FROM `peminjaman` \n" +
                                        "INNER JOIN anggota USING (id_anggota)\n" +
                                        "WHERE peminjaman.status LIKE '%"+cari+"%'\n" +
                                        "GROUP BY no_pinjam DESC");
            while(rs.next()){
                Object[] obj = new Object[4];
                obj[0]=rs.getString("no_pinjam");
                obj[1]=rs.getString("id_anggota");
                obj[2]=rs.getString("nama");
                obj[3]=rs.getString("status");
                model.addRow(obj);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return model;
    }
    
    public DefaultTableModel cariTransaksi(DefaultTableModel model, String cari){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Connection connect=em.unwrap(Connection.class);
            Statement st=(Statement) connect.createStatement();
            ResultSet rs=st.executeQuery("SELECT no_pinjam, id_anggota, anggota.nama, peminjaman.status FROM `peminjaman` \n" +
                                        "INNER JOIN anggota USING (id_anggota)\n" +
                                        "WHERE no_pinjam LIKE '%"+cari+"%'\n" +
                                        "or id_anggota LIKE '%"+cari+"%'\n" +
                                        "or anggota.nama LIKE '%"+cari+"%'\n" +
                                        "or peminjaman.status LIKE '%"+cari+"%'\n" +
                                        "GROUP BY no_pinjam DESC");
            while(rs.next()){
                Object[] obj = new Object[4];
                obj[0]=rs.getString("no_pinjam");
                obj[1]=rs.getString("id_anggota");
                obj[2]=rs.getString("nama");
                obj[3]=rs.getString("status");
                model.addRow(obj);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return model;
    }
    
    //Laporan Transaksi
    public Object[] firstDateLastDate(){
        EntityManager em=getEntityManager();
        Object[] obj=new Object[2];
        try{
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            Statement st=(Statement) connect.createStatement();
            ResultSet rs=st.executeQuery("SELECT MIN(tgl_pinjam), MAX(tgl_pinjam) FROM `peminjaman`");
            while(rs.next()){
                obj[0]=rs.getDate("MIN(tgl_pinjam)");
                obj[1]=rs.getDate("MAX(tgl_pinjam)");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return obj;
    }
    
    //Laporan Transaksi
    public DefaultTableModel dataTransaksi(DefaultTableModel model, String tgl1, String tgl2){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Connection connect=em.unwrap(Connection.class);
            Statement st=(Statement) connect.createStatement();
            ResultSet rs=st.executeQuery("SELECT no_pinjam, tgl_pinjam, tgl_pengembalian, id_anggota, anggota.nama, biaya_sewa \n" +
                                            "FROM `peminjaman` \n" +
                                            "INNER JOIN anggota USING (id_anggota)\n" +
                                            "WHERE tgl_pinjam BETWEEN '"+tgl1+"' AND '"+tgl2+"'");
            while(rs.next()){
                Object[] obj = new Object[6];
                obj[0]=rs.getString("no_pinjam");
                obj[1]=rs.getDate("tgl_pinjam");
                obj[2]=rs.getDate("tgl_pengembalian");
                obj[3]=rs.getString("id_anggota");
                obj[4]=rs.getString("nama");
                obj[5]=rs.getDouble("biaya_sewa");
                model.addRow(obj);
            }
            
        }catch(Exception ex){}
        return model;
    }
    
    //form pengembalian
    public void Status(String no, String ket){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Query q=em.createQuery("UPDATE Peminjaman  p SET  p.status = :ket where p.noPinjam = :no");
            q.setParameter("no", no);
            q.setParameter("ket", ket);
            q.executeUpdate();
            em.getTransaction().commit();
        }catch(Exception ex){
            em.close();
        }
    }
}
