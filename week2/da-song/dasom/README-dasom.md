## JAVASCRIPT 동작

- 싱글스레드인 이유

  - 웹에서 실행시키기 위해서 무거운 멀티 스레드 보다는 가벼운 싱글 스레드를 택하게 되었음
  - 싱글스레드 인 대신 async await 비동기를 통해서 여러 일을 같이 실행 시킬 수 있도록 하고 있음

- 인터프리터

  - 처음에는 인터프리터
  - v8 엔진에서 컴파일하는 과정을 추가하게 됨
    - -> 유사 인터프리터가 됨
    - 과정 : 토큰화 > AST > 컴파일

- 콜스택 / 힙 / 태스크 큐
  main.js

```js
var a = 1;
const b = 2;
let c = 3;

function plus(a, b) {
  return a + b;
}

function func() {
  return plus(1, 2);
}

const func2 = () => {
  console.log("a");
};

func();
```

- 콜스택 : func / plus
- web API: setTimeout / setInterval

- javascript 로 document 조작
- React를 뺀 frontend
