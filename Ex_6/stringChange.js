const strArgs = (process.argv.slice(2));
var arr = strArgs;

for (var i = strArgs.length; i > 0; i--){
  alert(arr.join("").toString());
  unshift(arr.pop());
}
