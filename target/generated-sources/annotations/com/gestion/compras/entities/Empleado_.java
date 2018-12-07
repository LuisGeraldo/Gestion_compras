package com.gestion.compras.entities;

import com.gestion.compras.entities.Departamento;
import com.gestion.compras.entities.Estado;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-06T23:14:50")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, Departamento> idDepatamento;
    public static volatile SingularAttribute<Empleado, Estado> idEstado;
    public static volatile SingularAttribute<Empleado, String> cedula;
    public static volatile SingularAttribute<Empleado, Integer> id;
    public static volatile SingularAttribute<Empleado, String> nombre;

}