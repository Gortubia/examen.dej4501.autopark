<%-- 
    Document   : verVoucher
    Created on : 27-nov-2018, 20:44:53
    Author     : adolf
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>voucher</title>

    <!-- Bootstrap core CSS -->
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!-- Custom fonts for this template -->
    <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/business-casual.min.css" rel="stylesheet">

  </head>

  <body>
 
<jsp:useBean id="estacionamiento" class="cl.duoc.dej4501.examen.autoPark.entity.Estacionamiento" scope="page"></jsp:useBean>
<jsp:useBean id="voucherVD" class="cl.duoc.dej4501.examen.autoPark.viewDomain.voucherViewDomain" scope="page"></jsp:useBean>
<jsp:useBean id="ticketVD" class="cl.duoc.dej4501.examen.autoPark.viewDomain.TicketsViewDomain" scope="page"></jsp:useBean>
 

    <h1 class="site-heading text-center text-white d-none d-lg-block">
      <span class="site-heading-upper text-primary mb-3">Web pagos Auto park</span>
      <span class="site-heading-lower">Auto park</span>
    </h1>

         
    <section class="page-section cta">
      <div class="container">
        <div class="row">
          <div class="col-xl-9 mx-auto">
              <form class="needs-validation"  name="frmVerVoucher" method="POST" action="./cleanVoucherServlet">
              <div class="container">
              <div class="col-md-6 mb-3">
                  <h2 class="mb-4"> Voucher N° : </h2>
                  <input type="text" value="${sessionScope.voucherVD.idVoucher}"  class="form-control" disabled>
              </div>
              </div>
            <hr class="mb-4">
            
            <div class="container">
             <div class="card mb-3"> 
             <c:choose>
                 <c:when test="${sessionScope.voucherVD==null}"> 
                     <div class="alert alert-warning" role="alert">
                         No se encuentra voucher. Debe primero pagar tickets..<a href="index.jsp">Inicio</a>
                     </div> 
                 </c:when> 
                 <c:otherwise> 
                     <!-- DataTables Card-->
                     <div class="card-header">
                         <i class="fa fa-user"></i>Tickets pagados</div>
                     <div class="card-body">
                         <div class="table-responsive">
                             <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                 <thead>
                                     <tr> 
                                         <th>Estacionamiento</th>
                                         <th>Monto</th> 
                                         <th>N° Ticket</th> 
                                     </tr>
                                 </thead>
                                 <tbody> 
                                     <c:forEach  items="${sessionScope.voucherVD.listadoTickets}" var="ticketVD">
                                         <tr>
                                             <td><c:out value="${ticketVD.nombreEstacionamiento}"></c:out> </td>
                                             <td><c:out value="${ticketVD.monto}"></c:out> </td> 
                                             <td><c:out value="${ticketVD.idTicket}"></c:out> </td> 
                                         </tr>
                                     </c:forEach>
                                 </tbody>
                             </table>
                         </div>
                     </div>
                     <div class="card-footer small text-muted">Auto Park.</div>
                 </div>
             </div>

             <div class="container" >             
            <hr class="mb-4">
            <div class="col-md-6 mb-3">
            <h4 class="mb-3">Total pagado $</h4>
            <input type="text" value="${sessionScope.voucherVD.totalVoucher}"  class="form-control" id="txttot" disabled>
            </div>
             
            <hr class="mb-4">
            <div class="row">
                <div class="col-md-4">
                    <button class="btn btn-info btn-xl" type="submit">Volver</button>
                </div>
            </div>
            </div>
          </form>
           </c:otherwise>
     </c:choose>
      <c:if test="${sessionScope.msgError!=null}">
         <fieldset>
             <div class="alert alert-warning" role="alert">
                 <legend>Mensajes</legend> 
                 <c:out value="${sessionScope.msgError}"></c:out>
                 <c:remove var="msgError"></c:remove>
                 </div>
             </fieldset>
     </c:if>    
          </div>
        </div>
      </div>
    </section>
     
    <footer class="footer text-faded text-center py-5">
      <div class="container">
        <p class="m-0 small">Copyright &copy; Auto Park 2018</p>
      </div>
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>


