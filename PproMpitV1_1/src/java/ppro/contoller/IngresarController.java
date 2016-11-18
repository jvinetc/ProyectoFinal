/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.contoller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import ppro.modelo.PproEntidadFinanciera;
import ppro.modelo.PproFactura;
import ppro.servicio.PproEntidadFinancieraFacade;

/**
 *
 * @author casa
 */
@ManagedBean
@SessionScoped
public class IngresarController implements Serializable{
    
    @EJB
    private PproEntidadFinancieraFacade financieraFacade;
    
    private List<PproEntidadFinanciera> entidad= new ArrayList<>();
    private boolean existe= true;
    @ManagedProperty(value = "#{pproFactura}")
    private PproFactura pproFactura;
    @ManagedProperty(value = "#{pproEntidadFinanciera}")
    private PproEntidadFinanciera pproEntidadFinanciera;
    
    private final String pathAbsoluto = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
    private final String destino = "resources\\tmp";
    private final String destinoFacura = "\\factura\\";
    private final String destinoBoleta = "\\boleta\\";
    private final String destinoNota = "\\notaCredito\\";
    
    @PostConstruct
    public void init(){
        entidad= financieraFacade.findAll();
    }
    
    public String onFlowProcess(FlowEvent event) { 
        if(existe){
            return "documento";
        }else{
             return event.getNewStep();   
        }                
    }
    

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public List<PproEntidadFinanciera> getEntidad() {
        return entidad;
    }

    public void setEntidad(List<PproEntidadFinanciera> entidad) {
        this.entidad = entidad;
    }
    
    public void calculaIva() {
        double iva = 0.19;
        double neto = (double) pproFactura.getFacNeto();
        double valorIva = neto * iva;
        double total = pproFactura.getFacNeto() + valorIva;
        pproFactura.setFacIva((int) valorIva);
        pproFactura.setFacMonto((int) total);
        //RequestContext.getCurrentInstance().update("formProv:iva formProv:monto");
    }

    public PproFactura getPproFactura() {
        return pproFactura;
    }

    public void setPproFactura(PproFactura pproFactura) {
        this.pproFactura = pproFactura;
    }

    public PproEntidadFinanciera getPproEntidadFinanciera() {
        return pproEntidadFinanciera;
    }

    public void setPproEntidadFinanciera(PproEntidadFinanciera pproEntidadFinanciera) {
        this.pproEntidadFinanciera = pproEntidadFinanciera;
    }
}
