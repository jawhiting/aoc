package com.drinkscabinet.aoc;

import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

public class Day13_2017 {

    private static final String input = "0: 3\n" +
            "1: 2\n" +
            "4: 4\n" +
            "6: 4";

    private static int delay = 0;

    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new StringReader(input));
        // 249444 too low
        List<String> lines = r.lines().collect(Collectors.toList());
        int sum = 1;
        while( sum != 0 ) {
            sum = lines.parallelStream().map(Day13_2017::parse).mapToInt(p -> impact(p.getLeft(), p)).sum();
            System.out.println(delay + " " + sum);
            delay+=2;
        }
    }

    private static int impact(int time, Pair<Integer, Integer> dr) {
        boolean caught = (time+delay) % (dr.getRight()*2-2) == 0;
        if( caught ) {
//            System.out.println("Caught on line " + time);
            return dr.getRight() * dr.getLeft();
        }
        return 0;
    }

    private static Pair<Integer, Integer> parse(String line) {
        String[] v = line.split(":");
        return Pair.of(Integer.valueOf(v[0].trim()), Integer.valueOf(v[1].trim()));
    }

    private static final String input2 = "0: 4\n" +
            "1: 2\n" +
            "2: 3\n" +
            "4: 4\n" +
            "6: 8\n" +
            "8: 5\n" +
            "10: 8\n" +
            "12: 6\n" +
            "14: 6\n" +
            "16: 8\n" +
            "18: 6\n" +
            "20: 6\n" +
            "22: 12\n" +
            "24: 12\n" +
            "26: 10\n" +
            "28: 8\n" +
            "30: 12\n" +
            "32: 8\n" +
            "34: 12\n" +
            "36: 9\n" +
            "38: 12\n" +
            "40: 8\n" +
            "42: 12\n" +
            "44: 17\n" +
            "46: 14\n" +
            "48: 12\n" +
            "50: 10\n" +
            "52: 20\n" +
            "54: 12\n" +
            "56: 14\n" +
            "58: 14\n" +
            "60: 14\n" +
            "62: 12\n" +
            "64: 14\n" +
            "66: 14\n" +
            "68: 14\n" +
            "70: 14\n" +
            "72: 12\n" +
            "74: 14\n" +
            "76: 14\n" +
            "80: 14\n" +
            "84: 18\n" +
            "88: 14";
}
