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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import ppro.modelo.PproMenu;
import ppro.modelo.PproUsuario;
import ppro.servicio.MenuServicio;

/**
 *
 * @author casa
 */
@ManagedBean
@SessionScoped
public class MenuController {

    @EJB
    private MenuServicio menuServicio;
    
    private List<PproMenu> listaMenu= new ArrayList<>();
    
    @ManagedProperty(value="#{pproMenu}")
    private PproMenu pproMenu;
    

    
    @ManagedProperty(value = "#{pproUsuario}")
    private PproUsuario pproUsuario;

    public PproUsuario getPproUsuario() {
        return pproUsuario;
    }

    public void setPproUsuario(PproUsuario pproUsuario) {
        this.pproUsuario = pproUsuario;
}

    
    

    public List<PproMenu> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(List<PproMenu> listaMenu) {
        this.listaMenu = listaMenu;
    }

    public PproMenu getPproMenu() {
        return pproMenu;
    }

    public void setPproMenu(PproMenu pproMenu) {
        this.pproMenu = pproMenu;
    }
    
    @PostConstruct
    public void lstarMenu(){
        
        try{
            listaMenu= menuServicio.listaMenu(pproUsuario);
        }catch(Exception ex){
            System.out.println("error: "+ex);
        }
    }
}
