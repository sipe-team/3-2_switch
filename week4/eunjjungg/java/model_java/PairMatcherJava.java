package model_java;

import camp.nextstep.edu.missionutils.Randoms;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * 페어 매칭과 관련된 모든 로직을 담당한다.
 */
public class PairMatcherJava {

    private final int MAX_TRIAL_COUNT = 3;

    /**
     * 페어 매칭을 시도한다.
     * @param curLevelPairHistory history를 신경 써야할 pair 기록
     * @param crewList 매칭을 시도할 대상 크루원들
     * @return 매칭에 성공하였다면 그 결과물을 PairInfo의 list 형태로 반환한다.
     * @throws IllegalArgumentException 내부 룰에 의해 매칭이 불가능한 경우 호출.
     */
    @NotNull
    public List<PairInfoJava> tryMatch(
            List<List<PairInfoJava>> curLevelPairHistory,
            List<String> crewList
    ) {
        int trial = 0;

        while (trial < MAX_TRIAL_COUNT) {
            trial++;
            List<PairInfoJava> pairs = tryMatchInternal(curLevelPairHistory, crewList);
            if (pairs != null) return pairs;
        }

        throw new IllegalArgumentException("ERROR) ㅁㅅㅁ 페어 매칭 시도가 실패. . .");
    }

    private List<PairInfoJava> tryMatchInternal(
            List<List<PairInfoJava>> curLevelPairHistory,
            List<String> crewList
    ) {
        List<PairInfoJava> pair = getShuffledPairs(crewList);
        if (pair.size() == 0) return null;

        boolean isValid = isValidPairs(pair, curLevelPairHistory);
        return isValid ? pair : null;
    }

    private List<PairInfoJava> getShuffledPairs(List<String> crewList) {
        List<String> shuffledCrews = Randoms.shuffle(crewList);

        if (shuffledCrews.size() < 2) {
            throw new IllegalArgumentException("ERROR) ㅜㅅㅜ 인원 부족. 채워와...");
        }

        List<PairInfoJava> pairs = new ArrayList<>();
        for (int i = 0; i < shuffledCrews.size() / 2; i++) {
            String firstCrew = shuffledCrews.get(i * 2);
            String secondCrew = shuffledCrews.get(i * 2 + 1);
            String lastCrew = (i * 2 + 2 == shuffledCrews.size() - 1) ? shuffledCrews.get(shuffledCrews.size() - 1) : null;
            pairs.add(new PairInfoJava(firstCrew, secondCrew, lastCrew));
        }

        return pairs;
    }

    private boolean isValidPairs(List<PairInfoJava> newPairs, List<List<PairInfoJava>> pairHistory) {
        if (pairHistory.isEmpty()) return true;

        // todo: 이번주 안으로.. ^.^ 검증 로직까지 완료할게요 . . .
        return true;
    }
}
