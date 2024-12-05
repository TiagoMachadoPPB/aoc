package com.tiago.aoc.days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day05 implements Day {

    Map<String, String> rightOptions = new HashMap<>();
    List<String[]> pages = new ArrayList<>();

    @Override
    public int part1(List<String> strings) {

        for (String line : strings) {
            if (line.contains("|")) {
                String[] split = line.split("\\|");
                String left = split[0];
                String right = split[1];
                String currentOptions = rightOptions.getOrDefault(split[0], "");
                rightOptions.put(left, currentOptions.concat(right + ","));
            } else {
                pages.add(line.split(","));
            }
        }
        pages.remove(0); // emptyLine

        int count = 0;
        for (String[] page : pages) {
            boolean isValid = true;
            for (int i = 0; i < page.length - 1 && isValid; i++) {
                int j = i + 1;
                String validOptions = rightOptions.getOrDefault(page[i], "");
                while (j < page.length && isValid) {
                    if (!validOptions.contains(page[j])) {
                        isValid = false;
                    }
                    j++;
                }
            }
            if (isValid) {
                count += Integer.parseInt(page[page.length / 2]);
            }
        }
        return count;
    }

    @Override
    public int part2(List<String> strings) {
        int count = 0;
        for (String[] page : pages) {
            boolean isValid = true;
            for (int i = 0; i < page.length - 1 && isValid; i++) {
                int j = i + 1;
                String validOptions = rightOptions.getOrDefault(page[i], "");
                while (j < page.length && isValid) {
                    if (!validOptions.contains(page[j])) {
                        isValid = false;
                    }
                    j++;
                }
            }
            if (!isValid) {
                count += fixOrder(page);
            }
        }
        return count;
    }

    private int fixOrder(String[] page) {
        int length = page.length;
        String[] ordered = new String[length];

        for (String number : page) {
            int validOptionsCount = 0;
            int j = 0;
            String validOptions = rightOptions.getOrDefault(number, "");
            while (j < length) {
                if (validOptions.contains(page[j])) validOptionsCount++;
                j++;
            }
            ordered[length - validOptionsCount - 1] = number;
        }

        return Integer.parseInt(ordered[length / 2]);
    }
}
