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
import ppro.modelo.PproFactura;

/**
 *
 * @author casa
 */
@Stateless
public class FacturaServicio {
     @PersistenceContext(unitName = "PproMpitV1PU")
        private EntityManager em;
     
     public boolean grabarFactura(PproFactura f){
         boolean hecho=false;
         try {
             em.persist(f);
             hecho=true;
         } catch (Exception e) {
         }
         return hecho;
     }
     
     
     public List<PproFactura> listaFactura(){
         TypedQuery<PproFactura> query=em.createNamedQuery("PproFactura.findAll",PproFactura.class);
         return query.getResultList();
     }
}
