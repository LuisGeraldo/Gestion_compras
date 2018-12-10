/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.ejb;

import com.gestion.compras.entities.OrdenCompra;
import com.gestion.compras.entities.OrdenCompraArticulo;
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
public class OrdenArticuloFacade extends AbstractFacade<OrdenCompraArticulo>{
    
    @PersistenceContext(unitName = "com.record.project_prjGestionCompras_war_1.0-versionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public OrdenArticuloFacade(){
        super(OrdenCompraArticulo.class);
    }
    
    public List<OrdenCompraArticulo> articulosPorSolicitud(int idSolicitud){
        List<OrdenCompraArticulo> ordenComprasArticulo = null;
        
        try {
            String consulta = "FROM OrdenCompraArticulo u WHERE u.idOrdenCompra = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, new OrdenCompra(idSolicitud));
            
            ordenComprasArticulo = query.getResultList();
            
        } catch (Exception e) {
            throw e;
        }
        return ordenComprasArticulo;
    }
    
    public List<OrdenCompraArticulo> listOrdenCompraArticulo(){
         List<OrdenCompraArticulo>  listOrdenArticulos = null;
        try {
            String consulta = "SELECT o FROM OrdenCompraArticulo o";
            Query query = em.createQuery(consulta);
            listOrdenArticulos = query.getResultList();
            
        } catch (Exception e) {
        
        }
        
        return listOrdenArticulos;
    }   
}
