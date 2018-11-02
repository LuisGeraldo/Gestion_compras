package com.gestion.compras.entities;

import com.gestion.compras.entities.Articulo;
import com.gestion.compras.entities.OrdenSolicitud;
import com.gestion.compras.entities.Solicitud;
import com.gestion.compras.entities.UnidadMedida;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-01T19:35:20")
@StaticMetamodel(SolicitudArticulo.class)
public class SolicitudArticulo_ { 

    public static volatile SingularAttribute<SolicitudArticulo, Articulo> idArticulo;
    public static volatile ListAttribute<SolicitudArticulo, OrdenSolicitud> ordenSolicitudList;
    public static volatile SingularAttribute<SolicitudArticulo, UnidadMedida> idUnidadMedida;
    public static volatile SingularAttribute<SolicitudArticulo, Integer> id;
    public static volatile SingularAttribute<SolicitudArticulo, Integer> cantidad;
    public static volatile SingularAttribute<SolicitudArticulo, Solicitud> idSolicitud;

}