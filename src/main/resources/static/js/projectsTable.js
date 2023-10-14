<<<<<<< HEAD
$(document).ready(function() {
=======
$(document).ready(function () {
>>>>>>> a04e22074c94280ec32b52ab5809b01714c48872
    loadProjects();
    $('#projectsTable').DataTable();
});

function getHeaders() {
    return {
        "Accept": "application/json",
        "Content-Type": "application/json",
        "Authorization": localStorage.token
    };
}

async function loadProjects() {
    try {
        const request = await fetch("api/projects", {
            method: "GET",
            headers: getHeaders()
        });

        const projects = await request.json();

        if (request.status >= 200 && request.status < 300) {
            let htmlList = '';
            for (let project of projects) {
                let deleteBtn = '<a href="#" onclick="deleteProject(' + project.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
                let editBtn = '<a href="createproject.html?projectId=' + project.id.toString() + '" onclick="editProject(' + project.id + ')" class="btn btn-primary btn-circle btn-sm"><i class="fas fa-edit"></i></a>';
                let inProgressBtn = '<a href="#" onclick="editProjectStatus(\'en_progreso\', ' + project.id + ') " class="btn btn-info btn-circle btn-sm"><i class="fas fa-edit"></i></a>';
                let checkBtn = '<a href="#" onclick="editProjectStatus(\'completado\', ' + project.id + ') " class="btn btn-success btn-circle btn-sm"><i class="fas fa-check"></i></a>';

                let startDate = project.startDate;
                let endDate = project.endDate;
                if (startDate == null) {
                    startDate = "No definido";
                }
                if (endDate == null) {
                    endDate = "No definido";
                }

                let htmlProject = '<tr><td>' + project.projectName + '</td><td>' + project.userForProject + '</td><td>' +
                    startDate + '</td><td>' + endDate + '</td><td>' + project.status + '</td><td>' + project.priority +
                    '</td><td>' + deleteBtn + ' ' + editBtn + ' ' + inProgressBtn + ' ' + checkBtn + '</td></tr>';
                htmlList += htmlProject;
            }
            document.querySelector('#projectsTable tbody').outerHTML = htmlList;
        }
        Error("La solicitud no se completó correctamente. Estado: " + request.status);
    } catch (error) {
        console.error("Error al cargar usuarios:", error);
    }
}

async function deleteProject(id) {
    if (!confirm("¿Desea eliminar el proyecto?")) {
        return;
    }

    try {
        const request = await fetch("api/projects/" + id, {
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

async function editProject(id) {
    if (!confirm("¿Desea editar el proyecto?")) {

    } else {
        try {
            const request = await fetch("projects/" + id, {
                method: "GET",
                headers: getHeaders()
            });
            if (request.status >= 200 && request.status < 300) {
                const project = await request.json();
                project.origin = "projectPlace"
                localStorage.setItem("projectToEdit", JSON.stringify(project));
            }
            Error("La solicitud de edición no se completó correctamente. Estado: " + request.status);
        } catch (error) {
            console.error("Error al editar el proyecto:", error);
        }
    }

}

async function editProjectStatus(newStatus, id) {
    if (!confirm(`¿Desea establecer el proyecto como ${newStatus}?`)) {
        return;
    }

    try {
        const request = await fetch("projects/" + id, {
            method: "GET",
            headers: getHeaders()
        });

        if (request.status >= 200 && request.status < 300) {
            const project = await request.json();
            project.origin = "projectPlace";
            localStorage.setItem("project_status", JSON.stringify(project));
            setProjectStatus(newStatus);
        } else {
            new Error("La solicitud de edición no se completó correctamente. Estado: " + request.status);
        }
    } catch (error) {
        console.error("Error al editar el proyecto:", error);
    }
}

function setProjectStatus(newStatus) {
    const projectToEdit = localStorage.getItem("project_status");

    if (projectToEdit) {
        try {
            const project = JSON.parse(projectToEdit);

            if (project.origin === "projectPlace") {
                fetch("projects/" + newStatus + "/" + project.id, {
                    method: "PATCH",
                    headers: getHeaders(),
                    body: JSON.stringify(project)
                })
                    .then(response => {
                        if (response.status >= 200 && response.status < 300) {
                            alert(`Proyecto actualizado a ${newStatus.toUpperCase()}.`)
                            location.reload();
                        } else {
                            throw new Error("Ocurrió un error al intentar actualizar el proyecto: " + response.status);
                        }
                    }).catch(error => {
                    throw new Error("Ocurrió un error al intentar actualizar el proyecto: " + error);
                })
            }
        } catch (e) {
            console.error(e);
        }
    }
}
