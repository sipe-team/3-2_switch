package tmp

import tmp.model.Command
import tmp.model.CommandDetail
import tmp.model.Course
import tmp.model.Level

/**
 * 유저 입력을 개발에서 사용하는 타입으로 변환하는, interpreter 개념으로 생성한 객체.
 * - 유저에게 guide를 제공하고,
 * - 유저에게 입력을 받고,
 * - 그 입력을 [T] 타입으로 parsing한다.
 *
 * used template method pattern . . .
 * 굳이 따지자면 mvc에서 view role...
 */
abstract class Interpreter<T> {
    protected val DIVIDER = "#############################################"

    /**
     * start interpreting
     */
    fun startSequence(): T? {
        println(createUxGuide())
        val userInput = readUserInput()
        return parser(userInput)
    }

    /**
     * @return T 타입을 입력 받기 위한 guide string을 반환한다.
     */
    abstract fun createUxGuide(): String

    /**
     * @return user의 입력을 받아, string으로 반환한다.
     */
    protected open fun readUserInput(): String { return readln() }

    /**
     * @return input string을 [T]로 parsing이 가능한 경우, [T]를 반환하고, 불가하다면 null 반환.
     */
    protected abstract fun parser(input: String): T?
}

/**
 * 유저의 [Command] 입력과 관련된 모든 sequence를 담당한다.
 */
class CommandInterpreter(
    private val commands: List<Command>
) : Interpreter<Command>() {

    override fun createUxGuide(): String { return createUxGuideInternal(commands) }

    override fun parser(input: String): Command? { return Command.parseFrom(input)
    }

    // region - internal support methods
    private fun createUxGuideInternal(commands: List<Command>): String {
        val guide = "기능을 선택하세요."
        val commandsString: String = commands.joinToString(separator = "\n") { it.printingName }
        return "$guide\n$commandsString"
    }
    // endregion
}

/**
 * 유저의 [CommandDetail] 입력과 관련된 모든 sequence를 담당한다.
 */
class CommandDetailInterpreter(
    private val levels: List<Level>,
    private val courses: List<Course>,
) : Interpreter<CommandDetail>() {

    override fun createUxGuide(): String { return createUxGuideInternal(levels, courses) }

    override fun parser(input: String): CommandDetail? {
        val userInputs = input.split(",").map { it.trim() }

        if (userInputs.size < 2 || userInputs.size > 3) return null

        val course = Course.parseFrom(input = userInputs[0]) ?: return null
        val level = Level.parseFrom(input = userInputs[1]) ?: return null
        val userInputActivity = userInputs.getOrNull(2)

        return if (isValidActivity(level, userInputActivity)) {
            CommandDetail(course, level, userInputActivity)
        } else null
    }

    // region - internal support methods
    private fun createUxGuideInternal(levels: List<Level>, courses: List<Course>): String {
        val courseGuide = "과정: ${getCourseGuide(courses, divider = " | ")}"
        val levelGuide = "미션: \n${getLevelGuide(levels, prefix = "\t- ", activityDivider = " | ")}"
        val inputGuideLine = "과정, 레벨, 미션을 선택하세요.\nex) 백엔드, 레벨1, 자동차경주"
        return "$DIVIDER\n$courseGuide\n$levelGuide\n$DIVIDER\n$inputGuideLine"
    }

    private fun getCourseGuide(courses: List<Course>, divider: String): String {
        return courses.joinToString(separator = divider) { it.printingName }
    }

    private fun getLevelGuide(levels: List<Level>, prefix: String, activityDivider: String): String {
        return levels.joinToString(separator = "\n") {
            "$prefix ${it.printingName}: ${it.activities.joinToString(separator = activityDivider)}"
        }
    }

    private fun isValidActivity(level: Level, activity: String?): Boolean {
        return (level.activities.isEmpty() && activity == null) ||
                (level.activities.isNotEmpty() && activity != null && level.isContainActivity(activity))
    }
    //endregion
}