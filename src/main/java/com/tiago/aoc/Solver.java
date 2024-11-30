package com.tiago.aoc;

import com.tiago.aoc.utils.InputReader;

import java.lang.reflect.Method;
import java.util.List;

public class Solver {
    public static void solveAll() {
        for (int day = 1; day <= 25; day++) {
            try {
                String className = String.format("com.tiago.aoc.days.Day%02d", day);
                Class<?> dayClass = Class.forName(className);
                Object instance = dayClass.getDeclaredConstructor().newInstance();

                Method part1 = dayClass.getMethod("solvePart1", List.class);
                Method part2 = dayClass.getMethod("solvePart2", List.class);

                List<String> input = InputReader.readInputAsList("day" + String.format("%02d", day) + ".txt");
                System.out.printf("Day %02d - Part 1: %s%n", day, part1.invoke(instance, input));
                System.out.printf("Day %02d - Part 2: %s%n", day, part2.invoke(instance, input));
            } catch (Exception e) {
                System.out.printf("Error solving day %02d: %s%n", day, e.getMessage());
            }
        }
    }
}

