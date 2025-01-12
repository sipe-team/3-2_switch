## 1주차(eunjung)

### 목표

- 프로그래머스 '코딩 기초 트레이닝' 문제 풀기

### 내용

- [1번 배열 비교하기](https://school.programmers.co.kr/learn/courses/30/lessons/181856?language=java)

```java
class Solution {
    /**
     * 문제: 배열 비교하기, 181856, Java
     * 소감: stream 변환을 처음 써 봐서 새롭다 ㅎ.ㅎ.. 신기해 !
     */
    public int solution_181856(int[] arr1, int[] arr2) {
        final int ARR_1_WIN = 1;
        final int ARR_2_WIN = -1;
        final int DRAW = 0;

        // array 길이가 다르면 더 긴 쪽이 우승
        // array 길이가 같다면 모든 원소 합 큰 쪽이 우승
        // arr 1이 크면 1, arr 2가 크면 -1, 같다면 0 반환
        int lengthOfArr1 = arr1.length;
        int lengthOfArr2 = arr2.length;

        if (lengthOfArr1 > lengthOfArr2) {
            return ARR_1_WIN;
        } else if (lengthOfArr1 < lengthOfArr2) {
            return ARR_2_WIN;
        }

        int sumOfArr1 = Arrays.stream(arr1).sum();
        int sumOfArr2 = Arrays.stream(arr2).sum();

        if (sumOfArr1 > sumOfArr2) {
            return ARR_1_WIN;
        } else if (sumOfArr1 < sumOfArr2) {
            return ARR_2_WIN;
        } else {
            return DRAW;
        }
    }
}
```

- [2번 배열 만들기 2](https://school.programmers.co.kr/learn/courses/30/lessons/181921?language=java)

```java
class Solution {
    /**
     * 문제: 배열 만들기2, 181921, Java
     * 소감: string의 모든 글자 검사하는건 [toCharArray] 연산자를 활용하면 된다는 걸 배웠다 !
     */
    public int[] solution_181921(int l, int r) {
        List<Integer> answer = new ArrayList<>();

        for (int i = l; i <= r; i++) {
            String str = String.valueOf(i);

            boolean isValidValue = true;
            for (int j = 0; j < str.length(); j++) {
            if (str.charAt(j) != '5' && str.charAt(j) != '0') {
                isValidValue = false;
                break;
            }
        }

            if (isValidValue) answer.add(i);
        }

        if (answer.isEmpty()) answer.add(-1);

        int[] answerArr = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            answerArr[i] = answer.get(i);
        }

        return answerArr;
    }
}
```

- [수 조작하기 1](https://school.programmers.co.kr/learn/courses/30/lessons/181926?language=java)

```java
class Solution {
    /**
     * 문제: 수 조작하기, 181926, Java
     * 소감: toCharArray 바로 써먹음. good.
     */
    public int solution_181926(int n, String control) {
        int answer = n;

        char[] controls = control.toCharArray();
        for (char input : controls) {
            answer += doControl(input);
        }

        return answer;
    }

    private int doControl(char input) {
        return switch (input) {
            case 'w' -> 1;
            case 's' -> -1;
            case 'd' -> 10;
            case 'a' -> -10;
            default -> 0;
        };
    }
}
```

- [수열과 구간 쿼리 3](https://school.programmers.co.kr/learn/courses/30/lessons/181924?language=java)

```java
class Solution {
    /**
     * 문제: 수 조작하기, 181924, Java
     * 소감: -
     */
    public int[] solution_181924(int[] arr, int[][] queries) {
        int[] answer = arr;

        for (int[] query : queries) {
            int idx1 = query[0];
            int idx2 = query[1];

            int valueOfIdx1 = arr[idx1];
            int valueOfIdx2 = arr[idx2];

            arr[idx1] = valueOfIdx2;
            arr[idx2] = valueOfIdx1;
        }

        return answer;
    }
}
```

### 💬
자바 동생 코틀린 ㅋ ㅋ. 재밌다 ! 
정현님이 언니고 내가 동생. 재밌다 !
