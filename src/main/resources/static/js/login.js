// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function login(){
let userInfo = {};
userInfo.email = document.getElementById("correoTxt").value;
userInfo.password = document.getElementById("contraseniaTxt").value;


const request = await fetch("api/login", {
                                         method: "POST",
                                         headers: {
                                         "Accept": "application/json",
                                         "Content-Type": "application/json"},
                                         body: JSON.stringify(userInfo)});
                                         const answer = await request.text();
                                         if (answer != "FAIL") {
                                           localStorage.token = answer;
                                           localStorage.email = userInfo.email;
                                           window.location.href = "users.html";
                                         } else {

                                           alert("Las  credenciales son incorrectas. Por favor intente nuevamente.");
                                         }
}

