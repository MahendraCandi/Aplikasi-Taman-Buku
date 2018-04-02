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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "peminjaman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peminjaman.findAll", query = "SELECT p FROM Peminjaman p"),
    @NamedQuery(name = "Peminjaman.findByNoPinjam", query = "SELECT p FROM Peminjaman p WHERE p.noPinjam = :noPinjam"),
    @NamedQuery(name = "Peminjaman.findByTglPinjam", query = "SELECT p FROM Peminjaman p WHERE p.tglPinjam = :tglPinjam"),
    @NamedQuery(name = "Peminjaman.findByTglPengembalian", query = "SELECT p FROM Peminjaman p WHERE p.tglPengembalian = :tglPengembalian"),
    @NamedQuery(name = "Peminjaman.findByBiayaSewa", query = "SELECT p FROM Peminjaman p WHERE p.biayaSewa = :biayaSewa"),
    @NamedQuery(name = "Peminjaman.findByUangBayar", query = "SELECT p FROM Peminjaman p WHERE p.uangBayar = :uangBayar"),
    @NamedQuery(name = "Peminjaman.findByUangKembali", query = "SELECT p FROM Peminjaman p WHERE p.uangKembali = :uangKembali"),
    @NamedQuery(name = "Peminjaman.findByIdAnggota", query = "SELECT p FROM Peminjaman p WHERE p.idAnggota = :idAnggota"),
    @NamedQuery(name = "Peminjaman.findByStatus", query = "SELECT p FROM Peminjaman p WHERE p.status = :status")})
public class Peminjaman implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "no_pinjam")
    private String noPinjam;
    @Basic(optional = false)
    @Column(name = "tgl_pinjam")
    @Temporal(TemporalType.DATE)
    private Date tglPinjam;
    @Basic(optional = false)
    @Column(name = "tgl_pengembalian")
    @Temporal(TemporalType.DATE)
    private Date tglPengembalian;
    @Basic(optional = false)
    @Column(name = "biaya_sewa")
    private double biayaSewa;
    @Basic(optional = false)
    @Column(name = "uang_bayar")
    private double uangBayar;
    @Basic(optional = false)
    @Column(name = "uang_kembali")
    private double uangKembali;
    @Basic(optional = false)
    @Column(name = "id_anggota")
    private String idAnggota;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;

    public Peminjaman() {
    }

    public Peminjaman(String noPinjam) {
        this.noPinjam = noPinjam;
    }

    public Peminjaman(String noPinjam, Date tglPinjam, Date tglPengembalian, double biayaSewa, double uangBayar, double uangKembali, String idAnggota, String status) {
        this.noPinjam = noPinjam;
        this.tglPinjam = tglPinjam;
        this.tglPengembalian = tglPengembalian;
        this.biayaSewa = biayaSewa;
        this.uangBayar = uangBayar;
        this.uangKembali = uangKembali;
        this.idAnggota = idAnggota;
        this.status = status;
    }

    public String getNoPinjam() {
        return noPinjam;
    }

    public void setNoPinjam(String noPinjam) {
        this.noPinjam = noPinjam;
    }

    public Date getTglPinjam() {
        return tglPinjam;
    }

    public void setTglPinjam(Date tglPinjam) {
        this.tglPinjam = tglPinjam;
    }

    public Date getTglPengembalian() {
        return tglPengembalian;
    }

    public void setTglPengembalian(Date tglPengembalian) {
        this.tglPengembalian = tglPengembalian;
    }

    public double getBiayaSewa() {
        return biayaSewa;
    }

    public void setBiayaSewa(double biayaSewa) {
        this.biayaSewa = biayaSewa;
    }

    public double getUangBayar() {
        return uangBayar;
    }

    public void setUangBayar(double uangBayar) {
        this.uangBayar = uangBayar;
    }

    public double getUangKembali() {
        return uangKembali;
    }

    public void setUangKembali(double uangKembali) {
        this.uangKembali = uangKembali;
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(String idAnggota) {
        this.idAnggota = idAnggota;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noPinjam != null ? noPinjam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peminjaman)) {
            return false;
        }
        Peminjaman other = (Peminjaman) object;
        if ((this.noPinjam == null && other.noPinjam != null) || (this.noPinjam != null && !this.noPinjam.equals(other.noPinjam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beebook_app.data.Peminjaman[ noPinjam=" + noPinjam + " ]";
    }
    
}
