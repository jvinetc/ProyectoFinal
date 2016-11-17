/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.servicio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ppro.modelo.PproEntidadFinanciera;

/**
 *
 * @author casa
 */
@Stateless
public class PproEntidadFinancieraFacade extends AbstractFacade<PproEntidadFinanciera> {

    @PersistenceContext(unitName = "PproMpitV1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PproEntidadFinancieraFacade() {
        super(PproEntidadFinanciera.class);
    }
    
}
