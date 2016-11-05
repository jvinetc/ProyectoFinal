/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.contoller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import ppro.modelo.PproUsuario;
import ppro.servicio.UsuarioServicio;

/**
 *
 * @author casa
 */
@ManagedBean
@SessionScoped
public class UsuarioController implements Serializable{

    @EJB
    private UsuarioServicio usuarioServicio;
      

     
    @ManagedProperty(value = "#{pproUsuario}")
    private PproUsuario pproUsuario;
    
    private List<PproUsuario> listaUsuarios= new ArrayList<>();
 
    
    public PproUsuario getPproUsuario() {
        return pproUsuario;
    }

    public void setPproUsuario(PproUsuario pproUsuario) {
        this.pproUsuario = pproUsuario;
    }

    public List<PproUsuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<PproUsuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    
    public void login() throws IOException{
        //String pagina="fallo";        
        pproUsuario=usuarioServicio.login(pproUsuario);
        if(pproUsuario.getUsuId() != null){ 
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pproUsuario",pproUsuario);
//            pagina=pproUsuario.getPproRelUsuarioPerfilCollection().iterator()
//                    .next().getRupPerfId().getPerfNombre();   
            FacesContext.getCurrentInstance().getExternalContext().redirect("escritorio.xhtml");
        } else{
            FacesContext.getCurrentInstance().getExternalContext().redirect("fallo.xhtml");
        }
        
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public void loginMd5()throws IOException{
        pproUsuario=usuarioServicio.loginMd5(pproUsuario);
        if(pproUsuario.getUsuId() != null){ 
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pproUsuario",pproUsuario);

            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/escritorio.xhtml");
        } else{
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/fallo.xhtml");
        }
    }
}
