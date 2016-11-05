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
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ppro.contoller.TipoDocumentoController;
import ppro.modelo.PproTipoDocumento;
import ppro.servicio.TipoDocumentoServicio;

/**
 *
 * @author STM1
 */
@FacesConverter("tipoDocConverted")
public class TipoDocConverted implements Converter{

    TipoDocumentoServicio tipoDocumentoServicio = lookupTipoDocumentoServicioBean();
    
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        for(PproTipoDocumento tipoDoc:getListaTipo()){
            if(tipoDoc.getTdocId().equals(Integer.parseInt(value))){
                return tipoDoc;
            }
        }
       // if(value!=null && value.trim().length()>0){
          //  try {
                //TipoDocumentoController tipoDoc= (TipoDocumentoController) context.getExternalContext().getApplicationMap().get("tipoDocumentoController");
                //return tipoDoc.getListaTipo().get(Integer.parseInt(value)-1);
               // PproTipoDocumento tipoDoc= tipoDocumentoServicio.buscarPorId(Integer.parseInt(value));
              //  return tipoDoc;
            //} catch (NumberFormatException e) {
               // throw  new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Conversion", "No es un valor valido"));
            //}
       // }else{
            return null;
        //}
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         if(value!=null){
            return String.valueOf(((PproTipoDocumento) value).getTdocId());
        }else{
            return null;
        }
    }

    private TipoDocumentoServicio lookupTipoDocumentoServicioBean() {
        try {
            Context c = new InitialContext();
            return (TipoDocumentoServicio) c.lookup("java:global/PproMpitV1_1/TipoDocumentoServicio!ppro.servicio.TipoDocumentoServicio");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public List<PproTipoDocumento> getListaTipo() {
        return tipoDocumentoServicio.listaTipo();
    }
    
}
