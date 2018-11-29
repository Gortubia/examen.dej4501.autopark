/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej4501.examen.autoPark.viewDomain;

import java.util.List;

/**
 *
 * @author adolf
 */
public class voucherViewDomain {
    
    private int idVoucher;
    private String opEnvio;
    private int totalVoucher;
    private List<TicketsViewDomain> listadoTickets;

    public voucherViewDomain() {
    }

    public voucherViewDomain(int totalBoucher, List<TicketsViewDomain> listadoTickets) {
        this.totalVoucher = totalBoucher;
        this.listadoTickets = listadoTickets;
    }

    public voucherViewDomain(int idVoucher, String opEnvio, int totalVoucher, List<TicketsViewDomain> listadoTickets) {
        this.idVoucher = idVoucher;
        this.opEnvio = opEnvio;
        this.totalVoucher = totalVoucher;
        this.listadoTickets = listadoTickets;
    }

    public int getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getOpEnvio() {
        return opEnvio;
    }

    public void setOpEnvio(String opEnvio) {
        this.opEnvio = opEnvio;
    }

    public int getTotalVoucher() {
        return totalVoucher;
    }

    public void setTotalVoucher(int totalVoucher) {
        this.totalVoucher = totalVoucher;
    }
    

    public List<TicketsViewDomain> getListadoTickets() {
        return listadoTickets;
    }

    public void setListadoTickets(List<TicketsViewDomain> listadoTickets) {
        this.listadoTickets = listadoTickets;
    }
    
    
    
    
}
