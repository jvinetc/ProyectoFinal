/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.converter;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ppro.contoller.TipoProveedorController;
import ppro.modelo.PproTipoProveedor;
import ppro.servicio.TipoProveedorServicio;

/**
 *
 * @author casa
 */
@FacesConverter(value = "tipoProvConverter")
public class TipoProvConverter implements Converter{

    TipoProveedorServicio tipoProveedorServicio = lookupTipoProveedorServicioBean();

        
    public List<PproTipoProveedor> getTipoProv(){
        return tipoProveedorServicio.listaTipoProv();
    }
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        for(PproTipoProveedor tipoProv:getTipoProv()){
            if(tipoProv.getTiproId().equals(Integer.parseInt(value))){
                return tipoProv;
            }
        }
            return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value!=null){
            return String.valueOf(((PproTipoProveedor)value).getTiproId());
        }else{
            return null;
        }
    }

    private TipoProveedorServicio lookupTipoProveedorServicioBean() {
        try {
            Context c = new InitialContext();
            return (TipoProveedorServicio) c.lookup("java:global/PproMpitV1_1/TipoProveedorServicio!ppro.servicio.TipoProveedorServicio");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}
