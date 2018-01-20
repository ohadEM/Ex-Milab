const express = require('express');
const app = express();
const server = require('http').createServer(app);
const io = require('socket.io')(server);
const alpha = require('alphavantage')({ key: 'FZBAB70O5ZP0F1JH'});

var res;

io.on('connect', function (socket) {
  socket.on('quotes', function (data) {
    console.log("Checking the currency " + data);
  });

  var rate = setInterval( () => {
    if (!socket) {
      console.log("Socket problem");
      return;
    }
    if (!currency) {
      console.log("Currency not found");
      socket.emit("Please enter new currency name");
      return;
    }

    alpha.forex.rate(coin, 'usd').then(data => {
      res = Object.values(data['Realtime Currency Exchange Rate'])[4];
      socket.emit('rate', res);
    });
  }, 1500);

  socket.on('disconnect', () => {
    clearInterval(rate);
  });
});

app.listen(port, function() {
  console.log("Listening on http://localhost:" + port);
});
