<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
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
  <script src="https://www.gstatic.com/firebasejs/6.2.4/firebase-storage.js"></script>
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
          <div class="site-logo col-6"><a href="index.jsp">S;ESTA</a></div>

          <nav class="mx-auto site-navigation">
            <ul class="site-menu js-clone-nav d-none d-xl-block ml-0 pl-0">
              <li><a href="#" class="active nav-link">Minhas Reservas</a></li>
              <li><a href="UsuarioServlet?action=pagination&idHospedagem=${idHospedagem}&recordsPerPage=5&currentPage=1" class="nav-link">Lista de Hospedagens</a></li>
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
    <section class="home-section section-hero inner-page overlay bg-image" style="background-image: url('images/hero_1.jpg');"
      id="home-section">

      <div class="container">         
        <div class="row align-items-center justify-content-center">
          <div class="col-md-12">
            <div class="mb-5 text-center">
              <h1 class="text-white font-weight-bold">Histórico de reservas</h1>
            </div>
          </div>
        </div>
      </div>

    </section>

    <section class="site-section">
      <div class="container">
        <div class="row mb-2 justify-content-center">
            <h4 class="mb-2">${msg}</h4>
        </div>
        <br />        

        <div class="mb-5">
            <c:forEach var="reserva" items="${listaReservas}">   
                <div class="text-center row align-items-start job-item border-bottom pb-3 mb-3 pt-3">
                  <div class="col-md-2">
                    <a href="UsuarioServlet?action=infHospedagem&idHospedagem=${reserva.idQuarto}"><img src="images/featured-listing-1.jpg" alt="Image" class="img-fluid"></a>
                  </div>
                  <div class="col-md-4">
                    <span class="badge badge-primary px-2 py-1 mb-3"></span>
                    <h2><a href="UsuarioServlet?action=inf&idQuarto=${reserva.idQuarto}">${reserva.nomeQuarto}</a> </h2>
                    <p class="meta">
                        Preço: 
                        <fmt:setLocale value = "pt_BR"/>
                        <fmt:formatNumber value = "${reserva.preco}" type = "currency"/>
                    </p>
                  </div>
                  <div class="col-md-3 text-left">
                    <h3>${reserva.bairro}</h3>
                    <span class="meta">${fn:toUpperCase(reserva.endereco.rua)}, ${reserva.endereco.numero}</span>
                    <h3><fmt:formatDate value="${reserva.dtReserva}" pattern="dd/MM/yyyy"/>
                        - <fmt:formatDate value="${reserva.horarioEntrada}" pattern="HH:mm"/></h3>
                  </div>
                  <div class="col-md-2">
                    <h3 style="center">
                        <!--avaliacao: ${reserva.avaliacao.avaliacao}
                        avaliacao: ${reserva.avaliacao.avaliacao eq 0.0}
                        -->
                        <c:choose>
                            <c:when test="${(reserva.pago eq true) && (reserva.avaliacao.avaliacao ne 0.0)}">
                                <strong onmouseover="apontaCursor(this)" title="Reserva já avaliada." class="text-black">Avalie</strong> 
                            </c:when>
                            <c:when test="${(reserva.pago eq false)}">
                                <strong onmouseover="apontaCursor(this)" title="Liberado após efetuar pagamento." class="text-black">Avalie</strong> 
                            </c:when>
                            <c:when test="${(reserva.pago eq true) && (reserva.avaliacao.avaliacao ne 0.0)}">
                                <strong onmouseover="apontaCursor(this)" title="Liberado após efetuar pagamento." class="text-black">Avalie</strong> 
                            </c:when>
                            <c:otherwise>
                                <a href="UsuarioServlet?action=avaliarPage&idQuarto=${reserva.idQuarto}&idReserva=${reserva.idReserva}">Avalie</a>
                            </c:otherwise>
                        </c:choose>
                    </h3>
                  </div>
                </div>
            </c:forEach>
        </div>

        <div class="row pagination-wrap">

          <div class="col-md-6 text-center text-md-left">
            <div class="custom-pagination ml-auto">
              
              <c:if test="${currentPage != 1}">
                <a class="prev" 
                    href="UsuarioServlet?action=historico&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">
                    Anterior
                </a>
              </c:if>

              <div class="d-inline-block">
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <a class="">
                                    ${i} <span class="sr-only">(current)</span></a>
                        </c:when>
                        <c:otherwise>
                            <a class="" 
                                href="UsuarioServlet?action=historico&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
              </div>  
              
              <c:if test="${currentPage lt noOfPages}">
                <a class="prev" 
                    href="UsuarioServlet?action=historico&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">
                    Próxima
                </a>
              </c:if> 
            
            </div>
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
      <c:redirect url = "login.jsp"/>
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

  <!-- <script src="js/bootstrap-select.min.js"></script> -->

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
    function apontaCursor(e){
        e = e || window.event;
	var target = e.target || e.srcElement;
        e.style.cursor = "pointer";
    }
    </script>

</body>

</html>
