<<<<<<< HEAD
<<<<<<< HEAD
document.querySelector(".btn.btn-primary.btn-user.btn-block").addEventListener("click", registerUser);
=======
=======
>>>>>>> a04e22074c94280ec32b52ab5809b01714c48872
const registerBtn = document.getElementById("register-btn");
registerBtn.addEventListener("click", registerUser);

async function registerUser() {
    let userInfo = {};
    userInfo.userName = document.getElementById("nombreTxt").value;
    userInfo.userLastName = document.getElementById("apellidoTxt").value;
    userInfo.email = document.getElementById("correoTxt").value;
    userInfo.password = document.getElementById("contraseniaTxt").value;
    let repeatPassword = document.getElementById("repetirContraseniaTxt").value;
<<<<<<< HEAD
>>>>>>> a04e22074c94280ec32b52ab5809b01714c48872
=======
>>>>>>> a04e22074c94280ec32b52ab5809b01714c48872

    if (repeatPassword !== userInfo.password) {
        alert("La contraseña es incorrecta");
        return;
    }

<<<<<<< HEAD
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
=======
>>>>>>> a04e22074c94280ec32b52ab5809b01714c48872
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
<<<<<<< HEAD
>>>>>>> a04e22074c94280ec32b52ab5809b01714c48872
=======
>>>>>>> a04e22074c94280ec32b52ab5809b01714c48872
}

