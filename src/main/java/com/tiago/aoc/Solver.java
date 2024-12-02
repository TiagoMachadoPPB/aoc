package com.tiago.aoc;

import com.tiago.aoc.utils.InputReader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Solver {

    public static void solveAll() throws Exception{
        for (int day = 1; day <= 25; day++) {

                String className = String.format("com.tiago.aoc.days.Day%02d", day);
                Class<?> dayClass = Class.forName(className);
                Object instance = dayClass.getDeclaredConstructor().newInstance();
                List<String> input = InputReader.readInputAsList("day" + String.format("%02d", day) + ".txt");

                // Part 1
                System.out.printf("Day %02d%n", day);
                executePart(instance, input, 1);
                executePart(instance, input, 2);


        }
    }

    private static Object executePart(Object instance, List<?> input, int part) throws Exception {
        Method method = instance.getClass().getMethod("part" + part, List.class);

        long start = System.currentTimeMillis();
        Object invoke = method.invoke(instance, input);
        long end = System.currentTimeMillis();
        System.out.printf("Part %d: %2s -- Took %d ms %n", part, invoke, end - start);

        return invoke;
    }
}

