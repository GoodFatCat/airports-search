package com.github.goodfatcat;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;


public class ReaderFromResourcesImpl implements ReaderFromResources {
    @Override
    public Tuple getListFromFile(String fileName, int column) {
        Tuple res = null;

        if (column < 0)
            throw new IllegalArgumentException("Column must be greater than 0");

        int counter = 1;

        try (InputStream in = getClass().getResourceAsStream("/" + fileName)) {
            BufferedReader reader;
            if (in != null)
                reader = new BufferedReader(new InputStreamReader(in));
            else
                throw new NoSuchFileException(fileName);
            if (reader.ready()) {
                try {
                    String line = reader.readLine().split(",")[column - 1];

                    if (line.startsWith("\"")) {
                        res = new Tuple(new ArrayList<>(), false);
                        res.getList().add(new Airport<>(line, counter++));
                    } else {
                        res = new Tuple(new ArrayList<>(), true);
                        res.getList().add(new Airport<>(line, counter++));
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("No such column");
                    System.exit(-1);
                }
            } else {
                throw new RuntimeException("File is empty");
            }

            while (reader.ready()) {
                String line = reader.readLine();
                String[] columns = line.split(",");

                String neededColumn = null;
                try {
                    neededColumn = columns[column - 1];
                    neededColumn = neededColumn.replaceAll("\"", "");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("no such column");
                    System.exit(-1);
                }
                res.getList().add(new Airport<>(neededColumn, counter++));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return res;
    }

    @Override
    public String getLineFromFile(String fileName, int line) {
        BufferedReader reader;

        try (InputStream in = getClass().getResourceAsStream("/" + fileName)) {
            if (in != null)
                reader = new BufferedReader(new InputStreamReader(in));
            else
                throw new NoSuchFileException(fileName);
            return reader.lines().skip(line - 1).findFirst().orElse(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
