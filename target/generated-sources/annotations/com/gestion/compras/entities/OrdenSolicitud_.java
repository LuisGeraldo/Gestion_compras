package com.gestion.compras.entities;

import com.gestion.compras.entities.OrdenCompra;
import com.gestion.compras.entities.SolicitudArticulo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-06T23:14:50")
@StaticMetamodel(OrdenSolicitud.class)
public class OrdenSolicitud_ { 

    public static volatile SingularAttribute<OrdenSolicitud, OrdenCompra> idOrdenCompra;
    public static volatile SingularAttribute<OrdenSolicitud, Integer> id;
    public static volatile SingularAttribute<OrdenSolicitud, SolicitudArticulo> idSolicitud;

}