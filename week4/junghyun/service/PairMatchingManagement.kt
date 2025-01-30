package pairmatching.service

import pairmatching.domain.Course
import pairmatching.domain.Level
import pairmatching.domain.Pair
import java.util.*

class PairMatchingManagement {
    private val scanner: Scanner = Scanner(System.`in`)
    private val matchingHistory: MutableMap<Course, MutableMap<Level, MutableSet<Pair>>> = mutableMapOf()

    private val pairMatchService: PairMatchService = PairMatchService(scanner, matchingHistory)
    private val pairSelectService: PairSelectService = PairSelectService(scanner, matchingHistory)
    private val pairResetService: PairResetService = PairResetService(matchingHistory)

    fun run() {
        val programOption = """
            기능을 선택하세요.
            1. 페어 매칭
            2. 페어 조회
            3. 페어 초기화
            Q. 종료
        """.trimIndent()

        while (true) {
            println(programOption)
            println("입력 : ")

            when (scanner.nextLine().trim()) {
                "1" -> pairMatchService.matchPair()
                "2" -> pairSelectService.getPairMatchingResult()
                "3" -> pairResetService.resetPairMatchingData()
                "Q", "q" -> {
                    println("프로그램을 종료합니다.")
                    scanner.close()
                    return
                }

                else -> throw IllegalArgumentException("잘못된 입력값 입니다.")
            }
        }
    }
}
