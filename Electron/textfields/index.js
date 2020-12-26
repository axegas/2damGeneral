let text = document.getElementById("text");

let button = document.getElementById("button");
button.addEventListener('click', ()=>{
    alert(text.value);
});

let sample = document.getElementById("sample")


// text field event
/*
text.addEventListener('click', ()=>{
    alert("Click in text field!");
});*/

text.addEventListener('keyup', (event) => {
    if (event.code == 'Enter') {
        //alert("Enter!");
        a = text.value;

        sample.innerHTML=a;
    }
});