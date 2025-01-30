package model_java;

import org.jetbrains.annotations.Nullable;

public enum CommandJava {
    PAIR_MATCH("1", "1. 페어 매칭"),
    PAIR_SHOW("2", "2. 페어 조회"),
    PAIR_INIT("3", "3. 페어 초기화"),
    QUIT("Q", "Q. 종료");

    private final String inputCode;
    private final String printingName;

    CommandJava(String inputCode, String printingName) {
        this.inputCode = inputCode;
        this.printingName = printingName;
    }

    public String getInputCode() {
        return this.inputCode;
    }

    public String getPrintingName() {
        return this.printingName;
    }

    @Nullable
    public static CommandJava parseFrom(String inputCode) {
        for (CommandJava command : CommandJava.values()) {
            if (command.inputCode.equals(inputCode)) return command;
        }

        return null;
    }
}
