package com.gestion.compras.entities;

import com.gestion.compras.entities.Estado;
import com.gestion.compras.entities.Solicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-06T23:14:50")
@StaticMetamodel(Departamento.class)
public class Departamento_ { 

    public static volatile SingularAttribute<Departamento, Estado> idEstado;
    public static volatile SingularAttribute<Departamento, Integer> id;
    public static volatile ListAttribute<Departamento, Solicitud> solicitudList;
    public static volatile SingularAttribute<Departamento, String> nombre;

}