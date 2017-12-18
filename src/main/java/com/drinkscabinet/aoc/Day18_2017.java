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
public class Day18_2017 {

    private long[] registers = new long[26];
    private List<String> instructions = new ArrayList<>();
    private long lastSound = 0;
    private int ip = 0;

    public static void main(String[] args) {
        new Day18_2017().run();
    }

    public void run() {
//        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("input.txt");
        BufferedReader br = new BufferedReader(new StringReader(input2));
        instructions = br.lines().collect(Collectors.toList());

        // -3989

        while (ip < instructions.size()) {
            execute(instructions.get(ip));
        }

        System.out.println(Arrays.toString(registers));
    }

    public void execute(String instruction) {
        String[] tokens = instruction.split(" ");
        System.out.println("Executing: " + ip + " " + instruction );
        try {
            switch (tokens[0]) {
                case "set":
                    registers[tokens[1].charAt(0) - 'a'] = getValue(tokens[2]);
                    ip++;
                    break;
                case "add":
                    registers[tokens[1].charAt(0) - 'a'] += getValue(tokens[2]);
                    ip++;
                    break;
                case "mul":
                    registers[tokens[1].charAt(0) - 'a'] *= getValue(tokens[2]);
                    ip++;
                    break;
                case "mod":
                    registers[tokens[1].charAt(0) - 'a'] %= getValue(tokens[2]);
                    ip++;
                    break;
                case "snd":
                    lastSound = getValue(tokens[1]);
                    ip++;
                    break;
                case "rcv":
                    if( getValue(tokens[1]) != 0) {
                        System.out.print(lastSound);
                        System.exit(0);
                    }
                    ip++;

                    break;
                case "jgz":
                    long val = getValue(tokens[1]);
                    if (val > 0) {
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
        System.out.println("Result: " + Arrays.toString(registers));
    }

    private long getValue(String token) {
        char first = token.charAt(0);
        if (first > '9') {
            // register
            return registers[first - 'a'];
        }
        return Integer.valueOf(token);
    }


    private static final String input = "set a 1\n" +
            "add a 2\n" +
            "mul a a\n" +
            "mod a 5\n" +
            "snd a\n" +
            "set a 0\n" +
            "rcv a\n" +
            "jgz a -1\n" +
            "set a 1\n" +
            "jgz a -2";

    private static final String input2 = "set i 31\n" +
            "set a 1\n" +
            "mul p 17\n" +
            "jgz p p\n" +
            "mul a 2\n" +
            "add i -1\n" +
            "jgz i -2\n" +
            "add a -1\n" +
            "set i 127\n" +
            "set p 952\n" +
            "mul p 8505\n" +
            "mod p a\n" +
            "mul p 129749\n" +
            "add p 12345\n" +
            "mod p a\n" +
            "set b p\n" +
            "mod b 10000\n" +
            "snd b\n" +
            "add i -1\n" +
            "jgz i -9\n" +
            "jgz a 3\n" +
            "rcv b\n" +
            "jgz b -1\n" +
            "set f 0\n" +
            "set i 126\n" +
            "rcv a\n" +
            "rcv b\n" +
            "set p a\n" +
            "mul p -1\n" +
            "add p b\n" +
            "jgz p 4\n" +
            "snd a\n" +
            "set a b\n" +
            "jgz 1 3\n" +
            "snd b\n" +
            "set f 1\n" +
            "add i -1\n" +
            "jgz i -11\n" +
            "snd a\n" +
            "jgz f -16\n" +
            "jgz a -19";
}
