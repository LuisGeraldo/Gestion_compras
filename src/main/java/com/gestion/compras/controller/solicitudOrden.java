/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.controller;

import com.gestion.compras.controller.util.JsfUtil;
import com.gestion.compras.ejb.EmpleadoFacade;
import com.gestion.compras.ejb.InventarioFacade;
import com.gestion.compras.ejb.OrdenArticuloFacade;
import com.gestion.compras.ejb.OrdenCompraFacade;
import com.gestion.compras.ejb.SolicitudArticuloFacade;
import com.gestion.compras.ejb.SolicitudFacade;
import com.gestion.compras.entities.Empleado;
import com.gestion.compras.entities.Estado;
import com.gestion.compras.entities.Inventario;
import com.gestion.compras.entities.OrdenCompra;
import com.gestion.compras.entities.OrdenCompraArticulo;
import com.gestion.compras.entities.Solicitud;
import com.gestion.compras.entities.SolicitudArticulo;
import com.gestion.compras.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author luis
 */

@Named
@ViewScoped
public class solicitudOrden implements Serializable{
    
    @EJB
    private EmpleadoFacade ejbEmpleadoFacade;
    
    @EJB
    private OrdenCompraFacade ejbOrdenCompraFacade;
          
    @EJB
    private SolicitudArticuloFacade ejSolicitudArticuloFacade;
        
    @EJB
    private InventarioFacade ejInventarioFacade;
    
    @EJB
    private SolicitudFacade ejbSolicitudFacade;
    
    @EJB
    private OrdenArticuloFacade ejOrdenArticuloFacade;
    
    private List<OrdenCompraArticulo> ordenCompraArticulo;
    
    List<SolicitudArticulo> listSolictudArticulo = null;
       
    private OrdenCompra ordenCompra;
    
    private Solicitud solicitud;
    
    private Usuario usuario;
    
    private Empleado empleado;
    
    private Inventario articuloInventario;

    public Usuario getUsuario() {
        if(usuario == null){
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    
    
    public String ordenCompra(int id){
        ordenCompraArticulo = new ArrayList<>();
        
        try { 
            solicitud = ejbSolicitudFacade.solicitudPorId(id);
            listSolictudArticulo = ejSolicitudArticuloFacade.articulosPorSolicitud(id);  
            
              if(solicitud.getIdEstado().getId() == 3){
                   JsfUtil.addErrorMessage("La solicitud ya estaba aprobada, no se puede volver a aprobar");
                   return "List";
                    
              }else{
                   for(SolicitudArticulo solicitudArticulo: listSolictudArticulo){
                      articuloInventario = ejInventarioFacade.invetarioWithArticulo(solicitudArticulo.getIdArticulo());

                    if(articuloInventario.getExistencia() <= 2){
                        ordenCompraArticulo.add(new OrdenCompraArticulo(solicitudArticulo.getCantidad(), solicitudArticulo.getIdArticulo()));
               
                    }else if(solicitudArticulo.getCantidad()  <= (articuloInventario.getExistencia() + 2)){
                        articuloInventario.setExistencia((articuloInventario.getExistencia() - solicitudArticulo.getCantidad()));
                        ejInventarioFacade.edit(articuloInventario);
                        
                    }else{
                        
                      int cantidadArticulos = (solicitudArticulo.getCantidad() - (articuloInventario.getExistencia() + 2));
                      articuloInventario.setExistencia((articuloInventario.getExistencia() + 2) - articuloInventario.getExistencia());
                      ejInventarioFacade.edit(articuloInventario);
                     
                      ordenCompraArticulo.add(new OrdenCompraArticulo(cantidadArticulos, solicitudArticulo.getIdArticulo()));
                      
                    }   
               }
           
                solicitud.setIdEstado(new Estado(3));
                ejbSolicitudFacade.edit(solicitud);
                crearOrdenCompra(ordenCompraArticulo, empleado);
                
                JsfUtil.addSuccessMessage("Solicitud aprobada");
            return "List";
           }
              
        } catch (Exception e) {
            JsfUtil.addSuccessMessage("Solicitud error" + e);
        }
        
        return "";
    }
    
    public void crearOrdenCompra(List<OrdenCompraArticulo> listOrdenCompraArticulo, Empleado empleado){
        ordenCompra = new OrdenCompra();
        
        try {
            if(!listOrdenCompraArticulo.isEmpty()){
                  ordenCompra.setFecha(JsfUtil.fecha());
                  ordenCompra.setIdEmpleado(empleado);
                  ordenCompra.setIdEstado(new Estado(4));

                  ejbOrdenCompraFacade.create(ordenCompra);

               for(OrdenCompraArticulo orden: listOrdenCompraArticulo){
                   orden.setIdOrdenCompra(new OrdenCompra(ordenCompra.getId()));
                   ejOrdenArticuloFacade.create(orden);
               }
               JsfUtil.addSuccessMessage("Algunos articulos no estaban en existencia, se genero una orden de compra");
             }
        } catch (Exception e) {
            JsfUtil.addErrorMessage("No se pudo crear la orden de compra" + e);
        }
    }
    
    public void obtenerUsuario(){
         usuario = (Usuario) JsfUtil.getSession("usuario");
         empleado = ejbEmpleadoFacade.empleadoWithCedula(usuario);
    }  
}
