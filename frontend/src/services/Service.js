import axios from 'axios';


export const Service ={

    getAll,
    add,
    getAllP,
    deleteP,
    addP,
    getProyectoEmpleado,
    deleteById,
    addProyectoEmpleado,
    borrarAsig


}
const url= "http://localhost:8080/";

    function getAll(){
        return axios.get(url + "empleados");
    }
    function deleteById(id_empleado){
        return axios.get(url + `baja/${id_empleado}`);
    }

    function add(data)  
    {
        return axios.post(url + "empleado/add", data);
    }

    function getAllP(){
        return axios.get(url + "proyectosbaja");
        
    }

    function deleteP(id_proyecto){
        return axios.get(url + `bajaP/${id_proyecto}`);
    }
    function addP(data){
        return axios.post(url + "proyecto/add", data);
    }
    
    function addProyectoEmpleado(data){
        return axios.post(url + "saveproyectoempleado", data);
    }

    function getProyectoEmpleado(){
        return axios.get(url + "proyectosempleados");
        
    }

    function borrarAsig(pk){
        return axios.post(url + "deleteproyectoempleado",pk);
    }



