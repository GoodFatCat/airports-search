package com.github.goodfatcat;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0)
            throw new IllegalArgumentException("Select column number");

        ReaderFromResources reader = new ReaderFromResourcesImpl();
        Finder finder = new FinderImpl();
        Scanner scanner = new Scanner(System.in);

        Tuple tuple = reader.getListFromFile("airports.csv", Integer.parseInt(args[0]));

        tuple.getList().sort((o1, o2) -> {
            String s1 = (String) o1.getValue();
            String s2 = (String) o2.getValue();
            return s1.compareTo(s2);
        });

        while (true) {
            System.out.println("Введите строку:");

            String scannedLine = scanner.nextLine();

            if (scannedLine.equals("!quit")) {
                break;
            }

            long time = System.currentTimeMillis();

            List<String> res = finder.find(scannedLine, tuple, reader, "airports.csv");

            for (String line : res) {
                System.out.println(line);
            }
            if (!res.isEmpty()) {
                String format = "Количество найденых строк: %d затрачено на поиск: %d ms \n";
                System.out.printf(format, res.size(), System.currentTimeMillis() - time);
            } else {
                System.out.println("Не найдено такой строки");
            }
        }
    }
}
