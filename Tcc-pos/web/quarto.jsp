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
</head>

<!--<body id="top"> -->
<body id="top" onload="carregaImagens()">
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

          <nav class="mx-auto site-navigation">
            <ul class="site-menu js-clone-nav d-none d-xl-block ml-0 pl-0">
              <li><a href="QuartoServlet?action=pagination&idHospedagem=${quarto.idHospedagem}&recordsPerPage=5&currentPage=1" class="nav-link">Lista de Quartos</a></li>
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
    <section class="section-hero overlay inner-page bg-image" style="background-image: url('images/garden-2-1470250.jpg');"
      id="home-section">
      <div class="container">
        <div class="row">
          <div class="col-md-7">
            <h1 class="text-white font-weight-bold">Informações do Quarto 
            </h1>
          </div>
        </div>
      </div>
    </section>
    
    
    <section class="site-section">
      <div class="container">
        <div class="row align-items-center mb-5">
          <div class="col-lg-8 mb-4 mb-lg-0">
            <div class="d-flex align-items-center">
              <div class="border p-2 d-inline-block mr-3 rounded">
                <img src="images/featured-listing-1.jpg" alt="Free Website Template By Free-Template.co">
              </div>
              <div>
                <h2>${quarto.nome}</h2>
                <div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-4">
            <div class="row">
              <div class="col-6">
                
              </div>
              <div class="col-6">
                <a href="QuartoServlet?action=editar&idQuarto=${quarto.id}" class="btn btn-block btn-primary btn-md">Editar</a>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-8">
            <div class="mb-5">                  
              <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                  <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                  <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                  <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img id="imgFoto1" class="d-block w-100" alt="First slide">
                  </div>
                  <div class="carousel-item">
                    <img id="imgFoto2" class="d-block w-100" alt="Second slide">
                  </div>
                  <div class="carousel-item">
                    <img id="imgFoto3" class="d-block w-100" alt="Third slide">
                  </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="sr-only">Next</span>
                </a>
              </div>   
             <br/>       
              <h3 class="h5 d-flex align-items-center mb-4 text-primary"><span class="icon-align-left mr-3"></span>
                Descrição</h3>
              <p>${quarto.descricao}</p>
            </div>                              
            
          </div>
          <div class="col-lg-4">
            <div class="bg-light p-3 border rounded mb-4">
              <h3 class="text-primary  mt-3 h5 pl-3 mb-3 ">
                  Informações 
              </h3>
              <ul class="list-unstyled pl-3 mb-0">

                <li class="mb-2"><strong class="text-black">Preço:</strong> 
                    <fmt:setLocale value = "pt_BR"/>
                    <fmt:formatNumber value = "${quarto.preco}" type = "currency"/>
                </li>
                
              </ul>
              <ul class="list-unstyled pl-3 mb-0">
                <li class="mb-2"><strong class="text-black">Adicionais:</strong> <br/>
                    <c:forEach var="add" items="${quarto.listaAdicionais}">   
                        <c:choose>
                            <c:when test="${add.id == 1}">
                                Ar-condicionado <br/>
                            </c:when>
                            <c:when test="${add.id == 2}">
                                Wi-fi <br/>
                            </c:when>
                            <c:when test="${add.id == 3}">
                                Televisão <br/>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </li>                
              </ul>
                
            </div>
    
            <div class="bg-light p-3 border rounded">
              <h3 class="text-primary  mt-3 h5 pl-3 mb-3 ">Horário</h3>                        
              <div class="px-3">
                  <c:forEach var="hora" items="${listaHorarios}">   
                    <div class="custom-control custom-switch">
                        <input type="checkbox" class="custom-control-input" id="${hora.idHorario}" 
                            <c:out value="${hora.disponibilidade == 'DISPONÍVEL' ? 'checked disabled' : 'disabled'}"/> >
                        <label class="custom-control-label" for="${hora.idHorario}">
                          <c:url value = "ReservaServlet" var = "reserva">
                            <c:param name = "action" value = "update"/>
                            <c:param name = "idQuarto" value = "${quarto.id}"/>
                            <c:param name = "idHorario" value = "${hora.idHorario}"/>                            
                          </c:url>  
                                                                                 
                            <c:choose>
                                <c:when test="${hora.disponibilidade == 'DISPONÍVEL'}">
                                   
                                        <fmt:formatDate value="${hora.horario}" pattern="HH:mm"/> ${hora.disponibilidade}
                                    
                                </c:when>
                                <c:when test="${hora.disponibilidade == 'REGISTRADO'}">
                                    
                                        <fmt:formatDate value="${hora.horario}" pattern="HH:mm"/> ${hora.disponibilidade}
                                     
                                </c:when>
                                <c:otherwise>
                                    <a href="${reserva}">  
                                        <s> <fmt:formatDate value="${hora.horario}" pattern="HH:mm"/> </s> ${hora.disponibilidade}
                                    </a> 
                                </c:otherwise>
                            </c:choose>
                                                 
                      </label>
                    </div>
                  </c:forEach>
              </div>
            </div>
    
            <div class="row" style="visibility:hidden" >  
                <a id="abreModal" href="#open-modal">Open Modal</a>
            </div>  
            <!-- MODAL -->
            <div id="open-modal" class="modal-window">
              <div>
                <a id="modalTagA" href="#modal-close" title="Close" class="modal-close">close &times;</a>
                <h1 id="modalTagH">Erro</h1>
                <div id="modalText">...</div>
              </div>
            </div>    
            <!-- MODAL -->
    
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
  
  <!-- LIB FIREBASE -->
  <script src="https://www.gstatic.com/firebasejs/6.2.4/firebase-app.js"></script>
  <!-- Add Firebase products that you want to use -->
  <script src="https://www.gstatic.com/firebasejs/6.2.4/firebase-auth.js"></script>
  <script src="https://www.gstatic.com/firebasejs/6.2.4/firebase-database.js"></script>
  <script src="https://www.gstatic.com/firebasejs/6.2.4/firebase-storage.js"></script>

</body>

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
    
    function mostraModal(msg, func){            
        document.getElementById("modalText").innerText = msg;  
        if( func == 'WAIT'){
            console.log('chegou aqui dentro');
            document.getElementById("modalTagH").innerText = 'CARREGANDO';  
            document.getElementById("modalTagA").style.visibility = 'hidden';                                 
        }
        document.getElementById('abreModal').click();
         console.log('chegou aqui fora  ');
    }
        
    function carregaImagens() {
        mostraModal("Carregando imagens. Por favor, aguarde.","WAIT");    
        
        var fotosArray = ['${quarto.listaImagemFirebase[0]}',
                '${quarto.listaImagemFirebase[1]}','${quarto.listaImagemFirebase[2]}' ];  
      
        ///////// REFERÊNCIA AO FIREBASE /////////////////////////////////////////////////////////
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
            
        var checkDownloadImagens = 0;
            
        var storageRefImg = firebase.storage().ref('/roomImage/'+fotosArray[0]);    
        // Get the download URL
        storageRefImg.getDownloadURL().then(function(url) {
          // Insert url into an <img> tag to "download"
            document.getElementById('imgFoto1').src = url; 
            console.log('carregou imagem 1');
            checkDownloadImagens++;
            if( checkDownloadImagens == 3 ) document.getElementById("modalTagA").click();
        }).catch(function(error) {
          // A full list of error codes is available at
          // https://firebase.google.com/docs/storage/web/handle-errors
          switch (error.code) {
            case 'storage/object-not-found':
              mostraModal("File doesn't exist.",0);
              break;
            case 'storage/unauthorized':
              mostraModal("User doesn't have permission to access the object.",0);
              break;
            case 'storage/canceled':
              mostraModal("storage/canceled.",0);
              break;
            case 'storage/unknown':
              mostraModal("storage/unknown.",0);
              break;
          }
        });  
        var storageRefImg1 = firebase.storage().ref('/roomImage/'+fotosArray[1]);    
        // Get the download URL
        storageRefImg1.getDownloadURL().then(function(url) {
          // Insert url into an <img> tag to "download"
            document.getElementById('imgFoto2').src = url; 
            console.log('carregou imagem 2');
            checkDownloadImagens++;
            if( checkDownloadImagens == 3 ) document.getElementById("modalTagA").click();
        }).catch(function(error) {
          // A full list of error codes is available at
          // https://firebase.google.com/docs/storage/web/handle-errors
          switch (error.code) {
            case 'storage/object-not-found':
              mostraModal("File doesn't exist.",0);
              break;
            case 'storage/unauthorized':
              mostraModal("User doesn't have permission to access the object.",0);
              break;
            case 'storage/canceled':
              mostraModal("storage/canceled.",0);
              break;
            case 'storage/unknown':
              mostraModal("storage/unknown.",0);
              break;
          }
        }); 
        var storageRefImg3 = firebase.storage().ref('/roomImage/'+fotosArray[2]);    
        // Get the download URL
        storageRefImg3.getDownloadURL().then(function(url) {
          // Insert url into an <img> tag to "download"
            document.getElementById('imgFoto3').src = url; 
            console.log('carregou imagem 3');
            //depois que terminar fechar o modal
            checkDownloadImagens++;
            if( checkDownloadImagens == 3 ) document.getElementById("modalTagA").click();
        }).catch(function(error) {
          // A full list of error codes is available at
          // https://firebase.google.com/docs/storage/web/handle-errors
          switch (error.code) {
            case 'storage/object-not-found':
              mostraModal("File doesn't exist.",0);
              break;
            case 'storage/unauthorized':
              mostraModal("User doesn't have permission to access the object.",0);
              break;
            case 'storage/canceled':
              mostraModal("storage/canceled.",0);
              break;
            case 'storage/unknown':
              mostraModal("storage/unknown.",0);
              break;
          }
        });
    }
</script>
</html>
