let data = [
    {name: "Nacho", phone: "966112233", age: 40},
    {name: "Ana", phone: "911223344", age: 35},
    {name: "Mario", phone: "611998877", age: 15},
    {name: "Laura", phone: "633663366", age: 17}
];
newPerson ({name: "Rodolfo", phone: "910011001", age: 20});
newPerson ({name: "Juan", phone: "965661564", age: 60});
deletePerson ("910011001");

/*
function newPerson(person){
    aux = data.filter (p => p.phone === person.phone);
    if(aux.length==0){
        data.push(person);
    }
}

function deletePerson(phone){
    data = data.filter (p => p.phone != phone);
}
*/
let person = {name: "Rodolfo", phone: "910011001", age: 20};


let newPerson = new Promise ((resolve, reject) => {
    aux = data.filter (p => p.phone === person.phone);
    if(aux.length==0){
        data.push(person);
        resolve (person);
    }else
        reject ("Error: phone already exists");
});

console.log (data);