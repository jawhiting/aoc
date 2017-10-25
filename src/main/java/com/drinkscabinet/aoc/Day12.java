package com.drinkscabinet.aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jwhiting on 12/12/2016.
 */
public class Day12 {

    private int[] registers = new int[4];
    private List<String> instructions = new ArrayList<>();
    private int ip = 0;

    public static void main(String[] args) {
        new Day12().run();
    }

    public void run() {
        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("bonus.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(systemResourceAsStream));
        instructions = br.lines().collect(Collectors.toList());

        while (ip < instructions.size()) {
            execute(instructions.get(ip));
        }

        System.out.println(Arrays.toString(registers));
    }

    public void execute(String instruction) {
        String[] tokens = instruction.split(" ");
//        System.out.println("Executing: " + ip + " " + instruction );
        try {
            switch (tokens[0]) {
                case "inc":
                    registers[tokens[1].charAt(0) - 'a']++;
                    ip++;
                    break;
                case "dec":
                    registers[tokens[1].charAt(0) - 'a']--;
                    ip++;
                    break;
                case "cpy":
                    registers[tokens[2].charAt(0) - 'a'] = getValue(tokens[1]);
                    ip++;
                    break;
                case "out":
                    System.out.print((char)getValue(tokens[1]));
                    ip++;
                    break;
                case "jnz":
                    int val = getValue(tokens[1]);
                    if (val != 0) {
                        ip += getValue(tokens[2]);
                    } else {
                        ip++;
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("Got exception on inst: " + instruction + " ip=" + ip + " tokens=" + Arrays.toString(tokens) + " registers=" + Arrays.toString(registers));
            e.printStackTrace();
            throw e;
        }
    }

    private int getValue(String token) {
        char first = token.charAt(0);
        if( first > '9' ) {
            // register
            return registers[first-'a'];
        }
        return Integer.valueOf(token);
    }


    private static final String input = "cpy 41 a\n" +
            "inc a\n" +
            "inc a\n" +
            "dec a\n" +
            "jnz a 2\n" +
            "dec a";

    private static final String input2 = "cpy 1 a\n" +
            "cpy 1 b\n" +
            "cpy 26 d\n" +
            "jnz c 2\n" +
            "jnz 1 5\n" +
            "cpy 7 c\n" +
            "inc d\n" +
            "dec c\n" +
            "jnz c -2\n" +
            "cpy a c\n" +
            "inc a\n" +
            "dec b\n" +
            "jnz b -2\n" +
            "cpy c b\n" +
            "dec d\n" +
            "jnz d -6\n" +
            "cpy 19 c\n" +
            "cpy 14 d\n" +
            "inc a\n" +
            "dec d\n" +
            "jnz d -2\n" +
            "dec c\n" +
            "jnz c -5";
}
