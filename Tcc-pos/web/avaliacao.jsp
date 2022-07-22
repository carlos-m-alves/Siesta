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
              <h1 class="text-white font-weight-bold">Avalie</h1>
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
        <form action="UsuarioServlet?action=avaliacao" method="POST">
            <div class="mb-5">
                <div class="row" align="center" >
                    <div align="center"class="col-lg-12 col-sm-12 blog-content" >
                        <p style="font-size:24px">Avalie a sua estadia</p>  
                    </div> 
                </div>
                <div class="row" align="center" >
                    <div class="col-lg-12 col-sm-12 blog-content" style="display: flex; align-items: center; justify-content: center;">
                        <fieldset class="rating" >
                            <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                            <input type="radio" id="star4half" name="rating" value="4.5" /><label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
                            <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                            <input type="radio" id="star3half" name="rating" value="3.5" /><label class="half" for="star3half" title="Meh - 3.5 stars"></label>
                            <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
                            <input type="radio" id="star2half" name="rating" value="2.5" /><label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
                            <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                            <input type="radio" id="star1half" name="rating" value="1.5" /><label class="half" for="star1half" title="Meh - 1.5 stars"></label>
                            <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                            <input type="radio" id="starhalf" name="rating" value="0.5" /><label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
                        </fieldset> 

                    <style>
                        @import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);

                        fieldset, label { margin: 0; padding: 0; }

                        h1 { font-size: 1.5em; margin: 10px; }

                        /****** Style Star Rating Widget *****/

                        .rating { 
                          border: none;
                          float: left;
                        }

                        .rating > input { display: none; } 
                        .rating > label:before { 
                          margin: 5px;
                          font-size: 1.25em;
                          font-family: FontAwesome;
                          display: inline-block;
                          content: "\f005";
                        }

                        .rating > .half:before { 
                          content: "\f089";
                          position: absolute;
                        }

                        .rating > label { 
                          color: #ddd; 
                         float: right; 
                        }

                        /***** CSS Magic to Highlight Stars on Hover *****/

                        .rating > input:checked ~ label, 
                        .rating:not(:checked) > label:hover,
                        .rating:not(:checked) > label:hover ~ label { color: #FFD700;  } 

                        .rating > input:checked + label:hover, 
                        .rating > input:checked ~ label:hover,
                        .rating > label:hover ~ input:checked ~ label, 
                        .rating > input:checked ~ label:hover ~ label { color: #FFED85;  } 

                    </style>
                    </div> 
                </div>
                <input id="idQuarto" name="idQuarto" type="text" value="${idQuarto}" style="visibility: hidden">
                <input id="idReserva" name="idReserva" type="text" value="${idReserva}" style="visibility: hidden">
                <br />
                <div class="row" align="center" >
                    <div align="center"class="col-lg-12 col-sm-12 blog-content" >
                        <input id="btnSubmit" type="submit" class="btn btn-primary btn-lg" 
                        value="Enviar" /> 
                    </div> 
                </div>
            </div>
    </form>
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
    </script>

</body>

</html>
