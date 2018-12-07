/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author luis
 */
@Entity
@Table(name = "solicitud_articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudArticulo.findAll", query = "SELECT s FROM SolicitudArticulo s")
    , @NamedQuery(name = "SolicitudArticulo.findById", query = "SELECT s FROM SolicitudArticulo s WHERE s.id = :id")
    , @NamedQuery(name = "SolicitudArticulo.findByCantidad", query = "SELECT s FROM SolicitudArticulo s WHERE s.cantidad = :cantidad")})
public class SolicitudArticulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "id_articulo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Articulo idArticulo;
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Solicitud idSolicitud;
    @JoinColumn(name = "id_unidad_medida", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UnidadMedida idUnidadMedida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolicitud")
    private List<OrdenSolicitud> ordenSolicitudList;

    public SolicitudArticulo() {
    }

    public SolicitudArticulo(Integer id) {
        this.id = id;
    }

    public SolicitudArticulo(Integer id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }
    
    public SolicitudArticulo(int cantidad, Articulo idArticulo,UnidadMedida idUnidadMedida){
        this.cantidad = cantidad;
        this.idArticulo = idArticulo;
        this.idUnidadMedida = idUnidadMedida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Articulo getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulo idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Solicitud getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Solicitud idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public UnidadMedida getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(UnidadMedida idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    @XmlTransient
    public List<OrdenSolicitud> getOrdenSolicitudList() {
        return ordenSolicitudList;
    }

    public void setOrdenSolicitudList(List<OrdenSolicitud> ordenSolicitudList) {
        this.ordenSolicitudList = ordenSolicitudList;
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
        if (!(object instanceof SolicitudArticulo)) {
            return false;
        }
        SolicitudArticulo other = (SolicitudArticulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "Articulo: "+ idArticulo.getDescripcion() + " , Cantidad: " + cantidad;
    }   
}
