<%-- 
    Document   : index
    Created on : 27-nov-2018, 21:04:06
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

    <title>Index</title>

    <!-- Bootstrap core CSS -->
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!-- Custom fonts for this template -->
    <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/business-casual.min.css" rel="stylesheet">

  </head>

  <body>
 <jsp:include page="/getAllEstacionamientoServlet" flush="true"></jsp:include> 
<jsp:include page="/getOpcionEnvioBoleta" flush="true"></jsp:include> 
<jsp:include page="/getMediosPago" flush="true"></jsp:include> 
<jsp:useBean id="estacionamiento" class="cl.duoc.dej4501.examen.autoPark.entity.Estacionamiento" scope="page"></jsp:useBean>
<jsp:useBean id="voucherVD" class="cl.duoc.dej4501.examen.autoPark.viewDomain.voucherViewDomain" scope="page"></jsp:useBean>
<jsp:useBean id="ticketVD" class="cl.duoc.dej4501.examen.autoPark.viewDomain.TicketsViewDomain" scope="page"></jsp:useBean>
 

    <h1 class="site-heading text-center text-white d-none d-lg-block">
      <span class="site-heading-upper text-primary mb-3">Web pagos Auto park</span>
      <span class="site-heading-lower">Auto park</span>
    </h1>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
      <div class="container">
        <a class="navbar-brand text-uppercase text-expanded font-weight-bold d-lg-none" href="#">autoPark</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav mx-auto">
            <li class="nav-item active px-lg-4">
              <a class="nav-link text-uppercase text-expanded" href="index.jsp">Inicio
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item px-lg-4">
              <a class="nav-link text-uppercase text-expanded" href="buscarVoucher.jsp">Ver Pagos</a>
            </li>
            <li class="nav-item px-lg-4">
              <a class="nav-link text-uppercase text-expanded" href="verEstacionamientos.jsp">Ver Estacionamientos</a>
            </li>
            <li class="nav-item px-lg-4">
              <a class="nav-link text-uppercase text-expanded" href="#">Ayuda</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    
    
    <section class="page-section cta">
      <div class="container">
        <div class="row">
          <div class="col-xl-9 mx-auto">
              <form class="needs-validation" name="frmaddticket" method="POST" action="./procesaTicketsServlet">
              <h1> Ingrese estacionamiento</h1>
              <hr class="mb-4">
            <div class="row">
                
              <div class="col-md-6 mb-6">
                 <label for="monto">Monto $ (minimo 500)</label>
                 <input type="number" min="500" class="form-control" id="txtMonto" name="txtMonto" placeholder="500" required="true">
              <div class="invalid-feedback">
                Ingrese monto.
              </div>
              </div>
              <div class="col-md-6">
                <label for="estacionamiento">Estacionamiento</label>
                <select class="custom-select d-block w-200" id="ddlEstacionamiento" name="ddlEstacionamiento" required="true">
                  <option value="">Seleccione...</option>
                  <c:forEach items="${sessionScope.listaEstacionemientos}" var="estacionamiento">
                      <option value="${estacionamiento.idEstacionamiento}"><c:out value="${estacionamiento.nombreEstacionamiento}"></c:out></option>
                 </c:forEach>
                </select>
                 
              </div>
            </div>
           <hr class="mb-4">
           <button class="btn btn-info btn-lg" type="submit">Agregar</button>
          </form>
        
            <hr class="mb-4">
            <form class="needs-validation"  name="frmaddticket" method="POST" action="./procesaVoucherServlet">
            <div class="container">
             <div class="card mb-3"> 
             <c:choose>
                 <c:when test="${sessionScope.voucherVD==null}"> 
                     <div class="alert alert-warning" role="alert">
                         No se encuentran tickets. Debe primero agregar ticket de pago..<a href="#"></a>
                     </div> 
                 </c:when> 
                 <c:otherwise> 
            <div class="container">
             <div class="row">
              <div class="col-md-6 mb-3">
                <label for="rut">Rut</label>
                <input type="number" class="form-control" id="txtRut" name="txtRut" placeholder="Rut" required="true">
                <div class="invalid-feedback">
                  Rut requerido.
                </div>
              </div>
              <div class="col-md-6 mb-3">
                <label for="nombre">Nombre</label>
                <input type="text" class="form-control" id="txtNombre" name="txtNombre" placeholder="Nombre"  required="true">
                <div class="invalid-feedback">
                  Nombre requerido. 
                </div>
              </div>
            </div> 
            <div class="mb-3">
              <label for="telefono">Teléfono</label>
              <input type="tel" class="form-control" id="txtTelefono" name="txtTelefono" placeholder="56777666222" required="true">
              <div class="invalid-feedback">
                Ingrese teléfono.
              </div>
            </div> 
            <div class="mb-3">
              <label for="correo">Correo<span class="text-muted"></span></label>
              <input type="email" class="form-control" id="txtEmail" name="txtEmail" required="true" placeholder="tu@ejemplo.com">
              <div class="invalid-feedback">
                Ingrese un email valido.
              </div>
            </div>
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="pago">Pago</label>
                <select class="custom-select d-block w-100" id="ddlMediosPago" name="ddlMediosPago" required="true">
                    <option value="">Seleccione...</option>
                  <c:forEach items="${sessionScope.listaMediosPago}" var="mediosPago">
                      <option value="${mediosPago.idmediosPagos}"><c:out value="${mediosPago.nombremediosPagos}"></c:out></option>
                </c:forEach>
                  <option></option>
                </select>
              </div>
              <div class="col-md-6 mb-6">
                <label for="eboleta">Envío boleta</label>
                <select class="custom-select d-block w-100" id="ddlOpEnvBoleta" name="ddlOpEnvBoleta" required="true">
                  <option value="">Seleccione...</option>
                  <c:forEach items="${sessionScope.listaOpcionEnvioBoleta}" var="envioBoleta">
                      <option value="${envioBoleta.idopEnvioBoleta}"><c:out value="${envioBoleta.opcionEnvioBoleta}"></c:out></option>
                </c:forEach>
                </select>
                 
              </div>
            </div>    
           </div>
                     <!-- DataTables Card-->
                     <div class="card-header">
                         <i class="fa fa-user"></i> Listado de tickets a pagar</div>
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
            <div class="container">
            <hr class="mb-4">
            <h4 class="mb-3 ">Total $</h4>
            <div class="col-md-6 mb-3">
            <input type="text" value="${sessionScope.voucherVD.totalVoucher}"  class="form-control" disabled>
            </div>
            <hr class="mb-4">
            <button class="btn btn-info btn-lg" type="submit">Pagar</button>
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
