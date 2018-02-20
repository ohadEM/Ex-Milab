const express = require('express');
const app = express();
const server = require('http').createServer(app);
const io = require('socket.io')(server);
const alpha = require('alphavantage')({key: 'FZBAB70O5ZP0F1JH'})

let port = process.env.PORT || 5000;

server.listen(port, function () {
	console.log('Server listening at port %d', port);
});

// Chatroom

var res;

io.on('connect', function (socket) {
	socket.on('quotes', function (data) {
    currency = data;
		console.log("Checking for coin" + currency)
	});

  let postRate = setInterval(() => {
    if (!socket) {
      console.log('Socket Error');
      return
    }
    if (!currency) {
      console.log('Currency Error');
      socket.emit("Enter currency name");
      return;
    }
	   alpha.forex.rate(currency, 'usd').then(data => {
       res = Object.values(data['The last price of the currency'])[4];
       socket.emit('postRate', res);
     });
   }, 15000);

	// when the user disconnects.. perform this
	socket.on('disconnect',() => {
		clearInterval(postRate);
	});
});
