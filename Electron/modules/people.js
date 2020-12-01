

// Arrow function para añadir personas, siempre que no exista ya su teléfono
let Add = (persona,datos) => {
    return new Promise((resolve, reject) => {
        let existe = datos.filter(pers => pers.telefono === persona.telefono);
        if (existe.length == 0) {
            datos.push(persona);
            resolve(persona);
        } else {
            reject("Error: el teléfono ya existe");
        }
    
    });
}

// Arrow function para borrar una persona por su teléfono
let Subtract = (telefono,datos) => {
    return new Promise((resolve, reject) => {
        let existePersona = datos.filter(persona => persona.telefono === telefono);
        if (existePersona.length > 0) {
            datos = datos.filter(persona => persona.telefono != telefono);
            resolve(existePersona[0]);
        } else {
            reject("Error: no se han encontrado coincidencias");
        }
    });
};

module.exports = {
    Add: Add,
    Subtract: Subtract
};