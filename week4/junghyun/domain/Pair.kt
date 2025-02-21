package pairmatching.domain

class Pair {

    private val crews: MutableList<Crew> = mutableListOf()

    fun formPairsOfTwo(firstCrew: Crew, secondCrew: Crew) {
        crews.addAll(mutableListOf(firstCrew, secondCrew))
    }

    fun addThirdCrew(crew: Crew) {
        crews.add(crew)
    }

    override fun toString(): String {
        if (crews.size == 2) {
            return crews[0].name + " : " + crews[1].name
        }
        return crews[0].name + " : " + crews[1].name + " : " + crews[2].name
    }
}
