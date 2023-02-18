package com.github.goodfatcat;

import java.util.ArrayList;
import java.util.List;

public class Tuple {
    private List<Airport<?>> list;
    private final boolean isNumbers;

    public Tuple(ArrayList<Airport<?>> list, boolean isNumbers) {
        this.list = list;
        this.isNumbers = isNumbers;
    }

    public List<Airport<?>> getList() {
        return list;
    }

    public boolean isNumbers() {
        return isNumbers;
    }
}
