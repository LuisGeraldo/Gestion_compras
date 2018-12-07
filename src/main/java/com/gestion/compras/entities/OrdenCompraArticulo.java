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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luis
 */
@Entity
@Table(name = "orden_compra_articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenCompraArticulo.findAll", query = "SELECT o FROM OrdenCompraArticulo o")
    , @NamedQuery(name = "OrdenCompraArticulo.findById", query = "SELECT o FROM OrdenCompraArticulo o WHERE o.id = :id")
    , @NamedQuery(name = "OrdenCompraArticulo.findByCantidad", query = "SELECT o FROM OrdenCompraArticulo o WHERE o.cantidad = :cantidad")})
public class OrdenCompraArticulo implements Serializable {

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
    @JoinColumn(name = "id_orden_compra", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private OrdenCompra idOrdenCompra;

    public OrdenCompraArticulo() {
    }

    public OrdenCompraArticulo(Integer id) {
        this.id = id;
    }

    public OrdenCompraArticulo(Integer id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }
    
    public OrdenCompraArticulo(int cantidad, Articulo articulo){
        this.cantidad = cantidad;
        this.idArticulo = articulo;
        
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

    public OrdenCompra getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(OrdenCompra idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
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
        if (!(object instanceof OrdenCompraArticulo)) {
            return false;
        }
        OrdenCompraArticulo other = (OrdenCompraArticulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestion.compras.entities.OrdenCompraArticulo[ id=" + id + " ]";
    }
    
}
