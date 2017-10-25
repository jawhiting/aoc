package com.drinkscabinet.aoc;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {

    public static void main(String[] args) {
        new Day8().execute(inst);
    }

    private final boolean[][] grid = new boolean[6][50];

    public void execute(String instructions) {
        BufferedReader reader = new BufferedReader(new StringReader(instructions));
        List<String> lines = reader.lines().collect(Collectors.toList());

        System.out.println(printGrid(grid));

        for (String line : lines) {
            if( line.startsWith("rect")) {
                String[] tokens = line.substring(5).split("x");
                int x = Integer.valueOf(tokens[0]);
                int y = Integer.valueOf(tokens[1]);
                for( int r = 0; r < y; r++) {
                    for( int c = 0; c < x; c++) {
                        grid[r][c] = true;
                    }
                }
            }
            else if( line.startsWith("rotate column")){
                String[] tokens = line.substring(16).split(" by ");
                int column = Integer.valueOf(tokens[0]);
                int count = Integer.valueOf(tokens[1]);
                for( int i = 0; i < count; i++) {
                    rotateColumn(column);
                }

            }
            else if( line.startsWith("rotate row")) {
                String[] tokens = line.substring(13).split(" by ");
                int row = Integer.valueOf(tokens[0]);
                int count = Integer.valueOf(tokens[1]);
                for( int i = 0; i < count; i++) {
                    rotateRow(row);
                }
            }
            System.out.println(printGrid(grid));
        }
    }

    private void rotateColumn(int column) {
        boolean last = grid[grid.length-1][column];
        for( int r = grid.length-1; r > 0; r-- ) {
            grid[r][column] = grid[r-1][column];
        }
        grid[0][column] = last;
    }

    private void rotateRow(int row) {
        boolean last = grid[row][grid[row].length-1];
        for( int c = grid[row].length-1; c > 0; c--) {
            grid[row][c] = grid[row][c-1];
        }
        grid[row][0] = last;
    }

    private String printGrid(boolean[][] grid) {
        StringBuffer buffer = new StringBuffer();
        for( int r = 0; r < grid.length; r++) {
            for( int c = 0; c < grid[r].length; c++) {
                buffer.append(grid[r][c] ? '@' : '.');
            }
            buffer.append(System.lineSeparator());
        }
        return buffer.toString();
    }

    private static String inst = "rect 1x1\n" +
            "rotate row y=0 by 4\n" +
            "rect 1x1\n" +
            "rotate row y=0 by 7\n" +
            "rect 1x1\n" +
            "rotate row y=0 by 10\n" +
            "rect 1x1\n" +
            "rotate row y=0 by 2\n" +
            "rect 1x1\n" +
            "rotate row y=0 by 5\n" +
            "rect 1x1\n" +
            "rotate row y=0 by 4\n" +
            "rect 1x1\n" +
            "rotate row y=0 by 2\n" +
            "rect 1x1\n" +
            "rotate row y=0 by 4\n" +
            "rect 1x1\n" +
            "rotate row y=0 by 4\n" +
            "rect 1x2\n" +
            "rotate row y=0 by 29\n" +
            "rotate column x=17 by 2\n" +
            "rotate column x=10 by 1\n" +
            "rotate column x=0 by 1\n" +
            "rect 20x1\n" +
            "rotate row y=2 by 4\n" +
            "rotate row y=1 by 5\n" +
            "rotate row y=0 by 3\n" +
            "rect 1x4\n" +
            "rotate column x=32 by 1\n" +
            "rotate row y=3 by 30\n" +
            "rotate row y=1 by 18\n" +
            "rotate row y=0 by 14\n" +
            "rotate column x=10 by 3\n" +
            "rotate column x=6 by 3\n" +
            "rotate column x=4 by 1\n" +
            "rotate column x=0 by 1\n" +
            "rect 12x1\n" +
            "rotate row y=2 by 29\n" +
            "rotate column x=5 by 2\n" +
            "rotate row y=2 by 21\n" +
            "rotate column x=15 by 3\n" +
            "rotate column x=2 by 2\n" +
            "rotate row y=3 by 20\n" +
            "rotate row y=2 by 29\n" +
            "rotate row y=0 by 47\n" +
            "rotate column x=11 by 3\n" +
            "rotate column x=8 by 3\n" +
            "rotate column x=7 by 1\n" +
            "rotate column x=6 by 2\n" +
            "rotate column x=5 by 1\n" +
            "rotate column x=4 by 2\n" +
            "rotate column x=3 by 1\n" +
            "rotate column x=1 by 3\n" +
            "rotate column x=0 by 1\n" +
            "rect 13x1\n" +
            "rotate column x=10 by 2\n" +
            "rotate column x=9 by 2\n" +
            "rotate row y=0 by 3\n" +
            "rotate column x=27 by 1\n" +
            "rotate row y=4 by 9\n" +
            "rotate row y=3 by 1\n" +
            "rotate row y=2 by 1\n" +
            "rotate row y=1 by 2\n" +
            "rotate row y=0 by 1\n" +
            "rect 1x5\n" +
            "rotate column x=33 by 1\n" +
            "rotate column x=30 by 4\n" +
            "rotate column x=24 by 1\n" +
            "rotate column x=23 by 1\n" +
            "rotate column x=18 by 1\n" +
            "rotate column x=8 by 1\n" +
            "rotate row y=4 by 23\n" +
            "rotate row y=3 by 1\n" +
            "rotate row y=2 by 1\n" +
            "rotate row y=1 by 3\n" +
            "rotate row y=0 by 1\n" +
            "rect 1x6\n" +
            "rotate column x=40 by 2\n" +
            "rotate column x=30 by 2\n" +
            "rotate column x=17 by 2\n" +
            "rotate column x=16 by 2\n" +
            "rotate row y=5 by 37\n" +
            "rotate row y=4 by 5\n" +
            "rotate row y=3 by 5\n" +
            "rotate row y=2 by 25\n" +
            "rotate row y=1 by 5\n" +
            "rotate row y=0 by 5\n" +
            "rotate column x=41 by 4\n" +
            "rotate column x=38 by 5\n" +
            "rotate column x=36 by 5\n" +
            "rotate column x=33 by 1\n" +
            "rotate column x=32 by 5\n" +
            "rotate column x=31 by 1\n" +
            "rotate column x=28 by 1\n" +
            "rotate column x=27 by 3\n" +
            "rotate column x=26 by 4\n" +
            "rotate column x=25 by 5\n" +
            "rotate column x=18 by 1\n" +
            "rotate column x=17 by 5\n" +
            "rotate column x=16 by 5\n" +
            "rotate column x=15 by 1\n" +
            "rotate column x=13 by 3\n" +
            "rotate column x=12 by 2\n" +
            "rotate column x=11 by 2\n" +
            "rotate column x=10 by 3\n" +
            "rotate column x=8 by 1\n" +
            "rotate column x=5 by 1";
}
