const projectBtn = document.getElementById("createProject-btn");
projectBtn.addEventListener("click", createProject);

async function createProject() {
    let projectInfo = {};
    
    projectInfo.projectName = document.getElementById("project-name").value;
    projectInfo.userForProject = document.getElementById("emailTxt").value;
    projectInfo.priority = document.getElementById("project-priority").value;
    projectInfo.startDate = document.getElementById("date-begin").value;
    projectInfo.endDate = document.getElementById("date-end").value;
    projectInfo.status = 0;

    try {
        const response = await fetch("api/projects/create", {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": localStorage.token
            },
            body: JSON.stringify(projectInfo)
        });

        if (response.ok) {
            alert("Se ha creado el proyecto");
            window.location.reload();
        } else {
            const errorMessage = await response.text();
            alert(`Error al crear el proyecto: ${errorMessage}`);
        }
    } catch (error) {
        console.error("Error de red:", error);
        alert("Error de red al intentar crear el proyecto");
    }
}
