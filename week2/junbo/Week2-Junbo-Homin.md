**kotlin 2주차.md**

### 1. `val` vs `var`
- `val`은 재할당이 불가능(Immutable)한 읽기 전용 변수  
- `var`는 재할당 가능(Mutable)한 변수  
- 불변성을 보장해야 하는 값(예: 고정된 리스트 등)은 `val`, 값이 바뀔 수 있는 상황이면 `var` 사용

```kotlin
val readOnlyValue = 10    // 재할당 불가
var mutableValue = 20    // 재할당 가능
```

### 2. 배열(Array)과 컬렉션(Collection)
- **배열**: `Array`, `arrayOf()` 등으로 생성  
- **컬렉션**: `List`, `Set`, `Map`  
  - `listOf()`, `setOf()`, `mapOf()` 는 **불변** 컬렉션
  - `mutableListOf()`, `mutableSetOf()`, `mutableMapOf()` 는 **가변** 컬렉션

```kotlin
// 배열
val numbers = arrayOf(1, 2, 3)

// 불변 리스트
val immutableList = listOf("A", "B", "C")

// 가변 리스트
val mutableList = mutableListOf<Int>()
mutableList.add(100)
```

#### 불변(Mutable vs. Immutable) 유의 사항
- `val`과 가변 컬렉션을 함께 쓰면 컬렉션 내부 요소는 변경 가능  
- `var`와 불변 컬렉션을 함께 쓰면 컬렉션 자체를 다른 객체로 교체 가능  

---

### 3. 반복문과 범위(Range)
- `for (i in 0..n)`: 0부터 n까지 포함
- `for (i in 0 until n)`: 0부터 n 전까지 (미만)
- `downTo`: 역순 반복
- 확장 함수 예: `forEachIndexed { index, value -> ... }`

```kotlin
val arr = arrayOf("Kotlin", "Java", "JavaScript")

for (i in arr.indices) {
    println("$i 번째: ${arr[i]}")
}

arr.forEachIndexed { index, value ->
    println("$index 번째: $value")
}
```

---

### 4. 스코프 함수 (Scope Function)
- `let`, `run`, `with`, `apply`, `also`
- **공통 특징**: 람다 블록 안에서 객체에 접근할 수 있게 하며, 블록의 결과값 혹은 객체 자체를 반환
- 자주 쓰는 형태
  - `let`: `it`을 사용, 블록의 결과를 반환
  - `run`: `this`를 사용, 블록의 결과를 반환
  - `apply`: `this`를 사용, **자기 자신**(객체) 반환
  - `also`: `it`을 사용, **자기 자신**(객체) 반환

```kotlin
// let - 결과 반환
val str: String? = "Hello"
val length = str?.let {
    println("문자열 길이: ${it.length}")
    it.length
} ?: 0

// apply - 자기 자신 반환
val list = mutableListOf<Int>().apply {
    add(1)
    add(2)
    add(3)
}
// list 그대로 반환
```

---

### 5. 널 안정성(Null-Safety)
- `?`를 이용한 **널레퍼런스 방지**  
- `?.`: 세이프 콜(Safe Call)
- `?:`: 엘비스 연산자(기본값 제공)

```kotlin
val str: String? = null
println(str?.length) // null이면 그냥 null
println(str?.length ?: -1) // null이면 -1
```

---

### 6. 클래스와 객체
- `object`: **싱글톤 패턴** 용도로 사용
- `data class`: 값 객체(불변), `toString`, `equals` 등 자동 생성
- `sealed class`: 상속받은 자식 클래스의 종류를 **한정**하여 컴파일 타임에 체크
- `companion object`: 클래스 내부에 선언, `static`처럼 사용 가능

```kotlin
sealed class LoginState {
    object Success : LoginState()
    object Fail : LoginState()
}

// 컴패니언 객체
class MyClass {
    companion object {
        const val MY_CONST = 100
    }
}
```

---

### 참고
- [Kotlin 공식 문서](https://kotlinlang.org/docs/home.html)
- [컬렉션 개념](https://kotlinlang.org/docs/collections-overview.html)

---

  
**javascript 2주차.md**

### 1. `var`, `let`, `const`
- `var`  
  - 함수 스코프, **호이스팅** 발생  
  - 재할당 가능하지만 문법적 혼동이 많아 잘 쓰지 않음  
- `let`  
  - 블록 스코프, 재할당 가능  
- `const`  
  - 블록 스코프, **재할당 불가능**  
  - 객체나 배열도 참조 자체는 고정되지만 내부 프로퍼티 변경은 가능

```javascript
let mutableValue = 10;
mutableValue = 20; // 가능

const readOnlyValue = 30;
readOnlyValue = 40; // 오류
```

---

### 2. 템플릿 리터럴(Template Literal)
- **백틱**(``` ` ```)을 이용해 문자열 내에서 `${}`로 변수, 표현식 등을 삽입
- 다중 줄 문자열 작성도 가능

```javascript
const name = "JavaScript";
console.log(`이름: ${name}, 길이: ${name.length}`);
```

---

### 3. 옵셔널 체이닝(Optional Chaining)
- `?.` 연산자를 이용해 중첩 객체가 `null` 또는 `undefined`일 때 에러 대신 `undefined`를 반환
```javascript
const user = { info: { address: { city: "Seoul" } } };
console.log(user.info?.address?.city); // "Seoul"
console.log(user.info?.phone?.number); // undefined (에러 발생 X)
```

---

### 4. 디스트럭처링 할당(Destructuring Assignment)
- 배열 또는 객체에서 필요한 값만 쉽게 꺼내 쓸 수 있는 문법

```javascript
// 배열
const arr = [1, 2, 3];
const [first, second] = arr; // first=1, second=2

// 객체
const userObj = { name: "Tom", age: 22 };
const { name, age } = userObj; 
// name="Tom", age=22
```

---

### 5. 스프레드(Spread) 연산자
- `...`을 이용해 배열/객체를 펼치거나(복사), 합치는(병합) 작업에 활용
- **배열** 합치기
```javascript
const arr1 = [1, 2];
const arr2 = [3, 4];
const merged = [...arr1, ...arr2]; // [1, 2, 3, 4]
```
- **객체** 확장
```javascript
const obj1 = { name: "Alex", age: 30 };
const obj2 = { ...obj1, city: "Seoul" }; 
// { name: "Alex", age: 30, city: "Seoul" }
```
- 기존 객체에 일부 필드만 덮어쓰거나 새로운 필드를 추가할 때 사용

---

### 6. 배열과 객체 순회
- `Array.prototype.map()`, `filter()`, `reduce()` 등 콜백 함수를 활용  
- 객체는 기본적으로 **이터러블**(iterable)이 아니므로, `Object.keys()`, `Object.values()`, `Object.entries()`로 순회

```javascript
// 배열
const numbers = [1, 2, 3, 4];
const evenNums = numbers.filter(num => num % 2 === 0);

// 객체
const userData = {
  name: "Tom",
  age: 22
};
Object.entries(userData).forEach(([key, value]) => {
  console.log(key, value);
});
```

---

### 7. 콜백 함수와 비동기 처리
- 자바스크립트는 **싱글 스레드** 기반, 비동기(Non-blocking) 실행
- 콜백 함수 → **콜백 지옥** 가능
- `Promise`, `async / await` → 비동기 흐름을 좀 더 동기적 형태로 작성 가능

```javascript
// Promise
function getData() {
  return new Promise((resolve, reject) => {
    // 비동기 처리
    const success = true;
    if(success) resolve("Success");
    else reject("Fail");
  });
}

// async / await
async function fetchData() {
  try {
    const result = await getData();
    console.log(result);
  } catch (error) {
    console.log(error);
  }
}
```

---

### 참고
- [MDN JavaScript 문서](https://developer.mozilla.org/en-US/docs/Web/JavaScript)
- [MDN Web Docs - Async/await](https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Asynchronous/Async_await)
