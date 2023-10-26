const loginBtn = document.getElementById('login-btn');
loginBtn.addEventListener("click", login);

async function login() {
        let userInfo = {};
        userInfo.username = document.getElementById("emailTxt").value;
        userInfo.password = document.getElementById("passwordTxt").value;

        const request = await fetch("/api/auth/login", {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify(userInfo)
        });

        const response = await request.json();
        const token = response.accessToken;
        localStorage.setItem('accessToken', token);
    const authRequest = await fetch("/api/protected", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        })
    localStorage.getItem('accessToken')
    if (authRequest.ok) {
        alert("Login successful");
        window.location.href="user/index";
    }
    else {
        alert("Error");
        window.location.reload();
    }
}

