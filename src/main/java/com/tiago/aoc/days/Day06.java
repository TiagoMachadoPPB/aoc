package com.tiago.aoc.days;

import java.util.HashSet;
import java.util.List;

public class Day06 implements Day {
    int lines;
    int cols;
    char[][] grid;
    int[] start;

    @Override
    public int part1(List<String> strings) {
        lines = strings.size();
        cols = strings.get(0).length();
        grid = new char[lines][cols];

        int index = 0;
        for (String line : strings) {
            grid[index] = line.toCharArray();
            index++;
        }

        char police = '^';
        start = getResult(police);

        return moveIt(grid, start[0], start[1], police);
    }

    private int[] getResult(char police) {
        int startingI = 0;
        int startingJ = 0;
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == police) {
                    startingI = i;
                    startingJ = j;
                }
            }
        }
        return new int[]{startingI, startingJ};
    }

    private int moveIt(char[][] grid, int i, int j, char police) {
        var isOut = false;
        int count = 0;

        while (!isOut) {
            int[] direction = getDirection(police);
            int newI = i + direction[0];
            int newJ = j + direction[1];
            if (validateBounds(newI, newJ)) {
                var newChar = grid[newI][newJ];

                if (newChar == '#') {
                    police = turnRight(police);
                } else {
                    if (newChar == '.' || newChar == '^') { // is unique
                        count++;
                        grid[newI][newJ] = '?';
                    }
                    i = newI;
                    j = newJ;
                }
            } else {
                isOut = true;
            }
        }

        return count;
    }

    private char turnRight(char c) {
        return switch (c) {
            case '^' -> '>';
            case '>' -> 'v';
            case 'v' -> '<';
            case '<' -> '^';
            default -> throw new IllegalArgumentException("Invalid direction: " + c);
        };
    }

    private boolean validateBounds(int newI, int newJ) {
        return newI >= 0 && newI < lines && newJ >= 0 && newJ < cols;
    }

    private int[] getDirection(char c) {
        return switch (c) {
            case '^' -> new int[]{-1, 0};
            case 'v' -> new int[]{1, 0};
            case '<' -> new int[]{0, -1};
            case '>' -> new int[]{0, 1};
            default -> throw new IllegalArgumentException("Invalid direction: " + c);
        };
    }

    @Override
    public int part2(List<String> strings) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                char current = grid[i][j];
                if (current != '#' && current != '^') {
                    grid[i][j] = '#';
                    if (isInLoop(grid)) {
                        count++;
                    }
                    grid[i][j] = current;
                }
            }
        }

        return count;
    }

    private boolean isInLoop(char[][] grid) {
        char police = '^';

        Position current = new Position(start[0], start[1], '^');
        HashSet<Position> positions = new HashSet<>();
        positions.add(current);
        boolean isOut = false;
        boolean isInLoop = false;

        while (!isOut && !isInLoop) {

            int[] direction = getDirection(police);
            int newI = current.i + direction[0];
            int newJ = current.j + direction[1];
            if (validateBounds(newI, newJ)) {
                var newChar = grid[newI][newJ];

                if (newChar == '#') {
                    police = turnRight(police);
                } else {
                    current = new Position(newI, newJ, police);

                    if (positions.contains(current)) {
                        isInLoop = true;
                    }
                    positions.add(current);
                }
            } else {
                isOut = true;
            }
        }
        return isInLoop;
    }

    private record Position(int i, int j, char direction) {
    }
}
