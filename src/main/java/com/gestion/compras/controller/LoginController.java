/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.controller;


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
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            user = ejbUsuarioFacade.iniciarSesion(usuario); 
            
            
            switch(user.getIdTipoUsuario().getDescripcion()){
                case "ADMIN": 
                    context.getExternalContext().redirect("view/admin/mantenimientos/Inicio/indexAdmin.xhtml");
                    context.getExternalContext().getSessionMap().put("usuario", user);
                   
                    break;
                    
                case "EMPLEADO":
                    context.getApplication().getNavigationHandler().handleNavigation(context, null, "view/empleado/indexEmpleado.xhtml");                
                    context.getExternalContext().getSessionMap().put("usuario", user);
                    
                    break;
                    
                default:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Credenciales incorrectas", "error"));
                    
                    break;      
            }
                  
        } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Credenciales incorrectas", "error"));
        }  
    }
}
