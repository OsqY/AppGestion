document.addEventListener("DOMContentLoaded", function () {
    const urlParams = new URLSearchParams(window.location.search);
    const projectId = urlParams.get("projectId");

    if (projectId) {
        $(document).ready(function () {
            loadProjectToEdit();
        });
    }

    function loadProjectToEdit() {
        try {
            const projectToEdit = localStorage.getItem('projectToEdit');
            if (projectToEdit) {
                const project = JSON.parse(projectToEdit);
                document.getElementById("createProject-title").textContent = "Editar Proyecto";
                const projectBtn = document.getElementById("createProject-btn");
                projectBtn.textContent = "Editar";
                projectBtn.removeEventListener("click", createProject);
                projectBtn.addEventListener("click", updateProject);
                if (project.origin === "projectPlace") {
                    document.getElementById("emailTxt").value = project.userForProject;
                    document.getElementById("project-name").value = project.projectName;
                    document.getElementById("project-description").value = project.description;
                    document.getElementById("project-priority").value = project.priority;
                    document.getElementById("date-begin").value = project.startDate;
                    document.getElementById("date-end").value = project.endDate;
                } else {
                    new Error("El proyecto no proviene del sitio correcto.")
                }
            }
        } catch (e) {
            console.error(e);
        }
    }

    function updateProject() {
        const projectToEdit = localStorage.getItem("projectToEdit");
        if (projectToEdit) {
            try {
                const project = JSON.parse(projectToEdit);

                const userEmail = document.getElementById("emailTxt").value;
                const projectName = document.getElementById("project-name").value;
                const priority = document.getElementById("project-priority").value;
                const startDate = document.getElementById("date-begin").value;
                const endDate = document.getElementById("date-end").value;

                project.userForProject = userEmail;
                project.projectName = projectName;
                project.priority = priority;
                project.startDate = startDate;
                project.endDate = endDate;
                project.status = projectToEdit.status;

                fetch("api/projects/" + project.id, {
                    method: "PUT",
                    headers: {
                        "Accept": "application/json",
                        "Content-Type": "application/json",
                        "Authorization": localStorage.token
                    },
                    body: JSON.stringify(project)
                })
                    .then(response => {
                        if (response.status >= 200 && response.status < 300) {
                            alert("Proyecto actualizado con éxito.");
                            location.reload();
                            document.getElementById("emailTxt").value = "";
                            document.getElementById("project-name").value = "";
                        } else {
                            throw new Error("Ocurrió un error al intentar actualizar el proyecto: " + response.status);
                        }
                    }).catch(error => {
                    throw new Error("Ocurrió un error al intentar actualizar el proyecto: " + error);
                });

            } catch (e) {
                console.error(e);
            }
        }
    }

})
