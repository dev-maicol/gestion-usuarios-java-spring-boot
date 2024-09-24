// Call the dataTables jQuery plugin
$(document).ready(function() {
  // alert("Bienvenido");
});


async function registrarUsuario(){
    let datos = {}
    datos.nombres = document.getElementById('txtNombres').value
    datos.apellidos = document.getElementById('txtApellidos').value
    datos.email = document.getElementById('txtEmail').value
    datos.password = document.getElementById('txtPassword').value

    let repetirPassword = document.getElementById('txtRepetirPassword').value
    if(repetirPassword != datos.password){
        alert('No son iguales las contraseñas')
        return
    }
    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    alert('Usuario creado con exito')
    window.location.href = 'login.html'

}

