/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej4501.examen.autoPark.presentacion;


import cl.duoc.dej4501.examen.autoPark.entity.Estacionamiento;
import cl.duoc.dej4501.examen.autoPark.persistence.EstacionamientoSessionBean;
import cl.duoc.dej4501.examen.autoPark.persistence.TicketSessionBean;
import cl.duoc.dej4501.examen.autoPark.viewDomain.voucherViewDomain;
import cl.duoc.dej4501.examen.autoPark.viewDomain.TicketsViewDomain;
import java.io.IOException; 
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adolf
 */
@WebServlet(name = "ProcesaTicketsServlet", urlPatterns = {"/procesaTicketsServlet"})
public class ProcesaTicketsServlet extends HttpServlet {

     @EJB
     private EstacionamientoSessionBean estacionamientoSB;
     @EJB
     private TicketSessionBean ticketSB;
     
     private TicketsViewDomain ticketVD;
     private voucherViewDomain voucherVD;
    
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
         int codigoborrar = 0;
          
        try {
            codigoborrar = Integer.parseInt(request.getParameter("codigo"));
        } catch (Exception e) {
            System.out.println("datos para voucher");
            codigoborrar = -1;
        }
        
        if(codigoborrar == -1)
        {
            response.sendRedirect("index.jsp"); 
        }else{
            voucherVD = (voucherViewDomain)request.getSession().getAttribute("voucherVD"); 
            
            for (TicketsViewDomain ticket1 : voucherVD.getListadoTickets()) {
                if(ticket1.getIdTicket()==codigoborrar){
                    try {
                         voucherVD.setTotalVoucher(voucherVD.getTotalVoucher()- ticket1.getMonto());
                         if(voucherVD.getListadoTickets().remove(ticket1))
                              {
                                 request.getSession().setAttribute("voucherVD", voucherVD);
                                 response.sendRedirect("index.jsp");  
                               }
                    } catch (Exception e) {
                        System.out.println("error"+e);
                    }        
                }
            }
        }
        response.sendRedirect("index.jsp");
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         
        String msgError = "";
        //parametros de post
        int monto = 0;
        int idEstacionamiento = 0;
        //para generar el voucher
        int total = 0;
        int codigoTicket = 0;
        String nombreEstacionamiento ="";

        boolean flag = true;
        HttpSession sesion = request.getSession();

        if (validacion(request)) {
            msgError += "\n faltan datos de ingreso en el formulario.";
            sesion.setAttribute("msgError", msgError);
        } else {
            try {
                monto = Integer.parseInt(request.getParameter("txtMonto"));
            } catch (Exception ex) {
                msgError += "\nMonto no corresponde" + ex;
                flag = false;
            }
               
            try {
                
                total = calculoTotal(request);
            } catch (Exception ex) {
                msgError += "\nerror al calcular el total" + ex;
                flag = false;
            }
            
            idEstacionamiento = Integer.parseInt(request.getParameter("ddlEstacionamiento"));
            //obtenemos el nombre del estacionamiento
            Estacionamiento objEstacionamiento = new Estacionamiento();
            objEstacionamiento = estacionamientoSB.getEstacionamientoById(idEstacionamiento);
            nombreEstacionamiento = objEstacionamiento.getNombreEstacionamiento();
            
            //obtenemos el ultimo id de ticket en la bbdd para agregarlo
            try {
                
                codigoTicket = ticketSB.findmaXiD();
            } catch (Exception ex) {
                msgError += "\nerror al obtener el ultimo id de ticket" + ex;
                flag = false;
            }
            
            
            
            if (flag) {
                //creamos un ticket para sumarlo a la lsita en el voucher
                ticketVD = new TicketsViewDomain(codigoTicket,monto,idEstacionamiento,nombreEstacionamiento);
                //creamos una lista de tickets y agregamos el ticketVD
                List <TicketsViewDomain> listatickets = new LinkedList<>();
                listatickets.add(ticketVD);
                
                //verificamos si es el primer ticekt del voucher
                if (request.getSession().getAttribute("voucherVD") == null) {
                     voucherVD = new voucherViewDomain();
                     //agregamos el total al voucher si es el primero se agrega el monto
                     voucherVD.setTotalVoucher(monto);
                     //agregamos el ticket como lista al voucher
                     voucherVD.setListadoTickets(listatickets);
                     
                    request.getSession().setAttribute("voucherVD", voucherVD);
                } else {
                    //si no es el primer ticket lo agregamos al voucher
                    boolean flag2 = true;
                    //tomamos el voucher de la session
                   voucherViewDomain requestVoucherVD = (voucherViewDomain) request.getSession().getAttribute("voucherVD");
                    /**
                     *
                     * @author adolf // verificamos si ya se encuentra el estacionamiento en la lista para agregar 
                     * el monto 
                     */
                    int montoNuevo = 0;
                    int montoOld =0;
                    for (TicketsViewDomain ticket1 : requestVoucherVD.getListadoTickets()) {
                        if (ticket1.getIdEstacionamiento()== idEstacionamiento) {
                            montoNuevo = monto;
                            montoOld = ticket1.getMonto();
                            ticket1.setMonto(montoNuevo); 
                            //descontamos el monto que ya se tenia del total del voucher
                            // y lo reemplazamos por el nuevo monto.
                            requestVoucherVD.setTotalVoucher(total - montoOld + montoNuevo);
                            flag2 = false;
                            request.getSession().setAttribute("voucherVD", voucherVD);
                        }
                    }
                    //si no es repetido el estacionamiento se agrega a la lista de estacionamientos del voucher
                    if(flag2){
                      //hacemos un listado con los ticket de la session
                     List<TicketsViewDomain> requestTicketVD = requestVoucherVD.getListadoTickets();
                    //sumamos 1 al id ticket
                    ticketVD.setIdTicket(ticketVD.getIdTicket() + 1 );
                    //agregamos el nuevo estacionamiento
                    requestTicketVD.add(ticketVD);
                    //se lo agregamos al voucher
                    requestVoucherVD.setListadoTickets(requestTicketVD);
                    //sumamos el monto al total del voucher
                    requestVoucherVD.setTotalVoucher(total + monto);
                    //lo agregamos a la session
                    request.getSession().setAttribute("voucherVD", voucherVD);
                    }
                }
            }
        }
        response.sendRedirect("index.jsp");
    }
/*
    *metodo para la validacion de parametros enviados desde el index.jsp
    */
    private boolean validacion(HttpServletRequest request) {
        return request.getParameter("txtMonto").isEmpty() 
                || request.getParameter("ddlEstacionamiento").isEmpty();

    }
     /*
    *metodo paca el calculo del total del voucher segun la session
    */
    
    private int calculoTotal(HttpServletRequest request) {
        int total = 0;
        try { 
              voucherVD = (voucherViewDomain)request.getSession().getAttribute("voucherVD");
                for (TicketsViewDomain ticket1 : voucherVD.getListadoTickets()) { 
                 total = total + (ticket1.getMonto());
                } 
            
        } catch (Exception e) {
            System.out.println("error"+e);
        }
          return total;
            
        }
   

}
