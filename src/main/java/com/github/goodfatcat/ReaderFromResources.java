package com.github.goodfatcat;

import java.util.List;

public interface ReaderFromResources {
    Tuple getListFromFile(String fileName, int column);

    List<String> getRequiredData(List<Airport<?>> list, String fileName);
}
