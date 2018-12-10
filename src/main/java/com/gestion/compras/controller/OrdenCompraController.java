package com.gestion.compras.controller;

import com.gestion.compras.entities.OrdenCompra;
import com.gestion.compras.controller.util.JsfUtil;
import com.gestion.compras.controller.util.PaginationHelper;
import com.gestion.compras.ejb.OrdenArticuloFacade;
import com.gestion.compras.ejb.OrdenCompraFacade;
import com.gestion.compras.entities.OrdenCompraArticulo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("ordenCompraController")
@SessionScoped
public class OrdenCompraController implements Serializable {

    private OrdenCompra current;
    private DataModel items = null;
    @EJB
    private com.gestion.compras.ejb.OrdenCompraFacade ejbFacade;
    
    @EJB
    private OrdenArticuloFacade ejbArticuloFacade;
    
    private List<OrdenCompraArticulo> listOrdenCompraArticulo = null;
    
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    List<OrdenCompraArticulo> listOrdenCompras = null;

    public OrdenCompraController() {
    }

    public List<OrdenCompraArticulo> getListOrdenCompras(int id) {
        listOrdenCompras = ejbArticuloFacade.articulosPorSolicitud(id);
        
        return listOrdenCompras;
    }

    public void setListOrdenCompras(List<OrdenCompraArticulo> listOrdenCompras) {
        this.listOrdenCompras = listOrdenCompras;
    }

    public List<OrdenCompraArticulo> getListOrdenCompraArticulo() {
        List<OrdenCompraArticulo> listOrdenCompra;
        
        listOrdenCompra = ejbArticuloFacade.listOrdenCompraArticulo();
        
        
        for(OrdenCompraArticulo or: listOrdenCompra){
            
          if(or.getIdArticulo().getDescripcion().equals(listOrdenCompra.get(listOrdenCompra.size() - 1).getIdArticulo().getDescripcion())){
              listOrdenCompraArticulo.add(new OrdenCompraArticulo(or.getCantidad(), or.getIdArticulo()));              
          }
            
        }
        
        return listOrdenCompraArticulo;
    }

    public void setListOrdenCompraArticulo(List<OrdenCompraArticulo> listOrdenCompraArticulo) {
        this.listOrdenCompraArticulo = listOrdenCompraArticulo;
    }

    
    
    
    public OrdenCompra getSelected() {
        if (current == null) {
            current = new OrdenCompra();
            selectedItemIndex = -1;
        }
        return current;
    }

    private OrdenCompraFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (OrdenCompra) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new OrdenCompra();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrdenCompraCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (OrdenCompra) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrdenCompraUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (OrdenCompra) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrdenCompraDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public OrdenCompra getOrdenCompra(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = OrdenCompra.class)
    public static class OrdenCompraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrdenCompraController controller = (OrdenCompraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ordenCompraController");
            return controller.getOrdenCompra(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof OrdenCompra) {
                OrdenCompra o = (OrdenCompra) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + OrdenCompra.class.getName());
            }
        }

    }
    
    
    
    

}
