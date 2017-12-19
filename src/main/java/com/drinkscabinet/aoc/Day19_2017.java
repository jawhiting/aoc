package com.drinkscabinet.aoc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

public class Day19_2017 {

    private static final String testInput =
            "     |          \n" +
            "     |  +--+    \n" +
            "     A  |  C    \n" +
            " F---|----E|--+ \n" +
            "     |  |  |  D \n" +
            "     +B-+  +--+ \n";

    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("Day19_2017.txt")));
//        BufferedReader r = new BufferedReader(new StringReader(testInput));

        List<String> lines = r.lines().collect(Collectors.toList());

        int row = 0;
        int col = lines.get(0).indexOf("|");

        int rD = 1;
        int cD = 0;

        StringBuilder path = new StringBuilder();
        int step = 0;

        while(true) {
            ++step;
            row += rD;
            col += cD;
            char next = lines.get(row).charAt(col);
            if( next == '|' || next == '-') continue;
            if( next == '+') {
                // change direction
                if( rD != 0) {
                    // currently up/down
                    rD = 0;

                    // decide l/r
                    if( isPath(lines, row, col+1) ) {
                        cD = 1;
                    }
                    else if( isPath(lines, row, col-1 ) ) {
                        cD = -1;
                    }
                    else {
                        System.out.println("Reached end (up/down) ");
                        break;
                    }
                }
                else {
                    cD = 0;
                    if( isPath(lines, row+1, col)) {
                        rD = 1;
                    }
                    else if( isPath(lines, row-1, col )){
                        rD = -1;
                    }
                    else {
                        System.out.println("Reached end (lr) ");
                        break;
                    }
                }
            }
            else if( next == ' ' ) {
//                throw new RuntimeException("hit space: " + row + " " + col);
                System.out.println("End run-off");
                break;
            }
            else {
                path.append(next);
            }

        }
        System.out.println("End: " + row + " " + col + " " + path.toString());
        System.out.println(step);

    }

    private static boolean isPath(List<String> grid, int row, int col) {
        if ( row < 0 || row > grid.size()-1) return false;
        if( col < 0 || col > grid.get(row).length()-1) return false;
        char c = grid.get(row).charAt(col);
        return c != ' ';
    }
}
