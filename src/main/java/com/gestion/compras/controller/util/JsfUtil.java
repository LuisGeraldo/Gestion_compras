package com.gestion.compras.controller.util;

import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

public class JsfUtil {

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }
    
    public static Object getSession(String key){
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }
    
    public static Object setSession(Object object, String key){
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, object);
    }
    
    public static Object sessionRemove(String key){
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
    }
    
    public static void redirect(String url) throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }
    
    public static String fecha(){
        Calendar fechaActual = Calendar.getInstance();
        return fechaActual.get(Calendar.DAY_OF_MONTH)+"/"+(fechaActual.get(Calendar.MONTH) + 1)+"/"+fechaActual.get(Calendar.YEAR);
    }
}
