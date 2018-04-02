/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebook_app.controller;

import beebook_app.data.Anggota;
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
public class AnggotaController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public AnggotaController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Anggota anggota) throws Exception {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(anggota);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void update(Anggota anggota) throws Exception{
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(anggota);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String nama) throws Exception{
        EntityManager em = getEntityManager();
        Anggota us;
        try{
            us=em.getReference(Anggota.class, nama);
            us.getIdAnggota();
            em.getTransaction().begin();
            em.remove(us);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Anggota findAnggota(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(Anggota.class, nama);
        }finally{}
    }
    
    public String nomorOtomatis(){
        String kode="Beebook-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select a from Anggota a order by a.idAnggota desc");
            q.setMaxResults(1);
            Anggota us=(Anggota) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("Beebook-000");
                String nomorurut = us.getIdAnggota().substring(8);
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
            Query q=em.createQuery("SELECT a.idAnggota, a.nama, a.jenisKelamin, a.noTelp, a.alamat, a.email, a.status from Anggota a");
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
            Query q=em.createQuery("SELECT a.idAnggota, a.nama, a.jenisKelamin, a.noTelp, a.alamat, a.email, a.status from Anggota a WHERE a.idAnggota like '%"+cari+"%'"
                                 + " or a.nama like '%"+cari+"%'"
                                 + " or a.jenisKelamin like '%"+cari+"%'"
                                 + " or a.noTelp like '%"+cari+"%'"
                                 + " or a.alamat like '%"+cari+"%'"
                                 + " or a.email like '%"+cari+"%'"
                                 + " or a.status like '%"+cari+"%'");
            List<Object> hasil=(List<Object>) q.getResultList();
                Iterator itr = hasil.iterator();
                while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    //Dialog Anggota
    public DefaultTableModel showTableDialog(DefaultTableModel model, String cari){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("SELECT a.idAnggota, a.nama, a.jenisKelamin, a.noTelp, a.alamat, a.email, a.status from Anggota a "
                    + "WHERE a.status LIKE :cari");
            q.setParameter("cari", cari);
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    
    public void Status(String id, String ket){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Query q=em.createQuery("UPDATE Anggota a SET a.status = :ket where a.idAnggota = :id");
            q.setParameter("id", id);
            q.setParameter("ket", ket);
            q.executeUpdate();
            em.getTransaction().commit();
        }catch(Exception ex){
            em.close();
        }
    }
}
