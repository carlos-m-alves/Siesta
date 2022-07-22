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
          <div class="site-logo col-6"><a href="index.html">S;ESTA</a></div>

          <nav class="mx-auto site-navigation">
            <ul class="site-menu js-clone-nav d-none d-xl-block ml-0 pl-0">
              <li><a href="UsuarioServlet?action=historico&recordsPerPage=5&currentPage=1" class=" nav-link">Minhas Reservas</a></li>
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
              <h1 class="text-white font-weight-bold">Lista de Quartos</h1>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="site-section">
      <div class="container">
          
        <form id="formPesqAdd">
            <input id="idHospedagem" name="idHospedagem" type="text" style="visibility: hidden">
            <input id="recordsPerPage" name="recordsPerPage" type="text" style="visibility: hidden">
            <input id="currentPage" name="currentPage" type="text" style="visibility: hidden">
            <input id="add" name="add" type="text" style="visibility: hidden">
        </form>
          
        <form action="UsuarioServlet?action=pesquisaAdicionais&idHospedagem=${idHospedagem}&recordsPerPage=5&currentPage=1" method="POST">
            <div class="row mb-5 justify-content-center">
              <h2 class="section-title mb-2">
                  Filtrar por adicionais:
              </h2>
              <div class="col-md-7 "style="display: flex;
                    align-items: center; justify-content: center;" >   
                  <fieldset>
                    <input style="margin-left:3px;margin-right: 3px" type="checkbox" class="inline" id="add1" name="add" value="1">Ar-condicionado
                    <input style="margin-left:3px;margin-right: 3px" type="checkbox" class="inline" id="add2" name="add" value="2">Wi-fi
                    <input style="margin-left:3px;margin-right: 3px" type="checkbox" class="inline" id="add3" name="add" value="3">Televisão
                  </fieldset>
              </div>
                <button type="submit" class="btn btn-primary btn-md">Pesquisar</button>
            </div>
        </form>  
          
        <div class="mb-5">
            <c:forEach var="quarto" items="${listaQuartos}">   
                <div class="row align-items-start job-item border-bottom pb-3 mb-3 pt-3">
                  <div class="col-md-2">
                    <a href="UsuarioServlet?action=inf&idQuarto=${quarto.id}"><img src="images/featured-listing-1.jpg" alt="Image" class="img-fluid"></a>
                  </div>
                  <div class="col-md-4">
                    <h2><a href="UsuarioServlet?action=inf&idQuarto=${quarto.id}">${quarto.nome}</a> </h2>
                  </div>
                  <div class="col-md-3 text-left">
                    <h3></h3>
                    <span class="meta"></span>
                  </div>
                  <div class="col-md-3 text-md-right">
                    <strong class="text-black">
                         <fmt:setLocale value = "pt_BR"/>
                         <fmt:formatNumber value = "${quarto.preco}" type = "currency"/>
                    </strong>
                  </div>
                </div>
            </c:forEach>
        </div>

        <div class="row pagination-wrap">

          <div class="col-md-6 text-center text-md-left">
            <div class="custom-pagination ml-auto">
              
              <c:if test="${currentPage != 1}">
                <!--<a class="prev" 
                    href="QuartoServlet?action=pagination&idHospedagem=${idHospedagem}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">
                    Anterior
                </a>
                --> 
                <a class="prev" href="#"
                    onclick="changePage(${idHospedagem},${recordsPerPage},${currentPage-1})">
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
                            <!--<a class="" 
                                href="QuartoServlet?action=pagination&idHospedagem=${idHospedagem}&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                            -->
                            <a class="" href="#"
                                onclick="changePage(${idHospedagem},${recordsPerPage},${i})">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
              </div>  
              
              <c:if test="${currentPage lt noOfPages}">
                <!--<a class="prev" 
                    href="QuartoServlet?action=pagination&idHospedagem=${idHospedagem}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">
                    Próxima
                </a>
                -->
                <a class="prev" href="#"
                    onclick="changePage(${idHospedagem},${recordsPerPage},${currentPage+1})">
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

  <!-- <script src="js/bootstrap-select.min.js"></script> -->

  <script src="js/custom.js"></script>
  
  <!-- LIB FIREBASE -->
    <script src="https://www.gstatic.com/firebasejs/6.2.4/firebase-app.js"></script>
    <!-- Add Firebase products that you want to use -->
    <script src="https://www.gstatic.com/firebasejs/6.2.4/firebase-auth.js"></script>
    <script src="https://www.gstatic.com/firebasejs/6.2.4/firebase-database.js"></script>
    <script src="https://www.gstatic.com/firebasejs/6.2.4/firebase-storage.js"></script>
    
  <script>
        function changePage(idHospedagem,recordsPerPage,currentPage){
            //alert('teste chamar idHospedagem: '+idHospedagem);
            //alert('teste chamar recordsPerPage: '+recordsPerPage);
            //alert('teste chamar currentPage: '+currentPage);
            var radiosAdd1 = document.getElementById('add1');
            var radiosAdd2 = document.getElementById('add2');
            var radiosAdd3 = document.getElementById('add3');
            var adicionais = [];
            var rdAr = radiosAdd1.checked   ? adicionais.push(radiosAdd1.value) : '';
            var rdWifi = radiosAdd2.checked ? adicionais.push(radiosAdd2.value) : '';
            var rdTv = radiosAdd3.checked   ? adicionais.push(radiosAdd3.value) : ''; 
            alert('qtd radios: '+adicionais.length);
            if( adicionais.length == 0 )
                adicionais = "";
            //chama servlet
            document.getElementById("formPesqAdd").action="./UsuarioServlet?action=pesquisaAdicionais";
            document.getElementById("formPesqAdd").method = "POST";
            document.getElementById("formPesqAdd").idHospedagem.value = idHospedagem;
            document.getElementById("formPesqAdd").recordsPerPage.value = recordsPerPage;
            document.getElementById("formPesqAdd").currentPage.value = currentPage;
            document.getElementById("formPesqAdd").add.value = adicionais;

            document.getElementById("formPesqAdd").submit(); 
        }       
        
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
