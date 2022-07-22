<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  </body>
  
    
    
    
  </head>
  <body id="top">

<div class="site-wrap">
<c:choose>
  <c:when test="${!sessionScope.logado}">    
    <!-- HOME -->
    <section class="home-section section-hero overlay bg-image" style="background-image: url('images/hero_1.jpg');" id="home-section">

      <div class="container">
        <div class="row align-items-center justify-content-center">
          <div class="col-md-12">
            <div class="mb-5 text-center">
              <h1 class="text-white font-weight-bold">S;ESTA</h1>
            </div>             
            
            <div class="row mb-2 justify-content-center">
                <h5 style="color:white" class="mb-2">${msg}</h5>
            </div>  
              
            <!--<form id="formLogin" method="post" action="LoginServlet?action=login" class="search-jobs-form">        -->
            <form id="formLogin" class="search-jobs-form"> 
                <input id="email" name="email" type="text" style="visibility: hidden">
                <input id="senha" name="senha" type="password" style="visibility: hidden">
            </form>              
              <div class="row mb-5">
                <div class="col-12 col-sm-6 col-md-6 col-lg-6 mb-6 mb-lg-0">
                  <p class="text-center">Digite seu e-mail:</p>
                </div>
                <div class="col-12 col-sm-6 col-md-6 col-lg-6 mb-6 mb-lg-0">
                  <input id="email2" name="email" type="text" class="form-control form-control-lg" placeholder="E-mail">
                </div>
              </div>
              <div class="row mb-5">
                <div class="col-12 col-sm-6 col-md-6 col-lg-6 mb-6 mb-lg-0">
                  <p class="text-center">Digite sua senha:</p>
                </div>
                <div class="col-12 col-sm-6 col-md-6 col-lg-6 mb-6 mb-lg-0">
                  <input id="senha2" name="senha" type="password" class="form-control form-control-lg" placeholder="Senha">
                </div>
              </div>
              <div class="d-flex justify-content-center">
                  <button id="login" type="submit" class="btn btn-primary btn-lg text-white btn-search">
                      <span class="icon-search icon mr-2"></span>
                      Entrar
                  </button>
              </div>  
              <div class="d-flex justify-content-center">
                  <a href="add_usuario.jsp">Novo usuário</a>
              </div>
            
               
          </div>
        </div>
      </div> 
    </section>
  </div>
</c:when>
<c:otherwise>
  <!-- <p>You're not logged in!</p> -->
  <c:redirect url = "/LoginServlet?action=list"/>
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
    var inputEmail = document.getElementById('email2');
    var inputPassword = document.getElementById('senha2');
    var btnLogin = document.getElementById('login');

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
        
    btnLogin.addEventListener('click', function (){
            
        firebase.auth().signInWithEmailAndPassword(inputEmail.value, inputPassword.value).then(function (result){
            //alert('conectado no firebase');
            //alert('conectado no firebase. email: '+inputEmail.value);
            document.getElementById("formLogin").action="./LoginServlet?action=login";
            document.getElementById("formLogin").method = "POST";
            document.getElementById("formLogin").email.value = inputEmail.value;
            document.getElementById("formLogin").senha.value = inputPassword.value;
            document.getElementById("formLogin").submit();  
            
        }).catch(function(error) {        
            alert('nao conectado');
            // Handle Errors here.
            var errorCode = error.code;
            var errorMessage = error.message;
            // ...
            console.log(errorCode);
            console.log(errorMessage);
        });

      /*  firebase.auth().onAuthStateChanged(function(user) {
            if (user) {
              // User is signed in.
              alert('ja esta logado depois de chamar a funcao signInWithEmailAndPassword');
            } else {
              // No user is signed in.
              alert('n esta logado depois de chamar a funcao signInWithEmailAndPassword');
            }
        });  */    
    });

    </script> 
    
</html>
