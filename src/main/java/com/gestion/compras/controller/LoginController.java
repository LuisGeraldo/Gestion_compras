/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.controller;

import com.gestion.compras.controller.util.JsfUtil;
import com.gestion.compras.ejb.UsuarioFacade;
import com.gestion.compras.entities.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author 20162339
 */
@Named
@ViewScoped

public class LoginController implements Serializable{
    @EJB
    private UsuarioFacade ejbUsuarioFacade;
    
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
    
    public void iniciarSesion(){
        Usuario user;
        try {
            user = ejbUsuarioFacade.iniciarSesion(usuario); 
            
            
            switch(user.getIdTipoUsuario().getDescripcion()){
                case "ADMIN": 
                    JsfUtil.setSession(user, "usuario");
                    JsfUtil.redirect("view/admin/mantenimientos/Inicio/indexAdmin.xhtml");
                    break;
                    
                case "EMPLEADO":
                    JsfUtil.setSession(user, "usuario");
                    JsfUtil.redirect("view/empleado/content/index/Empleado.xhtml");
                    break;
                    
                default:
                    JsfUtil.addErrorMessage("Credenciales incorrectas");
                    break;      
            }
                  
        } catch (Exception e) {
                    JsfUtil.addErrorMessage("Credenciales incorrectas");
        }  
    }
}
