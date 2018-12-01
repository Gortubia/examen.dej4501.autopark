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
    @NamedQuery(name = "Estacionamiento.findAll", query = "SELECT e FROM Estacionamiento e")
    , @NamedQuery(name = "Estacionamiento.findByIdEstacionamiento", query = "SELECT e FROM Estacionamiento e WHERE e.idEstacionamiento = :idEstacionamiento")
    , @NamedQuery(name = "Estacionamiento.findByNombreEstacionamiento", query = "SELECT e FROM Estacionamiento e WHERE e.nombreEstacionamiento = :nombreEstacionamiento")
    , @NamedQuery(name = "Estacionamiento.findByDireccion", query = "SELECT e FROM Estacionamiento e WHERE e.direccion = :direccion")})
public class Estacionamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estacionamiento")
    private Integer idEstacionamiento;
    @Size(max = 100)
    @Column(name = "nombre_estacionamiento")
    private String nombreEstacionamiento;
    @Size(max = 300)
    private String direccion;
    @OneToMany(mappedBy = "idEstacionamiento")
    private List<Ticket> ticketList;

    public Estacionamiento() {
    }

    public Estacionamiento(Integer idEstacionamiento) {
        this.idEstacionamiento = idEstacionamiento;
    }

    public Integer getIdEstacionamiento() {
        return idEstacionamiento;
    }

    public void setIdEstacionamiento(Integer idEstacionamiento) {
        this.idEstacionamiento = idEstacionamiento;
    }

    public String getNombreEstacionamiento() {
        return nombreEstacionamiento;
    }

    public void setNombreEstacionamiento(String nombreEstacionamiento) {
        this.nombreEstacionamiento = nombreEstacionamiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstacionamiento != null ? idEstacionamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estacionamiento)) {
            return false;
        }
        Estacionamiento other = (Estacionamiento) object;
        if ((this.idEstacionamiento == null && other.idEstacionamiento != null) || (this.idEstacionamiento != null && !this.idEstacionamiento.equals(other.idEstacionamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.dej4501.examen.autoPark.entity.Estacionamiento[ idEstacionamiento=" + idEstacionamiento + " ]";
    }
    
}
