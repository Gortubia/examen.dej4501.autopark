/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej4501.examen.autoPark.viewDomain;

/**
 *
 * @author adolf
 */
public class TicketsViewDomain {
    
    private int idTicket;
    private int monto;
    private int idEstacionamiento;
    private String nombreEstacionamiento;

    public TicketsViewDomain() {
    }

    public TicketsViewDomain(int idTicket, int monto, int idEstacionamiento, String nombreEstacionamiento) {
        this.idTicket = idTicket;
        this.monto = monto;
        this.idEstacionamiento = idEstacionamiento;
        this.nombreEstacionamiento = nombreEstacionamiento;
    }

    public int getIdEstacionamiento() {
        return idEstacionamiento;
    }

    public void setIdEstacionamiento(int idEstacionamiento) {
        this.idEstacionamiento = idEstacionamiento;
    }

    

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getNombreEstacionamiento() {
        return nombreEstacionamiento;
    }

    public void setNombreEstacionamiento(String nombreEstacionamiento) {
        this.nombreEstacionamiento = nombreEstacionamiento;
    }
    
    
    
}
