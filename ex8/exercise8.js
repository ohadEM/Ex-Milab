var express = require('express');
var app = express();

app.set('port', 3000);
app.use("/files", express.static(__dirname + '/files'));
var server = app.listen(app.get('port'), function() {
  var port = server.address().port;
})
