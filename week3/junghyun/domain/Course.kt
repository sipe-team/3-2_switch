package pairmatching.domain

enum class Course(val courseName: String) {

    /**
     * Kotlin 에서는 기본적으로 name 프로퍼티가 정의되어 있음.
     * 만약 같은 네이밍의 변수를 사용하고 싶다면 override 키워드를 사용해야 한다.
     */

    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    /**
     * companion object 는 클래스 내부에 정의된 싱글톤 객체로, 클래스와 연관된 정적 멤버나 유틸리티 메서드를 정의할 때 사용
     * Java 의 static 키워드와 비슷한 기능을 제공, Kotlin 의 객체지향적인 특성을 유지할 수 있다.
     */

    companion object {
        fun fromString(courseName: String?): Course {
            for (course in entries) {
                if (course.courseName.equals(courseName, ignoreCase = true)) {
                    return course
                }
            }
            throw IllegalArgumentException("잘못된 enum 데이터 입니다.")
        }
    }
}
