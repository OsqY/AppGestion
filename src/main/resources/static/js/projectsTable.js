// Call the dataTables jQuery plugin
$(document).ready(function() {
    loadProjects();
    $('#projectsTable').DataTable();
});
async function loadProjects() {
    try {
        const request = await fetch("projects/", {
            method: "GET",
            headers: getHeaders()
        });

        const projects = await request.json();

        if (request.status >= 200 && request.status < 300) {
            let htmlList = '';
            for (let project of projects) {
                let btnDelete = '<a href="#" onclick="deleteProject(' + project.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

                let startDate = project.startDate;
                let endDate = project.endDate;
                if (startDate == null){
                    startDate = "No definido";
                }
                if (endDate == null){
                    endDate = "No definido";
                }

                let htmlProject = '<tr><td>' + project.projectName + '</td><td>' + project.userForProject + '</td><td>' +
                    startDate + '</td><td>' + endDate + '</td><td>' + project.priority +
                    '</td><td>' + btnDelete + '</td></tr>';
                htmlList += htmlProject;
            }
            document.querySelector('#projectsTable tbody').outerHTML = htmlList;
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

async function deleteProject(id) {
    if (!confirm("¿Desea eliminar el proyecto?")) {
        return;
    }

    try {
        const request = await fetch("projects/" + id, {
            method: "DELETE",
            headers: getHeaders()
        });

        if (request.status >= 200 && request.status < 300) {
            location.reload();
        }
        Error("La solicitud de eliminación no se completó correctamente. Estado: " + request.status);
    } catch (error) {
        console.error("Error al eliminar el proyecto:", error);
    }
}
