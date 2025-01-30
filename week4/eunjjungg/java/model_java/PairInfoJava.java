package model_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record PairInfoJava(
        String first,
        String second,
        String third
) {

    public List<String> getCrews() {
        ArrayList<String> crews = new ArrayList<String>();

        if (first != null) crews.add(first);
        if (second != null) crews.add(second);
        if (third != null) crews.add(third);

        return crews.stream().toList();
    }

    public Boolean containsCrew(String crewName) {
        return Arrays.asList(getCrews()).contains(crewName);
    }
}
