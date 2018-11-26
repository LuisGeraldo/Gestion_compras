package com.gestion.compras.entities;

import com.gestion.compras.entities.Articulo;
import com.gestion.compras.entities.Departamento;
import com.gestion.compras.entities.Empleado;
import com.gestion.compras.entities.Marca;
import com.gestion.compras.entities.OrdenCompra;
import com.gestion.compras.entities.Proveedor;
import com.gestion.compras.entities.Solicitud;
import com.gestion.compras.entities.UnidadMedida;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T15:10:39")
@StaticMetamodel(Estado.class)
public class Estado_ { 

    public static volatile SingularAttribute<Estado, String> descripcion;
    public static volatile ListAttribute<Estado, Proveedor> proveedorList;
    public static volatile ListAttribute<Estado, Articulo> articuloList;
    public static volatile ListAttribute<Estado, Departamento> departamentoList;
    public static volatile ListAttribute<Estado, OrdenCompra> ordenCompraList;
    public static volatile ListAttribute<Estado, UnidadMedida> unidadMedidaList;
    public static volatile ListAttribute<Estado, Empleado> empleadoList;
    public static volatile SingularAttribute<Estado, Integer> id;
    public static volatile ListAttribute<Estado, Marca> marcaList;
    public static volatile ListAttribute<Estado, Solicitud> solicitudList;
    public static volatile SingularAttribute<Estado, String> nombre;

}