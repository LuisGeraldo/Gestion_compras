/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.entities;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "orden_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenCompra.findAll", query = "SELECT o FROM OrdenCompra o")
    , @NamedQuery(name = "OrdenCompra.findById", query = "SELECT o FROM OrdenCompra o WHERE o.id = :id")
    , @NamedQuery(name = "OrdenCompra.findByFecha", query = "SELECT o FROM OrdenCompra o WHERE o.fecha = :fecha")})
public class OrdenCompra implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrdenCompra")
    private Collection<OrdenCompraArticulo> ordenCompraArticuloCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "fecha")
    private String fecha;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empleado idEmpleado;
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Estado idEstado;

    public OrdenCompra() {
    }

    public OrdenCompra(Integer id) {
        this.id = id;
    }

    public OrdenCompra(Integer id, String fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
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
        if (!(object instanceof OrdenCompra)) {
            return false;
        }
        OrdenCompra other = (OrdenCompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestion.compras.entities.OrdenCompra[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<OrdenCompraArticulo> getOrdenCompraArticuloCollection() {
        return ordenCompraArticuloCollection;
    }

    public void setOrdenCompraArticuloCollection(Collection<OrdenCompraArticulo> ordenCompraArticuloCollection) {
        this.ordenCompraArticuloCollection = ordenCompraArticuloCollection;
    }
    
}
