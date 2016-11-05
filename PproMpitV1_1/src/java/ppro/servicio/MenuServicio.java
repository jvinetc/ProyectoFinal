/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ppro.modelo.PproMenu;
import ppro.modelo.PproUsuario;

/**
 *
 * @author casa
 */
@Stateless
public class MenuServicio {

    @PersistenceContext(unitName = "PproMpitV1PU")
    private EntityManager em;

    public List<PproMenu> listaMenu(PproUsuario pu){
        TypedQuery<PproMenu> query=em.createNamedQuery("PproMenu.porPerfil",PproMenu.class);
        query.setParameter("perfilId", pu.getPproRelUsuarioPerfilCollection().iterator().next().getRupPerfId());        
        return  query.getResultList();
    }
}
