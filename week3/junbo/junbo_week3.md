# 코틀린(Kotlin) 3주차 (추가 내용 보충)

## 1. Data Class 심화
- copy(),equals(), hascode(), toString()
- copy() 함수를 통해 일부 프로퍼티만 변경하면서 객체를 생성할 수 있습니다.
- toString() 데이터 클래스 내용을 좀 더 명확하게 제공해줌 ex) User(name=minsu, age=10)
- equals()는 두 데이터 클래스 인스턴스의 **프로퍼티가 동일한지** 비교해주며,  
- hashCode()는 해당 인스턴스의 **고유 식별 해시값**을 반환해 해시 기반 자료구조(예: HashMap) 등에서 활용됩니다.

```kotlin
val personA = Person(age = 20, name = "John")
val personB = personA.copy(name = "Alice") 
println(personB) // age=20, name="Alice"

// 사용 예시
fun main() {
    val personA = Person(10, "Sam")
    val personB = personA.copy(name = "Mike") // 얕은 복사
    println(personA)   // toString() 자동 생성
    println(personB)   // name만 변경됨
}
```
- 서버 개발 시
- API 응답 형식을 data class로 정의하면, 직관적이고 유지보수가 편리해집니다.


## 2. runCatching과 에러 처리
- 결과(Result) 체이닝	
- onSuccess, onFailure 외에도, 상황에 따라 recover, map 등의 함수를 적용해 조건부로 예외를 변환할 수 있습니다.
- 예:
```kotlin
val result = runCatching { riskyOperation() }
    .recover { exception ->
        // 특정 유형의 예외를 다른 값으로 변환
        if (exception is IllegalArgumentException) {
            "Recovered from IllegalArgument"
        } else {
            throw exception
        }
    }
    .onSuccess {
        println("Success -> $it")
    }
    .onFailure {
        println("Failure -> $it")
    }
```

- 서버/백엔드
- DB 질의나 파일 입출력 등에서 발생할 수 있는 에러를 runCatching으로 감싸면 로직 분리가 깔끔해집니다.
- 참고 자료: https://toss.tech/article/21010

## 3. 코루틴(Coroutine) 추가 예시
- 비동기 HTTP 요청(예: Ktor 클라이언트)과 결합

```kotlin
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*

val client = HttpClient()

suspend fun fetchData(): String {
    // 코루틴 컨텍스트 안에서 동작
    return client.get("https://example.com/data")
}

fun main() = runBlocking {
    val response = async { fetchData() }
    println(response.await())
}
```

- async를 사용하여 비동기로 HTTP 요청을 보내고, await()로 결과를 받음.
- Structured Concurrency(구조화된 동시성)
- 부모 스코프가 취소되면 자식 코루틴도 같이 취소되는 형태로, 메모리 누수나 예외 전파 문제를 예방합니다.

## 4. Ktor 간단하게 맛보기(인텔리제이 CE 기준)
1. https://ktor.io/ 에 들어가서 start 버튼을 누른다.
2. New Ktor Project 옆 Project artifcat에 원하는 프로젝트 패키지명 작성(Ktor는 Netty 기반으로 동작)
3. 아래 프로젝트에 원하는 Plugins 추가해준다. (다양하게 있지만 보통 CORS, kotlinx.serialization 등을 Add 함)
4. 원하는 경로에 다운로드하고 인텔리제이 CE로 해당 프로젝트 실행하면 기본 구성 요소는 추가 되어 있음
5. 미쳐 추가 하지못한 plugin들도 Gradle.kts에 코틀린 문법으로 추가 할수 있으니 걱정 안해도 된다.


#### ktor Routing
- 라우팅(Routing)

```kotlin
routing {
    get("/") {
        call.respondText("Hello, Ktor!")
    }

    get("/user/{id}") {
        val id = call.parameters["id"]
        call.respondText("User ID: $id")
    }
}
```
관련 내용 다음주 설명


---

# 자바스크립트(JavaScript) 3주차 정리

## 1. 라이브러리 vs. 프레임워크

보통 **리액트(React)**는 “리액트는 라이브러리일까, 프레임워크일까?”라는 말들이 많은데, 사용자 인터페이스(UI)를 만들기 위한 라이브러리다.  
리액트는 UI 영역만 다루는 것을 목표로 하므로, 라우팅이나 데이터 관리 등은 선택적으로 다른 라이브러리와 조합해 사용할 수 있다.

## 2. SPA(싱글 페이지 애플리케이션) 

### 전통적인 JSP/서버 렌더링

과거에는 JSP나 PHP 같은 서버 사이드 기술을 사용해 페이지 단위로 서버에서 HTML을 생성한 뒤, 완성된 HTML 파일을 클라이언트(브라우저)로 전송했다.
이 방식을 **서버 사이드 렌더링(SSR)**이라고도 부른다.

### 클라이언트 사이드 렌더링(CSR)

SSR과 달리, **CSR(Client Side Rendering)**에서는 브라우저가 자바스크립트 파일 하나를 받아서 모든 화면 전환 로직을 수행한다.
리액트, 뷰, 앵귤러 등 대부분의 현대 자바스크립트 라이브러리·프레임워크는 CSR 방식을 사용한다.
이렇게 해주면 브라우저는 처음에 한 번만 전체 HTML을 다운받고, 이후 페이지 이동이나 상태 변화는 DOM 일부만 업데이트하면서 동작한다.


### 싱글 페이지 애플리케이션(SPA)

SPA는 말 그대로 ‘페이지가 하나(single)인 애플리케이션’이다. 메인 HTML을 한 번만 불러오고, 페이지 이동은 자바스크립트가 동적으로 DOM을 수정함으로써 이뤄진다.
이를 통해 브라우저가 전체 HTML을 새로고침하지 않아도 되므로, 마치 네이티브 앱처럼 부드러운 화면 전환이 가능하다.
핵심 내용을 정리를 하면
- 메인 HTML(예: index.html)을 한 번 로드
- 이후 페이지 이동은 내부 요소만 교체(리액트가 가상 DOM 등을 통해 효율적으로 처리)


## 3. 번들링과 트랜스파일링

“CSR 방식이라면, 수많은 자바스크립트·타입스크립트 파일을 어떻게 효율적으로 관리하고, 브라우저가 이해할 수 있는 형태로 바꿀까?”
이를 위해 **번들러(Bundler)**와 **트랜스파일러(Transpiler)**가 등장한다.

3-1 바벨(Babel)
	•	최신 자바스크립트 문법(ES6+)이나 타입스크립트를 구형 브라우저가 이해할 수 있는 구버전 자바스크립트로 변환
	•	이 과정을 **트랜스파일링(Transpiling)**이라 부름
	•	Babel 공식 사이트에서 확인 가능

3-2 웹팩(Webpack) 등 번들러
	•	여러 파일(자바스크립트, CSS, 폰트, 이미지 등)을 하나의 최적화된 묶음(번들)으로 만드는 도구
	•	가장 오래된 대표적인 도구가 **웹팩(Webpack)**이며, 최근에는 esbuild(Go 언어로 만듬), SWC(Speedy Web Compiler로 Rust 언어로 만듬) 등 더 빠른 성능을 제공하는 대안도 등장
	•	Webpack 공식 사이트에서 확인 가능
※ 추가적으로 확인 해보니 esbuild,SWC 둘다 번들러 + 트랜스파일러 기능을 제공 해준다고 함

	번들러 동작 과정
	1.	여러 자바스크립트/타입스크립트 파일을 모두 모음
	2.	Babel 등 트랜스파일러를 이용해 브라우저 호환 코드를 생성
	3.	하나(또는 여러 개)로 합쳐서 배포

	이로 인해 첫 로딩이 느려질 수 있지만, **코드 스플리팅(Code Splitting) & 레이지 로딩(Lazy Loading)**을 통해 필요한 시점에 필요한 코드만 불러오게 최적화할 수 있다.

## 4. 리액트의 동작 원리

4-1 가상 DOM(Virtual DOM)

리액트는 가상 DOM을 활용해 DOM 변경이 필요한 부분만 효율적으로 업데이트한다.
전통적인 방식(JQuery 등)은 변경이 있을 때마다 직접 DOM을 수정해야 했지만, 리액트는 내부 알고리즘(렌더링 엔진)을 통해 최소한의 DOM 변경만 수행한다.

4-2 상태(State) 기반 렌더링

리액트 컴포넌트는 useState(함수형 컴포넌트 기준) 같은 훅(Hook)을 사용해 상태를 정의한다.
상태가 바뀌면 해당 컴포넌트를 재렌더링하면서 UI를 자동으로 갱신한다.

```JavaScript
// 이렇게 코드 작성 하는게 맞을까요?
import { useState } from 'react';

function Counter() {
  const [count, setCount] = useState(0);

  const handleClick = () => {
    setCount(count + 1);
  };

  return (
    <div>
      <p>현재 카운트: {count}</p>
      <button onClick={handleClick}>카운트 증가</button>
    </div>
  );
}
```
- useState는 [value, setter] 형태로 반환
-	value가 변경될 때마다 리액트가 알아서 재렌더링
-	DOM 조작을 직접 하지 않아도, 선언적인 코드를 통해 UI 변경

6. 프로젝트 생성 & 구조

리액트로 프로젝트를 시작할 때는 여러 가지 스캐폴딩(프로젝트 템플릿) 도구가 있다.
생성된 프로젝트를 열어보면 대략 아래와 같은 구조가 나타난다.

my-react-app/
 ┣ public/
 ┣ src/
 ┃ ┣ App.tsx
 ┃ ┣ main.tsx
 ┃ ┗ ...
 ┣ index.html
 ┣ package.json
 ┗ ...

	•	src/main.tsx(또는 index.js)에서 root.render(<App />) 형태로 최초 렌더링
	•	App.tsx에서 메인 컴포넌트의 구조 및 로직을 작성
	•	빌드시 Webpack 같은 모든 코드를 번들링하여 브라우저가 이해할 수 있는 하나(또는 여러 개)의 자바스크립트 파일을 만든다.

7. 첫 화면 렌더링 과정 요약 
	1.	브라우저가 index.html을 요청 및 로드
	2.	HTML 내부의 <div id="root"></div> 태그 확인
	3.	<script src="main.tsx(또는 main.js)"></script> 호출
	4.	리액트가 root.render(<App />)를 실행해 컴포넌트 트리를 구성
	5.	컴포넌트의 상태 변화(useState) 발생 시 필요한 부분만 재렌더링

이런 패턴 덕분에, SPA 구조를 쉽게 구현할 수 있다.


추가적인 궁금증 🤔
1. react hook 개념이 결국 리액트 컴포넌트 와 생명주기 관련 기능을 쉽게 다룰수 있게 도와 준다고 알고 있습니다.
 리액트 문서에 루프, 조건 또는 중첩 함수 내부에서 Hooks를 호출하지 마세요 되어 있는데 그럼 어떻게 리액트에서 상태와 로직을 어떻게 분리하고 관리하나요?
2. useReducer 와 useState 차이? 둘다 똑같이 재랜더링 기능 하는것 같아 보이는데 구체적인 차이가 무엇인지 궁금합니다.
3. useState가 있는데 왜 여러 상태 관리 라이브러리(redux, Zustand, MobX 등)가 나오게 된건가요?
4. 안드로이드에선 회사 및 프로젝트 마다 다를수 있겠지만 보통 일반적으로 순수 MVVM에서 확장된(?) 안드로이드용 MVVM(안드로이드 생명주기를 인식하고 데이터 holder 역할 및 UI 롼련 밀접된 로직 처리)을 해줌)을 사용하고 있습니다. 여기서 보통 react에선 대부문 많이 사용한다면 어떤걸 방식의 아키텍처 패턴을 사용하시는지 궁금합니다.
