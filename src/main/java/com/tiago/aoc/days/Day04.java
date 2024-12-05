package com.tiago.aoc.days;

import java.util.List;

public class Day04 implements Day {
    char[][] matrix;
    int[][] directions = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
    };

    @Override
    public int part1(List<String> strings) {
        int rows = strings.get(0).length();
        int columns = strings.size();
        matrix = new char[rows][columns];

        int lineIndex = 0;
        for (String line : strings) {
            matrix[lineIndex] = line.toCharArray();
            lineIndex++;
        }

        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                for (int[] dir : directions) {
                    if (checkWord(matrix, i, j, dir)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private boolean checkWord(char[][] grid, int row, int column, int[] direction) {
        int rows = grid.length;
        int columns = grid[0].length;
        String word = "XMAS";

        // Check each character of the word
        for (int k = 0; k < word.length(); k++) {
            int newRow = row + k * direction[0];
            int newColumn = column + k * direction[1];

            // Check if the position is out of bounds or does not match
            if (newRow < 0 || newRow >= rows || newColumn < 0 || newColumn >= columns || grid[newRow][newColumn] != word.charAt(k)) {
                return false;
            }
        }

        return true; // Word is found
    }

    @Override
    public int part2(List<String> strings) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int count = 0;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (isXMASPattern(matrix, i, j)) {
                    count++;
                }
            }
        }

        return count;
    }


    private boolean isXMASPattern(char[][] grid, int row, int col) {
        return  grid[row][col] == 'A' &&
                (grid[row + 1][col + 1] == 'S' && grid[row - 1][col - 1] == 'M' || grid[row + 1][col + 1] == 'M' && grid[row - 1][col - 1] == 'S')
                && (grid[row + 1][col - 1] == 'M' &&  grid[row - 1][col + 1] == 'S' || grid[row + 1][col - 1] == 'S' &&  grid[row - 1][col + 1] == 'M');


    }
}
