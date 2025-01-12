## 1주차

### 목표

- 프로그래머스 '코딩 기초 트레이닝' 문제 풀기

### 내용

- [1번 배열 비교하기](https://school.programmers.co.kr/learn/courses/30/lessons/181856?language=kotlin)

```kotlin
class Solution {
    fun solution(arr1: IntArray, arr2: IntArray): Int {
        if (arr1.size != arr2.size) {
            return if (arr1.size < arr2.size) -1 else 1
        }

        val sumArr1 = arr1.sum()
        val sumArr2 = arr2.sum()

        return when {
            sumArr1 < sumArr2 -> -1
            sumArr1 > sumArr2 -> 1
            else -> 0
        }
    }
}
```

- [2번 배열 만들기 2](https://school.programmers.co.kr/learn/courses/30/lessons/181921?language=kotlin)

```kotlin
class Solution {
    fun solution(l: Int, r: Int): IntArray {
        var answer = mutableListOf<Int>()

        for (i in l..r) {
            val numberStr = i.toString()
            if (numberStr.all { it == '0' || it == '5' }) {
                answer.add(i)
            }
        }

        return if (answer.isEmpty()) intArrayOf(-1) else answer.toIntArray()
    }
}
```

- [수 조작하기 1](https://school.programmers.co.kr/learn/courses/30/lessons/181926?language=kotlin)

```kotlin
class Solution {
    fun solution(n: Int, control: String): Int {
        var answer: Int = 0
        answer = n

        for (char in control) {
            answer += getControlNumber(char)
        }

        return answer
    }

    // w : n += 1
    // s : n -= 1
    // d : n += 10
    // a : n -= 10
    private fun getControlNumber(char: Char): Int {
        if (char == 'w') {
            return 1
        }
        if (char == 's') {
            return -1
        }
        if (char == 'd') {
            return 10
        }
        return -10
    }
}
```

- [수열과 구간 쿼리 3](https://school.programmers.co.kr/learn/courses/30/lessons/181924?language=kotlin)

```kotlin
class Solution {
    fun solution(arr: IntArray, queries: Array<IntArray>): IntArray {
        var answer: IntArray = intArrayOf()
        answer = arr

        queries.forEachIndexed { _, row ->
            val data = answer[row[0]]
            answer[row[0]] = answer[row[1]]
            answer[row[1]] = data
        }

        return answer
    }
}
```
