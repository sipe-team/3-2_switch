package tmp.model

enum class Course(val inputCode: String) {
    BACKEND("백엔드"),
    ANDROID("안드로이드");

    val printingName = inputCode

    companion object {
        fun parseFrom(input: String): Course? {
            return Course.entries.find { it.inputCode == input }
        }
    }
}