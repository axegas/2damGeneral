console.log(`Diagonal con N=20`);
var n= 20;
var s = "";
for(var j=0;j<n;j++){
    for(var i=0;i<n;i++){     
        s += j == i ? "*" : " "; 
    }
    console.log(s);
    s = "";
}