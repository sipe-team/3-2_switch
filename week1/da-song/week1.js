// 왜 Node JS 에서는 import 가 안되죠?
// CommonJS (require) / ECMA Script Module (import / export)

var fs = require("fs");
var inputString = fs.readFileSync("/dev/stdin").toString().split("\n")[0];
var [a, b] = inputString.split(" ").map((x) => Number(x));
console.log(a + b);
