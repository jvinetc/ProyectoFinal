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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import ppro.modelo.PproTipoProveedor;

/**
 *
 * @author casa
 */
@Stateless
public class TipoProveedorServicio {
    @PersistenceContext(unitName = "PproMpitV1PU")
        private EntityManager em;
    
    public List<PproTipoProveedor> listaTipoProv(){
        TypedQuery<PproTipoProveedor> query= em.createNamedQuery("PproTipoProveedor.findAll", PproTipoProveedor.class);
        return query.getResultList();
    }
    
    public PproTipoProveedor buscaPorId(int id){
        /*Query query= em.createNamedQuery("PproTipoProveedor.findByTiproId",PproTipoProveedor.class);
        query.setParameter("tiproId", id);
        return (PproTipoProveedor) query.getSingleResult();*/
        return em.find(PproTipoProveedor.class, id);
    }
}
