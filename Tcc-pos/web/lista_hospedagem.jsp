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
  
    <link rel="stylesheet" href="css/modal.css">
  
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
              <li><a href="AdministradorServlet?action=relatorios" class=" nav-link">Relatórios</a></li>
              <li><a href="#" class="active nav-link">Lista de Hospedagens</a></li>
              <li><a href="AdministradorServlet?action=paginationFunc&recordsPerPage=5&currentPage=1" class="nav-link">Lista de Parceiros</a></li>
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
              <h1 class="text-white font-weight-bold">Lista de Hospedagens</h1>
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
        <div class="row mb-5 justify-content-center">
          <div class="col-md-7 text-center">            
            <h2 class="section-title mb-2">
                ${qtdHospedagem} 
                ${qtdHospedagem > 1 ? 'Hospedagens cadastradas' : 'Hospedagem cadastrada'}
            </h2>
          </div>
          <div class="col-md-2">
            <a href="AdministradorServlet?action=addHospedagem" class="btn btn-block btn-primary btn-md">Adicionar</a>
          </div>
        </div>

        <div class="mb-5">
            <form id="formDel">
                <input type="hidden" id="idHospedagem" name="idHospedagem" />
            </form>
            <c:forEach var="hospedagem" items="${listaHospedagens}">   
                <div class="row align-items-start job-item border-bottom pb-3 mb-3 pt-3">
                  <div class="col-md-2">
                    <a href="AdministradorServlet?action=infHospedagem&idHospedagem=${hospedagem.id}"><img src="images/featured-listing-1.jpg" alt="Image" class="img-fluid"></a>
                  </div>
                  <div class="col-md-4">
                    <h2><a href="AdministradorServlet?action=infHospedagem&idHospedagem=${hospedagem.id}">${hospedagem.nome}</a> </h2>
                    <p class="meta">${hospedagem.rua}</p>
                  </div>
                  <div class="col-md-3 text-left">
                    <h3>${hospedagem.bairro}</h3>
                    <span class="meta">${hospedagem.bairro}</span>
                  </div>
                  <div class="col-md-3 text-md-right">
                    <a onclick="mostraModal('Gostaria de excluir definitivamente?', 'CONFIRME',${hospedagem.id})" class="btn btn-danger btn-md">Excluir</a>  
                    <!--<a href="AdministradorServlet?action=delHospedagem&idHospedagem=${hospedagem.id}" class="btn btn-danger btn-md">Excluir</a>  -->
                  </div>
                </div>
            </c:forEach>
            
            <div class="row" style="visibility:hidden">  
                <a id="abreModal" href="#open-modal">Open Modal</a>
            </div>  
            <!-- MODAL -->
            <div id="open-modal" class="modal-window">
              <div>
                <a id="modalTagA" href="#modal-close" title="Close" class="modal-close">Fechar &times;</a>
                <h1 id="modalTagH">Erro</h1>
                <div id="modalText">...</div>
                <a id="modalTagConfirme" onClick="enviaForm()"  class="btn btn-block btn-primary btn-md">Excluir</a>
              </div>
            </div>    
            <!-- MODAL -->
        </div>

        <div class="row pagination-wrap">

          <div class="col-md-6 text-center text-md-left">
            <div class="custom-pagination ml-auto">
              
              <c:if test="${currentPage != 1}">
                <a class="prev" 
                    href="AdministradorServlet?action=pagination&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">
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
                                href="AdministradorServlet?action=pagination&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
              </div>  
              
              <c:if test="${currentPage lt noOfPages}">
                <a class="prev" 
                    href="AdministradorServlet?action=pagination&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">
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
    function enviaForm(){
        document.getElementById("formDel").action="./AdministradorServlet?action=delHospedagem";
        document.getElementById("formDel").method = "POST";
        document.getElementById("formDel").idHospedagem.value = document.getElementById("idHospedagem").value;
        document.getElementById("formDel").submit(); 
    } 
      
    function mostraModal(msg, func, idParc){      
        //console.log('chegou aqui fora  '+msg+' '+func+' '+idParc);
        document.getElementById("modalText").innerText = msg;  
        if( func == 'WAIT'){
            //console.log('chegou aqui dentro');
            document.getElementById("modalTagH").innerText = 'CARREGANDO';  
            document.getElementById("modalTagA").style.visibility = 'hidden';   
            document.getElementById("modalTagConfirme").style.visibility = 'hidden';   
            
        }else if( func == 'CONFIRME' ){
            document.getElementById("modalTagH").innerText = 'Excluir';  
            document.getElementById("modalTagA").style.visibility = 'visible';   
            document.getElementById("modalTagConfirme").style.visibility = 'visible'; 
            document.getElementById("idHospedagem").value = idParc;  
            //console.log("id hosp setado: "+document.getElementById("idHospedagem").value);
        }else{
            document.getElementById("modalTagConfirme").style.visibility = 'hidden';   
            document.getElementById("modalTagA").style.visibility = 'visible';          
        }
        document.getElementById('abreModal').click();
        //console.log('chegou aqui fora  ');
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
