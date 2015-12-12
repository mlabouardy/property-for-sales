var express=require('express');
var bodyParser=require('body-parser');
var multer = require('multer');
var crypto=require('crypto');
var mime=require('mime');


var storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, './uploads')
  },
  filename: function (req, file, cb) {
    crypto.pseudoRandomBytes(16, function (err, raw) {
      cb(null, raw.toString('hex') + Date.now() + '.' + mime.extension(file.mimetype));
    });
  }
});

var upload = multer({ storage: storage });

var app=express();
var port=3000;

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true})); 

// CORS Support
app.use(function(req, res, next) {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');
  res.header('Access-Control-Allow-Headers', 'Content-Type, Authorization');
  next();
});

app.use(express.static('uploads'));


app.get('/',function(req,res){
	console.log('Hello');
	res.send('Test');
});

app.post('/upload',upload.single('file'),function(req,res){
	var response={
		filename:req.file.filename,
		success:true
	}
	res.send(response);
});

app.listen(port, function(){
	console.log('Working on port '+port);
})