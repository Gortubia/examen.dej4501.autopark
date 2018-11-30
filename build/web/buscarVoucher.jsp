<%-- 
    Document   : buscarVoucher
    Created on : 27-nov-2018, 21:28:51
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

                        <c:if test="${sessionScope.msgError!=null}">
                            <fieldset>
                                <div class="alert alert-warning" role="alert">
                                    <legend>Mensajes</legend> 
                                    <c:out value="${sessionScope.msgError}"></c:out>
                                    <c:remove var="msgError"></c:remove>
                                    </div>
                                </fieldset>
                        </c:if>

                        <form class="needs-validation" name="frmBuscaVoucher" method="POST" action="./getVoucherByRutServlet">
                            <h1> Ingrese Rut para buscar voucher</h1>
                            <hr class="mb-4">
                            <div class="row">
                                <div class="col-md-6 mb-6">
                                    <label for="rut">Rut</label>
                                    <input type="number" class="form-control" id="txtRut" name="txtRut" placeholder="rut" required="true">
                                </div>
                            </div>
                            <hr class="mb-4">
                            <button class="btn btn-info btn-lg" type="submit">buscar voucher</button>
                        </form>
                        <div class="container">
                            <div class="card mb-3"> 
                                <c:choose>
                                    <c:when test="${sessionScope.listadoVoucherVDRut==null}"> 
                                        <div class="alert alert-warning" role="alert">
                                            Ingrese rut para buscar voucher<a href="#"></a>
                                        </div> 
                                    </c:when> 
                                    <c:otherwise> 
                                        <!-- DataTables Card-->
                                        <div class="card-header">
                                            <i class="fa fa-user"></i>voucher encontrados para el rut:</div>
                                        <div class="card-body">
                                            <div class="table-responsive">
                                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                    <thead>
                                                        <tr> 
                                                            <th>Id</th>
                                                            <th>Estacionamientos</th> 
                                                            <th>Opcion envio</th> 
                                                            <th>Total</th> 
                                                        </tr>
                                                    </thead>
                                                    <tbody> 
                                                        <c:forEach  items="${sessionScope.listadoVoucherVDRut}" var="voucherVD">
                                                            <tr>
                                                                <td><c:out value="${voucherVD.idVoucher}"></c:out> </td>
                                                                    <td><!-- Muestra cada nombre de estacionamiento dentro de los tickets-->
                                                                    <c:forEach  items="${voucherVD.listadoTickets}" var="ticketVD">
                                                                        <c:out value="${ticketVD.nombreEstacionamiento}"></c:out> 
                                                                    </c:forEach>
                                                                </td>
                                                                <td><c:out value="${voucherVD.opEnvio}"></c:out> </td> 
                                                                <td><c:out value="${voucherVD.totalVoucher}"></c:out> </td> 
                                                                    <!-- Carga la pagina con los detalles del voucher llamando al servel verDetalleVoucherServlet-->
                                                                <c:url value="./verDetalleVoucherServlet" var="detalle">
                                                                    <c:param name="codigo" value="${voucherVD.idVoucher}"></c:param>
                                                                </c:url>
                                                                <td><input type="button" class=" btn-warning btn-small" name="btnDetalle" value="+" onclick="window.location.href = '${detalle}'"> </td>


                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <div class="card-footer small text-muted">Auto Park.</div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>

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
