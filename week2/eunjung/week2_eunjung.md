## JAVA 
- kotlin의 부모이자 언니이자 .. 여튼 그거
- ej) 개인적으로 근본 언어라고 생각합니다.. ^.^

### Class
```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
    	return name;
    }

    public int getAge() {
    	return age;
    }
}
```
- 외부에서 값을 참조하고 싶거나 셋업하고 싶다면 getter & setter를 정의해주어야 한다.
- 생성자 정의 방식도 코틀린과 조금 다르다. constructor 키워드 대신 `public 객체명`으로 만들어줘야 한다. 

### Null 
코틀린은 null 여부를 `?`로 나타낸다. 하지만 자바는 그런거 없다. 따라서 결과물이 null인지 아닌지 체크를 매번 해주어야 한다. 
```java
public temp(String name) {
	if (!StringUtils.hasText(name)) {
		//
	}
}
```

### 타입
- 자바는 타입을 앞에 명시한다. 
- Java 10부터는 var를 사용해서 타입 추론도 가능하다고 함. 
```java
String name1 = "test";
var name2= "test";
```

### Stream
- Collection 관련 api를 사용하고 싶을 때, 코틀린은 그냥 바로 사용 가능. 
- 그러나 자바는 Stream이라는 파이프라인을 거쳐서 사용해야 한다.
- from jh) 배열 또는 Collection 을 선언형 스타일로 처리할 때 사용하고, 데이터를 변환하거나 필터를 통해 최종 데이터 연산 결과를 만들 때 사용한다.
```java
List<Person> personList = new ArrayList<>();

List<PersonResponse> result = personList.stream()
                .filter(person -> Objects.nonNull(person.getSeq()) && Objects.nonNull(person.getName()))
                .map(person -> new PersonResponse(person.getName(), person.getSeq()))
                .toList();
```

### Record 객체 
- 데이터 저장하고 읽는데 사용함. 고대 자바에는 없었고 버전 업데이트 되면서 생겼다고 한다.
- 주로 DTO 객체 생성 시 사용한다고 함. 
- kotlin의 data class와 유사한 용도로 사용하면 될 듯하다. 
- 컴파일 시 자동으로 생성자를 만든다고 함. also getter, setter. 
```java
public record TestResponse(
        String name,
        int cost
) {

    public static TestResponse of(String name, int cost) {
        return new TestResponse(name, cost);
    }
}
```

### 문제 
- [문자열 내 마음대로 정렬하기](https://school.programmers.co.kr/learn/courses/30/lessons/12915?language=java)

```java
class Solution {
    /**
     * 문제: 문자열 내 마음대로 정렬하기, 12915, Java
     * 소감: 처음에는 stream의 toList를 썼는데, 프로그래머스 자바 버전이랑 안 맞다고 해서,
     * Arrays.asList api를 사용해보았다.
     */
    public String[] solution_12915(String[] strings, int n) {
        // array -> list
        List<String> stringList = new LinkedList<String>(Arrays.asList(strings));

        // compare
        stringList.sort((o1, o2) -> {
            char tmp1 = o1.charAt(n);
            char tmp2 = o2.charAt(n);

            if (tmp1 == tmp2) {
                return o1.compareTo(o2);
            }

            return Character.compare(tmp1, tmp2);
        });

        // list -> array
        String[] answer = new String[stringList.size()];
        for (int i = 0; i < stringList.size(); i++) {
            answer[i] = stringList.get(i);
        }

        return answer;
    }
}
```

- [두 개 뽑아서 더하기](https://school.programmers.co.kr/learn/courses/30/lessons/68644?language=java)

```java
class Solution {
    /**
     * 문제: 문자열 내 마음대로 정렬하기, 12915, Java
     * 소감: 자바 너무 어렵다 ;;; 특히 Array <-> List 변환이 너무 헷갈리고 어렵다 ㅜ.ㅜ;;
     */
    public int[] solution(int[] numbers) {

        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < numbers.length - 1; i++) {
            int j = i + 1;
            while (j < numbers.length) {
                nums.add(numbers[i] + numbers[j]);
                j++;
            }
        }


        int[] answer = new int[nums.size()];
        int i = 0;
        for (Integer num : nums) {
            answer[i++] = num;
        }
        Arrays.sort(answer);
        return answer;
    }
}
```

### 💬
한 주 또 시작이네요.. 화이팅하시죠.. ㅎ
