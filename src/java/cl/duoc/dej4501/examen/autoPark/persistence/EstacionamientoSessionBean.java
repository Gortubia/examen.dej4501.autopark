/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej4501.examen.autoPark.persistence;


import cl.duoc.dej4501.examen.autoPark.entity.Estacionamiento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author adolf
 */
@Stateless
public class EstacionamientoSessionBean {

    
    
    
     @PersistenceContext
    private EntityManager em;
    
     public List<Estacionamiento> getAllEstacionamientos(){
       return em.createNamedQuery("Estacionamiento.findAll",Estacionamiento.class)
               .getResultList();
    }
    
      public Estacionamiento getEstacionamientoById(int id){
        return em.find(Estacionamiento.class, id );
    }
    
}
