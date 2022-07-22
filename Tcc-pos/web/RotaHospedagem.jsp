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
  
  <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 80%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 80%;
        margin: 0;
        padding: 0;
      }
      #floating-panel {
        position: absolute;
        top: 10px;
        left: 25%;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
        text-align: center;
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
      }
    </style>
</head>

<body id="top" >
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
          <div class="site-logo col-6"><a href="#">S;ESTA</a></div>

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
    <section class="section-hero overlay inner-page bg-image" style="background-image: url('images/hero_1.jpg');"
      id="home-section">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <h1 id="nomeQuarto" class="text-white">Trajeto até ${quarto.nome}</h1>
          </div>
        </div>
      </div>
    </section>

    <section class="site-section" id="next-section">
      <div class="container" >
        <div class="row" align="center" >
            <input type="hidden" value="${cep}" name="cep" id="cep" />
        <!-- <div id="floating-panel">-->        
         <div id="map" style="text-align:center; width:1200px;height: 600px;"></div>
        </div>
      </div>
    </section>

    <footer class="site-footer">

      <div class="container">
        
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

  <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
    <script>
        function initMap() {        
                
            var directionsService = new google.maps.DirectionsService();
            var directionsRenderer = new google.maps.DirectionsRenderer();
            var map = new google.maps.Map(document.getElementById('map'), {
              zoom: 10,
    //         center: {lat: 41.85, lng: -87.65}
    //	  center: {lat: -25.53, lng: -49.27} //minha casa
              center: {lat: -25.539037, lng: -49.276460} 
              //-25.408428, -49.199826
              //lat: -25.4094202 lng: -49.1992982
              //-25.539037, -49.276460 //minha casa
            });
  
        //////////////added
            infoWindow = new google.maps.InfoWindow;
            // Try HTML5 geolocation.
            if (navigator.geolocation) {
              navigator.geolocation.getCurrentPosition(function(position) {
                var pos = {
                  lat: position.coords.latitude,
                  lng: position.coords.longitude
                };
                //  console.log("lat atual: "+pos.lat)
                //  console.log("lng atual: "+pos.lng)
                infoWindow.setPosition(pos);
                //var nomeQuarto = document.getElementById('nomeQuarto').innerHTML;
                var text = document.getElementsByTagName("h1")[0].innerText
                //var nome = text.replace("Trajeto até ","");
                //console.log('nomeQuarto '+document.getElementById('nomeQuarto').value);
                infoWindow.setContent('Localização atual');
                //infoWindow.setContent(text);
                //infoWindow.setContent(nome);                              
                
                infoWindow.open(map);
                map.setCenter(pos);
                
                calculateAndDisplayRoute(directionsService, directionsRenderer,pos);                
                directionsRenderer.setMap(map);
                
                
                console.log('cep '+document.getElementById('cep').value);
                
              }, function() {
                handleLocationError(true, infoWindow, map.getCenter());
              });
            } else {
              // Browser doesn't support Geolocation
              handleLocationError(false, infoWindow, map.getCenter());
            }
            ///////////////added
            
            

            //var onChangeHandler = function() {
            //  calculateAndDisplayRoute(directionsService, directionsRenderer);
            //  console.log('passou antes aqui??');
            //  directionsRenderer.setMap(map);
            //};
            //document.getElementById('start').addEventListener('change', onChangeHandler);
            //document.getElementById('end').addEventListener('change', onChangeHandler);
          }

          function calculateAndDisplayRoute(directionsService, directionsRenderer,pos) {
            directionsService.route(
                {
                  //origin: {query: document.getElementById('start').value},
                  //origin: {query: '81900520'}, //funciona
                  origin: {query: pos.lat+" "+pos.lng},
                  //destination: {query: document.getElementById('end').value},                  
                  destination: {query: document.getElementById('cep').value},
                  //destination: {query: '81900350'},
                  //travelMode: 'DRIVING'
                  travelMode: 'WALKING'                   
                },
                function(response, status) {
                  if (status === 'OK') {
                    directionsRenderer.setDirections(response);
                  } else {
                    window.alert('Directions request failed due to ' + status);
                  }
                });
          }
          
          function handleLocationError(browserHasGeolocation, infoWindow, pos) {
            infoWindow.setPosition(pos);
            infoWindow.setContent(browserHasGeolocation ?
                                  'Error: The Geolocation service failed.' :
                                  'Error: Your browser doesn\'t support geolocation.');
            infoWindow.open(map);
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
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD3mub-jGaZDNF_Fj4s_3Ha81ml8ql2sIU&callback=initMap">
    </script>
</body>

</html>
