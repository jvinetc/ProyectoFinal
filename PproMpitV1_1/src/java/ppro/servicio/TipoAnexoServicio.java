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
import ppro.modelo.PproProveedor;
import ppro.modelo.PproTipoAnexo;

/**
 *
 * @author casa
 */
@Stateless
public class TipoAnexoServicio {
    
    @PersistenceContext(unitName = "PproMpitV1PU")
    private EntityManager em;
    
    public PproTipoAnexo porTipoProv(PproProveedor p, String nombre){
        Query query=em.createNamedQuery("PproTipoAnexo.findByTanTipoProveedor",PproTipoAnexo.class);
        query.setParameter("tanTiproId", p.getProvTiproId());
        query.setParameter("tanNombre", nombre);        
        return  (PproTipoAnexo) query.getSingleResult();
    }
}
