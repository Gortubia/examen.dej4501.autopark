/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej4501.examen.autoPark.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "opcion_envio_boleta", catalog = "bbddautopark", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpcionEnvioBoleta.findAll", query = "SELECT o FROM OpcionEnvioBoleta o")
    , @NamedQuery(name = "OpcionEnvioBoleta.findByIdopEnvioBoleta", query = "SELECT o FROM OpcionEnvioBoleta o WHERE o.idopEnvioBoleta = :idopEnvioBoleta")
    , @NamedQuery(name = "OpcionEnvioBoleta.findByOpcionEnvioBoleta", query = "SELECT o FROM OpcionEnvioBoleta o WHERE o.opcionEnvioBoleta = :opcionEnvioBoleta")})
public class OpcionEnvioBoleta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_opEnvioBoleta")
    private Integer idopEnvioBoleta;
    @Size(max = 100)
    private String opcionEnvioBoleta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idopEnvioBoleta")
    private List<Voucher> voucherList;

    public OpcionEnvioBoleta() {
    }

    public OpcionEnvioBoleta(Integer idopEnvioBoleta) {
        this.idopEnvioBoleta = idopEnvioBoleta;
    }

    public Integer getIdopEnvioBoleta() {
        return idopEnvioBoleta;
    }

    public void setIdopEnvioBoleta(Integer idopEnvioBoleta) {
        this.idopEnvioBoleta = idopEnvioBoleta;
    }

    public String getOpcionEnvioBoleta() {
        return opcionEnvioBoleta;
    }

    public void setOpcionEnvioBoleta(String opcionEnvioBoleta) {
        this.opcionEnvioBoleta = opcionEnvioBoleta;
    }

    @XmlTransient
    public List<Voucher> getVoucherList() {
        return voucherList;
    }

    public void setVoucherList(List<Voucher> voucherList) {
        this.voucherList = voucherList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idopEnvioBoleta != null ? idopEnvioBoleta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpcionEnvioBoleta)) {
            return false;
        }
        OpcionEnvioBoleta other = (OpcionEnvioBoleta) object;
        if ((this.idopEnvioBoleta == null && other.idopEnvioBoleta != null) || (this.idopEnvioBoleta != null && !this.idopEnvioBoleta.equals(other.idopEnvioBoleta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.dej4501.examen.autoPark.entity.OpcionEnvioBoleta[ idopEnvioBoleta=" + idopEnvioBoleta + " ]";
    }
    
}
