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
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ppro.modelo.PproFormaPago;
import ppro.servicio.FormaPagoServicio;

/**
 *
 * @author casa
 */
@FacesConverter("formaPagoConverter")
public class FormaPagoConverter implements Converter{

    FormaPagoServicio formaPagoServicio = lookupFormaPagoServicioBean();
    
    private List<PproFormaPago> listaForma= new ArrayList<>();
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        for(PproFormaPago formaPago:getListaForma()){
            if(formaPago.getFpagId().equals(Integer.parseInt(value))){
                return formaPago;
            }
        }
            return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value!=null){
            return String.valueOf(((PproFormaPago)value).getFpagId());
        }else{
            return null;
        }
    }

    private FormaPagoServicio lookupFormaPagoServicioBean() {
        try {
            Context c = new InitialContext();
            return (FormaPagoServicio) c.lookup("java:global/PproMpitV1_1/FormaPagoServicio!ppro.servicio.FormaPagoServicio");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public List<PproFormaPago> getListaForma() {
        return formaPagoServicio.listaFormaPago();
    }
    
}
