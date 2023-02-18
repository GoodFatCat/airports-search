package com.github.goodfatcat;

import java.util.ArrayList;
import java.util.List;

public class FinderImpl implements Finder {
    @Override
    public List<String> find(String required, Tuple tuple, ReaderFromResources reader, String fileName) {
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

        List<String> res;
        List<Airport<?>> list = new ArrayList<>();

        for (int i = previousStep; i < Math.min(jumpStep, sortedList.size()); i++) {
            Airport<?> airport = sortedList.get(i);
            String line = airport.toString();
            if (line.startsWith(required)) {
                list.add(airport);
            }
        }

        res = reader.getRequiredData(list, fileName);

        if (tuple.isNumbers()){
            res.sort((o1, o2) -> {
                double d1 = Double.parseDouble(o1.split("\\[")[0]);
                double d2 = Double.parseDouble(o2.split("\\[")[0]);
                return Double.compare(d1, d2);
            });
        } else {
            res.sort((o1, o2) -> {
                String s1 = o1.split("\\[")[0];
                String s2 = o2.split("\\[")[0];
                return s1.compareTo(s2);
            });
        }
        return res;
    }
}
