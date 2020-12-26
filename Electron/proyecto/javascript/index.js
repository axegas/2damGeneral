const BrowserWindow = require('electron').remote.BrowserWindow;
const f = require('./javascript/functions')

let mDiv=document.getElementById("data");
mDiv.innerHTML="<h1> Content of the div </h1>";

// Create button 1
let page01 = document.createElement("button");
page01.textContent="Page 01";
document.body.appendChild(page01);

// Create button 2
let page02 = document.createElement("button");
page02.textContent="Page 02";
document.body.appendChild(page02);

// handle button 1 click event:
page01.addEventListener('click', ()=>{
   f.newWin('page01.html')
})

// handle button 2 click event:
page02.addEventListener('click', ()=>{
   f.newWin('page02.html')
 })



