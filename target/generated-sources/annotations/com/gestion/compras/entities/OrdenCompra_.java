package com.gestion.compras.entities;

import com.gestion.compras.entities.Empleado;
import com.gestion.compras.entities.Estado;
import com.gestion.compras.entities.OrdenCompraArticulo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-06T23:14:50")
@StaticMetamodel(OrdenCompra.class)
public class OrdenCompra_ { 

    public static volatile SingularAttribute<OrdenCompra, String> fecha;
    public static volatile SingularAttribute<OrdenCompra, Estado> idEstado;
    public static volatile SingularAttribute<OrdenCompra, Empleado> idEmpleado;
    public static volatile CollectionAttribute<OrdenCompra, OrdenCompraArticulo> ordenCompraArticuloCollection;
    public static volatile SingularAttribute<OrdenCompra, Integer> id;

}