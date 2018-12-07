/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.controller;

import com.gestion.compras.entities.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author luis
 */

@Named
@ViewScoped

public class SessionInf implements Serializable{
    
    Usuario usuario;

    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }
    
    
    public Usuario getUsuario() {
        if(usuario == null){
            usuario = new Usuario();
        }
        
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void sessionInfo(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
          
            usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            
        } catch (Exception e) {
        
        }  
    } 
    
}
