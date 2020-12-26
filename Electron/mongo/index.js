const modelo = require('./model')

let nombre = document.getElementById("nombre")
let apellidos = document.getElementById("apellidos");
let edad = document.getElementById("edad")

let insertar = document.getElementById("insertar")
let eliminar = document.getElementById("eliminar")
let mostrar = document.getElementById("mostrar")

insertar.addEventListener('click',()=>{
    var a = {
        nombre: nombre.value,
        apellidos: apellidos.value,
        edad: edad.value
    }
    modelo.insert(a)
});

eliminar.addEventListener('click',()=>{
    var a = {
        nombre: nombre.value,
        apellidos: apellidos.value,
        edad: edad.value
    }
    modelo.borrar(a)
});

mostrar.addEventListener('click',()=>{    
    modelo.selectAll();
});