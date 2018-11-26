package com.gestion.compras.entities;

import com.gestion.compras.entities.Articulo;
import com.gestion.compras.entities.Estado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T15:10:39")
@StaticMetamodel(Marca.class)
public class Marca_ { 

    public static volatile SingularAttribute<Marca, String> descripcion;
    public static volatile ListAttribute<Marca, Articulo> articuloList;
    public static volatile SingularAttribute<Marca, Estado> idEstado;
    public static volatile SingularAttribute<Marca, Integer> id;
    public static volatile SingularAttribute<Marca, String> nombre;

}