package com.github.goodfatcat;

import java.util.List;

public interface Finder {
    List<String> find(String required, Tuple tuple, ReaderFromResources reader, String fileName);
}
