package pairmatching.service

import pairmatching.domain.Course
import pairmatching.domain.Level
import pairmatching.domain.Pair

class PairResetService(
    private val matchingHistory: MutableMap<Course, MutableMap<Level, MutableSet<Pair>>>
) {

    fun resetPairMatchingData() {
        matchingHistory.clear()
        println("초기화 되었습니다.")
    }
}
