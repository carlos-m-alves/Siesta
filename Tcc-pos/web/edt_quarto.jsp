<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="br.bean.Quarto"%>
<%@ page import="br.bean.Adicional"%>
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

<body id="top" onload="carregaImagens()">
<!--<body id="top">-->
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
          <div class="site-logo col-6"><a href="index.html">S;esta</a></div>

          <nav class="mx-auto site-navigation">
            <ul class="site-menu js-clone-nav d-none d-xl-block ml-0 pl-0">
              <li><a href="QuartoServlet?action=pagination&idHospedagem=${quarto.idHospedagem}&recordsPerPage=5&currentPage=1" class="nav-link">Lista de Hospedagens</a></li>
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
              <h1 class="text-white font-weight-bold">Editar Quarto</h1>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- enctype="multipart/form-data" -->
    <form id="formAddQuarto" > 
        <input id="nomeQuartoServ" name="nomeQuartoServ" type="text" style="visibility: hidden">
        <input id="descricaoServ" name="descricaoServ" type="text" style="visibility: hidden">
        <input id="precoServ" name="precoServ" type="text" style="visibility: hidden">
        <input id="adicionaisServ" name="adicionaisServ" type="text" style="visibility: hidden">
        <input id="idQuartoServ" name="idQuartoServ" type="text" style="visibility: hidden">
    </form>
    <section class="site-section">
      <div class="container">
        <div class="row" align="center" >
            <div align="right"class="col-lg-4 col-sm-4 blog-content" >
                <p style="font-size:24px">Nome Quarto:</p>
            </div>  
          <div class="col-lg-5 col-sm-5 blog-content" >            
            <input id="nomeQuarto" type="text" class="form-control form-control-lg" placeholder=""
                   style="text-align: center;" maxlength="100" value="${quarto.nome}">     
          </div>
        </div>
        <div class="row" align="center" >
            <div align="right"class="col-lg-4 col-sm-4 blog-content" >
                <p style="font-size:24px">Descrição:</p>
            </div>  
          <div class="col-lg-5 col-sm-5 blog-content" >            
           <!-- <input id="nomeQuarto" type="text" class="form-control form-control-lg" 
                   style="text-align: center;" maxlength="255" value="${quarto.descricao}">  
            --><textarea id="descricao" name="descricao" maxlength="255" name="textarea" style="width:445px;height:150px;resize: none;">${quarto.descricao}</textarea>
          </div>
        </div>
        <div class="row" align="center" >
            <div align="right"class="col-lg-4 col-sm-4 blog-content" >
                <p style="font-size:24px">Preço:</p>  
            </div>  
          <div class="col-lg-5 col-sm-5 blog-content" >            
            <input onkeypress="return mask(event, this, '##,##')"  maxlength="5" placeholder="R$ 9,99" 
                   id="preco" type="text" class="form-control form-control-lg"
                   style="text-align: center;" value="${quarto.preco}">      
          </div>
        </div>
        <div class="row" align="center" >
            <div align="right"class="col-lg-4 col-sm-4 blog-content" >
                <p style="font-size:24px">Adicionais:</p>  
            </div>  
          <div class="col-lg-5 col-sm-5 blog-content" style="display: flex;
               align-items: center; justify-content: center;" >            
                           
            <c:forEach var="add" items="${quarto.listaAdicionais}"> 
                <c:choose>
                    <c:when test="${add.id == 1}">  
                        <input style="margin-left:3px;margin-right: 3px" type="radio"
                               class="inline" id="add1" name="add1" value="1" checked>Ar-condicionado    
                         <c:set var = "add1" scope = "page" value = "ok"/>
                    </c:when>    
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${add1 != 'ok'}">   
                    <input style="margin-left:3px;margin-right: 3px" type="radio"
                               class="inline" id="add1" name="add1" value="1">Ar-condicionado    
                </c:when>    
            </c:choose>
            <c:forEach var="add" items="${quarto.listaAdicionais}"> 
                <c:choose>
                    <c:when test="${add.id == 2}">  
                        <input style="margin-left:3px;margin-right: 3px" type="radio" 
                               class="inline" id="add2" name="add2" value="2" checked>Wi-fi             
                         <c:set var = "add2" scope = "page" value = "ok"/>
                    </c:when>    
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${add2 != 'ok'}">   
                    <input style="margin-left:3px;margin-right: 3px" type="radio" 
                           class="inline" id="add2" name="add2" value="2">Wi-fi
             
                </c:when>    
            </c:choose>        
            <c:forEach var="add" items="${quarto.listaAdicionais}"> 
                <c:choose>
                    <c:when test="${add.id == 3}">  
                        <input style="margin-left:3px;margin-right: 3px" type="radio"
                               class="inline" id="add3" name="add3" value="3" checked>Televisão                      
                         <c:set var = "add3" scope = "page" value = "ok"/>
                    </c:when>    
                </c:choose>
            </c:forEach>                        
            <c:choose>                
                <c:when test="${add3 != 'ok'}">   
                    <input style="margin-left:3px;margin-right: 3px" type="radio" 
                           class="inline" id="add3" name="add3" value="3">Televisão                         
                </c:when>    
            </c:choose>
                    
            <input style="margin-left:3px;margin-right: 3px" id="limparCampos" type="submit" class="inline btn btn-dark" name="pay4" value="Limpar">
          </div>
        </div> 
        <div class="row" align="center" >
            <div align="center"class="col-lg-12 col-sm-12 blog-content" >
                <p style="font-size:24px">FOTOS:</p>  
            </div> 
        </div>
        <div class="row" align="center" > 
            <div class="col-lg-4 col-sm-4 blog-content" style=";margin-bottom:10px;" >            
                <input style="visibility: hidden" class="form-control file-chooser-1" type="file" accept="image/*" name="foto1" id="foto1" multiple> 
                <img id="imgFoto1" class="preview-img-1 file-chooser-1" height="200" width="180" style="border-radius: 10%; cursor: pointer; border:1px solid #dbdbdb">
            </div>
            <div class="col-lg-4 col-sm-4 blog-content" >            
                <input style="visibility: hidden" class="form-control file-chooser-2" type="file" accept="image/*" name="foto2" id="foto2" multiple>   
                <img id="imgFoto2" class="preview-img-2 file-chooser-2" height="200" width="180" style="border-radius: 10%; cursor: pointer; border:1px solid #dbdbdb">
            </div>
            <div class="col-lg-4 col-sm-4 blog-content" >            
                <input style="visibility: hidden" class="form-control file-chooser-3" type="file" accept="image/*" name="foto3" id="foto3" multiple>   
                <img id="imgFoto3" class="preview-img-3 file-chooser-3" height="200" width="180" style="border-radius: 10%; cursor: pointer; border:1px solid #dbdbdb">
            </div>
        </div>    
        <script>
            var fotoUmAlterada = false;
            var fotoDoisAlterada = false;
            var fotoTresAlterada = false;
            const $ = document.querySelector.bind(document);
            const previewImg1 = $('.preview-img-1');
            const fileChooser1 = $('.file-chooser-1');
            fileChooser1.onchange = e => {
                const fileToUpload = e.target.files.item(0);
                const reader1 = new FileReader();
                reader1.onload = e => previewImg1.src = e.target.result;
                reader1.readAsDataURL(fileToUpload);
                alert('foi alterado');
                //fotoUmAlterada = true;
            };
            
            const previewImg2 = $('.preview-img-2');
            const fileChooser2 = $('.file-chooser-2');
            fileChooser2.onchange = e => {
                const fileToUpload2 = e.target.files.item(0);
                const reader2 = new FileReader();
                reader2.onload = e => previewImg2.src = e.target.result;
                reader2.readAsDataURL(fileToUpload2);
                //fotoDoisAlterada = true;
            };
            
            const previewImg3 = $('.preview-img-3');
            const fileChooser3 = $('.file-chooser-3');
            fileChooser3.onchange = e => {
                const fileToUpload3 = e.target.files.item(0);
                const reader3 = new FileReader();
                reader3.onload = e => previewImg3.src = e.target.result;
                reader3.readAsDataURL(fileToUpload3);
                //fotoTresAlterada = true;
            };          
        </script>
          
          
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
    
      function carregaImagens() {
        mostraModal("Carregando imagens. Por favor, aguarde.","WAIT");    
        
        var fotosArray = ['${quarto.listaImagemFirebase[0]}',
                '${quarto.listaImagemFirebase[1]}','${quarto.listaImagemFirebase[2]}' ];  
               
        ///////// REFERÊNCIA AO FIREBASE /////////////////////////////////////////////////////////
        /*var firebaseConfig = {
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
        */    
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
      /* 
         return new Promise(resolve => {
          setTimeout(() => {                          
            }
          }, 45000);
        */  
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
            document.getElementById('abreModal').click();
        }
        ///////////////////////////////////////////////////////////////////////        

        /////////////// INPUTS /////////////////////////////////////////////////////////////////////
        var nomeQuarto = document.getElementById('nomeQuarto');   
        var preco = document.getElementById('preco');
        var descricao = document.getElementById('descricao');     
        
        //var radiosAdd = document.getElementById('add');
        //pega conteúdo dos RADIOBUTTONS Adicionais
        var radiosAdd1 = document.getElementById('add1');
        var radiosAdd2 = document.getElementById('add2');
        var radiosAdd3 = document.getElementById('add3');
        var btnSubmit = document.getElementById('btnSubmit');
        
        var imgFoto1 = document.getElementById('imgFoto1');
        imgFoto1.addEventListener('click', function (){
            document.getElementById('foto1').click();
        });
        var imgFoto2 = document.getElementById('imgFoto2');
        imgFoto2.addEventListener('click', function (){
            document.getElementById('foto2').click();
        });
        var imgFoto3 = document.getElementById('imgFoto3');
        imgFoto3.addEventListener('click', function (){
            document.getElementById('foto3').click();
        });
       
        btnSubmit.addEventListener('click', function (){

            if( nomeQuarto.value.length <= 0 ){
                mostraModal("Preencha o campo NOME.",0);            
                return;
            }
            if( descricao.value.length <= 0 ){
                mostraModal("Preencha o campo DESCRIÇÃO.",0);            
                return;
            }
            if( preco.value.length <= 0 ){
                mostraModal("Preencha o campo PREÇO.",0);                
                return;
            }
            if( preco.value < 12 || preco.value > 30 ){
                mostraModal("O valor deve ser entre R$ 12,00 e R$ 30,00.",0);                
                return;
            }
            
            //var adicionais = [rdAr, rdWifi, rdTv];
            var adicionais = [];
            var rdAr = radiosAdd1.checked   ? adicionais.push(radiosAdd1.value) : '';
            var rdWifi = radiosAdd2.checked ? adicionais.push(radiosAdd2.value) : '';
            var rdTv = radiosAdd3.checked   ? adicionais.push(radiosAdd3.value) : '';  
                
            //verifica se nenhum foi selecionado
            /*
            var verificaRadio = false;
            for (var i = 0, length = radios.length; i < length; i++){
                if (radios[i].checked){
                    verificaRadio=true;
                    break;
                }
            }
            if( verificaRadio == false ){
                mostraModal("Escolha uma CLASSIFICAÇÃO para o quarto.",0);            
                return;
            }             
            */
            
            var arrayUpload = [document.getElementById("foto1").files[0],
                           document.getElementById("foto2").files[0],
                           document.getElementById("foto3").files[0]];
            ////////////////////////////////////////////////////////////////////////////////////
            //compara com oq vem da servlet
            //console.log('img1: ' + '${quarto.listaImagemFirebase[0]}');
            //console.log('img2: ' + '${quarto.listaImagemFirebase[1]}');
            //console.log('img3: ' + '${quarto.listaImagemFirebase[2]}');
            
            //nome das fotos que veem da servlet
            var fotosArray = ['${quarto.listaImagemFirebase[0]}',
                '${quarto.listaImagemFirebase[1]}','${quarto.listaImagemFirebase[2]}' ]; 
            //nomes para atualizar no firebase
            var fotoAtualizarUm = "";
            var fotoAtualizarDois = "";
            var fotoAtualizarTres = "";
            if( fotoUmAlterada ){
                alert('atualizar foto 1');
                fotoAtualizarUm = fotosArray[0];
            }
            if( fotoDoisAlterada ){
                alert('atualizar foto 2');
                fotoAtualizarDois = fotosArray[1];
            }
            if( fotoTresAlterada ){
                alert('atualizar foto 3');
                fotoAtualizarTres = fotosArray[2];
            }
        
            //console.log('atualiza1? ' + fotoAtualizarUm);
            //console.log('atualiza2? ' + fotoAtualizarDois);
            //console.log('atualiza3? ' + fotoAtualizarTres);

            var listaAdicionais = [];
            var qtd = parseInt('${fn:length(quarto.listaAdicionais)}');
            //alert(': '+'${quarto.listaAdicionais}');
            var a = '${quarto.listaAdicionais[0]}';
            var aa = '${quarto.listaAdicionais[0].id}';
            //alert('aqui: '+${quarto.listaAdicionais[0].id});
            //alert('aqui: '+${quarto.listaAdicionais[1].id});
            //alert('aqui: '+${quarto.listaAdicionais[2].id});
            
            //for(var k = 0; k < qtd; k++){
            //   console.log('id do add: '+k+'. -'+ '${quarto.listaAdicionais[k].id}'+'. ');
            //}       
            //if( fotoUmAlterada || fotoDoisAlterada || fotoTresAlterada ){
                secondFunction(fotoAtualizarUm, fotoAtualizarDois, fotoAtualizarTres, arrayUpload);
            //}
            resolverDepoisDe40Segundos(nomeQuarto.value, descricao.value, preco.value, adicionais);                
        });    
          
      function resolverDepoisDe40Segundos(nomeQuarto, descricao, preco, adicionais) {
        return new Promise(resolve => {
          setTimeout(() => {
              mostraModal("As imagens foram carregadas.","WAIT");                 
              console.log('printa qlq coisa depois de 45seg');
              
              //chama servlet
              document.getElementById("formAddQuarto").action="./QuartoServlet?action=edtForm";
              document.getElementById("formAddQuarto").method = "POST";
              document.getElementById("formAddQuarto").nomeQuartoServ.value = nomeQuarto;
              document.getElementById("formAddQuarto").descricaoServ.value = descricao;
              document.getElementById("formAddQuarto").precoServ.value = preco;
              document.getElementById("formAddQuarto").adicionaisServ.value = adicionais;
              document.getElementById("formAddQuarto").idQuartoServ.value = ${quarto.id};                          
              document.getElementById("formAddQuarto").submit();           
          }, 45000);
        });
      }
      
      async function secondFunction(fotoUmAlterada, fotoDoisAlterada, fotoTresAlterada, arrayUpload){
       mostraModal("A imagem está sendo carregada. Por favor aguarde.","WAIT");            
        await insereImagemFirebase(fotoUmAlterada, fotoDoisAlterada, fotoTresAlterada, arrayUpload);
        // now wait for firstFunction to finish...
        // do something else
      };
      
      async function insereImagemFirebase(fotoUmAlterada, fotoDoisAlterada, fotoTresAlterada, arrayUpload){
        /*var firebaseConfig = {
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
        */  
        if( fotoUmAlterada ){
            var desertRef = firebase.storage().ref('/roomImage/'+fotoUmAlterada);   
            // Delete the file
            desertRef.delete().then(function() {
              // File deleted successfully
               alert('terminou de apagar a imagem 1');
               //now insert:
                var storageRef0 = firebase.storage().ref('/roomImage/'+fotoUmAlterada);                       
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
            }).catch(function(error) {
              // Uh-oh, an error occurred!
            });
            
        }
        if( fotoDoisAlterada ){
            var desertRef = firebase.storage().ref('/roomImage/'+fotoDoisAlterada);   
                // Delete the file
                desertRef.delete().then(function() {
                // File deleted successfully
                //now insert:
                var storageRef0 = firebase.storage().ref('/roomImage/'+fotoDoisAlterada);                       
                var uploadTask0 = storageRef0.put(arrayUpload[1]);
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
            }).catch(function(error) {
              // Uh-oh, an error occurred!
            });
            
        }
        if( fotoTresAlterada ){
            var desertRef = firebase.storage().ref('/roomImage/'+fotoTresAlterada);   
                // Delete the file
                desertRef.delete().then(function() {
                // File deleted successfully
                //now insert:
                var storageRef0 = firebase.storage().ref('/roomImage/'+fotoTresAlterada);                       
                var uploadTask0 = storageRef0.put(arrayUpload[2]);
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
            }).catch(function(error) {
              // Uh-oh, an error occurred!
            });      
        }      
      };
      
        var BtnlimparCampos = document.getElementById('limparCampos');
        BtnlimparCampos.addEventListener('click', function (){
            var rad1 = document.getElementById('add1');
            var rad2 = document.getElementById('add2');
            var rad3 = document.getElementById('add3');
            rad1.checked=false;
            rad2.checked=false;
            rad3.checked=false;
        }); 
        
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
