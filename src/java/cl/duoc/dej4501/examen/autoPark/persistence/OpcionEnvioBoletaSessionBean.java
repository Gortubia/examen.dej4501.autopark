/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej4501.examen.autoPark.persistence;


import cl.duoc.dej4501.examen.autoPark.entity.OpcionEnvioBoleta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author adolf
 */
@Stateless
public class OpcionEnvioBoletaSessionBean {

    
     @PersistenceContext
    private EntityManager em;
    
     public List<OpcionEnvioBoleta> getAllOpcionEnvioBoleta(){
       return em.createNamedQuery("OpcionEnvioBoleta.findAll",OpcionEnvioBoleta.class)
               .getResultList();
    }
     
     
        public OpcionEnvioBoleta getOpENvioBoletaById(int id){
        return em.find(OpcionEnvioBoleta.class, id );
    }
      
    
     
}
