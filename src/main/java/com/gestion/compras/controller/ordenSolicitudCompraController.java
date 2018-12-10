/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.controller;

import com.gestion.compras.controller.util.JsfUtil;
import com.gestion.compras.ejb.InventarioFacade;
import com.gestion.compras.ejb.OrdenCompraFacade;
import com.gestion.compras.entities.Articulo;
import com.gestion.compras.entities.Estado;
import com.gestion.compras.entities.Inventario;
import com.gestion.compras.entities.OrdenCompra;
import java.io.Serializable;
import java.util.Calendar;
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
public class ordenSolicitudCompraController implements Serializable{
    @EJB
    private OrdenCompraFacade ejbOrdenCompraFacade;
    
    @EJB
    private InventarioFacade ejInvenetarioFacade;
    
    private OrdenCompra ordenCompra;
    
    public void ordenCompra(List<SolicituArticulo> solictudArticulo){
//        FacesContext context = FacesContext.getCurrentInstance();
         Calendar calendar = Calendar.getInstance();
         Inventario inventario = null;
        try {
            for(SolicituArticulo sr: solictudArticulo){
                inventario = ejInvenetarioFacade.invetarioWithArticulo(sr.getSolicitudArticulo().getIdArticulo());
                
                if(inventario.getExistencia() == 2){
                    ordenCompra.setFecha(calendar.getCalendarType());
                    ordenCompra.setIdEstado(new Estado(3));
//                    ordenCompra.set
                    
                }else{
                    inventario.setExistencia((inventario.getExistencia() - sr.getSolicitudArticulo().getCantidad()));
                    ejInvenetarioFacade.edit(inventario);
                 }
            }
            JsfUtil.addSuccessMessage("Solicitud aprobada");
        } catch (Exception e) {
             JsfUtil.addSuccessMessage("No se aprobar la solicitud");
        }
    }
}
