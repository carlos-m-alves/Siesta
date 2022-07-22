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
          <div class="site-logo col-6"><a href="#">S;esta</a></div>

          <nav class="mx-auto site-navigation">
            <ul class="site-menu js-clone-nav d-none d-xl-block ml-0 pl-0">
              <li><a href="AdministradorServlet?action=relatorios" class=" nav-link">Relatórios</a></li>
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
    <section class="home-section section-hero inner-page overlay bg-image"
      style="background-image: url('images/hero_1.jpg');" id="home-section">
      <div class="container">
        <div class="row align-items-center justify-content-center">
          <div class="col-md-12">
            <div class="mb-5 text-center">
              <h1 class="text-white font-weight-bold">Adicionar Parceiro</h1>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- enctype="multipart/form-data" -->
    <form id="formAddParc" > 
        <input id="nomeQuartoServ" name="nomeServ" type="text" style="visibility: hidden">
        <input id="precoServ" name="cpfServ" type="text" style="visibility: hidden">
        <input id="adicionaisServ" name="dataNascServ" type="text" style="visibility: hidden">
        <input id="classificacaoServ" name="emailServ" type="text" style="visibility: hidden">
    </form>
    <section class="site-section">
      <div class="container">
        <div class="row" align="center" >
            <div align="right"class="col-lg-4 col-sm-4 blog-content" >
                <p style="font-size:24px">Nome</p>
            </div>  
          <div class="col-lg-5 col-sm-5 blog-content" >            
            <input id="nome" type="text" class="form-control form-control-lg" placeholder=""
                   style="text-align: center;" maxlength="100">     
          </div>
        </div>
        <div class="row" align="center" >
            <div align="right"class="col-lg-4 col-sm-4 blog-content" >
                <p style="font-size:24px">CPF</p>  
            </div>  
          <div class="col-lg-5 col-sm-5 blog-content" >            
            <input onkeypress="return mask(event, this, '###.###.###-##')"  maxlength="14"
                   placeholder="" id="cpf" type="text" class="form-control form-control-lg"
                   style="text-align: center;">      
          </div>
        </div>    
        <div class="row" align="center" >
            <div align="right"class="col-lg-4 col-sm-4 blog-content" >
                <p style="font-size:24px">Data Nascimento</p>  
            </div>  
          <div class="col-lg-5 col-sm-5 blog-content" >            
            <input placeholder="12/12/1992" 
                   id="dataNasc" type="date" class="form-control form-control-lg" min="1950-01-01" max="2020-12-31"
                   style="text-align: center;">      
          </div>
        </div>
        <div class="row" align="center" >
            <div align="right"class="col-lg-4 col-sm-4 blog-content" >
                <p style="font-size:24px">Email</p>  
            </div>  
          <div class="col-lg-5 col-sm-5 blog-content" >            
            <input placeholder="" id="email" type="email" class="form-control form-control-lg" required
                   style="text-align: center;">      
          </div>
        </div>        
                  
        <div class="row" >
          <div align="center" class="col-lg-12 col-sm-12 blog-content" >            
            <input id="btnSubmit" type="submit" class="btn btn-primary btn-lg" 
                   accept=""value="Adicionar" />          
          </div>
        </div>  
          
        <div class="row" style="visibility:hidden" >  
            <a id="abreModal" href="#open-modal">Open Modal</a>
        </div>              
          
      </div>
    </section>
<!--    </form> -->


    <!-- MODAL -->

    <div id="open-modal" class="modal-window">
      <div>
        <a id="modalTagA" href="#modal-close" title="Close" class="modal-close">close &times;</a>
        <h1 id="modalTagH">Erro</h1>
        <div id="modalText">...</div>
      </div>
    </div>
    
    <!-- MODAL -->
    
    
 
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
    
    <script>
        function TestaCPF(strCPF) {
            var Soma;
            var Resto;
            Soma = 0;
          if (strCPF == "00000000000") return false;

          for (i=1; i<=9; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
          Resto = (Soma * 10) % 11;

            if ((Resto == 10) || (Resto == 11))  Resto = 0;
            if (Resto != parseInt(strCPF.substring(9, 10)) ) return false;

          Soma = 0;
            for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
            Resto = (Soma * 10) % 11;

            if ((Resto == 10) || (Resto == 11))  Resto = 0;
            if (Resto != parseInt(strCPF.substring(10, 11) ) ) return false;
            return true;
        }

        //mascara
        function mask(e, id, mask){
            var tecla=(window.event)?event.keyCode:e.which;   
            if((tecla>47 && tecla<58)){
                mascara(id, mask);
                return true;
            } 
            else{
                if (tecla==8 || tecla==0){
                    mascara(id, mask);
                    return true;
                } 
                else  return false;
            }
        }
        function mascara(id, mask){
            var i = id.value.length;
            var carac = mask.substring(i, i+1);
            var prox_char = mask.substring(i+1, i+2);
            if(i == 0 && carac != '#'){
                insereCaracter(id, carac);
                if(prox_char != '#')insereCaracter(id, prox_char);
            }
            else if(carac != '#'){
                insereCaracter(id, carac);
                if(prox_char != '#')insereCaracter(id, prox_char);
            }
            function insereCaracter(id, char){
                id.value += char;
            }
        }
        function uuidv4() {
          return ([1e7]+-1e3+-4e3+-8e3+-1e11).replace(/[018]/g, c =>
            (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
          )
        }
        function mostraModal(msg, func){            
            document.getElementById("modalText").innerText = msg;  
            if( func == 'WAIT'){
                document.getElementById("modalTagH").innerText = 'CARREGANDO';  
                document.getElementById("modalTagA").style.visibility = 'hidden';                                 
            }
            if( func == 'FECHAR'){
                document.getElementById("modalTagH").innerText = 'Opa!';                                
                document.getElementById("modalTagA").style.visibility = 'visible';                                 
            }
            document.getElementById('abreModal').click();
        }
        ///////////////////////////////////////////////////////////////////////
        
        //parametros vindo da servlet
        //var urlParams = new URLSearchParams(window.location.search);
        //var varIdQuarto = urlParams.get('idQuarto');
        //console.log('id do quarto: ' + urlParams.get('idQuarto')); 

        /////////////// INPUTS /////////////////////////////////////////////////////////////////////
        var nome = document.getElementById('nome');   
        var cpf = document.getElementById('cpf');
        var dataNasc = document.getElementById('dataNasc');
        var email = document.getElementById('email');
      
        btnSubmit.addEventListener('click', function (){
            var cpfOk = cpf.value.replace('.','').replace('-','');
            if( TestaCPF(cpfOk) ){
                mostraModal("Preencha com um CPF válido.",0);            
                return;
            }
            if( nome.value.length <= 0 ){
                mostraModal("Preencha o campo NOME.",0);            
                return;
            }
            if( cpf.value.length <= 0 ){
                mostraModal("Preencha o campo CPF.",0);                
                return;
            }
            if( dataNasc.value.length <= 0 ){
                mostraModal("Preencha o campo DATA NASCIMENTO.",0);                
                return;
            }
            if( email.value.length <= 0 ){
                mostraModal("Preencha o campo E-MAIL.",0);                
                return;
            }
            
            if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email.value)){
                //alert('ta ok o email');
            }else{
                mostraModal("Preencha com um e-mail válido.",0);   
                return;
            }
            ////////////////////////////////////////////////////////////////////////////////////
            //ajax
            //ajax - verifica se o email está na base
            var url = "EmailServlet";
            $.ajax({
                type: "GET",
                url : url, //url da Servlet
                data : {
                    email : email.value
                },//parametro passado para a Servlet
                dataType : 'json',
                success : function(data){
                    //alert('sucesso'+data);
                    if( data == true )
                        mostraModal("E-mail existente. Favor inserir outro.","FECHAR"); 
                },
                error : function(request, textSatus, errorThrown){
                    alert('deu erro??');
                    alert(request.status+',Error: '+request.statusText);
                    //Erro
                }
            });
            ////////////////////////////////////////////////////////////////////////////////////

            secondFunction(email.value, "123456");
            //chama servlet      
            //resolverDepoisDe10Segundos(nome.value, cpf.value, dataNasc.value, email.value);                
      });    
      function qlqCOisa(){
        //alert('printa algo');
        //chama servlet
        document.getElementById("formAddParc").action="./AdministradorServlet?action=insereParc";
        document.getElementById("formAddParc").method = "POST";
        document.getElementById("formAddParc").nomeServ.value = document.getElementById('nome').value;
        document.getElementById("formAddParc").cpfServ.value = document.getElementById('cpf').value;
        document.getElementById("formAddParc").dataNascServ.value = document.getElementById('dataNasc').value;
        document.getElementById("formAddParc").emailServ.value = document.getElementById('email').value;

        document.getElementById("formAddParc").submit();           
      }    
          
          
      function resolverDepoisDe10Segundos(nome, cpf, dataNasc, email) {
        return new Promise(resolve => {
          setTimeout(() => {
              mostraModal("O usuário foi inserido.","WAIT");                 
              console.log('printa qlq coisa depois de 10seg');
              
              //chama servlet
              document.getElementById("formAddParc").action="./AdministradorServlet?action=insereParc";
              document.getElementById("formAddParc").method = "POST";
              document.getElementById("formAddParc").nomeServ.value = nome;
              document.getElementById("formAddParc").cpfServ.value = cpf;
              document.getElementById("formAddParc").dataNascServ.value = dataNasc;
              document.getElementById("formAddParc").emailServ.value = email;
                          
              document.getElementById("formAddParc").submit();           
          }, 10000);
        });
      }
      
      async function secondFunction(email, senha){
       mostraModal("Carregando...","WAIT");            
        await insereUsuarioFirebase(email, senha);
        // now wait for firstFunction to finish...
        // do something else
      };
      
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
        
      async function insereUsuarioFirebase(email, senha ){
       
       firebase.auth().createUserWithEmailAndPassword(email, senha).then(function() {
            // Sign-out successful.
            mostraModal("Usuário inserido.",0);
            qlqCOisa();
            //alert('Usuário criado no firebase');
        }, function(error) {
          // An error happened.
          //alert('Não foi possivel criar um usuário.');
          //qlqCOisa();          
          mostraModal("Não foi possível inserir o usuário.","FECHAR"); 
        });
        
        /*
        firebase.auth().createUserWithEmailAndPassword(email, password).catch(function(error) {
            // Handle Errors here.
            var errorCode = error.code;
            var errorMessage = error.message;
            // ...
        });*/
      };    
        
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
    
    </c:when>
  <c:otherwise>
      <p>You're not logged in!</p>
      <c:redirect url = "index.jsp"/>
  </c:otherwise> 
</c:choose> 

</body>
</html>
