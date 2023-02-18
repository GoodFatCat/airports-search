package com.github.goodfatcat;

public interface ReaderFromResources {
    Tuple getListFromFile(String fileName, int column);
    String getLineFromFile(String fileName, int line);
}
