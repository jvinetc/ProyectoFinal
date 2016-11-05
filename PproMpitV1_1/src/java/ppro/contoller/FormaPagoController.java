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
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import ppro.modelo.PproFormaPago;
import ppro.servicio.FormaPagoServicio;

/**
 *
 * @author casa
 */
@ManagedBean
@RequestScoped
public class FormaPagoController {
    
    @EJB
    private FormaPagoServicio formaPagoServicio;
    
    @ManagedProperty(value = "#{pproFormaPago}")
    private PproFormaPago pproFormaPago;
    
    private List<PproFormaPago> listaFormaPago= new ArrayList<>();

    public PproFormaPago getPproFormaPago() {
        return pproFormaPago;
    }

    public void setPproFormaPago(PproFormaPago pproFormaPago) {
        this.pproFormaPago = pproFormaPago;
    }

    public List<PproFormaPago> getListaFormaPago() {
        return listaFormaPago;
    }

    public void setListaFormaPago(List<PproFormaPago> listaFormaPago) {
        this.listaFormaPago = listaFormaPago;
    }
    
    @PostConstruct
    public void init(){
        listaFormaPago= formaPagoServicio.listaFormaPago();
    }
}
