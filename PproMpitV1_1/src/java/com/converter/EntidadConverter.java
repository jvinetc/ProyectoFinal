/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ppro.modelo.PproEntidadFinanciera;
import ppro.servicio.PproEntidadFinancieraFacade;

/**
 *
 * @author casa
 */
@FacesConverter(value = "entidadConverter")
public class EntidadConverter implements Converter{
    
    PproEntidadFinancieraFacade financieraFacade= lookupFormaPagoServicioBean();
    private List<PproEntidadFinanciera> getEntidades(){
        return financieraFacade.findAll();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        for(PproEntidadFinanciera entidadF:getEntidades()){
            if(entidadF.getEntFinId().equals(Integer.parseInt(value))){
                return entidadF;
            }
        }
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value!=null){
            return String.valueOf(((PproEntidadFinanciera)value).getEntFinId());
        }else{
            return null;
        }
    }
    
    private PproEntidadFinancieraFacade lookupFormaPagoServicioBean() {
        try {
            Context c = new InitialContext();
            return (PproEntidadFinancieraFacade) c.lookup("java:global/PproMpitV1_1/PproEntidadFinancieraFacade!ppro.servicio.PproEntidadFinancieraFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
