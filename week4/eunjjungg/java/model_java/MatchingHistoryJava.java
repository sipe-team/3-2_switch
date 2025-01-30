package model_java;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MatchingHistoryJava {
    private Map<CommandDetailJava, List<PairInfoJava>> pairCache = new HashMap<>();

    public void insertPairInfo(CommandDetailJava commandDetail, List<PairInfoJava> pairs) {
        this.pairCache.put(commandDetail, pairs);
    }

    public Boolean deletePairInfo(CommandDetailJava commandDetail) {
        Boolean isSuccess = this.pairCache.remove(commandDetail) != null;
        return isSuccess;
    }

    @Nullable
    public List<PairInfoJava> getPairHistory(CommandDetailJava commandDetail) {
        return pairCache.get(commandDetail);
    }

    public List<List<PairInfoJava>> getPairHistoryByLevel(LevelJava level) {
        // 1. 레벨과 일치하는 걸 캐시에서 찾는다. (스트림으로 찾음)
        Stream<CommandDetailJava> keys = pairCache.keySet().stream().filter(detail -> detail.level() == level);
        // 2. map 사용해서 일치하는 PairInfo 리턴
        List<List<PairInfoJava>> history = keys.map(key -> pairCache.get(key)).toList();
        return history;
    }

    public Boolean isPairHistoryExist(CommandDetailJava commandDetail) {
        return this.pairCache.get(commandDetail) != null;
    }

    public void clearHistory() {
        this.pairCache.clear();
    }
}