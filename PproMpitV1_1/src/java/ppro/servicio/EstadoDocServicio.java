/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.servicio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ppro.modelo.PproEstadoDocumento;

/**
 *
 * @author casa
 */
@Stateless
public class EstadoDocServicio {
      @PersistenceContext(unitName = "PproMpitV1PU")
        private EntityManager em;
      
      public PproEstadoDocumento buscarEstado(int id){
          return  em.find(PproEstadoDocumento.class, id);
     }
}
