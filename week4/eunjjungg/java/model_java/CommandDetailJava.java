package model_java;

/**
 * getter 자동 생성해주는 레코드 객체 - 불변객체이다.
 */
public record CommandDetailJava(
        LevelJava level,
        CourseJava course,
        String activity
) {
    // 뭐가 추가로 필요하겠어..?
}