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
import ppro.modelo.PproDocumento;

/**
 *
 * @author casa
 */
@Stateless
public class DocumentoServicio {
    
     @PersistenceContext(unitName = "PproMpitV1PU")
        private EntityManager em;
     
     public boolean guardarDocumento(PproDocumento d){
         boolean hecho=false;
         try {
             em.persist(d);
             hecho=true;
         } catch (Exception e) {
         }
         return hecho;
     } 
     
     public List<PproDocumento> listaDocumento(){
         TypedQuery<PproDocumento> query= em.createNamedQuery("PproDocumento.findAll",PproDocumento.class);
         return query.getResultList();
     }
     
     public PproDocumento buscarDoc(PproDocumento documento){
          return  em.find(PproDocumento.class, documento.getDocId());
     }
     
     
     public boolean buscaPorNumero(PproDocumento documento){
         boolean hecho= false;
         try {
            Query query= em.createNamedQuery("PproDocumento.findByDocNumero",PproDocumento.class);
            query.setParameter("docNumero", documento.getDocNumero());
            documento=(PproDocumento) query.getSingleResult();
            if(documento.getDocId()!= null){
                hecho=true;
            }
         } catch (Exception e) {
         }
         
         return hecho;
     }
     
     public List<PproDocumento> buscaPorProveedor(PproDocumento pd){
         TypedQuery<PproDocumento> query=em.createNamedQuery("PproDocumento.buscarPorProv", PproDocumento.class);
         query.setParameter("docProvId", pd.getDocProvId());
         return query.getResultList();
     }
     
     public boolean validaDocumento(PproDocumento doc){
         try {
             Query query= em.createNamedQuery("PproDocumento.validarDocumento",PproDocumento.class);
             query.setParameter("rutProv", doc.getDocProvId().getProvPerId().getPerRutComp());
             query.setParameter("nDoc", doc.getDocNumero());
             doc=(PproDocumento)query.getSingleResult();
             return true;
         } catch (Exception e) {
         }
         return false;
     }
     
     
     public boolean actualizar(PproDocumento doc){
         try {
             em.merge(doc);
             return true;
         } catch (Exception e) {
         }
         return false;
     }
}
