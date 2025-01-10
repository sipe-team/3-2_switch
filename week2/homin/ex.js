const order = {
  orderId: 123,
  customer: {
    name: "Alice",
    email: "alice@example.com",
  },
  items: [
    { productId: 1, name: "Laptop", price: 1000, quantity: 1 },
    { productId: 2, name: "Mouse", price: 20, quantity: 2 },
  ],
  shipping: {
    address: "123 Main St",
    city: "Seoul",
    country: "Korea",
  },
  status: "Pending",
};

// Q1. 템플릿 리터럴로 주문 요약 출력
function printOrderSummary(order) {
}

// Q2. 옵셔널 체이닝으로 배송지 정보 출력
function printShippingInfo(order) {
}

// Q3. 비구조화 할당으로 데이터 추출 후 가공
// (a) 주문 상품의 이름과 수량을 객체로 변환
function mapItemsToQuantity(order) {
}

// (b) 고객 정보를 활용한 환영 메시지 생성
function generateWelcomeMessage(order) {
}

// Q4. 스프레드 연산자로 새 상품 추가
function addItem(order, newItem) {
}

// Q5. 배열 메서드 활용
// (a) 상품 이름 반환
function getItemNames(order) {
}

// (b) 총 주문 금액 계산
function getTotalPrice(order) {
}

// Q6. Object.keys, Object.values, Object.entries 활용
function displayCustomerInfo(order) {
}

// 결과 출력
console.log("\nQ1. 주문 요약 출력:");
const orderSummary = printOrderSummary(order);
console.log(orderSummary.summary);

console.log("\nQ2. 배송지 정보 출력:");
console.log(printShippingInfo(order));

console.log("\nQ3. 주문 상품의 이름과 수량 객체로 변환:");
console.log(mapItemsToQuantity(order));

console.log("\nQ4. 환영 메시지 생성:");
console.log(generateWelcomeMessage(order));

console.log("\nQ5. 상품 이름 목록:");
console.log(getItemNames(order));

console.log("\nQ6. 총 주문 금액 계산:");
console.log(getTotalPrice(order));

console.log("\nQ7. 고객 정보 출력:");
console.log(displayCustomerInfo(order));
/* 실행 결과
Q1. 주문 요약 출력:

      주문 번호: 123
      고객 이름: Alice
      이메일: alice@example.com
      주문 상태: Pending
      총 주문 금액: 1040
      배송지: 123 Main St, Seoul, Korea
    

Q2. 배송지 정보 출력:
123 Main St, Seoul, Korea

Q3. 주문 상품의 이름과 수량 객체로 변환:
{ Laptop: 1, Mouse: 2 }

Q4. 환영 메시지 생성:
Alice님, alice@example.com로 주문 확인 메일이 발송되었습니다.

Q5. 상품 이름 목록:
[ 'Laptop', 'Mouse' ]

Q6. 총 주문 금액 계산:
1040

Q7. 고객 정보 출력:
name: Alice
email: alice@example.com
*/