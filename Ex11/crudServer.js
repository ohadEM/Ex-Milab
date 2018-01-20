const express = require('express');
const app = express();
const router = express.Router();
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const instantMongoCrud = require('express-mongo-crud');
const MongoClient = require('mongodb').MongoClient;
const MONGO_URL = 'mongodb://ohadEM:tuvsxxx22@ds211088.mlab.com:11088/music';
var PORT = 5000;
mongoose.connect(MONGO_URL, { useMongoClient: true });

app.use(bodyParser.json());
var options = {
    host: `localhost:${PORT}`
}
app.use(instantMongoCrud(options));

router.get('/myapi', function(req, res){
    res.send('works well');
});

app.use(router);

app.listen(PORT, ()=>{
    console.log('started at '+PORT);
})
