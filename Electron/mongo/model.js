const mongo = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/mongo";

function insert(a){
    mongo.connect(url, function (err, db) {
        if (err) throw err;        
        dbo = db.db("mongo");        
        dbo.collection("Estudiantes").insertOne(a, function(err, obj) {
            if (err) throw err;
            db.close();
        });
    });
}

function borrar(a){
    mongo.connect(url, function (err, db) {
        if (err) throw err;        
        dbo = db.db("mongo");         
        dbo.collection("Estudiantes").deleteOne(a, function(err, obj) {
            if (err) throw err;
            db.close();
        })
    });
}



function selectAll(){
    mongo.connect(url, function (err, db) {
        if (err) throw err;        
        dbo = db.db("mongo");   
        dbo.collection("Estudiantes").find({}).toArray(function(err, result) {
            if (err) throw err;
            alert(JSON.stringify(result))  
            db.close();
        });
    })
}





module.exports = {
    "insert" : insert,
    "borrar" : borrar,
    "selectAll" : selectAll
}