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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import ppro.modelo.PproDocumento;
import ppro.modelo.PproPersona;
import ppro.modelo.PproProveedor;
import ppro.servicio.PersonaServicio;
import ppro.servicio.ProveedorServicio;

/**
 *
 * @author casa
 */
@ManagedBean
@ViewScoped
public class ProveedorConroller {

    @EJB
    private PersonaServicio personaServicio;

    @EJB
    private ProveedorServicio proveedorServicio;

    @ManagedProperty(value = "#{pproPersona}")
    private PproPersona pproPersona;

    @ManagedProperty(value = "#{pproProveedor}")
    private PproProveedor pproProveedor;

    private List<PproProveedor> listaProveedor = new ArrayList<>();
    private List<PproDocumento> listaDoc = new ArrayList<>();

    private boolean eleccion;

    public PproProveedor getPproProveedor() {
        return pproProveedor;
    }

    public void setPproProveedor(PproProveedor pproProveedor) {
        this.pproProveedor = pproProveedor;
    }

    public PproPersona getPproPersona() {
        return pproPersona;
    }

    public void setPproPersona(PproPersona pproPersona) {
        this.pproPersona = pproPersona;
    }

    public void traeProveedor() {

        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Proveedor encontrado "));

    }

    public List<PproProveedor> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<PproProveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public List<PproProveedor> completarProveedor(String query) {
        List<PproProveedor> listaProv = this.getListaProveedor();
        List<PproProveedor> filtroLista = new ArrayList<>();
        for (PproProveedor prov : listaProv) {
            if (String.valueOf(prov.getProvPerId().getPerRut()).contains(query)) {
                filtroLista.add(prov);
            }
        }
        return filtroLista;

    }

    @PostConstruct
    public void init() {
        listaProveedor = proveedorServicio.listaProveedor();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pproDocumento", null);
    }

    public List<PproDocumento> getListaDoc() {
        return listaDoc;
    }

    public void setListaDoc(List<PproDocumento> listaDoc) {
        this.listaDoc = listaDoc;
    }

    public boolean isEleccion() {
        return eleccion;
    }

    public void setEleccion(boolean eleccion) {
        this.eleccion = eleccion;
    }

    public void validaRutProv() {
        pproProveedor.setProvPerId(pproPersona);
        if(proveedorServicio.existePorveedor(pproProveedor)){
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage("El proveedor ya existe"));
            pproPersona.setPerRutComp(null);
        }else if (!personaServicio.validarRut(pproPersona)) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage("Rut no Invalido reintente"));
            pproPersona.setPerRutComp(null);
        } else {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage("Rut Valido"));
        }
    }
}
