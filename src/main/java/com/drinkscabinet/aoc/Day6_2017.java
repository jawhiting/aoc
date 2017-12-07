package com.drinkscabinet.aoc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day6_2017 {

    private static final String input = "5\t1\t10\t0\t1\t7\t13\t14\t3\t12\t8\t10\t7\t12\t0\t6";
    private static final String test = "0 2 7 0";
    public static void main(String[] args) {

        int[] ints = Arrays.stream(input.split("[ \\t]")).mapToInt(Integer::valueOf).toArray();

        int c = 0;
        while(!isSeen(ints)) {
            redist(ints, maxPos(ints));
            c++;
        }
        // Now its been seen - reset the seen map
        seen.clear();
        c = 0;
        while(!isSeen(ints)) {
            redist(ints, maxPos(ints));
            c++;
        }

        System.out.println(c);
    }

    private static void redist(int[] v, int pos) {
        int count = v[pos];
        v[pos] = 0;
//        int max = 0;
//        int maxPos = 0;
        for(int i = 1; i <= count; i++) {
            int p = (pos+i)%v.length;
            v[p]++;
//            if( v[p] > max || (v[p] == max && p < maxPos)) {
//                max = v[p];
//                maxPos = p;
//            }

        }
//        return maxPos;

    }

    private static final Set<String> seen = new HashSet<>(10001);

    private static final boolean isSeen(int[] v) {
        System.out.println(Arrays.toString(v));
        return !seen.add(Arrays.toString(v));
    }


    private static int maxPos(int[] v) {
        int max = 0;
        int maxPos = 0;
        for (int i = 0; i < v.length; i++) {
            if(v[i] > max ) {
                max = v[i];
                maxPos = i;
            }
        }
        return maxPos;
    }
}
