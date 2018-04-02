/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebook_app.controller;

import beebook_app.data.Buku;
import java.io.Serializable;
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
public class BukuController implements Serializable {
    private EntityManagerFactory emf=null;
    
    public BukuController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Buku buku) throws Exception {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(buku);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void update(Buku buku) throws Exception{
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(buku);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String nama) throws Exception{
        EntityManager em = getEntityManager();
        Buku bk;
        try{
            bk=em.getReference(Buku.class, nama);
            bk.getIdBuku();
            em.getTransaction().begin();
            em.remove(bk);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Buku findBuku(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(Buku.class, nama);
        }finally{}
    }
    
    public String nomorOtomatis(){
        String kode="B001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select b from Buku b order by b.idBuku desc");
            q.setMaxResults(1);
            Buku us=(Buku) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("B000");
                String nomorurut = us.getIdBuku().substring(1);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
    
    public DefaultTableModel showTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("SELECT b.idBuku, b.judul, b.pengarang, b.penerbit, b.tahunTerbit, b.kategori, b.harga from Buku b");
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    public DefaultTableModel cariData(DefaultTableModel model, String cari){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("SELECT b.idBuku, b.judul, b.pengarang, b.penerbit, b.tahunTerbit, b.kategori, b.harga from Buku b WHERE b.idBuku  like '%"+cari+"%'"
                                 + " or b.judul like '%"+cari+"%'"
                                 + " or b.pengarang like '%"+cari+"%'"
                                 + " or b.penerbit like '%"+cari+"%'"
                                 + " or b.tahunTerbit like '%"+cari+"%'"
                                 + " or b.kategori like '%"+cari+"%'"
                                 + " or b.harga like '%"+cari+"%'");
            List<Object> hasil=(List<Object>) q.getResultList();
                Iterator itr = hasil.iterator();
                while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    //Form Transaksi buuton buku enter
    public Iterator enterButton(String cari){
        EntityManager em=getEntityManager();
        try{
            Query q=em.createQuery("SELECT b.idBuku, b.judul, b.kategori, b.harga from Buku b WHERE b.idBuku  like '%"+cari+"%'"
                                 + " or b.judul like '%"+cari+"%'");
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            itr.hasNext();
            //Object[] obj = (Object[]) itr.next();
            return itr;
        }finally{}
    }
}
