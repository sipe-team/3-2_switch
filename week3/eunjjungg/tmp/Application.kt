package tmp

import tmp.model.Command
import tmp.model.CommandDetail
import tmp.model.Course
import tmp.model.Level
import java.lang.Exception
import java.lang.IllegalArgumentException

/**
 * Application level class...
 * 정확히 Application이 어떤 느낌으로 만들어야되는지는 모름.
 * 하지만,, 일단 Application이 최상위 객체라는 느낌으로.. 구현함..
 */
class Application {

    // region - constant values
    companion object {
        private val commands = listOf(Command.PAIR_MATCH, Command.PAIR_SHOW, Command.PAIR_INIT, Command.QUIT)
        private val levels = listOf(Level.LEVEL1, Level.LEVEL2, Level.LEVEL3, Level.LEVEL4, Level.LEVEL5)
        private val courses = listOf(Course.BACKEND, Course.ANDROID)
    }
    // endregion

    // region - instance variables
    // interpreter
    private val commandInterpreter: Interpreter<Command> = CommandInterpreter(commands)
    private val detailInterpreter: Interpreter<CommandDetail> = CommandDetailInterpreter(levels, courses)
    // cur commands
    private var curCommand: Command? = null
    private var curCommandDetail: CommandDetail? = null
    // flag
    private var isQuit = false
    // endregion

    // region - public interface
    fun execute() {
        while (isQuit.not()) {
            executeInternal()
        }
    }
    // endregion

    // region - internal support methods
    private fun executeInternal() {
        try {
            val command = checkCommand()
            executeCommand(command, getDetail = { checkDetail() })
            finishCommand()
        } catch (e: Exception) {
            handleException(e)
        }
    }

    private fun finishCommand() {
        // command 처리 완료 -> data initialize
        curCommand = null
        curCommandDetail = null
    }

    private fun handleException(e: Exception) {
        println(e.message)
    }

    private fun checkCommand(): Command {
        // command가 없다면 일단 command input을 받는 sequence를 시작한다.
        curCommand ?: run { curCommand = commandInterpreter.startSequence() }
        // 그럼에도 불구하고, command 생성에 실패했다면 예외 발생
        return curCommand ?: throw IllegalArgumentException("ERROR) ㅋㅅㅋ")
    }

    private fun checkDetail(): CommandDetail {
        // detail이 null이라면, detail을 입력 받는 sequence를 시작한다.
        curCommandDetail ?: run { curCommandDetail = detailInterpreter.startSequence() }
        // sequence 실패 시 예외 발생
        return curCommandDetail ?: throw IllegalArgumentException("ERROR) ㅇㅅㅇ")
    }
    // endregion

    // region - internal support methods - for execute
    private fun executeCommand(command: Command, getDetail: () -> CommandDetail) {
        when (command) {
            Command.PAIR_MATCH -> { matchPairs(getDetail.invoke()) }
            Command.PAIR_SHOW -> { showPairs(getDetail.invoke()) }
            Command.PAIR_INIT -> { clearPairs() }
            Command.QUIT -> { quit() }
        }
    }

    private fun matchPairs(detail: CommandDetail) { /* not implemented . . . */ }

    private fun showPairs(detail: CommandDetail) { /* not implemented . . . */ }

    private fun clearPairs() { /* not implemented . . . */ }

    private fun quit() { isQuit = true }
    // endregion
}
