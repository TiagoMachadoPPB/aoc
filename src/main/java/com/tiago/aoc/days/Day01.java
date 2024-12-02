package com.tiago.aoc.days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day01 implements Day{

    List<String> left = new ArrayList<>();
    List<String> right = new ArrayList<>();

    @Override
    public int part1(List<String> strings) {
        for (String string : strings) {
            String[] split = string.split(" {3}");
            left.add(split[0]);
            right.add(split[1]);
        }

        Collections.sort(left);
        Collections.sort(right);

        int count = 0;

        for (int i = 0; i < left.size(); i++) {
            count += Math.abs(Integer.parseInt(left.get(i)) - Integer.parseInt(right.get(i)));
        }

        return count;
    }

    @Override
    public int part2(List<String> strings) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (String string : right) {
            frequency.put(Integer.parseInt(string), frequency.getOrDefault(Integer.parseInt(string), 0) + 1);
        }

        return left.stream()
                .mapToInt(Integer::parseInt)
                .map(i -> i * frequency.getOrDefault(i, 0))
                .sum();
    }
}
