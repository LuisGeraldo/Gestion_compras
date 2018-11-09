/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.controller;


import com.gestion.compras.ejb.EmpleadoFacade;
import com.gestion.compras.ejb.InventarioFacade;
import com.gestion.compras.ejb.OrdenCompraFacade;
import com.gestion.compras.ejb.OrdenSolicitudFacade;
import com.gestion.compras.ejb.SolicitudArticuloFacade;
import com.gestion.compras.ejb.SolicitudFacade;
import com.gestion.compras.entities.Articulo;
import com.gestion.compras.entities.Departamento;
import com.gestion.compras.entities.Empleado;
import com.gestion.compras.entities.Estado;
import com.gestion.compras.entities.Inventario;
import com.gestion.compras.entities.OrdenCompra;
import com.gestion.compras.entities.Solicitud;
import com.gestion.compras.entities.SolicitudArticulo;
import com.gestion.compras.entities.UnidadMedida;
import com.gestion.compras.entities.Usuario;
import java.io.Serializable;
import java.util.Calendar;
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
    
    @EJB
    private InventarioFacade ejbInventarioFacade;
    
    @EJB
    private OrdenCompraFacade ejbOrdenCompraFacade;
               
    @EJB
    private OrdenSolicitudFacade ejbOrdenSolicitudFacade;
    
    Empleado empleado;
    
    Solicitud solicitud;
    
    Articulo articulo[];
    
    Articulo articuloSeleccionado[];
    
    SolicitudArticulo solicitudArticulo;
    
    OrdenCompra ordenCompra;
    
    

    @PostConstruct
    public void inti(){
        empleado = new Empleado();
        solicitud = new Solicitud();
        solicitudArticulo = new SolicitudArticulo();
//        invetario = new Inventario();
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

    public Articulo[] getArticulo() {
        if(articulo == null){
            articulo = new Articulo[50];
        }
        
        return articulo;
    }

    public void setArticulo(Articulo[] articulo) {
        this.articulo = articulo;
    }

    public void sessionInfo(){
           FacesContext context = FacesContext.getCurrentInstance();
          
        try {
           Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
           empleado = ejbEmpleadoFacade.empleadoWithCedula(usuario);         
        } catch (Exception e) {
            throw e;
        }   
    }
    
    public void solicitar(){
        Inventario inventario;
       
        Calendar fechaActual = Calendar.getInstance();
        
//        invetario = new Inventario();
        try {
            
            //Creo la solicitud    
             solicitud.setFechaSolicitud(fechaActual.get(Calendar.DAY_OF_MONTH)+"/"+(fechaActual.get(Calendar.MONTH) + 1)+"/"+fechaActual.get(Calendar.YEAR));
             solicitud.setIdDepatamento(new Departamento(empleado.getIdEmpleado().getId()));
             solicitud.setIdEmpleado(new Empleado(empleado.getId()));
             solicitud.setIdEstado(new Estado(1));
             ejbSolicitudFacade.create(solicitud);
             int idSolicitud = solicitud.getId();
            
            
            for(Articulo ar: articulo){
                inventario = ejbInventarioFacade.invetarioWithArticulo(new Articulo(ar.getId()));
                
                if(inventario.getExistencia() == 0){
                    
//                    ordenCompra.setFecha(fechaActual.get(Calendar.DAY_OF_MONTH)+"/"+(fechaActual.get(Calendar.MONTH) + 1)+"/"+fechaActual.get(Calendar.YEAR));
//                   
//                    ordenCompra.setMontoTotal(inventario.getCostoUnitario()); 
//                    ordenCompra.setIdEstado(new Estado(1));
//                    
//                    ejbOrdenCompraFacade.create(ordenCompra);
//                    int idOrden = ordenCompra.getId();
//                    
                    
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay "+ ar.getDescripcion() , "Solitud"));
               
                
                }else{
                    
                    solicitudArticulo.setCantidad(10);
                    solicitudArticulo.setIdArticulo(new Articulo(ar.getId()));
                    solicitudArticulo.setIdSolicitud(new Solicitud(idSolicitud));
                    solicitudArticulo.setIdUnidadMedida(new UnidadMedida(1));
                    inventario.setExistencia(inventario.getExistencia() - 10);
                    ejbInventarioFacade.edit(inventario);
                    ejbSolicituArticulo.create(solicitudArticulo); 
                     
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud completada", "Solitud"));
                } 
            }
            
        } catch (Exception e) {
            throw e;
        } 
    } 
    
    
    public void agregar(){
        
        for(Articulo ar: articulo){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item "+ar.getDescripcion(), "Solitud"));
            
        }
    }
    
}
