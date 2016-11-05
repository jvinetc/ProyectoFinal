/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.servicio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ppro.modelo.PproAnexoDocumento;

/**
 *
 * @author casa
 */
@Stateless
public class AnexoServicio {
     @PersistenceContext(unitName = "PproMpitV1PU")
        private EntityManager em;
     
     public boolean grabarAnexo(PproAnexoDocumento ad){
         boolean hecho=false;
         try {
             em.persist(ad);
             hecho=true;
         } catch (Exception e) {
         }
         return hecho;
     }
     
     public boolean actulizarAexo(PproAnexoDocumento ane){
         try {
             em.merge(ane);
             return true;
         } catch (Exception e) {
         }
         return false;
     }
}
