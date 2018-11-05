package com.gestion.compras.entities;

import com.gestion.compras.entities.Articulo;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-05T03:57:49")
@StaticMetamodel(Inventario.class)
public class Inventario_ { 

    public static volatile SingularAttribute<Inventario, Articulo> idArticulo;
    public static volatile SingularAttribute<Inventario, Integer> existencia;
    public static volatile SingularAttribute<Inventario, Integer> id;
    public static volatile SingularAttribute<Inventario, BigInteger> costoUnitario;

}