/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.ejb;

import com.gestion.compras.entities.Empleado;
import com.gestion.compras.entities.Estado;
import com.gestion.compras.entities.Solicitud;
import com.gestion.compras.entities.Usuario;
import java.util.List;
import javax.ejb.EJB;
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
    
    public List<Solicitud> noSolicitud(int idEstado){
         List<Solicitud> listSolicitudes = null;
        try {
            String consulta = "FROM Solicitud u WHERE u.idEstado = ?1";
           
            Query query = em.createQuery(consulta);
            query.setParameter(1, new Estado(idEstado));
            
            listSolicitudes = query.getResultList();
            
        } catch (Exception e) {
            throw e;
        }
        return listSolicitudes;
    }
    
    public List<Solicitud> misSolicitudes(Empleado empleado){
              
        List<Solicitud> listSolicitudes;
          
        try {
           String consulta = "FROM Solicitud u WHERE u.idEmpleado = ?1";
           Query query = em.createQuery(consulta);
           query.setParameter(1, empleado);
            
           listSolicitudes = query.getResultList();
            
        } catch (Exception e) {
            throw e;
        }
        
        return listSolicitudes;
    }
    
     public Solicitud solicitudPorId(int id){
        Solicitud solicitud = null;     
          
        try {
           String consulta = "FROM Solicitud u WHERE u.id = ?1";
           Query query = em.createQuery(consulta);
           query.setParameter(1, id);
            
           List<Solicitud> listSolicitudes = query.getResultList();
            
           if(!listSolicitudes.isEmpty()){
              solicitud = listSolicitudes.get(0);
           }
           
        } catch (Exception e) {
            throw e;
        }
        
        return solicitud;
    }
}
