## 2주차

### 목표

- Kotlin 에 대한 설명을 정리하고 관련 프로그래머스 문제 풀기

### 내용

- Kotlin 이란?

  - (Java 동생..ㅎ)
  - JVM 기반 언어, Java 와 100% 호환
  - 간결하고 Null 안전성을 내장
  - 함수형 프로그래밍을 지원한다.

- Class 선언

  - 생성자, `getter/setter`, `toString`, `equals`, `hashCode` 등을 자동으로 생성해준다.

    ```kotlin
    data class Person(val name: String, val age: Int)
    ```

- Null 안전성

  - 모든 변수는 기본적으로 Null 이 될 수 없다.
  - ? 을 사용해서 Null 허용 변수를 선언한다.

    ```kotlin
    val name: String? = null
    ```

- 타입 추론

  - 더 강력한 타입 추론을 지원한다.

    ```kotlin
    val name = "Test"
    val age = 25

    // 각각을 String, Int 타입으로 추론한다.
    ```

- 함수형 프로그래밍

  - Java 에서 아래와 같이 Stream 을 썼다면,

    ```java
    List<Person> personList = new ArrayList<>();

    List<PersonResponse> result = personList.stream()
                    .filter(person -> Objects.nonNull(person.getSeq()) && Objects.nonNull(person.getName()))
                    .map(person -> new PersonResponse(person.getName(), person.getSeq()))
                    .toList();
    ```

  - Kotlin 에서는 아래와 같이 사용할 수 있다.

    ```kotlin
    val personList: List<Person> = listOf()

    val result: List<PersonResponse> = personList
        .filter { person -> person.seq != null && person.name != null }
        .map { person -> PersonResponse(person.name!!, person.seq!!) }
    ```

- Collection

  - List, Set, Map : Immutable
    - 변경할 수 없는 컬렉션
    - 읽기 전용(Read-Only) 이며, 수정 기능이 없다.
    - Java 의 `Collections.unmodifiableList` 와 비슷하다.
  - MutableList, MutableSet, MutableMap : Mutable
    - 그 컬렉션의 내용을 바꿀 수 있음.
    - Java Collection 과 동일하다.
  - 선언 시

    ```
    List : listOf() / mutableListOf()
    Set : setOf() / mutableSetOf()
    Map : mapOf() / mutableMapOf()
    ```

- Collection API
  collection에 대고 `.` 누르면 사용할 수 있는 api가 주루룩 뜬다. 그중에 주로 쓰는 건 다음과 같다.

  - filter : predicate에 맞춰서 필터링 목적.
  - filterIsInstance : 특정 type으로 필터링하고 싶을 떄.

    ```kotlin
    fun main() {
        val anyThings = listOf<Any>("3", 3, "hi")
        val strings = anyThings.filterIsInstance<String>()
        println(strings) // 3, hi
    }
    ```

  - filterNotNull : element가 nullable일 때 nonnull만 필터링하고 싶은 경우.

    ```kotlin
    fun main() {
        val mList = listOf("3", null, "hi")
        val strings = mList.filterNotNull()
        println(strings) // 3, hi
    }
    ```

  - map / mapNotNull : 매핑 ! (자바의 맵과 거의 똑같은듯?)
  - any : predicate과 일치하는 요소가 있다면 true, 없으면 false 반환.

    ```kotlin
    fun main() {
        val mList = listOf("3", "hello", "hi")
        val isNumExist = mList.any { it.toIntOrNull() != null }
        println(isNumExist) // true
    }
    ```

- return@함수명
  - return
    - return 은 함수 실행을 종료하고 값을 반환한다.
    - 외부 함수 전체를 종료한다.
  - return@함수명
    - 람다만 종료할 때 사용한다.
    ```kotlin
    fun main() {
        listOf(1, 2, 3).forEach {
            if (it == 3) return@forEach
            println(it)
        }
        println("종료")
    }
    ```
- top level function

  - 함수를 사용하기 위해 클래스를 만들 필요가 없음.
  - 파일 레벨에서 간단하게 함수 선언 가능.
  - Java 의 static method 를 대체할 수 있다.

### 문제

- [문자열 내 마음대로 정렬하기](https://school.programmers.co.kr/learn/courses/30/lessons/12915?language=kotlin)

```kotlin
class Solution {
    fun solution(strings: Array<String>, n: Int): Array<String> {
        var answer = arrayOf<String>()

        //먼저 사전순 정렬
        strings.sort()

        //인덱스 n 번째 글자를 기준으로 오름차순 정렬
        answer = strings.sortedBy { it[n] }.toTypedArray()

        return answer
    }
}
```

- [두 개 뽑아서 더하기](https://school.programmers.co.kr/learn/courses/30/lessons/68644?language=kotlin)

```kotlin
class Solution {
    fun solution(numbers: IntArray): IntArray {
        val result: MutableSet<Int> = hashSetOf()

        for (i in 0 until numbers.size - 1) {
            for (j in i + 1 until numbers.size) {
                result.add(numbers[i] + numbers[j])
            }
        }

        return result.sorted().toIntArray()
    }
}
```
