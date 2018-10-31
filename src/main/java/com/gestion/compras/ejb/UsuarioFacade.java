/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.ejb;

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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "com.record.project_prjGestionCompras_war_1.0-versionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
     public Usuario iniciarSesion(Usuario usuario){
     Usuario user = null;
     
        try {
            String consulta = "FROM Usuario u WHERE u.cedula = ?1 and u.password = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, usuario.getCedula());
            query.setParameter(2, usuario.getPassword());
            
            List<Usuario> listUsuario = query.getResultList();
            
            if(!listUsuario.isEmpty()){
                user = listUsuario.get(0);
            }     
        } catch (Exception e) {
            throw e;
        }
        return  user;
    } 
}
