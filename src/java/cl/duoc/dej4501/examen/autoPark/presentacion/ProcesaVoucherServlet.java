/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej4501.examen.autoPark.presentacion;

import cl.duoc.dej4501.examen.autoPark.entity.Estacionamiento;
import cl.duoc.dej4501.examen.autoPark.entity.Mediospago;
import cl.duoc.dej4501.examen.autoPark.entity.OpcionEnvioBoleta;
import cl.duoc.dej4501.examen.autoPark.entity.Ticket;
import cl.duoc.dej4501.examen.autoPark.entity.Voucher;
import cl.duoc.dej4501.examen.autoPark.persistence.EstacionamientoSessionBean;
import cl.duoc.dej4501.examen.autoPark.persistence.OpcionEnvioBoletaSessionBean;
import cl.duoc.dej4501.examen.autoPark.persistence.TicketSessionBean;
import cl.duoc.dej4501.examen.autoPark.persistence.VoucherSessionBean;
import cl.duoc.dej4501.examen.autoPark.viewDomain.TicketsViewDomain;
import cl.duoc.dej4501.examen.autoPark.viewDomain.voucherViewDomain;
import java.io.IOException; 
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
@WebServlet(name = "ProcesaVoucherServlet", urlPatterns = {"/procesaVoucherServlet"})
public class ProcesaVoucherServlet extends HttpServlet {

    
     @EJB
     private EstacionamientoSessionBean estacionamientoSB;
     @EJB
     private TicketSessionBean ticketSB;
     @EJB
     private VoucherSessionBean voucherSB;
     @EJB
     private OpcionEnvioBoletaSessionBean opEnvioBoletaSB;
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
        int rut = 0;
        String nombre = "";
        String email = "";
        int telefono = 0;
        int id_op_pago = 0;
        int id_op_envio = 0;
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
                rut = Integer.parseInt(request.getParameter("txtRut"));
            } catch (Exception ex) {
                msgError += "\nRut no corresponde" + ex;
                flag = false;
            }
            try {
                nombre = request.getParameter("txtNombre");
            } catch (Exception ex) {
                msgError += "\nNombre no corresponde" + ex;
                flag = false;
            }
            try {
                email = request.getParameter("txtEmail");
            } catch (Exception ex) {
                msgError += "\nEmail no corresponde" + ex;
                flag = false;
            }
            try {
                telefono = Integer.parseInt(request.getParameter("txtTelefono"));
            } catch (Exception ex) {
                msgError += "\nTeléfono no corresponde" + ex;
                flag = false;
            }
            
            try {
                
                total = calculoTotal(request);
            } catch (Exception ex) {
                msgError += "\nerror al calcular el total" + ex;
                flag = false;
            }
            //buscamos el id del ultimo voucher
            int idVoucher = 0;
            try {
                //al ultimo id voucher le sumamos 1 para incrementar
                idVoucher = voucherSB.findmaXiD() + 1;
            } catch (Exception ex) {
                msgError += "\nError al buscar id voucher" + ex;
                flag = false;
            }
            //creamos obj medio pago para agregar al voucher
            Mediospago objMedioPago = new Mediospago();
            id_op_pago = Integer.parseInt(request.getParameter("ddlMediosPago"));
            objMedioPago.setIdmediosPagos(id_op_pago);
            
            //creamos un obj opcion envio para agregar al voucher
            OpcionEnvioBoleta objOpEnBoleta = new OpcionEnvioBoleta();
            id_op_envio = Integer.parseInt(request.getParameter("ddlOpEnvBoleta"));
            objOpEnBoleta.setIdopEnvioBoleta(id_op_envio);
            
             
            //creamos un obj voucher y le pasamos los parametros que traemos del index.jsp
            Voucher objVoucher = new Voucher();
            
            objVoucher.setIdVoucher(idVoucher);
            objVoucher.setRutCliente(rut);
            objVoucher.setNombreCliente(nombre);
            objVoucher.setTelefonoCliente(telefono);
            objVoucher.setEmailCliente(email);
            objVoucher.setIdmediosPagos(objMedioPago);
            objVoucher.setIdopEnvioBoleta(objOpEnBoleta);
            objVoucher.setTotal(total);
            
            //buscamos la opcion de envio para mostrar
            String opEnvio ="";
            OpcionEnvioBoleta objOpEnvioBoleta =  opEnvioBoletaSB.getOpENvioBoletaById(id_op_envio);
            opEnvio = objOpEnvioBoleta.getOpcionEnvioBoleta();
            //creamos un objVoucherVD para crear el voucher a mostrar al cliente
           
            voucherVD = (voucherViewDomain)request.getSession().getAttribute("voucherVD");
            voucherVD.setIdVoucher(idVoucher);
            voucherVD.setOpEnvio(opEnvio);
            voucherVD.setTotalVoucher(total);
            
            if (flag) { 
                
                try {
                    //grabamos el voucher en la bbdd
                    this.voucherSB.guardarVoucher(objVoucher);
                    //vamos a guardar cada uno de los tickets generados
                    addDetalleTicket(request, idVoucher);
                    sesion.setAttribute("voucherVD", voucherVD);                    
                    response.sendRedirect("verVoucher.jsp");
                } catch (Exception e) {
                    request.getSession().setAttribute("msgError", "Error al grabar información");
                    response.sendRedirect("index.jsp");
                }
            }
            response.sendRedirect("index.jsp");
        }
        
    }
/*
    *metodo para la validacion de parametros enviados desde el index.jsp
    */
    private boolean validacion(HttpServletRequest request) {
        return request.getParameter("txtRut").isEmpty()
                || request.getParameter("txtNombre").isEmpty()
                || request.getParameter("txtTelefono").isEmpty()
                || request.getParameter("txtEmail").isEmpty() 
                || request.getParameter("ddlMediosPago").isEmpty()
                || request.getParameter("ddlOpEnvBoleta").isEmpty();

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
    
    private boolean addDetalleTicket(HttpServletRequest request, int idVoucher) {
         
        Voucher voucher = new Voucher();
        voucher.setIdVoucher(idVoucher);
        //pasamos el voucher de la session al que acabamos de crear 
        voucherVD = (voucherViewDomain)request.getSession().getAttribute("voucherVD");
        
                //trabajamos cada uno de los tickets de la session
            for (TicketsViewDomain ticket1 : voucherVD.getListadoTickets()) {
                Ticket objTicket = new Ticket();
                Estacionamiento objEstacionamiento = estacionamientoSB.getEstacionamientoById(ticket1.getIdEstacionamiento());
                int idTicket =(ticketSB.findmaXiD()) + 1;
              
                objTicket.setIdTicket(idTicket);
                objTicket.setIdVoucher(voucher);
                objTicket.setIdEstacionamiento(objEstacionamiento);
                objTicket.setMonto((long)ticket1.getMonto()); 
             
            try {
                    this.ticketSB.guardarTicket(objTicket);
                } catch (Exception e) {
                    request.getSession().setAttribute("msgError", "Error al grabar información"+e);
                }   
            }
        
        return true;
            
        }
   

}
