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

<body id="top" onload="carrega()">
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
              <li><a href="#" class="active nav-link">Relatórios</a></li>
              <li><a href="AdministradorServlet?action=pagination&recordsPerPage=5&currentPage=1" class="nav-link">Lista de Hospedagens</a></li>
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
    <section class="section-hero overlay inner-page bg-image" style="background-image: url('images/hero_1.jpg');"
      id="home-section">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <h1 class="text-white">Relatórios</h1>
          </div>
        </div>
      </div>
    </section>


    <section class="site-section" id="next-section">
      <div class="container" >
        <div class="row" align="center" >
            <div class="col-md-6 blog-content" >
                <div class="col-md-12 offset-md-12">
                    <div class="card">
                        <div class="card-body">
                            <h1>Receita bruta
                        </div>
                        <div class="card-body">
                            <canvas id="myChart"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6 blog-content" >
                <div class="col-md-12 offset-md-12">
                    <div class="card">
                        <div class="card-body">
                            <h1>Hospedagens por bairro
                        </div>
                        <div class="card-body">
                            <canvas id="myChart2"></canvas>
                        </div>
                    </div>
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
  <script src="js/custom.js"></script>

  <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
    <script>
        function carrega(){
            getRelatorios();
            getRelatoriosHospedagens();
        }
        function getRelatorios(){            
            var url = "AjaxServlet";
            $.ajax({
                type: "GET",
                url : url, //url da Servlet
                data : {
                    //estadoId : estadoId
                },//parametro passado para a Servlet
                dataType : 'json',
                success : function(data){
                    //Se sucesso, limpa e preenche a combo de cidade
                    //alerta(JSON.stringify(data));
                    var lLucro = [];//new Array(5);  
                    var lMes = [];
                     
                    $.each(data, function(i, obj){
                        //console.log("i: ",i);
                        //alert( i + ": " + obj.lucro );
                        //alert( i + ": " + obj.mes );
                        var mydate = new Date(obj.data);
                        
                        var month = mydate.getUTCMonth() + 1; //months from 1-12
                        var day = mydate.getUTCDate();
                        var year = mydate.getUTCFullYear();
                        //var newdate = year + "/" + month + "/" + day;
                        var newdate = day + "/" + month + "/" + year;
                        //console.log("data formatada: "+newdate);
                        //console.log("string to date: "+mydate.format("yyyy/mm/dd").toDateString());
                        
                        //alert("mes: "+obj.data.toString());
                        lLucro.push(obj.lucro);
                        lMes.push(newdate);
                    });

                    //console.log("deu certo lucro: "+lLucro+".");
                    //console.log("deu certo mes: "+lMes+".");
                    
                    var ctx = document.getElementById('myChart').getContext('2d');      
                    var chart = new Chart(ctx, {
                        // The type of chart we want to create
                        type: 'line',

                        // The data for our dataset
                        data: {
                            labels: lMes,
                            datasets: [{
                                label: 'Lucro em R$',
                                //backgroundColor: 'rgb(255, 99, 132)',
                                borderColor: 'rgb(99, 255, 132)',
                                data: lLucro
                            }]
                        },

                        // Configuration options go here
                        options: {}            
                    });
                },
                error : function(request, textSatus, errorThrown){
                    alert(request.status+',Error: '+request.statusText);
                    //Erro
                }
            });
        }
        
        function getRelatoriosHospedagens(){            
            var url = "AjaxServletRelatorio";
            $.ajax({
                type: "GET",
                url : url, //url da Servlet
                data : {
                    //estadoId : estadoId
                },//parametro passado para a Servlet
                dataType : 'json',
                success : function(data){
                    //Se sucesso, limpa e preenche a combo de cidade
                    //alerta(JSON.stringify(data));
                    var lBairro = [];//new Array(5);  
                    var lQtd = [];
                     
                    $.each(data, function(i, obj){                        
                        lBairro.push(obj.bairro);
                        lQtd.push(obj.quantidade);
                    });

                    console.log("deu certo bairro "+lBairro+".");
                    console.log("deu certo qtd "+lQtd+".");
                    
                    var ctx2 = document.getElementById('myChart2').getContext('2d');      
                    var chart2 = new Chart(ctx2, {
                        type: 'line',
                        data: {
                            labels: lBairro,
                            datasets: [{
                                label: 'Quantidade de hospedagens por bairro de Cwb',
                                borderColor: 'rgb(255, 99, 132)',
                                data: lQtd
                            }]
                        },
                        options: {}            
                    });
                },
                error : function(request, textSatus, errorThrown){
                    alert(request.status+',Error: '+request.statusText);
                    //Erro
                }
            });
        }
    
        var oldData = [0, 10, 5, 2, 20, 30, 45];
        var newData = [10, 20, 30, 40, 50, 60, 70];
        var oldData1 = [99, 0, 4, 20, 40, 20, 12];
        var newData1 = [2, 10, 40, 55, 30, 65, 17];
        /*              
        var ctx2 = document.getElementById('myChart2').getContext('2d');   
        var chart2 = new Chart(ctx2, {
            // The type of chart we want to create
            type: 'line',

            // The data for our dataset
            data: {
                labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                datasets: [{
                    label: 'My First dataset',
                    //backgroundColor: 'rgb(255, 99, 132)',
                    borderColor: 'rgb(255, 99, 132)',
                    data: oldData
                },{
                    label: 'My Second dataset',
                    //backgroundColor: 'rgb(0, 200, 0)',
                    borderColor: 'rgb(0, 200, 0)',
                    data: oldData1
                }]
            },

            // Configuration options go here
            options: {}            
        });
        
        function updateChart(){
          //chart.data.datasets[0].data = newData;
          //chart.data.datasets[1].data = newData1;
          chart.data.labels = ["August","September","October"]
          chart.update();  
        };
        
        function addValue(){
          chart.data.labels.push("January");
          chart.update();  
        };
        */
        
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
