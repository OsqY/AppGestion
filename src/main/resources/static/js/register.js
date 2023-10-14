<<<<<<< HEAD
document.querySelector(".btn.btn-primary.btn-user.btn-block").addEventListener("click", registerUser);
=======
const registerBtn = document.getElementById("register-btn");
registerBtn.addEventListener("click", registerUser);

async function registerUser() {
    let userInfo = {};
    userInfo.userName = document.getElementById("nombreTxt").value;
    userInfo.userLastName = document.getElementById("apellidoTxt").value;
    userInfo.email = document.getElementById("correoTxt").value;
    userInfo.password = document.getElementById("contraseniaTxt").value;
    let repeatPassword = document.getElementById("repetirContraseniaTxt").value;
>>>>>>> a04e22074c94280ec32b52ab5809b01714c48872

    if (repeatPassword !== userInfo.password) {
        alert("La contraseña es incorrecta");
        return;
    }

<<<<<<< HEAD
if (repeatPassword !== userInfo.password){
alert("La contraseña es incorrecta");
return;
}

const request = await fetch("api/user", {
                                         method: "POST",
                                         headers: {
                                         "Accept": "application/json",
                                         "Content-Type": "application/json"},
                                         body: JSON.stringify(userInfo)});
                                         alert("La cuenta fue creada con éxito.");
                                         window.location.href = "login";
=======
    await fetch("api/user", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userInfo)
    });
    alert("La cuenta fue creada con éxito.");
    window.location.href = "login.html";
>>>>>>> a04e22074c94280ec32b52ab5809b01714c48872
}

