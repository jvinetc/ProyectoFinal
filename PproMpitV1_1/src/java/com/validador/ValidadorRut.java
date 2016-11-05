/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validador;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import ppro.modelo.PproPersona;
import ppro.servicio.PersonaServicio;

/**
 *
 * @author casa
 */
@FacesValidator("validadorRut")
public class ValidadorRut implements Validator{

    @EJB
    private PersonaServicio personaServicio;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String rut= (String) value;
        PproPersona per= new PproPersona();
        try {
            
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                
            }else{
                FacesMessage msg = new FacesMessage(
					" El rut es invalido, reintente");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                        per.setPerRutComp("");
			throw new ValidatorException(msg);
            }

        } catch (java.lang.NumberFormatException e) {
            FacesMessage msg = new FacesMessage(
					"Los valores ingresados no son validos");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                        per.setPerRutComp("");
			throw new ValidatorException(msg);
        }
    }
    
}
