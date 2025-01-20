package tmp.model

enum class Command(val inputCode: String, val printingName: String) {
    PAIR_MATCH("1", "1. 페어 매칭"),
    PAIR_SHOW("2", "2. 페어 조회"),
    PAIR_INIT("3", "3. 페어 초기화"),
    QUIT("Q", "Q. 종료");

    companion object {
        fun parseFrom(input: String): Command? {
            return Command.entries.find { it.inputCode == input }
        }
    }
}