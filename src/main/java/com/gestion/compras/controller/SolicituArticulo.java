/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.controller;


import com.gestion.compras.ejb.EmpleadoFacade;
import com.gestion.compras.ejb.SolicitudArticuloFacade;
import com.gestion.compras.ejb.SolicitudFacade;
import com.gestion.compras.entities.Departamento;
import com.gestion.compras.entities.Empleado;
import com.gestion.compras.entities.Estado;
import com.gestion.compras.entities.OrdenCompra;
import com.gestion.compras.entities.Solicitud;
import com.gestion.compras.entities.SolicitudArticulo;
import com.gestion.compras.entities.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author luis
 */

@Named
@ViewScoped

public class SolicituArticulo implements Serializable{
    @EJB
    private EmpleadoFacade ejbEmpleadoFacade;
    
    @EJB
    private SolicitudFacade ejbSolicitudFacade;
    
    @EJB
    private SolicitudArticuloFacade ejbSolicituArticulo;
     
    Empleado empleado;
    
    Solicitud solicitud;
      
    OrdenCompra ordenCompra;
    
    List<SolicitudArticulo> listSolicitudArticulo = null;
    
    List<Solicitud> listMisSolicitudes = null; 
    
       
    SolicitudArticulo solicitudArticulo;
    
    

    public SolicitudArticulo getSolicitudArticulo() {
        if(solicitudArticulo == null){
            solicitudArticulo = new SolicitudArticulo();
        }
        return solicitudArticulo;
    }

    public void setSolicitudArticulo(SolicitudArticulo solicitudArticulo) {
        this.solicitudArticulo = solicitudArticulo;
    }

    public List<SolicitudArticulo> getListSolicitudArticulo() {
        return listSolicitudArticulo;
    }

    public void setListSolicitudArticulo(List<SolicitudArticulo> listSolicitudArticulo) {
        this.listSolicitudArticulo = listSolicitudArticulo;
    }

    public List<Solicitud> getListMisSolicitudes() {
        return listMisSolicitudes;
    }

    public void setListMisSolicitudes(List<Solicitud> listMisSolicitudes) {
        this.listMisSolicitudes = listMisSolicitudes;
    }
    
    @PostConstruct
    public void inti(){
        empleado = new Empleado();
        solicitud = new Solicitud();
        listSolicitudArticulo = new ArrayList<>();
        listMisSolicitudes = new ArrayList<>();
    }
    
    public Empleado getEmpleado() {
        if(empleado == null){
            empleado = new Empleado();
        }
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }  

    public void solicitar(){
         Calendar fechaActual = Calendar.getInstance();
        
        try {
          
             solicitud.setFechaSolicitud(fechaActual.get(Calendar.DAY_OF_MONTH)+"/"+(fechaActual.get(Calendar.MONTH) + 1)+"/"+fechaActual.get(Calendar.YEAR));
             solicitud.setIdDepatamento(new Departamento(empleado.getIdDepatamento().getId()));
             solicitud.setIdEmpleado(new Empleado(empleado.getId()));
             solicitud.setIdEstado(new Estado(4));
             ejbSolicitudFacade.create(solicitud);
            
             
             for(SolicitudArticulo solicitudArticulo: listSolicitudArticulo){
                 solicitudArticulo.setIdSolicitud(new Solicitud(solicitud.getId()));
                 ejbSolicituArticulo.create(solicitudArticulo);
                 
             }
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud completada", "Solitud"));
 
          } catch (Exception e) {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud no completada", "Solitud"));
          } 
     }
    
    public void agregar(){        
        listSolicitudArticulo.add(new SolicitudArticulo(solicitudArticulo.getCantidad(), solicitudArticulo.getIdArticulo(), solicitudArticulo.getIdArticulo().getIdUnidadMedida()));
    }
    
    public void remover(){
        listSolicitudArticulo.remove(new SolicitudArticulo(1));
    }
    
    public void sessionInfo(){
           FacesContext context = FacesContext.getCurrentInstance();
          
        try {
           Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
           empleado = ejbEmpleadoFacade.empleadoWithCedula(usuario);
           listMisSolicitudes = ejbSolicitudFacade.misSolicitudes(empleado);
           
        } catch (Exception e) {
            throw e;
        }   
    }    
 
}
