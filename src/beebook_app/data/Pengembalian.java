/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebook_app.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Annisa Juraini
 */
@Entity
@Table(name = "pengembalian")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pengembalian.findAll", query = "SELECT p FROM Pengembalian p"),
    @NamedQuery(name = "Pengembalian.findById", query = "SELECT p FROM Pengembalian p WHERE p.id = :id"),
    @NamedQuery(name = "Pengembalian.findByNoPinjam", query = "SELECT p FROM Pengembalian p WHERE p.noPinjam = :noPinjam"),
    @NamedQuery(name = "Pengembalian.findByTglDikembalikan", query = "SELECT p FROM Pengembalian p WHERE p.tglDikembalikan = :tglDikembalikan"),
    @NamedQuery(name = "Pengembalian.findByDenda", query = "SELECT p FROM Pengembalian p WHERE p.denda = :denda")})
public class Pengembalian implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "no_pinjam")
    private String noPinjam;
    @Basic(optional = false)
    @Column(name = "tgl_dikembalikan")
    @Temporal(TemporalType.DATE)
    private Date tglDikembalikan;
    @Basic(optional = false)
    @Column(name = "denda")
    private double denda;

    public Pengembalian() {
    }

    public Pengembalian(Integer id) {
        this.id = id;
    }

    public Pengembalian(Integer id, String noPinjam, Date tglDikembalikan, double denda) {
        this.id = id;
        this.noPinjam = noPinjam;
        this.tglDikembalikan = tglDikembalikan;
        this.denda = denda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoPinjam() {
        return noPinjam;
    }

    public void setNoPinjam(String noPinjam) {
        this.noPinjam = noPinjam;
    }

    public Date getTglDikembalikan() {
        return tglDikembalikan;
    }

    public void setTglDikembalikan(Date tglDikembalikan) {
        this.tglDikembalikan = tglDikembalikan;
    }

    public double getDenda() {
        return denda;
    }

    public void setDenda(double denda) {
        this.denda = denda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pengembalian)) {
            return false;
        }
        Pengembalian other = (Pengembalian) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beebook_app.data.Pengembalian[ id=" + id + " ]";
    }
    
}
