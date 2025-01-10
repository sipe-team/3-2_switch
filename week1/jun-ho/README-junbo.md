### 1번 문제

```jsx
var fs = require('fs');
var input = fs.readFileSync('/dev/stdin').toString().split(' ');
var a = parseInt(input[0]);
var b = parseInt(input[1]);

console.log(a+b);
console.log(a-b);
console.log(a*b);
console.log(Math.floor(a/b));
console.log(a%b);
```

### 2번 문제

```jsx
var fs = require('fs');
var input = fs.readFileSync('/dev/stdin').toString().split(' ');
var a = parseInt(input[0]);
var b = parseInt(input[1]);
var c;

if(a-b > 0) {
	c = '>';
} else if (a-b < 0) {
	c = '<';
} else {
	c = '==';
}

console.log(c);
```

### 3번 문제

```jsx
var fs = require('fs');
var input = fs.readFileSync('/dev/stdin').toString().split(' ');
var a = parseInt(input[0]);

for(var i = 1; i <= a; i++) {
	var str = '*';
	console.log(str.repeat(i));
}
```

### 4번 문제

```jsx
var fs = require('fs');
var input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
var [N, X] = input[0].split(' ').map(Number);
var A = input[1].split(' ').map(Number);
var result = A.filter(num => num < X);
console.log(result.join(' '));
```

### 5번 문제

```jsx
var fs = require('fs');
var input = fs.readFileSync('/dev/stdin').toString().trim();

if (input === '') {
  console.log(0);
} else {
  var words = input.split(' ');
  console.log(words.length);
}
```