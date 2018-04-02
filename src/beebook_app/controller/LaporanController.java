/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebook_app.controller;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Annisa Juraini
 */
public class LaporanController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public LaporanController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    
    
    public void cetakBuku(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File(""); 
            String nama=file.getAbsolutePath()+"\\src\\report\\reportBuku.jasper";
            HashMap param=new HashMap();
            Locale local=new Locale("id", "ID");
            param.put(JRParameter.REPORT_LOCALE, local);
            JasperPrint jprint=JasperFillManager.fillReport (nama, param, connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
            viewer.setFitPageZoomRatio();
            viewer.setVisible(true);
            connect.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
    
    public void cetakAnggota(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File(""); 
            String nama=file.getAbsolutePath()+"\\src\\report\\reportAnggota.jasper";
            HashMap param=new HashMap();
            Locale local=new Locale("id", "ID");
            param.put(JRParameter.REPORT_LOCALE, local);
            JasperPrint jprint=JasperFillManager.fillReport (nama, param, connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
            viewer.setFitPageZoomRatio();
            viewer.setVisible(true);
            connect.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
    
    public void cetakTransaksi(Date tgl1, Date tgl2){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\src\\report\\reportTransaksi.jasper";
            HashMap param = new HashMap();
            Locale local=new Locale("id", "ID");
            param.put(JRParameter.REPORT_LOCALE, local);
            param.put("tgl1", tgl1);
            param.put("tgl2", tgl2);
            JasperPrint jprint=JasperFillManager.fillReport (namafile, param,connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
            viewer.setFitPageZoomRatio();
            viewer.setVisible(true);
            connect.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }
        
    }
}
