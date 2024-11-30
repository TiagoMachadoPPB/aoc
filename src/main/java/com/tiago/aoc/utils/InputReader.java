package com.tiago.aoc.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class InputReader {
    public static List<String> readInputAsList(String fileName) {
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/inputs/" + fileName))) {
            return lines.toList();
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }

    public static String[] readInputAsArray(String fileName) {
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/inputs/" + fileName))) {
            return lines.toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }
}

