/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.ejb;

import com.gestion.compras.entities.Estado;
import com.gestion.compras.entities.Solicitud;
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
public class SolicitudFacade extends AbstractFacade<Solicitud> {

    @PersistenceContext(unitName = "com.record.project_prjGestionCompras_war_1.0-versionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudFacade() {
        super(Solicitud.class);
    }
    
    public int noSolicitud(int idEstado){
        int cantidad = 0;
        try {
           String consulta = "FROM Solicitud u WHERE u.idEstado = ?1";
           
            Query query = em.createQuery(consulta);
            query.setParameter(1, new Estado(idEstado));
            
            List<Estado> listSolicitudes = query.getResultList();
            cantidad = listSolicitudes.size();
            
        } catch (Exception e) {
            throw e;
        }
 
        return cantidad;
    }
    
}
