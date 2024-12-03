package com.tiago.aoc.days;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 implements Day {
    @Override
    public int part1(List<String> strings) {
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        int count = 0;

        for (String string : strings) {
            Matcher matcher = pattern.matcher(string);

            while (matcher.find()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                count += x * y;
            }
        }

        return count;
    }

    @Override
    public int part2(List<String> strings) {
        Pattern mulPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Pattern doPattern = Pattern.compile("do\\(\\)");
        Pattern dontPattern = Pattern.compile("don't\\(\\)");

        boolean isMulEnabled = true;
        int count = 0;

        for (String line : strings) {
            Matcher mulMatcher = mulPattern.matcher(line);
            Matcher doMatcher = doPattern.matcher(line);
            Matcher dontMatcher = dontPattern.matcher(line);

            int currentIndex = 0;

            while (currentIndex < line.length()) {
                if (doMatcher.find(currentIndex) && doMatcher.start() == currentIndex) {
                    isMulEnabled = true;
                    currentIndex = doMatcher.end();
                } else if (dontMatcher.find(currentIndex) && dontMatcher.start() == currentIndex) {
                    isMulEnabled = false;
                    currentIndex = dontMatcher.end();
                } else if (mulMatcher.find(currentIndex) && mulMatcher.start() == currentIndex) {
                    if (isMulEnabled) {
                        int x = Integer.parseInt(mulMatcher.group(1));
                        int y = Integer.parseInt(mulMatcher.group(2));
                        count += x * y;
                    }
                    currentIndex = mulMatcher.end();
                } else {
                    currentIndex++;
                }
            }
        }

        return count;
    }
}



