/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.controller;

import com.gestion.compras.controller.util.JsfUtil;
import com.gestion.compras.entities.Usuario;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author luis
 */

@Named
@ViewScoped
public class ControlAccesoClass implements Serializable{
   
    Usuario user;
  
    public void onlyAdmin() {
        try {
           user = new Usuario();
           user = (Usuario) JsfUtil.getSession("usuario");
           
           if(user == null || !user.getIdTipoUsuario().getDescripcion().equals("ADMIN")){
               JsfUtil.redirect("./../../../../alert.xhtml");
           }   
           
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Ocurrio un error" + e);
        }
    }
       
    public void onlyEmpleado(){
        try {
           user = new Usuario();        
           user = (Usuario) JsfUtil.getSession("usuario");
           
           if(user == null || !user.getIdTipoUsuario().getDescripcion().equals("EMPLEADO")){
               JsfUtil.redirect("./../../../../NoPermiso.xhtml");
           }
           
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Ocurrio un error" + e);
        }
    }    
                
    public void cerraSesion(){           
     FacesContext context = FacesContext.getCurrentInstance();
        try {
            JsfUtil.sessionRemove("usuario");
            JsfUtil.redirect("./../../../../LoginView.xhtml");
        } catch (Exception e) {
           JsfUtil.addErrorMessage("Ocurrio un error" + e);
        }
    }     
}
