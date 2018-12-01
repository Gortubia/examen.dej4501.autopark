/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej4501.examen.autoPark.persistence;



import cl.duoc.dej4501.examen.autoPark.entity.Ticket;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author adolf
 */
@Stateless
public class TicketSessionBean {

 
    
    
     @PersistenceContext
    private EntityManager em;
    
     public List<Ticket> getAllTickets(){
       return em.createNamedQuery("Ticket.findAll",Ticket.class)
               .getResultList();
    }
    
      public Ticket getTicketById(int id){
        return em.find(Ticket.class, id );
    }
      
      public int findAllByVoucherId (){
        int maxId = 0; 
        try {
            
            maxId = (int)  em.createQuery("SELECT MAX(t.idTicket) FROM Ticket t") 
                    .getSingleResult();  
        } catch (Exception e) {
        }
        return maxId;
        
    }
      
      
      public int findmaXiD (){
        int maxId = 0; 
        try {
            
            maxId = (int)  em.createQuery("SELECT MAX(t.idTicket) FROM Ticket t") 
                    .getSingleResult();  
        } catch (Exception e) {
        }
        return maxId;
        
    }
    
     public void guardarTicket (Ticket tic) throws ControllerException{
                 em.persist(tic);
        
     }
    
    
}
