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
              <h1 class="text-white font-weight-bold">Atualizar Informações da Hospedagem</h1>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- enctype="multipart/form-data" -->
    <form id="formAddHotel" > 
        <input id="idHotelServ" name="idHotelServ" value="${hospedagem.id}" type="text" style="visibility: hidden">
        <input id="nomeHotelServ" name="nomeHotelServ" type="text" style="visibility: hidden">
        <input id="enderecoServ" name="enderecoServ" type="text" style="visibility: hidden">
        <input id="bairroServ" name="bairroServ" type="text" style="visibility: hidden">
        <input id="numeroServ" name="numeroServ" type="text" style="visibility: hidden">
        <input id="complementoServ" name="complementoServ" type="text" style="visibility: hidden">
        <input id="cepServ" name="cepServ" type="text" style="visibility: hidden">        
    </form>
    <section class="site-section">
      <div class="container">
        <div class="row" align="center" >
            <div align="right"class="col-lg-4 col-sm-4 blog-content" >
                <p style="font-size:24px">Nome Hotel</p>
            </div>  
          <div class="col-lg-5 col-sm-5 blog-content" >            
            <input id="nomeHotel" type="text" class="form-control form-control-lg" placeholder=""
                   style="text-align: center;" maxlength="100" value="${hospedagem.nome}">     
          </div>
        </div>
        <div class="row" align="center" >
            <div align="right"class="col-lg-4 col-sm-4 blog-content" >
                <p style="font-size:24px">Endereço</p>  
            </div>  
          <div class="col-lg-5 col-sm-5 blog-content" >            
            <input maxlength="100" placeholder="" id="endereco" type="text" class="form-control form-control-lg"
                   style="text-align: center;" value="${hospedagem.rua}">      
          </div>
        </div> 
        <div class="row" align="center" >
            <div align="right"class="col-lg-4 col-sm-4 blog-content" >
                <p style="font-size:24px">Bairro</p>  
            </div>  
            <div class="col-lg-5 col-sm-5 blog-content">
                <select id="bairro" class="form-control form-control-lg" style="align-content:center;text-align:center;text-align-last: center;">
                    <option value="${hospedagem.idBairro}" selected>${hospedagem.bairro}</option>
                    <c:forEach items="${listaBairros}" var="bairro">
                        <option value="${bairro.idBairro}">${bairro.descricao}</option>
                    </c:forEach>                         
                </select>
            </div>
        </div>   
        <div class="row" align="center" >
            <div align="right"class="col-lg-4 col-sm-4 blog-content" >
                <p style="font-size:24px">Número</p>  
            </div>  
          <div class="col-lg-5 col-sm-5 blog-content" >            
            <input maxlength="100" placeholder="" id="numero" type="text" class="form-control form-control-lg"
                   style="text-align: center;" onkeypress='return SomenteNumero(event)' value="${hospedagem.numero}">      
          </div>
        </div>   
        <div class="row" align="center" >
            <div align="right"class="col-lg-4 col-sm-4 blog-content" >
                <p style="font-size:24px">Complemento</p>  
            </div>  
          <div class="col-lg-5 col-sm-5 blog-content" >            
            <input maxlength="100" placeholder="" id="complemento" type="text" class="form-control form-control-lg"
                   style="text-align: center;" value="${hospedagem.complemento}">      
          </div>
        </div>  
        <div class="row" align="center" >
            <div align="right"class="col-lg-4 col-sm-4 blog-content" >
                <p style="font-size:24px">Cep</p>  
            </div>  
          <div class="col-lg-5 col-sm-5 blog-content" >            
            <input onkeypress="return mask(event, this, '#####-###')"  maxlength="9" placeholder="99999-999" 
                   id="cep" name="cep" type="text" class="form-control form-control-lg"
                   style="text-align: center;" value="${hospedagem.cep}">      
          </div>
        </div>                  
                   
        <div class="row" >
          <div align="center" class="col-lg-12 col-sm-12 blog-content" >            
            <input id="btnSubmit" type="submit" class="btn btn-primary btn-lg" 
                   accept=""value="Atualizar" />          
          </div>
        </div>  
          
        <div class="row" style="visibility:hidden" >  
            <a id="abreModal" href="#open-modal">Open Modal</a>
        </div>               
          
      </div>
    </section>

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
    
    <!-- input mask -->
    <script src="https://rawgit.com/RobinHerbots/Inputmask/3.x/dist/jquery.inputmask.bundle.js"></script> 
    <script>
        $("input[id*='cep']").inputmask({
            mask: ['99999-999'],
            keepStatic: true
        });
        
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
        function mostraModal(msg, func){            
            document.getElementById("modalText").innerText = msg;  
            if( func == 'WAIT'){
                document.getElementById("modalTagH").innerText = 'CARREGANDO';  
                document.getElementById("modalTagA").style.visibility = 'hidden';                                 
            }
            document.getElementById('abreModal').click();
        }
        ///////////////////////////////////////////////////////////////////////
        
        //parametros vindo da servlet
        //var urlParams = new URLSearchParams(window.location.search);
        //var varIdQuarto = urlParams.get('idQuarto');
        //console.log('id do quarto: ' + urlParams.get('idQuarto')); 

        /////////////// INPUTS /////////////////////////////////////////////////////////////////////
        var nomeHotel = document.getElementById('nomeHotel');   
        var endereco = document.getElementById('endereco');
        var numero = document.getElementById('numero');
        var complemento = document.getElementById('complemento');
        var bairro = document.getElementById('bairro');   
        var cep = document.getElementById('cep');
        var btnSubmit = document.getElementById('btnSubmit');
                        
        btnSubmit.addEventListener('click', function (){
            var idBairro = bairro.options[bairro.selectedIndex].value;
            console.log('bairro: '+idBairro);
                
            if( nomeHotel.value.length <= 0 ){
                mostraModal("Preencha o campo NOME HOTEL.",0);            
                return;
            }
            if( endereco.value.length <= 0 ){
                mostraModal("Preencha o campo ENDEREÇO.",0);                
                return;
            }
            if( numero.value.length <= 0 ){
                mostraModal("Preencha o campo NÚMERO.",0);                
                return;
            }
            if( idBairro == "" ){               
                mostraModal("Preencha o campo BAIRRO.",0);                
                return;
            }
            if( cep.value.length <= 0 ){
                mostraModal("Preencha o campo CEP.",0);                
                return;
            }
            
            ///////////////////////////////////////////////
            //chama servlet
            document.getElementById("formAddHotel").action="./AdministradorServlet?action=edtHospedagem";
            document.getElementById("formAddHotel").method = "POST";
            document.getElementById("formAddHotel").nomeHotelServ.value = nomeHotel.value;
            document.getElementById("formAddHotel").enderecoServ.value = endereco.value;
            document.getElementById("formAddHotel").bairroServ.value = idBairro;
            document.getElementById("formAddHotel").numeroServ.value = numero.value;
            document.getElementById("formAddHotel").complementoServ.value = complemento.value;
            document.getElementById("formAddHotel").cepServ.value = cep.value;

            document.getElementById("formAddHotel").submit();           
        ////////////////////////////////////////////////////////////////////////////////////
                    
        //secondFunction(arrayUpload, nomeUid);
        //chama servlet      
        //resolverDepoisDe40Segundos(nomeQuarto.value, preco.value, adicionais, classificacao, nomeUid);                
        });    
          
      function resolverDepoisDe40Segundos(nomeQuarto, preco, adicionais, classificacao, nomeUid) {
        return new Promise(resolve => {
          setTimeout(() => {
              mostraModal("As imagens foram carregadas.","WAIT");                 
              console.log('printa qlq coisa depois de 45seg');
              
              //chama servlet
              document.getElementById("formAddQuarto").action="./QuartoServlet?action=insere";
              document.getElementById("formAddQuarto").method = "POST";
              document.getElementById("formAddQuarto").nomeQuartoServ.value = nomeQuarto;
              document.getElementById("formAddQuarto").precoServ.value = preco;
              document.getElementById("formAddQuarto").adicionaisServ.value = adicionais;
              document.getElementById("formAddQuarto").classificacaoServ.value = classificacao;
              document.getElementById("formAddQuarto").nomeUidServ.value = nomeUid;
                          
              document.getElementById("formAddQuarto").submit();           
          }, 45000);
        });
      }
      
      async function secondFunction(arrayUpload, nomeUidArray){
       mostraModal("A imagem está sendo carregada. Por favor aguarde.","WAIT");            
        await insereImagemFirebase(arrayUpload, nomeUidArray );
        // now wait for firstFunction to finish...
        // do something else
      };
      
      async function insereImagemFirebase(arrayUpload, nomeUidVet ){
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
        
        var storageRef0 = firebase.storage().ref('/roomImage/'+nomeUidVet[0]);                       
        var uploadTask0 = storageRef0.put(arrayUpload[0]);
        uploadTask0.on('state_changed', 
        function progress(snapshot){
            //alert('progresso..');
            var percentege = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
        }, function error(err){
            alert('deu erro ');
            mostraModal("Ocorreu algum erro ao inserir a imagem",0); 
        }, function complete(){               
            //var downloadURL = uploadTask.snapshot.downloadURL;
            alert('terminou de carregar imagem');
        });
        var storageRef1 = firebase.storage().ref('/roomImage/'+nomeUidVet[1]);                       
        var uploadTask1 = storageRef1.put(arrayUpload[1]);
        uploadTask1.on('state_changed', 
        function progress(snapshot){
            //alert('progresso..');
            var percentege = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
        }, function error(err){
            alert('deu erro ');
            mostraModal("Ocorreu algum erro ao inserir a imagem",0); 
        }, function complete(){               
            //var downloadURL = uploadTask.snapshot.downloadURL;
            alert('terminou de carregar imagem');
        });
        var storageRef2 = firebase.storage().ref('/roomImage/'+nomeUidVet[2]);                       
        var uploadTask2 = storageRef2.put(arrayUpload[2]);
        uploadTask2.on('state_changed', 
        function progress(snapshot){
            //alert('progresso..');
            var percentege = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
        }, function error(err){
            alert('deu erro ');
            mostraModal("Ocorreu algum erro ao inserir a imagem",0); 
        }, function complete(){               
            //var downloadURL = uploadTask.snapshot.downloadURL;
            alert('terminou de carregar imagem');
        });
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
    function SomenteNumero(e){
        var tecla=(window.event)?event.keyCode:e.which;   
        if((tecla>47 && tecla<58)) return true;
        else{
            if (tecla==8 || tecla==0) return true;
            else  return false;
        }
    }
    </script> 
    
    </c:when>
  <c:otherwise>
      <p>You're not logged in!</p>
      <c:redirect url = "index.jsp"/>
  </c:otherwise> 
</c:choose> 

</body>
</html>
