package model_java;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public enum LevelJava {
    LEVEL1("레벨1", new String[]{"자동차경주", "로또", "숫자야구게임"}),
    LEVEL2("레벨2", new String[]{"장바구니", "결제", "지하철노선도"}),
    LEVEL3("레벨3", new String[]{}),
    LEVEL4("레벨4", new String[]{"성능개선", "배포"}),
    LEVEL5("레벨5", new String[]{});

    private final String inputCode;
    private final String printingName;
    private final String[] activities;

    LevelJava(String inputCode, String[] activities) {
        this.inputCode = inputCode;
        this.printingName = this.inputCode;
        this.activities = activities;
    }

    public String getInputCode() {
        return this.inputCode;
    }

    public String getPrintingName() {
        return this.printingName;
    }

    public String[] getActivities() {
        return this.activities;
    }

    public Boolean isContainActivity(String input) {
        return Arrays.asList(this.activities).contains(input);
    }

    @Nullable
    public static LevelJava parseFrom(String input) {
        for (LevelJava level : LevelJava.values()) {
            if (level.inputCode.equals(input)) return level;
        }
        return null;
    }
}
