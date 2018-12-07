/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.compras.controller.util;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author luis
 */
@Named(value = "componentValidator")
@RequestScoped
public class ComponentValidator implements Serializable{


    @PostConstruct
    public void init(){
        
    }
    
     public void validateCedula(FacesContext f, UIComponent c, Object value){
            String cedula = value.toString();
            if(!validarCedula(cedula)){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cedula es incorrecta", null));
            }           
    }
     
 
     
     public  boolean validarCedula(String cedula) {
        if (cedula == null || cedula.length() != 11 || cedula.equals("00000000000")) {
            return false;
        }
        int suma = 0;
        int division = 0;
        String peso = "1212121212";
        for (int i = 0; i < 10; i++) {
            int mul = (cedula.charAt(i) - '0') * (peso.charAt(i) - '0');
            while (mul > 0) {
                suma += mul % 10;
                mul /= 10;
            }
        }
        division = (suma / 10) * 10;
        if (division < suma) {
            division += 10;
        }
        int digito = division - suma;
        if (digito != cedula.charAt(10) - '0') {
            return false;
        }
        return true;
    }    
 } 

