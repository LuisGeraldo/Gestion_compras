/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luis
 */
@Entity
@Table(name = "orden_solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenSolicitud.findAll", query = "SELECT o FROM OrdenSolicitud o")
    , @NamedQuery(name = "OrdenSolicitud.findById", query = "SELECT o FROM OrdenSolicitud o WHERE o.id = :id")})
public class OrdenSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_orden_compra", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private OrdenCompra idOrdenCompra;
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SolicitudArticulo idSolicitud;

    public OrdenSolicitud() {
    }

    public OrdenSolicitud(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrdenCompra getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(OrdenCompra idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public SolicitudArticulo getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(SolicitudArticulo idSolicitud) {
        this.idSolicitud = idSolicitud;
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
        if (!(object instanceof OrdenSolicitud)) {
            return false;
        }
        OrdenSolicitud other = (OrdenSolicitud) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestion.compras.entities.OrdenSolicitud[ id=" + id + " ]";
    }
    
}
