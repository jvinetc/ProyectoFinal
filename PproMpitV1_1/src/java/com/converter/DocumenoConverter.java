/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ppro.contoller.DocumentoController;
import ppro.modelo.PproProveedor;

/**
 *
 * @author casa
 */
@FacesConverter("documentoConverter")
public class DocumenoConverter implements Converter{

        @PersistenceContext(unitName = "PproMpitV1PU")
        private EntityManager em;
        
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         if(value!=null && value.trim().length()>0){
            try {
                Integer id= Integer.parseInt(value);
                DocumentoController documento= (DocumentoController) context.getExternalContext().getApplicationMap().get("documentoController");
                 Query query=em.createNamedQuery("PproProveedor.findByProvId", PproProveedor.class);
                query.setParameter("provId", id);
                documento.getPproDocumento().setDocProvId((PproProveedor) query.getSingleResult());
                return documento.getPproDocumento();
            } catch (NumberFormatException e) {
                throw  new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Conversion", "No es un valor valido"));
            }
        }else{
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value!=null){
            return String.valueOf(((PproProveedor) value).getProvId());
        }else{
            return null;
        }
    }
    
}
