/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.servicio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ppro.modelo.PproPersona;

/**
 *
 * @author casa
 */
@Stateless
public class PersonaServicio {
    
    @PersistenceContext(unitName = "PproMpitV1PU")
    private EntityManager em;
    
    public boolean validarRut(PproPersona per) {

        boolean validacion = false;
        try {
            String rut= per.getPerRutComp();
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }
    
    
    public boolean grabarPersona(PproPersona per){
        try {
            em.persist(per);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
