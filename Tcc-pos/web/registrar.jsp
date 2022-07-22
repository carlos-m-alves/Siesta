<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage = "erro.jsp" %>
<!doctype html>
<html lang="en">

<head>
  <title>S;esta</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="css/custom-bs.css">
  <link rel="stylesheet" href="css/jquery.fancybox.min.css">
  <link rel="stylesheet" href="css/bootstrap-select.min.css">
  <link rel="stylesheet" href="fonts/icomoon/style.css">
  <link rel="stylesheet" href="fonts/line-icons/style.css">
  <link rel="stylesheet" href="css/owl.carousel.min.css">
  <link rel="stylesheet" href="css/animate.min.css">
  <link rel="stylesheet" href="css/style.css">
  
  <!-- LIB FIREBASE -->
  <script src="https://www.gstatic.com/firebasejs/6.2.4/firebase-app.js"></script>
  <!-- Add Firebase products that you want to use -->
  <script src="https://www.gstatic.com/firebasejs/6.2.4/firebase-auth.js"></script>
  <script src="https://www.gstatic.com/firebasejs/6.2.4/firebase-database.js"></script>
</head>

<body id="top">
<c:choose>
 <c:when test="${! empty logado}">  

  <div class="site-wrap">

    <div class="site-mobile-menu site-navbar-target">
      <div class="site-mobile-menu-header">
        <div class="site-mobile-menu-close mt-3">
          <span class="icon-close2 js-menu-toggle"></span>
        </div>
      </div>
      <div class="site-mobile-menu-body"></div>
    </div> <!-- .site-mobile-menu -->


    <!-- NAVBAR -->
    <header class="site-navbar mt-3">
      <div class="container-fluid">
        <div class="row align-items-center">
          <div class="site-logo col-6"><a href="index.html">S;esta</a></div>

          <nav class="mx-auto site-navigation">
            <ul class="site-menu js-clone-nav d-none d-xl-block ml-0 pl-0">
              <li><a href="QuartoServlet?action=pagination&id=${idHotel}&recordsPerPage=5&currentPage=1" class="nav-link">Lista de Quartos</a></li>
            </ul>
          </nav>

          <div class="right-cta-menu text-right d-flex aligin-items-center col-6">
            <div class="ml-auto">
              <a id="sair" href="LogoutServlet" class="btn btn-primary border-width-2 d-none d-lg-inline-block"><span
                  class="mr-2 icon-paper-plane"></span>Sair</a>
            </div>
            <a href="#" class="site-menu-toggle js-menu-toggle d-inline-block d-xl-none mt-lg-2 ml-3"><span
                class="icon-menu h3 m-0 p-0 mt-2"></span></a>
          </div>

        </div>
      </div>
    </header>

    <!-- HOME -->
    <!-- HOME -->
    <section class="section-hero overlay inner-page bg-image" style="background-image: url('images/hero_1.jpg');"
      id="home-section">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <h1 class="text-white">Registrar</h1>
          </div>
        </div>
      </div>
    </section>


    <section class="site-section" id="next-section">
      <div class="container" >
        <div class="row" align="center" >
          <div class="col-lg-12 blog-content" >
            <h3 class="mb-4">Cliente: ${reserva.nomePessoa}</h3>
    
            <p><b>CPF:</b> ${reserva.cpf}</p>
            <p><b>DATA:</b> 
                <fmt:formatDate value="${reserva.dtReserva}" pattern="dd/MM/yyyy"/>
            </p>
            <p><b>HORA ENTRADA:</b> 
                <fmt:formatDate value="${reserva.horarioEntrada}" pattern="HH:mm"/>
            </p>
            <p><b>PREÇO:</b> 
                <fmt:setLocale value = "pt_BR"/>
                <fmt:formatNumber value = "${reserva.preco}" type = "currency"/>
            </p>
            
            <c:choose>
                <c:when test="${reserva.disponibilidade == 'CHECK-IN' }">
                    <form action="ReservaServlet?action=registrar&idReserva=${reserva.idReserva}&preco=${reserva.preco}&idQuarto=${reserva.idQuarto}&idHotel=${reserva.idHotel}&idHoras=${reserva.idHrChegada}&dtReservada=${reserva.dtReserva}" 
                        method="POST">  
                        <p><b>FORMA DE PAGAMENTO: </b> 
                            <div class="col-12 col-sm-6 col-md-6 col-lg-3 mb-4 mb-lg-0">
                              <select class="form-control" id="formaPagamento" name="formaPagamento">
                                <option value="${selected}" selected>${selected}</option>
                                 <c:forEach items="${listaFormasPagamento}" var="forma">
                                    <option value="${forma.idFormaPagamento}">${forma.descPagamento}</option>
                                 </c:forEach>   
                              </select>
                            </div>
                        </p>
                        <input type="submit" class="btn btn-primary border-width-2 d-none d-lg-inline-block" 
                           accept=""value="Pagamento Efetuado"/>    
                    </form>
                </c:when>  
                <c:when test="${reserva.disponibilidade == 'RESERVADO' }">
                    <c:url value = "ReservaServlet" var = "checkin">
                            <c:param name = "action" value = "checkin"/>
                            <c:param name = "idHotel" value = "${reserva.idHotel}"/>
                            <c:param name = "idQuarto" value = "${reserva.idQuarto}"/>
                            <c:param name = "idHoras" value = "${reserva.idHrChegada}"/>
                            <c:param name = "dtReservada" value = "${reserva.dtReserva}"/>
                    </c:url>  

                    <a href="${checkin}" class="btn btn-primary border-width-2 d-none d-lg-inline-block" >  
                       Check-in
                    </a> 
                </c:when>  
                <c:otherwise>
                    <p>Já foi pago.</p>
                </c:otherwise>
            </c:choose>               
          
          </div>  
        </div>
      </div>
    </section>

    <footer class="site-footer">

      <div class="container">
        <div class="row mb-5">
          <div class="col-6 col-md-3 mb-4 mb-md-0">
          </div>
          <div class="col-6 col-md-3 mb-4 mb-md-0">
          </div>
          <div class="col-6 col-md-3 mb-4 mb-md-0">
          </div>
          <div class="col-6 col-md-3 mb-4 mb-md-0">
          </div>
        </div>

        <div class="row text-center">
          <div class="col-12">
            <p>
              <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
              Copyright &copy;
              <script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made
              with <i class="icon-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com"
                target="_blank">Colorlib</a>
              <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            </p>
          </div>
        </div>
      </div>
    </footer>

  </div>

 </c:when>
  <c:otherwise>
      <p>You're not logged in!</p>
      <c:redirect url = "index.jsp"/>
  </c:otherwise> 
</c:choose> 
 
  <!-- SCRIPTS -->
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
  <script src="js/isotope.pkgd.min.js"></script>
  <script src="js/stickyfill.min.js"></script>
  <script src="js/jquery.fancybox.min.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>

  <script src="js/jquery.waypoints.min.js"></script>
  <script src="js/jquery.animateNumber.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/custom.js"></script>

  <script>
    var btnSair = document.getElementById('sair');       
    btnSair.addEventListener('click', function (){
         var firebaseConfig = {
        apiKey: "AIzaSyDeKP8dYDhhDElP6kxI6xqOTXMdZaM5NVg",
        authDomain: "tcc-pos-87a64.firebaseapp.com",
        databaseURL: "https://tcc-pos-87a64.firebaseio.com",
        projectId: "tcc-pos-87a64",
        storageBucket: "tcc-pos-87a64.appspot.com",
        messagingSenderId: "433779576620",
        appId: "1:433779576620:web:34bb258c7b959294"
      };
        // Initialize Firebase
        firebase.initializeApp(firebaseConfig);  
        firebase.auth().signOut().then(function() {
          // Sign-out successful.
          //alert('desconectado do firebase');
        }, function(error) {
          // An error happened.
        });

    });
      
  </script>
</body>

</html>
