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
import ppro.modelo.PproTipoProveedor;
import ppro.servicio.TipoProveedorServicio;

/**
 *
 * @author casa
 */
@ManagedBean
@RequestScoped
public class TipoProveedorController {

    @EJB
    private TipoProveedorServicio tipoProveedorServicio;    
    
    @ManagedProperty(value = "#{pproTipoProveedor}")
    PproTipoProveedor pproTipoProveedor;
    
    List<PproTipoProveedor> listaTipoProv= new ArrayList<>();

    public PproTipoProveedor getPproTipoProveedor() {
        return pproTipoProveedor;
    }

    public void setPproTipoProveedor(PproTipoProveedor pproTipoProveedor) {
        this.pproTipoProveedor = pproTipoProveedor;
    }

    public List<PproTipoProveedor> getListaTipoProv() {
        return listaTipoProv;
    }

    public void setListaTipoProv(List<PproTipoProveedor> listaTipoProv) {
        this.listaTipoProv = listaTipoProv;
    }
    
    @PostConstruct
    public void init(){
        listaTipoProv= tipoProveedorServicio.listaTipoProv();
    }
}
