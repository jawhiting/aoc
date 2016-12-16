package com.drinkscabinet.aoc;

/**
 * Created by jwhiting on 16/12/2016.
 */
public class Day16 {

    private final int length;
    private final String seed;
    private final boolean part2;

    public Day16(int length, String seed, boolean part2) {
        this.length = length;
        this.seed = seed;
        this.part2 = part2;
    }

    public void run() {
        String s = seed;
        while( s.length() < length ) {
            s = flipReverseIt(s);
            System.out.println("Length: " + s.length());
        }
        s = s.substring(0, length);
        String checksum = checksum(s);
        //System.out.println(s);
        System.out.println(checksum);
    }

    private static String checksum(String s) {
        StringBuilder cs = new StringBuilder(s.length()/2+10);
        char[] chars = s.toCharArray();

        for( int i = 0; i < s.length()/2; i++ ) {
            // Get each pair
            if( chars[i*2] == chars[i*2+1]) {
                cs.append(1);
            }
            else {
                cs.append(0);
            }
            if( i % 10000 == 0 )
            System.out.println("CSPos: " + i);
        }
        if( cs.length() % 2 == 0 ) {
            return checksum(cs.toString());
        }
        else {
            return cs.toString();
        }
    }

    private static String flipReverseIt(String s) {
        StringBuilder result = new StringBuilder(s.length()*2+1);
        result.append(s);
        result.append(0);
        char[] chars = s.toCharArray();
        for( int i = chars.length-1; i >= 0; i--) {
            if( chars[i] == '1') {
                result.append('0');
            }
            else {
                result.append('1');
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        new Day16(20, "10000", false).run();
        new Day16(35651584, "10011111011011001", false).run();

    }

}
