/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.contoller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import ppro.modelo.PproTipoDocumento;
import ppro.servicio.TipoDocumentoServicio;

/**
 *
 * @author casa
 */
@ManagedBean
@RequestScoped
public class TipoDocumentoController {

    @EJB
    private TipoDocumentoServicio tipoDocumentoServicio;
    
    @ManagedProperty(value = "#{pproTipoDocumento}")
    private PproTipoDocumento pproTipoDocumento;

    private List<PproTipoDocumento> listaTipo= new ArrayList<>();
    
    public PproTipoDocumento getPproTipoDocumento() {
        return pproTipoDocumento;
    }

    public void setPproTipoDocumento(PproTipoDocumento pproTipoDocumento) {
        this.pproTipoDocumento = pproTipoDocumento;
    }

    public List<PproTipoDocumento> getListaTipo() {
        return listaTipo;
    }

    public void setListaTipo(List<PproTipoDocumento> listaTipo) {
        this.listaTipo = listaTipo;
    }
    
    @PostConstruct
    public void llenaLista(){
        listaTipo= tipoDocumentoServicio.listaTipo();
    }   
    
    public void seleccion(){   
       
        FacesContext.getCurrentInstance().
                addMessage(null,new FacesMessage("Tipo documento cambiado "));
        
       
    }
   
}
