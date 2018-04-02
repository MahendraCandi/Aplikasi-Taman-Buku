/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebook_app.controller;

import beebook_app.data.Pengembalian;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Annisa Juraini
 */
public class PengembalianController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public PengembalianController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Pengembalian kembali) throws Exception {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(kembali);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Pengembalian findPengembalian(int id){
        EntityManager em=getEntityManager();
        try{
            return em.find(Pengembalian.class, id);
        }finally{}
    }
    
    public DefaultTableModel showTableKembali(DefaultTableModel model, String no){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Connection connect=em.unwrap(Connection.class);
            Statement st=(Statement) connect.createStatement();
            ResultSet rs=st.executeQuery("SELECT id_buku, buku.judul, buku.pengarang, buku.kategori, buku.harga \n" +
                                            "FROM `detail_transaksi`\n" +
                                            "INNER JOIN buku USING(id_buku)\n" +
                                            "WHERE no_pinjam = '"+no+"'");
            while(rs.next()){
                Object[] obj = new Object[5];
                obj[0]=rs.getString("id_buku");
                obj[1]=rs.getString("judul");
                obj[2]=rs.getString("pengarang");
                obj[3]=rs.getString("kategori");
                obj[4]=rs.getString("harga");
                model.addRow(obj);
            }
        }catch(Exception ex){}
        return model;
    }
    
    //Form Detail
    public int id(String no){
        EntityManager em = getEntityManager();
        int id=0;
        try{
            em.getTransaction().begin();
            Query q=em.createQuery("SELECT p.id FROM Pengembalian p WHERE p.noPinjam = :no");
            q.setParameter("no", no);
            id=Integer.parseInt(q.getSingleResult().toString());
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return id;
    }
}
