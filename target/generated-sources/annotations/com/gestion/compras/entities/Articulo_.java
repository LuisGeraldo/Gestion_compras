package com.gestion.compras.entities;

import com.gestion.compras.entities.Estado;
import com.gestion.compras.entities.Inventario;
import com.gestion.compras.entities.Marca;
import com.gestion.compras.entities.SolicitudArticulo;
import com.gestion.compras.entities.UnidadMedida;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T15:10:39")
@StaticMetamodel(Articulo.class)
public class Articulo_ { 

    public static volatile SingularAttribute<Articulo, String> descripcion;
    public static volatile SingularAttribute<Articulo, Estado> idEstado;
    public static volatile ListAttribute<Articulo, SolicitudArticulo> solicitudArticuloList;
    public static volatile ListAttribute<Articulo, Inventario> inventarioList;
    public static volatile SingularAttribute<Articulo, UnidadMedida> idUnidadMedida;
    public static volatile SingularAttribute<Articulo, Integer> id;
    public static volatile SingularAttribute<Articulo, Marca> idMarca;

}