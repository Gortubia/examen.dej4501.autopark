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
@Table(catalog = "bbddautopark", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mediospago.findAll", query = "SELECT m FROM Mediospago m")
    , @NamedQuery(name = "Mediospago.findByIdmediosPagos", query = "SELECT m FROM Mediospago m WHERE m.idmediosPagos = :idmediosPagos")
    , @NamedQuery(name = "Mediospago.findByNombremediosPagos", query = "SELECT m FROM Mediospago m WHERE m.nombremediosPagos = :nombremediosPagos")})
public class Mediospago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mediosPagos")
    private Integer idmediosPagos;
    @Size(max = 15)
    @Column(name = "nombre_mediosPagos")
    private String nombremediosPagos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmediosPagos")
    private List<Voucher> voucherList;

    public Mediospago() {
    }

    public Mediospago(Integer idmediosPagos) {
        this.idmediosPagos = idmediosPagos;
    }

    public Integer getIdmediosPagos() {
        return idmediosPagos;
    }

    public void setIdmediosPagos(Integer idmediosPagos) {
        this.idmediosPagos = idmediosPagos;
    }

    public String getNombremediosPagos() {
        return nombremediosPagos;
    }

    public void setNombremediosPagos(String nombremediosPagos) {
        this.nombremediosPagos = nombremediosPagos;
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
        hash += (idmediosPagos != null ? idmediosPagos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mediospago)) {
            return false;
        }
        Mediospago other = (Mediospago) object;
        if ((this.idmediosPagos == null && other.idmediosPagos != null) || (this.idmediosPagos != null && !this.idmediosPagos.equals(other.idmediosPagos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.dej4501.examen.autoPark.entity.Mediospago[ idmediosPagos=" + idmediosPagos + " ]";
    }
    
}
