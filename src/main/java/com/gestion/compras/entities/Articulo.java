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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author luis
 */
@Entity
@Table(name = "articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a")
    , @NamedQuery(name = "Articulo.findById", query = "SELECT a FROM Articulo a WHERE a.id = :id")
    , @NamedQuery(name = "Articulo.findByDescripcion", query = "SELECT a FROM Articulo a WHERE a.descripcion = :descripcion")})
public class Articulo implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<OrdenCompraArticulo> ordenCompraArticuloCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private List<SolicitudArticulo> solicitudArticuloList;
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Estado idEstado;
    @JoinColumn(name = "id_marca", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Marca idMarca;
    @JoinColumn(name = "id_unidad_medida", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UnidadMedida idUnidadMedida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private List<Inventario> inventarioList;

    public Articulo() {
    }

    public Articulo(Integer id) {
        this.id = id;
    }

    public Articulo(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<SolicitudArticulo> getSolicitudArticuloList() {
        return solicitudArticuloList;
    }

    public void setSolicitudArticuloList(List<SolicitudArticulo> solicitudArticuloList) {
        this.solicitudArticuloList = solicitudArticuloList;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
        this.idMarca = idMarca;
    }

    public UnidadMedida getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(UnidadMedida idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    @XmlTransient
    public List<Inventario> getInventarioList() {
        return inventarioList;
    }

    public void setInventarioList(List<Inventario> inventarioList) {
        this.inventarioList = inventarioList;
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
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    @XmlTransient
    public Collection<OrdenCompraArticulo> getOrdenCompraArticuloCollection() {
        return ordenCompraArticuloCollection;
    }

    public void setOrdenCompraArticuloCollection(Collection<OrdenCompraArticulo> ordenCompraArticuloCollection) {
        this.ordenCompraArticuloCollection = ordenCompraArticuloCollection;
    }
    
}
