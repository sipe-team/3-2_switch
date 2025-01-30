import model_java.*;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ApplicationJava {

    private static final List<String> backendCrew = Arrays.asList("jh1", "jh2", "jh3", "jh4", "jh5", "jh6", "jh7", "jh8", "jh9");
    private static final List<String> androidCrew = Arrays.asList("ej1", "ej2", "ej3", "ej4", "ej5", "ej6");

    private final List<CommandJava> commands = Arrays.asList(CommandJava.PAIR_MATCH, CommandJava.PAIR_SHOW, CommandJava.PAIR_INIT, CommandJava.QUIT);
    private final List<LevelJava> levels = Arrays.asList(LevelJava.LEVEL1, LevelJava.LEVEL2, LevelJava.LEVEL3, LevelJava.LEVEL4, LevelJava.LEVEL5);
    private final List<CourseJava> courses = Arrays.asList(CourseJava.BACKEND, CourseJava.ANDROID);

    private BaseInterpreter<CommandJava> commandInterpreter = new CommandInterpreterJava(commands);
    private BaseInterpreter<CommandDetailJava> detailInterpreter = new CommandDetailInterpreterJava(levels, courses);

    private PairMatcherJava pairMatcher = new PairMatcherJava();

    @Nullable
    private CommandJava curCommand = null;
    @Nullable
    private CommandDetailJava curCommandDetail = null;

    private Boolean isQuit = false;
    private MatchingHistoryJava pairHistory = new MatchingHistoryJava();

    public static void main(String[] args) {
        new ApplicationJava().execute();
    }

    public void execute() {
        while (!isQuit) {
            executeInternal();
        }

        // on destroy
        pairHistory = null;
        pairMatcher = null;
        commandInterpreter = null;
        detailInterpreter = null;;
    }

    private void executeInternal() {
        try {
            CommandJava command = checkCommand();
            executeCommand(command);
            resetCommand();
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void resetCommand() {
        curCommand = null;
        curCommandDetail = null;
    }

    private void handleException(Exception e) {
        System.out.println(e.getMessage());
    }

    private CommandJava checkCommand() {
        if (curCommand != null) return curCommand;

        curCommand = commandInterpreter.startSequence();
        if (curCommand == null) { throw new IllegalArgumentException("ERROR) ㅋㅅㅋ 커맨드 값 제대로 입력 ㄱ ㄱ"); }
        return curCommand;
    }

    private CommandDetailJava checkDetail() {
        if (curCommandDetail != null) return curCommandDetail;

        curCommandDetail = detailInterpreter.startSequence();
        if (curCommandDetail == null) { throw new IllegalArgumentException("ERROR) ㅇㅅㅇ 디테일 값 제대로 입력 ㄱ ㄱ"); }
        return curCommandDetail;
    }

    private void executeCommand(CommandJava command) {
        switch (command) {
            case PAIR_MATCH -> matchPairs(checkDetail());
            case PAIR_SHOW -> showPairs(checkDetail());
            case PAIR_INIT -> clearPairs();
            case QUIT -> quit();
        }
    }

    private void matchPairs(CommandDetailJava detail) {
        boolean isPairHistoryExist = pairHistory.isPairHistoryExist(detail);
        if (isPairHistoryExist && !askRematchPair()) { return; }

        List<String> crews = getCrewsByCourse(detail.course());
        List<List<PairInfoJava>> pairsWithSameLevel = pairHistory.getPairHistoryByLevel(detail.level());

        try {
            List<PairInfoJava> result = pairMatcher.tryMatch(pairsWithSameLevel, crews);
            pairHistory.insertPairInfo(detail, result);
            showPairs(detail);
        } catch (Exception e) {
            resetCommand(); // 매치에 실패한 경우 제일 초기로 돌아가야 함. 그렇지 않으면 계속 실패할 수 있음.
            throw e;
        }
    }

    private boolean askRematchPair() {
        System.out.println("매칭 정화보가 있습니다. 다시 매칭하시겠습니까?\n네 | 아니오");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine().trim();
        return answer.equals("네");
    }

    private List<String> getCrewsByCourse(CourseJava course) {
        return switch (course) {
            case BACKEND -> backendCrew;
            case ANDROID -> androidCrew;
        };
    }

    private void showPairs(CommandDetailJava detail) {
        List<PairInfoJava> history = pairHistory.getPairHistory(detail);
        if (history == null) {
            resetCommand(); // 기록이 없는 경우 제일 초기로 돌아가야 함.
            throw new IllegalArgumentException("ERROR) -ㅅ- 입력값과 일치하는 페어 매칭 기록이 없삼 . . .");
        }

        System.out.println("페어 매칭 결과입니다.");
        for (PairInfoJava pair : history) {
            List<String> crews = pair.getCrews();
            String printString = String.join(" : ", crews);
            System.out.println(printString);
        }
    }

    private void clearPairs() {
        pairHistory.clearHistory();
        System.out.println("초기화 되었습니다 ! ! !");
    }

    private void quit() {
        isQuit = true;
    }
}
