/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.ejb;

import com.gestion.compras.entities.Empleado;
import com.gestion.compras.entities.Usuario;
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
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "com.record.project_prjGestionCompras_war_1.0-versionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
    
    public Empleado empleadoWithCedula(Usuario usuario){
        Empleado empleado = null;
        try {
            String consulta = "FROM Empleado u WHERE u.cedula = ?1";
            Query query  = em.createQuery(consulta);
            query.setParameter(1, usuario.getCedula());
            
            List<Empleado> listEmpleado = query.getResultList();
            
            if(!listEmpleado.isEmpty()){
                empleado = listEmpleado.get(0);
            }
        } catch (Exception e) {
            throw  e;
        }
       return empleado;
    }
}
