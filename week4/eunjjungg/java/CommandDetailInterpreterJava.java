import model_java.CommandDetailJava;
import model_java.CourseJava;
import model_java.LevelJava;

import java.util.List;
import java.util.stream.Collectors;

public class CommandDetailInterpreterJava extends BaseInterpreter<CommandDetailJava> {

    private final List<LevelJava> levels;
    private final List<CourseJava> courses;

    public CommandDetailInterpreterJava(List<LevelJava> levels, List<CourseJava> courses) {
        this.levels = levels;
        this.courses = courses;
    }

    @Override
    protected String createUxGuide() {
        return createUxGuideInternal(levels, courses);
    }

    @Override
    protected CommandDetailJava parser(String input) {
        String[] inputs = input.split(",");

        if (inputs.length < 2 || inputs.length > 3) return null;

        CourseJava course = CourseJava.parseFrom(inputs[0].trim());
        LevelJava level = LevelJava.parseFrom(inputs[1].trim());
        String activity = inputs.length > 2 ? inputs[2].trim() : null;

        if (course == null || level == null) return null;
        if (isValidActivity(level, activity)) {
            return new CommandDetailJava(level, course, activity);
        }

        return null;
    }

    private Boolean isValidActivity(LevelJava level, String activity) {
        return (level.getActivities().length == 0 && activity == null) ||
                (level.getActivities().length != 0 && level.isContainActivity(activity));

    }

    // region - internal support methods
    private String createUxGuideInternal(List<LevelJava> levels, List<CourseJava> courses) {
        String courseGuide = "과정: " + getCourseGuide(courses);
        String levelGuide = "미션: \n" + getLevelGuide(levels);
        String inputGuideLine = "과정, 레벨, 미션을 선택하세요.\nex) 백엔드, 레벨1, 자동차경주";
        String DIVIDER = "#############################################";
        return DIVIDER + "\n" + courseGuide + "\n" + levelGuide + "\n" + DIVIDER + "\n" + inputGuideLine;
    }

    private String getCourseGuide(List<CourseJava> courses) {
        return courses.stream()
                .map(CourseJava::getPrintingName) // 리플렉션 아직 어색해요..ㅎㅎ
                .collect(Collectors.joining(" | "));
    }

    private String getLevelGuide(List<LevelJava> levels) {
        return levels.stream()
                .map(level -> "\t- " + level.getPrintingName() + ": " + getActivityGuide(level.getActivities()))
                .collect(Collectors.joining("\n"));
    }

    private String getActivityGuide(String[] activities) {
        return String.join(" | ", activities);
    }
    // endregion
}