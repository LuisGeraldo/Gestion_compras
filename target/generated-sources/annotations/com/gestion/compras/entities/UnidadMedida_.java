package com.gestion.compras.entities;

import com.gestion.compras.entities.Articulo;
import com.gestion.compras.entities.Estado;
import com.gestion.compras.entities.SolicitudArticulo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-01T19:35:20")
@StaticMetamodel(UnidadMedida.class)
public class UnidadMedida_ { 

    public static volatile ListAttribute<UnidadMedida, Articulo> articuloList;
    public static volatile SingularAttribute<UnidadMedida, Estado> idEstado;
    public static volatile ListAttribute<UnidadMedida, SolicitudArticulo> solicitudArticuloList;
    public static volatile SingularAttribute<UnidadMedida, Integer> id;
    public static volatile SingularAttribute<UnidadMedida, String> nombre;

}