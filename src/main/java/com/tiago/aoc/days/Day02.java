package com.tiago.aoc.days;

import java.util.Arrays;
import java.util.List;

public class Day02 implements Day {

    @Override
    public int part1(List<String> strings) {
        int validReports = 0;

        for (String report : strings) {
            String[] levels = report.split(" ");
            int[] numbers = Arrays.stream(levels).mapToInt(Integer::parseInt).toArray();

            if (isSafe(numbers)) {
                validReports++;
            }
        }
        return validReports;
    }

    @Override
    public int part2(List<String> strings) {
        int validReports = 0;

        for (String report : strings) {
            String[] levels = report.split(" ");
            int[] numbers = Arrays.stream(levels).mapToInt(Integer::parseInt).toArray();

            if (isSafe(numbers)) {
                validReports++;
            } else {
                for (int i = 0; i < numbers.length; i++) {
                    int[] modifiedReport = removeElement(numbers, i);
                    if (isSafe(modifiedReport)) {
                        validReports++;
                        break;
                    }
                }
            }
        }

        return validReports;
    }

    private boolean isSafe(int[] numbers) {
        if (numbers.length < 2) return true;

        boolean ascending = numbers[1] > numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            int diff = numbers[i] - numbers[i - 1];

            if (Math.abs(diff) >= 4 || diff == 0 || (numbers[i] > numbers[i - 1] != ascending)) {
                return false;
            }
        }
        return true;
    }

    private int[] removeElement(int[] numbers, int index) {
        int[] result = new int[numbers.length - 1];
        for (int i = 0, j = 0; i < numbers.length; i++) {
            if (i != index) {
                result[j++] = numbers[i];
            }
        }
        return result;
    }
}
