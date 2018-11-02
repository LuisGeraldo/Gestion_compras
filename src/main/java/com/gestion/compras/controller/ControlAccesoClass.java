/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.controller;

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
        FacesContext context = FacesContext.getCurrentInstance();
        try {
           
           user = new Usuario();
           
           user = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
           
           if(user == null || !user.getIdTipoUsuario().getDescripcion().equals("ADMIN")){
               context.getExternalContext().redirect("./../../../../alert.xhtml");
           }   
           
        } catch (Exception e) {
            
        }
    }
       
       
    public void onlyEmpleado(){
        try {
           FacesContext context = FacesContext.getCurrentInstance();
           user = new Usuario();
           
           user = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
           
           if(user == null || !user.getIdTipoUsuario().getDescripcion().equals("EMPLEADO")){
                context.getExternalContext().redirect("./../../../../NoPermiso.xhtml");
           }   
           
        } catch (Exception e) {
            
        }
    }    
                
    public void cerraSesion(){           
     FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().getSessionMap().remove("usuario");
            context.getExternalContext().redirect("./../../../../LoginView.xhtml");
        } catch (Exception e) {
           
        }
    }     
}
