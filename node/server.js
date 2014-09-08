var thrift = require('thrift');
var binaryService = require('./thrift/BinaryUpload.js');

var controller = {
  upload: function upload(request, result) {
    var buf = new Buffer(request.blob, 'binary').toString('hex');
    console.log(buf.length);
    var hexString = "";
    for (i = 0; i < buf.length / 2; i++) { hexString = hexString + buf.slice(2*i, 2*i+2) + " "; }
    console.log(hexString);
    result(null);
  },
}

var server = thrift.createServer(binaryService, controller, {
  transport : thrift.TFramedTransport,
  protocol  : thrift.TBinaryProtocol
});

var port = 9090;

console.log("Listening on port: " + port);
server.listen(port);
