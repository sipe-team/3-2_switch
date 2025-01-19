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
                if (level.levelName.equals(levelName, ignoreCase = true)) {
                    return level
                }
            }
            throw IllegalArgumentException("잘못된 enum 데이터 입니다.")
        }
    }
}
