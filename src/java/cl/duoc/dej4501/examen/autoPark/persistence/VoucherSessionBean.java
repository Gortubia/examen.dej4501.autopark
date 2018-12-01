/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej4501.examen.autoPark.persistence;

import cl.duoc.dej4501.examen.autoPark.entity.Voucher;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author adolf
 */
@Stateless
public class VoucherSessionBean {

       @PersistenceContext
    private EntityManager em;

    public List<Voucher> getAllVouchers() {
        return em.createNamedQuery("Voucher.findAll", Voucher.class)
                .getResultList();
    }

    public Voucher getVoucherById(int id) {
        return em.find(Voucher.class, id);
    }

    public void update() {
        em.getEntityManagerFactory().getCache().evictAll();
    }

    public List<Voucher> getAllVoucherByRut(int rutCliente) {

        return em.createNamedQuery("Voucher.findByRutCliente", Voucher.class)
                .setParameter("rutCliente", rutCliente)
                .getResultList();
    }

    public int findmaXiD() {
        int maxId = 0;
        try {

            maxId = (int) em.createQuery("SELECT MAX(v.idVoucher) FROM Voucher v")
                    .getSingleResult();
        } catch (Exception e) {
        }
        return maxId;

    }

    public void guardarVoucher(Voucher vou) throws ControllerException {
        em.persist(vou);

    }

}
