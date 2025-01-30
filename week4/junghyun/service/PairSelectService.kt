package pairmatching.service

import pairmatching.domain.Course
import pairmatching.domain.Level
import pairmatching.domain.Pair
import pairmatching.utils.InputValidationUtils.validateAndSplitInput
import java.util.*

class PairSelectService(
    private val scanner: Scanner,
    private val matchingHistory: MutableMap<Course, MutableMap<Level, MutableSet<Pair>>>
) {

    fun getPairMatchingResult() {
        val fetchingOption = """
                #############################################
                과정: 백엔드 | 프론트엔드
                미션:
                  - 레벨1: 자동차경주 | 로또 | 숫자야구게임
                  - 레벨2: 장바구니 | 결제 | 지하철노선도
                  - 레벨3: 
                  - 레벨4: 성능개선 | 배포
                  - 레벨5: 
                ############################################
                과정, 레벨, 미션을 선택하세요.
                ex) 백엔드, 레벨1, 자동차경주
                
                """.trimIndent()
        println(fetchingOption)

        val input = scanner.nextLine().trim()
        val inputData = validateAndSplitInput(input, 3)

        val course = Course.fromString(inputData[0])
        val level = Level.fromString(inputData[1])

        if (hasNoPairs(course, level)) {
            println("조건에 맞는 매칭 결과가 없습니다.")
            return
        }

        printMatchingResult(course, level)
    }

    private fun hasNoPairs(course: Course, level: Level): Boolean {
        return matchingHistory[course]?.containsKey(level) != true
    }

    private fun printMatchingResult(course: Course, level: Level) {
        println("페어 매칭 결과입니다.")
        matchingHistory[course]?.get(level)?.forEach { println(it) }
        println()
    }
}
