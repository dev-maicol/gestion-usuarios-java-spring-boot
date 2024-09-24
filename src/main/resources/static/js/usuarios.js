// Call the dataTables jQuery plugin
$(document).ready(function() {
  // alert("Bienvenido");
  cargarUsuarios();
  $('#usuarios').DataTable();

  actualizarEmailUsuario()
});

function actualizarEmailUsuario(){
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email
}

async function cargarUsuarios(){
    const request = await fetch('api/usuarios', {
        method: 'GET',
        headers:getHeaders()
    });

    const usuarios = await request.json();
    let usuariosHTML = ''

    for(let usuario of usuarios){
        let botonEliminar = '<a href="#" onclick="eliminarUsuario('+ usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'
        usuariosHTML += '<tr><td>'+usuario.id+'</td><td>'+ usuario.nombres + ' '+ usuario.apellidos+'</td><td>'+ usuario.email+'</td><td>'+ (usuario.celular == null ? '-':usuario.celular)+'</td><td>'+botonEliminar+'</td></tr>'
    }

    console.log(usuarios);

    document.querySelector('#usuarios tbody').outerHTML = usuariosHTML


}

function getHeaders(){
    return {
    'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'Authorization': localStorage.token
    }
}
async function eliminarUsuario(id){
    if(confirm('¿Esta seguro de eliminar el usuario?')){
        const request = await fetch('api/usuario/' + id, {
                method: 'DELETE',
                headers: getHeaders()
            });
        cargarUsuarios()
    }



}