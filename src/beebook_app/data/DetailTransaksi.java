/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebook_app.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "detail_transaksi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailTransaksi.findAll", query = "SELECT d FROM DetailTransaksi d"),
    @NamedQuery(name = "DetailTransaksi.findByIdDetail", query = "SELECT d FROM DetailTransaksi d WHERE d.idDetail = :idDetail"),
    @NamedQuery(name = "DetailTransaksi.findByNoPinjam", query = "SELECT d FROM DetailTransaksi d WHERE d.noPinjam = :noPinjam"),
    @NamedQuery(name = "DetailTransaksi.findByIdBuku", query = "SELECT d FROM DetailTransaksi d WHERE d.idBuku = :idBuku")})
public class DetailTransaksi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detail")
    private Integer idDetail;
    @Basic(optional = false)
    @Column(name = "no_pinjam")
    private String noPinjam;
    @Basic(optional = false)
    @Column(name = "id_buku")
    private String idBuku;

    public DetailTransaksi() {
    }

    public DetailTransaksi(Integer idDetail) {
        this.idDetail = idDetail;
    }

    public DetailTransaksi(Integer idDetail, String noPinjam, String idBuku) {
        this.idDetail = idDetail;
        this.noPinjam = noPinjam;
        this.idBuku = idBuku;
    }

    public Integer getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Integer idDetail) {
        this.idDetail = idDetail;
    }

    public String getNoPinjam() {
        return noPinjam;
    }

    public void setNoPinjam(String noPinjam) {
        this.noPinjam = noPinjam;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetail != null ? idDetail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailTransaksi)) {
            return false;
        }
        DetailTransaksi other = (DetailTransaksi) object;
        if ((this.idDetail == null && other.idDetail != null) || (this.idDetail != null && !this.idDetail.equals(other.idDetail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beebook_app.data.DetailTransaksi[ idDetail=" + idDetail + " ]";
    }
    
}
