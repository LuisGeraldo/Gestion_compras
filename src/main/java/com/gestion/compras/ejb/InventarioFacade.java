/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.ejb;

import com.gestion.compras.entities.Articulo;
import com.gestion.compras.entities.Inventario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author luis
 */
@Stateless
public class InventarioFacade extends AbstractFacade<Inventario> {

    @PersistenceContext(unitName = "com.record.project_prjGestionCompras_war_1.0-versionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarioFacade() {
        super(Inventario.class);
    }
    
    public Inventario invetarioWithArticulo(Articulo articulo){
         Inventario inventario = null;
        try {
            String consulta = "FROM Inventario u WHERE u.idArticulo = ?1";
            Query query  = em.createQuery(consulta);
            query.setParameter(1, articulo);
            
            List<Inventario> listInventario = query.getResultList();
            
            if(!listInventario.isEmpty()){
                inventario = listInventario.get(0);
            }
        } catch (Exception e) {
            throw  e;
        }
       return inventario;
    }
}
