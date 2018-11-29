/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej4501.examen.autoPark.presentacion;


import cl.duoc.dej4501.examen.autoPark.viewDomain.TicketsViewDomain;
import cl.duoc.dej4501.examen.autoPark.viewDomain.voucherViewDomain;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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
@WebServlet(name = "VerDetalleVoucherServlet", urlPatterns = {"/verDetalleVoucherServlet"})
public class VerDetalleVoucherServlet extends HttpServlet {

  
     
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
              
        HttpSession sesion = request.getSession();
        //creamos un listado de voucher
        List<voucherViewDomain> listVoucherVD = new LinkedList<>();
        //dejamos el listado de vouchers de la session en la variable para trabajar
        listVoucherVD = (List<voucherViewDomain>)request.getSession().getAttribute("listadoVoucherVDRut");
        int idVoucher =Integer.parseInt(request.getParameter("codigo"));
        //comparamos el codigo con cada voucher de la lista para obtenerlo
        for(voucherViewDomain voucherVD : listVoucherVD ){
            //si encontramos el voucher lo enviamos a la pagina para mostrar el detalle
            //jusnto con sus tickets
            if(voucherVD.getIdVoucher()== idVoucher){
                //enviamos el voucher si el codigo corresponde
                   sesion.setAttribute("voucherVD", voucherVD);                    
                  response.sendRedirect("verVoucher.jsp");
            }else{//si no se encuentra enviamos un error y volvemos a la pagina
                sesion.setAttribute("msgError", "No hay detalle");
            response.sendRedirect("buscarVoucher.jsp");
            }
                
        }
 
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
