var firebase = "";
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

var inputEmail = document.getElementById('email');
var inputPassword = document.getElementById('senha');
var btnLogin = document.getElementById('login');

btnLogin.addEventListener('click', function (){
    firebase.auth().signInWithEmailAndPassword(inputEmail.value, inputPassword.value).then(function (result){
        alert('conectado');
    }).catch(function(error) {        
        alert('nao conectado');
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        // ...
    });
});

/*
function uploadFile(){
    console.log('chegou aqui');
    var f1 = document.getElementById("foto1");
    console.log('chegou aqui'+f1.value);
    alert(f1.value);
    
    var filename = selectedFile.name;
    var storageRef = firebase.storage().ref('roomsImages/'+filename);

    var uploadTask = storageRef.put(selectedFile);
    
    // Register three observers:
    // 1. 'state_changed' observer, called any time the state changes
    // 2. Error observer, called on failure
    // 3. Completion observer, called on successful completion
    uploadTask.on('state_changed', function(snapshot){
      // Observe state change events such as progress, pause, and resume
      // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
      var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
      console.log('Upload is ' + progress + '% done');
      switch (snapshot.state) {
        case firebase.storage.TaskState.PAUSED: // or 'paused'
          console.log('Upload is paused');
          break;
        case firebase.storage.TaskState.RUNNING: // or 'running'
          console.log('Upload is running');
          break;
      }
    }, function(error) {
      // Handle unsuccessful uploads
    }, function() {
      // Handle successful uploads on complete
      // For instance, get the download URL: https://firebasestorage.googleapis.com/...
      uploadTask.snapshot.ref.getDownloadURL().then(function(downloadURL) {
        console.log('File available at', downloadURL);
      });
    });
}*/