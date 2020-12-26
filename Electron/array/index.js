const BrowserWindow = require('electron').remote.BrowserWindow;
const f = require('./javascript/functions')
let Name = ["Name 1", "Name 2", "Name 3","Name 4"];

let people = [
   {
      Name: "Nacho",
      telephone: "966112233",
      age: 40
   },
   {
      Name: "Ana",
      telephone: "911223344",
      age: 35
   },
   {
      Name: "Mario",
      telephone: "611998877", 
      age: 15
   },
   {
      Name: "Laura",
      telephone: "633663366",
      age: 17
   }
];



/*
var s = "";
for(i=0;i<4;i++){
   s += Name[i] + "<br>";
}
*/

var s = "<table border='1'><tr><td>Nombre</td><td>Teléfono</td><td>Edad</td></tr>";
for(i=0;i<people.length;i++){
   s += "<tr><td>" + people[i].Name + "</td><td>" + people[i].telephone + "</td><td>" + people[i].age + "</td></tr>"
}
s += "</table>"

let mDiv=document.getElementById("data");


// Create button 1
let page01 = document.createElement("button");
page01.textContent="Open Window";
document.body.appendChild(page01);


// handle button 1 click event:
page01.addEventListener('click', ()=>{
  mDiv.innerHTML=s;
})

