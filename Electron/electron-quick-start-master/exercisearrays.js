let data = [
{name: "Nacho", telephone: "966112233", age: 40},
{name: "Ana", telephone: "911223344", age: 35},
{name: "Mario", phone: "611998877", age: 15},
{name: "Laura", telephone: "633663366", age: 17}
];

data.push({name: "Pedro", telephone: "611944444", age: 25});
data.push({name: "Julia", phone: "633232323", age: 37});

//sort by age
//data.sort((a, b) => a.age - b.age);

//sort by name
data.sort((a, b) => {
    var nameA = a.name.toUpperCase(); // ignore upper and lowercase
    var nameB = b.name.toUpperCase(); // ignore upper and lowercase
    if (nameA < nameB) {
      return -1;
    }
    if (nameA > nameB) {
      return 1;
    }
    return 0;
});


//older than 30
var array = new Array();
for(var j=0;j<data.length;j++){
    if(data[j].age > 30){
        array.push(data[j]);
    }
}

console.log(array);