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
import ppro.modelo.PproFormaPago;

/**
 *
 * @author casa
 */
@Stateless
public class FormaPagoServicio {
      @PersistenceContext(unitName = "PproMpitV1PU")
        private EntityManager em;
      
      public List<PproFormaPago> listaFormaPago(){
          TypedQuery<PproFormaPago> query= em.createNamedQuery("PproFormaPago.findAll",PproFormaPago.class);
          return query.getResultList();
      }
      
      public PproFormaPago buscarPorId(int id){
          try {
              Query query= em.createNamedQuery("PproFormaPago.findByFpagId",PproFormaPago.class);
              query.setParameter("fpagId", id);
              return (PproFormaPago)query.getSingleResult();
          } catch (Exception e) {
          }
          return null;
      }
}
