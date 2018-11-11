package com.gestion.compras.entities;

import com.gestion.compras.entities.Estado;
import com.gestion.compras.entities.OrdenSolicitud;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-10T14:33:02")
@StaticMetamodel(OrdenCompra.class)
public class OrdenCompra_ { 

    public static volatile SingularAttribute<OrdenCompra, String> fecha;
    public static volatile SingularAttribute<OrdenCompra, Estado> idEstado;
    public static volatile ListAttribute<OrdenCompra, OrdenSolicitud> ordenSolicitudList;
    public static volatile SingularAttribute<OrdenCompra, Integer> id;
    public static volatile SingularAttribute<OrdenCompra, BigInteger> montoTotal;

}