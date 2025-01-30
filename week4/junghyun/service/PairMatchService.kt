package pairmatching.service

import pairmatching.domain.Course
import pairmatching.domain.Crew
import pairmatching.domain.Level
import pairmatching.domain.Pair
import pairmatching.utils.FileReaderUtils.readMarkdownFile
import pairmatching.utils.InputValidationUtils.validateAndSplitInput
import pairmatching.utils.InputValidationUtils.validateInput
import java.util.*

class PairMatchService(
    private val scanner: Scanner,
    private val matchingHistory: MutableMap<Course, MutableMap<Level, MutableSet<Pair>>>
) {
    companion object {
        private const val BACKEND_CREW_FILE_NAME = "backend-crew"
        private const val FRONTEND_CREW_FILE_NAME = "frontend-crew"
    }

    private val backendCrewNames = readMarkdownFile(BACKEND_CREW_FILE_NAME)
    private val frontendCrewNames = readMarkdownFile(FRONTEND_CREW_FILE_NAME)
    private val crewList = mutableListOf<Crew>()

    fun matchPair() {
        initCrewData()

        val matchingOption = """
                #############################################
                과정: 백엔드 | 프론트엔드
                미션:
                  - 레벨1: 자동차경주 | 로또 | 숫자야구게임
                  - 레벨2: 장바구니 | 결제 | 지하철노선도
                  - 레벨3: 
                  - 레벨4: 성능개선 | 배포
                  - 레벨5: 
                ############################################
                """.trimIndent()
        println(matchingOption)

        while (true) {
            println("과정, 레벨, 미션을 선택하세요.")
            println("ex) 백엔드, 레벨1, 자동차경주")
            println()

            var input = scanner.nextLine().trim()
            val inputData = validateAndSplitInput(input, 3)

            val course = Course.fromString(inputData[0])
            val level = Level.fromString(inputData[1])

            //매칭 정보가 있는지 여부 파악
            if (hasExistingPairs(course, level)) {
                println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?")
                println("네 | 아니오")

                input = scanner.nextLine().trim()
                validateInput(input)

                if (input.equals("아니요", ignoreCase = true)) {
                    continue
                }
            }

            //매칭 정보가 있어도 다시 매칭하는 경우 or 매칭 정보가 없는 경우
            createPairs(course, level)
            printMatchingResult(course, level)
            break
        }

    }

    private fun initCrewData() {
        if (crewList.isEmpty()) {
            createAndAddCrew(Course.BACKEND, backendCrewNames)
            createAndAddCrew(Course.FRONTEND, frontendCrewNames)
        }
    }

    private fun createAndAddCrew(course: Course, crewNames: List<String>) {
        crewNames.forEach { crewList.add(Crew(course, it)) }
    }

    /**
     * kotlin 에서는 대괄호를 사용해서 value 를 가져올 수 있다.
     */
    private fun hasExistingPairs(course: Course, level: Level): Boolean {
        return matchingHistory[course]?.containsKey(level) ?: false
    }

    private fun createPairs(course: Course, level: Level) {
        val previousPairs = getPreviousPairs(course, level)
        val crewListForCourse = crewList.filter { it.course == course }.toMutableList()

        repeat(3) {
            crewListForCourse.shuffle()

            val pairs = generateNewPairs(previousPairs, crewListForCourse)
            if (pairs.isNotEmpty()) {
                matchingHistory.getOrPut(course) { mutableMapOf() }
                    .getOrPut(level) { mutableSetOf() }
                    .addAll(pairs)
                return
            }
        }

        throw IllegalStateException("매칭 경우의 수가 부족합니다.")
    }

    private fun getPreviousPairs(course: Course, level: Level): Set<Pair> {
        return matchingHistory[course]?.get(level).orEmpty()
    }

    private fun generateNewPairs(previousPairs: Set<Pair>, crewListForCourse: List<Crew>): MutableSet<Pair> {
        val pairs = mutableSetOf<Pair>()
        var i = 0

        while (i < crewListForCourse.size - 1) {
            val pair = Pair()
            pair.formPairsOfTwo(crewListForCourse[i], crewListForCourse[i + 1])
            i += 2

            //3명씩 짝 짓는 경우
            if (i == crewListForCourse.size - 1) {
                pair.addThirdCrew(crewListForCourse[i])
                i++
            }

            if (previousPairs.isNotEmpty() && previousPairs.contains(pair)) {
                return mutableSetOf()
            }

            pairs.add(pair)
        }

        return pairs
    }

    private fun printMatchingResult(course: Course, level: Level) {
        println("페어 매칭 결과입니다.")
        matchingHistory[course]?.get(level)?.forEach { println(it) }
        println()
    }
}
