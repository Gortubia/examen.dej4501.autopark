/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej4501.examen.autoPark.persistence;


import cl.duoc.dej4501.examen.autoPark.entity.Mediospago;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author adolf
 */
@Stateless
public class MediosPagoSessionBean {

       @PersistenceContext
    private EntityManager em;
    
     public List<Mediospago> getAllOpcionesPago(){
       return em.createNamedQuery("Mediospago.findAll",Mediospago.class)
               .getResultList();
    }
    
}
