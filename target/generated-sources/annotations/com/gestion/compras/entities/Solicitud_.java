package com.gestion.compras.entities;

import com.gestion.compras.entities.Departamento;
import com.gestion.compras.entities.Empleado;
import com.gestion.compras.entities.Estado;
import com.gestion.compras.entities.SolicitudArticulo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-30T13:47:19")
@StaticMetamodel(Solicitud.class)
public class Solicitud_ { 

    public static volatile SingularAttribute<Solicitud, Departamento> idDepatamento;
    public static volatile SingularAttribute<Solicitud, Estado> idEstado;
    public static volatile SingularAttribute<Solicitud, String> fechaSolicitud;
    public static volatile SingularAttribute<Solicitud, Empleado> idEmpleado;
    public static volatile ListAttribute<Solicitud, SolicitudArticulo> solicitudArticuloList;
    public static volatile SingularAttribute<Solicitud, Integer> id;

}