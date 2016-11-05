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
import ppro.modelo.PproTipoDocumento;

/**
 *
 * @author casa
 */
@Stateless
public class TipoDocumentoServicio {
     @PersistenceContext(unitName = "PproMpitV1PU")
        private EntityManager em;
     
    public List<PproTipoDocumento> listaTipo(){
        TypedQuery<PproTipoDocumento> query= em.createNamedQuery("PproTipoDocumento.findAll",PproTipoDocumento.class);
        return query.getResultList();
    }
    
    public PproTipoDocumento buscarPorId(int id){
        Query query= em.createNamedQuery("PproTipoDocumento.findByTdocId",PproTipoDocumento.class);
        query.setParameter("tdocId", id);
        return (PproTipoDocumento) query.getSingleResult();
    }
}
