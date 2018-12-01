/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej4501.examen.autoPark.presentacion;


import cl.duoc.dej4501.examen.autoPark.entity.Estacionamiento;
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
@WebServlet(name = "GetVoucherByRutServlet", urlPatterns = {"/getVoucherByRutServlet"})
public class GetVoucherByRutServlet extends HttpServlet {

    @EJB
    private VoucherSessionBean voucherSB;
    @EJB 
    private TicketSessionBean ticketSB;
    @EJB
    private OpcionEnvioBoletaSessionBean opcionEnvBoletaSB;
    @EJB
    private EstacionamientoSessionBean estacionamientoSB;
     
    //generamos los viewDomain para cargar los valores obtenidos de la consulta a la BBDD y pasar los parametros
    //para generar campos mas amables y no pasar los id directos de la BBDD
    private voucherViewDomain voucherVD;
    private TicketsViewDomain ticketVD;
    

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
        

        boolean flag = true;
        HttpSession sesion = request.getSession();

        if (validacion(request)) {
            msgError += "\n faltan datos de ingreso en el formulario.";
            sesion.setAttribute("msgError", msgError);
        } else {
            try {
                rut = Integer.parseInt(request.getParameter("txtRut"));
                //eliminamos lo que este en session
              //   sesion = request.getSession(true);
              // sesion.invalidate();
            } catch (Exception ex) {
                msgError += "\nRut no corresponde" + ex;
                flag = false;
            }
              
            //creamos un Listado de objvoucher  para buscar por RUT enviado desde buscaVoucher.jsp
            //todos los voucher de ese rut
            List<Voucher> listObjVoucher;         
            //verificamos si encuentra el rut en la BBDD y procesamos la insformación
            if (flag) {  
                try {
                     /*
                    * voucherSB.update(); --> llama a un metodo en el sesionbean para refrescar los datos desde la base de datos
                    *para poder listar la informacion
                    */
                  //  actualizamos los  datos de la entidad desde la base de datos para poder mostrar
                    voucherSB.update();
                    
                    //una vez actualizada podemos seguir trabajando los datos 
                    //buscamos los datos del voucher por RUT en la BBDD y  los pasamos al objVoucher
                    listObjVoucher = this.voucherSB.getAllVoucherByRut(rut); 
                     //si no encuentra informacion con el rut retorna a la pagina
                     if (listObjVoucher.isEmpty()){
                         request.getSession().setAttribute("msgError", "No se encuentra el rut");                    
                         response.sendRedirect("buscarVoucher.jsp");
                         //si encuentra vouchers coon el rut procesa los tickets de cada voucher
                     }else{
                         //si se encuentra el rut en la BBDD vamos a buscar los ticket que corresponden
                         // a cada uno de los vouchers 
                         //llamamos al metodo para crear la lista de vourcherVD
                         List<voucherViewDomain> listadoVoucherVDRut = getAllDetalleTicket(listObjVoucher);
                            //enviamos el listado de los vouchers mas ticket recuperados por rut
                            request.getSession().setAttribute("listadoVoucherVDRut", listadoVoucherVDRut);
                           // sesion.setAttribute("listadoVoucherVDRut", listadoVoucherVDRut);                    
                            response.sendRedirect("buscarVoucher.jsp");
                     } 
                } catch (Exception e) {
                    request.getSession().setAttribute("msgError", "No se encuentra rut");
                    response.sendRedirect("buscarVoucher.jsp");
                }
            }
            response.sendRedirect("bucarVoucher.jsp");
        } 
    }
/*
    *metodo para la validacion de parametro RUT enviado desde el buscarVoucher.jsp
    */
    private boolean validacion(HttpServletRequest request) {
        return request.getParameter("txtRut").isEmpty(); 
    } 
   
    private List<voucherViewDomain> getAllDetalleTicket(List<Voucher> listaVoucher) {
          //Creamos una lista con VoucherVD para enviar pro la session con parametros adecuados
          List<voucherViewDomain> listaVoucherVD = new LinkedList<>(); 
        try {
               //se procesa cada uno de los vouchers de la lista para crear una lista de vouchers
            for (Voucher voucher1 : listaVoucher) {
                voucherVD = new voucherViewDomain();
                //cargamos los datos del voucher en el voucherVD para poder agregarlos a la lista
                voucherVD.setIdVoucher(voucher1.getIdVoucher());
                //obtenemos la opcion envio segun el id 
                //buscamos la opcion envio boleta completa por sessionBeans segun el id que esta en el voucher
                OpcionEnvioBoleta objOpEnvioBoleta = opcionEnvBoletaSB.getOpENvioBoletaById(voucher1.getIdopEnvioBoleta().getIdopEnvioBoleta());
                //seteamos la opcion de envio boleta (String) al voucherVD sacando el String del objeto opcion env boleta
                voucherVD.setOpEnvio(objOpEnvioBoleta.getOpcionEnvioBoleta());
                //asignamos el total del voucher al voucherVD
                voucherVD.setTotalVoucher(voucher1.getTotal());
                
                //creamos una lista obj ticket para almacenar los datos obtenidos del voucher procesado de la lista
                List<TicketsViewDomain> listaobjTicketVD = new LinkedList<>();
                //analizamos la lista de tickets del voucher y los guardamos para
                //pasarlos al ticketVD conparametros correctos para mostrar
                
                for (Ticket ticket1 : voucher1.getTicketList()) {
                    ticketVD = new TicketsViewDomain();
                    ticketVD.setIdTicket(ticket1.getIdTicket());
                    //pasa el monto de long a int y lo guardamos en el ticketVD
                    ticketVD.setMonto(ticket1.getMonto().intValue());
                    ticketVD.setIdEstacionamiento(ticket1.getIdEstacionamiento().getIdEstacionamiento());
                    //obtenemos sel nobre del estacionamiento mediante 
                    Estacionamiento estacionamiento = estacionamientoSB.getEstacionamientoById(ticket1.getIdEstacionamiento().getIdEstacionamiento());
                    ticketVD.setNombreEstacionamiento(estacionamiento.getNombreEstacionamiento());
                    listaobjTicketVD.add(ticketVD);
                }
                //terminada de crear la lista de View Domains de ticket los agregamos al VoucherVD que enviaremos en la session
                //sumamos la lista de tickets al voucher.
                voucherVD.setListadoTickets(listaobjTicketVD);
                //agregamos el voucher al listado de boucher para crear el listado por rut en la grilla
                listaVoucherVD.add(voucherVD);

            }

        } catch (Exception e) {

            System.out.println("\nError" + e);
        }

        return listaVoucherVD;
    }

}
