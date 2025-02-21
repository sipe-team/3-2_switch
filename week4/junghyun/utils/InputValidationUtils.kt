package pairmatching.utils

object InputValidationUtils {

    fun validateInput(input: String) {
        require(input.isNotBlank()) { "입력 데이터가 없습니다." }
    }

    fun validateAndSplitInput(input: String, size: Int): List<String> {
        validateInput(input)

        val inputData = input.split(", ")
        require(inputData.size == size) { "입력한 데이터가 부족합니다." }

        return inputData
    }
}
