package com.github.goodfatcat;

import java.util.ArrayList;
import java.util.List;

public class FinderImpl implements Finder {
    @Override
    public List<String> find(String required, Tuple tuple, ReaderFromResources reader) {
        int jumpStep = (int) Math.sqrt(tuple.getList().size());
        int previousStep = 0;

        List<Airport<?>> sortedList = tuple.getList();

        while (required.compareTo(sortedList.get(
                        Math.min(jumpStep, sortedList.size() - 1)).toString().
                substring(0, required.length())) >= 0) {
            if (required.compareTo(sortedList.get(
                            Math.min(jumpStep, sortedList.size() - 1)).toString().
                    substring(0, required.length())) != 0) {
                previousStep = jumpStep;
            }
            jumpStep += jumpStep;

            if (previousStep >= sortedList.size()) {
                return null;
            }
        }

        List<String> res = new ArrayList<>();

        for (int i = previousStep; i < Math.min(jumpStep, sortedList.size()); i++) {
            Airport<?> airport = sortedList.get(i);
            String line = airport.toString();
            if (line.startsWith(required)) {
                res.add(line + "[" + reader.getLineFromFile("airports.csv", airport.getId())
                        + "]");
            }
        }
        if (tuple.isNumbers()){
            res.sort((o1, o2) -> {
                double d1 = Double.parseDouble(o1.split("\\[")[0]);
                double d2 = Double.parseDouble(o2.split("\\[")[0]);
                return Double.compare(d1, d2);
            });
        }
        return res;
    }
}
