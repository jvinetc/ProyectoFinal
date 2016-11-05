/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.contoller;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import ppro.modelo.PproDocumento;
import ppro.servicio.DocumentoServicio;

/**
 *
 * @author casa
 */
@ManagedBean
@RequestScoped
public class EdicionController {

    @EJB
    private DocumentoServicio documentoServicio;
    
    @ManagedProperty(value = "pproDocumento")
    PproDocumento pproDocumento;

    public PproDocumento getPproDocumento() {
        return pproDocumento;
    }

    public void setPproDocumento(PproDocumento pproDocumento) {
        this.pproDocumento = pproDocumento;
    }
    
    public void traeDocumento(){
        pproDocumento=documentoServicio.buscarDoc(pproDocumento);
    }
    
}
