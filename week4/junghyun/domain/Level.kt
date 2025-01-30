package pairmatching.domain

enum class Level(val levelName: String) {

    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    companion object {
        fun fromString(levelName: String?): Level {
            for (level in entries) {
                //Level 클래스에만 코멘트 참고해서 방법 변경해보았음.
                if (level.levelName.lowercase() == levelName?.lowercase()) {
                    return level
                }
            }
            throw IllegalArgumentException("잘못된 enum 데이터 입니다.")
        }
    }
}
