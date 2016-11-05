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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ppro.contoller.ProveedorConroller;
import ppro.modelo.PproProveedor;
import ppro.servicio.ProveedorServicio;

/**
 *
 * @author casa
 */
@FacesConverter("valorConverted")
public class ValorConverted implements Converter{

    ProveedorServicio proveedorServicio = lookupProveedorServicioBean();
    
        
    
    List<PproProveedor> listaProv= new ArrayList<>();

    public List<PproProveedor> getListaProv() {
        return proveedorServicio.listaProveedor();
    }
    
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        //if(value!=null && value.trim().length()>0){
            //try {
                for(PproProveedor prov:getListaProv()){
                    if(prov.getProvId().equals(Integer.parseInt(value))){
                        return prov;
                    }
                    
                }
                //ProveedorConroller proveedor= (ProveedorConroller) context.getExternalContext().getApplicationMap().get("proveedorConroller");
                //return proveedor.getListaProveedor().get(Integer.parseInt(value)-1);
                
            //} catch (NumberFormatException e) {
              //  throw  new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Conversion", "No es un valor valido"));
            //}
        //}else{
            return null;
        //}
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value!=null){
            return String.valueOf(((PproProveedor) value).getProvId());
        }else{
            return null;
        }
    }

    private ProveedorServicio lookupProveedorServicioBean() {
        try {
            Context c = new InitialContext();
            return (ProveedorServicio) c.lookup("java:global/PproMpitV1_1/ProveedorServicio!ppro.servicio.ProveedorServicio");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}
