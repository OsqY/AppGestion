// Call the dataTables jQuery plugin
$(document).ready(function() {
    loadUsers();
    $('#usersTable').DataTable();
});
async function loadUsers() {
    try {
        const request = await fetch("api/users", {
            method: "GET",
            headers: getHeaders()
        });

        const users = await request.json();

        if (request.status >= 200 && request.status < 300) {
            let htmlList = '';
            for (let user of users) {
                let btnDelete = '<a href="#" onclick="deleteUser(' + user.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

                let txtPhone = user.phoneNumber == null ? '-' : user.phoneNumber;
                let htmlUser = '<tr><td>' + user.id + '</td><td>' + user.userName + ' ' + user.userLastName + '</td><td>' +
                    user.email + '</td><td>' + txtPhone +
                    '</td><td>' + btnDelete + '</td></tr>';
                htmlList += htmlUser;
            }
            document.querySelector('#usersTable tbody').outerHTML = htmlList;
        }
            Error("La solicitud no se completó correctamente. Estado: " + request.status);

    } catch (error) {
        console.error("Error al cargar usuarios:", error);
    }
}

function getHeaders() {
    return {
        "Accept": "application/json",
        "Content-Type": "application/json",
        "Authorization": localStorage.token
    };
}

async function deleteUser(id) {
    if (!confirm("¿Desea eliminar al usuario?")) {
        return;
    }

    try {
        const request = await fetch("api/users/" + id, {
            method: "DELETE",
            headers: getHeaders()
        });

        if (request.status >= 200 && request.status < 300) {
            location.reload();
        }
            Error("La solicitud de eliminación no se completó correctamente. Estado: " + request.status);
    } catch (error) {
        console.error("Error al eliminar usuario:", error);
    }
}
