package com.drinkscabinet.aoc;

import java.util.LinkedList;

public class Day17_2017 {

    public static void main(String[] args) {
        new Day17_2017().run();
    }

    private final int step = 301;
    private LinkedList<Integer> list = new LinkedList<>();
    private int cp = 0;

    private void run() {
        list.add(0, 0);

        long start = System.currentTimeMillis();
        for( int i = 1; i <= 50000000; i++) {
            cp = (cp+step) % i;
            if( cp++ == 0 ) {
                System.out.println("Switching to " + i);
            }
        }

        System.out.println("Took " + (System.currentTimeMillis() - start));
        System.exit(0);
        cp = 0;
        // changes whenever (cp + step) % cp is 0
        for( int i = 1; i <= 2017; i++) {
            cp = (cp + step) % i;
            list.add(++cp, i);

//            int i0 = list.indexOf(0);
//            System.out.println(i + ": 0 at " + i0 + " Next: " + list.get(i0+1));

            if( i % 100000 == 0 ) System.out.println(i);
//            System.out.println("CP: " + cp + "\t" + list);
        }

        for( int i = cp-3; i < cp+3; i++) {
            System.out.println(list.get(i));
        }
    }

}
