/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej4501.examen.autoPark.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adolf
 */
@Entity
@Table(catalog = "bbddautopark", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voucher.findAll", query = "SELECT v FROM Voucher v")
    , @NamedQuery(name = "Voucher.findByIdVoucher", query = "SELECT v FROM Voucher v WHERE v.idVoucher = :idVoucher")
    , @NamedQuery(name = "Voucher.findByRutCliente", query = "SELECT v FROM Voucher v WHERE v.rutCliente = :rutCliente")
    , @NamedQuery(name = "Voucher.findByNombreCliente", query = "SELECT v FROM Voucher v WHERE v.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "Voucher.findByTelefonoCliente", query = "SELECT v FROM Voucher v WHERE v.telefonoCliente = :telefonoCliente")
    , @NamedQuery(name = "Voucher.findByEmailCliente", query = "SELECT v FROM Voucher v WHERE v.emailCliente = :emailCliente")
    , @NamedQuery(name = "Voucher.findByTotal", query = "SELECT v FROM Voucher v WHERE v.total = :total")})
public class Voucher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_voucher")
    private Integer idVoucher;
    @Column(name = "rut_cliente")
    private Integer rutCliente;
    @Size(max = 50)
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "telefono_cliente")
    private Integer telefonoCliente;
    @Size(max = 50)
    @Column(name = "email_cliente")
    private String emailCliente;
    private Integer total;
    @OneToMany(mappedBy = "idVoucher")
    private List<Ticket> ticketList;
    @JoinColumn(name = "id_mediosPagos", referencedColumnName = "id_mediosPagos")
    @ManyToOne(optional = false)
    private Mediospago idmediosPagos;
    @JoinColumn(name = "id_opEnvioBoleta", referencedColumnName = "id_opEnvioBoleta")
    @ManyToOne(optional = false)
    private OpcionEnvioBoleta idopEnvioBoleta;

    public Voucher() {
    }

    public Voucher(Integer idVoucher) {
        this.idVoucher = idVoucher;
    }

    public Integer getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(Integer idVoucher) {
        this.idVoucher = idVoucher;
    }

    public Integer getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(Integer rutCliente) {
        this.rutCliente = rutCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(Integer telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @XmlTransient
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public Mediospago getIdmediosPagos() {
        return idmediosPagos;
    }

    public void setIdmediosPagos(Mediospago idmediosPagos) {
        this.idmediosPagos = idmediosPagos;
    }

    public OpcionEnvioBoleta getIdopEnvioBoleta() {
        return idopEnvioBoleta;
    }

    public void setIdopEnvioBoleta(OpcionEnvioBoleta idopEnvioBoleta) {
        this.idopEnvioBoleta = idopEnvioBoleta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVoucher != null ? idVoucher.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voucher)) {
            return false;
        }
        Voucher other = (Voucher) object;
        if ((this.idVoucher == null && other.idVoucher != null) || (this.idVoucher != null && !this.idVoucher.equals(other.idVoucher))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.dej4501.examen.autoPark.entity.Voucher[ idVoucher=" + idVoucher + " ]";
    }
    
}
