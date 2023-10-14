<<<<<<< HEAD
document.querySelector(".btn.btn-primary.btn-user.btn-block").addEventListener("click", login);
=======

const loginBtn = document.getElementById('login-btn');
loginBtn.addEventListener("click", login);

>>>>>>> a04e22074c94280ec32b52ab5809b01714c48872
async function login(){
let userInfo = {};
userInfo.email = document.getElementById("emailTxt").value;
userInfo.password = document.getElementById("passwordTxt").value;


const request = await fetch("api/login", {
                                         method: "POST",
                                         headers: {
                                         "Accept": "application/json",
                                         "Content-Type": "application/json"},
                                         body: JSON.stringify(userInfo)});
                                         const answer = await request.text();
                                         if (answer !== "FAIL") {
                                           localStorage.token = answer;
                                           localStorage.email = userInfo.email;
                                           window.location.href = "index";
                                         } else {
                                           alert("Las credenciales son incorrectas. Por favor intente nuevamente.");
                                         }
}

