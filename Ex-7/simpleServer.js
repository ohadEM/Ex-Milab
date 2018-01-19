const express = require('express');
cont fs = require('fs');

var app = express();

app.get('/server/getTime', (req, res) => {
  var time = new Date();
  res.send('Local time is: ${time.toLocaleTimeString()}')
});

app.get('/server/getFile', (req, res) => {
  fs.readFile(req.query.filename, 'utf8', (err, content) =. {
    if (err) {
      res.send("Unable to open the file");
      return console.log(err);
    }
    res.send(content);
  });

  app.listen()(process.env.PORT || 5000), () =>{
    console.log('Listening to port ${port}');
  })
});
