/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebook_app.controller;

import beebook_app.data.DetailTransaksi;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Annisa Juraini
 */
public class DetailController implements Serializable{
    private EntityManagerFactory emf=null;
    public DetailController(EntityManagerFactory emf){
        this.emf=emf;
    }
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void saveDetail(DetailTransaksi detail)throws Exception{
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(detail);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public DefaultTableModel showTablePinjam(DefaultTableModel model, String no){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Connection connect=em.unwrap(Connection.class);
            Statement st=(Statement) connect.createStatement();
            ResultSet rs=st.executeQuery("SELECT id_buku, buku.judul, buku.pengarang, buku.penerbit, buku.tahun_terbit, buku.kategori, buku.harga \n" +
                                            "FROM `detail_transaksi`\n" +
                                            "INNER JOIN buku USING(id_buku)\n" +
                                            "WHERE no_pinjam = '"+no+"'");
            while(rs.next()){
                Object[] obj = new Object[7];
                obj[0]=rs.getString("id_buku");
                obj[1]=rs.getString("judul");
                obj[2]=rs.getString("pengarang");
                obj[3]=rs.getString("penerbit");
                obj[4]=rs.getString("tahun_terbit");
                obj[5]=rs.getString("kategori");
                obj[6]=rs.getString("harga");
                model.addRow(obj);
            }
        }catch(Exception ex){}
        return model;
    }
    
    
    
}
