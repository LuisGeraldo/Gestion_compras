/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.ejb;

import com.gestion.compras.entities.Solicitud;
import com.gestion.compras.entities.SolicitudArticulo;
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
public class SolicitudArticuloFacade extends AbstractFacade<SolicitudArticulo> {

    @PersistenceContext(unitName = "com.record.project_prjGestionCompras_war_1.0-versionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudArticuloFacade() {
        super(SolicitudArticulo.class);
    }
    
    public List<SolicitudArticulo> articulosPorSolicitud(int idSolicitud){
        List<SolicitudArticulo> articulosSolicitud = null;
        
        try {
            String consulta = "FROM SolicitudArticulo u WHERE u.idSolicitud = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, new Solicitud(idSolicitud));
            
            articulosSolicitud = query.getResultList();
            
        } catch (Exception e) {
            throw e;
        }
        return articulosSolicitud;
    }
    
    
}
