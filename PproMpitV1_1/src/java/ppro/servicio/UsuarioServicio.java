/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.servicio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ppro.modelo.PproUsuario;

/**
 *
 * @author casa
 */
@Stateless
public class UsuarioServicio {
   
    @PersistenceContext(unitName = "PproMpitV1PU")
    private EntityManager em;
    
    public PproUsuario login(PproUsuario pu){
        
        try {
            Query query= em.createNamedQuery("PproUsuario.login",PproUsuario.class);
        query.setParameter("usuUsername", pu.getUsuUsername());
        query.setParameter("usuPass", pu.getUsuPass());
        pu=(PproUsuario) query.getSingleResult();    
        } catch (Exception e) {
        }        
        return pu;
    }
    
    public PproUsuario loginMd5(PproUsuario pu){
        try {
            Query query=em.createNamedQuery("PproUsuario.findByUsuUsernameMd5",PproUsuario.class);
            query.setParameter("usuUsernameMd5", pu.getUsuUsernameMd5());
            pu=(PproUsuario) query.getSingleResult();
        } catch (Exception e) {
        }
        return pu;
    }
    
}
