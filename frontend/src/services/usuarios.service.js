import { AXIOS } from '@/plugins/axios'

export const usuariosService = {
   
    createUsuario,
}

function createUsuario (usuario) {

    return AXIOS({
        method: 'post',
        url: '/api/usuarios',
        data: JSON.stringify({
            nombre: usuario.nombre, 
            apellido: usuario.apellido,
            email: usuario.email, }),
        headers: {
            'Content-Type': 'application/json',
            Authorization: sessionStorage.getItem('token')
        }
    })
}