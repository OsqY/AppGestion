async function assignProject() {
    let projectInfo = {};
    projectInfo.projectName = document.getElementById("project-name").value;
    projectInfo.userForProject = document.getElementById("email").value;
    projectInfo.priority = document.getElementById("project-priority").value;
    projectInfo.startDate = document.getElementById("date-begin").value;
    projectInfo.endDate = document.getElementById("date-end").value;

    try {
        const response = await fetch("projects/create", {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify(projectInfo)
        });

        if (response.ok) {
            alert("Se ha creado el proyecto");
            window.location.reload();
        }
            const errorMessage = await response.text();
            alert(`Error al crear el proyecto: ${errorMessage}`);
    } catch (error) {
        console.error("Error de red:", error);
        alert("Error de red al intentar crear el proyecto");
    }
}
