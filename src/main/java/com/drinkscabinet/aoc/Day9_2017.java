package com.drinkscabinet.aoc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day9_2017 {

    private static final String input = //"106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36"
                                      "106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36";

    private int[] list;
    private int currentPosition = 0;
    private int skip = 0;

    public static void main(String[] args) {
        // 7adfd64c2a3a4968cf78d1b7fd418d - fail
        new Day9_2017().run(input, 64);
    }

    public void run(String input, int rounds) {

        int[] lengths = parseLengths(input);
        System.out.println(Arrays.toString(lengths));
        list = IntStream.range(0, 256).toArray();

        for( int r = 0; r < rounds; r++ ) {
            for (Integer length : lengths) {
                reverse(currentPosition, length);
                currentPosition = currentPosition + length + skip;
                currentPosition %= list.length;
                ++skip;
//                System.out.println(Arrays.toString(list));
            }
//            System.out.println(Arrays.toString(list));
        }
//        System.out.println(list[0] * list[1]);
        String hash = denseHash(list);
        System.out.println(hash);
        System.out.println(hash.length());
    }

    private static String denseHash(int[] ints) {
        StringBuilder result = new StringBuilder(32);
        for( int i = 0; i < ints.length; i += 16) {
            int h = 0;
            for( int j = 0; j < 16; j++) {
                h ^= ints[i+j];
            }
            String hex = Integer.toHexString(h);
            if( hex.length() < 2) {
                hex = "0"+hex;
            }
            result.append(hex);
        }
        return result.toString();
    }



    private static int[] parseLengths(String s) {
        int[] ints = s.trim().chars().toArray();
        int[] padding = new int[]{17, 31, 73, 47, 23};
        int[] result = new int[ints.length + padding.length];
        System.arraycopy(ints, 0, result, 0, ints.length);
        System.arraycopy(padding, 0, result, ints.length, padding.length);
        return result;

    }

    private void reverse(int start, int length) {
        int[] v = new int[length];
        // copy values from the list into a reversed list
        for( int i = 0; i < length; i++ ) {
            v[v.length-i-1] = list[(start+i)%list.length];
        }

        // now copy them back
        for( int i = 0; i < length; i++ ) {
            list[(start+i)%list.length] = v[i];
        }
    }

}
