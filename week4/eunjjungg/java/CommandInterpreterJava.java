import model_java.CommandJava;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 유저의 Command 입력과 관련된 모든 sequence를 담당한다.
 */
public class CommandInterpreterJava extends BaseInterpreter<CommandJava> {
    private final List<CommandJava> commands;

    public CommandInterpreterJava(List<CommandJava> commands) {
        this.commands = commands;
    }

    @Override
    protected String createUxGuide() {
        return createUxGuideInternal(commands);
    }

    @Override
    protected CommandJava parser(String input) {
        return CommandJava.parseFrom(input);
    }

    // region - internal support methods
    private String createUxGuideInternal(List<CommandJava> commands) {
        String guide = "기능을 선택하세요.";
        String commandsString = commands.stream()
                .map(command -> command.getPrintingName() + "\n")
                .collect(Collectors.joining());

        return guide + "\n" + commandsString;
    }
    // endregion
}