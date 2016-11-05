/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validador;

import java.io.File;
import java.util.Arrays;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author casa
 */
@FacesValidator("validadorArchivo")
public class ValidadorArchivo implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UploadedFile file = (UploadedFile) value;
        String nombre = file.getFileName();
        String fileType = file.getContentType();
        long tamanio = file.getSize();
        String[] validos = new String[]{"pdf", "jpg", "png"};
        int posicion = Arrays.binarySearch(validos, fileType);
        if (posicion > 0) {
            FacesMessage msg = new FacesMessage(
                    " El archivo no es valido, reintente");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);
        }
        if(tamanio > 102400){
            FacesMessage msg = new FacesMessage(
                    " El archivo es demasiado grande, reintente");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);
        }
    }

}
