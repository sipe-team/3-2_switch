
# JavaScript 주요 문법 기능 설명

이 리드미는 JavaScript에서 자주 사용되는 기능인 **템플릿 리터럴**, **옵셔널 체이닝**, **비구조화 할당**, **스프레드 연산자**, **배열 메서드**, 그리고 **Object 메서드** (`Object.keys`, `Object.values`, `Object.entries`)에 대한 설명과 예제를 제공합니다.

## 1. 템플릿 리터럴 (Template Literals)

**템플릿 리터럴**은 문자열을 보다 편리하게 다룰 수 있게 해주는 기능입니다. 백틱(``` ` ```)으로 문자열을 감싸며, 문자열 안에 변수를 쉽게 삽입할 수 있습니다. 또한, 여러 줄로 문자열을 작성하거나 표현식을 계산할 수 있습니다.

### 예시:
```javascript
const name = "Alice";
const age = 25;

// 템플릿 리터럴을 이용한 문자열 생성
const greeting = `안녕하세요, 제 이름은 ${name}이고 나이는 ${age}입니다.`;
console.log(greeting);  // 안녕하세요, 제 이름은 Alice이고 나이는 25입니다.
```

### 특징:
- 변수를 `${}`로 감싸서 문자열 내에 삽입할 수 있습니다.
- 여러 줄의 문자열을 쉽게 만들 수 있습니다.

---

## 2. 옵셔널 체이닝 (Optional Chaining)

**옵셔널 체이닝**(`?.`)은 객체나 배열에서 값을 접근할 때, 중간에 `null` 또는 `undefined`가 있을 경우 에러를 발생시키지 않고 `undefined`를 반환하도록 해줍니다. 이를 통해 null 체크를 줄일 수 있습니다.

### 예시:
```javascript
const user = {
  name: "Alice",
  address: {
    city: "Seoul",
    zip: "12345"
  }
};

// 옵셔널 체이닝을 사용한 안전한 접근
const city = user.address?.city;
const country = user.address?.country;

console.log(city);     // Seoul
console.log(country);  // undefined
```

### 특징:
- `?.` 연산자를 사용하여, 객체가 `null` 또는 `undefined`일 경우 에러를 방지합니다.

---

## 3. 비구조화 할당 (Destructuring Assignment)

**비구조화 할당**은 배열이나 객체에서 값을 추출하여 변수에 할당할 수 있는 방법입니다. 코드가 간결해지고 가독성이 향상됩니다.

### 예시:
```javascript
// 객체에서 비구조화 할당
const person = { name: "Alice", age: 25 };
const { name, age } = person;
console.log(name, age);  // Alice 25

// 배열에서 비구조화 할당
const numbers = [1, 2, 3];
const [first, second] = numbers;
console.log(first, second);  // 1 2
```

### 특징:
- 객체나 배열에서 값을 추출하여 직접 변수로 할당할 수 있습니다.
- 객체의 키와 변수명이 동일할 때는 더 간단히 사용할 수 있습니다.

---

## 4. 스프레드 연산자 (Spread Operator)

**스프레드 연산자**(`...`)는 배열이나 객체를 펼치는 데 사용됩니다. 객체나 배열을 복사하거나 합치는 데 유용하게 사용됩니다.

### 예시:
```javascript
// 배열에서 스프레드 연산자
const arr1 = [1, 2, 3];
const arr2 = [...arr1, 4, 5];
console.log(arr2);  // [1, 2, 3, 4, 5]

// 객체에서 스프레드 연산자
const obj1 = { name: "Alice", age: 25 };
const obj2 = { ...obj1, city: "Seoul" };
console.log(obj2);  // { name: 'Alice', age: 25, city: 'Seoul' }
```

### 특징:
- 배열이나 객체의 복사 및 합치기를 쉽게 할 수 있습니다.
- 객체나 배열의 상태를 변경하지 않고 새로운 객체나 배열을 생성합니다.

---

## 5. 배열 메서드 (Array Methods)

JavaScript의 배열은 다양한 내장 메서드를 제공합니다. 이 메서드들은 배열을 효과적으로 다룰 수 있게 도와줍니다. 대표적인 배열 메서드로는 `map()`, `filter()`, `reduce()` 등이 있습니다.

### 예시:

```javascript
const numbers = [1, 2, 3, 4, 5];

// map() - 배열의 각 요소에 대해 함수를 실행
const doubled = numbers.map(num => num * 2);
console.log(doubled);  // [2, 4, 6, 8, 10]

// filter() - 조건에 맞는 요소만 필터링
const evenNumbers = numbers.filter(num => num % 2 === 0);
console.log(evenNumbers);  // [2, 4]

// reduce() - 배열을 하나의 값으로 축소
const sum = numbers.reduce((acc, num) => acc + num, 0);
console.log(sum);  // 15
```

### 특징:
- `map()`, `filter()`, `reduce()` 등을 사용해 배열을 쉽게 다룰 수 있습니다.
- 배열을 변형하거나 값을 계산하는 데 매우 유용합니다.

---

## 6. Object 메서드: `Object.keys()`, `Object.values()`, `Object.entries()`

**`Object.keys()`**, **`Object.values()`**, **`Object.entries()`**는 객체에서 키와 값들을 배열로 변환하는 메서드입니다.

### 예시:

```javascript
const person = { name: "Alice", age: 25, city: "Seoul" };

// Object.keys() - 객체의 키를 배열로 반환
const keys = Object.keys(person);
console.log(keys);  // ['name', 'age', 'city']

// Object.values() - 객체의 값을 배열로 반환
const values = Object.values(person);
console.log(values);  // ['Alice', 25, 'Seoul']

// Object.entries() - 객체의 [키, 값] 쌍을 배열로 반환
const entries = Object.entries(person);
console.log(entries);  // [['name', 'Alice'], ['age', 25], ['city', 'Seoul']]
```

### 특징:
- `Object.keys()`, `Object.values()`, `Object.entries()`를 사용하여 객체의 키, 값, 또는 키-값 쌍을 배열 형태로 반환할 수 있습니다.
- 객체의 각 항목을 순회하거나 다른 데이터 구조로 변환할 때 유용합니다.

---

## 결론

이 리드미에서 다룬 JavaScript 문법들은 코드의 가독성을 높이고, 작업을 더 쉽게 만들어주는 강력한 도구들입니다. 각 기능을 잘 활용하면 더 간결하고 효율적인 코드를 작성할 수 있습니다.

자세한 사항은 아래 문서를 참조해 보세요:
- [MDN Web Docs: 템플릿 리터럴](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Template_literals)
- [MDN Web Docs: 옵셔널 체이닝](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/Optional_chaining)
- [MDN Web Docs: 비구조화 할당](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/Destructuring_assignment)
- [MDN Web Docs: 스프레드 연산자](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/Spread_syntax)
- [MDN Web Docs: 배열 메서드](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array)
- [MDN Web Docs: Object 메서드](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Object)

